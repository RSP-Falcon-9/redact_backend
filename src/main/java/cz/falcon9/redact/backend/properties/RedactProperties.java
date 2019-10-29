package cz.falcon9.redact.backend.properties;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("redact")
public class RedactProperties {

    @NotBlank
    private String jwtSecret;
    
    public void setJwtSecret(String jwtSecret) {
    	this.jwtSecret = jwtSecret;
    }
    
    public String getJwtSecret() {
    	return jwtSecret;
    }

}
