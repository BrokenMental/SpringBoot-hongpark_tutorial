package com.example.firstproject.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//어노테이션 생성
@Target({ElementType.TYPE, ElementType.METHOD}) //어노테이션 적용 대상
@Retention(RetentionPolicy.RUNTIME) //어노테이션 유지 기간
public @interface RunningTime {

}
