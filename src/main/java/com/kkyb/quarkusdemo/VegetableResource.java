package com.kkyb.quarkusdemo;

import com.kkyb.quarkusdemo.domain.dto.Potato;
import com.kkyb.quarkusdemo.domain.dto.Tomato;
import com.kkyb.quarkusdemo.domain.dto.Vegetable;
import com.kkyb.quarkusdemo.error.Problems;
import com.kkyb.quarkusdemo.service.PotatoService;
import com.kkyb.quarkusdemo.service.TomatoService;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.UUID;
import java.util.stream.Stream;

@Path("/vegetable")
public class VegetableResource {

    PotatoService potatoService;
    TomatoService tomatoService;

    VegetableResource(PotatoService potatoService, TomatoService tomatoService) {
        this.potatoService = potatoService;
        this.tomatoService = tomatoService;
    }

    @POST
    public Uni<Response> create(Vegetable vegetable) {
        return (switch (vegetable) {
            case Potato potato -> potatoService.create(potato);
            case Tomato tomato -> tomatoService.create(tomato);
        }).map(dto -> Responses.response(RestResponse.Status.CREATED, dto));
    }

    @GET
    public Uni<Response> find() {
        return potatoService.findAll()
                .flatMap(potatoes -> tomatoService.findAll()
                        .map(tomatoes -> Stream.concat(potatoes.stream(), tomatoes.stream()))
                        .map(Stream::toList))
                .map(dto -> Responses.response(RestResponse.Status.OK, dto));
    }

    @GET
    @Path("/potato/{id}")
    public Uni<Response> findPotato(String id) {
        UUID uuid;
        try {
            uuid =  UUID.fromString(id);
        } catch (IllegalArgumentException exception) {
            return Uni.createFrom()
                    .item(Responses.error(Problems.ID_VALIDATION_ERROR));
        }
        return potatoService.find(uuid)
                .map(dto -> Responses.response(RestResponse.Status.OK, dto));
    }

    @GET
    @Path("/tomato/{id}")
    public Uni<Response> findTomato(String id) {
        UUID uuid;
        try {
            uuid =  UUID.fromString(id);
        } catch (IllegalArgumentException exception) {
            return Uni.createFrom()
                    .item(Responses.error(Problems.ID_VALIDATION_ERROR));
        }
        return tomatoService.find(uuid)
                .map(dto -> Responses.response(RestResponse.Status.OK, dto));
    }

    @GET
    @Path("/potato")
    public Uni<Response> findPotatoes() {
        return potatoService.findAll()
                .map(dtos -> Responses.response(RestResponse.Status.OK, dtos));
    }

    @GET
    @Path("/tomato")
    public Uni<Response> findTomatoes() {
        return tomatoService.findAll()
                .map(dtos -> Responses.response(RestResponse.Status.OK, dtos));
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@RestPath String id, Vegetable vegetable) {
        if(!id.equals(vegetable.id().toString())) {
            return Uni.createFrom()
                    .item(Responses.error(Problems.ID_VALIDATION_ERROR));
        }
        return (switch (vegetable) {
            case Potato potato -> potatoService.update(potato);
            case Tomato tomato -> tomatoService.update(tomato);
        }).map(dto -> Responses.response(RestResponse.Status.OK, dto));
    }

    @PUT
    @Path("/process/{id}")
    public Uni<Response> process(@RestPath String id, Vegetable vegetable) {
        if(!id.equals(vegetable.id().toString())) {
            return Uni.createFrom()
                    .item(Responses.error(Problems.ID_VALIDATION_ERROR));
        }
        return (switch (vegetable) {
            case Potato potato -> potatoService.process(potato);
            case Tomato tomato -> tomatoService.process(tomato);
        }).map(dto -> Responses.response(RestResponse.Status.OK, dto));
    }
}