package com.example.demovalidate.enums;

import lombok.Getter;

@Getter
public enum ProcessNodeEnum {

    BIDDER_MAIN_POST(StageEnum.BIDDER_MAIN,"POST","post",1),
    BIDDER_MAIN_QUOTE(StageEnum.BIDDER_MAIN,"QUOTE","quote",2),
    BIDDER_MAIN_CALIBRATION(StageEnum.BIDDER_MAIN,"CALIBRATION","calibration",3),
    BIDDER_MAIN_SIGN(StageEnum.BIDDER_MAIN,"SIGN","sign",4),

    POST_RELEASE_DOC(StageEnum.POST,"RELEASE_DOC","releaseDoc",10),
    POST_AUDIT(StageEnum.POST,"AUDIT","audit",20),
    POST_RELEASE_ANNOUNCE(StageEnum.POST,"RELEASE_ANNOUNCE","releaseAnnounce",30),

    SIGN_EDIT(StageEnum.SIGN,"EDIT","edit",10),
    SIGN_CONFIRM(StageEnum.SIGN,"CONFIRM","confirm",20),
    SIGN_APPROVE(StageEnum.SIGN,"APPROVE","approve",30),
    SIGN_SIGN(StageEnum.SIGN,"SIGN","sign",40);
    private StageEnum stage;
    private String nodeCode;

    private String simpleCode;
    private Integer order;

    public static ProcessNodeEnum getNodeByStageAndNodeCode(String stageCode,String nodeCode){
        for (ProcessNodeEnum value : ProcessNodeEnum.values()) {
            if(value.getNodeCode().equals(nodeCode) && value.getStage().getCode().equals(stageCode)){
                return value;
            }
        }
        throw new IllegalArgumentException();
    }

    ProcessNodeEnum(StageEnum stage, String nodeCode, String simpleCode, Integer order) {
        this.stage = stage;
        this.nodeCode = nodeCode;
        this.simpleCode = simpleCode;
        this.order = order;
    }
}
