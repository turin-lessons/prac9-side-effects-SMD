# SideEffectsLab - Jetpack Compose Side-effects Practice

This project is a practical laboratory work ("Practice 9: Side-effects") demonstrating the usage of various Jetpack Compose side-effect APIs and related concepts.

## Project Structure & Effect APIs Used

Each task is implemented as a separate screen to explore how and when different Compose side-effect handlers behave:

* **Task 1: Splash Screen (`Task1Screen.kt`)**
  * **API Chosen:** `LaunchedEffect` & `rememberUpdatedState`
  * **Why:** Used to perform a 2-second delay effect upon entering the composition. `rememberUpdatedState` is utilized to ensure that a rapidly changing parent callback (version counter) is correctly captured without restarting the coroutine effect.

* **Task 2: Snackbar Button (`Task2Screen.kt`)**
  * **API Chosen:** `rememberCoroutineScope`
  * **Why:** LaunchedEffect cannot be called directly from user event callbacks like `onClick`. `rememberCoroutineScope` provides a coroutine scope tied to the composition's lifecycle, allowing us to safely trigger suspend functions like `showSnackbar` from UI interactions.

* **Task 3: Lifecycle Logger (`Task3Screen.kt`)**
  * **API Chosen:** `DisposableEffect`
  * **Why:** Ideal for registering and unregistering external lifecycle observers (`LifecycleEventObserver`). The `onDispose` block guarantees that observers are properly cleaned up when the composable leaves the composition, preventing memory leaks.

* **Task 4: SideEffect & Analytics (`Task4Screen.kt`)**
  * **API Chosen:** `SideEffect`
  * **Why:** Used to publish non-Compose state (passing `userType` to an external `MockAnalytics` object) after every successful recomposition.

* **Task 5: Scroll: derivedStateOf & snapshotFlow (`Task5Screen.kt`)**
  * **API Chosen:** `derivedStateOf` & `snapshotFlow`
  * **Why:** 
    * `derivedStateOf` is used to optimize performance by reducing unnecessary recompositions when calculating whether the list has scrolled past the first item.
    * `snapshotFlow` converts Compose state changes into a cold Flow, allowing standard flow operators (`map`, `distinctUntilChanged`, `filter`) to cleanly trigger analytics events.

* **Task 6 (Bonus): produceState (`Task6Screen.kt`)**
  * **API Chosen:** `produceState`
  * **Why:** Converts a suspend-based network loading function into a standard Compose `State<ImageState>` (handling Loading, Success, and Error states cleanly).

## Submission Details
* Developed in Android Studio using Jetpack Compose.
* Implemented with structured separate commits for each task.
