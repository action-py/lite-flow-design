package com.example.demovalidate.liteflow.process;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目名称：demo-validate
 * 类 名 称：ExceptionEndCmp
 * 类 描 述：TODO
 * 创建时间：2022/12/19 11:39
 * 创 建 人：panyong
 */
@Slf4j
@LiteflowComponent("exceptionEnd")
public class ExceptionEndCmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        log.info("进入异常节点");
        this.setIsEnd(true);
    }
}
