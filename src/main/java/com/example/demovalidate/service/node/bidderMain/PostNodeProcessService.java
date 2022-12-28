package com.example.demovalidate.service.node.bidderMain;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.enums.ProcessNodeEnum;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.inter.node.BidderMainNodeProcessBase;
import com.example.demovalidate.service.status.StatusBusinessFunctionInterface;
import com.example.demovalidate.service.status.StatusBusinessTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 项目名称：demo-validate
 * 类 名 称：PostNodeProcessService
 * 类 描 述：TODO
 * 创建时间：2022/12/27 15:07
 * 创 建 人：panyong
 */
@Slf4j
@Service("post_MAIN_NodeProcessService")
public class PostNodeProcessService extends StatusBusinessTemplate implements BidderMainNodeProcessBase {

    @Override
    public Object process(ProcessEvent event) {
        return null;
    }

    @Override
    public Object process(ProcessBusinessDto dto) {
        String apply = this.apply(dto);
        return apply;
    }

    @Override
    public StatusBusinessFunctionInterface waiting() {
        StatusBusinessFunctionInterface function = (e) -> {
            return this.getClass().getSimpleName()+":WAITING";
        };
        return function;
    }

    @Override
    public StatusBusinessFunctionInterface open() {
        StatusBusinessFunctionInterface function = (e) -> {
            return this.getClass().getSimpleName()+":OPEN";
        };
        return function;
    }

    @Override
    public StatusBusinessFunctionInterface finished() {
        StatusBusinessFunctionInterface function = (e) -> {
            return this.getClass().getSimpleName()+":FINISHED";
        };
        return function;
    }

    @Override
    public StatusBusinessFunctionInterface abnormal() {
        StatusBusinessFunctionInterface function = (e) -> {
            return this.getClass().getSimpleName()+":ABNORMAL";
        };
        return function;
    }

    @Override
    public int getOrder() {
        return ProcessNodeEnum.BIDDER_MAIN_POST.getOrder();
    }
}
