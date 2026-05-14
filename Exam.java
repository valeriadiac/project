public abstract class Exam {

    private static int nextID = 0;
    private int id;
    private String examName;
    private String categoryName;
    private int maxSlots;
    private double cost;
    private int doctorId;

    Exam(String examName, String categoryName, int maxSlots, double cost, int doctorId) {
        this.id = nextID++;
        this.examName = examName;
        this.categoryName = categoryName;
        this.maxSlots = maxSlots;
        this.cost = cost;
        this.doctorId = doctorId;
    }

    Exam(int id,String examName, String categoryName, int maxSlots, double cost, int doctorId ) {
        this.id = id;
        if (id >= nextID) nextID = id + 1;
        this.examName = examName;
        this.categoryName = categoryName;
        this.maxSlots = maxSlots;
        this.cost = cost;
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getMaxSlots() {
        return maxSlots;
    }

    public double getExamCost() {
        return cost;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public void setMaxSlots(int maxSlots) {
        this.maxSlots = maxSlots;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public abstract double getCost(boolean fastResults);

      
@Override
    public String toString(){
        return String.format("Exam ID: %d | Name: %s | Category: %s | Max Slots: %d | Cost: %.2f | Doctor ID: %d",
                id, examName, categoryName, maxSlots, cost, doctorId);
    }
      
    public String toStringFile() {
        return id + "," + examName + "," + categoryName + "," + maxSlots + "," + cost + "," + doctorId;
    }
}
