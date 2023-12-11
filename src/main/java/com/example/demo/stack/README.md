# Stack Demo Application

This repository contains a demo application showcasing the implementation of a Stack data structure in Java. The application is built using Spring Boot and provides a RESTful API to interact with the Stack.

## Features

The application provides the following endpoints to interact with the Stack:

- `GET /stack/isEmpty`: Check if the stack is empty.
- `GET /stack/isFull`: Check if the stack is full.
- `GET /stack/peek`: Get the top item from the stack without removing it.
- `POST /stack/pop`: Remove the top item from the stack.
- `POST /stack/popAll`: Remove all items from the stack.
- `POST /stack/push`: Add an item to the top of the stack.
- `POST /stack/setMaxSize`: Set the maximum size of the stack.
- `GET /stack/stackList`: Get a list of all items in the stack.

## Live Implementation

The application is live at [https://polymorphicheroes.com/stacks/](https://polymorphicheroes.com/stacks/).

## Getting Started

To run the application locally, you need to have Java and Maven installed on your machine.

1. Clone the repository:

```sh
git clone https://github.com/username/repository.git
cd repository
```

2. Build the application:

```sh
mvn clean install
```

3. Run the application:

```sh
mvn spring-boot:run
```

The application will start and listen on `http://localhost:8080`.

## Testing

The application comes with unit tests. You can run them using the following command:

```sh
mvn test
```

## Stack Visualization

The application includes a visualization of the stack using D3.js. You can access it by opening the `index.html` file located in [`src/main/resources/static/stack`](command:_github.copilot.openRelativePath?%5B%22src%2Fmain%2Fresources%2Fstatic%2Fstack%22%5D "src/main/resources/static/stack").

## Contributing

Contributions are welcome. Please open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
