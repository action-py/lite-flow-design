package com.example.demovalidate.dto;

import com.example.demovalidate.liteflow.process.node.ProcessNode;
import lombok.Data;

/**
 * 项目名称：demo-validate
 * 类 名 称：ProcessBusinessDto
 * 类 描 述：TODO
 * 创建时间：2022/12/19 16:56
 * 创 建 人：panyong
 */
@Data
public class ProcessBusinessDto {
    //标的ID
    private Long biddingId;
    //节点
    private ProcessNode node;
    //用户信息 用户id 用户类型
    private String userInfo;

    public ProcessBusinessDto() {
    }

    public ProcessBusinessDto(Long biddingId, ProcessNode node, String userInfo) {
        this.biddingId = biddingId;
        this.node = node;
        this.userInfo = userInfo;
    }
}
