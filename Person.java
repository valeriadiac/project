/* 
MYRTO ANASTASIADI ALEXIOU
AM: 3250008
Webmail: p3250008@aueb.gr
--------------------------
VALERIA DIACONU
AM: 3250238
Webmail: p3250238@aueb.gr 
*/

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