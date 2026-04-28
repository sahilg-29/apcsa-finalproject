public class HealthHabit extends Habit {
    private String healthType;
    private int targetMinutesPerDay;

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

    public boolean hitMinutesTarget(int minutesLogged) {
        return minutesLogged >= targetMinutesPerDay;
    }

    public String getCategoryLabel() {
        return "Health";
    }

    public String toString() {
        return super.toString() + " [" + healthType + ", target " + targetMinutesPerDay + " min/day]";
    }
}
