package com.glory.jwtreferenceproject.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Test
    public void createUser() {
        User user = new User("tebo@gmail.com", passwordEncoder.encode("tebo1234"));
        User savedUser = userRepository.save(user);
        assert savedUser.getId() > 0;
        assert savedUser.getEmail() != null;
    }

    @Test
    public void assignRoleToUser() {
        Integer userId = 3;

        User user = userRepository.findById(userId).get();
        user.addRole(new Role(3));

        User savedUser = userRepository.save(user);
        assert savedUser.getRoles().size() == 1;
    }

}