# SawmillApp
**Version 1.0**

This is a Springboot application to expose Rest API and perform CRUD operation with the in-memory database H2.

## prerequisite

Java 1.8+

Maven 3.6+

Java IDE(STS recommended for Springboot development)

API Testing tool(POSTMAN)

### Building from Source

Download the source code and go to the project location where you find pom.xml and run the below maven command

``` mvn clean install ```

#### Running the Application

Once the build is success you can find the application jar in the target folder of the project location.

The default port of application is 8080 and the profile set is dev.

Based on the preference you can change the port and profile

Command to run with profiles and port: ``` java -jar SawmillApp-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev --server.port=9090 ```

Command to run with default profile and port: ``` java -jar SawmillApp-0.0.1-SNAPSHOT.jar ```

Add the symbol & at the end of the command to run the application in the backgroup


#### End points

Application perform the Crud operation and the values are stored in the in-memory database.

Change the configuration as per your need in the properties file

Create Sawmill     : /sawmill/createSawmill
Read Specific ID   : /sawmill/getSawmill/id
List all entries   : /sawmill/listAllSawmill
Update             : /sawmill/updateSawmill/id
Delete             : /sawmill/deleteSawMill/id

##### Testing the application

You can use any API testing tool which is comfortable 

I have used Postman to test the API

**Create Sawmill**

Request: POST

URL: localhost:8080/sawmill/createSawmill

Sample Payload(Body) 
```
{
"name":"SAWMILL 0655"
}
```
Response Payload
```
{
    "id": 1,
    "name": "SAWMILL 0655",
    "createdAt": "2022-01-31@07:37:28"
}
```

**Update Sawmill**

Request: PUT

URL: localhost:8080/sawmill/updateSawmill/1

Sample Payload(Body)
```
{
"name":"SAWMILL 0655",
"city":"madrid",
"country":"spain",
}
```
Response Payload
```
{
    "id": 1,
    "name": "SAWMILL 0655",
    "city": "madrid",
    "country": "spain",
    "createdAt": "2022-01-31@07:37:28",
    "updatedAt": "2022-01-31@07:44:57"
}
```

**Read Specific ID**

Request: GET

URL: localhost:8080/sawmill/getSawmill/1

Response Payload
```
{
    "id": 1,
    "name": "SAWMILL 0655",
    "city": "madrid",
    "country": "spain",
    "createdAt": "2022-01-31@07:37:28",
    "updatedAt": "2022-01-31@07:44:57"
}
```
List All

Request: GET

URL: localhost:8080/sawmill/listAllSawmill

Response Payload
```
[
    {
        "id": 1,
        "name": "SAWMILL 0655",
        "city": "delhi",
        "country": "germany2",
        "createdAt": "2022-01-31@07:37:28",
        "updatedAt": "2022-01-31@07:44:57"
    }
]
```
Delete

Request: DELETE

URL: localhost:8080/sawmill/deleteSawMill/1

Response: no-content HTTP.STATUS=204




