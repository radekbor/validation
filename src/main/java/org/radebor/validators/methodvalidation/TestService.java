package org.radebor.validators.methodvalidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TestService {

    private static final Logger log = LoggerFactory.getLogger(TestService.class);

    private MyService myService;

    @Autowired
    TestService(MyService myService) {
        this.myService = myService;
    }

    @PostConstruct
    public void test() {
        log.info("test");
        try {
            myService.foo(null);
        } catch (Exception e) {
            log.error("null passed");
        }
        try {
            MyPojo myPojo = new MyPojo(null);
            myService.foo(myPojo);
        } catch (Exception e) {
            log.error("not valid object passed");
        }

    }
}
