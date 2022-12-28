package com.example.demovalidate.inter.node.base;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.event.ProcessEvent;
import org.springframework.core.Ordered;

//节点的顶级接口
public interface StageNodeProcessBase extends Ordered {
    String nodeServiceSuffix = "NodeProcessService";
    ThreadLocal<Boolean>  nextExecute = new ThreadLocal<>();
    //是否是当前节点 定位是否是事件执行的第一个节点
    default Boolean isCurrentNode(ProcessEvent event){
        String serviceName = event.getNode().getSimpleCode()+this.nodeServiceSuffix;
        return this.getClass().getSimpleName().equals(serviceName);
    }

    default Boolean isExecute(ProcessEvent event){
        Boolean businessFlag = Boolean.FALSE;
        Boolean currentNode = isCurrentNode(event);
        if(currentNode){//当前节点 处理事件  设置后续是否继续执行
            businessFlag = Boolean.TRUE;//todo next node 是否执行 ==》 endFlag.set(Boolean.FALSE || Boolean.TRUE);
        }else {
            Boolean flag = nextExecute.get();
            if(null==flag){//不是当前节点 && 事件未处理 直接走下一节点
                businessFlag=null;// todo nothing
            }else if(flag){//该节点需要执行 设置后续是否继续执行
                businessFlag = Boolean.TRUE;
                //todo next node 是否执行 ==》 endFlag.set(Boolean.FALSE || Boolean.TRUE);
            }else{//该节点不需要执行  计算下一节点是否执行
                //todo next node 是否执行 ==》 endFlag.set(Boolean.FALSE || Boolean.TRUE);
            }
        }
        return businessFlag;
    }
    Object process(ProcessEvent event);

    Object process(ProcessBusinessDto dto);
}
