package cz.falcon9.redact.backend.config;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("redact")
public class RedactConfig {

    @NotBlank
    private String jwtSecret;
    
    @NotBlank
    private String articlesDir;
    
    @NotBlank
    private String archivesDir;
    
    public String getArchivesDir() {
        return archivesDir;
    }

    public void setArchivesDir(String archivesDir) {
        this.archivesDir = archivesDir;
    }

    public String getArticlesDir() {
        return articlesDir;
    }

    public String getJwtSecret() {
    	return jwtSecret;
    }

    public void setArticlesDir(String articlesDir) {
        this.articlesDir = articlesDir;
    }
    
    public void setJwtSecret(String jwtSecret) {
    	this.jwtSecret = jwtSecret;
    }

}
