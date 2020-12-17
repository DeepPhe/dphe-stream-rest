/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.healthnlp.deepphe.stream.spring.filter;

import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import org.healthnlp.deepphe.stream.spring.exception.AccessDeniedException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author zhy19
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    @Value("${auth.token}")
    private String authToken;
    
    private static final String AUTH_HEADER = "Authorization";
    public static final String AUTH_SCHEME_BEARER = "Bearer";

    private static final AccessDeniedException BEARER_AUTH_TOKEN_REQUIRED = new AccessDeniedException("Auth token is required.");
    private static final AccessDeniedException BEARER_AUTH_SCHEME_REQUIRED = new AccessDeniedException("Bearer Authentication scheme is required to access this resource.");
    private static final AccessDeniedException BEARER_AUTH_INVALID_TOKEN = new AccessDeniedException("Invalid auth token provided.");

    // https://jersey.java.net/documentation/latest/filters-and-interceptors.html
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String method = requestContext.getMethod();
        String path = requestContext.getUriInfo().getPath(true);
        // No auth needed to see the WADL
        if (method.equals("GET") && path.equals("application.wadl")) {
            return;
        }
        
        // Based on testing, getHeaderString() matches the header string in a case-insensitive way
        // So no need to worry about the cases of the "Authorization" header
        String authCredentials = requestContext.getHeaderString(AUTH_HEADER);
        if (authCredentials == null) {
            throw BEARER_AUTH_TOKEN_REQUIRED;
        }

        // All other endpoints use bearer JWT to verify the API consumer
        // Use lower case to check since HTTP headers are case-insentive
        // Add a space to make sure "Bearer" is sepatated from the token string by a space
        if (!authCredentials.toLowerCase().startsWith(AUTH_SCHEME_BEARER.toLowerCase() + " ")) {
            throw BEARER_AUTH_SCHEME_REQUIRED;
        }

        // Verify token
        // "\\s+" will cause any number of consecutive spaces to split the string into parts
        // Here we split the auth string by space(s) and the second part is the token
        String token = authCredentials.split("\\s+")[1];

        if (!token.equals(authToken)) {
            throw BEARER_AUTH_INVALID_TOKEN;
        }
 
    }

}
