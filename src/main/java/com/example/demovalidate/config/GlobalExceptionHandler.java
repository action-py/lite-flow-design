package com.example.demovalidate.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.metadata.ConstraintDescriptor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 项目名称：demo-validate
 * 类 名 称：GlobalExceptionHandler
 * 类 描 述：TODO
 * 创建时间：2022/12/7 11:06
 * 创 建 人：panyong
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    public String validErrorHandler(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        List<String> results = new ArrayList<>();
        Set<String> sensitiveSets = new HashSet<>();
        //遍历
        if (!CollectionUtils.isEmpty(errors)) {
            for (ObjectError error : errors) {
                ConstraintViolationImpl constraintViolation = error.unwrap(ConstraintViolationImpl.class);
                ConstraintDescriptor constraintDescriptor = constraintViolation.getConstraintDescriptor();
                constraintDescriptor.getAnnotation().annotationType();
                results.add(error.getDefaultMessage());
                //特定注解
                /*if (ValidateWord.class.isAssignableFrom(constraintDescriptor.getAnnotation().annotationType())) {
                    String[] split = Objects.requireNonNull(error.getDefaultMessage()).split(",");
                    sensitiveSets.addAll(Arrays.asList(split));
                } else {
                    //其他异常
                    results.add(error.getDefaultMessage());
                }*/
            }
            if (!CollectionUtils.isEmpty(sensitiveSets)) {
                results.add(0, String.format("您所输入的内容中包含敏感词，请重新输入，敏感词：%s", String.join(" ", sensitiveSets)));
            }
        }
        return results.stream().collect(Collectors.joining(" "));
    }
}
