package com.chriniko.example.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CodeLogger {

    boolean printParamsValues() default false;

    String callMethodWithNoParamsToString() default "toString";
}
