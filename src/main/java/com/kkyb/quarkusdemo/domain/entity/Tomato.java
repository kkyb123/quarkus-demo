package com.kkyb.quarkusdemo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tomato")
public class Tomato extends BaseEntity {

    @Column
    private String name;

    @Column(name = "processed_form")
    private String processedForm;

    public String getName() {
        return name;
    }

    public Tomato setName(String name) {
        this.name = name;
        return this;
    }

    public String getProcessedForm() {
        return processedForm;
    }

    public Tomato setProcessedForm(String processedForm) {
        this.processedForm = processedForm;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tomato tomato = (Tomato) o;
        return Objects.equals(super.getId(), tomato.getId())
                && Objects.equals(name, tomato.name)
                && Objects.equals(processedForm, tomato.processedForm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), name, processedForm);
    }
}
