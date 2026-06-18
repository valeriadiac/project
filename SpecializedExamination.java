/* 
MYRTO ANASTASIADI ALEXIOU
AM: 3250008
Webmail: p3250008@aueb.gr
--------------------------
VALERIA DIACONU
AM: 3250238
Webmail: p3250238@aueb.gr 
*/

public class SpecializedExamination extends Exam {
    private String specialty;
    private static final double costIncreaseRate = 0.30;

    //Constructor for loading specialized exam from file
    public SpecializedExamination(String examName, int maxSlots, double cost, int doctorId, String specialty) {
        super(examName, "Specialized", maxSlots, cost, doctorId);
        this.specialty = specialty;
    }
    //Constructor for new specialized exam
    public SpecializedExamination(int id, String examName, int maxSlots, double cost, int doctorId, String specialty) {
        super(id, examName, "Specialized", maxSlots, cost, doctorId);
        this.specialty = specialty;
    }

    //getter for specialty
    public String getSpecialty() {return specialty;}

    //abstract method getCost() implementation
    public double getCost(boolean fastResults){
        if (fastResults) return getExamCost() + (getExamCost() *costIncreaseRate);
        else return getExamCost();
    }
    //toString method
    @Override
    public String toString() {
        return super.toString() + String.format(" | Specialty: %s", specialty);
    }
}