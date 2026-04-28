import java.util.ArrayList;

public class HabitTest {
    public static void main(String[] args) {
        ArrayList<Habit> habitRegistry = new ArrayList<Habit>();
        habitRegistry.add(new HealthHabit("Morning Run", "2026-04-28", 66, "exercise", 30));
        habitRegistry.add(new ProductivityHabit("Study Session", "2026-04-28", 66, "study", true));

        for (Habit habit : habitRegistry) {
            System.out.println(habit.getHabitTitle() + " -> " + habit.getCategoryLabel());
        }
    }
}
