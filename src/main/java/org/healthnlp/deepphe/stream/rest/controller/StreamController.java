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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * DeepPhe document Stream (combined NLP and Summary) pipeline Rest Controller.  Delegates to StreamService.
 * @author SPF , chip-nlp
 * @version %I%
 * @since 6/17/2020
 */
@Component
@Path("/process")
@Api( value = "StreamController" ) // Swagger
public class StreamController {


   private final StreamService _streamService;

   @Autowired

   public StreamController(final StreamService streamService) {
      _streamService = streamService;
   }

   @POST
   @Path( "/patient/{patientId}/doc/{docId}")
   // Swagger
   @ApiOperation(value = "Immediately process document text for a patient.")
   @ApiResponses(value = {
         @ApiResponse(code = 200, message = "Success"),
         @ApiResponse(code = 401, message = "Unauthorized"),
         @ApiResponse(code = 403, message = "Forbidden"),
         @ApiResponse(code = 404, message = "Not Found"),
         @ApiResponse(code = 500, message = "Failure")})
   @Consumes(MediaType.TEXT_PLAIN)
   @Produces(MediaType.TEXT_PLAIN)
   public Response summarizeDoc( @PathParam("patientId") final String patientId,
                                @PathParam("docId") final String docId,
                                final String text ) throws AnalysisEngineProcessException {
      final String result = _streamService.summarizeDoc( patientId, docId, text );
      return Response
            .status( Response.Status.OK )
            .entity( result )
            .build();
   }


   @POST
   @Path( "/patient/{patientId}/doc/{docId}")
   // Swagger
   @ApiOperation(value = "Immediately process document text for a patient and store the results.")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Success"),
           @ApiResponse(code = 401, message = "Unauthorized"),
           @ApiResponse(code = 403, message = "Forbidden"),
           @ApiResponse(code = 404, message = "Not Found"),
           @ApiResponse(code = 500, message = "Failure")})
   @Consumes(MediaType.TEXT_PLAIN)
   @Produces(MediaType.TEXT_PLAIN)
   public Response summarizeAndStoreDoc( @PathParam("patientId") final String patientId,
                                 @PathParam("docId") final String docId,
                                 final String text ) throws AnalysisEngineProcessException {
      final String result = _streamService.summarizeAndStoreDoc( patientId, docId, text );
      return Response
              .status( Response.Status.OK )
              .entity( result )
              .build();
   }

   @POST
   @Path( "/patient/{patientId}")
   // Swagger
   @ApiOperation(value = "Summarize a Patient based upon stored docs.")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Success"),
           @ApiResponse(code = 401, message = "Unauthorized"),
           @ApiResponse(code = 403, message = "Forbidden"),
           @ApiResponse(code = 404, message = "Not Found"),
           @ApiResponse(code = 500, message = "Failure")})
   @Consumes(MediaType.TEXT_PLAIN)
   @Produces(MediaType.TEXT_PLAIN)
   public Response summarizePatient( @PathParam("patientId") final String patientId ) throws AnalysisEngineProcessException {
      final String result = _streamService.summarizePatient( patientId );
      return Response
              .status( Response.Status.OK )
              .entity( result )
              .build();
   }


}
