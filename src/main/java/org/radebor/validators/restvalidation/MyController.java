package org.radebor.validators.restvalidation;

import org.radebor.validators.methodvalidation.MyPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MyController {

    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    @PostMapping("/handle")
    public boolean handle(
            @Valid
            @RequestBody RestPojo pojo) {
        return true;
    }
}
