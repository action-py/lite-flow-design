package com.example.demovalidate.service.abs.impl;

import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.service.abs.StatusAbstractProcessTemplate;
import org.springframework.stereotype.Component;

/**
 * 项目名称：demo-validate
 * 类 名 称：FinishStatusProcessTemplate
 * 类 描 述：TODO
 * 创建时间：2022/12/10 20:28
 * 创 建 人：panyong
 */
@Component("finishStatusProcessTemplate")
public class FinishStatusProcessTemplate extends StatusAbstractProcessTemplate {
    @Override
    public Boolean validateData(ProcessEvent event) {
        return true;
    }

    @Override
    public Object business(ProcessEvent event, Boolean flag) {
        return event;
    }

    @Override
    public Object notice(ProcessEvent event, Object o) {
        return event;
    }
}
