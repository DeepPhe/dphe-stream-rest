package org.healthnlp.deepphe.stream.rest.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import javax.ws.rs.core.Response;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.healthnlp.deepphe.stream.rest.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * DeepPhe document Stream (combined NLP and Summary) pipeline Rest Controller.  Delegates to StreamService.
 * @author SPF , chip-nlp
 * @version %I%
 * @since 6/17/2020
 */
@Component
@Path("/summarizeDoc")
@Api( value = "StreamController" ) // Swagger
public class StreamController {


   private final StreamService _streamService;

   @Autowired

   public StreamController(final StreamService streamService) {
      _streamService = streamService;
   }

   @GET
   @Path( "/doc/{docId}")
   // Swagger
   @ApiOperation(value = "Immediately process document text for a patient.")
   @ApiResponses(value = {
         @ApiResponse(code = 200, message = "Success"),
         @ApiResponse(code = 401, message = "Unauthorized"),
         @ApiResponse(code = 403, message = "Forbidden"),
         @ApiResponse(code = 404, message = "Not Found"),
         @ApiResponse(code = 500, message = "Failure")})
   @Consumes(TEXT_PLAIN)
   @Produces(APPLICATION_JSON)
   public Response summarizeDoc( @PathParam("docId") final String docId,
                                final String text ) throws AnalysisEngineProcessException {
      final String result = _streamService.summarizeDoc( docId, text );
      return Response
            .status( Response.Status.OK )
            .entity( result )
            .build();
   }


}
