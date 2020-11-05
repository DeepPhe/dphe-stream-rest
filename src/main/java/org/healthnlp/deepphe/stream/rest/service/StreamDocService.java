package org.healthnlp.deepphe.stream.rest.service;


import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.nlp.pipeline.DmsRunner;
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
//public class StreamService extends Neo4jDriverService {
public class StreamDocService {

   static private final Logger LOGGER = Logger.getLogger( "StreamDocService" );

   static private volatile boolean _initialized = false;

//   @Autowired
//   public StreamService(final DeepPheProperties properties ) {
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
            System.out.println( "StreamDocService can't initialize " + initE.getMessage() );
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
    * @return JSON with document extracted and summarized information ASAP.
    * @throws AnalysisEngineProcessException if pipeline fails.
    */
   public String summarizeAndStoreDoc( final String patientId,
                               final String docId,
                               final String text ) throws AnalysisEngineProcessException {
      LOGGER.info( "Summarizing and Storing Document " + docId + " for patient " + patientId );
      System.out.println( "Summarizing and Storing Document " + docId + " for patient " + patientId );
      return DmsRunner.getInstance().summarizeAndStoreDoc( patientId, docId, text );
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
