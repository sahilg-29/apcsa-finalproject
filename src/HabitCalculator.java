import java.util.ArrayList;

public class HabitCalculator {
    public static int[][] buildWeeklyGrid(ArrayList<DailyEntry> entryLedger) {
        int weeks = (entryLedger.size() + 6) / 7;
        int[][] completionGrid = new int[weeks][7];

        for (int i = 0; i < entryLedger.size(); i++) {
            int week = i / 7;
            int dayOfWeek = i % 7;
            completionGrid[week][dayOfWeek] = entryLedger.get(i).isCompletionFlag() ? 1 : 0;
        }

        return completionGrid;
    }
}
