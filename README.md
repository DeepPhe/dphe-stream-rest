# DeepPhe Stream Rest Controller

DeepPhe Rest Controller for supporting the Kentucky Cancer Patient Data Management System (CPDMS) and the SEER-DMS.

## Prerequisites - You must have the following installed to build
* Oracle Java 1.8
* Maven 3.x

## Build

````
mvn clean package
````

## Start the API HTTP server

````
java -jar target/deepphe-stream-rest.jar
````

## API documentation

This API is developed with Jersey, which supports WADL. You can view the generated WADL by going to `http://localhost:9000/api/application.wadl?detail=true` and see all resource available in the application.

You can also view the generated the `swagger.json` at `http://localhost:9000/api/swagger.json`.
