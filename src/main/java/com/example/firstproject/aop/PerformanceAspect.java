package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    //특정 어노테이션을 지정(해당 어노테이션이 실행 될 때)
    @Pointcut("@annotation(com.example.firstproject.annotaion.RunningTime)")
    private void enableRunningTime() {

    }

    //기본 패키지의 모든 메소드를 지정
    @Pointcut("execution(* com.example.firstproject..*.*(..))")
    private void cut() {

    }

    //Around 는 모든 조건을 만족하는 대상을 전후로 부가 기능 삽입
    @Around("cut() && enableRunningTime()") //&&으로 동시조건 사용 가능
    public void loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable { //ProceedingJoinPoint 는 대상을 실행까지 할 수 있음
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object returningObj = joinPoint.proceed();
        stopWatch.stop();

        //메소드 명
        String methodName = joinPoint.getSignature()
                .getName();

        log.info("{}의 총 수행 시간 => {} sec", methodName, stopWatch.getTotalTimeSeconds());
    }
}
