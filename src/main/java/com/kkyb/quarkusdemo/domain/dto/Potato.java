package com.kkyb.quarkusdemo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.util.UUID;

public record Potato(UUID id, @NotEmpty String name, @Null String processedForm) implements Vegetable,
        ProcessedVegetable {

  @Override
  @JsonProperty
  public String type() {
    return "Potato";
  }
}
