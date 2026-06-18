/* 
MYRTO ANASTASIADI ALEXIOU
AM: 3250008
Webmail: p3250008@aueb.gr
--------------------------
VALERIA DIACONU
AM: 3250238
Webmail: p3250238@aueb.gr 
*/

public class Doctor extends Person {
    private static int nextId= 0;
    private int id;
    private String specialty;
    private int years;

    // Constructor for loading doctor from file
    public Doctor(String name, int phone, String specialty, int years) {
        super(name, phone);
        this.id = ++nextId;
        this.specialty = specialty;
        this.years = years;

    }
    // Constructor for new doctor
    public Doctor(int id, String name, int phone, String specialty, int years) {
        super(name, phone);
        this.id = id;
        this.specialty = specialty;
        this.years = years;
        if (id > nextId) nextId = id;
    }

    //getters
    public int getId() {return id; }

    public String getSpecialty() {return specialty; }

    public int getYears() {return years; }
    
    //toString method
    @Override
    public String toString() {
        return String.format("Doctor ID: %d | Name: %s | Phone: %d | Specialty: %s | Years of Experience: %d",
                id, getName(), getPhone(), specialty, years);
    }
}