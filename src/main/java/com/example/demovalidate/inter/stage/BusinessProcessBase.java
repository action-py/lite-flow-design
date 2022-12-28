package com.example.demovalidate.inter.stage;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.enums.ProcessNodeEnum;
import com.example.demovalidate.enums.StageEnum;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.inter.node.base.StageNodeProcessBase;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public interface BusinessProcessBase {

     String nodeServiceSuffix="NodeProcessService";

     default String getNodeServiceName(ProcessNodeEnum node){
         String stage = (node.getStage().equals(StageEnum.BIDDER_MAIN)
                            ||  node.getStage().equals(StageEnum.TENDER_MAIN))
                                    ? "MAIN" : node.getStage().getCode();
         return node.getSimpleCode()+"_"+stage+"_"+nodeServiceSuffix;
     }

     default Object execute(ProcessBusinessDto dto,List<?> list) {
         List<StageNodeProcessBase> nodeProcessList = list.stream().map(o -> (StageNodeProcessBase) o).collect(Collectors.toList());
         String nodeServiceName = getNodeServiceName(dto.getNode().getNodeEnum());
         AtomicReference<Object> result = new AtomicReference<>();
         nodeProcessList.forEach(o->{
             String value = o.getClass().getAnnotation(Service.class).value();
             if(value.equals(nodeServiceName)){
                 result.set(o.process(dto));
                 return;
             }
         });
         return result == null ? null : result.get();
     }

    /**
     * 是否当前事件
     * @param event
     * @return
     */
    Boolean isCurrentStage(ProcessEvent event);

    /**
     * 事件信息 event
     * @param event
     * @return
     */
     Object process(ProcessEvent event);

    /**
     * 业务处理
     * @param dto
     * @return
     */
     Object process(ProcessBusinessDto dto);
}
