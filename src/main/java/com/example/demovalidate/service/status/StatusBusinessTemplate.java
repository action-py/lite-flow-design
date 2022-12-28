package com.example.demovalidate.service.status;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.service.status.StatusBusinessFunctionInterface;

/**
 * 项目名称：demo-validate
 * 类 名 称：TestFunctionInterfaceTemplate
 * 类 描 述：TODO
 * 创建时间：2022/12/20 16:01
 * 创 建 人：panyong
 */
public abstract class StatusBusinessTemplate {


    public abstract StatusBusinessFunctionInterface waiting();
    public abstract StatusBusinessFunctionInterface open();
    public abstract StatusBusinessFunctionInterface finished();
    public abstract StatusBusinessFunctionInterface abnormal();

    public String apply(ProcessBusinessDto dto){
        StatusBusinessFunctionInterface face;
        switch (dto.getNode().getStatus()){
            case STAGE_NODE_STATUS_WAITING:
                face =  waiting();
                break;
            case STAGE_NODE_STATUS_OPEN:
                face = open();
                break;
            case STAGE_NODE_STATUS_FINISHED:
                face = finished();
                break;
            case STAGE_NODE_STATUS_ABNORMAL:
                face = abnormal();
                break;
            default:
                throw new IllegalArgumentException();
        }
        String handle = face.handle(dto);
        return handle;
    }

}
