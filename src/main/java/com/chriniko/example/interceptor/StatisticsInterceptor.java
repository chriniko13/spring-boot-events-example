package com.chriniko.example.interceptor;

import com.chriniko.example.service.StatisticsService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class StatisticsInterceptor {

    @Autowired
    private StatisticsService statisticsService;

    @Pointcut("execution(* (@StatisticsAware *).*(..))")
    void methodOfAnnotatedClass() {}

    @Around("@annotation(com.chriniko.example.interceptor.StatisticsAware) || methodOfAnnotatedClass())")
    public Object intercept(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("Inside StatisticsInterceptor#intercept....");

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        StatisticsAware statisticsAware = method.getAnnotation(StatisticsAware.class);
        boolean isClassDecorated = statisticsAware == null;
        if (isClassDecorated) {
            Class<?> declaringClass = method.getDeclaringClass();
            statisticsAware = declaringClass.getAnnotation(StatisticsAware.class);
        }

        switch (statisticsAware.resourceName()) {
            case "ticket-resource":
                statisticsService.increaseTicketResourceCounter();
                break;

            default:
                throw new IllegalStateException();
        }

        return pjp.proceed();

    }

}
