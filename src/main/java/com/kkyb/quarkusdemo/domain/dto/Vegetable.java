package com.kkyb.quarkusdemo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = Potato.class, name = "Potato"),
  @JsonSubTypes.Type(value = Tomato.class, name = "Tomato")
})
public sealed interface Vegetable extends Dto permits Potato, Tomato {

  String name();

  @JsonProperty
  String type();
}
