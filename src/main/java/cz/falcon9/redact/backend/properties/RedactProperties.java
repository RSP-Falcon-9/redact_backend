package cz.falcon9.redact.backend.properties;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("redact")
public class RedactProperties {

    @NotBlank
    private String jwtSecret;
    
    @NotBlank
    private String articlesDir;
    
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
