

public class Person {
    private String name;
    private int phone;
    
    //Constructor for Person class
    public Person(String name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    //Getters
    public String getName() {return name; }
    
    public int getPhone() { return phone;}

    //toString Method
    @Override 
    public String toString() {
        return String.format("Name: %s | Phone: %d", name, phone); 
    }
}
