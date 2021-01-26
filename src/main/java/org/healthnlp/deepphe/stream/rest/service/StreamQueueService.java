package org.healthnlp.deepphe.stream.rest.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.neo4j.node.Text;
import org.healthnlp.deepphe.nlp.pipeline.DmsRunner;
//import org.healthnlp.deepphe.spring.property.DeepPheProperties;
//import org.healthnlp.deepphe.spring.service.Neo4jDriverService;
//import org.neo4j.driver.Driver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


/**
 * DeepPhe document Stream pipeline Rest Service.  Uses Neo4j Driver connection.
 *
 * @author SPF , chip-nlp
 * @version %I%
 * @since 7/24/2020
 */
@Service
//public class StreamQueueService extends Neo4jDriverService {
public class StreamQueueService {

   static private final Logger LOGGER = Logger.getLogger( "StreamQueueService" );

   static private volatile boolean _initialized = false;

//   @Autowired
//   public StreamQueueService(final DeepPheProperties properties ) {
//      super( properties );
//   }

   /**
    *
    * @throws ResourceInitializationException if Stream pipeline couldn't be initialized.
    */
   @PostConstruct
   public void init() throws ResourceInitializationException {
      synchronized ( LOGGER ) {
         if ( _initialized ) {
            return;
         }
         LOGGER.info( "Initializing analysis engine ..." );
         System.out.println( "Initializing analysis engine ..." );
         try {
            // The first access of a singleton enum instantiates it.
            DmsRunner.getInstance();
         } catch ( ExceptionInInitializerError initE ) {
            System.out.println( "StreamQueueService can't initialize " + initE.getMessage() );
            throw new ResourceInitializationException( initE );
         }
         LOGGER.info( "Analysis Engine Initialized." );
         System.out.println( "Analysis Engine Initialized." );
         _initialized = true;
      }
   }


   /**
    *
    * @param patientId patient ID.
    * @param docId document ID.
    * @param text plaintext document.
    * @return Message that text has been or could not be added to the document queue.
    * @throws AnalysisEngineProcessException if queueing fails.
    */
   public String queueAndStoreDoc( final String patientId,
                            final String docId,
                            final String text ) throws AnalysisEngineProcessException {
      final String result = DmsRunner.getInstance().queueAndStoreDoc( patientId, docId, text );
      final Text gText = new Text();
      gText.setName( "Document Queued" );
      gText.setValue( result );
      final Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
      return gson.toJson( gText );
   }

//   /**
//    * {@inheritDoc}
//    */
//   @Override
//   protected void addAtServiceToClass() {}
//
//   /**
//    * {@inheritDoc}
//    */
//   @Override
//   protected void addAtAutowiredToConstructor() {}
//
//   /**
//    * {@inheritDoc}
//    */
//   @Override
//   @Bean
//   public Driver neo4jDriver() {
//      return createDriver();
//   }


}
