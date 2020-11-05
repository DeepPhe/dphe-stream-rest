package org.healthnlp.deepphe.stream.spring.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;


public class AccessForbiddenException extends WebApplicationException {

    private static final long serialVersionUID = 5034334598244081944L;

    public AccessForbiddenException(String message) {
        super(message, Status.FORBIDDEN);
    }

}
