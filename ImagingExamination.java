public class ImagingExamination extends Exam {

    private String machineType;
    private static final double costIncreaseRate = 0.10;

    public ImagingExamination(String examName, int maxSlots, double cost, int doctorId, String machineType) {
        super(examName, "Imaging", maxSlots, cost, doctorId);
        this.machineType = machineType;
    }

    public ImagingExamination(int id, String examName, int maxSlots, double cost, int doctorId, String machineType) {
        super(id, examName, "Imaging", maxSlots, cost, doctorId);
        this.machineType = machineType;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public double getCost(boolean fastResults){
        if (fastResults) {
            return getExamCost() + (getExamCost() *costIncreaseRate);
        } else {
            return getExamCost();
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Machine Type: %s", machineType);
    }

}
