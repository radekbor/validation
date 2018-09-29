package org.radebor.validators.methodvalidation;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class MyPojo {

    @NotNull(message = "id can't be null")
    private Long id;

    MyPojo(@NotNull Long id) {
        this.id = id;
    }
}
