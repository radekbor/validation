package org.radebor.validators.restvalidation.simple;

import org.hibernate.validator.constraints.Length;

public class RestPojo {

    @Length(min = 4, message = "error.id.to-short")
    @Length(max = 10, message = "error.id.to-long")
    private String id;

    @Length(min = 5, max = 10, message = "error.ssn.length-between")
    private String ssn;

    @Length(max = 10, min = 5, message = "error.ssn.length-between")
    private String partnerSsn;

    public String getId() {
        return id;
    }

    public String getSsn() {
        return ssn;
    }
}
