# MicroServices Assessment

This is a Micro-Service Project providing Student Management, Fee Collection and Receipt Generation APIs.
It uses Sprint Boot, Spring Cloud, Eureka (Discovery Server) and Zuul (API gateway, Proxy)

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
3. **Run the service registry and discovery application**
4. **Run the Config Server**
5. **Run the Student microservice application**
6. **Run the Transaction microservice application**
7. **Run the API gateway Application**
8. **Package the application**