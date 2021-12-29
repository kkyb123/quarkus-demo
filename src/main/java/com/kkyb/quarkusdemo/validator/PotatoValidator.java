package com.kkyb.quarkusdemo.validator;

import com.kkyb.quarkusdemo.domain.dto.Potato;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PotatoValidator extends IdentifiableValidator<Potato> {

    PotatoValidator() {
        super(List.of(StructuralValidator.create()));
    }

    @Override
    public Uni<Potato> validation(Potato potato) {
        return Uni.createFrom().item(potato);
    }
}
