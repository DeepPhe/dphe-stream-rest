package org.healthnlp.deepphe.stream;

import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.nlp.pipeline.CrDmsRunner;
import org.healthnlp.deepphe.stream.rest.service.StreamService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

final public class DocSummarizer {

    static private final Logger LOGGER = Logger.getLogger( "DocSummarizer" );

    public static void main( final String... args ) {
        LOGGER.info( "Initializing ..." );
        final StreamService service = new StreamService();
        try {
            service.init();
        } catch ( ResourceInitializationException riE ) {
            LOGGER.error( riE.getMessage() );
            System.exit( -1 );
        }
        LOGGER.info( "Reading doc " + args[0] + " from " + args[1] );
        final String docId = args[ 0 ];
        String text = "";
        try {
            text = new String ( Files.readAllBytes( Paths.get( args[ 1 ] ) ) );
        } catch ( IOException ioE ) {
            LOGGER.error( "Processing Failed:\n" + ioE.getMessage() );
            System.exit( -1 );
        }
        LOGGER.info( "Doc " + args[0] + " has size: " + text.length() );
        // Process doc text
        try {
            final String json = service.summarizeDoc( docId, text );
            LOGGER.info( "Result JSON:\n" + json );
        } catch ( AnalysisEngineProcessException aeE ) {
            LOGGER.error( "Processing Failed:\n" + aeE.getMessage() );
            System.exit( -1 );
        }
        CrDmsRunner.getInstance().close();
        System.exit( 0 );
    }

}
