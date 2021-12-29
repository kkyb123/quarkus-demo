package com.kkyb.quarkusdemo.error;

import com.kkyb.quarkusdemo.Responses;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.ws.rs.core.Response;
import java.util.List;

public class ExceptionMapper {

    @ServerExceptionMapper
    public Response mapException(Throwable cause) {
        if (cause instanceof AppException appException) {
            return Responses.error(appException.getProblem());
        }
        var problem = new Problem("Generic Exception", cause.getMessage(), 500,
                "Error", "", "00000001", List.of());
        return Responses.error(problem);
    }
}
