// Health-specific habit subclass
public class HealthHabit extends Habit {
    private String healthType;
    private int targetMinutesPerDay;

    // Constructor
    public HealthHabit(String habitTitle, String creationDate, int goalDayCount, String healthType, int targetMinutesPerDay) {
        super(habitTitle, creationDate, goalDayCount);
        this.healthType = healthType;
        this.targetMinutesPerDay = targetMinutesPerDay;
    }

    public String getHealthType() {
        return healthType;
    }

    public int getTargetMinutesPerDay() {
        return targetMinutesPerDay;
    }

    // Checks if minutes target met
    public boolean hitMinutesTarget(int minutesLogged) {
        return minutesLogged >= targetMinutesPerDay;
    }

    public String getCategoryLabel() {
        return "Health";
    }

    // Formats as string
    public String toString() {
        return super.toString() + " [" + healthType + ", target " + targetMinutesPerDay + " min/day]";
    }
}
