package org.healthnlp.deepphe.stream.spring.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Zhou Yuan <zhy19@pitt.edu>
 */
public class BadRequestException extends WebApplicationException {

    private static final long serialVersionUID = 307417604685672217L;
    
    public BadRequestException(String message) {
        super(message, Response.Status.BAD_REQUEST);
    }
}
