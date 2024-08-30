<h1 align="center">Netflix Clone</h1>

<h3 align="center">
Netflix App Clone using modern Android development with Hilt, Coroutines, Jetpack (Room, ViewModel), and Jetpack Compose based on MVVM architecture.
</h3>

<div align="center">
<img src="/previews/home.png" width="300"/>

<img src="/previews/detail.png" width="300"/>

<img src="/previews/video.png" width="300"/>
</div>


## Tech stack
- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose) Android’s modern toolkit for building native UI. (The app was built using JetpackCompose and XML for create interfaces to give a demonstration of how we can inject new way to create interfaces with old way to create interfaces).
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection.
- Jetpack
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Observe Android lifecycles and handle UI states upon the lifecycle changes.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
    - [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) - Handling and manage the navigation in the app.
    - [Room Persistence](https://developer.android.com/jetpack/androidx/releases/room) - Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) - A modern JSON library for Kotlin.
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - state-holder observable flow that emits the current and new state updates to its collectors.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - for asynchronous calls.
- [Coil](https://coil-kt.github.io/coil/) - for loading images from network.
- [MockK](https://mockk.io/) - Library for unit testing based in kotlin.

<br />

## Architecture
**Netflix Clone** is based on the MVVM architecture and the Repository pattern and it has the approach in Clean Architecture where we can manage different layers.

The project follows a modular and layered architecture, specifically the MVVM (Model-View-ViewModel) pattern and Clean Architecture principles. Here is an explanation of the package structure:  

- **/core** This module contains the core modules that are shared across the application.  
    - **/ui:** Contains UI-related components that are reusable across different features.
    - **/data:** Manages data sources such as network and cache. It includes:
        - **/local:** Handles local data storage, such as Room database entities and DAOs.
        - **/remote:** Manages remote data sources, such as API services. 
    - **/domain:** Contains business logic, models and use cases. It acts as an intermediary between the data layer and the presentation layer.
   
- **/features** This directory contains the feature-specific modules, each representing a distinct feature of the application.  
   - **/feature_name:** Each feature module includes:
     - **/data:** Consume data from the core data module and use adapter pattern to adapt data to the ui needs.
     - **/domain:** Contains the business logic and use cases specific to the feature.
     - **/ui:** Contains the UI components specific to the feature.
     
<br />

## How do I run the app?
- Clone the repository
- Open it in Android Studio
- Wait until dependencies are installed
- Run app in your emulator or physical device
