package org.healthnlp.deepphe.stream.spring.exception.mapper;


import org.healthnlp.deepphe.stream.spring.dto.ErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * Generic Exception mapper.
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Context
    private HttpServletRequest httpRequest;

    @Override
    public Response toResponse(WebApplicationException exception) {
        Response response = exception.getResponse();
        StatusType statusType = response.getStatusInfo();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(statusType.getReasonPhrase());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setPath(httpRequest.getPathInfo());
        errorResponse.setStatus(statusType.getStatusCode());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return Response.status(statusType).entity(errorResponse).build();
    }

}
