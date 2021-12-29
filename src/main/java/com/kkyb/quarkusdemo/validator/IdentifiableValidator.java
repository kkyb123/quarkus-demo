package com.kkyb.quarkusdemo.validator;

import com.kkyb.quarkusdemo.domain.dto.Dto;
import com.kkyb.quarkusdemo.error.Problems;
import io.smallrye.mutiny.Uni;

import java.util.List;

public abstract class IdentifiableValidator<D extends Dto> implements Validator<D>{

    private final List<Validator<D>> supplementaryValidators;

    protected IdentifiableValidator(List<Validator<D>> supplementaryValidators) {
        this.supplementaryValidators = supplementaryValidators;
    }

    @Override
    public List<Validator<D>> supplementaryValidators() {
        return supplementaryValidators;
    }

    @Override
    public Uni<D> onUpdateValidation(D object) {
        if (object.id() == null) {
            return Uni.createFrom()
                    .failure(Problems.PAYLOAD_VALIDATION_ERROR
                            .withProblemError("object.id", "must not be null for an update operation")
                            .toException());
        }
        return Uni.createFrom().item(object);
    }
}
