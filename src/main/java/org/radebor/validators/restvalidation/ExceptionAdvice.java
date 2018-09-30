package org.radebor.validators.restvalidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.Constraint;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class ExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object test(MethodArgumentNotValidException e) {
        Map<String, String> result = new HashMap<>();

        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for(ObjectError objectError: allErrors) {
            Object[] arguments = objectError.getArguments();
            arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
            log.info("{} \n {} \n {} \n {}", objectError.getObjectName(), arguments, objectError.getCode(), objectError.getCodes());
            String errorMessage = messageSource
                    .getMessage(objectError.getDefaultMessage(), objectError.getArguments(), Locale.getDefault());
            result.put(objectError.getObjectName(), errorMessage);
        }

        return result;
    }
}
