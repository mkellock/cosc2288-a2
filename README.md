# COSC2288 Assignment 2

This project is Matt Kellock's (s3812552) assignment 2 submission, a project management application.
It is built in Java utilising Maven as the build tool.

## Installation

Please ensure you have the following installed prior to execution:

- Java 1.8
- Maven

## Test

Run the following to execute unit tests

```bash
# Test the application
mvn test
```

## Compile

Run the following to compile the application

```bash
# Build the application for execution
mvn compile
```

## Execution

```bash
# Execute the application
mvn exec:java -Dexec.mainClass=com.cosc2288.App
```

## Information

### How did you apply MVC design pattern to build this application?

I prefer developing my applications from their models up rather than their views down, so started by mapping the objects and their functions in a basic UML diagram (see cosc2288-2.drawio for the early stage design). I wanted to keep controller logic out of the views, so the views only responsibility is to control the UI, passing back to the App class any data manipulation activities, this way the logic in the view code is only for UI manipulation, and the App code can act as a bridge between the view and the controller layers. This approach keeps view logic simple and purpose driven, however comes at the expense of making the App class more convoluted. A future version of the App class could be refactored into smaller classes with dependency injection chains to break it apart.

### How does your code adhere to SOLID design principles?



### What other design patterns does your code follow? Why did you choose these design patterns?



## Contributing

Sorry, no contributions are accepted as this is a university assignment.

## License

[GNU General Public License v3.0](https://choosealicense.com/licenses/gpl-3.0/)
