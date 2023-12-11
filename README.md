# Demo Applications

This repository contains a collection of demo applications implemented in Java. Each application showcases a different concept or data structure.

## Applications

Currently, the repository includes the following applications:

- Stack: A demo application showcasing the implementation of a Stack data structure. For more details, refer to the [Stack README](./src/main/java/com/example/demo/stack/README.md).

## Getting Started

To run any of the applications, you need to have Java and Maven installed on your machine.

1. Clone the repository:

```sh
git clone https://github.com/username/repository.git
cd repository
```

2. Navigate to the application directory:

```sh
cd src/main/java/com/example/demo/<application-directory>
```

3. Build the application:

```sh
mvn clean install
```

4. Run the application:

```sh
mvn spring-boot:run
```

The application will start and listen on `http://localhost:8080`.

## Testing

Each application comes with unit tests. You can run them using the following command:

```sh
mvn test
```

## Contributing

Contributions are welcome. Please open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
