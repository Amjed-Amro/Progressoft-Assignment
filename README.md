# ClusteredData Warehouse


## About The Project

This project serves as a robust data warehousing solution designed to facilitate comprehensive FX deal analysis for Bloomberg. It plays a pivotal role by accepting incoming FX deals and efficiently persisting them into a PostgreSQL database. By doing so, it creates a centralized and structured repository of valuable deal data, empowering users to perform in-depth analysis, generate insightful reports, and make informed decisions. 

To ensure the integrity and reliability of the data warehouse, the system implements a series of key functionalities:

* Request Fields: The system meticulously captures essential deal information, including Deal Unique Id, From Currency ISO Code (Ordering Currency), To Currency ISO Code, Deal timestamp, and Deal Amount in ordering currency.

* Validate Row Structure: Robust validation checks are in place to verify data integrity. These checks cover aspects such as missing fields and data type/format validation.

* Prevent Duplicate Imports: To maintain data consistency, the system has been designed to prevent the accidental import of the same request data more than once, eliminating duplication.

* No Rollback Allowed: Once a row is successfully imported into the system, it is saved in the database without the option for rollback. This approach ensures that every row imported is securely stored in the database, safeguarding the integrity of your FX deal data.

## Getting Started

These instructions will give you a copy of the project up and running on
your local machine for development and testing purposes.

### Prerequisites

In order to ensure the successful development, testing, and deployment of this data warehousing solution, there are specific software and tools that are essential prerequisites:

* [Java-17]: The project relies on Java 17, a robust and modern programming language, to provide the foundation for its functionality.

* [Docker]: Docker, the industry-standard containerization platform, is a critical component for ensuring the seamless deployment of this system, making it efficient and portable.

* [IDE] : (Integrated Development Environment): An IDE is indispensable for developers, providing a comprehensive environment for coding, testing, and debugging. Choose an IDE that aligns with your preferences and development practices.


### Installing and Deployment

A step by step series of examples that tell you how to get a development
environment running

First you need to clone this repository

    git clone https://github.com/Amjed-Amro/Progressoft-Assignment.git

And then thile in the repository file use this to deploy the database and the application

    sudo doocker-compose up -d

now you have two containers running, postgres database "fxdeals-DB" and the application container "fxdeals-spring-app-1"

## Running the tests

There are two types of test that you can run using any IDE you want

### Unit Tests

This test has been designed to comprehensively assess all the application methods responsible for the addition of new FX deals to the database.

In order to execute this test successfully, it is imperative that the PostgreSQL database container is operational. Therefore, please ensure that the database container is up and running before initiating the test. Subsequently, you can commence the test by launching the application through your preferred Integrated Development Environment (IDE).


### Integration Tests

This performance test is specifically designed to assess the efficiency and reliability of the API responsible for bulk addition of FX Deals to the database. It does so by sending HTTP requests to the application and subsequently validating the response.

To ensure the successful execution of this test, it is critical that the PostgreSQL database container is operational. Prior to initiating the test, please confirm that the database container is in an active state. Following this verification, you can initiate the test by launching the application within your chosen Integrated Development Environment (IDE)

## How to use api

This application operates on port 8081 on the local host. The API endpoint for adding a new FX Deal can be accessed at http://localhost:8081/api/v1/addDeal for Post Requests and on http://localhost:8081/api/v1/getDeal/{id} for Get Request.



## POST Request 

### Request Guidelines

- The API only accepts POST requests on "http://localhost:8081/deals/api/v1/addDeal" with a JSON payload conforming to the following structure:

```json
{
	"id": "Must be an integer and unique",
	"fromCurrencyIsoCode": "Must be a valid currency ISO code",
	"toCurrencyIsoCode": "Must be a valid currency ISO code",
	"dealTimeStamp": "Must follow the format dd-mm-yyyy mm:hh",
	"dealAmount": "Must be a double"
}
```

### Successful Response

- When the request adheres to these guidelines, a 200 success code is returned, along with a response body structured as follows:

```json
{
	"response": {
		"code": "000",
		"message": "Request Processed Successfully",
		"status": "SUCCESS"
	},
	"body": {
		"id": 5,
		"fromCurrencyIsoCode": "USD",
		"toCurrencyIsoCode": "JOD",
		"dealTimeStamp": "12-02-2022 17:00",
		"dealAmount": 20.0
	}
}
```

### Error Handling

- In case the request does not comply with the specified rules, a 400 bad request code is returned. The response body provides information about the encountered issues:

```json
{
	"response": {
		"code": "001",
		"message": "Invalid Request",
		"status": "REJECTED"
	},
	"body": {
		"toCurrencyIsoCode": "must match \"^[A-Z]{3}$\"",
		"fromCurrencyIsoCode": "From currencyISO code cannot be empty"
	}
}
```
By following these guidelines, you can effectively interact with the application's API and handle responses based on the provided codes and structures.



## GET Requests

### Request Guidelines

- The API only accepts GET requests on "http://localhost:8081/api/v1/getDeal/{id}" whith changing {id} with an integer

### Successful Response

- When the request adheres to these guidelines, a 200 success code is returned, along with a response body structured as follows:

```json
{
	"response": {
		"code": "000",
		"message": "Request Processed Successfully",
		"status": "SUCCESS"
	},
	"body": {
		"id": 5,
		"fromCurrencyIsoCode": "USD",
		"toCurrencyIsoCode": "USD",
		"dealTimeStamp": "12-02-2022 17:00",
		"dealAmount": 20.0
	}
}
```

### Error Handling

- In case the request contains an Id that cant be found in database, a 400 bad request code is returned. The response body provides information about the encountered issues:

```json
{
	"response": {
		"code": "001",
		"message": "fx deal not found",
		"status": "REJECTED"
	}
}
```
- In case the request contains a non integer Id, a 400 bad request code is returned. The response body provides information about the encountered issues:

```json
{
	"response": {
		"code": "999",
		"message": "Sorry! Service Technical Error",
		"status": "FAILED"
	}
}
```

## Author
  - **Amjed Amro** 
