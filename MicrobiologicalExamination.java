/* 
MYRTO ANASTASIADI ALEXIOU
AM: 3250008
Webmail: p3250008@aueb.gr
--------------------------
VALERIA DIACONU
AM: 3250238
Webmail: p3250238@aueb.gr 
*/

public class MicrobiologicalExamination extends Exam {
    private String sampleType;
    private static final double costIncreaseRate = 0.20;

    //Constructor for loading microbiological exam from file
    public MicrobiologicalExamination(String examName, int maxSlots, double cost, int doctorId, String sampleType) {
        super(examName, "Microbiological", maxSlots, cost, doctorId);
        this.sampleType = sampleType;
    }
    //Constructor for new microbiological exam
    public MicrobiologicalExamination(int id, String examName, int maxSlots, double cost, int doctorId, String sampleType) {
        super(id, examName, "Microbiological", maxSlots, cost, doctorId);
        this.sampleType = sampleType;
    }

    //Getter for sampleType
    public String getSampleType() {return sampleType;}


    //abstract method getCost() implementation
    public double getCost(boolean fastResults){
        if (fastResults)  return getExamCost() + (getExamCost() *costIncreaseRate);
        else return getExamCost();
    }
    //toString method
    @Override
    public String toString() {
        return super.toString() + String.format(" | Sample Type: %s", sampleType);
    }
}