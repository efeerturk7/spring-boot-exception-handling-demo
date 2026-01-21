Markdown

# 🚗 Car Inventory Management System

A robust **Spring Boot** REST API project designed to demonstrate **advanced exception handling**, **DTO patterns**, and **professional architectural standards**. This project manages the inventory of cars and brands with a focus on clean code and maintainability.

## 🚀 Features

* **RESTful API Development:** Complete CRUD operations for `Car` and `Brand` entities.
* **Advanced Exception Handling:**
    * Global Exception Handler using `@ControllerAdvice`.
    * Custom Exception Classes (e.g., `BrandNotFoundException`, `CarNotFoundException`).
    * Standardized API Error Responses (`ApiError` & `RootEntity` wrappers).
* **Data Transfer Objects (DTO):**
    * Separation of Request (IU) and Response DTOs.
    * **Payload Optimization:** Using `@JsonInclude(JsonInclude.Include.NON_NULL)` to hide null fields (like recursive Brand data) in responses.
* **Validation:** Input validation using Spring Validation (`@Valid`, `@NotNull`, `@Min`, etc.).
* **Integration Testing:**
    * Service layer tests using **JUnit 5** and **Spring Boot Test**.
    * `@Transactional` tests to prevent LazyInitializationException.
    * Verifying business logic and data integrity.

## 🛠️ Tech Stack

* **Java 17+**
* **Spring Boot 3.x**
* **Spring Data JPA** (Hibernate)
* **PostgreSQL** (Database)
* **Lombok** (Boilerplate reduction)
* **JUnit 5** (Testing)
* **Maven** (Build Tool)

## 📂 Project Structure

```text
src/main/java/com/example/exception_handling_bootcamp
├── controller      # REST Controllers (API Endpoints)
├── dto             # Data Transfer Objects (Request/Response)
├── entity          # JPA Entities (Database Tables)
├── exception       # Custom Exceptions & Global Handler
├── repository      # Data Access Layer
└── service         # Business Logic Layer
🧪 Testing
The project includes integration tests to ensure data integrity and relationship management.

Java

// Example Test Scenario: Fetching a Brand and ensuring its Car list is retrieved correctly
@Test
@Transactional
public void testFindBrandById_ShouldReturnCarsInside() {
    DtoBrand result = brandService.findBrandById(1L);
    assertFalse(result.getDtoCarList().isEmpty());
    assertNotNull(result.getDtoCarList().get(0).getId()); 
}
📝 API Response Example
GET /exception_bc/brand/find/1

JSON

{
    "result": true,
    "data": {
        "id": 1,
        "name": "BMW",
        "dtoCarList": [
            {
                "id": 4,
                "model": "M5 CS",
                "price": 668693.5
                // "brand": null -> Automatically hidden via @JsonInclude
            }
        ]
    },
    "message": "successful"
}
⚙️ Setup & Run
Clone the repository:

Bash

git clone [https://github.com/efeerturk7/spring-boot-exception-handling-demo.git](https://github.com/efeerturk7/spring-boot-exception-handling-demo.git)
Configure Database: Update application.properties with your PostgreSQL credentials.

Run the App:

Bash

mvn spring-boot:run
Developed by Bahadır Efe Ertürk as part of a Spring Boot Bootcamp.
