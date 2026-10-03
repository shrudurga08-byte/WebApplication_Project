# Practice Web Application

A clean, framework-less Java boilerplate that separates frontend, backend, and database responsibilities.

## 📁 Architecture
- **Frontend**: Plain HTML, CSS, and Vanilla JS (`src/main/resources/static/`)
- **Backend**: Plain Java HTTP Server (`src/main/java/com/example/`)
- **Database**: MySQL initialization scripts (`database/`)
- **Config**: Application properties (`src/main/resources/application.properties`)

## ⚙️ Prerequisites
- **Java**: 21 (Configured in `gradle.properties`)
- **Gradle**: Controlled via Gradle Wrapper (`gradlew`)
- **MySQL**: (Setup deferred to future development)

## 🚀 How to Build & Test
We use Test-Driven Development (TDD) with JUnit 5. To run the tests:
