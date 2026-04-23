import java.util.ArrayList;

public abstract class Habit {
    public static final int MAX_DIFFICULTY = 5;
    public static final int MIN_DIFFICULTY = 1;
    public static final int DEFAULT_GOAL_DAYS = 66; // from Lally 2010 for habit automaticity

    private String habitTitle;
    private String creationDate;
    private int goalDayCount;
    private ArrayList<DailyEntry> entryLedger;

    public Habit(String habitTitle, String creationDate) {
        this(habitTitle, creationDate, DEFAULT_GOAL_DAYS);
    }

    public Habit(String habitTitle, String creationDate, int goalDayCount) {
        this.habitTitle = habitTitle;
        this.creationDate = creationDate;
        this.goalDayCount = goalDayCount;
        this.entryLedger = new ArrayList<DailyEntry>();
    }

    public String getHabitTitle() {
        return habitTitle;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public int getGoalDayCount() {
        return goalDayCount;
    }

    public ArrayList<DailyEntry> getEntryLedger() {
        return entryLedger;
    }

    public void recordEntry(DailyEntry newEntry) {
        entryLedger.add(newEntry);
    }

    public abstract String getCategoryLabel();

    @Override
    public String toString() {
        return habitTitle + " (created " + creationDate + ", goal " + goalDayCount + " days)";
    }
}
