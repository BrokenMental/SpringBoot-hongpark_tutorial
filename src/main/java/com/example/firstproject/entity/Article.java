package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //DB가 해당 객체를 인식할 수 있음
@AllArgsConstructor
@NoArgsConstructor //디폴트 생성자를 추가
@ToString
public class Article {

    @Id //primary
    //@GeneratedValue //auto_increment(warning : will duplicate idx)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB auto make idx
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void patch(Article article) {
        if(article.title != null) {
            this.title = article.title;
        }

        if(article.content != null) {
            this.content = article.content;
        }
    }
}
