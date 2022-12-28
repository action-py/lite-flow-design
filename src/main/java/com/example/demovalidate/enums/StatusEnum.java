package com.example.demovalidate.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    WAITING("WAITING","waiting","等待"),
    OPEN("OPEN","open","开启"),
    FINISH("FINISH","finish","完成"),
    ABNORMAL("ABNORMAL","abnormal","驳回"),
    REST("REST","rest","驳回"),
    ABNORMAL_NODE_REST("ABNORMAL_NODE_REST","abnormal_node_rest","驳回");

    private String code;
    private String name;

    private String desc;

    StatusEnum(String code, String name,String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }
}
