package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(long id, ArticleForm dto) {
        Article article = dto.toEntity();
        log.info("id : {}, article: {}", id, article.toString());

        Article target = articleRepository.findById(id).orElse(null);

        //if it doesn't exist target or if not match id
        if(target == null || id != article.getId()) {
            log.info("잘못된 요청, id: {}, article: {}", id, article);
            return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(article);
        return updated;
    }

    public Article delete(long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if(target == null) {
            return null;
        }

        articleRepository.delete(target);
        return target;
    }

    @Transactional //method contain transaction, transaction used Service Area
    public List<Article> createArticles(List<ArticleForm> dtos) {
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        /*
        //위 stream 문법을 풀어 쓸 경우 아래와 같음
        List<Article> articleList = new ArrayList<>();
        for(int i=0; i<dtos.size(); i++) {
            ArticleForm dto = dtos.get(i);
            Article entity = dto.toEntity();
            articleList.add(entity);
        }
        */

        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        /*
        //위 stream 문법을 풀어 쓸 경우
        for(int i=0; i<articleList.size(); i++) {
            Article article = articleList.get(i);
            articleRepository.save(article);
        }
        */

        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패")
        );

        return articleList;
    }
}
