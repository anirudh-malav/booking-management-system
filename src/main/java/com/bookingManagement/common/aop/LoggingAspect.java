package com.bookingManagement.common.aop;

import com.bookingManagement.common.annotations.EnableLogging;
import com.bookingManagement.common.constants.Constants;
import com.bookingManagement.common.util.LoggingUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Order(1)
public class LoggingAspect {
    @Around("@annotation(logging)")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint, EnableLogging logging) throws Throwable {
        loggingBeforeReturning(joinPoint);

        long startTime = System.currentTimeMillis();
        try {
            Object retValue = joinPoint.proceed();
            long timeElapsed = System.currentTimeMillis() - startTime;
            loggingAfterReturning(joinPoint, retValue, timeElapsed);
            return retValue;
        } catch (Throwable throwable) {
            loggingAfterThrowing(joinPoint, throwable);
            throw throwable;
        }
    }
    private void loggingBeforeReturning(JoinPoint joinPoint) {
        Map<String, String> loggingData = new HashMap<>();
        loggingData.put(Constants.Logging.KEY_CLASS_NAME, joinPoint.getSignature().getDeclaringTypeName());
        loggingData.put(Constants.Logging.KEY_METHOD_NAME, joinPoint.getSignature().getName());
        loggingData.put(Constants.Logging.KEY_METHOD_ARGUMENTS, Arrays.toString(joinPoint.getArgs()));
        loggingData.put(Constants.Logging.KEY_METHOD_PHASE, Constants.Logging.METHOD_PHASE_STARTED);
        LoggingUtil.info(loggingData);
    }

    private void loggingAfterReturning(JoinPoint joinPoint, Object retValue, long timeElapsed) {
        Map<String, String> loggingData = new HashMap<>();
        loggingData.put(Constants.Logging.KEY_CLASS_NAME, joinPoint.getSignature().getDeclaringTypeName());
        loggingData.put(Constants.Logging.KEY_METHOD_NAME, joinPoint.getSignature().getName());
        loggingData.put(Constants.Logging.KEY_METHOD_ARGUMENTS, Arrays.toString(joinPoint.getArgs()));
        loggingData.put(Constants.Logging.KEY_METHOD_PHASE, Constants.Logging.METHOD_PHASE_COMPLETED);
        loggingData.put(Constants.Logging.KEY_RETURN_VALUE, String.valueOf(retValue));
        loggingData.put(Constants.Logging.KEY_TIME_ELAPSED_IN_MILLIS, String.valueOf(timeElapsed));
        LoggingUtil.info(loggingData);
    }

    private void loggingAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        Map<String, String> loggingData = new HashMap<>();
        loggingData.put(Constants.Logging.KEY_CLASS_NAME, joinPoint.getSignature().getDeclaringTypeName());
        loggingData.put(Constants.Logging.KEY_METHOD_NAME, joinPoint.getSignature().getName());
        loggingData.put(Constants.Logging.KEY_METHOD_ARGUMENTS, Arrays.toString(joinPoint.getArgs()));
        loggingData.put(Constants.Logging.KEY_METHOD_PHASE, Constants.Logging.METHOD_PHASE_EXCEPTION_THROWN);
        loggingData.put(Constants.Logging.KEY_CAUSE, String.valueOf(throwable.getCause()));
        loggingData.put(Constants.Logging.KEY_STACK_TRACE, Arrays.toString(throwable.getStackTrace()));
        loggingData.put(Constants.Logging.KEY_LOCALIZED_MESSAGE, throwable.getLocalizedMessage());
        loggingData.put(Constants.Logging.KEY_SUPPRESSED, Arrays.toString(throwable.getSuppressed()));
        loggingData.put(Constants.Logging.KEY_MESSAGE, throwable.getMessage());
        LoggingUtil.error(loggingData);
    }
}
