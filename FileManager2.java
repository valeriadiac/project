import java.io.*;
import java.util.*;

class FileManager {

    // --------4 hashmaps -------
    HashMap<Integer, Doctor> doctors = new HashMap<>();
    HashMap<Integer, Patient> patients = new HashMap<>();
    HashMap<Integer, Exam> exams = new HashMap<>();
    HashMap<Integer, Appointment> appointments = new HashMap<>();

    // ------- 4 methods to load the Items from file to hashmap ------------

    // 1
    public void loadDoctors(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim();
                }
                Doctor d = new Doctor(
                    Integer.parseInt(tokens[0]),
                    tokens[1],
                    Integer.parseInt(tokens[2]),
                    tokens[3],
                    Integer.parseInt(tokens[4])
                );
                doctors.put(d.getId(), d);
            }
            reader.close(); // close the file
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    } // loadDoctors

    // 2
    public void loadPatients(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim();
                }
                Patient p = new Patient(
                    Integer.parseInt(tokens[0]),
                    tokens[1],
                    Integer.parseInt(tokens[2]),
                    tokens[3]
                );
                patients.put(p.getId(), p);
            }
            reader.close(); // close the file
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    } // loadPatients

    // 3
    public void loadExams(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim();
                }
                String category = tokens[2];
                Exam exam = null;
                if (category.equals("Imaging")) {
                    exam = new ImagingExamination(
                        Integer.parseInt(tokens[0]),
                        tokens[1],
                        Integer.parseInt(tokens[3]),
                        Double.parseDouble(tokens[4]),
                        Integer.parseInt(tokens[5]),
                        tokens[6]
                    );
                } else if (category.equals("Microbiological")) {
                    exam = new MicrobiologicalExamination(
                        Integer.parseInt(tokens[0]),
                        tokens[1],
                        Integer.parseInt(tokens[3]),
                        Double.parseDouble(tokens[4]),
                        Integer.parseInt(tokens[5]),
                        tokens[6]
                    );
                } else if (category.equals("Specialized")) {
                    exam = new SpecializedExamination(
                        Integer.parseInt(tokens[0]),
                        tokens[1],
                        Integer.parseInt(tokens[3]),
                        Double.parseDouble(tokens[4]),
                        Integer.parseInt(tokens[5]),
                        tokens[6]
                    );
                }
                if (exam != null) exams.put(exam.getId(), exam);
            }
            reader.close(); // close the file
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    } // loadExams

    // 4
    public void loadAppointments(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim();
                }
                Appointment a = new Appointment(
                    Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    tokens[3],
                    Boolean.parseBoolean(tokens[4])
                );
                appointments.put(a.getAppointmentId(), a);
            }
            reader.close(); // close the file
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    } // loadAppointments

    // -----------------------END--------------------------

    // ------- 4 methods that allow to write in a file for each hashmap ------------

    // 1
    public void saveDoctors(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Doctor d : doctors.values()) {
                writer.write(
                    d.getId() + ","
                    + d.getName() + ","
                    + d.getPhone() + ","
                    + d.getSpecialty() + ","
                    + d.getYears()
                );
                writer.newLine();
            }
            writer.close(); // close the file
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    } // saveDoctors

    // 2
    public void savePatients(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Patient p : patients.values()) {
                writer.write(
                    p.getId() + ","
                    + p.getName() + ","
                    + p.getPhone() + ","
                    + p.getEmail()
                );
                writer.newLine();
            }
            writer.close(); // close the file
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    } // savePatients

    // 3
    public void saveExams(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Exam item : exams.values()) {
                if (item instanceof ImagingExamination) {
                    ImagingExamination im = (ImagingExamination) item;
                    writer.write(
                        im.getId() + ","
                        + im.getExamName() + ","
                        + im.getCategoryName() + ","
                        + im.getMaxSlots() + ","
                        + im.getExamCost() + ","
                        + im.getDoctorId() + ","
                        + im.getMachineType()
                    );
                } else if (item instanceof MicrobiologicalExamination) {
                    MicrobiologicalExamination mic = (MicrobiologicalExamination) item;
                    writer.write(
                        mic.getId() + ","
                        + mic.getExamName() + ","
                        + mic.getCategoryName() + ","
                        + mic.getMaxSlots() + ","
                        + mic.getExamCost() + ","
                        + mic.getDoctorId() + ","
                        + mic.getSampleType()
                    );
                } else if (item instanceof SpecializedExamination) {
                    SpecializedExamination sp = (SpecializedExamination) item;
                    writer.write(
                        sp.getId() + ","
                        + sp.getExamName() + ","
                        + sp.getCategoryName() + ","
                        + sp.getMaxSlots() + ","
                        + sp.getExamCost() + ","
                        + sp.getDoctorId() + ","
                        + sp.getSpecialty()
                    );
                }
                writer.newLine();
            }
            writer.close(); // close the file
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    } // saveExams

    // 4
    public void saveAppointments(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Appointment ap : appointments.values()) {
                writer.write(
                    ap.getAppointmentId() + ","
                    + ap.getPatientId() + ","
                    + ap.getExamId() + ","
                    + ap.getDate() + ","
                    + ap.getFastResults()
                );
                writer.newLine();
            }
            writer.close(); // close the file
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    } // saveAppointments

    // -----------------------END--------------------------

    // ------------ 4 methods to add an Item in a Hashmap --------------

    // 1
    public void addDoctor(Doctor d) {
        doctors.put(d.getId(), d);
    } // addDoctor

    // 2
    public void addPatient(Patient p) {
        patients.put(p.getId(), p);
    } // addPatient

    // 3
    public void addExam(Exam ex) {
        exams.put(ex.getId(), ex);
    } // addExam

    // 4
    public void addAppointment(Appointment ap) {
        appointments.put(ap.getAppointmentId(), ap);
    } // addAppointment

    // -----------------------END--------------------------

    // -------- 4 different methods to display all the Items in a Hashmap -----------

    // 1
    public void showAllDoctors() {
        for (Doctor d : doctors.values()) {
            System.out.println(d);
        }
    } // showAllDoctors

    // 2
    public void showAllPatients() {
        for (Patient p : patients.values()) {
            System.out.println(p);
        }
    } // showAllPatients

    // 3 display all exams (sorted based on the name of the exam)
    public void showAllExams() {
        exams.values().stream()
            .sorted(Comparator.comparing(Exam::getExamName, String.CASE_INSENSITIVE_ORDER))
            .forEach(System.out::println);
    } // showAllExams

    // 4
    public void showAllAppointments() {
        for (Appointment ap : appointments.values()) {
            System.out.println(ap);
        }
    } // showAllAppointments

    // -----------------------END--------------------------

    // -------- methods for doctor ----------------------------

    public void addDoctorFromUser(Scanner in) {
        System.out.println("Enter doctor name: ");
        String name = in.nextLine();

        System.out.println("Enter doctor phone: ");
        int phone = Integer.parseInt(in.nextLine());

        System.out.println("Enter years of experience: ");
        int years = Integer.parseInt(in.nextLine());

        // All specialities that are offered
        System.out.println("Choose speciality: ");
        System.out.println("1. Cardiology");
        System.out.println("2. Radiology");
        System.out.println("3. Neurology");
        int choice = Integer.parseInt(in.nextLine());

        String specialty = "";
        if (choice == 1) specialty = "Cardiology";
        if (choice == 2) specialty = "Radiology";
        if (choice == 3) specialty = "Neurology";

        Doctor d = new Doctor(name, phone, specialty, years);
        addDoctor(d);
        System.out.println("Doctor added successfully!");
    } // addDoctorFromUser

    // ----------------------------1---------------------------------------
    public int doctorHelper(Scanner in) {
        while (true) {
            showAllDoctors(); // the system shows all the doctors
            System.out.println("Enter doctor ID: ");
            int id = Integer.parseInt(in.nextLine());

            Doctor d = doctors.get(id);

            // the system checks if the doctor exists
            if (d == null) {
                System.out.println("Doctor not found!");
                continue; // the id is requested again
            }
            System.out.println("Doctor details: ");
            System.out.println(d);
            return id;
        }
    } // doctorHelper

    // ------------------------------3---------------------
    public void showDoctorDetails(Scanner in) {
        int id = doctorHelper(in);
        for (Exam ex : exams.values()) {
            if (ex.getDoctorId() == id) System.out.println(ex);
        }
    } // showDoctorDetails

    // ------------------------------4-----------------
    public void showDoctorAppointments(Scanner in) {
        int id = doctorHelper(in);
        for (Exam ex : exams.values()) {
            if (ex.getDoctorId() == id) {
                for (Appointment ap : appointments.values()) {
                    if (ap.getExamId() == ex.getId()) {
                        System.out.println(ap);
                    }
                }
            }
        }
    } // showDoctorAppointments

    // ------------------------------------------
    public int patientHelper(Scanner in) {
        while (true) {
            showAllPatients(); // the system shows all the patients
            System.out.println("Enter patient ID: ");
            int id = Integer.parseInt(in.nextLine());

            Patient p = patients.get(id);

            // the system checks if the patient exists
            if (p == null) {
                System.out.println("Patient not found!");
                continue; // the id is requested again
            }
            System.out.println("Patient details: ");
            System.out.println(p);
            return id;
        }
    } // patientHelper

    // --------------------1--------------------
    public void addPatientFromUser(Scanner in) {
        System.out.println("Enter patient name: ");
        String name = in.nextLine();

        System.out.println("Enter patient phone: ");
        int phone = Integer.parseInt(in.nextLine());

        System.out.println("Enter patient email: ");
        String email = in.nextLine();

        Patient p = new Patient(name, phone, email);
        addPatient(p);
        System.out.println("Patient added successfully!");
    } // addPatientFromUser

    // ----------------------3-------------------
    public void showPatientDetails(Scanner in) {
        int id = patientHelper(in);
        System.out.println("Appointments of this patient: ");
        for (Appointment ap : appointments.values()) {
            if (ap.getPatientId() == id) {
                System.out.println(ap);
            }
        }
    } // showPatientDetails

    // ------------------methods for exams----------------------------

    // -----------------1------------------------
    public void addExamFromUser(Scanner in) {
        System.out.println("Enter exam name: ");
        String name = in.nextLine();

        System.out.println("Enter maximum slots per day: ");
        int maxSlots = Integer.parseInt(in.nextLine());

        System.out.println("Enter the cost: ");
        double cost = Double.parseDouble(in.nextLine());

        int doctorId = doctorHelper(in);

        // shows the categories
        System.out.println("Choose exam category: ");
        System.out.println("1. Imaging Examination");
        System.out.println("2. Microbiological Examination");
        System.out.println("3. Specialized Examination");

        int choice1 = Integer.parseInt(in.nextLine());
        Exam ex = null;

        if (choice1 == 1) {
            System.out.println("Choose machine type: ");
            System.out.println("1. MRI");
            System.out.println("2. CT");
            System.out.println("3. X-Ray");
            int choice2 = Integer.parseInt(in.nextLine());
            String extra = "";
            if (choice2 == 1) extra = "MRI";
            if (choice2 == 2) extra = "CT";
            if (choice2 == 3) extra = "X-Ray";
            ex = new ImagingExamination(name, maxSlots, cost, doctorId, extra);
        } else if (choice1 == 2) {
            System.out.println("Choose sample type: ");
            System.out.println("1. Blood");
            System.out.println("2. Urine");
            System.out.println("3. Swab");
            int choice2 = Integer.parseInt(in.nextLine());
            String extra = "";
            if (choice2 == 1) extra = "Blood";
            if (choice2 == 2) extra = "Urine";
            if (choice2 == 3) extra = "Swab";
            ex = new MicrobiologicalExamination(name, maxSlots, cost, doctorId, extra);
        } else if (choice1 == 3) {
            System.out.println("Choose speciality: ");
            System.out.println("1. Cardiology");
            System.out.println("2. Neurology");
            System.out.println("3. Pulmonology");
            int choice2 = Integer.parseInt(in.nextLine());
            String extra = "";
            if (choice2 == 1) extra = "Cardiology";
            if (choice2 == 2) extra = "Neurology";
            if (choice2 == 3) extra = "Pulmonology";
            ex = new SpecializedExamination(name, maxSlots, cost, doctorId, extra);
        }

        // creating the exam object
        if (ex != null) addExam(ex);
        System.out.println("Added the exam successfully!");
    } // addExamFromUser

    // ------------------END----------------------------------------------------

    // collects an exam from the list
    public int examHelper(Scanner in) {
        while (true) {
            showAllExams();
            System.out.println("Enter exam ID from the list: ");
            int id = Integer.parseInt(in.nextLine());
            Exam ex = exams.get(id);

            if (ex == null) {
                System.out.println("Exam not found!");
                continue; // the id is requested again
            }
            System.out.println("Exam details: ");
            System.out.println(ex);
            return id;
        }
    } // examHelper

    // --------------------------shows exam details--------------------
    public void showExamDetails(Scanner in) {
        int id = examHelper(in);
        System.out.println("Appointments for this exam: ");
        for (Appointment ap : appointments.values()) {
            if (ap.getExamId() == id) System.out.println(ap);
        }
    } // showExamDetails

    // --------------END---------------------------------------------------

    // methods for appointments

    public void addAppointmentFromUser(Scanner in) {
        int idPatient = patientHelper(in);
        int idExam = examHelper(in);
        Exam ex = exams.get(idExam); //!
        String date = "";
        while (true) {
            System.out.println("Enter date (DD:MM:YYYY): ");
            date = in.nextLine();

            if (!date.matches("\\d{2}:\\d{2}:\\d{4}")) {
                System.out.println("Invalid date format!");
                continue;
            }
            // controls if there is any appointment available on a specific date
            int count = 0;
            for (Appointment ap : appointments.values()) {
                if (ap.getExamId() == idExam && ap.getDate().equals(date)) {
                    count++;
                }
            }
            if (count >= ex.getMaxSlots()) {
                System.out.println("No available slots for this date.");
                continue;
            }
            break;
        }
        System.out.println("Fast results? (yes / no): ");
        boolean fast = in.nextLine().equalsIgnoreCase("yes");
        Appointment ap = new Appointment(idPatient, idExam, date, fast);
        addAppointment(ap);
        System.out.println("Appointment added successfully!");
    } // addAppointmentFromUser

    // -------------------------END-----------------------------

    // to delete an appointment
    public void deleteAppointment(Scanner in) {
        int id = 0;
        Appointment appointment = null;
        while (true) {
            showAllAppointments();
            System.out.println("Enter appointment ID : ");
            id = Integer.parseInt(in.nextLine());
            appointment = appointments.get(id);
            if (appointment == null) {
                System.out.println("Appointment not found.");
                continue;
            }
            break;
        }
        System.out.println("Are you sure you want to delete this appointment? (yes / no): ");
        boolean answer = in.nextLine().equalsIgnoreCase("yes");
        if (answer) {
            appointments.remove(id);
            System.out.println("Appointment deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    } // deleteAppointment

    // show appointments based on a date
    public void showAppointmentsByDate(Scanner in) {
        String date;
        while (true) {
            System.out.println("Enter date (DD:MM:YYYY): ");
            date = in.nextLine();

            if (!date.matches("\\d{2}:\\d{2}:\\d{4}")) {
                System.out.println("Invalid date format! Try again.");
                continue;
            }
            break;
        }
        System.out.println("Appointments for date: " + date);
        for (Appointment appointment : appointments.values()) {
            if (appointment.getDate().equals(date)) {
                Patient patient = patients.get(appointment.getPatientId());
                Exam exam = exams.get(appointment.getExamId());
                System.out.println(appointment.toString() + " | Patient Name: " + patient.getName()
                    + " | Exam Name: " + exam.getExamName());
            }
        }
    } // showAppointmentsByDate

    // --------------------END---------------------------------------

    public void showPatientAppointments(Scanner in) {
        int id = patientHelper(in);
        System.out.println("Appointments for patient ID: " + id);
        for (Appointment ap : appointments.values()) {
            if (ap.getPatientId() == id) System.out.println(ap); 
        }
    } // showPatientAppointments

    // revenue per patient
    public void revenuePerPatient() {
        double total = 0;
        for (Patient patient: patients.values()) {
            double patientRevenue = 0;
            System.out.println("\nPatient: " + patient.getName());
            for (Appointment appointment : appointments.values()) {
                if (appointment.getPatientId() == patient.getId()) {
                    Exam exam = exams.get(appointment.getExamId());
                    double cost = exam.getCost(appointment.getFastResults());
                    System.out.println(appointment + " | Cost: " + cost);
                    patientRevenue += cost;
                }
            }
       
        System.out.println("Total revenue from patient: " + patientRevenue);
        total += patientRevenue;
        }
    
    System.out.println("\nTotal revenue from all patients: " + total);
} // revenuePerPatient

// revenue per exam
    public void revenuePerExam() {
        
        double total = 0;
        for (Exam exam : exams.values()) {
            double examRevenue = 0;
            System.out.println("\nExam: " + exam.getExamName());
            for (Appointment appointment : appointments.values()) {
                if (appointment.getExamId() == exam.getId()) {
                    double cost = exam.getCost(appointment.getFastResults());
                    System.out.println(appointment + " | Cost: " + cost);
                    examRevenue += cost;
                }
            }
            System.out.println("Total revenue from exam: " + examRevenue);
            total+= examRevenue;
        }
        
        System.out.println("\nTotal revenue from all exams: " + total);
    } // revenuePerExam

    // revenue per exam category
    public void revenuePerCategory() {
        
        double total = 0;
        String[] categories = {"Imaging", "Microbiological", "Specialized"};
        for (String category : categories) {
            double categoryRevenue = 0;
            System.out.println("\nCategory: " + category);
            for (Exam exam : exams.values()) {
                if (exam.getCategoryName().equals(category)) {
                    for (Appointment appointment : appointments.values()) {
                        if (appointment.getExamId() == exam.getId()) {
                            double cost = exam.getCost(appointment.getFastResults());
                            System.out.println(appointment + " | Cost: " + cost);
                            categoryRevenue += cost;
                        }
                    }
                }
            }
            
            System.out.println("Total revenue from category: " + categoryRevenue);
            total += categoryRevenue;
        }
        System.out.println("\nTotal revenue from all categories: " + total);
        } 
}
