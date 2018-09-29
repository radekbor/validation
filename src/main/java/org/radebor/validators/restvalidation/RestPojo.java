package org.radebor.validators.restvalidation;

import org.hibernate.validator.constraints.Length;

public class RestPojo {

    @Length(min = 4, message = "error.id.to-short")
    @Length(max = 10, message = "error.id.to-long")
    private String id;

    public String getId() {
        return id;
    }
}
