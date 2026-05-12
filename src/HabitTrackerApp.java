import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Main application for habit tracking
public class HabitTrackerApp {
    private static final String SAVE_FILE_PATH = "habits.csv";
    private static Scanner sessionScanner = new Scanner(System.in);
    private static ArrayList<Habit> habitRegistry;
    private static boolean isRunning = true;

    public static void main(String[] args) throws IOException {
        System.out.println("========== HABIT AUTOMATICITY TRACKER ==========");
        habitRegistry = HabitFileManager.loadAllHabits(SAVE_FILE_PATH);
        while (isRunning) {
            displayMenu();
            int menuChoice = readMenuChoice();
            routeMenuChoice(menuChoice);
        }
    }

    // Displays main menu
    private static void displayMenu() {
        System.out.println();
        System.out.println("  1. Add a new habit");
        System.out.println("  2. Log today's entry");
        System.out.println("  3. View habit strength report");
        System.out.println("  4. View weekly heatmap");
        System.out.println("  5. Save & quit");
        System.out.print("Enter choice (1-5): ");
    }

    // Reads menu choice
    private static int readMenuChoice() {
        while (!sessionScanner.hasNextInt()) {
            System.out.println("Not a number. Try again:");
            sessionScanner.next();
        }
        int choice = sessionScanner.nextInt();
        sessionScanner.nextLine();
        return choice;
    }

    // Routes to selected option
    private static void routeMenuChoice(int menuChoice) throws IOException {
        switch (menuChoice) {
            case 1:
                addNewHabit();
                break;
            case 2:
                logTodayEntry();
                break;
            case 3:
                showStrengthReport();
                break;
            case 4:
                showWeeklyHeatmap();
                break;
            case 5:
                saveAndQuit();
                break;
            default:
                System.out.println("Choice must be 1-5.");
                break;
        }
    }

    // Adds a new habit
    private static void addNewHabit() {
        System.out.print("Habit title: ");
        String habitTitle = sessionScanner.nextLine();
        System.out.print("Creation date (YYYY-MM-DD): ");
        String creationDate = sessionScanner.nextLine();
        System.out.print("Target day count: ");
        int goalDayCount = readIntegerInRange(1, 365);
        System.out.print("Category (1=Health, 2=Productivity): ");
        int categoryChoice = readIntegerInRange(1, 2);
        if (categoryChoice == 1) {
            System.out.print("Health type (exercise, nutrition, sleep): ");
            String healthType = sessionScanner.nextLine();
            System.out.print("Target minutes per day: ");
            int targetMinutes = readIntegerInRange(1, 1440);
            habitRegistry.add(new HealthHabit(habitTitle, creationDate, goalDayCount, healthType, targetMinutes));
        } else {
            System.out.print("Work category (study, deep work, reading): ");
            String workCategory = sessionScanner.nextLine();
            System.out.print("Requires focus mode? (true/false): ");
            boolean requiresFocusMode = readBoolean();
            habitRegistry.add(new ProductivityHabit(habitTitle, creationDate, goalDayCount, workCategory, requiresFocusMode));
        }
    }

    // Logs an entry for today
    private static void logTodayEntry() {
        if (habitRegistry.isEmpty()) {
            System.out.println("No habits available. Add one first.");
            return;
        }
        for (int i = 0; i < habitRegistry.size(); i++) {
            System.out.println((i + 1) + ". " + habitRegistry.get(i).getHabitTitle());
        }
        System.out.print("Choose a habit number: ");
        int habitIndex = readIntegerInRange(1, habitRegistry.size()) - 1;
        Habit selectedHabit = habitRegistry.get(habitIndex);
        System.out.print("Entry date (YYYY-MM-DD): ");
        String entryDate = sessionScanner.nextLine();
        System.out.print("Completed today? (y/n): ");
        boolean completionFlag = readYesNo();
        System.out.print("Difficulty (1-5): ");
        int difficulty = readIntegerInRange(1, 5);
        System.out.print("Optional note (blank for none): ");
        String noteText = sessionScanner.nextLine();
        DailyEntry entry;
        if (noteText.isEmpty()) {
            entry = new DailyEntry(entryDate, completionFlag, difficulty);
        } else {
            entry = new DailyEntry(entryDate, completionFlag, difficulty, noteText);
        }
        selectedHabit.recordEntry(entry);
    }

    // Shows strength report
    private static void showStrengthReport() {
        if (habitRegistry.isEmpty()) {
            System.out.println("No habits to report.");
            return;
        }
        for (Habit habit : habitRegistry) {
            double score = HabitCalculator.calculateAutomaticity(habit.getEntryLedger(), habit.getGoalDayCount());
            int streak = HabitCalculator.computeCurrentStreak(habit.getEntryLedger(), 0);
            String grade = HabitCalculator.gradeHabitStrength(score);
            System.out.println(habit.getHabitTitle() + " [" + habit.getCategoryLabel() + "]");
            System.out.println("  Automaticity: " + score + "%");
            System.out.println("  Current streak: " + streak);
            System.out.println("  Strength: " + grade);
        }
    }

    // Shows weekly heatmap
    private static void showWeeklyHeatmap() {
        if (habitRegistry.isEmpty()) {
            System.out.println("No habits to show.");
            return;
        }
        for (Habit habit : habitRegistry) {
            ArrayList<DailyEntry> ledger = habit.getEntryLedger();
            int weekCount = Math.max(1, (ledger.size() + 6) / 7);
            int[][] grid = HabitCalculator.buildWeeklyGrid(ledger, weekCount);
            System.out.println(habit.getHabitTitle() + " [" + habit.getCategoryLabel() + "]");
            for (int weekIndex = 0; weekIndex < grid.length; weekIndex++) {
                StringBuilder row = new StringBuilder("Week " + (weekIndex + 1) + ": ");
                for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
                    int value = grid[weekIndex][dayIndex];
                    if (value == 1) {
                        row.append("[✓]");
                    } else if (value == 0) {
                        row.append("[ ]");
                    } else {
                        row.append("[.]");
                    }
                }
                System.out.println(row.toString());
            }
        }
    }

    // Saves and quits
    private static void saveAndQuit() throws IOException {
        HabitFileManager.saveAllHabits(habitRegistry, SAVE_FILE_PATH);
        isRunning = false;
    }

    // Reads integer in range
    private static int readIntegerInRange(int min, int max) {
        while (!sessionScanner.hasNextInt()) {
            System.out.println("Not a number. Try again:");
            sessionScanner.next();
        }
        int value = sessionScanner.nextInt();
        sessionScanner.nextLine();
        if (value < min || value > max) {
            System.out.println("Value must be between " + min + " and " + max + ".");
            return readIntegerInRange(min, max);
        }
        return value;
    }

    // Reads yes/no
    private static boolean readYesNo() {
        String input = sessionScanner.nextLine().trim().toLowerCase();
        if (input.equals("y") || input.equals("yes")) {
            return true;
        }
        if (input.equals("n") || input.equals("no")) {
            return false;
        }
        System.out.print("Please enter y or n: ");
        return readYesNo();
    }

    // Reads boolean
    private static boolean readBoolean() {
        String input = sessionScanner.nextLine().trim().toLowerCase();
        if (input.equals("true")) {
            return true;
        }
        if (input.equals("false")) {
            return false;
        }
        System.out.print("Enter true or false: ");
        return readBoolean();
    }
}
