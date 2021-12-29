package com.kkyb.quarkusdemo.error;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

public record Problem(@JsonProperty("title") String title, @JsonProperty("detail") String detail,
                      @JsonProperty("statusCode") Integer statusCode,
                      @JsonProperty("type") String type, @JsonProperty("instance") String instance,
                      @JsonProperty("errorCode") String code,
                      @JsonProperty("invalidParams") List<ProblemError> problemErrors)
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 952764315510279419L;

    public Problem with(String title, String detail) {
        return new Problem(title, detail, statusCode, type, instance, code, problemErrors);
    }

    public Problem withProblemError(ProblemError problemError) {
        if(problemErrors == null) {
            return new Problem(title, detail, statusCode, type, instance, code, List.of(problemError));
        }
        var errors = Stream.concat(problemErrors.stream(), Stream.of(problemError))
                .toList();
        return new Problem(title, detail, statusCode, type, instance, code, errors);
    }

    public Problem withProblemError(String name, String reason) {
        return withProblemError(new ProblemError(name, reason));
    }

    public Problem withProblemErrors(List<ProblemError> newProblemErrors) {
        if(problemErrors == null) {
            return new Problem(title, detail, statusCode, type, instance, code, newProblemErrors);
        }
        var errors = Stream.concat(problemErrors.stream(), newProblemErrors.stream())
                .toList();
        return new Problem(title, detail, statusCode, type, instance, code, errors);
    }

    public AppException toException() {
        return new AppException(this);
    }
}
