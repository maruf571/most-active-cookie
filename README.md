# Most Active Cookie
This is a java application that process a csv file and find out the most active 
cookie(s) for a specific day from the log file. cookie_log.csv is the sample file.

## Assumptions
- If multiple cookies meet that criteria, please return all of them on separate lines
- Please only use additional libraries for testing, logging and cli-parsing. There are libraries for most
languages which make this too easy (e.g pandas) and we’d like you to show off your coding skills
- You can assume -d parameter takes date in UTC time zone
- You have enough memory to store the contents of the whole file
- Cookies in the log file are sorted by timestamp (most recent occurrence is the first line of the file)

## Tools & technology
- Language: Java 11
- Build tool: Maven
- Framework: Spring boot 2.7.5
- Test framework: Junit5

## Build, Test and Run 
### Development 
- On development environment, run the command like following 
```
mvn spring-boot:run -Dspring-boot.run.arguments="--f=cookie_log.csv --d=2018-12-09"
```
### Test
- To run the test, use following command
```
mvn clean test
```
### Build application
- To build application, run the following
```
mvn clean package
```

### Run jar
- After build, run the following to execute the application
```
java -jar target/cookiefinder-1.0.0.jar --f=cookie_log.csv --d=2018-12-09
```

## Problem running with specific version of java? Use docker
- build docker image
```
# For arm processor
docker buildx build --platform linux/arm64 -t cookiefinder .
# For amd processor
docker buildx build --platform linux/amd64 -t cookiefinder .
```

- run docker image
```
docker run \
 -v $(pwd)/cookie_log.csv:/opt/app/cookie_log.csv \
 cookiefinder \
 --f=/opt/app/cookie_log.csv \
 --d=2018-12-09
```  


## List of exceptions
- InvalidArgumentException: When there are problems with command line argument input
- FileReadException: When there are problems with reading file, date format etc.
- NotFoundException: If file not found on the proper location


## Directory structures
### Source directory
- dto: Contain pojo
- exception: Error and exception related class
- extractor: Extract most active cookies from the map
- parser: Parse log and command line argument
- service: Service aggregate all the functionality 
- validator: Validate different input 
- resources: Spring boot resources folder 
- CookieFinderApplication: Start point of the application

### Test directory
- extractor: Unit test for extractor
- integrationtest: Integration test
- parser: Unit test for parser



