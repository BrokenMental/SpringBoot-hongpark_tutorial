package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest //this class testing link to spring boot
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        Article a = new Article(1L, "aaaaa", "1111"); //why add 1st param word 'L'? : 1 is int, not Long type
        Article b = new Article(2L, "bbbbb", "2222");
        Article c = new Article(3L, "ccccc", "3333");
        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));

        List<Article> articles = articleService.index();

        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show__successs() {
        long id = 1L;
        Article expected = new Article(id, "aaaaa", "1111");

        Article article = articleService.show(id);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show__fail_not_found_id() {
        long id = -1L;
        Article expected = null;

        Article article = articleService.show(id);

        assertEquals(expected, article); //we expect 'expected' class is null, null is not follow .toString()
    }

    @Test
    @Transactional //if not select ddl, after run test rollback
    void create__success_input_data_title_and_content() {
        String title = "dddd";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        Article article = articleService.create(dto);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create__fail_input_all_data() {
        String title = "dddd";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content); //ArticleForm class not input id
        Article expected = null;

        Article article = articleService.create(dto);

        assertEquals(expected, article);
    }
}