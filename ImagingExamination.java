/* 
MYRTO ANASTASIADI ALEXIOU
AM: 3250008
Webmail: p3250008@aueb.gr
--------------------------
VALERIA DIACONU
AM: 3250238
Webmail: p3250238@aueb.gr 
*/

public class ImagingExamination extends Exam {
    private String machineType;
    private static final double costIncreaseRate = 0.10;
    //Constructor for loading imaging exam from file
    public ImagingExamination(String examName, int maxSlots, double cost, int doctorId, String machineType) {
        super(examName, "Imaging", maxSlots, cost, doctorId);
        this.machineType = machineType;
    }
    //Constructor for new imaging exam
    public ImagingExamination(int id, String examName, int maxSlots, double cost, int doctorId, String machineType) {
        super(id, examName, "Imaging", maxSlots, cost, doctorId);
        this.machineType = machineType;
    }
    
    //Getter and Setter for machineType
    public String getMachineType() { return machineType;}

    //abstract method getCost() implementation
    public double getCost(boolean fastResults){
        if (fastResults)return getExamCost() + (getExamCost() *costIncreaseRate);
        else return getExamCost();
    }
    //toString method
    @Override
    public String toString() {
        return super.toString() + String.format(" | Machine Type: %s", machineType);
    }
}