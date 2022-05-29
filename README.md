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

S: Each class in my application has a single responsibility, models represent one object, controllers control one model and views represent one editable element, or control the base application where elements are loaded into.

O: Each of the models, controllers and app classes have an interfaces to enable complete rewrites of existing class logic, additionally, each class can be overridden to alter partial logic.

L: There is not any derived classes in this application, however, some exist in the view layer. Derivations of underlying JavaFX UI controls are unchanged, extending their behaviour without altering it.

I: Each model and controller implements a purposeful interface, taking the interface segregation principal to its extreme, I could have built out interfaces where methods are similar (e.g. name getters and setters) and inherit from multiple interfaces, however, the intent behind each method may be different even if the name of the method is the same, thus it was a design choice to duplicate interface code.

D: This is best seen in the controllers, where connection strings are passed in from the App class. This is especially useful in testing where the connection object is different from the main application.

### What other design patterns does your code follow? Why did you choose these design patterns?

In the early days of the application design, I experimented with the Hibernate ORM and using the factory pattern to inject database connections. Although the factory pattern worked fine, I could not get the ORM to behave with SQLite, thus replaced it with JDBC queries. The View -> App -> Controller relationship uses the Chain of Responsibility pattern, where each layer makes decisions on its own to pass on to the next.

## Contributing

Sorry, no contributions are accepted as this is a university assignment.

## License

[GNU General Public License v3.0](https://choosealicense.com/licenses/gpl-3.0/)
