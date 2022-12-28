package com.example.demovalidate.liteflow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeForComponent;

import java.util.Random;

/**
 * 项目名称：demo-validate
 * 类 名 称：ForCmp
 * 类 描 述：TODO
 * 创建时间：2022/12/15 17:44
 * 创 建 人：panyong
 */
@LiteflowComponent("forCmp")
public class ForCmp extends NodeForComponent {
    @Override
    public int processFor() throws Exception {
        Integer size = (Integer)this.getRequestData();
        Random random = new Random();
        for (int j = 0; j < size.intValue(); j++) {
            int i = random.nextInt(10000);
            this.sendPrivateDeliveryData("a",i);
            System.out.println("-----------"+i);
        }
        return size;
    }
}
