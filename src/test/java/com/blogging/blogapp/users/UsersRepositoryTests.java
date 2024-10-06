package com.blogging.blogapp.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UsersRepositoryTests {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Order(1)
    void can_create_user() {
        var user = UserEntity.builder()
                .username("testUser")
                .email("testUser@blog.com").build();
        usersRepository.save(user);
    }

    @Test
    @Order(2)
    void can_find_users() {
        var user = UserEntity.builder()
                .username("testUser")
                .email("testUser@blog.com").build();
        usersRepository.save(user);

        var users = usersRepository.findAll();
        assert 1 == users.size();
    }
}
