package com.kkyb.quarkusdemo.domain.dto;

public interface ProcessedVegetable {
  default String processedForm() {
    return "";
  }
}
