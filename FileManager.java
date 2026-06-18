

import java.util.*;
import java.io.*;
public class FileManager {
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
                String[] tokens = line.split(",");// devide the line in words every time ts sees " , " and then it creates an array with the words  
                for (int i = 0; i<tokens.length; i++) tokens[i]= tokens[i].trim();// deletes the spaces that might exist in a word
                Doctor d = new Doctor(Integer.parseInt(tokens[0]),//0=Id
                                    tokens[1],//1=name
                                    Integer.parseInt(tokens[2]),// 2=phone
                                    tokens[3],// 3=speciality
                                    Integer.parseInt(tokens[4]));//4=years of experience
                doctors.put(d.getId(),d);// it adds the doctor in the hashmap
            }
            reader.close(); //Closing the file
        }
        catch (IOException e){ System.err.println("Error while reading file: " + e.getMessage());}
    } //----------------------------------------------------------------------------------------------------------------------------------
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
    }//-----------------------------------------------------------------------------------------------------------------
    public void loadExams(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                for (int i = 0; i < tokens.length; i++ ) tokens[i] = tokens[i].trim();
                String category = tokens[2];
                Exam e = null; 
                    
                if (category.equals("Imaging")){
                    e = new ImagingExamination(Integer.parseInt(tokens[0]), tokens[1],//0=id,1=examName
                                               Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4]),//3=maxslots,4=cost
                                               Integer.parseInt(tokens[5]), tokens[6]);//5=doctorID,6=machineType
                } else if (category.equals("Microbiological")){
                    e = new MicrobiologicalExamination(Integer.parseInt(tokens[0]), tokens[1],//0=id,1=examName
                                                       Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4]),//3=maxslots,4=cost
                                                       Integer.parseInt (tokens[5]), tokens[6]//5= doctorID   ,6=sampleType
                    );
                } else if (category.equals("Specialized")){
                    e=new SpecializedExamination(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[3]),//0=id,1=examName,3=maxslots
                                            Double.parseDouble(tokens[4]), Integer.parseInt(tokens[5]), tokens[6]);//4=cost,5= doctorID   ,6=specialty
                }
                exams.put(e.getId(),e);// κατευθειαν βαζουμε χωρισ ιφ τοτε
                }
            } catch (IOException e) {System.err.println("Error while reading file: " + e.getMessage());}
    }//-----------------------------------------------------------------------------------------------------------------------------
    public void loadAppointments(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] tokens =line.split(",");
                for (int i = 0; i<tokens.length; i++)tokens[i]= tokens[i].trim();
                Appointment ap = new Appointment(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),//0=id,1=patientId
                                                Integer.parseInt(tokens[2]), tokens[3], Boolean.parseBoolean(tokens[4]));//2=examId,3=date,4=fastResults
                appointments.put(ap.getAppointmentId(),ap);
            }
            reader.close(); //Closing the file
        } catch (IOException e) {System.err.println("Error while reading file: " + e.getMessage());}
    }
    //-------------------------------------------------------------------------------------------------------------------------------
    //Creating 4 methods for saving items from hashmaps to files
    public void saveDoctors(String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (Doctor d:doctors.values()){
                writer.write(d.getId() + ","
                            + d.getName() + "," + d.getPhone() + "," 
                            + d.getSpecialty() + "," + d.getYears());
                writer.newLine();//we change the line for the next time we want to write somethig else in the file 
            }
            writer.close(); 
        } catch (IOException e) {System.err.println("Error while writing to file: " + e.getMessage());}
    }

    public void savePatients(String filePath){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (Patient p: patients.values()){
                writer.write(p.getId() + "," + p.getName() + "," + p.getPhone() + "," + p.getEmail());
                writer.newLine();//we change the line for the next time we want to write somethig else in the file 
            }
            writer.close();
        } catch (IOException e) {System.err.println("Error while writing to file: " + e.getMessage());}
    }//-----------------------------------------------------------------------------------------------------
    public void saveExams(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Exam e: exams.values()){// e has sees only the methods from the superclass
                if (e instanceof ImagingExamination){
                    ImagingExamination im = (ImagingExamination) e;//downcasting in order to have access on the methods of the subclass
                    writer.write(im.getId() + "," + im.getExamName() + "," + "Imaging" + "," + im.getMaxSlots() + "," 
                                + im.getExamCost() + "," + im.getDoctorId() +"," + im.getMachineType());
                } else if (e instanceof MicrobiologicalExamination){
                    MicrobiologicalExamination mic = (MicrobiologicalExamination) e;//downcasting
                    writer.write(mic.getId() + "," + mic.getExamName() + "," 
                                + "Microbiological" + "," + mic.getMaxSlots() + "," 
                                + mic.getExamCost() + "," + mic.getDoctorId() +"," + mic.getSampleType());
                } else if (e instanceof SpecializedExamination){
                    SpecializedExamination sp = (SpecializedExamination) e;//downcasting
                    writer.write(sp.getId() + "," + sp.getExamName() + "," 
                                + "Specialized" + "," + sp.getMaxSlots() + "," 
                                + sp.getExamCost() + "," + sp.getDoctorId() +"," + sp.getSpecialty());
                }
                writer.newLine();//we change the line for the next time we want to write somethig else in the file 
            }
            writer.close();
        } catch (IOException e) {System.err.println("Error while writing to file: " + e.getMessage());}
    }//----------------------------------------------------------------------------------------------------
    public void saveAppointments(String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (Appointment ap: appointments.values()){
                writer.write(ap.getAppointmentId() + "," + ap.getPatientId() + "," + ap.getExamId() + "," + ap.getDate() + "," + ap.getFastResults());
                writer.newLine();//we change the line for the next time we want to write somethig else in the file 
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
        System.out.print("\nEnter doctor name: ");
        String name = in.nextLine();
        System.out.print("\nEnter doctor phone: ");
        int phone= Integer.parseInt(in.nextLine());
        System.out.print("\nEnter doctor's years of experience: ");
        int years = Integer.parseInt(in.nextLine());
        
        String specialty ="";
        while(specialty.isEmpty()){
            System.out.println("Choose specialty:");
            System.out.println("1. Cardiology");
            System.out.println("2. Neurology");
            System.out.println("3. Pulmonology"); 
            System.out.println("4. Other");
           
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 1) specialty = "Cardiology";
            else if (choice == 2) specialty ="Neurology";
            else if (choice == 3) specialty = "Pulmonology";
            else if(choice == 4) {
                System.out.print("Enter specialty: ");
                specialty = in.nextLine();
            } else System.out.println("Invalid choice. Choose again.\n");
        }
        Doctor d = new Doctor(name, phone, specialty, years);
        addDoctor(d);
        System.out.println("Doctor was added successfully.");
    }

    public int doctorHelper(Scanner in){
        while(true){
            showAllDoctors();//the system shows all doctors
            System.out.print("\nEnter Doctor id: ");//then the user has to choose 
            int id = Integer.parseInt(in.nextLine());
            Doctor d = doctors.get(id);
            //the system checks if the doctor exists
            if (d == null){
                System.out.println("\nDoctor not found");
                continue; //id input required again
            }
            System.out.println("\nDoctor found: " + d+"\n");
            return id;
        }
    }

    public void showDoctorDetails(Scanner in){
        int id = doctorHelper(in);
        boolean found = false;
        for (Exam e: exams.values()) {
            if (e.getDoctorId() == id) {
                System.out.println(e);
                found = true;
            }
        } 
        if (!found) System.out.println("\nNo exam offered by this doctor\n");
    }

    public void showDoctorAppointments(Scanner in){
        int id = doctorHelper(in);
        boolean found=false;
        boolean found2;
        for (Exam e: exams.values()) {
            if (e.getDoctorId() == id) {
                found2 = false;
                System.out.println(e);
                found = true;
                for (Appointment ap: appointments.values()) {
                    if (ap.getExamId() == e.getId()) {
                        System.out.println(ap);
                        found2=true;
                    }
                }
                if (!found2) System.out.println("\nNo appointments for this exam: " + e.getExamName() + "\n");
            }
            
        }
        if (!found) System.out.println("\nNo exam offered by this doctor\n");
    }

    //-------------------------------------------------------------------------------------------------------------
    //methods for patient
    public void addPatientFromUser(Scanner in){
        System.out.print("\nEnter patient name: ");
        String name = in.nextLine();
        System.out.print("\nEnter patient phone: ");
        int phone = Integer.parseInt(in.nextLine());
        System.out.print("\nEnter patient email: ");
        String email = in.nextLine();

        Patient p = new Patient(name, phone, email);
        addPatient(p);
        System.out.println("\nPatient was added successfully.");
    }

    public int patientHelper(Scanner in){
        while(true){
            showAllPatients();//the system shows all patients
            System.out.print("\nEnter patient id: ");//the user has to choose
            int id = Integer.parseInt(in.nextLine());
            Patient p = patients.get(id);
            //the system checks if the patient exists
            if (p == null){
                System.out.println("\nPatient not found");
                continue; //id input required again
            }
            System.out.println("\nPatient found: " + p +"\n");
            return id;
        }
    }

    public void showPatientDetails(Scanner in){
        int id = patientHelper(in);
        System.out.println("\nAppointments for this patient: \n");
        boolean found = false;
        for (Appointment ap: appointments.values()) {
            if (ap.getPatientId() == id) {
                System.out.println(ap);
                found = true;
            } 
        }

        if (!found) System.out.println("\nNo appointments found for this patient\n");
    }
    //-------------------------------------------------------------------------------------------------------------
    //methods for exams
    public void addExamFromUser (Scanner in) {
        System.out.print("\nEnter exam name: ");
        String name = in.nextLine();
        System.out.print("\nEnter max slots per day: ");
        int maxSlots = Integer.parseInt(in.nextLine());
        System.out.print("\nEnter cost: ");
        double cost = Double.parseDouble(in.nextLine());
        int doctorId = doctorHelper(in);
        Exam ex = null;//will hold the created exam object
        while (ex==null){//loop until the user selects a valid exam category and details
        System.out.println("Pick exam category:");
        System.out.println("1. Imaging");
        System.out.println("2. Microbiological");
        System.out.println("3. Specialized");
        int choice = Integer.parseInt(in.nextLine());
        switch (choice){
            case 1:
                System.out.println("Choose machine type:");
                System.out.println("1. MRI");
                System.out.println("2. CT");
                System.out.println("3. X-Ray");
                System.out.println("4. Other");
                int choice2 = Integer.parseInt(in.nextLine());
                String im="";
                if (choice2 == 1) im= "MRI";
                else if (choice2 == 2) im ="CT";
                else if (choice2 == 3) im = "X-Ray";
                else if (choice2 ==4) {
                    System.out.print("Enter machine type: ");
                    im = in.nextLine();
                }
                ex = new ImagingExamination(name, maxSlots, cost, doctorId, im);
                break;
            case 2:
                System.out.println("Choose sample:");
                System.out.println("1. Blood");
                System.out.println("2. Urine");
                System.out.println("3. Swab");
                System.out.println("4. Other");
                int choice3 = Integer.parseInt(in.nextLine());
                String sample ="";
                if (choice3 == 1) sample="Blood";
                else if (choice3 ==2) sample ="Urine";
                else if (choice3==3) sample= "Swab";
                else if (choice3==4) {
                    System.out.print("Enter sample type: ");
                    sample =in.nextLine();
                } else System.out.println("Invalid choice. Choose again.\n");
                ex =new MicrobiologicalExamination(name,maxSlots, cost, doctorId,sample);
                break;
            case 3:
                System.out.println("Choose specialty:");
                System.out.println("1. Cardiology");
                System.out.println("2. Neurology");
                System.out.println("3. Pulmonology");
                System.out.println("4. Other");
                int choice4 = Integer.parseInt(in.nextLine());
                String specialty = "";
                if (choice4 == 1) specialty = "Cardiology";
                else if (choice4 == 2)specialty = "Neurology";
                else if (choice4==3) specialty= "Pulmonology";
                else if (choice4==4) {
                    System.out.print("Enter specialty: ");
                    specialty =in.nextLine();
                } else System.out.println("Invalid choice. Choose again.\n");
                ex = new SpecializedExamination(name, maxSlots,cost, doctorId,specialty);
                break;
            }
            if (ex==null) System.out.println("Invalid choice"); 
        }  
        addExam(ex);
        System.out.println("Added the exam successfully");
        }
    //----------------------------------------------------------------------
    public int examHelper(Scanner in) {
        while(true){
            showAllExams();
            System.out.print("\nEnter exam id: ");
            int id =Integer.parseInt(in.nextLine());
            Exam ex=exams.get(id);
            if (ex ==null) {
                System.out.println("\nCould not find exam");
                continue; //id input required again
            }
            System.out.println("\nExam found: " + ex +"\n");
            return id;      
        }
    }//------------------------------------------------------------------------
    public void showExamDetails(Scanner in){
        int id =examHelper(in);
        System.out.println("\nAppointments for this exam:\n");
        boolean found = false;
        for(Appointment ap:appointments.values()) {
            if (ap.getExamId() == id) {
                System.out.println(ap);
                found = true;
            }
        }
        if (!found) System.out.println("\nNo appointments found for this exam\n");
    }
    //-------------------------------------------------------------------------------------------------------------
    //methods for appointments
    public void addAppointmentFromUser(Scanner in) {
        int idPatient = patientHelper(in);
        int idExam= examHelper(in);
        Exam ex = exams.get(idExam);
        String date;
        while(true){
            System.out.print("Enter date (DD:MM:YYYY): ");
            date = in.nextLine();
            if (!date.matches("\\d{2}:\\d{2}:\\d{4}")){
                System.out.println("Invalid format please enter the date again ");
                continue;
            }
            int count = 0;//controls if there is any appointment available in an specific date 
            for (Appointment ap:appointments.values()) if (ap.getExamId() ==idExam && ap.getDate().equals(date)) count++;
            if (count>= ex.getMaxSlots()){
                System.out.println("\nNo available appointments. Choose a different date\n");
                continue;
            }
            break;
        }
        System.out.println("Fast results? (Yes/No):");
        boolean fastResults = in.nextLine().equalsIgnoreCase("yes");
        Appointment ap = new Appointment(idPatient, idExam, date, fastResults ) ;
        addAppointment(ap);
        System.out.println("\nAppointment added successfully\n");
    }//----------------------------------------------------------------------------
    public void deleteAppointment(Scanner in){
        Appointment ap= null;
        int id;
        while (true) {
            showAllAppointments();
            System.out.print("\nEnter appointment id:");
            id = Integer.parseInt(in.nextLine());
            ap = appointments.get(id);
            if(ap==null) {
                System.out.println("Could not find appointment");
                continue; //id input required again
            }
            break;
        }
        System.out.println("\nAre you sure you want to delete the appointment? (Yes/No): ");
        boolean answer = in.nextLine().equalsIgnoreCase("yes");
        if (answer){
            appointments.remove(id);
            System.out.println("Appointment deleted");
        } else System.out.println("Deletion cancelled");
    }

    public void showAppointmentsByDate(Scanner in){
        String date;
        while(true) {
            System.out.print("Enter date (DD:MM:YYYY): ");
            date = in.nextLine();
            if (!date.matches("\\d{2}:\\d{2}:\\d{4}")){
                System.out.println("\nInvalid format please enter the date again ");
                continue;
            }
            break;
        }
        System.out.println("\nAppointments for " + date +"\n" );
        for (Appointment appointment : appointments.values()){
            if (appointment.getDate().equals(date)) {
                Patient p=patients.get(appointment.getPatientId());
                Exam ex = exams.get(appointment.getExamId());
                System.out.println("\n"+ appointment.toString() + " | Patient Name: " + p.getName() + " | Exam Name: "+ ex.getExamName()+"\n");
            }
        }
    }

    //-------------------------------------------------------------------------------------------------------------
    //methods to calculate the statistics
    public void revenuePerPatient(){
        double total= 0;
        for (Patient p: patients.values()){
            double patientTotal = 0;// revenue for specific patient
            System.out.println("\nPatient: " + p.getName());
            for(Appointment ap:appointments.values()){//check all appointments to find those belonging to this patient 
                if (ap.getPatientId() == p.getId()) {
                    Exam exam = exams.get(ap.getExamId());// retrieve the exam linked to the appointment
                    double cost = exam.getCost(ap.getFastResults());// calculate cost
                    System.out.println(ap + " | Cost: " + cost);//print appointment details 
                    patientTotal += cost;
                }
            }
            System.out.println("\nTotal revenue for " + p.getName() + ": " + patientTotal);
            total +=patientTotal;
        }
        System.out.println("\nTotal revenue: " + total);
    }
    //----------------------------------------------------------------------------------------------
    public void revenuePerExam(){
        double total =0;
        for (Exam ex: exams.values()){
            double examTotal = 0;//revenue generated by this specific exam
            System.out.println("\nExam: "+ex.getExamName());
            for(Appointment ap:  appointments.values()){//check all appointments to find thoe linked to this exam
                if (ap.getExamId() == ex.getId()){
                    double cost = ex.getCost(ap.getFastResults());//calculate cost for this appointment
                    System.out.println(ap + " | Cost: " + cost);//print appointment and cost
                    examTotal += cost;
                }
            }
            System.out.println("\nTotal revenue for " + ex.getExamName()+": "+ examTotal);
            total += examTotal;
        }
        System.out.println("\nTotal revenue: " + total);
    }//-----------------------------------------------------------------
    public void revenuePerCategory(){
        double total= 0;//total revenue from all categories combined 
        String[] categories ={"Imaging","Microbiological","Specialized"};
        for(String c: categories){
            double categoryTotal =0;//revenue generated by this category
            System.out.println("\nCategory: " + c);
            for (Exam ex: exams.values()){//checks all exams to find those belonging to this category
                if (ex.getCategoryName().equals(c)){
                    for(Appointment ap: appointments.values()){// for each exam check all appointments
                        if (ap.getExamId() == ex.getId()){
                            double cost= ex.getCost(ap.getFastResults());
                            System.out.println(ap+" | Cost: " + cost);
                            categoryTotal +=cost ;
                        }
                    }
                }
            }
            System.out.println("\nTotal revenue for " + c + ": " + categoryTotal) ;
            total+= categoryTotal;
        }
        System.out.println("\nTotal revenue: "+ total);
    }
}    
