package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component //IoC 컨테이너가 해당 객체를 생성 및 관리
@Aspect //AOP 클래스 선언, 부가 기능을 주입하는 클래스
public class DebuggingAspect {

    //특정 타겟을 지정해서 그 안에 부가 기능을 주입할 것인지 지정
    @Pointcut("execution(* com.example.firstproject.api.*.*(..))") //api 패키지의 모든 메소드
    //@Pointcut("execution(* com.example.firstproject.service.CommentService.*(..))") //CommentService 의 모든 메소드가 실행될 때
    //@Pointcut("execution(* com.example.firstproject.service.CommentService.create(..))") //CommentService 의 create 가 실행될 때 만
    private void cut() {

    }

    //실행 시점을 설정, 특정 부가기능을 어느 시점에 실행할 지 지정하는 어노테이션
    //Before 는 대상이 수행되기 이전에 실행
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) { //JoinPoint : 지정한 대상(여기서는 cut()) 메소드
        Object[] args = joinPoint.getArgs();

        //클래스 명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        //메소드 명
        String methodName = joinPoint.getSignature()
                .getName();

        for(Object obj: args) {
            log.info("{}#{}의 입력 값 => {}", className, methodName, obj);
        }
    }

    //AfterReturning 은 대상이 호출 성공 후 실행
    //두번쨰 parameter 를 사용할 경우, 어노테이션에 returning 옵션을 추가해야 함, 반드시 명칭이 동일해야 함
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint,
                                   Object returnObj) { //두번째 parameter 를 받아올 경우, 대상의 로직이 끝난 후 리턴값이 입력 됨
        //클래스 명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        //메소드 명
        String methodName = joinPoint.getSignature()
                .getName();

        log.info("{}#{}의 반환 값 => {}", className, methodName, returnObj);
    }
}
