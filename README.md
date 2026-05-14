# ExpenseViewerApp

ExpenseViewerApp is a modern Android application designed to help users track and manage their daily expenses. Built using the latest Android development tools and best practices, it provides a clean and intuitive interface for monitoring spending habits.

## 📱 Screenshots

<p align="center">
  <img width="300" alt="Screenshot_20260514_235932" src="https://github.com/user-attachments/assets/bd93f3ae-21d9-4110-8570-9b355c0fbcce" />
  <img width="300" alt="Screenshot_20260515_000001" src="https://github.com/user-attachments/assets/5fade961-1adf-43ec-82bf-8b5efb17dd0f" />
</p>

## ✨ Features

- **Expense List**: View all your expenses in a clean, categorized list.
- **Detailed Tracking**: Record expense title, amount, and date.
- **Offline Support**: Access your data even without an internet connection, thanks to local database caching.
- **Clean UI**: Built with Jetpack Compose for a smooth and responsive user experience.
- **Modern Architecture**: Follows Clean Architecture principles for better maintainability and testability.

## 🛠 Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Local Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/)
- **Asynchronous Programming**: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
- **Architecture**: MVVM (Model-View-ViewModel) + Clean Architecture
- **JSON Parsing**: [Gson](https://github.com/google/gson)

## 🏗 Project Structure

The project follows Clean Architecture patterns:
- **`data/`**: Implementation of repositories, local (Room) and remote (Retrofit) data sources, and data mappers.
- **`domain/`**: Business logic, including use cases, repository interfaces, and domain models (`Expense`).
- **`presentation/`**: UI layer containing Compose screens, ViewModels, and UI state management.
- **`di/`**: Hilt modules for dependency injection.
- **`util/`**: Common utility classes and helpers.

## 🚀 Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/ExpenseViewerApp.git
   ```
2. **Open in Android Studio**:
   Open Android Studio (Ladybug or newer recommended) and select "Open" then navigate to the cloned folder.
3. **Sync Gradle**:
   Wait for the project to sync and download all dependencies.
4. **Run the App**:
   Connect an Android device or start an emulator and click the "Run" button.

## 🧪 Testing

The project includes:
- **Unit Tests**: Using JUnit and MockK to test ViewModels and Use Cases.
- **UI Tests**: Using Compose Test library for UI component verification.
