package com.example.demovalidate.liteflow.process.node;

import com.example.demovalidate.enums.ProcessNodeEnum;
import com.example.demovalidate.enums.StageEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 项目名称：demo-validate
 * 类 名 称：ProcessNode
 * 类 描 述：TODO
 * 创建时间：2022/12/15 10:15
 * 创 建 人：panyong
 */
@Data
public class ProcessNode {
    /** stage meta */
    Long id;

    String code;
    String name;

    Long nodeMetaId;

    ProcessNodeEnum nodeEnum;

    StageEnum stage;

    /** 阶段当前状态 */
    ProcessNodeStatusEnum status;

    /** 最后节点的更新时间 */
    LocalDateTime lastUpdateTime;

    public ProcessNode() {
    }

    public ProcessNode(String code, String stageCode, String status) {
        this.code = code;
        this.nodeEnum = ProcessNodeEnum.getNodeByStageAndNodeCode(stageCode,code);
        this.stage = StageEnum.getInstanceByCode(stageCode);
        this.status = ProcessNodeStatusEnum.getByCode(status);
    }

    public ProcessNode(ProcessNodeEnum nodeEnum, StageEnum stage, ProcessNodeStatusEnum status) {
        this.code = nodeEnum.getNodeCode();
        this.nodeEnum = nodeEnum;
        this.stage = stage;
        this.status = status;
    }

    public ProcessNode(String code, String name, ProcessNodeStatusEnum status, StageEnum stage) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.stage = stage;
    }
}
