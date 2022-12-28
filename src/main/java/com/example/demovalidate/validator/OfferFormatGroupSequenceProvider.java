package com.example.demovalidate.validator;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 项目名称：cupid
 * 类 名 称：OfferFormatGroupSequenceProvider
 * 类 描 述：TODO
 * 创建时间：2022/12/6 18:56
 * 创 建 人：panyong
 */
public class OfferFormatGroupSequenceProvider implements DefaultGroupSequenceProvider<OfferDto> {
    @Override
    public List<Class<?>> getValidationGroups(OfferDto dto) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(OfferDto.class); // 这一步不能省,否则Default分组都不会执行了，会抛错的
        defaultGroupSequence.add(Objects.nonNull(dto) ? WhenAge20And30Group.class : WhenAge30And40Group.class);
        return defaultGroupSequence;
    }
}
