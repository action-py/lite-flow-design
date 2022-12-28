package com.example.demovalidate.liteflow.process.node;

import com.example.demovalidate.event.ProcessEvent;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 项目名称：demo-validate
 * 类 名 称：ProcessNodeContext
 * 类 描 述：TODO
 * 创建时间：2022/12/15 11:00
 * 创 建 人：panyong
 */
@Data
public class ProcessNodeContext {
    ProcessEvent event;
    List<ProcessNode> nodes;

    //当前待处理的节点
    //PS:在【chooseStatus】组件中使用
    ProcessNode currentNode;

    List<Object> result = new LinkedList<>();

    public ProcessNodeContext(ProcessEvent event, List<ProcessNode> nodes) {
        this.event = event;
        this.nodes = nodes;
    }

}
