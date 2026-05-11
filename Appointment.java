public class Appointment {

    private static int nextID = 0;
    private int id;
    private int patientId;
    private int examId;
    private String date;
    private boolean fastResults;

    public Appointment(int patientId, int examId, String date, boolean fastResults) {
        this.id = nextID++;
        this.patientId = patientId;
        this.examId = examId;
        this.date = date;
        this.fastResults = fastResults;
    }

    public Appointment(int id, int patientId, int examId, String date, boolean fastResults) {
        this.id = id;
        if (id >= nextID) nextID = id + 1;
        this.patientId = patientId;
        this.examId = examId;
        this.date = date;
        this.fastResults = fastResults;
    }

    public int getAppointmentId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getExamId() {
        return examId;
    }

    public String getDate() {
        return date;
    }

    public boolean getFastResults() {
        return fastResults;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Appointment ID: %d | Patient ID: %d | Exam ID: %d | Date: %s | Fast Results: %b",
                id, patientId, examId, date, fastResults);
    }

    public String toStringFile() {
        return id + "," + patientId + "," + examId + "," + date + "," + fastResults;
    }
}
