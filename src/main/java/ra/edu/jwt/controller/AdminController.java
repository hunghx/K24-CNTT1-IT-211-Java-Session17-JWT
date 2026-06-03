package ra.edu.jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String test(){
        return "test successfully";
    }
}
