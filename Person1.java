public class Person{
    private static int nextId=0;
    private int id;
    private String name ;
    private int phone ;
    



    Patient (String name,int phone,){
        this.id=++nextId;
        this.name=name;
        this.phone=phone;
        
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id ;
    }

    public int getPhone(){
        return phone;
    }
    

    public String toString(){
        return String.format("ID:%d| Name:%s |Telephonenumber:%d",
        id,name,phone);
    }
    
    



}