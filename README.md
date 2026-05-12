# APCSA Final Project

## Project Description

This project is a simple Java habit tracker application that helps students track daily health and productivity habits. It stores habit entries locally in a CSV file so the program can keep data between runs. The app is designed to be easy to use and easy to extend with new habit types.

## What the Project Does

- Tracks daily habits for health and productivity
- Saves habit data locally in `habits.csv`
- Reads saved habits on startup so users can continue tracking progress
- Uses object-oriented design with separate classes for habits, calculations, and file management

## Core Features

- Add new habits and record daily progress
- Load and save habit data using a CSV file
- Organize behavior into classes like `Habit`, `HealthHabit`, `ProductivityHabit`, `HabitCalculator`, and `HabitFileManager`
- Calculate and display habit progress over time

## Files in the Project

- `Habit.java` - base class for habit data
- `HealthHabit.java` - health-focused habit behavior
- `ProductivityHabit.java` - productivity-focused habit behavior
- `HabitCalculator.java` - computes progress and habit summary data
- `HabitFileManager.java` - reads and writes habit data to `habits.csv`
- `HabitTrackerApp.java` - main application entry point
- `HabitTest.java` - unit tests for validating habit behavior
- `DailyEntry.java` - tracks daily entries for habits

## How to Run

1. Open the project in your Java IDE or compile with `javac`.
2. Run `HabitTrackerApp` as the main class.
3. The app will load saved habits from `src/habits.csv` if available.
4. Add or view habits and save changes before closing.

## Why This Project Matters

Keeping data locally lets the app remember habits between sessions without using the cloud. This makes it a good fit for students learning Java, file handling, and simple persistence.
