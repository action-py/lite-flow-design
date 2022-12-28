package com.example.demovalidate.service.status;

import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.inter.status.FinishStatusProcessBase;
import com.example.demovalidate.service.abs.StatusAbstractProcessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 项目名称：demo-validate
 * 类 名 称：FinishStatusProcessService
 * 类 描 述：TODO
 * 创建时间：2022/12/10 17:59
 * 创 建 人：panyong
 */
@Service
public class FinishStatusProcessService implements FinishStatusProcessBase {

    @Autowired
    private Map<String, StatusAbstractProcessTemplate> nodeStatusProcessMap;


    @Override
    public String releaseDoc(ProcessEvent event) {
        StatusAbstractProcessTemplate template = nodeStatusProcessMap.get("finishStatusProcessTemplate");
        Object handler = template.handler(event);
        return handler.toString();
    }

    @Override
    public String audit(ProcessEvent event) {
        //todo something business logic
        return null;
    }

    @Override
    public String releaseAnnounce(ProcessEvent event) {
        return null;
    }

    @Override
    public String edit(ProcessEvent event) {
        return null;
    }

    @Override
    public String confirm(ProcessEvent event) {
        return null;
    }

    @Override
    public String approve(ProcessEvent event) {
        return null;
    }

    @Override
    public String sign(ProcessEvent event) {
        return null;
    }
}
