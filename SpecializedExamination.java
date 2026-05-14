public class SpecializedExamination extends Exam {

    private String specialty;
    private static final double costIncreaseRate = 0.30;

    public SpecializedExamination(String examName, int maxSlots, double cost, int doctorId, String specialty) {
        super(examName, "Specialized", maxSlots, cost, doctorId);
        this.specialty = specialty;
    }

    public SpecializedExamination(int id, String examName, int maxSlots, double cost, int doctorId, String specialty) {
        super(id, examName, "Specialized", maxSlots, cost, doctorId);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
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
        return super.toString() + String.format(" | Specialty: %s", specialty);
    }
}
