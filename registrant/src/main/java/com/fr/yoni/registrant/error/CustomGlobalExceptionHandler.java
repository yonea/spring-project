package com.fr.yoni.registrant.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Set;

/**
 * ConrollerAdvice class to return the wrongly filled in mandatory fields
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(ConstraintViolationException exception, HttpServletResponse response) throws IOException {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        StringBuilder builder = new StringBuilder();

        for (ConstraintViolation<?> violation : violations) {
            builder.append(" - ").append(violation.getMessage());
        }

        response.sendError(HttpStatus.BAD_REQUEST.value(), builder.toString());
    }

}
