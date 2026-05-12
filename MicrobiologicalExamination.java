public class MicrobiologicalExamination extends Exam {

    private String sampleType;
    private static final double costIncreaseRate = 0.20;

    public MicrobiologicalExamination(String examName, int maxSlots, double cost, int doctorId, String sampleType) {
        super(examName, "Microbiological", maxSlots, cost, doctorId);
        this.sampleType = sampleType;
    }

    public MicrobiologicalExamination(int id, String examName, int maxSlots, double cost, int doctorId, String sampleType) {
        super(id, examName, "Microbiological", maxSlots, cost, doctorId);
        this.sampleType = sampleType;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
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
        return super.toString() + String.format(" | Sample Type: %s", sampleType);
    }

    @Override
    public String toStringFile() {
        return super.toStringFile() + "," + sampleType;
    }
    
}
