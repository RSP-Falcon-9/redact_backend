package cz.falcon9.redact.backend.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.falcon9.redact.backend.data.articles.ArticleStatus;
import cz.falcon9.redact.backend.data.models.articles.Article;
import cz.falcon9.redact.backend.data.models.editions.EditionEntity;
import cz.falcon9.redact.backend.exceptions.ArgumentNotFoundException;
import cz.falcon9.redact.backend.exceptions.InternalException;
import cz.falcon9.redact.backend.exceptions.InvalidArgumentException;
import cz.falcon9.redact.backend.providers.RedactConfigProvider;
import cz.falcon9.redact.backend.repositories.ArticleRepository;
import cz.falcon9.redact.backend.repositories.EditionRepository;
import cz.falcon9.redact.backend.services.EditionService;

@Service
public class EditionServiceImpl implements EditionService {

    @Autowired
    private RedactConfigProvider config;
    
    @Autowired
    private EditionRepository editionRepo;
    
    @Autowired
    private ArticleRepository articleRepo;
    
    
    @Override
    public List<EditionEntity> getEditions() {
        List<EditionEntity> editions = new ArrayList<>();
        
        editionRepo.findAll().forEach(editions::add);;
        
        return editions;
    }
    
    @Override
    public List<EditionEntity> getUnarchivedEditions() {
        List<EditionEntity> editions = new ArrayList<>();
        
        editionRepo.findAllUnarchived().forEach(editions::add);;
        
        return editions;
    }
    
    @Override
    public List<EditionEntity> getArchivedEditions() {
        List<EditionEntity> editions = new ArrayList<>();
        
        editionRepo.findAllArchived().forEach(editions::add);;
        
        return editions;
    }
    
    @Override
    public FileSystemResource getArchiveFile(Integer number) {
        File articleFile = new File(config.getArchiveZipFilePath(number));
        if (!articleFile.exists()) {
            throw new ArgumentNotFoundException("File for given archive doesn't exist.");
        }

        return new FileSystemResource(articleFile);
    }
    
    @Override
    public EditionEntity getEdition(Integer number) {
        if (number == null) return null;
        
        return editionRepo.findById(number).orElseThrow(() ->
            new InvalidArgumentException(String.format("Edition %s is not valid!", number))
        );
    }
    
    @Override
    public EditionEntity createNew(String description, ZonedDateTime deadline) {
        return editionRepo.save(
                EditionEntity.builder()
                .withDescription(description)
                .withDeadline(deadline)
                .withArchived(false)
                .build());
    }

    @Override
    public EditionEntity edit(Integer number, String description, ZonedDateTime deadline) {
        EditionEntity edition = editionRepo.findById(number).orElseThrow(() ->
            new InvalidArgumentException(String.format("Edition %s is not valid!", number))
        );
        
        return editionRepo.save(
                EditionEntity.builder()
                .withId(edition.getId())
                .withDescription(description)
                .withDeadline(deadline)
                .withArchived(edition.isArchived())
                .build());
    }

    @Override
    @Transactional
    public void archive(Integer number) {
        EditionEntity edition = editionRepo.findById(number).orElseThrow(() ->
            new InvalidArgumentException(String.format("Edition %s is not valid!", number))
        );
        
        if (edition.isArchived()) return;
        
        List<Article> editionArticles = articleRepo.findByEdition(number);
        
        if (editionArticles.size() == 0) return;
        
        ListIterator<Article> editionArticlesIterator = editionArticles.listIterator();
        File file = new File(config.getArchiveZipFilePath(number));
        // Create a buffer for reading the files
        byte[] buf = new byte[1024];
        int archivedArticles = 0;
        
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(file));
            
            while(editionArticlesIterator.hasNext()) {
                Article article = editionArticlesIterator.next();
                
                // skip unaccepted articles
                if (article.getLatestVersion().getStatus() != ArticleStatus.ACCEPTED) continue;
                
                // get file reference
                System.out.println(config.getArticlePdfFilePath(article.getId(),
                        article.getLatestVersion().getVersion()));
                File articleFile = new File(config.getArticlePdfFilePath(article.getId(),
                        article.getLatestVersion().getVersion()));
                FileInputStream articleFis = new FileInputStream(articleFile);
                
                // add ZIP entry to output stream
                zos.putNextEntry(new ZipEntry(articleFile.getName()));
                
                // transfer bytes from the file to the ZIP file
                int len;
                while((len = articleFis.read(buf)) > 0) {
                    zos.write(buf, 0, len);
                }
                
                // complete the entry
                zos.closeEntry();
                articleFis.close();
                ++archivedArticles;
            }
            
            zos.close();
        } catch (IOException ex) {
            throw new InternalException(String.format("Cannot zip archive!"));
        }
        
        if (archivedArticles == 0) return;
        
        editionRepo.save(
                EditionEntity.builder()
                .withId(edition.getId())
                .withDescription(edition.getDescription())
                .withDeadline(edition.getDeadline())
                .withArchived(true)
                .build());
    }

    @Override
    public void delete(Integer number) {
        editionRepo.deleteById(number);
    }
    
}
