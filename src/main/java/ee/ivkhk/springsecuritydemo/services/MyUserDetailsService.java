package ee.ivkhk.springsecuritydemo.services;

import ee.ivkhk.springsecuritydemo.entity.MyUser;
import ee.ivkhk.springsecuritydemo.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ee.ivkhk.springsecuritydemo.security.MyUserDetails;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<MyUser> myUser = myUserRepository.findByUsername(username);
        return myUser.map(MyUserDetails::new).orElseThrow(()->new UsernameNotFoundException(username + "Not found"));
    }
}
