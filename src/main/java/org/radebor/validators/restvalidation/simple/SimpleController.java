package org.radebor.validators.restvalidation.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SimpleController {

    private static final Logger log = LoggerFactory.getLogger(SimpleController.class);

    @PostMapping("/handle")
    public boolean handle(
            @Valid
            @RequestBody RestPojo pojo) {
        return true;
    }
}
