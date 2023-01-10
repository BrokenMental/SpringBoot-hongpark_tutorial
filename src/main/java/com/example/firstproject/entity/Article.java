package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //DB가 해당 객체를 인식할 수 있음
@AllArgsConstructor
@NoArgsConstructor //디폴트 생성자를 추가
@ToString
public class Article {

    @Id //primary
    @GeneratedValue //auto_increment
    private Long id;

    @Column
    private String title;

    @Column
    private String content;
}
