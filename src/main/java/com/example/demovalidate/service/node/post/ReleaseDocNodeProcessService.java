package com.example.demovalidate.service.node.post;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.enums.ProcessNodeEnum;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.inter.node.PostNodeProcessBase;
import com.example.demovalidate.inter.status.base.StatusProcessBase;
import com.example.demovalidate.service.status.StatusBusinessFunctionInterface;
import com.example.demovalidate.service.status.StatusBusinessTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 项目名称：demo-validate
 * 类 名 称：ReleaseDocNodeProcess
 * 类 描 述：TODO
 * 创建时间：2022/12/9 18:01
 * 创 建 人：panyong
 */
@Slf4j
@Service("releaseDoc_POST_NodeProcessService")
public class ReleaseDocNodeProcessService extends StatusBusinessTemplate implements PostNodeProcessBase {

    @Autowired
    private Map<String, StatusProcessBase> statusProcessMap;

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
        String apply = this.apply(dto);
        return apply;
        /*//business 业务处理 status
        String statusServiceName = dto.getNode().getStatus().getCode()+"StatusProcessService";
        StatusProcessBase statusProcessBase = statusProcessMap.get(statusServiceName);
        //通过反射调用对象的指定方法
        try {
            Method method = statusProcessBase.getClass().getDeclaredMethod(dto.getNode().getNodeEnum().getSimpleCode());
            method.setAccessible(true);
            method.invoke(statusProcessBase,dto);
        }catch (Exception e) {
            log.error("err:{}",e);
        }
        log.info("准备处理对应状态");
        return new Object();*/
    }

    @Override
    public int getOrder() {
        return ProcessNodeEnum.POST_RELEASE_DOC.getOrder();
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
