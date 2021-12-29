package com.kkyb.quarkusdemo.service;

import com.kkyb.quarkusdemo.domain.dto.Dto;
import com.kkyb.quarkusdemo.domain.entity.Entity;
import com.kkyb.quarkusdemo.domain.mapper.DomainMapper;
import com.kkyb.quarkusdemo.error.Problems;
import com.kkyb.quarkusdemo.repository.Repository;
import com.kkyb.quarkusdemo.validator.Validator;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.UUID;

public interface CrudService<D extends Dto, E extends Entity> {

    default Uni<D> create(D dto) {
        return validator().onCreate(dto)
                .map(mapper()::toEntity)
                .flatMap(repository()::persistAndFlush)
                .map(mapper()::toDto);
    }

    default Uni<D> find(UUID id) {
        return repository().findById(id)
                .onItem()
                .ifNull()
                .failWith(Problems.NOT_FOUND.toException())
                .map(mapper()::toDto);
    }

    default Uni<List<D>> findAll() {
        return repository().findAll()
                .list()
                .map(entities -> entities
                        .stream()
                        .map(mapper()::toDto)
                        .toList());
    }

    default Uni<D> update(D dto) {
        return validator().onUpdate(dto)
                .flatMap(dto1 -> repository().findById(dto1.id()))
                .onItem()
                .ifNull()
                .failWith(Problems.NOT_FOUND.toException())
                .replaceWith(mapper().toEntity(dto))
                .flatMap(entity -> repository()
                        .getSession()
                        .flatMap(session -> session
                                .merge(entity)
                                .call(session::flush)))
                .map(mapper()::toDto);
    }

    Repository<E> repository();

    Validator<D> validator();

    DomainMapper<D, E> mapper();
}
