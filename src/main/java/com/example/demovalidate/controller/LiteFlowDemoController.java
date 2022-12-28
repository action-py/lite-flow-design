package com.example.demovalidate.controller;

import com.example.demovalidate.event.PostEvent;
import com.example.demovalidate.liteflow.process.node.NodeUtils;
import com.example.demovalidate.liteflow.process.node.ProcessNode;
import com.example.demovalidate.liteflow.process.node.ProcessNodeContext;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目名称：demo-validate
 * 类 名 称：LiteFlowDemoController
 * 类 描 述：TODO
 * 创建时间：2022/12/12 15:33
 * 创 建 人：panyong
 */
@RestController
public class LiteFlowDemoController {

    @Autowired
    private FlowExecutor flowExecutor;

    @PostMapping("/lite_flow/process")
    public ProcessNodeContext process(@RequestBody PostEvent event){
        ProcessNodeContext processNodeContext = new ProcessNodeContext(event, NodeUtils.nodes);
        LiteflowResponse liteflowResponse = flowExecutor.execute2Resp("bidderProcessNodeHandle", null,processNodeContext);
        System.out.println(liteflowResponse.getMessage());
        ProcessNodeContext contextBean = liteflowResponse.getContextBean(ProcessNodeContext.class);
        return contextBean;
    }

    @PostMapping("/lite_flow/test")
    public void test(){
        LiteflowResponse liteflowResponse = flowExecutor.execute2Resp("chain1", "arg");
        System.out.println(liteflowResponse);
        List<ProcessNode> nodes = NodeUtils.nodes;
    }

    @PostMapping("/lite_flow/test2")
    public void test2(){
        LiteflowResponse liteflowResponse = flowExecutor.execute2Resp("chain2", "arg");
        System.out.println(liteflowResponse);
    }

    @GetMapping("/lite_flow/test3")
    public void test3(@RequestParam("size") Integer size){
        LiteflowResponse liteflowResponse = flowExecutor.execute2Resp("chain3", size);
        System.out.println(liteflowResponse);
    }
}
