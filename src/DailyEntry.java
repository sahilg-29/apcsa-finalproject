// Represents a daily entry for a habit
public class DailyEntry {
    public static final int MIN_DIFFICULTY = 1;
    public static final int MAX_DIFFICULTY = 5;

    private String entryDate;
    private boolean completionFlag;
    private int difficultyRating;
    private String noteText;

    // Constructor for basic entry
    public DailyEntry(String entryDate, boolean completionFlag) {
        this.entryDate = entryDate;
        this.completionFlag = completionFlag;
        this.difficultyRating = MIN_DIFFICULTY;
        this.noteText = "";
    }

    // Constructor with difficulty
    public DailyEntry(String entryDate, boolean completionFlag, int difficultyRating) {
        this.entryDate = entryDate;
        this.completionFlag = completionFlag;
        this.noteText = "";
        setDifficultyRating(difficultyRating);
    }

    // Full constructor
    public DailyEntry(String entryDate, boolean completionFlag, int difficultyRating, String noteText) {
        this.entryDate = entryDate;
        this.completionFlag = completionFlag;
        this.noteText = noteText != null ? noteText : "";
        setDifficultyRating(difficultyRating);
    }

    // Sets difficulty within bounds
    private void setDifficultyRating(int difficultyRating) {
        if (difficultyRating < MIN_DIFFICULTY) {
            this.difficultyRating = MIN_DIFFICULTY;
        } else if (difficultyRating > MAX_DIFFICULTY) {
            this.difficultyRating = MAX_DIFFICULTY;
        } else {
            this.difficultyRating = difficultyRating;
        }
    }

    public String getEntryDate() {
        return entryDate;
    }

    public boolean isCompletionFlag() {
        return completionFlag;
    }

    public int getDifficultyRating() {
        return difficultyRating;
    }

    public String getNoteText() {
        return noteText;
    }

    public boolean isCompleted() {
        return completionFlag;
    }

    // Formats entry as string
    public String toString() {
        String status = completionFlag ? "DONE" : "SKIP";
        String note = noteText.isEmpty() ? "" : " — " + noteText;
        return String.format("[%s] %s (difficulty=%d)%s", entryDate, status, difficultyRating, note);
    }
}
