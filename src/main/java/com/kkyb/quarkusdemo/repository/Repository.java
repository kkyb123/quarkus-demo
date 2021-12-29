package com.kkyb.quarkusdemo.repository;

import com.kkyb.quarkusdemo.domain.entity.Entity;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;

import java.util.UUID;

public interface Repository<E extends Entity> extends PanacheRepositoryBase<E, UUID> {
}
