package cz.falcon9.redact.backend.providers;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.falcon9.redact.backend.config.RedactConfig;

@Component
public class RedactConfigProvider {

    @Autowired
    private RedactConfig config;
    
    @PostConstruct
    public void createDirs() {
        // create articles directory if it doesn't exist
        File articlesDir = new File(config.getArticlesDir());
        if (!articlesDir.exists()) {
            articlesDir.mkdirs();
        }
        
        // create archives directory if it doesn't exist
        File archivesDir = new File(config.getArchivesDir());
        if (!archivesDir.exists()) {
            archivesDir.mkdirs();
        }
    }
    
    public String getArticlesPath() {
        return config.getArticlesDir();
    }
    
    public String getArchivesPath() {
        return config.getArchivesDir();
    }
    
    public String getJwtSecret() {
        return config.getJwtSecret();
    }
    
    public String getArticlePdfFilePath(String articleId, Integer articleVersion) {
        return config.getArticlesDir()
                .concat(File.separator)
                .concat(articleId)
                .concat("_")
                .concat(articleVersion.toString())
                .concat(".pdf");
    }
    
    public String getArchiveZipFilePath(Integer number) {
        return config.getArchivesDir()
                .concat(File.separator)
                .concat(number.toString())
                .concat(".zip");
    }
    
}
