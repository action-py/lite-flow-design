package com.example.demovalidate.strategy;

import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.service.abs.StatusAbstractProcessTemplate;

public interface StatusProcessStrategy {

    Object handler(StatusAbstractProcessTemplate template, ProcessEvent event);
}
