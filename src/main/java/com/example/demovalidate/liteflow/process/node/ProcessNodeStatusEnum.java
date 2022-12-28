package com.example.demovalidate.liteflow.process.node;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProcessNodeStatusEnum {
    STAGE_NODE_STATUS_UNKNOWN("UNKNOWN", "未知"),
    STAGE_NODE_STATUS_WAITING("WAITING", "等待中"),
    STAGE_NODE_STATUS_OPEN("OPEN", "打开中"),
    STAGE_NODE_STATUS_FINISHED("FINISH", "已完成"),
    STAGE_NODE_STATUS_CANCEL("CANCEL", "取消"),
    STAGE_NODE_STATUS_ABNORMAL("ABNORMAL", "异常的"),
    STAGE_NODE_STATUS_HIDDEN("HIDDEN", "隐藏的"),
    STAGE_NODE_STATUS_TEMP_REST("TEMP_REST", "临时重置状态"),//当前状态为中转状态，仅用于推动状态机扭转阶段状态，不应展示给用户
    ;

    private final String code;
    private final String message;


    public static ProcessNodeStatusEnum getByCode(String code) {
        for (ProcessNodeStatusEnum v : ProcessNodeStatusEnum.values()) {
            if (v.getCode().equals(code)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
