package org.healthnlp.deepphe.stream.spring.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ResourceNotFoundException extends WebApplicationException {

    private static final long serialVersionUID = 2866404024557445862L;

    public ResourceNotFoundException() {
        super("Resource not found.", Response.Status.NOT_FOUND);
    }

    public ResourceNotFoundException(String message) {
        super(message, Response.Status.NOT_FOUND);
    }

}
