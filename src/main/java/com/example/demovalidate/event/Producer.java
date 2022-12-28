package com.example.demovalidate.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 项目名称：demo-validate
 * 类 名 称：Producer
 * 类 描 述：TODO
 * 创建时间：2022/12/8 11:25
 * 创 建 人：panyong
 */
@RequiredArgsConstructor
@Component
public class Producer {

    private final ApplicationEventPublisher eventPublisher;

    public void publishEvent(Object event){
        eventPublisher.publishEvent(event);
    }
}
