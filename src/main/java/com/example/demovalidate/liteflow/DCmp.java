package com.example.demovalidate.liteflow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;

import java.util.Random;

/**
 * 项目名称：demo-validate
 * 类 名 称：DCmp
 * 类 描 述：TODO
 * 创建时间：2022/12/12 16:41
 * 创 建 人：panyong
 */
@LiteflowComponent("d")
public class DCmp extends NodeSwitchComponent {
    @Override
    public String processSwitch() throws Exception {

        Random random = new Random();
        Boolean flag = random.nextInt()%2==0;
        String chainId = this.getChainId();
        String result = flag ? (chainId.equals("chain1")? "b":"tag:boy") :"when1";
        System.err.println(result);
        return result;
    }
}
