# Fun-Times
Some fun for all

----

This is an exercise for [Henrys grocery](Henrys%20grocery.md)

## Prerequsites

- [Open JDK 8] or above (https://openjdk.java.net/)

Run

    java --version
    
to confirm 

## Building
This projects uses [Gradle](http://gradle.org)

To build project, run

    ./gradlew build
    
## Running
After the project is built,

    java -jar build/libs/Fun-Times-Forked-By-Adrian-0.0.1-SNAPSHOT.jar

Inputs and outputs are via command line.

Press [Ctrl-Z] or [command-Z] to exit the application 

## To Do

 - Link to an external payment microservice
 - Containize this app, for example, use Docker
 - Stock Control
   - Handle situations where an item is out of stock
 - Input and Output
   - Currently command line prompt is used and it is cumbersome.  funtimes.App and funtimes.command.** are excluded from test coverage
 - Discount contigent
   - Discounts, say for bread could be buy 2 soup or milk and get 1 loaf of bread half price
 - Stock items, prices and discount can change over time
 - Currency conversion
 - Shipping costs 
 


