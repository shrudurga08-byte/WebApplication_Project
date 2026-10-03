# Phase 01: Web Application Boilerplate

## A. Phase identity and purpose
* **Phase:** 01
* **Original objective:** Establish a minimal, framework-free Java web application boilerplate.
* **Intended outcome:** A clean separation of frontend (HTML/CSS/JS), backend (Java standard HTTP server), and database (MySQL) layers, built with Gradle, ready to support future authentication features.

## B. Starting state and constraints
* **What was known:** A basic web app is needed, but the exact domain is undecided.
* **Technology choices:** Plain Java 21, `com.sun.net.httpserver.HttpServer`, Vanilla HTML/CSS/JS, MySQL, Gradle.
* **Constraints:** No Spring, Spring Boot, ORMs, or frontend frameworks are allowed.
* **Assumptions:** A local MySQL server will be available on port 3306 for future phases.

## C. Final implementation state
* **Implemented and verified:** 
  * Gradle 8.9 wrapper setup and build configuration.
  * Java HTTP Server listening on port 8080.
  * TDD setup with JUnit 5.
  * Health endpoint (`GET /api/health`) responding with `{"status":"UP"}`.
  * Static file handler (`GET /`) serving frontend assets.
  * Frontend HTML, CSS, and JS files.
* **Placeholders:** 
  * `application.properties` (database config).
  * `database/schema.sql` (initial MySQL schema script).
* **Incomplete/Deferred:** Actual JDBC database connection and user authentication.

## D. Architecture and request flow
1. **Startup:** `com.example.App.main()` initializes the `HttpServer` on port 8080.
2. **Routing:** 
   * `/api/health` routes to `HealthHandler`.
   * `/` routes to `StaticFileHandler`.
3. **Frontend:** `StaticFileHandler` reads files from `src/main/resources/static/` on the classpath and serves them with appropriate MIME types. 
4. **Database:** Currently disconnected; schema and properties exist as structural foundations only.

## E. File-by-file inventory
* `build.gradle` & `settings.gradle`: Configures Java plugin, JUnit 5, and the application entry point.
* `gradle.properties`: Enforces `org.gradle.java.home` to Java 21 to avoid version conflicts.
* `src/main/java/com/example/App.java`: Main server initialization and route registration.
* `src/main/java/com/example/HealthHandler.java`: Returns `{"status":"UP"}` JSON response.
* `src/main/java/com/example/StaticFileHandler.java`: Serves static frontend files from the classpath.
* `src/test/java/com/example/HealthHandlerTest.java`: JUnit test asserting the health endpoint functionality.
* `src/main/resources/static/index.html`: Entry point HTML with a fetch button.
* `src/main/resources/static/css/style.css`: Basic stylesheet.
* `src/main/resources/static/js/app.js`: Connects to backend via `fetch('/api/health')`.
* `src/main/resources/application.properties`: Placeholder for JDBC URL and credentials.
* `database/schema.sql`: Placeholder MySQL `system_config` table script.
* `README.md`: Project documentation.

## F. Build and dependency configuration
* **Java:** 21 (Microsoft HotSpot JDK)
* **Gradle:** 8.9 (Wrapper used)
* **Dependencies:** `org.junit.jupiter:junit-jupiter-api:5.10.0` (testImplementation).
* **Commands:**
  * Build/Test: `./gradlew clean test`
  * Run server: `./gradlew run`

## G. Database state
* **Existing SQL:** Defines a placeholder `system_config` table.
* **JDBC Integration:** Not implemented.
* **Missing:** No users, roles, or authentication schema exists yet. The next phase must define the `users` table.

## H. Verification record
* `./gradlew test`: **Pass** (Successfully compiles and runs `HealthHandlerTest`).
* `./gradlew run`: **Pass** (Server starts on 8080).
* `GET http://localhost:8080/api/health`: **Pass** (Returns 200 OK JSON).
* `GET http://localhost:8080/`: **Pass** (Serves `index.html` after Phase 8 fix).

## I. Problems, failures, and fixes
* **Issue:** Gradle daemon crashed due to Java 26 "Unsupported class file major version 70" error.
  * **Fix:** Installed JDK 21 via `winget` and configured `gradle.properties` to explicitly set `org.gradle.java.home`.
* **Issue:** Source files were incorrectly placed in `src/test/java/com/example/App.java` causing `ClassNotFoundException` during `./gradlew run`.
  * **Fix:** Moved `App.java` and `HealthHandler.java` to `src/main/java/com/example/`.
* **Issue:** Windows file lock (`java.nio.file.AccessDeniedException`) on the `build` directory during testing.
  * **Fix:** Stopped running Gradle/Java processes and renamed the `build` folder to bypass OS-level locks.
* **Issue:** 404 error when navigating to `http://localhost:8080/`.
  * **Fix:** Implemented `StaticFileHandler.java` to serve the frontend files from the root context.

## J. Architectural decisions
* Used standard Java `HttpServer` to strictly adhere to the "no frameworks" constraint.
* Added a `StaticFileHandler` because the built-in HTTP server does not serve static files by default, making the frontend accessible without a separate web server.
* Placed configuration in `application.properties` to ensure credentials are not hardcoded into Java classes in future phases.

## K. Security and operational considerations
* Passwords and database credentials in `application.properties` are placeholders and must be replaced with environment variables in production.
* The server runs locally on port `8080` via HTTP (no TLS/HTTPS configured yet).

## L. Explicitly deferred work
* User registration, login, and logout.
* Password hashing and storage.
* Session management and tokens.
* JDBC database connections.
* Email/phone verification.

## M. Commit and working-tree linkage
* **Git status:** Git is not currently initialized in this directory (`fatal: not a git repository`). No commits exist.

## N. Resume-from-here
The initial boilerplate is verified and complete. 

**Next Planned Task:**
Implement the Registration feature.
* **Fields required:** Full Name, Phone Number, Email Address, Password.
* **Endpoint:** `POST /api/register`
* **Requirements:** Server-side validation, secure password hashing, JDBC-based persistence, unique email/phone constraints, safe error handling, and TDD tests.

**Prerequisites needed before starting:**
1. A running MySQL server instance on port 3306.
2. The MySQL JDBC Driver added to `build.gradle` dependencies.
3. A `users` table defined in `database/schema.sql`.
