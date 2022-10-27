<h1 align="center">JetStore</h1>

<p align="center">  
JetStore is a Jetpack Compose demo with modern Android development using Hilt, Coroutines, Flow, Jetpack (Compose, ViewModel), and Material Design based on MVVM architecture.
</p>
</br>

<p align="center">
<img src="/images/cover.png"/>
</p>

## 📲 Download APK
Download the APK from this [Link](https://drive.google.com/file/d/1jNc50vYWa9fhTDpcxMdw3SQgyWvGJCVu/view?usp=sharing).

## 🛠 Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack:
  - [Compose](https://developer.android.com/jetpack/compose): Android’s modern toolkit for building native UI.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): UI related data holder and lifecycle aware.
  - [App Startup](https://developer.android.com/topic/libraries/app-startup): Provides a straightforward, performant way to initialize components at application startup.
  - [Hilt Navigation](https://developer.android.com/jetpack/compose/libraries#hilt-navigation): For navigating screens and injecting dependencies.
  - [Hilt](https://dagger.dev/hilt/): for dependency injection.
- [Material-Components](https://github.com/material-components/material-components-android): Material design components for building ripple animation, and CardView.
- [Accompanist](https://github.com/google/accompanist): A group of libraries that supplement [Jetpack Compose](https://developer.android.com/jetpack/compose) with features that are commonly required by developers but not yet available..
- [WhatIf](https://github.com/skydoves/whatif): Check nullable objects and empty collections more fluently.
- [Timber](https://github.com/JakeWharton/timber): A logger with a small, extensible API.

## Architecture
**JetStore** is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

<img src="images/figure1.png" alt="drawing" width="500"/>

The overall architecture of **JetStore** is composed of two layers; the UI layer and the data layer. Each layer has dedicated components and they have each different responsibilities, as defined below:

**JetStore** was built with [Guide to app architecture](https://developer.android.com/topic/architecture), so it would be a great sample to show how the architecture works in real-world projects.


### Architecture Overview


<img src="images/figure4.png" alt="drawing" width="500"/>

- Each layer follows [unidirectional event/data flow](https://developer.android.com/topic/architecture/ui-layer#udf); the UI layer emits user events to the data layer, and the data layer exposes data as a stream to other layers.
- The data layer is designed to work independently from other layers and must be pure, which means it doesn't have any dependencies on the other layers.

With this loosely coupled architecture, you can increase the reusability of components and scalability of your app.

### UI Layer

<img src="images/figure2.png" alt="drawing" width="500"/>

The UI layer consists of UI elements to configure screens that could interact with users and [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that holds app states and restores data when configuration changes.

### Data Layer

<img src="images/figure3.png" alt="drawing" width="500"/>

The data Layer consists of repositories, which include business logic, it should query data from the local database and requesting remote data from the network, but it now has fake data without using local or remote data sources. It is implemented following the [single source of truth](https://en.wikipedia.org/wiki/Single_source_of_truth) principle.<br>