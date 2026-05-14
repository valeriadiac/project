import java.io.*
import java.util.Scanner;

public class Main{
    //----------------Collection for the main menu-------------------
    public static HashMap<String,String> mainMenu=new HashMap<>();
    mainMenu.put("1","Doctors");
    mainMenu.put("2","Patients");
    mainMenu.put("3","Exams");
    mainMenu.put("4","Appointmets");
    mainMenu.put("5","Statistics");
    mainMenu.put("0","Exit");
    //----------------------------Doctors menu-------------------------
    public static HashMap<String,String> doctorsMenu=new HashMap<>();
    doctorsMenu.put("1","Add doctor");
    doctorsMenu.put("2","Show all doctors");
    doctorsMenu.put("3","Show doctor details");
    doctorsMenu.put("4","Doctor apointments");
    doctorsMenu.put("0","Back");
    //--------------------------Patient menu ----------------------------------
    public static HashMap<String,String> patientsMenu=new HashMap<>();
    patientsMenu.put("1","Add patient");
    patientsMenu.put("2","Show all patients");
    patientsMenu.put("3","Show patient details");
    patientsMenu.put("0","Back");
    //--------------------------Exam menu------------------------------------
    public static HashMap<String,String> examsMenu=new HashMap<>();
    examsMenu.put("1","Add exam");
    examsMenu.put("2","Show all exams");
    examsMenu.put("3","Show exam details");
    examsMenu.put("0","Back");
    //-------------------------------Appointments menu---------------------------
    public static HashMap<String,String> appointmentsMenu=new HashMap<>();
    appointmentsMenu.put("1","Add apointment");
    appointmentsMenu.put("2","Show all appointments");
    appointmentsMenu.put("3","Show apointments of a patient");
    appointmentsMenu.put("4","Delete appointment");
    appointmentsMenu.put("5","Show appointments by date");
    appointmentsMenu.put("0","Back");
    //----------------------method that shows a menu-----------------------
    
    public static void printMenu(HashMap<String,String> menu){
        System.out,println("\n-------------------------");
        for (String key :menu.keySet()){
            System.out,println(key+" . "+menu.get(key));

        }
        System.out,println("----------------------------");
    }
    







    //----------------------------MAIN--------------------------------
    public static void main (String[] args ){
        Scanner in =new Scanner(System.in);
        FileManager fm=new File FileManager();

        
        File f1= new File("doctors.txt");
        File f2= new File("patients.txt");
        File f3= new File("exams.txt");
        File f4= new File("appointmets.txt");

        // if file doesnt exists creat new and load it in the collections
        if(!f1.exists()){
            System.out.println("doctors.txt not found. Creating initial doctors...");
            fm.saveDoctor("doctors.txt");

        }
        else {
            System.out.println("doctors.txt not found. Creating initial doctors...");
            fm.loadDoctor("doctors.txt");
        }
        // PATIENTS
        if(!f2.exists()){
            System.out.println("patients.txt not found. Creating initial patients...");
            fm.savePatient("patients.txt");

        }
        else {
            System.out.println("patients.txt not found. Creating initial patients...");
            fm.loadPatient("patients.txt");
        }
         // EXAMS
        if(!f3.exists()){
            System.out.println("exams.txt not found. Creating initial exams...");
            fm.savePatient("exams.txt");

        }
        else {
            System.out.println("exams.txt not found. Creating initial exams...");
            fm.loadPatient("exams.txt");
        }
        //APPOINTMENTS
        if(!f4.exists()){
            System.out.println("appointments.txt not found. Creating initial appointments...");
            fm.savePatient("appointments.txt");

        }
        else {
            System.out.println("appointments.txt not found. Creating initial appointments...");
            fm.loadPatient("appointments.txt");
        }


        //------------------MAIN MENU LOOP -------------------------------------------
         boolean running =true;

         while(running){
            printMenu(mainMenu);
            System.out.println("Choose: ");
            String choice=in.nextLine();

            switch (choice)
         }


        




    } 

}