package ee.ivkhk.springsecuritydemo.config;

import ee.ivkhk.springsecuritydemo.entity.MyUser;
import ee.ivkhk.springsecuritydemo.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminConfig {
    @Autowired
    private MyUserService myUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public CommandLineRunner insertSuperUser() {
        return args -> {
            if (myUserService.allUsers().isEmpty()) {
                MyUser admin = new MyUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("12345"));
                admin.getRoles().add("ADMIN");
                admin.getRoles().add("MANAGER");
                admin.getRoles().add("USER");
                myUserService.save(admin);
            }
        };
    }
}
