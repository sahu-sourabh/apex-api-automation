# Apex API Automation Framework

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Maven-3.6+-blue?style=for-the-badge&logo=apache-maven)
![TestNG](https://img.shields.io/badge/TestNG-7.10-red?style=for-the-badge&logo=testng)

An enterprise-grade API Automation Testing Framework designed with clean architecture principles, utilizing Java, REST Assured, and TestNG. This repository serves as a professional-grade portfolio asset demonstrating modular test design patterns.

---

## 📌 Project Status
**Current Phase:** `Phase 1: Core Architecture Completed`  
**Status:** 🟡 **In Active Development / Progress**

---

## 🚀 Key Achievements & Current Implementations

* **Environment Separation:** Implemented a dynamic `.properties` configuration management system to quickly switch target base environments without altering underlying Java code.
* **Global Configuration Hub:** Developed a base engine (`BaseTest`) utilizing TestNG lifecycle hooks (`@BeforeSuite`) to universally apply global Request Specifications (e.g., Default Content-Types, Request Headers).
* **Robust Core Routing:** Configured structural package-level separation across test layers (`base`, `utils`, `tests`) ensuring strict adherence to the **Single Responsibility Principle (SRP)**.
* **Clean Version Control:** Maintained a clean, production-ready commit history, tracking only operational code assets via advanced `.gitignore` sanitization blocks.

---

## 🗺️ Future Roadmap & Upcoming Phases

### Phase 2: Advanced Data Serialization & Payload Management
* [ ] Integrate **Jackson Databind** engines to completely replace brittle hardcoded JSON request body strings.
* [ ] Design decoupled **POJOs (Plain Old Java Objects)** mapping data blueprints inside an isolated `models` layer.

### Phase 3: Commercial-Grade Reporting & Logging
* [ ] Integrate **ExtentReports** or **Allure** to auto-generate beautiful HTML dashboards visualizing historical execution runs.
* [ ] Implement advanced runtime filters utilizing REST Assured custom validation log filters.

### Phase 4: CI/CD Integration & Infrastructure Verification
* [ ] Author local system setup instructions for global system environment runtime paths.
* [ ] Build a robust **GitHub Actions Workflow** (`.github/workflows/`) pipeline to trigger full regression suites automatically upon every repository code commit or pull request.

---

## 🛠️ Local Setup & Prerequisites

* **JDK Target:** Java 25
* **Build Engine:** Maven 3.6+
* **IDE Recommend:** Visual Studio Code (with *Extension Pack for Java*)

To execute tests via the internal VS Code test runner framework, open this root directory as a standalone project workspace folder, wait for Maven dependencies to complete indexing, and tap the test play triggers embedded in the `HttpBinTests.java` file.