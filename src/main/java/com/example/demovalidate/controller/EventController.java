package com.example.demovalidate.controller;

import com.example.demovalidate.event.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称：demo-validate
 * 类 名 称：EventController
 * 类 描 述：TODO
 * 创建时间：2022/12/8 11:30
 * 创 建 人：panyong
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class EventController {
    private final Producer producer;

    @PostMapping("/event")
    public String event(@RequestBody PostEvent event){
        log.info("请求入参:{}",event);
        producer.publishEvent(event);
        return "success";
    }

    @PostMapping("/event2")
    public String event2(@RequestBody CustomerEvent event){
        log.info("请求入参:{}",event);
        producer.publishEvent(event);
        return "success";
    }

    @PostMapping("/event3")
    public String event3(@RequestBody SignEvent event){
        log.info("请求入参:{}",event);
        producer.publishEvent(event);
        return "success";
    }
}
