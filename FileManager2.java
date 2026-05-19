import java.util.*;
import java.io.*;
class FileManager {
    //Creating four hashmaps
    HashMap<Integer, Doctor> doctors = new HashMap<>();
    HashMap<Integer, Patient> patients = new HashMap<>();
    HashMap<Integer, Exam> exams = new HashMap<>();
    HashMap<Integer, Appointment> appointments = new HashMap<>();
    //Creating 4 methods for loading items from files to hashmaps
    public void loadDoctors(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                for (int i = 0; i<tokens.length; i++) tokens[i]= tokens[i].trim();
                Doctor d = new Doctor(Integer.parseInt(tokens[0]),
                                    tokens[1],
                                    Integer.parseInt(tokens[2]),
                                    tokens[3],
                                    Integer.parseInt(tokens[4]));
                doctors.put(d.getId(),d);
            }
            reader.close(); //Closing the file
        }
        catch (IOException e){ System.err.println("Error while reading file: " + e.getMessage());}
    } 

    public void loadPatients(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                for (int i = 0; i<tokens.length; i++) tokens[i] = tokens[i].trim();
                Patient p=new Patient(Integer.parseInt(tokens[0]), tokens[1],
                                      Integer.parseInt(tokens[2]), tokens[3]);
                patients.put(p.getId(),p);
            }
            reader.close(); //Closing the file
        }
        catch(IOException e) {System.err.println("Error while reading file: " + e.getMessage());}
    }

    public void loadExams(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                for (int i = 0; i < tokens.length; i++ ) tokens[i] = tokens[i].trim();
                String category = tokens[2];
                Exam e = null;
                if (category.equals("Imaging")){
                    e = new ImagingExamination(Integer.parseInt(tokens[0]), tokens[1],
                                               Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4]),
                                               Integer.parseInt(tokens[5]), tokens[6]);
                } else if (category.equals("Microbiological")){
                    e = new MicrobiologicalExamination(Integer.parseInt(tokens[0]), tokens[1],
                                                       Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4]),
                                                       Integer.parseInt (tokens[5]), tokens[6]
                    );
                } else if (category.equals("Specialized")){
                    e=new SpecializedExamination(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[3]),
                                            Double.parseDouble(tokens[4]), Integer.parseInt(tokens[5]), tokens[6]);
                }
                if (e != null) exams.put(e.getId(),e);
            }
        } catch (IOException e) {System.err.println("Error while reading file: " + e.getMessage());}
    }

    public void loadAppointments(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] tokens =line.split(",");
                for (int i = 0; i<tokens.length; i++)tokens[i]= tokens[i].trim();
                Appointment ap = new Appointment(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
                                                Integer.parseInt(tokens[2]), tokens[3], Boolean.parseBoolean(tokens[4]));
                appointments.put(ap.getAppointmentId(),ap);
            }
            reader.close(); //Closing the file
        } catch (IOException e) {System.err.println("Error while reading file: " + e.getMessage());}
    }
    //-------------------------------------------------------------------------------------------------------------
    //Creating 4 methods for saving items from hashmaps to files
    public void saveDoctors(String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (Doctor d:doctors.values()){
                writer.write(d.getId() + ","
                            + d.getName() + "," + d.getPhone() + "," 
                            + d.getSpecialty() + "," + d.getYears());
                writer.newLine();
            }
            writer.close(); 
        } catch (IOException e) {System.err.println("Error while writing to file: " + e.getMessage());}
    }

    public void savePatients(String filePath){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (Patient p: patients.values()){
                writer.write(p.getId() + "," + p.getName() + "," + p.getPhone() + "," + p.getEmail());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {System.err.println("Error while writing to file: " + e.getMessage());}
    }

    public void saveExams(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Exam e: exams.values()){
                if (e instanceof ImagingExamination){
                    ImagingExamination im = (ImagingExamination) e;
                    writer.write(im.getId() + "," + im.getExamName() + "," + "Imaging" + "," + im.getMaxSlots() + "," 
                                + im.getExamCost() + "," + im.getDoctorId() +"," + im.getMachineType());
                } else if (e instanceof MicrobiologicalExamination){
                    MicrobiologicalExamination mic = (MicrobiologicalExamination) e;
                    writer.write(mic.getId() + "," + mic.getExamName() + "," 
                                + "Microbiological" + "," + mic.getMaxSlots() + "," 
                                + mic.getExamCost() + "," + mic.getDoctorId() +"," + mic.getSampleType());
                } else if (e instanceof SpecializedExamination){
                    SpecializedExamination sp = (SpecializedExamination) e;
                    writer.write(sp.getId() + "," + sp.getExamName() + "," 
                                + "Specialized" + "," + sp.getMaxSlots() + "," 
                                + sp.getExamCost() + "," + sp.getDoctorId() +"," + sp.getSpecialty());
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {System.err.println("Error while writing to file: " + e.getMessage());}
    }

    public void saveAppointments(String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (Appointment ap: appointments.values()){
                writer.write(ap.getAppointmentId() + "," + ap.getPatientId() + "," + ap.getExamId() + "," + ap.getDate() + "," + ap.getFastResults());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {System.err.println("Error while writing to file: " + e.getMessage());}
    }

    //-------------------------------------------------------------------------------------------------------------
    //Creating methods for adding items to hashmaps 
    public void addDoctor(Doctor d) {
        doctors.put(d.getId(),d);
    }

    public void addPatient(Patient p){
        patients.put(p.getId(), p);
    }

    public void addExam(Exam e) {
        exams.put(e.getId(), e);
    }

    public void addAppointment(Appointment ap){
        appointments.put(ap.getAppointmentId(),ap);
    }

    //-------------------------------------------------------------------------------------------------------------
    //Creating methods for displaying all the items from a hashmap
    public void showAllDoctors(){
        for (Doctor d: doctors.values()) System.out.println(d);
    }

    public void showAllPatients(){ 
        for(Patient p:patients.values()) System.out.println(p);
    }

     // display all exams (sorted based on the name of the exam)
    public void showAllExams(){
        exams.values().stream()
            .sorted(Comparator.comparing(Exam::getExamName, String.CASE_INSENSITIVE_ORDER))
            .forEach(System.out::println);
    }

    public void showAllAppointments(){
        for (Appointment ap: appointments.values())System.out.println(ap);
    }

    //-------------------------------------------------------------------------------------------------------------
    //methods for doctor
    public void addDoctorFromUser(Scanner in) {
        System.out.println("Enter doctor name: ");
        String name = in.nextLine();
        System.out.println("Enter doctor phone: ");
        int phone= Integer.parseInt(in.nextLine());
        System.out.println("Enter doctor's years of experience: ");
        int years = Integer.parseInt(in.nextLine());
        System.out.println("Choose specialty:");
        String specialty = in.nextLine();

        Doctor d = new Doctor(name, phone, specialty, years);
        addDoctor(d);
        System.out.println("Doctor was added successfully.");
    }

    public int doctorHelper(Scanner in){
        while(true){
            showAllDoctors();
            System.out.println("Enter Doctor id: ");
            int id = Integer.parseInt(in.nextLine());
            Doctor d = doctors.get(id);
            //the system checks if the doctor exists
            if (d == null){
                System.out.println("Doctor found");
                continue; //id input required again
            }
            System.out.println("Doctor found: " + d);
            return id;
        }
    }

    public void showDoctorDetails(Scanner in){
        int id = doctorHelper(in);
        for (Exam e: exams.values()) if (e.getDoctorId() == id) System.out.println(e);
    }

    public void showDoctorAppointments(Scanner in){
        int id = doctorHelper(in);
        for (Exam e: exams.values()) {
            if (e.getDoctorId() == id) {
                for (Appointment ap: appointments.values()) if (ap.getExamId() == e.getId()) System.out.println(ap);
            }
        }
    }

    //-------------------------------------------------------------------------------------------------------------
    //methods for patient
    public void addPatientFromUser(Scanner in){
        System.out.println("Enter patient name: ");
        String name = in.nextLine();
        System.out.println("Enter patient phone: ");
        int phone = Integer.parseInt(in.nextLine());
        System.out.println("Enter patient email: ");
        String email = in.nextLine();

        Patient p = new Patient(name, phone, email);
        addPatient(p);
        System.out.println("Patient was added successfully.");
    }

    public int patientHelper(Scanner in){
        while(true){
            showAllPatients();
            System.out.println("Enter patient id: ");
            int id = Integer.parseInt(in.nextLine());
            Patient p = patients.get(id);
            //the system checks if the patient exists
            if (p == null){
                System.out.println("Patient not found");
                continue; //id input required again
            }
            System.out.println("Patient found: " + p);
            return id;
        }
    }

    public void showPatientDetails(Scanner in){
        int id = patientHelper(in);
        System.out.println("Appointments for this patient: ");
        for (Appointment ap: appointments.values()) if (ap.getPatientId() == id) System.out.println(ap);
    }

    //-------------------------------------------------------------------------------------------------------------
    //methods for exams
    public void addExamFromUser (Scanner in) {
        System.out.println("Enter exam name: ");
        String name = in.nextLine();
        System.out.println("Enter max slots per day: ");
        int maxSlots = Integer.parseInt(in.nextLine());
        System.out.println("Enter cost: ");
        double cost = Double.parseDouble(in.nextLine());
        int doctorId = doctorHelper(in);
        System.out.println("Pick exam category:");
        System.out.println("1. Imaging");
        System.out.println("2. Microbiological");
        System.out.println("3. Specialized");
        int choice = Integer.parseInt(in.nextLine());
        Exam ex = null;
        switch (choice){
            case 1:
                System.out.println("Choose machine type:");
                System.out.println("1. MRI");
                System.out.println("2. CT");
                System.out.println("3. X-Ray");
                int choice2 = Integer.parseInt(in.nextLine());
                String im =null;
                if (choice2 == 1) im= "MRI";
                else if (choice2 == 2) im ="CT";
                else if (choice2 == 3) im = "X-Ray";
                ex = new ImagingExamination(name, maxSlots, cost, doctorId, im);
            case 2:
                System.out.println("Choose sample:");
                System.out.println("1. Blood");
                System.out.println("2. Urine");
                System.out.println("3. Swab");
                int choice3 = Integer.parseInt(in.nextLine());
                String sample = null;
                if (choice3 == 1) sample="Blood";
                else if (choice3 ==2) sample ="Urine";
                else if (choice3==3) sample= "Swab";
                ex =new MicrobiologicalExamination(name,maxSlots, cost, doctorId,sample);
            case 3:
                System.out.println("Choose specialty:");
                System.out.println("1. Cardiology");
                System.out.println("2. Neurology");
                System.out.println("3. Pulmonology");
                int choice4 = Integer.parseInt(in.nextLine());
                String specialty = null;
                if (choice4 == 1) specialty = "Cardiology";
                else if (choice4 == 2)specialty = "Neurology";
                else if (choice4==3) specialty= "Pulmonology";
                ex = new SpecializedExamination(name, maxSlots,cost, doctorId,specialty);
        }
        if (ex !=null) addExam(ex);
        System.out.println("Added the exam successfully");
    }

    public int examHelper(Scanner in) {
        while(true){
            showAllExams();
            System.out.println("Enter exam id: ");
            int id =Integer.parseInt(in.nextLine());
            Exam ex=exams.get(id);
            if (ex ==null) {
                System.out.println("Could not find exam");
                continue; //id input required again
            }
            System.out.println("Exam found: " + ex);
            return id;      
        }
    }

    public void showExamDetails(Scanner in){
        int id =examHelper(in);
        System.out.println("Appointments for this exam: ");
        for(Appointment ap:appointments.values()) if (ap.getExamId() == id) System.out.println(ap);
    }

    //-------------------------------------------------------------------------------------------------------------
    //methods for appointments
    public void addAppointmentFromUser(Scanner in) {
        int idPatient = patientHelper(in);
        int idExam= examHelper(in);
        Exam ex = exams.get(idExam);
        String date;
        while(true){
            System.out.println("Enter date (DD:MM:YYYY): ");
            date = in.nextLine();
            if (!date.matches("\\d{2}:\\d{2}:\\d{4}")){
                System.out.println("Invalid format please enter the date again ");
                continue;
            }
            int count = 0;
            for (Appointment ap:appointments.values()) if (ap.getExamId() ==idExam && ap.getDate().equals(date)) count++;
            if (count>= ex.getMaxSlots()){
                System.out.println("No available appointments. Choose a different date");
                continue;
            }
            break;
        }
        System.out.println("Fast results? (Yes/No):");
        boolean fastResults = in.nextLine().equalsIgnoreCase("yes");
        Appointment ap = new Appointment(idPatient, idExam, date, fastResults ) ;
        addAppointment(ap);
        System.out.println("Appointment added successfully");
    }

    public void deleteAppointment(Scanner in){
        int id = 0;
        Appointment ap= null;
        while (true) {
            showAllAppointments();
            System.out.println("Enter appointment id:");
            id = Integer.parseInt(in.nextLine());
            ap = appointments.get(id);
            if(ap==null) {
                System.out.println("Could not find appointment");
                continue; //id input required again
            }
            break;
        }
        System.out.println("Are you sure you want to delete the appointment? (Yes/No): ");
        boolean answer = in.nextLine().equalsIgnoreCase("yes");
        if (answer){
            appointments.remove(id);
            System.out.println("Appointment deleted");
        } else System.out.println("Deletion cancelled");
    }

    public void showAppointmentsByDate(Scanner in){
        String date;
        while(true) {
            System.out.println("Enter date (DD:MM:YYYY): ");
            date = in.nextLine();
            if (!date.matches("\\d{2}:\\d{2}:\\d{4}")){
                System.out.println("Invalid format please enter the date again ");
                continue;
            }
            break;
        }
        System.out.println("Appointments for " + date );
        for (Appointment appointment : appointments.values()){
            if (appointment.getDate().equals(date)) {
                Patient p=patients.get(appointment.getPatientId());
                Exam ex = exams.get(appointment.getExamId());
                System.out.println(appointment.toString() + " | Patient Name: " + p.getName() + " | Exam Name: " + ex.getExamName());
            }
        }
    }

    public void showPatientAppointments(Scanner in){
        int id =patientHelper(in);
        System.out.println("Appointments for this patient: ");
        for(Appointment ap:appointments.values()) if(ap.getPatientId()==id) System.out.println(ap);
    }

    //-------------------------------------------------------------------------------------------------------------
    //methods to calculate the statistics
    public void revenuePerPatient(){
        double total= 0;
        for (Patient p: patients.values()){
            double patientTotal = 0;
            System.out.println("Patient: " + p.getName());
            for(Appointment ap:appointments.values()){
                if (ap.getPatientId() == p.getId()) {
                    Exam exam = exams.get(ap.getExamId());
                    double cost = exam.getCost(ap.getFastResults());
                    System.out.println(ap + " | Cost: " + cost);
                    patientTotal += cost;
                }
            }
            System.out.println("Total revenue for " + p.getName() + ": " + patientTotal);
            total +=patientTotal;
        }
        System.out.println("Total revenue: " + total);
    }

    public void revenuePerExam(){
        double total =0;
        for (Exam ex: exams.values()){
            double examTotal = 0;
            System.out.println("Exam: "+ex.getExamName());
            for(Appointment ap:  appointments.values()){
                if (ap.getExamId() == ex.getId()){
                    double cost = ex.getCost(ap.getFastResults());
                    System.out.println(ap + " | Cost: " + cost);
                    examTotal += cost;
                }
            }
            System.out.println("Total revenue for " + ex.getExamName()+": "+ examTotal);
            total += examTotal;
        }
        System.out.println("Total revenue: " + total);
    }

    public void revenuePerCategory() {
        double total= 0;
        String[] categories ={"Imaging","Microbiological","Specialized"};
        for(String c: categories){
            double categoryTotal =0;
            System.out.println("Category: " + c);
            for (Exam ex: exams.values()){
                if (ex.getCategoryName().equals(c)){
                    for(Appointment ap: appointments.values()){
                        if (ap.getExamId() == ex.getId()){
                            double cost= ex.getCost(ap.getFastResults());
                            System.out.println(ap+" | Cost: " + cost);
                            categoryTotal +=cost ;
                        }
                    }
                }
            }
            System.out.println("Total revenue for " + c + ": " + categoryTotal) ;
            total+= categoryTotal;
        }
        System.out.println("Total revenue: "+ total);
    }
}
