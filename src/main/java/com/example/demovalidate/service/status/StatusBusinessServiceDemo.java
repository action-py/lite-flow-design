package com.example.demovalidate.service.status;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.enums.BiddingSideEnum;
import com.example.demovalidate.enums.StatusEnum;
import com.example.demovalidate.event.PostEvent;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.liteflow.process.node.ProcessNode;

/**
 * 项目名称：demo-validate
 * 类 名 称：TestFunctionInterface1
 * 类 描 述：TODO
 * 创建时间：2022/12/20 11:08
 * 创 建 人：panyong
 */
public class StatusBusinessServiceDemo extends StatusBusinessTemplate {
    @Override
    public StatusBusinessFunctionInterface waiting() {
        StatusBusinessFunctionInterface function = (e) -> {
            return e.getUserInfo()+":WAITING";
        };
        return function;
    }

    @Override
    public StatusBusinessFunctionInterface open() {
        StatusBusinessFunctionInterface function = (e) -> {
            return e.getUserInfo()+":OPEN";
        };
        return function;
    }

    @Override
    public StatusBusinessFunctionInterface finished() {
        StatusBusinessFunctionInterface function = (e) -> {
            return e.getUserInfo()+":FINISHED";
        };
        return function;
    }

    @Override
    public StatusBusinessFunctionInterface abnormal(){
        StatusBusinessFunctionInterface function = (e) -> {
            return e.getUserInfo()+":ABNORMAL";
        };
        return function;
    }


    public static void main(String[] args) {
        StatusBusinessTemplate statusBusiness = new StatusBusinessServiceDemo();
        ProcessEvent event = new PostEvent(StatusEnum.ABNORMAL, BiddingSideEnum.BIDDING_SIDE_BIDDER,"{userName:jack}");
        ProcessNode node = new ProcessNode("AUDIT","POST","FINISH");
        ProcessBusinessDto dto = new ProcessBusinessDto(1080l, node, "{userName:jack}");
        String apply = statusBusiness.apply(dto);
        System.out.println(apply);
    }
}
