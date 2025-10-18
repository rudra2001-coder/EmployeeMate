EmployeeMate is an offline-first Android application that helps employees manage their daily work schedules, meal counts, off-days, and shift details.
It also allows them to view colleagues’ information, track attendance, manage holidays, and export their monthly reports—all without an internet connection.
📱 Employee Management App (Offline)

An offline-first employee management app built with Kotlin + Jetpack Compose.
It helps employees manage their daily schedules, work hours, meal tracking, off-days, and colleague interactions — all without internet connectivity.

The app is designed to be clean, modern, and enjoyable to use — blending elegant UI design with productivity-focused features.

🚀 Core Features
Category	Description
🕒 Work Schedule Management	Add, edit, and view your daily or weekly work hours. Mark shifts (morning/evening/night).
🍱 Meal Tracking System	Record daily meal counts. Automatically resets monthly. Shows monthly and weekly meal summary.
📅 Off Days & Holidays	Mark off days, company holidays, or personal leaves.
👥 Colleague Directory	Maintain a contact list of colleagues, their phone numbers, and roles.
🔄 Shift Swapping	Request and record shift swaps with colleagues.
📊 Reports & Analytics	See your total hours worked, meals consumed, and remaining meal balance.
💾 Offline Data Storage	All data is stored locally using Room Database — no internet required.
⏫ Data Export	Export your meal/schedule records as CSV or JSON (for personal records or HR).
⚙️ Settings	Customize meal allowances, reset data, backup/restore locally.
🧭 Modern Compose UI	Jetpack Compose with Material 3 for modern UI and smooth user experience.



🧩 Tech Stack
Component	Technology Used
Language	Kotlin
UI	Jetpack Compose + Material 3
Local Storage	Room Database (SQLite)
Architecture	MVVM
State Management	ViewModel + LiveData
Navigation	Jetpack Compose Navigation
Export/Backup	Kotlin File I/O + CSV Utils
Icons & Fonts	Material Icons Extended
Build System	Gradle (KTS)
⚙️ Dependencies (add to build.gradle)
dependencies {
    // Core
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.2")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.09.02"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.3.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")

    // Icons
    implementation("androidx.compose.material:material-icons-extended")

    // Gson (for JSON export)
    implementation("com.google.code.gson:gson:2.11.0")
}

🧍‍♂️ Models Overview
Model	Description
Employee	Employee name, position, phone, email
Schedule	Date, startTime, endTime, shiftType, remarks
Meal	Date, mealCount, mealType (breakfast/lunch/dinner)
Holiday	Date, title, reason
ShiftSwap	FromEmployeeId, ToEmployeeId, date, status
Settings	MonthlyMealLimit, workHoursPerDay, themePreference
🧠 Key Logic Flow

Local Database (Room) stores all employee, meal, and schedule data.

Compose ViewModels manage UI state, handle insertion, deletion, and updates.

Navigation Component handles screen transitions (Dashboard, Meals, Schedule, etc.).

ExportUtils creates CSV/JSON backups on local storage.

Monthly Reset Logic runs once per month to refresh meal counters.

🖼️ Screen List (UI Plan)
Screen	Purpose
🏠 DashboardScreen	Overview of today’s schedule, meals, and quick stats
⏰ ScheduleScreen	List, add, or edit daily/weekly work schedules
🍴 MealScreen	Track daily meals, view monthly summary
🧑‍🤝‍🧑 ColleaguesScreen	Directory of all employees with contact info
📅 HolidayScreen	View or declare holidays/off-days
🔄 ShiftSwapScreen	Manage or request shift swaps with colleagues
📊 ReportsScreen	Visual reports of hours and meals
⚙️ SettingsScreen	App settings and data export options
💡 Design Language

Theme: Material 3, pastel backgrounds, rounded cards, fluid animations

Typography: Inter or Poppins

Colors: Calm blue/teal primary tones

Animations: Motion fade-in, scale transitions for better user flow

📦 How to Build

Clone or extract the project folder.

Open in Android Studio (2023.3+ recommended).

Sync Gradle → Build → Run on Emulator/Device.

The app runs fully offline.

🔜 Future (Version 2 - Online Sync)
Planned Feature	Description
☁️ Cloud Sync	Real-time sync with Firebase or custom backend
🧾 Admin Dashboard	Web portal for HR or managers
🔔 Notifications	Push alerts for shift changes or holidays
👥 Team Groups	Chat or announcements board
📄 License

MIT License © 2025 Mahmudul Hasan Rudra

