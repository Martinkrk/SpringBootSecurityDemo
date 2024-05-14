package ee.ivkhk.springsecuritydemo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object credentials = authentication.getCredentials();
            if (credentials != null) {
                model.addAttribute("password",credentials.toString());
            }else{
                model.addAttribute("password","Пароль = null");
            }
            model.addAttribute("username",authentication.getName());
            model.addAttribute("roles", authentication.getAuthorities().toString());
        }

        model.addAttribute("title", "Homepage");
        model.addAttribute("text", "Hello, folks");
        return "index";
    }

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("title", "User");
        model.addAttribute("text", "Hello, user");
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("title", "Admin");
        model.addAttribute("text", "Hello, admin");
        return "admin";
    }
}
