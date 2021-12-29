package com.kkyb.quarkusdemo.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProblemError(@JsonProperty("name") String name,
                           @JsonProperty("reason") String reason) {
}
