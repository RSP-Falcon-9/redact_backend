package cz.falcon9.redact.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import cz.falcon9.redact.backend.config.RedactConfig;

@SpringBootApplication
@EnableConfigurationProperties({
    RedactConfig.class,
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
