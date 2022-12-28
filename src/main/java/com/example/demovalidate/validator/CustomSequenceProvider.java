package com.example.demovalidate.validator;

import com.example.demovalidate.dto.OfferDto;
import com.example.demovalidate.inter.PlanA;
import com.example.demovalidate.inter.PlanB;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：demo-validate
 * 类 名 称：CustomSequenceProvider
 * 类 描 述：TODO
 * 创建时间：2022/12/7 11:51
 * 创 建 人：panyong
 */
public class CustomSequenceProvider implements DefaultGroupSequenceProvider<OfferDto> {
    /**
     * 当前模式
     * 默认校验通过了才会校验指定的分组校验 ==》返回分组校验的错误提示
     * 默认校验未通过时不会校验分组校验 ==》返回默认校验的错误提示
     * @param offerDto
     * @return
     */
    @Override
    public List<Class<?>> getValidationGroups(OfferDto offerDto) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();

        defaultGroupSequence.add(OfferDto.class);
        if(offerDto!=null){
            if(null==offerDto.getPrice()){
                defaultGroupSequence.add(PlanA.class);
            }
            if(null==offerDto.getUserName() || "".equals(offerDto.getUserName())){
                defaultGroupSequence.add(PlanB.class);
            }
        }


        //defaultGroupSequence.add(offerDto != null ? PlanA.class: PlanB.class);

        return defaultGroupSequence;
    }
}
