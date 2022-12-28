package com.example.demovalidate.service.global;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.enums.StageEnum;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.inter.stage.BusinessProcessBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 项目名称：demo-validate
 * 类 名 称：ProcessHandler
 * 类 描 述：TODO
 * 创建时间：2022/12/8 15:39
 * 创 建 人：panyong
 */
@Slf4j
@Component
public class ProcessHandler {
    @Autowired
    private Map<String, BusinessProcessBase> processMap;
    @Autowired
    private List<BusinessProcessBase> processList;

    private static final String stageServiceSuffix="ProcessService";


    public Object handle(ProcessBusinessDto dto) {
        //根据规则 找到对应的阶段
        StageEnum stage = dto.getNode().getStage();
        String stageServiceName = stage.getSimpleCode()+ stageServiceSuffix;
        log.info("待获取的stageService:{}",stageServiceName);
        BusinessProcessBase businessProcess = processMap.get(stageServiceName);
        if(null==businessProcess){
            log.warn("stageService:{}获取失败",stageServiceName);
            throw new IllegalArgumentException();
        }
        //对应阶段执行,后期拓展方便 ==》弹性可插拔
        Object process = businessProcess.process(dto);
        return process;
    }
    public void handle(ProcessEvent event) {
        //根据规则 找到对应的阶段
        StageEnum stage = event.getNode().getStage();
        String stageServiceName = stage.getCode()+ stageServiceSuffix;
        log.info("待获取的stageService:{}",stageServiceName);
        BusinessProcessBase businessProcess = processMap.get(stageServiceName);
        if(null==businessProcess){
            log.warn("stageService:{}获取失败",stageServiceName);
            return;
        }
        //对应阶段执行,后期拓展方便 ==》弹性可插拔
        businessProcess.process(event);
        /*Iterator<Map.Entry<String, BusinessProcessBase>> iterator = processMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, BusinessProcessBase> next = iterator.next();
            log.info("serviceName:{}",next.getKey());
            next.getValue().process(event);
        }
        log.info("-------------list-start-----------------");
        //找到需要执行的节点
        processList.forEach(o->{
            o.process(event);
        });
        log.info("-------------list-end-----------------------");*/
    }
}
