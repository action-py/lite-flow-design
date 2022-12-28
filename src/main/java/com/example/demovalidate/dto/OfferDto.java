package com.example.demovalidate.dto;

import com.example.demovalidate.annotation.OfferFormat;
import com.example.demovalidate.inter.PlanA;
import com.example.demovalidate.inter.PlanB;
import com.example.demovalidate.validator.CustomSequenceProvider;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 项目名称：demo-validate
 * 类 名 称：OfferDto
 * 类 描 述：TODO
 * 创建时间：2022/12/7 10:33
 * 创 建 人：panyong
 */

//@GroupSequenceProvider(value = CustomSequenceProvider.class)
@ToString
@Data
public class OfferDto {
    @OfferFormat(message = "编号不可为空")
    private String offerNo;
    @NotNull(message = "价格不为空",groups = {PlanA.class})
    private Integer price;
    @NotBlank(message = "名称不为空",groups = {PlanA.class,PlanB.class})
    private String userName;
}
