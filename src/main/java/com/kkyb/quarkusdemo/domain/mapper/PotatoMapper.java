package com.kkyb.quarkusdemo.domain.mapper;

import com.kkyb.quarkusdemo.domain.dto.Potato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PotatoMapper extends DomainMapper<Potato, com.kkyb.quarkusdemo.domain.entity.Potato> {

    @Override
    Potato toDto(com.kkyb.quarkusdemo.domain.entity.Potato entity);

    @Override
    com.kkyb.quarkusdemo.domain.entity.Potato toEntity(Potato dto);
}
