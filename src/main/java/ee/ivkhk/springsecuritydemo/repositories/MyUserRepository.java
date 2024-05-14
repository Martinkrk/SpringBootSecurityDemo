package ee.ivkhk.springsecuritydemo.repositories;

import ee.ivkhk.springsecuritydemo.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    public Optional<MyUser> findByUsername(String username);
}
