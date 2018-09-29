package org.radebor.validators.methodvalidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class MyService {

    private static final Logger log = LoggerFactory.getLogger(MyService.class);

    public void foo(
            @Valid
            @NotNull(message = "can't be null") MyPojo pojo) {
        log.info("inside foo");
    }
}
