package com.kkyb.quarkusdemo.service;

import com.kkyb.quarkusdemo.domain.dto.Tomato;
import com.kkyb.quarkusdemo.domain.mapper.DomainMapper;
import com.kkyb.quarkusdemo.domain.mapper.TomatoMapper;
import com.kkyb.quarkusdemo.error.Problems;
import com.kkyb.quarkusdemo.repository.Repository;
import com.kkyb.quarkusdemo.repository.TomatoRepository;
import com.kkyb.quarkusdemo.validator.TomatoValidator;
import com.kkyb.quarkusdemo.validator.Validator;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TomatoService implements CrudService<Tomato, com.kkyb.quarkusdemo.domain.entity.Tomato> {

    TomatoRepository repository;
    TomatoValidator validator;
    TomatoMapper mapper;

    TomatoService(TomatoRepository repository, TomatoValidator validator, TomatoMapper mapper) {
        this.repository = repository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Repository<com.kkyb.quarkusdemo.domain.entity.Tomato> repository() {
        return repository;
    }

    @Override
    public Validator<Tomato> validator() {
        return validator;
    }

    @Override
    public DomainMapper<Tomato, com.kkyb.quarkusdemo.domain.entity.Tomato> mapper() {
        return mapper;
    }

    public Uni<Tomato> process(Tomato tomato) {
        return validator.onUpdate(tomato)
                .flatMap(tomato1 -> repository.findById(tomato1.id()))
                .onItem()
                .ifNull()
                .failWith(Problems.NOT_FOUND.toException())
                .map(entity -> entity.setProcessedForm("There you have your tomato sauce"))
                .map(mapper::toDto);
    }
}
