package org.radebor.validators.methodvalidation;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class MyPojo {

    @NotNull
    private Long id;

    MyPojo(@NotNull Long id) {
        this.id = id;
    }
}
