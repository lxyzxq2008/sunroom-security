package com.sunroom.security.demo.validator;

import com.sunroom.security.demo.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private IHelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("valid value : "+value);
        System.out.println(helloService.greet(String.valueOf(value)));
        System.out.println(context);
        return false;
    }
}
