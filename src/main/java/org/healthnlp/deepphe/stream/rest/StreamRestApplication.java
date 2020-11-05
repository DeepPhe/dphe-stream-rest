package org.healthnlp.deepphe.stream.rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 7/24/2020
 */
@SpringBootApplication
@ComponentScan({ "org.healthnlp.deepphe"})
public class StreamRestApplication {

   public static void main( final String... args ) {
      SpringApplication.run( StreamRestApplication.class, args );
   }


}
