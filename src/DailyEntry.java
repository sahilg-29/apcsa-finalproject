public class DailyEntry {
    public static final int MIN_DIFFICULTY = 1;
    public static final int MAX_DIFFICULTY = 5;

    private String entryDate;
    private boolean completionFlag;
    private int difficultyRating;
    private String noteText;

    public DailyEntry(String entryDate, boolean completionFlag) {
        this.entryDate = entryDate;
        this.completionFlag = completionFlag;
        this.difficultyRating = MIN_DIFFICULTY;
        this.noteText = "";
    }

    public DailyEntry(String entryDate, boolean completionFlag, int difficultyRating) {
        this.entryDate = entryDate;
        this.completionFlag = completionFlag;
        this.noteText = "";
        setDifficultyRating(difficultyRating);
    }

    public DailyEntry(String entryDate, boolean completionFlag, int difficultyRating, String noteText) {
        this.entryDate = entryDate;
        this.completionFlag = completionFlag;
        this.noteText = noteText != null ? noteText : "";
        setDifficultyRating(difficultyRating);
    }

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

    public String toString() {
        String status = completionFlag ? "DONE" : "SKIP";
        String note = noteText.isEmpty() ? "" : " — " + noteText;
        return String.format("[%s] %s (difficulty=%d)%s", entryDate, status, difficultyRating, note);
    }

    public static void main(String[] args) {
        DailyEntry entry1 = new DailyEntry("2025-11-17", true);
        DailyEntry entry2 = new DailyEntry("2025-11-18", false, 4);
        DailyEntry entry3 = new DailyEntry("2025-11-19", true, 7, "felt strong today");

        System.out.println(entry1);
        System.out.println(entry2);
        System.out.println(entry3);
    }
}
