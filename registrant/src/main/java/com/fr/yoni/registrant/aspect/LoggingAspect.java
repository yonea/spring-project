package com.fr.yoni.registrant.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Date;

/**
 * LoggingAspect class using AOP to track executed methods and calculate processing time
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

        /* Get intercepted method details */
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Object[] methodInput = proceedingJoinPoint.getArgs();

        final StopWatch stopWatch = new StopWatch();

        /* Measure method execution time */
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        /* Log method with input, output and execution time */
        LOGGER.info("Method " + className + "." + methodName + " execution started at " + new Date());
        LOGGER.info("Input : " + Arrays.toString(methodInput));
        LOGGER.info("Output : " + result);
        LOGGER.info("Execution time of method " + className + "." + methodName + " : " +  stopWatch.getTotalTimeMillis() + " ms");
        LOGGER.info("Method " + className + "." + methodName + " execution ended at " + new Date());

        return result;
    }

    @AfterThrowing(pointcut = "execution(* com.fr.yoni.registrant.service..*(..)))", throwing = "exception")
    public void logAfterThrowingAllMethods(JoinPoint joinPoint, Exception exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        /* Get intercepted method details */
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Object[] methodInput = joinPoint.getArgs();
        String messageError = exception.getMessage();

        /* Log method with input and exception message */
        LOGGER.info("Method " + className + "." + methodName + "execution started at " + new Date());
        LOGGER.info("Input : " + Arrays.toString(methodInput));

        if (exception instanceof ConstraintViolationException) {
            LOGGER.error(getFieldsInException(messageError));
        } else {
            LOGGER.error(messageError);
        }

    }

    /**
     * Method to get indications on the mandatory field
     * @param messageError entire error message
     * @return StringBuilder list of missing mandatory fields
     */
    public StringBuilder getFieldsInException(String messageError){
        StringBuilder msgLogError = new StringBuilder();
        String[] parts = messageError.split("messageTemplate='");
        for (int i = 1; i < parts.length; i++){
            int endMessage = parts[i].indexOf("'}");
            String content = parts[i].substring(0, endMessage);
            msgLogError.append(" - ").append(content);
        }
        return msgLogError;
    }
}