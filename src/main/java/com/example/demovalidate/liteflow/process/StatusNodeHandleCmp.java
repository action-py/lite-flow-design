package com.example.demovalidate.liteflow.process;

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
 * 类 名 称：StatusNodeHandleCmp
 * 类 描 述：当前节点只做处理，不做逻辑校验
 * 创建时间：2022/12/14 19:08
 * 创 建 人：panyong
 */
@Slf4j
@LiteflowComponent("statusNodeHandle")
public class StatusNodeHandleCmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        ProcessNodeContext context = this.getContextBean(ProcessNodeContext.class);
        ProcessEvent event = context.getEvent();
        String stage,nodeCode,status;
        String tag = this.getTag();
        if(null==tag || "".equals(tag)){
            //上下文获取 当前节点 当前状态
            stage = event.getNode().getStage().getCode();
            nodeCode = event.getNode().getNodeCode();
            status = event.getStatus().getCode();
        }else{
            log.info("tag参数:{}",tag);
            //node:AUDIT|status:OPEN
            String[] split = tag.split("\\|");
            String[] nodeInfo = split[0].split(":");
            String[] statusInfo = split[1].split(":");
            nodeCode = nodeInfo[1];
            status = statusInfo[1];
            if(split.length==3){//当前阶段设置下一阶段节点状态时使用
                String[] stageInfo = split[2].split(":");
                stage =stageInfo[1];
            }else {
                stage = event.getNode().getStage().getCode();
            }
        }
        if(status.equals(StatusEnum.REST.getCode())){
            status = ProcessNodeStatusEnum.STAGE_NODE_STATUS_OPEN.getCode();
        }
        //加上阶段防止 不同阶段的节点重名导致的获取节点错误问题
        ProcessNode node = context.getNodes().stream().filter(o -> {
            return (o.getStage().getCode().equals(stage)) && (o.getCode().equals(nodeCode));
        }).findFirst().get();
        node.setStatus(ProcessNodeStatusEnum.getByCode(status));
        log.info("变更后的节点信息:{}",node);
    }
}
