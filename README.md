# Nooro Android Development Assessment

This repository contains the Nooro Android Development Assessment project. The app demonstrates clean architecture, modern Android development practices, and adherence to best practices for working with APIs.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Setup](#project-setup)
- [How to Run](#how-to-run)

---

## Project Overview

This project aims to showcase an Android application that fetches and displays weather information. It implements a clean architecture with separation of concerns, leveraging state-of-the-art technologies like Jetpack Compose, Kotlin Coroutines, and StateFlow.

---

## Features

- Fetch weather data from a public API
- Display current weather details
- Update city selection dynamically
- Handle API errors gracefully

---

## Tech Stack

- **Kotlin**
- **Jetpack Compose**: For declarative UI
- **Coroutines**: For asynchronous programming
- **StateFlow**: For state management
- **Retrofit**: For networking
- **MockK**: For testing
- **JUnit**: For unit testing

---

## Project Setup

To run this project locally, you need to add your **BASE_URL** and **API_KEY** to the `local.properties` file.

### Steps to Configure:
1. Open the `local.properties` file (located in the root of the project).
2. Add the following lines:
   ```properties
   BASE_URL="https://api.weatherapi.com/v1" 
   API_KEY=your_api_key_here
   
### Why Store Keys in `local.properties`?

Storing sensitive data like API keys or Base URLs in the `local.properties` file keeps them out of your source code. This practice:
- Enhances security by preventing accidental exposure of keys in version control (e.g., GitHub).
- Makes it easy to configure different environments (development, staging, production).
- Ensures modularity and portability of the codebase.

## How to run
1. Clone the repository:

```bash
git clone https://github.com/your-username/nooro-assessment.git
cd nooro-assessment
```
2. Open the project in Android Studio.
3. Add your BASE_URL and API_KEY to local.properties as explained above.

### Build and run the project:

Use the Run button in Android Studio, or Execute the following command:
```bash
./gradlew assembleDebug
```

## Testing

The project includes unit tests to ensure the application behaves as expected. The testing strategy demonstrates a mix of mocking and creating fake objects to showcase testing skills:

- **MockK**: Used to create mocks for dependencies like `WeatherRepository` to simulate API responses.
- **Fake Object**: A custom `FakeSimpleStorage` was implemented to mimic the behavior of `SimpleStorage` without relying on a mocked implementation. This demonstrates the ability to use fakes effectively for testing scenarios where stateful behavior is important.

### Running Tests

1. Use Android Studio's built-in test runner, or
2. Execute the following command:
   ```bash
   ./gradlew testDebugUnitTest