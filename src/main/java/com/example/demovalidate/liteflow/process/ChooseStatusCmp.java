package com.example.demovalidate.liteflow.process;

import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.liteflow.process.node.ProcessNode;
import com.example.demovalidate.liteflow.process.node.ProcessNodeContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * 项目名称：demo-validate
 * 类 名 称：ChooseStatusCmp
 * 类 描 述：TODO
 * 创建时间：2022/12/14 19:04
 * 创 建 人：panyong
 */
@Slf4j
@LiteflowComponent("chooseStatus")
public class ChooseStatusCmp extends NodeSwitchComponent {
    //事件可发送的状态
    static final Set<String> userOperationStatus;
    //节点可接受的状态
    static final Set<String> nodeOperationStatus;
    static {
        userOperationStatus = new HashSet<String>(){{
            add("FINISH");//完成
            add("ABNORMAL");//驳回
            add("REST");//节点重置
            add("ABNORMAL_NODE_REST");//流程重置
        }};
        nodeOperationStatus = new HashSet<String>(){{
            add("OPEN");//节点为开启状态时 ==》 用户操作 FINISH | ABNORMAL
            add("ABNORMAL");//节点为异常状态时 ==》用户操作 REST | ABNORMAL_NODE_RESET

        }};
    }


    @Override
    public String processSwitch() throws Exception {
        ProcessNodeContext contextBean = this.getContextBean(ProcessNodeContext.class);
        ProcessEvent event = contextBean.getEvent();
        if(!userOperationStatus.contains(event.getStatus().getCode())){
            log.warn("事件当前状态【{}】不可操作!",event.getStatus());
            return "exceptionEnd";
        }
        //OPEN ABNORMAL
        if(!nodeOperationStatus.contains(contextBean.getCurrentNode().getStatus().getCode())){
            log.warn("当前节点状态【{}】不可操作!",contextBean.getCurrentNode().getStatus().getCode());
            return "exceptionEnd";
        }
        return event.getStatus().getCode();
    }
}
