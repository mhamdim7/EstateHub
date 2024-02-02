# EstateHub

- [Tech Stack](#description)
- [UX](#ux)
- [Running Unit Tests](#runningUnitTests)

## Tech Stack

This Android app utilizes Dagger Hilt for dependency injection, Jetpack Compose for UI, Coroutines for asynchronous tasks, Navigation Component for app navigation, Retrofit for API requests, Mockk for Unit tests and follows MVVM with Clean Architecture principles.

## UX

The app is designed with a very basic interface, supports Dark and Light modes and adapting to Android theme's color palettes. It incorporates error handling for various situations and loading states to indicate ongoing operations.

## Running Unit Tests

Tests can be run either
- From the command line using Gradle:
  ```bash
  ./gradlew test
- From android studio
