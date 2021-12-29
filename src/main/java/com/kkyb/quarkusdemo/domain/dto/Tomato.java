package com.kkyb.quarkusdemo.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.util.UUID;

public record Tomato(UUID id, @NotEmpty String name, @Null String processedForm) implements Vegetable,
        ProcessedVegetable {

  @Override
  public String type() {
    return "Tomato";
  }
}
