package org.healthnlp.deepphe.stream.rest.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.healthnlp.deepphe.stream.rest.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;


/**
 * DeepPhe document Stream (combined NLP and Summary) pipeline Rest Controller.  Delegates to StreamService.
 * This is the same as StreamController except that it uses PUT instead of GET.
 * @author SPF , chip-nlp
 * @version %I%
 * @since 6/17/2020
 */
@Component
@Path("/summarizeOneDoc")
@Api( value = "StreamOneDocController" ) // Swagger
public class StreamOneDocController {


   private final StreamService _streamService;

   @Autowired

   public StreamOneDocController( final StreamService streamService ) {
      _streamService = streamService;
   }

   @PUT
   @Path( "/doc/{docId}")
   // Swagger
   @ApiOperation(value = "Immediately process document text for a patient, no actual storage for PUT.")
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
