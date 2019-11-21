package cz.falcon9.redact.backend.services;

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
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import cz.falcon9.redact.backend.data.dtos.author.CreateArticleRequest;
import cz.falcon9.redact.backend.data.models.articles.Article;
import cz.falcon9.redact.backend.data.models.articles.ArticleVersion;
import cz.falcon9.redact.backend.data.models.auth.User;
import cz.falcon9.redact.backend.exceptions.ArgumentNotFoundException;
import cz.falcon9.redact.backend.exceptions.ForbiddenException;
import cz.falcon9.redact.backend.exceptions.InternalException;
import cz.falcon9.redact.backend.exceptions.InvalidArgumentException;
import cz.falcon9.redact.backend.properties.RedactProperties;
import cz.falcon9.redact.backend.repositories.ArticleRepository;
import cz.falcon9.redact.backend.repositories.UserRepository;

@Service
@Secured("ROLE_AUTHOR")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    RedactProperties redactProps;
    
    @Autowired
    ArticleRepository articleRepo;
    
    @Autowired
    UserRepository userRepo;
    
    @Override
    public List<Article> getAllArticles(String authorId) {
        return articleRepo.findAllByAuthor(authorId);
    }
    
    @Override
    public Article insertArticle(CreateArticleRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepo.findById(authentication.getName());
        
        if (!user.isPresent()) {
            // TODO: Log
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
                .build());
        
        Article article = Article.builder()
                .withId(UUID.randomUUID().toString())
                .withName(request.getName())
                .withUser(user.get())
                .withVersions(versions)
                .build();
        
        articleRepo.save(article);
        
        try (InputStream is = request.getFile().getInputStream()) {
            byte[] buffer = new byte[is.available()]; 
            File f = new File(redactProps.getArticlesDir().concat(File.separator).concat(fileName));
            OutputStream os = new FileOutputStream(f);

            is.read(buffer);
            os.write(buffer);
            os.close();
        } catch (IOException ex) {
            //log.error("IOException caught: {}", ex);
            
            throw new InternalException(String.format("Cannot upload file with name %s!", request.getFile().getOriginalFilename()));
        }
        
        return article;
    }
    
    @Override
    public FileSystemResource getArticleFile(String articleId, Integer version) {
        Optional<Article> optionalArticle = articleRepo.findById(articleId);
        if (!optionalArticle.isPresent()) {
            throw new ArgumentNotFoundException(String.format("Image %s does not exist.", articleId));
        }
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Article article = optionalArticle.get();
        if (article.getUser().getUserName() != authentication.getName()) {
            throw new ForbiddenException("You don't have access to this article!");
        }
        
        File articleFile = new File(redactProps.getArticlesDir().concat(File.separator)
                .concat(articleId).concat("_").concat(version.toString()).concat(".pdf"));
        if (!articleFile.exists()) {
            throw new ArgumentNotFoundException("File for give article/version doesn't exist.");
        }

        return new FileSystemResource(articleFile);
    }

}
