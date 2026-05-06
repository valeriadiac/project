public class Appointment {

    private static int nextID=0;
    private int id;
    private String date;
    private boolean fastResults;

    public Appointment(String date, boolean fastResults) {
        this.id = ++nextID;
        this.date = date;
        this.fastResults = fastResults;
    }

    public int getAppointmentID() {
        return id;
    }
    public String getDate() {
        return date;
    }

}
