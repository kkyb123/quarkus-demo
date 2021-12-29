package com.kkyb.quarkusdemo.service;

import com.kkyb.quarkusdemo.domain.dto.Potato;
import com.kkyb.quarkusdemo.domain.mapper.DomainMapper;
import com.kkyb.quarkusdemo.domain.mapper.PotatoMapper;
import com.kkyb.quarkusdemo.error.Problems;
import com.kkyb.quarkusdemo.repository.PotatoRepository;
import com.kkyb.quarkusdemo.repository.Repository;
import com.kkyb.quarkusdemo.validator.PotatoValidator;
import com.kkyb.quarkusdemo.validator.Validator;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PotatoService implements CrudService<Potato, com.kkyb.quarkusdemo.domain.entity.Potato> {

    PotatoRepository repository;
    PotatoValidator validator;
    PotatoMapper mapper;

    @Inject
    PotatoService(PotatoRepository repository, PotatoValidator validator, PotatoMapper mapper) {
        this.repository = repository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Repository<com.kkyb.quarkusdemo.domain.entity.Potato> repository() {
        return repository;
    }

    @Override
    public Validator<Potato> validator() {
        return validator;
    }

    @Override
    public DomainMapper<Potato, com.kkyb.quarkusdemo.domain.entity.Potato> mapper() {
        return mapper;
    }

    public Uni<Potato> process(Potato potato) {
        return validator.onUpdate(potato)
                .flatMap(potato1 -> repository.findById(potato1.id()))
                .onItem()
                .ifNull()
                .failWith(Problems.NOT_FOUND.toException())
                .map(entity -> entity.setProcessedForm("There you have your potato chips"))
                .map(mapper::toDto);
    }
}
