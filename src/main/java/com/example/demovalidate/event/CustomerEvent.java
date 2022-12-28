package com.example.demovalidate.event;

import lombok.Data;

/**
 * 项目名称：demo-validate
 * 类 名 称：processEvent
 * 类 描 述：TODO
 * 创建时间：2022/12/8 11:21
 * 创 建 人：panyong
 */
@Data
public class CustomerEvent {
    private Long bizId;
    private String status;
    private String node;
    private String userInfo;
}
