# News App Demo

This repository contains a demonstration of a News App developed using Kotlin. The app showcases various modern Android development practices and libraries.

## Features

- Modern Android app development using Kotlin.
- Clean Architecture with MVVM pattern.
- Dependency Injection using Hilt.
- Network requests using Retrofit.
- Pagination using Paging 3 library.
- Asynchronous programming using Coroutines and Flow.


## Architecture Overview

The app follows a Clean Architecture approach, separating layers into:

- **Presentation Layer (UI)**: Contains ViewModels, Activities/Fragments, and UI-related code.
- **Domain Layer**: Contains Use Cases and domain models.
- **Data Layer**: Manages data sources, repositories, and networking.

## Libraries Used

- Kotlin: A modern programming language for Android development.
- Hilt: Dependency Injection library for managing app-level and activity/fragment-level dependencies.
- Retrofit: A type-safe HTTP client for making network requests.
- Paging 3: Library for efficiently loading and displaying large data sets.
- Coroutines: For handling asynchronous tasks and concurrency.
- Flow: For reactive programming and asynchronous data streams.
- LiveData: For observing data changes in the UI.
- Navigation Component: For handling navigation between fragments and activities.

## Setup and Installation

1. Clone this repository: `git clone https://github.com/your-username/news-app-demo.git`
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.
