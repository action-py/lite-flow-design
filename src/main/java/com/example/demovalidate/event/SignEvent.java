package com.example.demovalidate.event;

/**
 * 项目名称：demo-validate
 * 类 名 称：PostEvent
 * 类 描 述：TODO
 * 创建时间：2022/12/8 11:23
 * 创 建 人：panyong
 */
public class SignEvent extends ProcessEvent{
    @Override
    Object getExtData() {
        return "SIGN";
    }
}
