public class Doctor extends Person {

    private static int nextId= 0;
    private int id;
    private String specialty;
    private int years;

    public Doctor(String name, int phone, String specialty, int years) {

        super(name, phone);
        this.id = ++nextId;
        this.specialty = specialty;
        this.years = years;

    }

    public Doctor(int id, String name, int phone, String specialty, int years) {

        super(name, phone);
        this.id = id;
        this.specialty = specialty;
        this.years = years;
        if (id > nextId) nextId = id;
    }

    public int getId() {
        return id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getYears() {
        return years;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setYears(int years) {
        this.years = years;
    }

    @Override
    public String toString() {
        return String.format("Doctor ID: %d | Name: %s | Phone: %d | Specialty: %s | Years of Experience: %d",
                id, getName(), getPhone(), specialty, years);
    }

    public String toStringFile() {
        return id + "," + getName() + "," + getPhone() + "," + specialty + "," + years;
    }
}
