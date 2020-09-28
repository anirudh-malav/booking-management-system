package com.bookingManagement.common.aop;

import com.bookingManagement.common.annotations.SetMappedDiagnosticContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.json.JSONObject;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class MappedDiagnosticContextAspect {

    @Around("@annotation(mappedDiagnosticContext)")
    public Object setMDC(ProceedingJoinPoint joinPoint, SetMappedDiagnosticContext mappedDiagnosticContext) throws Throwable {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("RequestController", joinPoint.getSignature().getDeclaringType().getName());
        jsonObject.put("RequestMethod", joinPoint.getSignature().getName());
        int argIndex = 0;
        for(Object arg: joinPoint.getArgs()) {
            jsonObject.put(String.format("params[%s]", argIndex), String.valueOf(arg));
            argIndex++;
        }
        MDC.put(mappedDiagnosticContext.actionType().name(), String.valueOf(jsonObject));
        return joinPoint.proceed();
    }
}