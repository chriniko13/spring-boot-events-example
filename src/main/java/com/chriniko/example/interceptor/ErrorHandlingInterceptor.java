package com.chriniko.example.interceptor;


import com.chriniko.example.event.ErrorEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorHandlingInterceptor {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Pointcut("@annotation(ErrorHandlingAware)")
    void errorHandlingAwarePointcut() {
    }

    @AfterThrowing(pointcut = "errorHandlingAwarePointcut()", throwing = "error")
    public void handleError(JoinPoint joinPoint, Exception error) {

        applicationEventPublisher.publishEvent(new ErrorEvent(this, error));

        System.out.println("ErrorHandlingInterceptor ----> error: " + error);

    }

}
