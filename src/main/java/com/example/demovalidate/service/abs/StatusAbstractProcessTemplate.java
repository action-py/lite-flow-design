package com.example.demovalidate.service.abs;

import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.strategy.SimpleStatusProcessStrategy;
import com.example.demovalidate.strategy.StatusProcessStrategy;

/**
 * 项目名称：demo-validate
 * 类 名 称：FinishStatusAbseratProcess
 * 类 描 述：TODO
 * 创建时间：2022/12/10 19:02
 * 创 建 人：panyong
 */
public abstract class StatusAbstractProcessTemplate {

    public abstract Boolean validateData(ProcessEvent event);

    public abstract Object business(ProcessEvent event,Boolean flag);

    public abstract Object notice(ProcessEvent event,Object o);
    public Object handler(ProcessEvent event){
        return handler(event,null);
    }
    public Object handler(ProcessEvent event, StatusProcessStrategy strategy){
        if(null == strategy){
            strategy = new SimpleStatusProcessStrategy();
        }
        return strategy.handler(this,event);
    }

}
