# 🚗 Car Inventory Management System

A robust **Spring Boot** REST API project designed to demonstrate **advanced exception handling**, **DTO patterns**, **Unit Testing (Mockito)**, and **professional architectural standards**. This project manages the inventory of cars and brands with a focus on clean code, maintainability, and test reliability.

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
* **Comprehensive Testing Strategy:**
    * **Unit Testing:** Isolated business logic testing using **Mockito** (`@Mock`, `@InjectMocks`).
    * **Integration Testing:** Data layer and relationship verification using **JUnit 5** and **Spring Boot Test**.

## 🛠️ Tech Stack

* **Java 17+**
* **Spring Boot 3.x**
* **Spring Data JPA** (Hibernate)
* **PostgreSQL** (Database)
* **Mockito** (Mocking Framework for Unit Tests)
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
🧪 Testing Strategy
This project implements a professional Testing Pyramid approach, combining both Unit and Integration tests.

1. Unit Testing (Mockito)
Business logic is tested in isolation without relying on the database or Spring Context. This ensures fast execution and validates logical correctness.

Key Techniques:

@Mock: Simulating Repository dependencies.

@InjectMocks: Injecting mocks into the Service layer.

Verification: Ensuring methods like save() are called correctly.

Java

// Example: Testing 'Add Car' logic without DB connection
@Test
void testAddCar_WhenBrandExists_ShouldSaveAndReturnDtoCar() {
    // 1. Arrange: Mock the behavior of repositories
    when(brandRepository.existsById(1L)).thenReturn(true);
    when(carRepository.save(any(Car.class))).thenReturn(savedCar);

    // 2. Act: Call the service method
    DtoCar result = carService.addCar(dtoCarIU, 1L);

    // 3. Assert & Verify: Check results and interactions
    assertNotNull(result);
    verify(carRepository, times(1)).save(any(Car.class));
}
2. Integration Testing
Ensures data integrity, database constraints, and relationships (OneToMany/ManyToOne) work as expected within the Spring Context.

Java

// Example: Fetching a Brand and ensuring its Car list is retrieved correctly from DB
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
                // "brand": null -> Automatically hidden via @JsonInclude for optimization
            }
        ]
    },
    "message": "successful"
}
⚙️ Setup & Run
Clone the repository:

Bash

git clone [https://github.com/efeerturk7/spring-boot-exception-handling-demo.git](https://github.com/efeerturk7/spring-boot-exception-handling-demo.git)
Configure Database: Update src/main/resources/application.properties with your PostgreSQL credentials.

Run the App:

Bash

mvn spring-boot:run
Developed by Bahadır Efe Ertürk as part of a Spring Boot Bootcamp.
