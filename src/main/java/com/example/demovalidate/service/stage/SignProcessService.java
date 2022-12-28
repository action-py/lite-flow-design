package com.example.demovalidate.service.stage;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.event.SignEvent;
import com.example.demovalidate.inter.node.SignNodeProcessBase;
import com.example.demovalidate.inter.node.base.StageNodeProcessBase;
import com.example.demovalidate.inter.stage.BusinessProcessBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 项目名称：demo-validate
 * 类 名 称：SignProcessService
 * 类 描 述：命名规则：阶段名称+ProcessService
 * 创建时间：2022/12/8 15:35
 * 创 建 人：panyong
 */
@Slf4j
@Service
public class SignProcessService implements BusinessProcessBase, Ordered {


    @Autowired
    private List<SignNodeProcessBase> nodeProcessList;
    public Boolean isCurrentStage(ProcessEvent event) {
        log.info(this.getClass().getSimpleName() + ":{}",event);
        if(event instanceof SignEvent){
            log.info("event 类型:{}",event.getClass().getSimpleName());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    @Override
    public Object process(ProcessEvent event) {
        log.info(this.getClass().getSimpleName() + ":{}",event);
        return null;
    }

    @Override
    public Object process(ProcessBusinessDto dto) {
        //List<StageNodeProcessBase> list = nodeProcessList.stream().map(o->(StageNodeProcessBase)o).collect(Collectors.toList());;
        Object execute = this.execute(dto, nodeProcessList);
        return execute;
        /*String nodeServiceName = getNodeServiceName(dto.getNode().getNodeEnum());
        AtomicReference<Object> result = null;
        nodeProcessList.forEach(o->{
            if(o.getClass().getSimpleName().equals(nodeServiceName)){
                result.set(o.process(dto));
                return;
            }
        });
        return result == null ? null : result.get();*/
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
