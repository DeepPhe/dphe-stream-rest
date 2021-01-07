/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.healthnlp.deepphe.stream.spring.filter;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import org.healthnlp.deepphe.stream.rest.service.AuthFilterService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author zhy19
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    private final AuthFilterService authFilterService;
    
    @Autowired
    public AuthFilter(AuthFilterService authFilterService) {
        this.authFilterService = authFilterService;
    }
    
    // https://jersey.java.net/documentation/latest/filters-and-interceptors.html
    @Override
    public void filter(ContainerRequestContext requestContext) {
        String method = requestContext.getMethod();
        String path = requestContext.getUriInfo().getPath(true);
        // No auth needed to see the WADL
        if (method.equals("GET") && path.equals("application.wadl")) {
            return;
        }
        
        authFilterService.verifyAuthToken(requestContext);
 
    }

}
