package com.kkyb.quarkusdemo.validator;

import com.kkyb.quarkusdemo.domain.dto.Tomato;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TomatoValidator extends IdentifiableValidator<Tomato>{

    TomatoValidator() {
        super(List.of(StructuralValidator.create()));
    }

    @Override
    public Uni<Tomato> validation(Tomato tomato) {
        return Uni.createFrom().item(tomato);
    }
}
