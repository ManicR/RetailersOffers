# README: RetailersOffers

An example of a RESTful WebServer developed using SpringBoot.

## Requirements:
The fully fledged server uses the following:

* Spring Framework 
* SpringBoot 3.3.2
* H2 Database
* Maven Dependencies

## Building the project
You will need:

* Java 17
* Maven 3.1.1 or higher
  
## Git
Clone the project and use Maven to build the server

```  mvn clean install ``` <br>
<br>
and 
<br>

``` mvn spring-boot:run ```

## Port
Currently the Application will run in Default Port = 8080

## Swagger Path:
``` http://localhost:8080/swagger-ui/index.html#/ ```

## H2 Database console path:
``` http://localhost:8080/h2-console/ ```

## Database Configuration details:
* JDBC URL :``` jdbc:h2:mem:RetailerDB ```
* username:``` sa ```
* password :``` password ```

## Data-set
For Insert the sample data: 

```
INSERT INTO Transaction ( id ,  CUSTOMER_ID, date, amount) VALUES 
(1, 1, '2024-05-01', 120.00),
(2, 2, '2024-05-05', 150.00),
(3, 1, '2024-06-02', 80.00),
(4, 2, '2024-06-07', 135.00),
(5, 1, '2024-06-12', 142.00),
(6, 2, '2024-07-14', 77.00),
(7, 1, '2024-07-20', 90.00),
(8, 2, '2024-07-22', 130.00);
```
## GetAPI for finding rewards of the customer:

``` http://localhost:8080/rewards/customer?cid=2 ```

# API's
Creating new transaction using Postman

## Post API
``` localhost:8080/rewards/transaction ```

body with JSON format <br>
Accept: application/json <br>

``` Content-Type: application/json ```

```
{
  "customerId": 1,
  "date": "2024-07-25",
  "amount": 230
}
```


## Get API's

Get the created Transaction by Id

``` localhost:8080/rewards/transaction/1 ```



Get the created Transactions by CustormerId

``` localhost:8080/rewards/customer/1 ```


## Setup :
* Transaction Entity Class
* TransactionModel DTO Class
* TransactionRepository Interface
* TransactionService Class
* TransactionController Class

## Transaction Entity:
Represents the database entity with lombok annotations for boilerplate code.

## TransactionModel Dto Class:
Represents the data Transfer object.

## Transaction Repository
Providing methods for accessing and manipulating transactions in the database.

## TransactionService Class:
Contains getRewardPointsForCustomer to get reward Points , And Mapper methods used for converting Entity with DTO simultaneously.

## TransactionController:
Having Post and Get API , for Handling Http requests and responses.
