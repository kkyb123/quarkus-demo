package com.kkyb.quarkusdemo;

import com.kkyb.quarkusdemo.error.Problem;
import com.kkyb.quarkusdemo.error.Problems;
import org.jboss.resteasy.reactive.RestResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface Responses {

    static <T> Response response(RestResponse.Status status, MediaType mediaType, T payload) {
        return RestResponse.ResponseBuilder
                .create(status)
                .entity(payload)
                .type(mediaType)
                .build()
                .toResponse();
    }

    static <T> Response response(RestResponse.Status status, T payload) {
        return response(status, MediaType.APPLICATION_JSON_TYPE, payload);
    }

    static Response error(Problem problem) {
        if(problem == null) {
            return response(RestResponse.Status.INTERNAL_SERVER_ERROR, Problems.INTERNAL_SERVER_ERROR);
        }
        return response(RestResponse.Status.fromStatusCode(problem.statusCode()), problem);
    }
}
