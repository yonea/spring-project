package com.fr.yoni.registrant.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Date;

/**
 * LoggingAspect class using AOP to track executed methods and calculate processing time
 * Inspired by thanks to https://howtodoinjava.com/spring-boot2/logging/performance-logging-aspectj-aop//**
 * @author Yoni Baroukh
 */
@Aspect
@Component
public class LoggingAspect
{
    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    /**
     * AOP expression for which methods shall be intercepted
     * @param proceedingJoinPoint
     * @return Object
     * @throws Throwable
     */
    @Around("execution(* com.fr.yoni.registrant.service..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method with execution time
        LOGGER.info("Method " + className + "." + methodName + "execution started at " + new Date());
        LOGGER.info("Execution time of method " + className + "." + methodName + " : " +  stopWatch.getTotalTimeMillis() + " ms");
        LOGGER.info("Method " + className + "." + methodName + "execution ended at " + new Date());

        return result;
    }

    @AfterThrowing(pointcut = "execution(* com.fr.yoni.registrant.service..*(..)))", throwing = "exception")
    public void logAfterThrowingAllMethods(Exception exception) {
        LOGGER.error(exception.getMessage());
    }
}