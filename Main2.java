import java.io.*;
import java.util.Scanner;

public class Main {
    //----------------------method that shows a menu-----------------------
    public static void printMenu(String[] menu) {
        System.out.println("\n-------------------------");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }
        System.out.println("----------------------------");
    }
    //----------------------------MAIN--------------------------------
    public static void main(String[] args) {
        // Declarations
        Scanner in = new Scanner(System.in);
        FileManager fm = new FileManager();

        File f1 = new File("doctors.txt");
        File f2 = new File("patients.txt");
        File f3 = new File("exams.txt");
        File f4 = new File("appointments.txt");

        // if file doesnt exists creat new and load it in the collections
        if (!f1.exists()) {
            System.out.println("\ndoctors.txt not found. Creating initial doctors...");
            Doctor d1 = new Doctor("Myrto Anastasiadi", 111, "Cardiology", 10);
            Doctor d2 = new Doctor("Mariah Johnson", 102, "Neurology", 5);
            Doctor d3 = new Doctor("William Smith", 123, "Radiology", 8);
            fm.addDoctor(d1);
            fm.addDoctor(d2);
            fm.addDoctor(d3);
        } else {
            System.out.println("\ndoctors.txt found. Loading doctors...");
            fm.loadDoctors("doctors.txt");
        }

        // PATIENTS
        if (!f2.exists()) {
            System.out.println("\npatients.txt not found. Creating initial patients...");
            Patient p1 = new Patient("Victoria ", 1241, "alice@email.com");
            Patient p2 = new Patient("Bob Black", 1122, "bob@email.com");
            Patient p3 = new Patient("Carol Green", 1163, "carol@email.com");
            fm.addPatient(p1);
            fm.addPatient(p2);
            fm.addPatient(p3);
        } else {
            System.out.println("\npatients.txt found. Loading patients...");
            fm.loadPatients("patients.txt");
        }

        // EXAMS
        if (!f3.exists()) {
            System.out.println("\nexams.txt not found. Creating initial exams...");
            ImagingExamination ex1 = new ImagingExamination("Brain MRI", 5, 150.0, 1, "MRI");
            ImagingExamination ex2 = new ImagingExamination("Chest X-Ray", 10, 50.0, 3, "X-Ray");
            ImagingExamination ex3 = new ImagingExamination("Abdomen CT", 4, 120.0, 3, "CT");
            
            MicrobiologicalExamination ex4 = new MicrobiologicalExamination("Blood Test", 20, 30.0, 1, "Blood");
            MicrobiologicalExamination ex5 = new MicrobiologicalExamination("Urine Test", 20, 25.0, 1, "Urine");
            MicrobiologicalExamination ex6 = new MicrobiologicalExamination("Covid Test", 15, 40.0, 2, "Swab");
            
            SpecializedExamination ex7 = new SpecializedExamination("Holter 24h", 3, 200.0, 1, "Cardiology");
            SpecializedExamination ex8 = new SpecializedExamination("EEG", 3, 180.0, 2, "Neurology");
            SpecializedExamination ex9 = new SpecializedExamination("Spirometry", 5, 100.0, 2, "Pulmonology");
            
            fm.addExam(ex1);
            fm.addExam(ex2);
            fm.addExam(ex3);
            fm.addExam(ex4);
            fm.addExam(ex5);
            fm.addExam(ex6);
            fm.addExam(ex7);
            fm.addExam(ex8);
            fm.addExam(ex9);

        } else {
            System.out.println("\nexams.txt found. Loading exams...");
            fm.loadExams("exams.txt");
        }

        //APPOINTMENTS
        if (!f4.exists()) {
            System.out.println("\nappointments.txt not found. Creating initial appointments...");
            
            Appointment a1 = new Appointment(1, 1, "01:01:2026", false);
            Appointment a2 = new Appointment(2, 4, "02:01:2026", true);
            Appointment a3 = new Appointment(3, 7, "03:01:2026", false);
            
            fm.addAppointment(a1);
            fm.addAppointment(a2);
            fm.addAppointment(a3);

        } else {
            System.out.println("\nappointments.txt found. Loading appointments...");
            fm.loadAppointments("appointments.txt");
        }

        //------------------MAIN MENU LOOP ----!!!!!!!!!!!!!!!!
        String[] mainMenu = {"Exit", "Doctors", "Patients", "Exams", "Appointments", "Statistics"};
        String[] doctorsMenu = {"Back", "Add doctor", "Show all doctors", "Show doctor details", "Doctor appointments"};
        String[] patientsMenu = {"Back", "Add patient", "Show all patients", "Show patient details"};
        String[] examsMenu = {"Back", "Add exam", "Show all exams", "Show exam details"};
        String[] appointmentsMenu = {"Back", "Add appointment", "Show all appointments", "Show appointments of a patient", "Delete appointment", "Show appointments by date"};
        String[] statisticsMenu = {"Back", "Revenue per patient", "Revenue per exam", "Revenue per category"};

        boolean running = true;
        while (running) {
            printMenu(mainMenu);
            System.out.println("Choose: ");
            int choice = Integer.parseInt(in.nextLine());

            switch (choice) {
                case 1: // Doctors
                    boolean inDoctors = true;
                    while (inDoctors) {
                        printMenu(doctorsMenu);
                        System.out.println("Choose: ");
                        int doctorChoice = Integer.parseInt(in.nextLine());
                        switch (doctorChoice) {
                            case 1: fm.addDoctorFromUser(in); break;
                            case 2: fm.showAllDoctors(); break;
                            case 3: fm.showDoctorDetails(in); break;
                            case 4: fm.showDoctorAppointments(in); break;
                            case 0: inDoctors = false; break;
                            default: System.out.println("Invalid  ");
                        }
                    }
                    break;

                case 2: // Patients
                    boolean inPatients = true;
                    while (inPatients) {
                        printMenu(patientsMenu);
                        System.out.println("Choose: ");
                        int patientChoice = Integer.parseInt(in.nextLine());
                        switch (patientChoice) {
                            case 1: fm.addPatientFromUser(in); break;
                            case 2: fm.showAllPatients(); break;
                            case 3: fm.showPatientDetails(in); break;
                            case 0: inPatients = false; break;
                            default: System.out.println("Invalid ");
                        }
                    }
                    break;

                case 3: // Exams
                    boolean inExams = true;
                    while (inExams) {
                        printMenu(examsMenu);
                        System.out.println("Choose: ");
                        int examChoice = Integer.parseInt(in.nextLine());
                        switch (examChoice) {
                            case 1: fm.addExamFromUser(in); break;
                            case 2: fm.showAllExams(); break;
                            case 3: fm.showExamDetails(in); break;
                            case 0: inExams = false; break;
                            default: System.out.println("Invalid ");
                        }
                    }
                    break;

                case 4: // Appointments
                    boolean inAppointments = true;
                    while (inAppointments) {
                        printMenu(appointmentsMenu);
                        System.out.println("Choose: ");
                        int appointmentChoice = Integer.parseInt(in.nextLine());
                        switch (appointmentChoice) {
                            case 1: fm.addAppointmentFromUser(in); break;
                            case 2: fm.showAllAppointments(); break;
                            case 3: fm.showPatientAppointments(in); break;
                            case 4: fm.deleteAppointment(in); break;
                            case 5: fm.showAppointmentsByDate(in); break;
                            case 0: inAppointments = false; break;
                            default: System.out.println("Invalid ");
                        }
                    }
                    break;

              
                case 5: // Statistics
                    boolean inStatistics = true;
                    while (inStatistics) {
                        printMenu(statisticsMenu);
                        System.out.println("Choose: ");
                        int statisticsChoice = Integer.parseInt(in.nextLine());
                        switch (statisticsChoice) {
                            case 1: fm.revenuePerPatient(); break;
                            case 2: fm.revenuePerExam(); break;
                            case 3: fm.revenuePerCategory(); break;
                            case 0: inStatistics = false; break;
                            default: System.out.println("Invalid ");
                        }
                    }
                    break;

                case 0: // Exit
                    running = false;
                    break;

                default:
                    System.out.println("Invalid");
            }
        }

        // Save all collections to files on exit
        fm.saveDoctors("doctors.txt");
        fm.savePatients("patients.txt");
        fm.saveExams("exams.txt");
        fm.saveAppointments("appointments.txt");
        System.out.println("\nData saved successfully");
    }
}
