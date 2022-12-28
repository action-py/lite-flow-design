package com.example.demovalidate.event;

import com.example.demovalidate.service.global.ProcessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 项目名称：demo-validate
 * 类 名 称：Consumer
 * 类 描 述：TODO
 * 创建时间：2022/12/8 11:25
 * 创 建 人：panyong
 */
@Slf4j
@Component
public class Consumer {
    @Autowired
    private ProcessHandler processHandler;

    @EventListener(value = ProcessEvent.class)
    public void eventHandler(ProcessEvent event){
        if(event instanceof PostEvent){
            log.info("event 类型:{}",event.getClass().getSimpleName());
        }
        if(event instanceof SignEvent){
            log.info("event 类型:{}",event.getClass().getSimpleName());
        }
        log.info("event stage:{}",event.getNode().getStage());
        log.info("接收到的事件:{}",event);
        processHandler.handle(event);
    }
}
