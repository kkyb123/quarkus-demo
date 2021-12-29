package com.kkyb.quarkusdemo.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public interface Problems {
    Logger LOGGER = LoggerFactory.getLogger(Problems.class);

    String BAD_PAYLOAD_KEY = "Bad Payload";
    String VALIDATION_ERROR_KEY = "Validation Error";
    String INTERNAL_SERVER_ERROR_KEY = "Internal Server Error";

    Problem JSON_DESERIALIZATION_ERROR =
            new Problem(BAD_PAYLOAD_KEY, "Unable to deserialize the object provided.", 400,
                    "Serialization/Deserialization Error", "", "00000001", List.of());

    Problem JSON_SERIALIZATION_ERROR =
            new Problem("JSON Serialization Error", "Unable to serialize the object provided.", 400,
                    "Serialization/Deserialization Error", "", "00000002", List.of());

    Problem PAYLOAD_VALIDATION_ERROR =
            new Problem(BAD_PAYLOAD_KEY, "The payload provided is invalid.", 400,
                    VALIDATION_ERROR_KEY, "", "00000003", List.of());

    Problem ID_VALIDATION_ERROR =
            new Problem(BAD_PAYLOAD_KEY, "The ID provided is invalid.", 400,
                    VALIDATION_ERROR_KEY, "", "00000013", List.of());

    Problem NO_PAYLOAD_PROVIDED_ERROR =
            new Problem(BAD_PAYLOAD_KEY, "The payload provided should not be null.", 400,
                    VALIDATION_ERROR_KEY, "", "00000004", List.of());

    Problem NULL_OBJECT_PROVIDED_ERROR =
            new Problem("Null Object", "The object provided should not be null.", 400,
                    VALIDATION_ERROR_KEY, "", "00000005", List.of());

    Problem OBJECT_VALIDATION_ERROR = new Problem("Bad Object", "The object provided is invalid.",
            400, VALIDATION_ERROR_KEY, "", "00000006", List.of());

    Problem FORBIDDEN_OPERATION_ERROR = new Problem("Forbidden Operation",
            "This request is forbidden.", 403, "Authorisation Error", "", "00002000", List.of());

    Problem NOT_FOUND = new Problem("Not Found", "The requested resource was not found.", 404,
            "Resource Error", "", "00002001", List.of());

    Problem INTERNAL_SERVER_ERROR = new Problem(INTERNAL_SERVER_ERROR_KEY,
            "An error occurred while processing the request.", 500, INTERNAL_SERVER_ERROR_KEY, "",
            "00002002", List.of());

    Problem DATABASE_ERROR = new Problem("Database Error", "A database error occurred.", 500,
            "Persistence Error", "", "00002003", List.of());

    static <T extends Throwable> Problem fromThrowable(T cause) {
        LOGGER.debug("Problems.fromThrowable(cause) => invoked");
        LOGGER.trace("cause => {}", cause.getMessage(), cause);
        if (cause instanceof AppException) {
            return ((AppException)cause).getProblem();
        }
        return INTERNAL_SERVER_ERROR.with("Generic Exception", cause.getMessage());
    }

    static AppException toThrowable(Problem problem) {
        LOGGER.debug("Problems.toThrowable(problem) => invoked");
        LOGGER.trace("problem => {}", problem);
        return new AppException(Objects.requireNonNullElse(problem, INTERNAL_SERVER_ERROR));
    }
}
