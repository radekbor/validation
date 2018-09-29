package org.radebor.validators.restvalidation;

import org.hibernate.validator.constraints.Length;

public class RestPojo {

    @Length(min = 4)
    private String id;

    public String getId() {
        return id;
    }
}
