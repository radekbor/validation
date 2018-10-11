package org.radebor.validators.restvalidation.group;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CreateUserController {

    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @PostMapping("/user")
    public boolean validateUser(@Valid @RequestBody UserRequest userRequest) {
        log.info("user = {} {}", userRequest.getCountry(), userRequest.getSsn());
        return true;
    }
}
