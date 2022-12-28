package com.example.demovalidate.service.status;

import com.example.demovalidate.dto.ProcessBusinessDto;
import com.example.demovalidate.event.ProcessEvent;
import lombok.extern.slf4j.Slf4j;

@FunctionalInterface
public interface StatusBusinessFunctionInterface {

    String apply(ProcessBusinessDto dto);

    default String handle(ProcessBusinessDto dto){
        //todo before something
        System.out.println("入参："+dto);
        String apply = apply(dto);
        //todo after something
        System.out.println("出参："+apply);
        return apply;
    }
}
