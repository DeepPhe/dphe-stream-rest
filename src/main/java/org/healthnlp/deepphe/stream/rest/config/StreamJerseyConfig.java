package org.healthnlp.deepphe.stream.rest.config;

import org.healthnlp.deepphe.stream.rest.controller.StreamController;
import org.healthnlp.deepphe.stream.rest.controller.StreamDocController;
import org.healthnlp.deepphe.stream.rest.controller.StreamPatientController;
import org.healthnlp.deepphe.stream.rest.controller.StreamQueueController;
import org.healthnlp.deepphe.stream.spring.config.AbstractJerseyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collection;

/**
 * Adds NlpController to the configuration.
 *
 * @author SPF , chip-nlp
 * @version %I%
 * @since 7/24/2020
 */
@Configuration
public class StreamJerseyConfig extends AbstractJerseyConfig {

   @Autowired
   public StreamJerseyConfig() {
      super();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected void addAtConfigurationToClass() {}

   /**
    * {@inheritDoc}
    */
   @Override
   protected void addAtAutowiredToConstructor() {}

   /**
    * {@inheritDoc}
    */
   @Override
   protected Collection<Class<?>> getComponents() {
      return Arrays.asList( StreamController.class, StreamDocController.class, StreamOneDocController, StreamQueueController.class, StreamPatientController.class );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected String getSwaggerConfigId() {
      return "deepphe-stream-rest";
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected String getSwaggerTitle() {
      return "DeepPhe Stream REST Swagger Documentation.";
   }


}
