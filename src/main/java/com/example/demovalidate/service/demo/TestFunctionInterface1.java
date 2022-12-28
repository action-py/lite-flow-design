package com.example.demovalidate.service.demo;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.event.PostEvent;
import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.service.status.StatusBusinessFunctionInterface;

import java.util.function.Function;

/**
 * 项目名称：demo-validate
 * 类 名 称：TestFunctionInterface1
 * 类 描 述：TODO
 * 创建时间：2022/12/20 11:08
 * 创 建 人：panyong
 */
public class TestFunctionInterface1 {

    public static Function<ProcessEvent,String> userInfo(){
        Function<ProcessEvent,String> function = (e) -> {
            return e.getUserInfo();
        };
        return function;
    }

    public static StatusBusinessFunctionInterface finish(){
        StatusBusinessFunctionInterface function = (e) -> {
            return e.getUserInfo()+"finish";
        };
        return function;
    }
    public static StatusBusinessFunctionInterface abnormal(){
        StatusBusinessFunctionInterface function = (e) -> {
            return e.getUserInfo()+"abnormal";
        };
        return function;
    }
    public static StatusBusinessFunctionInterface rest(){
        StatusBusinessFunctionInterface function = (e) -> {
            return e.getUserInfo()+"rest";
        };
        return function;
    }
    public static StatusBusinessFunctionInterface abnormalNodeRest(){
        StatusBusinessFunctionInterface function = (e) -> {
            return e.getUserInfo()+"finish";
        };
        return function;
    }

    public static void main(String[] args) {
        ProcessEvent event = new PostEvent();
        event.setUserInfo("{userName:jack}");
        /*String s = finishHandle(event, (e) -> {
            return e.getUserInfo();
        });*/
        /*String s = finishHandle(event, userInfo());*/
        /*UserInfo userInfo = (e) -> {
            return e.getUserInfo();
        };
        String s = finishHandle(event, userInfo);
        System.err.println(s);*/
        ProcessBusinessDto dto = new ProcessBusinessDto();
        //StatusBusinessFunctionInterface face = (e)-> e.getUserInfo();
        StatusBusinessFunctionInterface face;
        switch (dto.getNode().getStatus()){
            case STAGE_NODE_STATUS_WAITING:
                face =  finish();
                break;
            case STAGE_NODE_STATUS_OPEN:
                face = abnormal();
                break;
            case STAGE_NODE_STATUS_FINISHED:
                face = rest();
                break;
            case STAGE_NODE_STATUS_ABNORMAL:
                face = abnormalNodeRest();
                break;
            default:
                throw new IllegalArgumentException();
        }
        String handle = face.handle(dto);
        System.err.println(handle);
    }

    public static String finishHandle(ProcessEvent event, UserInfo function){
        String apply = function.apply(event);
        return apply;
    }

    public static String finishHandle(ProcessEvent event, Function<ProcessEvent,String> function){
        String apply = function.apply(event);
        return apply;
    }

    interface UserInfo{
        String apply(ProcessEvent event);
    }
}
