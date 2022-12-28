package com.example.demovalidate.liteflow.process.node;

import com.example.demovalidate.enums.StageEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：demo-validate
 * 类 名 称：NodeUtils
 * 类 描 述：TODO
 * 创建时间：2022/12/15 10:29
 * 创 建 人：panyong
 */
@Data
public class NodeUtils {

    public static List<ProcessNode> nodes=new ArrayList<>();

    static {
        nodes.add(new ProcessNode("POST","发布",ProcessNodeStatusEnum.STAGE_NODE_STATUS_OPEN, StageEnum.BIDDER_MAIN));
        nodes.add(new ProcessNode("QUOTE","投标",ProcessNodeStatusEnum.STAGE_NODE_STATUS_WAITING,StageEnum.BIDDER_MAIN));
        nodes.add(new ProcessNode("CALIBRATION","定标",ProcessNodeStatusEnum.STAGE_NODE_STATUS_WAITING,StageEnum.BIDDER_MAIN));
        nodes.add(new ProcessNode("SIGN","签约",ProcessNodeStatusEnum.STAGE_NODE_STATUS_WAITING,StageEnum.BIDDER_MAIN));

        nodes.add(new ProcessNode("RELEASE_DOC","发布文件",ProcessNodeStatusEnum.STAGE_NODE_STATUS_OPEN, StageEnum.POST));
        nodes.add(new ProcessNode("AUDIT","审核",ProcessNodeStatusEnum.STAGE_NODE_STATUS_WAITING,StageEnum.POST));
        nodes.add(new ProcessNode("RELEASE_ANNOUNCE","发布公告",ProcessNodeStatusEnum.STAGE_NODE_STATUS_WAITING,StageEnum.POST));

        nodes.add(new ProcessNode("REGISTER","报名",ProcessNodeStatusEnum.STAGE_NODE_STATUS_WAITING,StageEnum.QUOTE));
        nodes.add(new ProcessNode("QUOTE","报价",ProcessNodeStatusEnum.STAGE_NODE_STATUS_WAITING,StageEnum.QUOTE));
    }

    public ProcessNode getNext(ProcessNode node){
        int i = nodes.indexOf(node);
        ProcessNode processNode =null;
        if (i!= nodes.size() - 1)
        {
            processNode =nodes.get(i);
        }
        return  processNode;
    }

    public static void main(String[] args) {
        String tag = "node:AUDIT|status:OPEN";
        String[] split = tag.split(":");
        System.out.println(split[0]);
    }
}
