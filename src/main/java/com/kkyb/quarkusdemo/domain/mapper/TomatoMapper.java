package com.kkyb.quarkusdemo.domain.mapper;

import com.kkyb.quarkusdemo.domain.dto.Tomato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface TomatoMapper extends DomainMapper<Tomato, com.kkyb.quarkusdemo.domain.entity.Tomato> {

    @Override
    Tomato toDto(com.kkyb.quarkusdemo.domain.entity.Tomato entity);

    @Override
    com.kkyb.quarkusdemo.domain.entity.Tomato toEntity(Tomato dto);
}
