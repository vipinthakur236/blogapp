package com.blogging.blogapp.articles;

import com.blogging.blogapp.users.UserEntity;
import com.blogging.blogapp.users.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class ArticlesRepositoryTests {

    @Autowired
    private ArticlesRepository articlesRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Order(1)
    void can_create_article() {
        var user = UserEntity.builder()
                .username("testUser")
                .email("testUser@blog.com").build();
        usersRepository.save(user);

        var article = ArticleEntity.builder()
                .author(user)
                .title("Title A")
                .body("Content A")
                .slug("my-first-blog-post").build();
        articlesRepository.save(article);
    }

    @Test
    @Order(2)
    void can_find_articles() {
        var user = UserEntity.builder()
                .username("testUser")
                .email("testUser@blog.com").build();
        usersRepository.save(user);

        var articleA = ArticleEntity.builder()
                .author(user)
                .title("Title A")
                .body("Content A")
                .slug("my-first-blog-post").build();
        articlesRepository.save(articleA);

        var articleB = ArticleEntity.builder()
                .author(user)
                .title("Title B")
                .body("Content B")
                .slug("my-second-blog-post").build();
        articlesRepository.save(articleB);

        var articles = articlesRepository.findAll();
        assert 2 == articles.size();
    }
}
