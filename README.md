# Apex API Automation Framework (Core Architecture MVP)

[![API Regression Suite](https://github.com/sahu-sourabh/apex-api-automation/actions/workflows/maven.yml/badge.svg)](https://github.com/sahu-sourabh/apex-api-automation/actions/workflows/maven.yml)

An enterprise-grade, lightweight REST API automation blueprint engineered with Java, REST Assured, and TestNG. This framework is architected as a production-ready MVP, establishing a highly scalable, single-threaded boilerplate foundation that can be instantly expanded to fit complex microservice ecosystems.

---

## 🏛️ Architectural Strategy & MVP Focus

This repository serves as a functional architectural blueprint rather than an exhaustive test-case dump. The framework focuses entirely on a pristine, zero-leak end-to-end user state lifecycle. 

By avoiding bloated, third-party reporting frameworks and heavy threading layers out of the box, this boilerplate maximizes execution speed and limits runtime complexity, delivering a deterministic testing environment.

### Core Engineering Implementations:
* **Strict Type-Safety & Data Separation:** Leveraging the Jackson Data Bind library to handle explicit POJO Serialization (`PostRequest`) and Deserialization (`PostResponse`), eliminating hardcoded JSON text blobs.
* **Zero Hardcoding Data Isolation Strategy:** Completely externalized environmental constants (endpoints, headers) into configuration properties and decoupled functional test data/assertion messaging into a dedicated `TestData` utility layer.
* **Stateful E2E Lifecycle Testing:** Validates a continuous HTTP CRUD flow (POST → GET → PUT → DELETE) by dynamically cascading server-generated runtime entities across tests via TestNG execution dependencies.
* **Externalized Configuration Management:** Completely decouples environment properties (URLs, timeouts) from the core testing logic via a clean `ConfigReader` engine.
* **Decoupled Aspect Logging:** Integrates a global `ITestListener` to intercept test run states at runtime, injecting professional terminal execution markers without polluting functional test files.

---

## 🛠️ Technology Stack

* **Language:** Java 25 (JDK)
* **API Client:** REST Assured (v5.x)
* **Test Engine:** TestNG
* **JSON Parser:** Jackson Data Bind
* **Build Tool:** Apache Maven
* **CI/CD Platform:** GitHub Actions

---

## 📁 Repository Structure

```text
├── .github/workflows/
│   └── maven.yml          # GitHub Actions Cloud CI Pipeline
├── src/
│   └── test/
│       ├── java/com/apex/api/
│       │   ├── base/
│       │   │   └── BaseTest.java        # Global specifications & hooks
│       │   ├── models/
│       │   │   ├── PostRequest.java     # Serialization POJO Model
│       │   │   └── PostResponse.java    # Deserialization POJO Model
│       │   ├── tests/
│       │   │   └── PostCrudE2ETests.java # Dynamic E2E Validation Engine
│       │   └── utils/
│       │       ├── ConfigReader.java    # Safe Properties Processor
│       │       └── TestListener.java    # Intercepting Terminal Logger
│       └── resources/
│           └── config.properties        # Environment Global Settings
└── pom.xml                              # Maven Project Object Model
```

## 🚀 Execution & Continuous Integration
### Local Execution
To clean your project workspace, compile the classes, and execute the complete regression suite locally via the terminal, run:
- mvn clean test

All HTML execution matrices and XML configurations will be generated directly into the standard `target/surefire-reports/` path.

### Cloud CI/CD Pipeline
This framework features native Continuous Integration. Every repository `push` or `pull_request` targeting the `main` branch automatically triggers a headless Linux container inside GitHub Actions.

The pipeline handles:

1. Virtual OS Provisioning (`ubuntu-latest`)
2. Java Development Kit setup (`Temurin JDK 25`)
3. Automated Maven Dependency compilation and Test execution
4. Automated Build Artifact packaging, extracting and preserving the HTML testing reports directly into the GitHub build dashboard summary.

---