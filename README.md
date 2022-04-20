### What is it? 🤔

This is a SpringBoot application that runs on Apache Tomcat created with usage of Maven. It also contains some
additional dependencies on top of spring-boot-starters e.g., Lombok to make coding faster, appache-commons to not
reinvent the wheel and junit / mockito / assertj for unit testing.

Implementation is located in `/src/main/java` and unit tests are in `src/test/java`. 

### How to run it? 🏃

In order to run it locally please use Maven and type

`mvn spring-boot:run`

in directory that contains pom.xml file (it would look like X/zadanie, where X is the path to directory where the .zip
file was extracted into).

The application starts at port 8080. To change it please add `server.port=X` into properties file, where X is number of
a port.

As for mappings - I've made an assumption that mappings are predefined. So I've decided to use a file in resources
folder called `mappings.json` to define said mappings (I've only defined two mappings for numbers up to 4, so if you
want to test it, please either edit the file with additional mappings).

The application reads the file and then based on that it responds to end-user request.

### API 🔗

As for API the application has one public endpoint - `\divisors` that accepts `POST` requests. If one would like to
access it, it's possible using `localhost:8080/divisors` endpoint.

The application accepts request in form of JSON body e.g:

```json
{
  "category": "Furniture",
  "numberList": [
    1,
    2,
    3,
    4
  ]
}
```
where the `category` is the name of mapping user wants to use and `numberList` is a list of numbers to find divisors for.

Application responds with JSON message that looks like this:
```json
{
    "mappingList": [
        {
            "number": 1,
            "divisors": [
                "Chair"
            ]
        },
        {
            "number": 4,
            "divisors": [
                "Chair",
                "Bed",
                "Table"
            ]
        }
    ]
}
```
where `number` is initial number and `divisors` is a list of mapped divisors.

In successful cases application responds with HTTP Status Code 200.
In case of invalid request application responds with status 400 Bad Request.