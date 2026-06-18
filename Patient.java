/* 
MYRTO ANASTASIADI ALEXIOU
AM: 3250008
Webmail: p3250008@aueb.gr
--------------------------
VALERIA DIACONU
AM: 3250238
Webmail: p3250238@aueb.gr 
*/

public class Patient extends Person {
    private static int nextId=0;
    private int id ;
    private String email;

    // Constructor for loading patient from file
    Patient(int id, String name, int phone, String email) {
        super(name, phone);
        this.id = id;
        if (id > nextId) nextId = id;
        this.email = email;
    }
    // Constructor for new patient
    Patient(String name, int phone, String email) {
        super(name, phone);
        this.id = ++nextId;
        this.email = email;
    }

    //Getters
    public int getId() { return id; }
    
    public String getEmail(){return email; }

    //toString Method
    @Override 
    public String toString(){
        return String.format("Patient Name & Phone number: %s | Patient email : %s |Patient ID : %d",super.toString(),getEmail(),getId());
    }
}