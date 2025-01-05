# CoinCalculator

CoinCalculator is a Java-based project that aims to calculate the minimum number of coins needed to reach a target amount. It utilizes the Dropwizard framework for API development and comes with comprehensive test cases to ensure its functionality.

## Features
- **API Endpoint**: Calculate the minimum coins required for a given amount.
- **Customizable Denominations**: Supports user-defined coin denominations.
- **Error Handling**: Deals with invalid denominations and unconstructible target amounts.
- **Tests**: Incorporates unit tests using JUnit 4 and 5.

## Technologies Used
- **Java 17**: The programming language used for development.
- **Dropwizard (Core)**: Backend framework for developing RESTful APIs.
- **JUnit 4 & 5**: For testing and validation purposes.
- **Maven**: Manages dependencies and the build process.

## Project Structure
```
CoinCalculator
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.example.coincalculator
│   │   │   │   ├── resources  (API Resources)
│   │   │   │   ├── service    (Business Logic)
│   │   │   │   │   ├── model  (Data Models)
│   │   │   ├── CoinCalculatorApplication.java
│   │   │   ├── CoinCalculatorConfiguration.java
│   ├── test
│   │   ├── java
│   │   │   ├── com.example.coincalculator
│   │   │   │   ├── CoinServiceTest.java
├── target
├── Dockerfile
├── pom.xml
└──.gitignore
```

## How to Run Locally
### Prerequisites
- Java 17 or higher installed.
- Maven installed.
- Docker (optional, for containerized deployment).

### Steps
1. Clone the repository:
```bash
git clone https://github.com/your-username/CoinCalculator.git
cd CoinCalculator
```
2. Build the project:
```bash
mvn clean install
```
3. Run the application:
```bash
java -jar target/CoinCalculator-1.0-SNAPSHOT.jar server
```
4. Access the API:
The API is available at `http://localhost:8080/calculate`.
Example POST request payload:
```json
{
  "targetAmount": 126.5,
  "denominations": [0.5, 2.0, 5.0, 10.0, 50.0, 100.0]
}
```

## Docker Deployment
1. Build the Docker image:
```bash
docker build -t coin-calculator.
```
2. Run the Docker container:
```bash
docker run -p 8080:8080 coin-calculator
```

## API Endpoints
- **POST /calculate**:
    - **Request**: JSON payload containing `targetAmount` and `denominations`.
    - **Response**:
        - Success: Returns the minimum coins required.
        - Error: Provides appropriate error messages for invalid inputs.

## Testing
Run the tests with:
```bash
mvn test
```
Test cases cover:
- Valid input scenarios.
- Invalid coin denominations.
- Unconstructible target amounts.

## Built With
- **Dropwizard**: Lightweight Java framework for creating RESTful web services.
- **Maven**: Build and dependency management.
- **JUnit**: Testing framework. 
