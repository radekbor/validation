package org.radebor.validators.restvalidation.group;

import org.hibernate.validator.group.GroupSequenceProvider;
import org.radebor.validators.restvalidation.group.Country;
import org.radebor.validators.restvalidation.group.CountryGroup;
import org.radebor.validators.restvalidation.group.CountryGroupProvider;

import javax.validation.constraints.Pattern;

@GroupSequenceProvider(CountryGroupProvider.class)
public class UserRequest {
    private UserRequest() {
    }


    private Country country;

    @Pattern.List({
            @Pattern(message = "invalid.ssn.fi", regexp = "^[0-9]{6}-(?:[0-9]+){1,3}[0-9A-Za-z]$", groups = CountryGroup.FICountryGroup.class),
            @Pattern(message = "invalid.ssn.us", regexp = "^(\\d{3}-\\d{2}-\\d{4})|(\\d{3}\\d{2}\\d{4})$", groups = CountryGroup.USCountryGroup.class)
    })
    private String ssn;

    public Country getCountry() {
        return country;
    }

    public String getSsn() {
        return ssn;
    }
}
