package org.radebor.validators.restvalidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.Constraint;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class ExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object test(MethodArgumentNotValidException e) {
        Map<String, String> result = new HashMap<>();

        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError objectError : allErrors) {
            Object[] arguments = objectError.getArguments();
            arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
            log.info("{} \n {} \n {} \n {}", objectError.getObjectName(), arguments, objectError.getCode(), objectError.getCodes());
            Locale locale = LocaleContextHolder.getLocale();
            log.info("{} {}", locale, objectError.getDefaultMessage());
            try {
                String errorMessage = messageSource
                        .getMessage(objectError.getDefaultMessage(), objectError.getArguments(), locale);
                result.put(objectError.getObjectName(), errorMessage);
            } catch (NoSuchMessageException ex) {
                result.put(objectError.getObjectName(), objectError.getDefaultMessage());
            }
        }
        return result;
    }
}
