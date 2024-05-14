package ee.ivkhk.springsecuritydemo.controllers;

import ee.ivkhk.springsecuritydemo.entity.MyUser;
import ee.ivkhk.springsecuritydemo.repositories.MyUserRepository;
import ee.ivkhk.springsecuritydemo.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MyUserService myUserService;
    @GetMapping("/registration")
    public String addNewUser(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("text", "");
        return "registration";
    }

    @PostMapping("/registration")
    public String storeNewUser(
            Model model,
            @RequestParam String username,
            @RequestParam String password) {
        MyUser myUser = new MyUser();
        myUser.setUsername(username);
        myUser.setPassword(passwordEncoder.encode(password));
        myUser.getRoles().add("USER");
        myUserService.save(myUser);
        model.addAttribute("info", String.format("The user [%s] has been added", myUser.getUsername()));
        return  "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String authorize() {

        return "redirect:/";
    }
}
