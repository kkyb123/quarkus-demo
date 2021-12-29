package com.kkyb.quarkusdemo.validator;

import com.kkyb.quarkusdemo.error.AppException;
import com.kkyb.quarkusdemo.error.ProblemError;
import com.kkyb.quarkusdemo.error.Problems;
import io.smallrye.mutiny.Uni;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Luminara Team.
 */
public class StructuralValidator<T> implements Validator<T> {

    public static <T> StructuralValidator<T> create() {
        return new StructuralValidator<>();
    }

    private StructuralValidator() {}

    @Override
    public Uni<T> validation(T object) {
        if (Objects.isNull(object)) {
            return Uni.createFrom()
                    .failure(new AppException(Problems.OBJECT_VALIDATION_ERROR
                            .withProblemError("object", "can't validate the structure of a null object")));
        }

        try (ValidatorFactory factory =
                javax.validation.Validation.buildDefaultValidatorFactory()) {
            javax.validation.Validator validator = factory.getValidator();
            Set<ConstraintViolation<T>> violations = validator.validate(object);

            if (!violations.isEmpty()) {
                List<ProblemError> problemErrors = violations.stream()
                        .map(constraintViolation -> new ProblemError(
                                constraintViolation.getPropertyPath().toString(),
                                constraintViolation.getMessage()))
                        .collect(Collectors.toList());
                return Uni.createFrom()
                        .failure(new AppException(Problems.OBJECT_VALIDATION_ERROR.withProblemErrors(problemErrors)));
            }
        }
        return Uni.createFrom().item(object);
    }
}
