/* 
MYRTO ANASTASIADI ALEXIOU
AM: 3250008
Webmail: p3250008@aueb.gr
--------------------------
VALERIA DIACONU
AM: 3250238
Webmail: p3250238@aueb.gr 
*/

public class Appointment {

    private static int nextId = 0;
    private int id;
    private int patientId;
    private int examId;
    private String date;
    private boolean fastResults;

    // Constructor for loading appointment from file
    public Appointment(int patientId, int examId, String date, boolean fastResults) {
        this.id = ++nextId;
        this.patientId = patientId;
        this.examId = examId;
        this.date = date;
        this.fastResults = fastResults;
    }
    
    // Constructor for new appointment
    public Appointment(int id, int patientId, int examId, String date, boolean fastResults) {
        this.id = id;
        this.patientId = patientId;
        this.examId = examId;
        this.date = date;
        this.fastResults = fastResults;
        if (id > nextId) nextId = id;
    }

    // getters
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
    
    //toString Method
    @Override
    public String toString() {
        return String.format("Appointment ID: %d | Patient ID: %d | Exam ID: %d | Date: %s | Fast Results: %b",
                id, patientId, examId, date, fastResults);
    }
}
