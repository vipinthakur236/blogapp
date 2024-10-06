package com.blogging.blogapp.comments;

import com.blogging.blogapp.articles.ArticleEntity;
import com.blogging.blogapp.articles.ArticlesRepository;
import com.blogging.blogapp.users.UserEntity;
import com.blogging.blogapp.users.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class CommentsRepositoryTests {

    @Autowired
    private ArticlesRepository articlesRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CommentsRepository commentsRepository;

    @Test
    @Order(1)
    void can_create_comment() {
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

        var comment = CommentEntity.builder()
                .article(article)
                .author(user)
                .body("Thanks for the suggestion").build();

        commentsRepository.save(comment);
    }

    @Test
    @Order(2)
    void can_find_comments() {
        var userA = UserEntity.builder()
                .username("testUserA")
                .email("testUserA@blog.com").build();
        usersRepository.save(userA);

        var article = ArticleEntity.builder()
                .author(userA)
                .title("Title A")
                .body("Content A")
                .slug("my-first-blog-post").build();
        articlesRepository.save(article);

        var commentA = CommentEntity.builder()
                .article(article)
                .author(userA)
                .body("Thanks for the suggestion").build();

        var userB = UserEntity.builder()
                .username("testUserB")
                .email("testUserB@blog.com").build();
        usersRepository.save(userB);

        var commentB = CommentEntity.builder()
                .article(article)
                .author(userB)
                .body("Share more info").build();

        commentsRepository.save(commentA);
        commentsRepository.save(commentB);
    }
}
