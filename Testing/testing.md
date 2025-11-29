

## **Name: spring-testing-lab**

A project whose code exists ONLY to teach and demonstrate:

* Unit tests (Mockito)
* Parameterized tests
* Service-layer testing
* Controller tests (`@WebMvcTest`)
* Repository tests (`@DataJpaTest`)
* Integration tests (`@SpringBootTest`)
* Testcontainers (PostgreSQL)
* MockMvc
* JSONPath assertions
* Exception testing
* Slice tests (MVC, JPA, WebFlux optional)
* Embedded Kafka testing (optional advanced module)
* Coverage measurement (Jacoco)

This one repo will be your **testing playground + reference book**.

---

# 🌳 **Project Domain (simple + realistic): “Library Management System”**

Not LeetCode-level, not toy-level — just right.

### Entities:

* **Book** (id, title, author, status, createdAt)
* **Member** (id, name, email)
* **Loan** (id, memberId, bookId, issuedAt, returnedAt)

### Use cases:

* Add book
* Borrow book
* Return book
* List available books
* Get member summary

This domain gives us enough logic to write powerful tests without building a heavy app.

---

# 🏗 **Project Structure**

```
spring-testing-lab/
│
├── src/main/java/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
│
├── src/test/java/
│   ├── unit/
│   │    ├── service/
│   │    └── util/
│   │
│   ├── slices/
│   │    ├── controller/
│   │    └── repository/
│   │
│   ├── integration/
│   │    ├── api/
│   │    └── db/
│   │
│   └── testcontainers/
│        ├── postgres/
│        └── kafka/ (optional advanced)
│
└── README.md (your documentation)
```

This structure mimics **real enterprise projects**.

---

# 🔥 **What You Will Practice in This Project**

## **1. UNIT TESTING (Mockito)**

You will write unit tests for:

* BookService
* MemberService
* LoanService
* Utility classes

Using:

* `@ExtendWith(MockitoExtension.class)`
* `@Mock`, `@InjectMocks`
* `when()`, `verify()`, `ArgumentCaptor`
* `assertThrows()`, `assertEquals()`
* **Parameterized tests**

  * `@ValueSource`
  * `@CsvSource`
  * `@MethodSource`

Examples you will learn:

* Testing branch logic
* Testing mapping logic
* Testing exceptions
* Testing post-conditions
* Mocking nested calls

---

## **2. SLICE TESTING**

### **@WebMvcTest**

* Test only controllers
* Mock service layer
* Test JSON responses
* Validate request bodies
* Validate error responses

### **@DataJpaTest**

* Test repositories
* Use H2
* Validate queries
* Validate relationships

---

## **3. INTEGRATION TESTING**

### **@SpringBootTest**

* Testing real workflow end-to-end:

  * Add book → Borrow book → Return book
* Using real services + controllers
* No mocks

### **MockMvc + SpringBootTest**

* Full controller integration

---

## **4. TESTCONTAINERS**

### PostgreSQL Testcontainer

* Real DB in Docker
* Spring Boot auto-wired
* Run repository + service tests
* This teaches you REAL backend testing

You will learn:

* Container lifecycle
* Dynamic properties
* Schema auto-generation

---

## **5. ADVANCED TESTING (Optional Modules)**

### Embedded Kafka (optional)

To learn event-driven testing.

### Wiremock

To mock external APIs.

---

# 📘 **README Structure**

Your repo should include:

## 1. **Testing Fundamentals**

* What is testing?
* Types of tests
* Differences between unit vs integration

## 2. **Unit Testing**

* Examples + explanation

## 3. **Mockito Deep-Dive**

* Stubbing
* Verification
* ArgumentCaptor
* Exception mocking
* Parameterized tests

## 4. **Controller testing (MockMvc)**

## 5. **Repository testing**

## 6. **Integration testing**

## 7. **Testcontainers**

## 8. **Common testing mistakes**

