package com.bookingManagement.common.aop;

import com.bookingManagement.common.annotations.ValidateRequest;
import com.bookingManagement.common.constants.Constants;
import com.bookingManagement.common.helper.APIResponse;
import com.bookingManagement.model.UserDetails;
import com.bookingManagement.service.impl.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Order(0)
@Component
public class ValidateRequestAspect {
    private Logger logger = LoggerFactory.getLogger(ValidateRequestAspect.class);

    private final UserService userService;

    @Autowired
    public ValidateRequestAspect(UserService userService) {
        this.userService = userService;
    }

    @Around("@annotation(setServletRequest)")
    public Object setMDC(ProceedingJoinPoint joinPoint, ValidateRequest setServletRequest) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        String userEmail = request.getHeader(Constants.Header.USER_EMAIL);
        String userPassword = request.getHeader(Constants.Header.USER_PASSWORD);

        String uri = request.getRequestURI();
        logger.info("userEmail: {}, Uri: {}", userEmail, uri);

        if (StringUtils.isEmpty(userEmail) || StringUtils.isEmpty(userPassword)) {
            return APIResponse.renderFailure("Missing request headers", 401, HttpStatus.UNAUTHORIZED);
        }
        validateRequest(userEmail, userPassword);
        return joinPoint.proceed();
    }

    private UserDetails validateRequest(String userEmail, String userPassword) {
        return userService.validateUser(userEmail, userPassword);
    }

}
