import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

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

    //-------------------Submenu loops------------------------------------
    public static void doctorsMenuLoop(Scanner in,FileManager fm){
        boolean back=false;
        while ((!back)) {
            printMenu(doctorsMenu);
            System.out.println("Choose : ");
            String c = in.nextLine();
            
            switch (c){
                case "1":fm.addDoctor(in); break ;
                case "2":fm.showAllDoctors(); break;
                case "3": fm.showDoctorDetails(in); break;
                case "4":fm.showDoctorAppointment(in); break;
                case "0": back=true; break;
                default : System.out.println("Invalid choice.");

                        
            }
        
        }
    }



    public static void patientsMenuLoop(Scanner in,FileManager fm){
        boolean back=false;
        while ((!back)) {
            printMenu(patientsMenu);
            System.out.println("Choose : ");
            String c = in.nextLine();
            
            switch (c){
                case "1":fm.addPatient(in); break ;
                case "2":fm.showAllPatients(); break;
                case "3": fm.showPatientDetails(in); break;
                case "0": back=true; break;
                default : System.out.println("Invalid choice.");
                
                        
            }
        
        }
    }

    public static void examsMenuLoop(Scanner in,FileManager fm){
        boolean back=false;
        while ((!back)) {
            printMenu(examsMenu);
            System.out.println("Choose : ");
            String c = in.nextLine();
            
            switch (c){
                case "1":fm.addExam(in); break ;
                case "2":fm.showAllExams(); break;
                case "3": fm.showExamDetails(in); break;
                case "0": back=true; break;
                default : System.out.println("Invalid choice.");
                
                        
            }
        
        }
    }


    public static void appointmentsMenuLoop(Scanner in,FileManager fm){
        boolean back=false;
        while ((back)) {
            printMenu(appointmentsMenu);
            System.out.println("Choose : ");
            String c = in.nextLine();
            
            switch (c){
                case "1":fm.addAppointment(in); break ;
                case "2":fm.showAllAppointments(); break;
                case "3": fm.showPatientAppointments(in); break;
                case "4":fm.deleteAppointment(in); break;
                case "5": fm.showAppointmentDate(in);break;
                case "0": back=true; break;
                default : System.out.println("Invalid choice.");
                
                        
            }
        
        }
    }


    public static void satisticsMenuLoop(Scanner in,FileManager fm){
        boolean back=false;
        while ((!back)) {
            
        
        }
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
            Doctor d1=new Doctor("Ektor",34567238,"Dermatologist",17);
            Doctor d2=new Doctor("Maria",4568799,"Cardiologist",23);
            Doctor d3=new Doctor("Sara",23864567,"Physiologist",15);
            fm.addDoctor(d1);
            fm.addDoctor(d2);
            fm.addDoctor(d3);




        }
        else {
            System.out.println("Loading doctors.txt ...");
            fm.loadDoctor("doctors.txt");
        }
        // PATIENTS
        if(!f2.exists()){
            System.out.println("patients.txt not found. Creating initial patients...");
            Patient p1=new Patient("Giorgos",87534567,"gior@.gmail.com");
            Patient p2=new Patient("Mary",45678905,"mary@gmail.com");
            Patient p3=new Patient("Nicol",854567897,"nicol@gmail.com");
            fm.addPatient(p1);
            fm.addPatient(p2);
            fm.addPatient(p3);



        }
        else {
            System.out.println("Loading patients.txt ...");
            fm.loadPatient("patients.txt");
        }
         // EXAMS
        if(!f3.exists()){
            System.out.println("exams.txt not found. Creating initial exams...");
            Specialized ex1= new Specialized("Specialized", 23, 80, 3, "Cardiology");
            Microbiological ex2 =new Microbiological("Microbiological", 10, 40, 1, "Blood");
            ImagingExamination ex3=new ImagingExamination("Imaging", 5, 20, 8, "CT");
            fm.addExam(ex1);
            fm.addExam(ex2);
            fm.addExam(ex3);


        }
        else {
            System.out.println("Loading exams.txt ...");
            fm.loadPatient("exams.txt");
        }
        //APPOINTMENTS
        if(!f4.exists()){
            System.out.println("appointments.txt not found. Creating initial exams...");
            Appointment a1=new Appointment(7, 7, "20:04:2026",true );
            Appointment a2=new Appointment(7, 9, "08:05:2026",false );
            Appointment a3=new Appointment(6, 6, "22:10:2026",true );
            fm.addAppointment(a1);
            fm.addAppointment(a2);
            fm.addAppointment(a3);



       }
        else {
            System.out.println("Loading appointments.txt ...");
            fm.loadPatient("appointments.txt");
        }


        //------------------MAIN MENU LOOP -------------------------------------------
         boolean running =true;

         while(running){
            printMenu(mainMenu);
            System.out.println("Choose: ");
            String choice=in.nextLine();

            switch (choice){
                case "1":
                    doctorsMenuLoop(in,fm);
                    break;
                case "2":
                    patientsMenuLoop(in,fm);
                    break;
                case "3":
                    examsMenuLoop(in,fm);
                case "4":
                    appointmentsMenuLoop(in,fm);
                    break;
                case "5":
                    satisticsMenuLoop(in,fm);
                    break;
                case "0":
                    running =false;
                    break;
                default:
                    System.out.println("Invalid Choice.");
                


            }
        }

        //----------------Save all before exit---------------------------
        fm.saveDoctor("doctors.txt");
        fm.savePatient("patients.txt");
        fm.saveExam("exams.txt");
        fm.saveAppointment("appointments.txt");


        System.out.println("Data saved ! The program is finished successfully ! ");




        




    } 

}