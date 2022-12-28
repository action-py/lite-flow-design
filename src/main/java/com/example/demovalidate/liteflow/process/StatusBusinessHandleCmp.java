package com.example.demovalidate.liteflow.process;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.enums.StatusEnum;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.liteflow.process.node.ProcessNode;
import com.example.demovalidate.liteflow.process.node.ProcessNodeContext;
import com.example.demovalidate.liteflow.process.node.ProcessNodeStatusEnum;
import com.example.demovalidate.service.global.ProcessHandler;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 项目名称：demo-validate
 * 类 名 称：StatusBusinessHandleCmp
 * 类 描 述：TODO
 * 创建时间：2022/12/14 19:09
 * 创 建 人：panyong
 */
@LiteflowComponent("statusBusinessHandle")
public class StatusBusinessHandleCmp extends NodeComponent {
    @Autowired
    private ProcessHandler processHandler;

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
        ProcessNode node = new ProcessNode(nodeCode,stage,status);
        ProcessBusinessDto dto = new ProcessBusinessDto(event.getBiddingId(), node, event.getUserInfo());
        //调用方法执行对应的业务代码
        Object handle = processHandler.handle(dto);
        context.getResult().add(handle);
    }
}
