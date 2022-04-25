### Cake Manager REST API Application

This application run as the REST backend service and provide API to get all the Cakes, download all the cakes JSON and add new Cake.


### How to Run
This application is build on SpringBoot and build with the Maven. Please follow the below steps to run this application.
* Clone this repository
* Make sure you are on JDK 1.8 or above with Maven 3.x
* You can build the project and run the tests by running ```mvn clean install```
* Once successfully built, you can run the service using following command:
```
    java -jar target/cake-manager-api-0.0.1-SNAPSHOT.jar
```
Once the application runs you should see something like this

```
23:57:23.325 [main] INFO  o.s.b.w.e.tomcat.TomcatWebServer - Tomcat started on port(s): 8082 (http) with context path ''
23:57:23.337 [main] INFO  c.w.cakemgr.CakeManagerApplication - Started CakeManagerApplication in 4.776 seconds (JVM running for 5.424)
```

### About the Service
The service is just a simple cake manager REST service. It uses an file database (H2) to store the data. Database is initialsed with the default data using the file present at ```src\main\resources\test-data.json```


#### API Endpoints
Here are some endpoints you can call:

##### Get all the cakes.
```
http://localhost:8082/
```

##### Get all the cakes in JSON File
```
http://localhost:8082/cakes
```

##### Create new Cake
```
http://localhost:8082/cakes
Request Type: POST 
Accept: application/json
Content-Type: application/json
{
"title" : "Sample Cake",
"description" : "Very tasty",
"image" : "https://chelsweets.com/wp-content/uploads/2019/04/IMG_1029-2-735x1103.jpg",
}
RESPONSE: HTTP 201 (Created)

### Docker Container
Simple Dockerfile is created to deploy the application to the Docker container. If you have the Docker installed and running you can use the following command to build the docker image:
```
docker build -t cake-manager-api .
```

To run the application on Docker container use the following command:
```
docker run -p 8082:8082 springio/gs-spring-boot-docker
```
