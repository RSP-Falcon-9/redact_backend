package cz.falcon9.redact.backend.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cz.falcon9.redact.backend.data.articles.ArticleReviewStatus;
import cz.falcon9.redact.backend.data.articles.ArticleStatus;
import cz.falcon9.redact.backend.data.models.articles.Article;
import cz.falcon9.redact.backend.data.models.articles.ArticleReview;
import cz.falcon9.redact.backend.data.models.articles.ArticleVersion;
import cz.falcon9.redact.backend.data.models.auth.User;
import cz.falcon9.redact.backend.exceptions.ArgumentNotFoundException;
import cz.falcon9.redact.backend.exceptions.ForbiddenException;
//import cz.falcon9.redact.backend.exceptions.ForbiddenException;
import cz.falcon9.redact.backend.exceptions.InternalException;
import cz.falcon9.redact.backend.exceptions.InvalidArgumentException;
import cz.falcon9.redact.backend.properties.RedactProperties;
import cz.falcon9.redact.backend.repositories.ArticleRepository;
import cz.falcon9.redact.backend.repositories.UserRepository;
import cz.falcon9.redact.backend.services.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);
    
    @Autowired
    RedactProperties redactProps;
    
    @Autowired
    ArticleRepository articleRepo;
    
    @Autowired
    UserRepository userRepo;
    
    @Override
    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }
    
    @Override
    public List<Article> getAllArticles(String authorId) {
        return articleRepo.findAllByAuthor(authorId);
    }
   
    @Override
    public List<Article> getNewArticles() {
        return articleRepo.findNew();
    }
    
    @Override
    public List<Article> getAllArticlesForReviewer(String reviewerId) {
        return articleRepo.findByReviewer(reviewerId);
    }
    
    @Override
    public Article insertNewArticle(String name, MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepo.findById(authentication.getName());
        
        if (!user.isPresent()) {
            log.debug("User is not present in repo, but in auth is: {}", authentication.getName());
            
            throw new InvalidArgumentException(String.format("User %s is not valid!", authentication.getName()));
        }

        String articleId = UUID.randomUUID().toString();
        String fileName = articleId.concat("_").concat("0").concat(".pdf");
        ArrayList<ArticleVersion> versions = new ArrayList<ArticleVersion>();
        
        versions.add(ArticleVersion.builder()
                .withArticleId(articleId)
                .withFileName(fileName)
                .withVersion(0)
                .withPublishDate(new Date(Calendar.getInstance().getTime().getTime()))
                .withStatus(ArticleStatus.NEW)
                .build());
        
        Article article = Article.builder()
                .withId(articleId)
                .withName(name)
                .withUser(user.get())
                .withVersions(versions)
                .build();

        try (InputStream is = file.getInputStream()) {
            // create folder if it doesn't exist
            File dir = new File(redactProps.getArticlesDir());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            byte[] buffer = new byte[is.available()]; 
            File f = new File(dir.getCanonicalPath().concat(File.separator).concat(fileName));
            OutputStream os = new FileOutputStream(f);
            
            log.debug("Testing file output dir: {}", dir.getCanonicalPath().concat(File.separator).concat(fileName));
            
            is.read(buffer);
            os.write(buffer);
            os.close();
        } catch (IOException ex) {  
            throw new InternalException(String.format("Cannot upload file with name %s!", file.getOriginalFilename()));
        }
        
        articleRepo.save(article);
        
        return article;
    }
    
    @Override
    public Article insertNewArticleVersion(String id, MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepo.findById(authentication.getName());
        
        if (!user.isPresent()) {
            log.debug("User is not present in repo, but in auth is: {}", authentication.getName());
            
            throw new InvalidArgumentException(String.format("User %s is not valid!", authentication.getName()));
        }

        Optional<Article> optionalArticle = articleRepo.findById(id);
        if (!optionalArticle.isPresent()) {
            throw new ArgumentNotFoundException(String.format("Article %s doesn't exist!", authentication.getName()));
        }
        
        Article article = optionalArticle.get();
        ArticleVersion articleVersion = article.getLatestVersion();
        if (articleVersion.getStatus() != ArticleStatus.DENIED) {
            throw new InvalidArgumentException(String.format("Cannot upload new version until article %s version %s is denied!",
                    id, articleVersion.getVersion()));
        }
        
        Integer nextVersion = articleVersion.getVersion() + 1;
        String fileName = id.concat("_").concat(String.valueOf(nextVersion)).concat(".pdf");
        
        article.getVersions().add(ArticleVersion.builder()
                .withArticleId(id)
                .withFileName(fileName)
                .withVersion(nextVersion)
                .withPublishDate(new Date(Calendar.getInstance().getTime().getTime()))
                .withStatus(ArticleStatus.NEW)
                .build());

        try (InputStream is = file.getInputStream()) {
            // create folder if it doesn't exist
            File dir = new File(redactProps.getArticlesDir());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            byte[] buffer = new byte[is.available()]; 
            File f = new File(dir.getCanonicalPath().concat(File.separator).concat(fileName));
            OutputStream os = new FileOutputStream(f);
            
            log.debug("Testing file output dir: {}", dir.getCanonicalPath().concat(File.separator).concat(fileName));
            
            is.read(buffer);
            os.write(buffer);
            os.close();
        } catch (IOException ex) {  
            throw new InternalException(String.format("Cannot upload file with name %s!", file.getOriginalFilename()));
        }
        
        articleRepo.save(article);
        
        return article;
    }
    
    @Override
    public Article getArticle(String articleId) {
        Optional<Article> optionalArticle = articleRepo.findById(articleId);
        if (!optionalArticle.isPresent()) {
            throw new ArgumentNotFoundException(String.format("Article %s does not exist.", articleId));
        }
        
        return optionalArticle.get();
    }
    
    @Override
    @Transactional
    public FileSystemResource getArticleFile(String articleId, Integer version) {
        Optional<Article> optionalArticle = articleRepo.findById(articleId);
        if (!optionalArticle.isPresent()) {
            throw new ArgumentNotFoundException(String.format("No file for article %s exists.", articleId));
        }
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());
        Article article = optionalArticle.get();
        boolean allowedToSee = roles.contains("ROLE_ADMIN") || roles.contains("ROLE_CHIEF_EDITOR") || roles.contains("ROLE_EDITOR");
        
        if (roles.contains("ROLE_AUTHOR") && article.getUser().getUserName().equals(authentication.getName())) {
            allowedToSee = true;
        }
        
        if (roles.contains("ROLE_REVIEWER") &&
                (article.getVersion(version).getReviewWithReviewer(authentication.getName()) != null)) {
            allowedToSee = true;
        }
        
        if (!allowedToSee) {
            throw new ForbiddenException("You don't have access to this article!");
        }
        
        File articleFile = new File(redactProps.getArticlesDir().concat(File.separator)
                .concat(articleId).concat("_").concat(version.toString()).concat(".pdf"));
        if (!articleFile.exists()) {
            throw new ArgumentNotFoundException("File for give article/version doesn't exist.");
        }

        return new FileSystemResource(articleFile);
    }
    
    @Override
    public void updateArticle(Article article) {
        articleRepo.save(article);
    }
    
    @Override
    @Transactional
    public void assignReviewerToArticle(String articleId, Integer version, String reviewerId) {
        Article article = getArticle(articleId);
        ArticleVersion articleVersion = article.getVersion(version);
        
        if (articleVersion == null) {
            throw new ArgumentNotFoundException(String.format("Cannot find article version %s.", version));
        }
        
        Optional<User> optionalReviewer = userRepo.findById(reviewerId);
        if (!optionalReviewer.isPresent()) {
            throw new ArgumentNotFoundException(String.format("Cannot find reviewer with id %s.", reviewerId));
        }
        
        Optional<ArticleReview> optionalArticleReview = articleVersion.getReviews().stream().filter(articleRev ->
            articleRev.getReviewer().getUserName().equals(reviewerId)).findFirst();
        if (optionalArticleReview.isPresent()) {
            throw new InvalidArgumentException(String.format("Reviewer %s was already assigned for this article %s and version %s.",
                    reviewerId, articleId, version));
        }
        
        articleVersion.setStatus(ArticleStatus.REVIEW_PENDING);
        articleVersion.getReviews().add(ArticleReview.builder()
                .withId(UUID.randomUUID().toString())
                .withArticleId(articleId)
                .withVersion(version)
                .withReviewStatus(ArticleReviewStatus.NEW)
                .withReviewer(optionalReviewer.get())
                .build());
       
        updateArticle(article);
    }
    
    @Override
    @Transactional
    public void setArticleStatus(String articleId, Integer version, ArticleStatus status) {
        Article article = getArticle(articleId);
        ArticleVersion articleVersion = article.getVersion(version);
        
        if (articleVersion == null) {
            throw new ArgumentNotFoundException(String.format("Cannot find article version %s.", version));
        }
        
        articleVersion.setStatus(status);
        
        updateArticle(article);
    }
    
}
