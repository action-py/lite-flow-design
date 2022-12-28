package com.example.demovalidate.service.node.sign;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.enums.ProcessNodeEnum;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.inter.node.SignNodeProcessBase;
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
 * 类 名 称：AuditNodeProcessService
 * 类 描 述：节点_阶段_NodeProcessService
 * PS:多阶段下节点名称重复
 * 创建时间：2022/12/9 18:04
 * 创 建 人：panyong
 */
@Slf4j
@Service("sign_SIGN_NodeProcessService")
public class SignNodeProcessService extends StatusBusinessTemplate implements SignNodeProcessBase {

    @Autowired
    private Map<String, StatusProcessBase> statusProcessMap;

    @Deprecated
    @Override
    public Object process(ProcessEvent event){
        /**
         * 计算是否执行
         * null  : return 进入下一个节点
         * false : 判断下个方法是否执行
         * true  : 执行状态方法 ==》 判断下个方法是否执行
         */
        Boolean businessFlag = isExecute(event);
        if(null == businessFlag)
            return null;
        if(businessFlag){
            //business 业务处理 status
            String statusServiceName = event.getStatus().getName()+"StatusProcessService";
            StatusProcessBase statusProcessBase = statusProcessMap.get(statusServiceName);
            //通过反射调用对象的指定方法
            try {
                Method method = statusProcessBase.getClass().getDeclaredMethod(event.getNode().getSimpleCode());
                method.setAccessible(true);
                method.invoke(statusProcessBase,event);
                //statusProcessBase.sign(event);
            }catch (Exception e) {
                log.error("err:{}",e);
            }

            log.info("准备处理对应状态");
        }
        //todo something next node 判断下个节点是否执行
        nextExecute.set(Boolean.FALSE || Boolean.TRUE);
        return new Object();
    }

    @Override
    public Object process(ProcessBusinessDto dto) {
        return null;
    }

    @Override
    public int getOrder() {
        return ProcessNodeEnum.SIGN_SIGN.getOrder();
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
