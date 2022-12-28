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
 * 类 名 称：RestStatusNodeHandleCmp
 * 类 描 述：重置当前流程的节点
 * PS: tag --> node:AUDIT|status:WAITING
 * 创建时间：2022/12/14 19:17
 * 创 建 人：panyong
 */
@Slf4j
@LiteflowComponent("restStatusNodeHandle")
public class RestStatusNodeHandleCmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        String tag = this.getTag();
        if(null==tag || "".equals(tag)){
            log.warn("重置节点tag信息为空");
            this.setIsEnd(true);
            return;
        }
        log.info("tag参数:{}",tag);
        ProcessNodeContext context = this.getContextBean(ProcessNodeContext.class);
        ProcessEvent event = context.getEvent();
        String stage,nodeCode,status;
        //node:AUDIT|status:OPEN
        String[] split = tag.split("\\|");
        String[] nodeInfo = split[0].split(":");
        String[] statusInfo = split[1].split(":");
        nodeCode = nodeInfo[1];
        status = statusInfo[1];
        stage = event.getNode().getStage().getCode();
        //加上阶段防止 不同阶段的节点重名导致的获取节点错误问题
        ProcessNode node = context.getNodes().stream().filter(o -> {
            return (o.getStage().getCode().equals(stage)) && (o.getCode().equals(nodeCode));
        }).findFirst().get();
        node.setStatus(ProcessNodeStatusEnum.getByCode(status));
        System.out.println(tag);
    }
}
