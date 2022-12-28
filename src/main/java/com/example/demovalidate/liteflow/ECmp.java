package com.example.demovalidate.liteflow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * 项目名称：demo-validate
 * 类 名 称：ACmp
 * 类 描 述：TODO
 * 创建时间：2022/12/12 14:55
 * 创 建 人：panyong
 */
@LiteflowComponent("e")
public class ECmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        System.out.println("e");
    }
}
