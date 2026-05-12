// Productivity-specific habit subclass
public class ProductivityHabit extends Habit {
    private String workCategory;
    private boolean requiresFocusMode;

    // Constructor
    public ProductivityHabit(String habitTitle, String creationDate, int goalDayCount, String workCategory, boolean requiresFocusMode) {
        super(habitTitle, creationDate, goalDayCount);
        this.workCategory = workCategory;
        this.requiresFocusMode = requiresFocusMode;
    }

    public String getWorkCategory() {
        return workCategory;
    }

    public boolean isRequiresFocusMode() {
        return requiresFocusMode;
    }

    // Toggles focus mode
    public boolean flipFocusMode() {
        requiresFocusMode = !requiresFocusMode;
        return requiresFocusMode;
    }

    public String getCategoryLabel() {
        return "Productivity";
    }

    // Formats as string
    public String toString() {
        return super.toString() + " [" + workCategory + ", requiresFocusMode=" + requiresFocusMode + "]";
    }
}
