package com.kkyb.quarkusdemo.domain.mapper;

import com.kkyb.quarkusdemo.domain.dto.Dto;
import com.kkyb.quarkusdemo.domain.entity.Entity;

public interface DomainMapper<D extends Dto, E extends Entity> {
    D toDto(E entity);
    E toEntity(D dto);
}
