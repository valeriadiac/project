/* 
MYRTO ANASTASIADI ALEXIOU
AM: 3250008
Webmail: p3250008@aueb.gr
--------------------------
VALERIA DIACONU
AM: 3250238
Webmail: p3250238@aueb.gr 
*/
import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class Main{
    //----------------Collection for the main menu-------------------
    public static HashMap<String,String> mainMenu=new HashMap<>();
    //----------------------------Doctors menu-------------------------
    public static HashMap<String,String> doctorsMenu=new HashMap<>();
    //--------------------------Patient menu ----------------------------------
    public static HashMap<String,String> patientsMenu=new HashMap<>();
    //--------------------------Exam menu----------------------------------
    public static HashMap<String,String> examsMenu=new HashMap<>();
    //-------------------------------Appointments menu---------------------------
    public static HashMap<String,String> appointmentsMenu=new HashMap<>();
    //-------------------------------Statistics Menu-------------------------------
    public static HashMap<String,String> statisticsMenu=new HashMap<>();
   
    //----------------------method that shows a menu-----------------------
    public static void printMenu(HashMap<String,String> menu){
        System.out.println("\n-------------------------");
        for (String key :menu.keySet()){
            System.out.println(key+" . "+menu.get(key));

        }
        System.out.println("----------------------------");
    }

    //-------------------Submenu loops------------------------------------
    public static void doctorsMenuLoop(Scanner in,FileManager fm){
        boolean back=false;
        while ((!back)) {
            printMenu(doctorsMenu);
            System.out.print("Choose : ");
            String c = in.nextLine();
            switch (c){
                case "1":fm.addDoctorFromUser(in); break ;
                case "2":fm.showAllDoctors(); break;
                case "3": fm.showDoctorDetails(in); break;
                case "4":fm.showDoctorAppointments(in); break;
                case "0": back=true; break;
                default : System.out.println("Invalid choice.");
            }
        }
    }

    public static void patientsMenuLoop(Scanner in,FileManager fm){
        boolean back=false;
        while ((!back)) {
            printMenu(patientsMenu);
            System.out.print("Choose : ");
            String c = in.nextLine();
            switch (c){
                case "1":fm.addPatientFromUser(in); break ;
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
            System.out.print("Choose : ");
            String c = in.nextLine();
            switch (c){
                case "1":fm.addExamFromUser(in); break ;
                case "2":fm.showAllExams(); break;
                case "3": fm.showExamDetails(in); break;
                case "0": back=true; break;
                default : System.out.println("Invalid choice.");       
            }
        }
    }


    public static void appointmentsMenuLoop(Scanner in,FileManager fm){
        boolean back=false;
        while ((!back)) {
            printMenu(appointmentsMenu);
            System.out.print("Choose : ");
            String c = in.nextLine();
            switch (c){
                case "1":fm.addAppointmentFromUser(in); break ;
                case "2":fm.showAllAppointments(); break;
                case "3": fm.showPatientAppointments(in); break;
                case "4":fm.deleteAppointment(in); break;
                case "5": fm.showAppointmentsByDate(in);break;
                case "0": back=true; break;
                default : System.out.println("Invalid choice.");  
            }
        }
    }


    public static void statisticsMenuLoop(Scanner in,FileManager fm){
        boolean back=false;
        while ((!back)) {
          printMenu(statisticsMenu); 
          System.out.print("Choose : ");
          String c = in.nextLine();
          switch (c) {
            case "1":fm.revenuePerPatient();break;
            case "2":fm.revenuePerExam(); break;
            case "3":fm.revenuePerCategory(); break;
            case "0":back=true; break;
          }
        }
    }
    
    //----------------------------MAIN--------------------------------
    public static void main (String[] args ){
        Scanner in =new Scanner(System.in);
        FileManager fm=new FileManager();
        mainMenu.put("1","Doctors");
        mainMenu.put("2","Patients");
        mainMenu.put("3","Exams");
        mainMenu.put("4","Appointmets");
        mainMenu.put("5","Statistics");
        mainMenu.put("0","Exit");

        doctorsMenu.put("1","Add doctor");
        doctorsMenu.put("2","Show all doctors");
        doctorsMenu.put("3","Show doctor details");
        doctorsMenu.put("4","Doctor apointments");
        doctorsMenu.put("0","Back");

        patientsMenu.put("1","Add patient");
        patientsMenu.put("2","Show all patients");
        patientsMenu.put("3","Show patient details");
        patientsMenu.put("0","Back");

        examsMenu.put("1","Add exam");
        examsMenu.put("2","Show all exams");
        examsMenu.put("3","Show exam details");
        examsMenu.put("0","Back");

        appointmentsMenu.put("1","Add apointment");
        appointmentsMenu.put("2","Show all appointments");
        appointmentsMenu.put("3","Show apointments of a patient");
        appointmentsMenu.put("4","Delete appointment");
        appointmentsMenu.put("5","Show appointments by date");
        appointmentsMenu.put("0","Back");

        statisticsMenu.put("1","Revenue per patient");
        statisticsMenu.put("2","Revenue per exam");
        statisticsMenu.put("3","revenue per category");
        statisticsMenu.put("0","Back");

        File f1= new File("doctors.txt");
        File f2= new File("patients.txt");
        File f3= new File("exams.txt");
        File f4= new File("appointmets.txt");

        // if file doesnt exists creat new and load it in the collections
        if(!f1.exists()){
            System.out.println("doctors.txt not found. Creating initial doctors");
            Doctor d1=new Doctor("Ektor",3456,"Pulmonology",17);
            Doctor d2=new Doctor("Maria",4568,"Cardiology",23);
            Doctor d3=new Doctor("Sara",2386,"Neurology",15);
            fm.addDoctor(d1);
            fm.addDoctor(d2);
            fm.addDoctor(d3);
        }
        else {
            System.out.println("Loading doctors.txt");
            fm.loadDoctors("doctors.txt");
        }
        // PATIENTS
        if(!f2.exists()){
            System.out.println("patients.txt not found. Creating initial patients.");
            Patient p1=new Patient("Giorgos",87534567,"gior@.gmail.com");
            Patient p2=new Patient("Mary",45678905,"mary@gmail.com");
            Patient p3=new Patient("Nicol",854567897,"nicol@gmail.com");
            fm.addPatient(p1);
            fm.addPatient(p2);
            fm.addPatient(p3);
        }
        else {
            System.out.println("Loading patients.txt");
            fm.loadPatients("patients.txt");
        }
         // EXAMS
        if(!f3.exists()){
            System.out.println("exams.txt not found. Creating initial exams");
            SpecializedExamination ex1= new SpecializedExamination("Holter 24h", 23, 80,2, "Cardiology");
            SpecializedExamination ex2 = new SpecializedExamination("Spirometry", 13, 90.0, 1, "Pulmonology");
            SpecializedExamination ex3 = new SpecializedExamination("EEG", 12, 85, 3, "Neurology");

            MicrobiologicalExamination ex4 =new MicrobiologicalExamination("Blood Test", 18, 35, 1, "Blood");
            MicrobiologicalExamination ex5 = new MicrobiologicalExamination("Urine Test", 12, 30.0, 1, "Urine");
            MicrobiologicalExamination ex6 = new MicrobiologicalExamination("Covid Test", 10, 20, 2, "Swab");
            
            ImagingExamination ex7=new ImagingExamination("Spine MRI", 4, 70, 2, "MRI");
            ImagingExamination ex8=new ImagingExamination("Abdominal X-Ray", 7, 85, 3, "X-Ray");
            ImagingExamination ex9=new ImagingExamination("Brain CT", 2, 150, 1, "CT");
            
            fm.addExam(ex1);
            fm.addExam(ex2);
            fm.addExam(ex3);
            fm.addExam(ex4);
            fm.addExam(ex5);
            fm.addExam(ex6);
            fm.addExam(ex7);
            fm.addExam(ex8);
            fm.addExam(ex9);
        }
        else {
            System.out.println("Loading exams.txt");
            fm.loadExams("exams.txt");
        }
        //APPOINTMENTS
        if(!f4.exists()){
            System.out.println("appointments.txt not found. Creating initial appointments");
            Appointment a1=new Appointment(1, 7, "20:04:2026",true );
            Appointment a2=new Appointment(1, 6, "08:05:2026",false );
            Appointment a3=new Appointment(3, 4, "22:10:2026",true );
            fm.addAppointment(a1);
            fm.addAppointment(a2);
            fm.addAppointment(a3);
       }
        else {
            System.out.println("Loading appointments.txt ");
            fm.loadAppointments("appointments.txt");
        }
        //------------------MAIN MENU LOOP -------------------------------------------
         boolean running =true;
         while(running){
            printMenu(mainMenu);
            System.out.print("Choose: ");
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
                    statisticsMenuLoop(in,fm);
                    break;
                case "0":
                    running =false;
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        }
        //----------------Save all before exit---------------------------
        fm.saveDoctors("doctors.txt");
        fm.savePatients("patients.txt");
        fm.saveExams("exams.txt");
        fm.saveAppointments("appointments.txt");
        System.out.println("Data saved ! The program is finished successfully ! ");
    } 
}
