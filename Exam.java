public abstract class Exam {

    private static int examID = 0;
    private String examName;
    private String categoryName;
    private int maxSlots;

    public Exam (int examID, String examName, String categoryName, int maxSlots) {

        this.examID = ++examID;
        this.examName = examName;
        this.categoryName = categoryName;
        this.maxSlots = maxSlots;

    }

    public int getExamID() {
        return examID;
    }
    public String getExamName() {
        return examName;
    }

    public abstract int getCost();

}
