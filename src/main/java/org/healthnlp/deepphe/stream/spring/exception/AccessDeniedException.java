package org.healthnlp.deepphe.stream.spring.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;


public class AccessDeniedException extends WebApplicationException {

    private static final long serialVersionUID = 4170696268993981747L;

    public AccessDeniedException( String message) {
        super(message, Status.UNAUTHORIZED);
    }

}
