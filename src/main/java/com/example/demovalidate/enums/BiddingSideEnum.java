package com.example.demovalidate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BiddingSideEnum {

    BIDDING_SIDE_BIDDER("BIDDER", "招标方"),
    BIDDING_SIDE_TENDER("TENDER", "投标方"),
    ;

    private final String code;
    private final String message;


    public static BiddingSideEnum getByCode(String code) {
        for (BiddingSideEnum v : BiddingSideEnum.values()) {
            if (v.getCode().equals(code)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
