package com.example.demovalidate.event;

import com.example.demovalidate.enums.BiddingSideEnum;
import com.example.demovalidate.enums.ProcessNodeEnum;
import com.example.demovalidate.enums.StatusEnum;

/**
 * 项目名称：demo-validate
 * 类 名 称：PostEvent
 * 类 描 述：TODO
 * 创建时间：2022/12/8 11:23
 * 创 建 人：panyong
 */
public class PostEvent extends ProcessEvent{
    public PostEvent() {
        super();
    }

    public PostEvent(StatusEnum status, BiddingSideEnum processType, String userInfo) {
        super(null,processType, status, null, userInfo);
    }

    @Override
    Object getExtData() {
        return "POST";
    }
}
