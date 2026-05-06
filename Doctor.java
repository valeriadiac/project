public class Doctor extends Person {

    private static int nextID= 0;
    private int id;
    private String doctorName;
    private int doctorPhone;
    private String specialty;
    private int years;

    public Doctor(int id, String doctorName, int doctorPhone, String specialty, int years) {

        super(id, doctorName, doctorPhone);
        this.specialty = specialty;
        this.years = years;

    }


}
