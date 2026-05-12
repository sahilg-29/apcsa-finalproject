import java.util.ArrayList;

// Utility class for habit calculations
public class HabitCalculator {
    // Calculates automaticity score
    public static double calculateAutomaticity(ArrayList<DailyEntry> entryLedger, int goalDayCount) {
        double completedCount = 0;
        for (int i = 0; i < entryLedger.size(); i++) {
            if (entryLedger.get(i).isCompleted()) {
                completedCount++;
            }
        }
        double daysTracked = entryLedger.size();
        if (daysTracked == 0) {
            return 0.0;
        }
        double completionRate = completedCount / daysTracked;
        double timeProgress = Math.min(1.0, daysTracked / goalDayCount);
        double automaticityScore = completionRate * timeProgress * 100;
        return Math.round(automaticityScore * 10.0) / 10.0;
    }

    // Computes current streak
    public static int computeCurrentStreak(ArrayList<DailyEntry> entryLedger, int indexFromEnd) {
        if (indexFromEnd >= entryLedger.size()) {
            return 0;
        }
        int checkIndex = entryLedger.size() - 1 - indexFromEnd;
        if (checkIndex < 0) {
            return 0;
        }
        if (!entryLedger.get(checkIndex).isCompleted()) {
            return 0;
        }
        return 1 + computeCurrentStreak(entryLedger, indexFromEnd + 1);
    }

    // Grades habit strength
    public static String gradeHabitStrength(double automaticityScore) {
        if (automaticityScore < 25.0) {
            return "Weak";
        } else if (automaticityScore < 50.0) {
            return "Building";
        } else if (automaticityScore < 75.0) {
            return "Strong";
        } else {
            return "Automatic";
        }
    }

    // Builds weekly completion grid
    public static int[][] buildWeeklyGrid(ArrayList<DailyEntry> entryLedger, int weekCount) {
        int[][] completionGrid = new int[weekCount][7];
        for (int weekIndex = 0; weekIndex < weekCount; weekIndex++) {
            for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
                int entryIndex = weekIndex * 7 + dayIndex;
                if (entryIndex < entryLedger.size()) {
                    completionGrid[weekIndex][dayIndex] = entryLedger.get(entryIndex).isCompleted() ? 1 : 0;
                } else {
                    completionGrid[weekIndex][dayIndex] = -1;
                }
            }
        }
        return completionGrid;
    }
}
