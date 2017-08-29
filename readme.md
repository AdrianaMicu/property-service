# Property service for vacation rentals #

This is a **Property Service** written in **Java 8** and using the **Spring-Boot** framework to develop the **REST API** that allows any client to connect to the service and manage and view different properties.

The build tool used is **Gradle**. In the **build.gradle** file, there are all the dependencies needed for the project.

**Mockito** has been used to help with the unit testing.

# Introduction #

The service REST API allows: property creation, property update, property delete, property viewing.

For this solution, the data will not persist beyond the lifespan of the server.

# Prerequisites #

Have your prefferred IDE installed.
Have JDK installed.

# Functionality #

Using basic **curl** syntax or a tool like **Postman** for example, the service supports following requests:

**POST** request to create a listing:

http://{hostname}:{port}/property-service/listings

#### Request Body example
	{
		"contact": {
			"phone": "15126841100"
		},
		"address": {
			"address": "1011 W 5th St",
			"postalCode": "1011",
			"countryCode": "US",
			"city": "Austin",
			"state": "TX"
		},
		"location": {
			"lat": 40.4255485534668,
			"lng": -3.7075681686401367
		}
	}

#### Response Body example
	{
		"id": "e1aed9b3-4f90-4acb-9056-f482c7b556ee",
		"contact": {
			"phone": "15126841100",
			"formattedPhone": "+1 512-684-1100"
		},
		"address": {
			"address": "1011 W 5th St",
			"postalCode": "1011",
			"countryCode": "US",
			"city": "Austin",
			"state": "TX",
			"country": "United States"
		},
		"location": {
			"lat": 40.4255485534668,
			"lng": -3.7075681686401367
		}
	}


**GET** request to retrieve a listing by id:

http://{hostname}:{port}/property-service/listings/{id}

#### Response Body example
	{
		"id": "e1aed9b3-4f90-4acb-9056-f482c7b556ee",
		"contact": {
			"phone": "15126841100",
			"formattedPhone": "+1 512-684-1100"
		},
		"address": {
			"address": "1011 W 5th St",
			"postalCode": "1011",
			"countryCode": "US",
			"city": "Austin",
			"state": "TX",
			"country": "United States"
		},
		"location": {
			"lat": 40.4255485534668,
			"lng": -3.7075681686401367
		}
	}


**PUT** request to update a previously created listing by id:

http://{hostname}:{port}/property-service/listings/{id}

#### Request Body example
	{
		"contact": {
			"phone": "15126841101"
		},
		"address": {
			"address": "1012 W 5th St",
			"postalCode": "1012",
			"countryCode": "US",
			"city": "Austin",
			"state": "TX"
		},
		"location": {
			"lat": 41.4255485534668,
			"lng": -4.7075681686401367
		}
	}

#### Response Body example
	{
		"id": "e1aed9b3-4f90-4acb-9056-f482c7b556ee",
		"contact": {
			"phone": "15126841101",
			"formattedPhone": "+1 512-684-1101"
		},
		"address": {
			"address": "1012 W 5th St",
			"postalCode": "1012",
			"countryCode": "US",
			"city": "Austin",
			"state": "TX",
			"country": "United States"
		},
		"location": {
			"lat": 41.4255485534668,
			"lng": -4.7075681686401367
		}
	}

<br /><br />
**DELETE** request to delete a previously created listing by id

http://{hostname}:{port}/property-service/listings/{id}

<br /><br />


## Running the service

Make sure you have an IDE installed.
Make sure you have JDK installed.
Clone this project.

You can import it into your IDE and then get the Gradle dependecies and then run the project. Spring Boot will start up Tomcat and will listen by default on the 8080 port. The resources folder contains an **application.properties** file that stores the default port, context path and application name.

The project can also be built as a jar file and then started from the command line using the following command:

	java -jar property-service-1.0.0.jar


## Tests

The tests can be run from the IDE or from the command line using the following command:

	gradle test
