# bk_shop124_app

There are Two To Run This Application :

1. Normal way of using Maven to run the Application
2. Use Docker to run the Application

        
          <!-- First Way to use Maven -->

<!-- MUST HAVE -->
JDK 8 
MYSQL

<!-- STEP FOR RUNNING APPLICATION  -->

STEP 1:CLone The Application from Github
STEP 2:Import your project in eclipse or intellij and  Update The Maven dependencies
STEP 3: CREATE DATABASE CALLED shop_db 
STEP 4: Update application.properties to set your Database Username and Password
STEP 5: Set Application server Port in application.properties or use already existing one 
STEP 6: RUN APPLICATION AS Springboot Application
STEP 7: Visit localhost:{port}/swagger-ui/index.html for Application Endpoints
        - {port} replcace port with port number you used in application.properties of the application 
        - eg: localhost:8000/swagger-ui/index.html


        <!-- Second Way to use Docker -->

- install Docker in your machine if you don't have 
- Clone the Application to your local Machine 
- navigate to your Application Folder 
- type command "docker-compose up " to start the application 
- open your browser and type localhost:8000/swagger-ui/index.html