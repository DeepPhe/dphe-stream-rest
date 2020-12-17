package org.healthnlp.deepphe.stream.spring.config;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletProperties;
import org.healthnlp.deepphe.stream.spring.exception.mapper.WebApplicationExceptionMapper;
import org.healthnlp.deepphe.stream.spring.filter.CORSFilter;

import java.util.Collection;
import org.healthnlp.deepphe.stream.spring.filter.AuthFilter;


/**
 * Abstract Configuration for Swagger, Jersey.  Unnecessary abstract methods for annotation in extension reminder.
 * @author SPF , chip-nlp
 * @version %I%
 * @since 7/23/2020
 */
abstract public class AbstractJerseyConfig extends ResourceConfig {

   /**
    * A reminder to add @Configuration annotation to the class declaration.
    */
   abstract protected void addAtConfigurationToClass();

   /**
    * A reminder to add @Autowired annotation to the class constructor method.
    */
   abstract protected void addAtAutowiredToConstructor();

   /**
    * Make certain that you have a public constructor with @Autowired that calls super();
    */
   public AbstractJerseyConfig() {
      // Register all endpoint classes
      getComponents().forEach( super::register );

      // Register exception mapper
      register( WebApplicationExceptionMapper.class );

      //Register filters
      register(AuthFilter.class);
      register( CORSFilter.class );

      // By default, Jersey doesn't return any entities that would include validation errors to the client.
      // Enable Jersey bean validation errors to users
      property( ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true );

      // Swagger.json
      configureSwagger();
      property( ServletProperties.FILTER_FORWARD_ON_404, true );

   }

   /**
    *
    * @return Component classes that should be registered as REST controllers or endpoints or whatever you want to call it.
    */
   abstract protected Collection<Class<?>> getComponents();

   /**
    *
    * @return some ID, such as the name of the project.  e.g. dphe-stream-rest
    */
   abstract protected String getSwaggerConfigId();

   /**
    *
    * @return Some descriptive title for the resulting swagger documentation.  e.g. DeepPhe NLP REST Swagger Documentation.
    */
   abstract protected String getSwaggerTitle();

   /**
    *
    * @return the base path for auto searching.  e.g. /spring
    * Dependent modules should have spring classes in org.healthnlp.deepphe.stream.spring and not in their own package.
    */
   protected String getSwaggerBasePath() {
      return "/spring";
   }

   /**
    *
    * @return the full package for resources.  e.g. org.healthnlp.deepphe.stream.spring
    */
   protected String getSwaggerResourcePackage() {
      return "org.healthnlp.deepphe.stream.spring";
   }


   /**
    * Standard configuration for Swagger.
    */
   private void configureSwagger() {
      this.register( ApiListingResource.class );
      this.register( SwaggerSerializers.class );
      final BeanConfig config = new BeanConfig();
      config.setConfigId( getSwaggerConfigId() );
      config.setTitle( getSwaggerTitle() );
      config.setVersion( "0.4.0" );
      config.setBasePath( getSwaggerBasePath() );
      config.setResourcePackage( getSwaggerResourcePackage() );
      config.setScan( true );
   }



}
