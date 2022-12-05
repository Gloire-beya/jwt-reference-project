package com.glory.jwtreferenceproject.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoleRepositoryTest {

    @Autowired RoleRepository roleRepository;

    @Test
    public void createRoles(){
        Role admin = new Role("ROLE_ADMIN");
        Role customer = new Role("ROLE_CUSTOMER");
        Role editor = new Role("ROLE_EDITOR");
        roleRepository.saveAll(List.of(admin, editor, customer));
        assert roleRepository.count() == 3;
    }

    @Test
    public void listRoles(){
        List<Role> roleList = roleRepository.findAll();
    }
}