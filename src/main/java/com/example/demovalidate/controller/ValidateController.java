package com.example.demovalidate.controller;

import com.example.demovalidate.dto.OfferDto;
import com.example.demovalidate.inter.PlanA;
import com.example.demovalidate.inter.PlanB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 项目名称：demo-validate
 * 类 名 称：ValidateController
 * 类 描 述：TODO
 * 创建时间：2022/12/7 10:32
 * 创 建 人：panyong
 */
@Slf4j
@RestController
public class ValidateController {
    @PostMapping("/validate")
    public Long validate(@Validated({PlanB.class}) @RequestBody OfferDto dto){
        log.info("{}",dto);
        return 1l;
    }

    @PostMapping("/validate1")
    public Long validate1(@Valid @RequestBody OfferDto dto){
        log.info("{}",dto);
        return 1l;
    }
}
