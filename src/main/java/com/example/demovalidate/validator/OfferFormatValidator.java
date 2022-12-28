package com.example.demovalidate.validator;


import com.example.demovalidate.annotation.OfferFormat;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 项目名称：cupid
 * 类 名 称：OfferFormatValidator
 * 类 描 述：TODO
 * 创建时间：2022/12/6 18:28
 * 创 建 人：panyong
 */
public class OfferFormatValidator implements ConstraintValidator<OfferFormat,String> {
    @Override
    public void initialize(OfferFormat constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        System.err.println(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(s);
        System.out.println(constraintValidatorContext);
        return !(null==s || s.equals(""));
    }
}
