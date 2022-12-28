package com.example.demovalidate.strategy;

import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.service.abs.StatusAbstractProcessTemplate;
import org.springframework.stereotype.Service;

/**
 * 项目名称：demo-validate
 * 类 名 称：SimpleStatusProcessStrategy
 * 类 描 述：TODO
 * 创建时间：2022/12/10 19:36
 * 创 建 人：panyong
 */
@Service
public class SimpleStatusProcessStrategy implements StatusProcessStrategy{
    @Override
    public Object handler(StatusAbstractProcessTemplate template, ProcessEvent event) {
        Boolean validateFlag = template.validateData(event);
        Object business = template.business(event, validateFlag);
        Object notice = template.notice(event, business);
        return null;
    }
}
