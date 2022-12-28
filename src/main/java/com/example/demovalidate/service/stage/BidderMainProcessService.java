package com.example.demovalidate.service.stage;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.event.PostEvent;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.inter.node.BidderMainNodeProcessBase;
import com.example.demovalidate.inter.node.PostNodeProcessBase;
import com.example.demovalidate.inter.stage.BusinessProcessBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：demo-validate
 * 类 名 称：PostProcessService
 * 类 描 述：命名规则：阶段名称+ProcessService
 * 创建时间：2022/12/8 15:33
 * 创 建 人：panyong
 */
@Slf4j
@Service
public class BidderMainProcessService implements BusinessProcessBase, Ordered {
    @Autowired
    private List<BidderMainNodeProcessBase> nodeProcessList;
    /*@Autowired
    private ApplicationContext ctx;*/


    @Override
    public Boolean isCurrentStage(ProcessEvent event) {
        log.info(this.getClass().getSimpleName() + ":{}",event);
        if(event instanceof PostEvent){
            log.info("event 类型:{}",event.getClass().getSimpleName());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Object process(ProcessEvent event) {
        return null;
    }

    @Override
    public Object process(ProcessBusinessDto dto) {
        Object execute = this.execute(dto, nodeProcessList);
        return execute;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
