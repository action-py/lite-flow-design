package com.example.demovalidate.liteflow.process;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.enums.BiddingSideEnum;
import com.example.demovalidate.enums.StageEnum;
import com.example.demovalidate.enums.StatusEnum;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.liteflow.process.node.ProcessNode;
import com.example.demovalidate.liteflow.process.node.ProcessNodeContext;
import com.example.demovalidate.liteflow.process.node.ProcessNodeStatusEnum;
import com.example.demovalidate.service.global.ProcessHandler;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 项目名称：demo-validate
 * 类 名 称：StageBusinessHandleCmp
 * 类 描 述：TODO
 * 创建时间：2022/12/14 19:22
 * 创 建 人：panyong
 */
@Slf4j
@LiteflowComponent("stageBusinessHandle")
public class StageBusinessHandleCmp extends NodeComponent {

    @Autowired
    private ProcessHandler processHandler;

    @Override
    public void process() throws Exception {
        //执行对应节点任务
        ProcessNodeContext context = this.getContextBean(ProcessNodeContext.class);
        ProcessEvent event = context.getEvent();
        String stage, nodeCode,status;
        stage = event.getProcessType().equals(BiddingSideEnum.BIDDING_SIDE_BIDDER) ?
                StageEnum.BIDDER_MAIN.getCode():StageEnum.TENDER_MAIN.getCode();
        String tag = this.getTag();
        if(null==tag || "".equals(tag)){
            //当前阶段对应的节点
            nodeCode = event.getNode().getStage().getCode();
            status = ProcessNodeStatusEnum.STAGE_NODE_STATUS_FINISHED.getCode();
        }else{
            log.info("tag参数:{}",tag);
            //stage:QUOTE|status:OPEN
            String[] split = tag.split("\\|");
            String[] stageInfo = split[0].split(":");
            String[] statusInfo = split[1].split(":");
            nodeCode = stageInfo[1];
            status = statusInfo[1];
        }
        ProcessNode node = new ProcessNode(nodeCode,stage,status);
        ProcessBusinessDto dto = new ProcessBusinessDto(event.getBiddingId(), node, event.getUserInfo());
        //调用方法执行对应的业务代码
        Object handle = processHandler.handle(dto);
        context.getResult().add(handle);
    }
}
