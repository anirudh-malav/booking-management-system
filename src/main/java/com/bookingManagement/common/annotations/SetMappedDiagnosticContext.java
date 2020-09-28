package com.bookingManagement.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SetMappedDiagnosticContext {
    ActionType actionType() default ActionType.API_REQUEST;

    public enum ActionType {
        API_REQUEST
    }
}
