public class Patient extends Person {
    private static int nextId=0;
    private int id ;
    private String email;


    Patient(String name, int phone ,String email){
        super(name,phone);
        this.id=++nextId;
        this.email=email;
    }
    Patient (int id, String name, int phone, String specialty, int years) {

        super(name, phone);
        this.id = id;
        if (id >= nextId) nextId = id +1;
        this.email = email;
    }

    public int getID(){return id; }
    
    public String getEmail(){return email;}

    public void setEmail(String email ){this.email=email;}

    @Override 
    public String toString(){
        return String.format("Patient Name and Phone number: %s | Patient email : %s |Patient ID : %d",super.toString(),getEmail(),getID());
    }

    public String toStringFile(){
        return id+" , "+getName()+" , "+getPhone()+" , "+email;
    }
}


