package com.kkyb.quarkusdemo.validator;

import io.smallrye.mutiny.Uni;

import java.util.List;

public interface Validator<T> {

    Uni<T> validation(T object);

    default List<Validator<T>> supplementaryValidators() {
        return List.of();
    }

    default Uni<T> onCreateValidation(T object) {
        return Uni.createFrom().item(object);
    }

    default Uni<T> onUpdateValidation(T object) {
        return Uni.createFrom().item(object);
    }

    default Uni<T> onCreate(T object) {
        return onCreateValidation(object)
                .flatMap(validated -> processValidatorList(supplementaryValidators(), validated));
    }

    default Uni<T> onUpdate(T object) {
        return onUpdateValidation(object)
                .flatMap(validated -> processValidatorList(supplementaryValidators(), validated));
    }

    default Uni<T> validate(T object) {
        return validation(object)
                .flatMap(validated -> processValidatorList(supplementaryValidators(), validated));
    }

    private Uni<T> processValidatorList(List<Validator<T>> validators, T object) {
        return validators.stream()
                .reduce(Uni.createFrom().item(object),
                        (uni, validator) -> uni.flatMap(validator::validation),
                        (uni, uni2) -> uni.flatMap(result -> uni2));
    }
}
