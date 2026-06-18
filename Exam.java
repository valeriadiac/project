/* 
MYRTO ANASTASIADI ALEXIOU
AM: 3250008
Webmail: p3250008@aueb.gr
--------------------------
VALERIA DIACONU
AM: 3250238
Webmail: p3250238@aueb.gr 
*/

public abstract class Exam {
    private static int nextId = 0;
    private int id;
    private String examName;
    private String categoryName;
    private int maxSlots;
    protected double cost;
    private int doctorId;

    //Constructor for loading examination from file
    public Exam(String examName, String categoryName, int maxSlots, double cost, int doctorId) {
        this.id = ++nextId;
        this.examName = examName;
        this.categoryName = categoryName;
        this.maxSlots = maxSlots;
        this.cost = cost;
        this.doctorId = doctorId;
    }
    //Constructor for new examination
    public Exam(int id, String examName, String categoryName, int maxSlots, double cost, int doctorId) {
        this.id = id;
        this.examName = examName;
        this.categoryName = categoryName;
        this.maxSlots = maxSlots;
        this.cost = cost;
        this.doctorId = doctorId;
        if (id > nextId) nextId = id;
    }

    //getters
    public int getId() {return id;}

    public String getCategoryName() {return categoryName;}

    public int getMaxSlots() {return maxSlots;}

    public double getExamCost() {return cost; }

    public int getDoctorId() { return doctorId;}

    public String getExamName() { return examName;}

    //abstract method getCost()
    public abstract double getCost(boolean fastResults);
    //toString method
    @Override
    public String toString() {
        return String.format("Exam ID: %d | Name: %s | Category: %s | Max Slots: %d | Cost: %.2f | Doctor ID: %d",
                id, examName, categoryName, maxSlots, cost, doctorId);
    }
}