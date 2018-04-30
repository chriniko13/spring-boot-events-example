package com.chriniko.example.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CodeLoggerAware {

    private static final String DASH_LINE = "===================================";
    private static final String NEXT_LINE = "\n";
    private static final Logger log = LoggerFactory.getLogger(CodeLoggerAware.class);

    @Pointcut("@annotation(codeLog)")
    public void codeLogger(CodeLogger codeLog) {
    }

    @Before("codeLogger(codeLog)")
    public void doCodeLogger(JoinPoint jp, CodeLogger codeLog) {

        StringBuilder str = new StringBuilder(NEXT_LINE);
        str.append(DASH_LINE);
        str.append(NEXT_LINE);

        str.append(" Class: " + getClassSimpleName(jp));

        str.append(NEXT_LINE);

        str.append(" Method: " + getMethodName(jp));

        str.append(NEXT_LINE);

        if (codeLog.printParamsValues()) {

            Object[] args = jp.getArgs();
            str.append(NEXT_LINE);

            for (Object obj : args) {

                str.append(" Param: " + obj.getClass().getSimpleName());
                str.append(NEXT_LINE);

                try {
                    String methodToCall = codeLog.callMethodWithNoParamsToString();

                    if ("toString".equals(methodToCall)) {
                        str.append(" Value: " + obj);
                    } else {
                        str.append(" Value: " + obj
                                .getClass()
                                .getDeclaredMethod(methodToCall)
                                .invoke(obj));
                    }

                } catch (Exception e) {
                    str.append(" Value: [ERROR]> " + e.getMessage());
                }

                str.append(NEXT_LINE);
            }
        }

        str.append(DASH_LINE);
        log.info(str.toString());
    }

    private String getMethodName(JoinPoint jp) {
        return jp.getSignature().getName();
    }

    private String getClassSimpleName(JoinPoint jp) {
        return jp.getTarget().getClass().getSimpleName();
    }
}
