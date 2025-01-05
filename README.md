# Coin Calculator - Backend

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
click the link to see:
https://github.com/SUPERpowerGT/2025_XUZIYI_Frontend?tab=readme-ov-file#docker-deployment


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

# Challenges and Solutions in Backend Development

During the development of the backend for the Coin Calculator project, several challenges arose due to unfamiliarity with the **Dropwizard framework**, dependency management, build configuration, algorithm implementation, and Docker configuration. Below is a structured summary of the difficulties and the solutions for each, organized for readability.

---

## Challenge 1: Unfamiliarity with the Dropwizard Framework

**Problem:**  
As Dropwizard was a new framework for us, we faced several difficulties in understanding its architecture and dependency management. Misconfigured dependencies often led to issues like errors during the packaging of the JAR file.

**Solution:**  
1. **Official Documentation**: We referred to the official Dropwizard documentation to understand its architecture and configuration requirements.
2. **Dependency Resolution**: After trial and error with incompatible dependencies, we resolved the issues by using dependency examples from the official documentation. Below is the final `pom.xml` configuration for dependencies:
   ```xml
   <dependencyManagement>
       <dependencies>
           <dependency>
               <groupId>io.dropwizard</groupId>
               <artifactId>dropwizard-bom</artifactId>
               <version>4.0.11</version>
               <type>pom</type>
               <scope>import</scope>
           </dependency>
       </dependencies>
   </dependencyManagement>
   <dependencies>
       <dependency>
           <groupId>io.dropwizard</groupId>
           <artifactId>dropwizard-core</artifactId>
       </dependency>
   </dependencies>
   ```

---

## Challenge 2: Build Configuration Issues

**Problem:**  
We initially struggled to configure the backend build system correctly. Specifically, the process of packaging the JAR file (`mvn clean package`) failed due to missing or incorrectly configured plugins in the `pom.xml`.

**Solution:**  
1. Added the `maven-shade-plugin` for building a self-contained JAR file:
   ```xml
   <build>
       <plugins>
           <plugin>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-shade-plugin</artifactId>
               <version>3.2.4</version>
               <executions>
                   <execution>
                       <phase>package</phase>
                       <goals>
                           <goal>shade</goal>
                       </goals>
                       <configuration>
                           <transformers>
                               <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                   <mainClass>com.example.coincalculator.CoinCalculatorApplication</mainClass>
                               </transformer>
                           </transformers>
                       </configuration>
                   </execution>
               </executions>
           </plugin>
       </plugins>
   </build>
   ```
2. Learned about the Maven build lifecycle by consulting online resources and official Maven documentation.

---

## Challenge 3: Algorithm Implementation

**Problem:**  
As we were unfamiliar with algorithm design, we initially implemented a **Greedy Algorithm** to solve the "minimum number of coins" problem. However, this approach failed in cases where the Greedy Algorithm could not find the optimal solution.

**Solution:**  
1. **Dynamic Programming**: After researching and studying materials on algorithm design, we decided to implement the **Dynamic Programming** approach to solve the problem.
2. **Example-Based Learning**: We referred to multiple examples of the "minimum coin problem" to understand and implement the logic. Below is a simplified version of the implemented solution:
   ```java
   public CoinResponse calculateMinimumCoins(CoinRequest request) {
       double target = request.getTargetAmount();
       List<Double> denominations = request.getDenominations();
       
       // Dynamic Programming Table
       int[] dp = new int[(int)(target * 100) + 1];
       Arrays.fill(dp, Integer.MAX_VALUE);
       dp[0] = 0;

       // Compute minimum coins for each amount
       for (int i = 1; i <= target * 100; i++) {
           for (double coin : denominations) {
               int coinValue = (int)(coin * 100);
               if (i >= coinValue && dp[i - coinValue] != Integer.MAX_VALUE) {
                   dp[i] = Math.min(dp[i], dp[i - coinValue] + 1);
               }
           }
       }

       if (dp[(int)(target * 100)] == Integer.MAX_VALUE) {
           throw new IllegalArgumentException("Cannot raise the target amount with the provided denominations.");
       }

       return new CoinResponse(...); // Convert results to the response
   }
   ```

---

## Challenge 4: Docker Configuration

**Problem:**  
Configuring Docker to containerize the backend application was challenging. Specifically, ensuring that the backend could run as a standalone container and integrate with the frontend required proper configuration.

**Solution:**  
1. **Dockerfile**: Created a Dockerfile to containerize the backend service:
   ```dockerfile
   FROM openjdk:17-jdk-slim

   WORKDIR /app

   COPY target/CoinCalculator-1.0-SNAPSHOT.jar app.jar

   EXPOSE 8080

   CMD ["java", "-jar", "app.jar", "server"]
   ```
2. **Network Configuration**: Ensured that the backend container was part of the same Docker network as the frontend container to enable communication.
3. **Testing**: Verified the Docker image locally before pushing it to Docker Hub.

---

## Summary

### Challenges:
1. **Unfamiliarity with Dropwizard**: Struggled to understand its architecture and dependency management.
2. **Build Configuration Issues**: Maven build failures due to missing or incorrect configurations.
3. **Algorithm Implementation**: The initial Greedy Algorithm could not solve all cases, requiring a switch to Dynamic Programming.
4. **Docker Configuration**: Ensuring the backend containerized application could work seamlessly with the frontend.

### Solutions:
1. **Dropwizard Documentation**: Resolved dependency and architecture issues by using examples from the official documentation.
2. **Maven Plugins**: Configured the `maven-shade-plugin` to create a runnable JAR file.
3. **Dynamic Programming**: Replaced the Greedy Algorithm with a Dynamic Programming solution to handle all edge cases.
4. **Dockerfile and Networking**: Built a Dockerfile for the backend and tested container communication with the frontend.

---

## Final Outcome:
- **Backend Functionality**: The backend API (e.g., `/api/calculate`) works as expected, correctly solving the minimum coin problem using dynamic programming.
- **Docker Deployment**: The backend application is successfully containerized and integrated with the frontend using Docker networking.
- **Resilience**: Dependency and build configurations are stable, ensuring smooth packaging and deployment processes.

This concludes the backend development summary for the Coin Calculator project.
