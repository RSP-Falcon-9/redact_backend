package cz.falcon9.redact.backend.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Secured("ROLE_ANONYMOUS")
public class ClientController {
	
    @GetMapping("/bruda")
    public String handleBruda() {
        return "ITS OKAY MAJ BRUDA";
    }
    
    @GetMapping("/bruda2")
    public String handleBruda2() {
        return "ITS OKAY MAJ BRUDA2";
    }

}
