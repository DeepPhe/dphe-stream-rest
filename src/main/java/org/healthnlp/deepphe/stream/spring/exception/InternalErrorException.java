package org.healthnlp.deepphe.stream.spring.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


public class InternalErrorException extends WebApplicationException {

    private static final long serialVersionUID = 4206999110807572726L;

    public InternalErrorException() {
    }

    public InternalErrorException(String message) {
        super(message, Response.Status.INTERNAL_SERVER_ERROR);
    }

}
