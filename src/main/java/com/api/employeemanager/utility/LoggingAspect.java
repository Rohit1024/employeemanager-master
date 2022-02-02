package com.api.employeemanager.utility;

import com.api.employeemanager.exception.CustomException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect
{

    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
    @AfterThrowing(pointcut = 
            "execution(* com.api.employeemanager.service.EmployeeService.*(..))",
            throwing = "exception")
    public void logServiceException(CustomException exception)
    {
	    LOGGER.error(exception.getMessage(), exception);
    }

}
