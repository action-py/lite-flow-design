package com.example.demovalidate.service.node.sign;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.enums.ProcessNodeEnum;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.inter.node.SignNodeProcessBase;
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
@Service("edit_SIGN_NodeProcessService")
public class EditNodeProcessService extends StatusBusinessTemplate implements SignNodeProcessBase {
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
        return null;
    }

    @Override
    public int getOrder() {
        return ProcessNodeEnum.SIGN_EDIT.getOrder();
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
