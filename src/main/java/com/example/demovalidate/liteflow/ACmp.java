package com.example.demovalidate.liteflow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * 项目名称：demo-validate
 * 类 名 称：ACmp
 * 类 描 述：TODO
 * 创建时间：2022/12/12 14:55
 * 创 建 人：panyong
 */
@LiteflowComponent("a")
public class ACmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        Integer param = (Integer)this.getPrivateDeliveryData();
        System.err.println(Thread.currentThread().getName() + "=======================a:"+param);
        System.err.println(Thread.currentThread().getName() + "************************:"+ThreadLocalParamUtil.threadLocal.get());
        ThreadLocalParamUtil.threadLocal.set(param);
    }
}
