package org.healthnlp.deepphe.stream.spring.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 *
 * @author zhy19
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
        headers.add(ALLOW_METHODS_HEADER, "POST, GET, PUT, OPTIONS, DELETE");
        // Cache the response to this preflight request in browser for the max age 86400 seconds (= 24 hours)
        headers.add(MAX_AGE_HEADER, "86400");
        headers.add(ALLOW_HEADER, "Authorization, Cache-Control, Content-Type");
    }

}
