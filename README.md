# Apex API Automation Framework (Core Architecture Blueprint)

[![API Regression Suite](https://github.com/sahu-sourabh/apex-api-automation/actions/workflows/maven.yml/badge.svg)](https://github.com/sahu-sourabh/apex-api-automation/actions/workflows/maven.yml)

An enterprise-grade, lightweight REST API automation engine engineered with Java, REST Assured, and TestNG. This framework is architected as a production-ready blueprint, establishing a thread-safe, decoupled boilerplate foundation engineered to scale across extensive enterprise microservice ecosystems.

---

## 🏛️ Architectural Strategy & Structural Design

This repository serves as a highly scalable operational engine rather than a generic automation script dump. The core framework centers entirely on a pristine, zero-leak end-to-end entity lifecycle, implementing explicit object data isolation patterns.

### Core Engineering Implementations:
* **Strict Type-Safety & Decoupled Models:** Leverages the Jackson Data Bind library to handle explicit POJO Serialization and Deserialization across isolated `models/user` and `models/article` namespaces, utilizing defensive `@JsonIgnoreProperties` protocols to guarantee pipeline immunity against unexpected downstream API payload upgrades.
* **Cascading Environment Secret Routing Engine:** Implements an enterprise configuration lookup abstraction inside `ConfigReader`. It prioritizes high-security cloud system environment variables (e.g., CI/CD GitHub Secrets) before gracefully falling back to localized `.properties` targets.
* **Dynamic Datasets via Runtime Randomization:** Integrates `Datafaker` to programmatically inject unique data profiles (usernames, email addresses) at runtime, preventing database collision state overlaps (`422 Unprocessable Entity`) and paving a clean path for frictionless parallel suite scaling.
* **Thread-Safe HTTP Client Encapsulation:** Eradicates static RestAssured global instance mutations by binding operational contexts directly to granular client engines (`executePost`, `executePut`), isolating network states from cross-contamination risks.
* **Stateful E2E Lifecycle Testing:** Validates a continuous HTTP CRUD flow (POST → GET → PUT → DELETE) by cascading dynamic server-generated metrics (slugs, tokens) across components using sequential TestNG dependency gates and a failsafe `@AfterClass` teardown cleanup routine.

---

## 🛠️ Technology Stack

* **Language:** Java 25 (JDK)
* **API Client:** REST Assured (v5.5.0)
* **Test Engine:** TestNG (v7.10.2)
* **JSON Parser:** Jackson Databind (v2.18.0)
* **Data Generator:** Net Datafaker (v2.2.2)
* **Build Tool:** Apache Maven
* **CI/CD Platform:** GitHub Actions

---

## 📁 Repository Structure

```text
├── .github/workflows/
│   └── maven.yml          # GitHub Actions Cloud CI Pipeline Engine
├── src/
│   └── test/
│       ├── java/com/apex/api/
│       │   ├── base/
│       │   │   └── BaseTest.java        # Thread-Safe HTTP Engine & Specs
│       │   ├── models/
│       │   │   ├── article/             # Encapsulated Article Schema Models
│       │   │   │   ├── PostArticleRequest.java
│       │   │   │   ├── PostArticleResponse.java
│       │   │   │   ├── RequestArticle.java
│       │   │   │   ├── ResponseArticle.java
│       │   │   │   └── ResponseAuthor.java
│       │   │   └── user/                # Encapsulated User Authentication Models
│       │   │       ├── PostUserRequest.java
│       │   │       ├── PostUserResponse.java
│       │   │       ├── RequestUser.java
│       │   │       └── ResponseUser.java
│       │   ├── tests/
│       │   │   └── ArticleCrudE2ETests.java # Dynamic E2E Validation Suite
│       │   └── utils/
│       │       ├── ConfigReader.java    # Cascading System Variable Processor
│       │       ├── TestData.java        # Immutable HTTP Protocol Constants
│       │       └── TestListener.java    # Intercepting Terminal Event Logger
│       └── resources/
│           └── config.properties        # Global Environment Settings
├── .gitignore             # Rigid IDE & Local Environment Exclusion Map
└── pom.xml                # Project Object Model Dependency Framework
```
## 🚀 Execution & Continuous Integration
**Local Execution**

To wipe local workspace compilation caches, download dependencies, and execute the complete regression suite locally via the terminal, use:

```Bash
mvn clean test
```
All HTML execution reports, TestNG output summaries, and surefire logs generate dynamically into the local `target/surefire-reports/` path.

**Cloud CI/CD Execution Pipeline**

This framework features an enterprise-hardened Continuous Integration workflow. Every codebase changes matching a `push` or a `pull_request` sequence targeting the `main` branch automatically provisions an isolated Linux runner container inside GitHub Actions.

The automated pipeline sequence handles:

**Container Initialization:** Provisions an elastic virtual engine (ubuntu-latest).

**Runtime Configuration Setup:** Builds out a clean Temurin JDK 25 instance complete with native Maven dependency caching configurations.

**Secure Variable Injection:** Securely bridges operational keys into the environment mapping blocks using native GitHub Repository Secrets (secrets.API_TEST_PASSWORD).

**Automated Testing & Artifact Preservation:** Packages dependencies, runs the entire regression suite, and extracts both the TestNG artifact grids and surefire logs out to the cloud platform interface dashboard before runtime resources are swept away.

---