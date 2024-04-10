# MicroServices Assessment

This is a Micro-Service Project providing Student Management, Fee Collection and Receipt Generation APIs.
It uses Sprint Boot, Spring Cloud, Eureka (Discovery Server) and Spring Cloud API Gateway.

##Getting Started
These instruction will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
You need to install the following software:
* Java JDK 17+
* Maven 3.0+

### Usage
The best way to run this application is with IDEs' like IntelliJ IDEA or Eclipse.

## How to run?

### Build all modules:

'micro-service-assessment> ./mvnw clean package -DskipTests=true'

### Start each micro-serivce in local:

**Local:** 'micro-service-assessment/student-service> ./mvnw sprint-boot:run'

* config-server:
  * hostname: config-server
  * Ports: 8088:8088
  * URL: http://localhost:8088/

* service-registry:
    * hostname: service-registry
    * Ports: 8761:8761
    * URL: http://localhost:8761/

* student-service:
    * hostname: student-service
    * Ports: 8082:8082
    * URL: http://localhost:8082/

* transaction-service:
    * hostname: transaction-service
    * Ports: 8081:8081
    * URL: http://localhost:8081/

* gateway-service:
    * hostname: gateway-service
    * Ports: 8060:8060
    * URL: http://localhost:8060/

## Architecture
This system consists of following modules:
- **config-server** - a module that uses Sprint Cloud Config Server for running configuration server in the 'native' mode.
- **service-registry** - a module that uses Sprint Cloud Netflix Eureka as an embedded discovery server.
- **student-service** - a module containing the first microservice that allow to perform CRUD operation on in-memory repository of students.
- **transaction-service** - a module containing the second microservice that allow to perform CRUD operation on in-memory repository of transaction and receipts. It also communicates with student-service.
- **gateway-service** - a module that uses Sprint Cloud Gateway for running Sprint Boot application that acts as a proxy/gateway in our architecture.

### Setup
To run this project, install it locally as follow:

1. **Clone the application**

    ```bash
   git clone https://github.com/MakAarti/MicroServicesAssessment.git
   ```

2. **Change the servers' port for the applications as per you want**

   Each application will start at the server on the port as specified by default.
   If you want to change the default `port` for the microservice

   + open `src/main/resources/application.yml` file
   + change `server.port` property

   
3. **Run the service registry and discovery application**

   You can run the discovery service by typing the following command

    ```bash
    cd service-registry
    mvn sprint-boot:run
    ```

    The server will start on port `8761` by default, So once you have successfully started application you'll be able to visit the Eureka dashboard under address `http//localhost:8761`
   If you changed the port in `src/main/resources/application.yml` files, the use your custom port `http://localhost:port`

4. **Run the Config Server**

   You can run the config server microservice by typing the following command

    ```bash
    cd config-server
    mvn sprint-boot:run
    ```

    The config-server microservice will start on port `8088` by default.
   If you changed the port in `src/main/resources/application.yml` files, the use your custom port `http://localhost:port`
   But we will use the address of **Zuul API Gateway** as a routing address for all microservie requests.
   
5. **Run the Student microservice application**

   You can run the student microservice by typing the following command

    ```bash
    cd student-service
    mvn sprint-boot:run
    ```

    The student microservice will start on port `8082` by default, so you'll be able to visit the student microservice under address `http://localhost:8082`.
   If you changed the port in `src/main/resources/application.yml` files, use your custom port `http://localhost:port`
   But we will use the address of **API Gateway** as a routing address for all microservie requests.
   
6. **Run the Transaction microservice application**

   You can run the transaction microservice by typing the following command

    ```bash
    cd transaction-service
    mvn sprint-boot:run
    ```

    The transaction microservice will start on port `8081` by default, so you'll be able to visit the transaction microservice under address `http://localhost:8081`.
   If you changed the port in `src/main/resources/application.yml` files, use your custom port `http://localhost:port`
   But we will use the address of **API Gateway** as a routing address for all microservie requests.

   
7. **Run the API gateway Application**

   You can run the API gateway service by typing the following command

    ```bash
    cd gateway-service
    mvn sprint-boot:run
    ```

    The transaction microservice will start on port `8060` by default, so you'll be able to visit the transaction microservice under address `http://localhost:8060`.

   **API Gateway will forward the request to the specific microservice based on its proxy configuration.**
   
8. **Package the application**

   You can also package the application in the form of a `jar` file and then run each application like so 

    ```bash
    cd service_directory
    mvn clean package
    java -jar target/service_name-0.0.1-SNPSHOT.jar
    ```

    + *service_directory*: the directory of the service.
    + *service_name*: the name of the service


### Running

To access the application use the following endpoints

**Eureka Discovery Service**

After running discovery-service, its monitoring console available on `8761` port.

`http://localhost:8761`

* **API Gateway and Microservices**

  + View info about `API Gateway` serivce `http://localhost:8060/actuator/info`
  + Check Health for `API Gateway` serivce `http://localhost:8060/actuator/health`
 
  API Gateway is configured to be available under its default port 8060 and it forwards requests:

  * From `/api/v1/student/` parth to `student-service`
  * From `/api/v1/transaction/` parth to `transaction-service`
  * From `/api/v1/receipt/` parth to `transaction-service`

### Swagger

http://localhost:8082/swagger-ui/index.html ( student-service )

http://localhost:8081/swagger-ui/index.html ( transaction-service )

### H2-Database Console

http://localhost:8082/h2-console ( student-service )

http://localhost:8081/h2-console ( transaction-service )


### Postman Collection

https://github.com/MakAarti/MicroServicesAssessment/blob/main/MicroServiceAssessment.postman_collection.json
