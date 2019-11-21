package cz.falcon9.redact.backend.data.dtos.author;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class CreateArticleRequest {
    
    @NotEmpty
    private String name;

    @NotNull
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }
    
    public String getName() {
        return name;
    }
    
}
