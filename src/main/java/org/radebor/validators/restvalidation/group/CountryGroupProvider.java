package org.radebor.validators.restvalidation.group;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryGroupProvider implements DefaultGroupSequenceProvider<UserRequest> {

    @Override
    public List<Class<?>> getValidationGroups(UserRequest userRequest) {
        List<Class<?>> result = new ArrayList<>();
        result.add(UserRequest.class);
        if (userRequest != null) {
            if (userRequest.getCountry() == Country.FI) {
                result.add(CountryGroup.FICountryGroup.class);
            }
            if (userRequest.getCountry() == Country.US) {
                result.add(CountryGroup.USCountryGroup.class);
            }
        }

        return result;
    }
}
