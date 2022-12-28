package com.example.demovalidate.liteflow.process;

import com.example.demovalidate.enums.BiddingSideEnum;
import com.example.demovalidate.enums.StageEnum;
import com.example.demovalidate.enums.StatusEnum;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.liteflow.process.node.ProcessNode;
import com.example.demovalidate.liteflow.process.node.ProcessNodeContext;
import com.example.demovalidate.liteflow.process.node.ProcessNodeStatusEnum;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目名称：demo-validate
 * 类 名 称：StageHandleCmp
 * 类 描 述：TODO
 * 创建时间：2022/12/14 19:21
 * 创 建 人：panyong
 */
@Slf4j
@LiteflowComponent("stageHandle")
public class StageHandleCmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        //获取阶段 节点
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
        //加上阶段防止 不同阶段的节点重名导致的获取节点错误问题
        ProcessNode node = context.getNodes().stream().filter(o -> {
            return (o.getStage().getCode().equals(stage)) && (o.getCode().equals(nodeCode));
        }).findFirst().get();
        node.setStatus(ProcessNodeStatusEnum.getByCode(status));
        log.info("变更后的节点信息:{}",node);
    }
}
