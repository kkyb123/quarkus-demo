package com.kkyb.quarkusdemo.domain.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Table(name = "potato")
public class Potato extends BaseEntity {

  @Column
  private String name;

  @Column(name = "processed_form")
  private String processedForm;

  public String getName() {
    return name;
  }

  public Potato setName(String name) {
    this.name = name;
    return this;
  }

  public String getProcessedForm() {
    return processedForm;
  }

  public Potato setProcessedForm(String processedForm) {
    this.processedForm = processedForm;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Potato potato = (Potato) o;
    return Objects.equals(super.getId(), potato.getId())
            && Objects.equals(name, potato.name)
            && Objects.equals(processedForm, potato.processedForm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.getId(), name, processedForm);
  }
}
