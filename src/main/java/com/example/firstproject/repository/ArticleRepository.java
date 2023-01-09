package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    //CrudRepository : crud 관련 interface, <{관리할 entity 명},{해당 entity primary Column 자료형}>

}
