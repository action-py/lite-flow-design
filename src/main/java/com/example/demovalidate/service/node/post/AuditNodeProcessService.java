package com.example.demovalidate.service.node.post;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.enums.ProcessNodeEnum;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.inter.node.PostNodeProcessBase;
import com.example.demovalidate.service.status.StatusBusinessFunctionInterface;
import com.example.demovalidate.service.status.StatusBusinessTemplate;
import org.springframework.stereotype.Service;

/**
 * 项目名称：demo-validate
 * 类 名 称：AuditNodeProcessService
 * 类 描 述：TODO
 * 创建时间：2022/12/9 18:04
 * 创 建 人：panyong
 */
@Service("audit_POST_NodeProcessService")
public class AuditNodeProcessService extends StatusBusinessTemplate implements PostNodeProcessBase {



    @Override
    public Boolean isCurrentNode(ProcessEvent event) {
        return null;
    }


    @Override
    public Object process(ProcessEvent event) {
        return null;
    }

    @Override
    public Object process(ProcessBusinessDto dto) {
        /*StatusBusinessTemplate statusBusiness = this;
        String apply = statusBusiness.apply(dto);*/
        String apply = this.apply(dto);
        return apply;
    }

    @Override
    public int getOrder() {
        return ProcessNodeEnum.POST_AUDIT.getOrder();
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
}
