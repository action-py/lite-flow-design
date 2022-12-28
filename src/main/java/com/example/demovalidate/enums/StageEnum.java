package com.example.demovalidate.enums;

import lombok.Getter;

@Getter
public enum StageEnum {
    BIDDER_MAIN("BIDDER_MAIN","bidderMain",1),
    TENDER_MAIN("TENDER_MAIN","tenderMain",5),
    POST("POST","post",10),
    QUOTE("QUOTE","quote",20),
    CALIBRATION("CALIBRATION","calibration",30),
    SIGN("SIGN","sign",40);
    private String code;

    private String simpleCode;
    private Integer order;

    public static StageEnum getInstanceByCode(String code){
        for (StageEnum value : StageEnum.values()) {
            if(value.getCode().equals(code)){
                return value;
            }
        }
        throw new IllegalArgumentException();
    }

    StageEnum(String code,String simpleCode, Integer order) {
        this.code = code;
        this.simpleCode = simpleCode;
        this.order = order;
    }
}
