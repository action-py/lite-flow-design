package com.example.demovalidate.event;

import com.example.demovalidate.enums.BiddingSideEnum;
import com.example.demovalidate.enums.ProcessNodeEnum;
import com.example.demovalidate.enums.StatusEnum;
import lombok.Data;

/**
 * 项目名称：demo-validate
 * 类 名 称：processEvent
 * 类 描 述：TODO
 * 创建时间：2022/12/8 11:21
 * 创 建 人：panyong
 */
@Data
public abstract class ProcessEvent {
    //标的ID
    private Long biddingId;
    //流程类型
    private BiddingSideEnum processType;
    //状态
    private StatusEnum status;
    //节点
    private ProcessNodeEnum node;
    //用户信息 用户id 用户类型
    private String userInfo;
    abstract Object getExtData();

    public ProcessEvent() {
    }

    public ProcessEvent(Long biddingId,BiddingSideEnum processType, StatusEnum status, ProcessNodeEnum node, String userInfo) {
        this.biddingId = biddingId;
        this.processType = processType;
        this.status = status;
        this.node = node;
        this.userInfo = userInfo;
    }
}
