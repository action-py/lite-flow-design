package com.example.demovalidate.liteflow.process;

import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.liteflow.process.node.ProcessNode;
import com.example.demovalidate.liteflow.process.node.ProcessNodeContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目名称：demo-validate
 * 类 名 称：ChooseNodeCmp
 * 类 描 述：TODO
 * 创建时间：2022/12/14 19:03
 * 创 建 人：panyong
 */
@Slf4j
@LiteflowComponent("chooseNode")
public class ChooseNodeCmp extends NodeSwitchComponent {
    @Override
    public String processSwitch() throws Exception {
        ProcessNodeContext contextBean = this.getContextBean(ProcessNodeContext.class);
        ProcessEvent event = contextBean.getEvent();
        ProcessNode node = contextBean.getNodes().stream().filter(o -> {
            return o.getCode().equals(event.getNode().getNodeCode());
        }).findFirst().orElse(null);
        if(null == node){
            log.warn("未找到事件【{}】中对应的节点",event);
            return "exceptionEnd";
        }
        contextBean.setCurrentNode(node);
        return node.getCode();
    }
}
