package com.example.demovalidate.liteflow.process;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.liteflow.process.node.ProcessNode;
import com.example.demovalidate.liteflow.process.node.ProcessNodeContext;
import com.example.demovalidate.service.global.ProcessHandler;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 项目名称：demo-validate
 * 类 名 称：RestStatusBusinessHandleCmp
 * 类 描 述：TODO
 * 创建时间：2022/12/14 19:19
 * 创 建 人：panyong
 */
@Slf4j
@LiteflowComponent("restStatusBusinessHandle")
public class RestStatusBusinessHandleCmp extends NodeComponent {
    @Autowired
    private ProcessHandler processHandler;

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

        ProcessNode node = new ProcessNode(nodeCode,stage,status);
        ProcessBusinessDto dto = new ProcessBusinessDto(event.getBiddingId(), node, event.getUserInfo());
        //调用方法执行对应的业务代码
        Object handle = processHandler.handle(dto);
        context.getResult().add(handle);
    }
}
