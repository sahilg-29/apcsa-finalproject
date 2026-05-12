import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// Handles saving and loading habits to/from file
public class HabitFileManager {
    // Saves all habits to file
    public static void saveAllHabits(ArrayList<Habit> habitRegistry, String filePath) throws IOException {
        PrintWriter writer = new PrintWriter(filePath);
        for (Habit habit : habitRegistry) {
            if (habit instanceof HealthHabit) {
                HealthHabit healthHabit = (HealthHabit) habit;
                writer.println("HABIT,Health," + healthHabit.getHabitTitle() + "," + healthHabit.getCreationDate() + "," + healthHabit.getGoalDayCount() + "," + healthHabit.getHealthType() + "," + healthHabit.getTargetMinutesPerDay());
            } else if (habit instanceof ProductivityHabit) {
                ProductivityHabit productivityHabit = (ProductivityHabit) habit;
                writer.println("HABIT,Productivity," + productivityHabit.getHabitTitle() + "," + productivityHabit.getCreationDate() + "," + productivityHabit.getGoalDayCount() + "," + productivityHabit.getWorkCategory() + "," + productivityHabit.isRequiresFocusMode());
            }
            for (DailyEntry entry : habit.getEntryLedger()) {
                String note = entry.getNoteText();
                writer.println("ENTRY," + entry.getEntryDate() + "," + entry.isCompleted() + "," + entry.getDifficultyRating() + "," + note);
            }
        }
        writer.close();
    }

    // Loads all habits from file
    public static ArrayList<Habit> loadAllHabits(String filePath) throws IOException {
        File file = new File(filePath);
        ArrayList<Habit> registry = new ArrayList<Habit>();
        if (!file.exists()) {
            return registry;
        }
        Scanner scanner = new Scanner(file);
        Habit currentHabit = null;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] tokens = line.split(",", -1);
            String recordType = tokens[0];
            switch (recordType) {
                case "HABIT":
                    if (tokens.length < 7) {
                        continue;
                    }
                    String category = tokens[1];
                    String title = tokens[2];
                    String created = tokens[3];
                    int goalCount = Integer.parseInt(tokens[4]);
                    if (category.equals("Health")) {
                        String healthType = tokens[5];
                        int targetMinutes = Integer.parseInt(tokens[6]);
                        currentHabit = new HealthHabit(title, created, goalCount, healthType, targetMinutes);
                        registry.add(currentHabit);
                    } else if (category.equals("Productivity")) {
                        String workCategory = tokens[5];
                        boolean requiresFocus = Boolean.parseBoolean(tokens[6]);
                        currentHabit = new ProductivityHabit(title, created, goalCount, workCategory, requiresFocus);
                        registry.add(currentHabit);
                    }
                    break;
                case "ENTRY":
                    if (tokens.length < 5 || currentHabit == null) {
                        continue;
                    }
                    String entryDate = tokens[1];
                    boolean completionFlag = Boolean.parseBoolean(tokens[2]);
                    int difficultyRating = Integer.parseInt(tokens[3]);
                    String noteText = tokens[4];
                    if (tokens.length > 5) {
                        StringBuilder builder = new StringBuilder(noteText);
                        for (int i = 5; i < tokens.length; i++) {
                            builder.append(",").append(tokens[i]);
                        }
                        noteText = builder.toString();
                    }
                    DailyEntry entry = new DailyEntry(entryDate, completionFlag, difficultyRating, noteText);
                    currentHabit.recordEntry(entry);
                    break;
                default:
                    break;
            }
        }
        scanner.close();
        return registry;
    }
}
