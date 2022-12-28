package com.example.demovalidate.validator;

import lombok.Data;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 项目名称：cupid
 * 类 名 称：OfferDto
 * 类 描 述：TODO
 * 创建时间：2022/12/6 18:27
 * 创 建 人：panyong
 */
@GroupSequenceProvider(OfferFormatGroupSequenceProvider.class)
@Data
public class OfferDto implements Serializable {
    //@OfferFormat(message = "这是一个测试")
    private String offerNo;
    @NotNull(message = "价格不为空",groups = {WhenAge20And30Group.class})
    private double price;
    @NotNull(message = "名称不为空",groups = {WhenAge30And40Group.class})
    private String userName;
}
