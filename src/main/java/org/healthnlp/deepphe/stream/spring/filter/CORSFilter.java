package org.healthnlp.deepphe.stream.spring.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 *
 * Jun 5, 2016 10:49:39 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

    private static final String ALLOW_ORIGIN_HEADER = "Access-Control-Allow-Origin";

    private static final String REQUEST_HEADER = "Access-Control-Request-Headers";

    private static final String ALLOW_HEADER = "Access-Control-Allow-Headers";

    private static final String ALLOW_METHODS_HEADER = "Access-Control-Allow-Methods";

    private static final String ALLOW_CREDENTIALS_HEADER = "Access-Control-Allow-Credentials";

    private static final String MAX_AGE_HEADER = "Access-Control-Max-Age";

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.add(ALLOW_ORIGIN_HEADER, "*");
        headers.add(ALLOW_METHODS_HEADER, "POST, GET, OPTIONS, DELETE");
        headers.add(MAX_AGE_HEADER, "3600");
        headers.add(ALLOW_HEADER, "x-requested-with");
    }

}
