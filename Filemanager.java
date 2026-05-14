import java.io*;
import java.util*;



public class  FileManager{
    // --------4 hashmaps -------
    HashMap<Integer,Doctor> doctors= new HashMap<>();
    HashMap<Integer,Patient> patients= new HashMap<>();
    HashMap<Integer,Exam> exams= new HashMap<>();
    HashMap<Integer, Appointment> apointments= new HashMap<>();

    // a common method that returns an array with the words from the file 
    private String[] nLine(String line ){
        String[] n = line.split(",");
        // we divide the line into words every time we see the symbol ","
                
         // we delete the spases that might exist in a word
        for(int i =0; i<n.length;i++){
                n[i]=n[i].trim();


        }
        return n; 


    }


    // ------- 4 methods to load the Ites from file to hashmap ------------

    // 1
     public void loadDoctor (String fileName){
        try (BufferedReader reader= new  BufferedReader(new FileReader (fileName))){
            String line ; // every line from the file 
            while((line=reader.readLine())!null) {
                String[]  elements =nLine(line);
                //0 =id
                //1=name
                //2=phone
                //3=specialty
                //4=years
                Doctor d= new Doctor(
                  Integer.parseInt(elements[0] ),
                  elements[1],
                  Integer.parseInt(elements[2]),
                  elements[3],
                  Integer.parseInt(elements[4])

                );
                doctors.put(d.getID(),d);
    
            }
            reader.close();// close the file 

        }catch (IOException e){
            System.err.println("Error reading File :"+e.getMessage());
        }
     }//load the doctors file 


     //2
    public void loadPatient (String fileName){
        try (BufferedReader reader= new  BufferedReader(new FileReader (fileName))){
            String line ;
            while((line=reader.readline())!null){
                String[] elements=nLine(line);
                Patient p=new Patient(
                    Integer.parseInt(elements[0]),
                    elements[1],
                    Integer.parseInt(elements[2]),
                    elements[3]
            );
            patients.put(p.getID,p) ;
            
            

        }
        reader.close();

    }catch (IOException e){
            System.err.println("Error reading File :"+e.getMessage());
        }
    }

    //3 
    public void loadExam (String fileName){
        try (BufferedReader reader= new  BufferedReader(new FileReader (fileName))){
            String line ;
            while((line=reader.readline())!null){
                String[] elements=nLine(line);
                String category =elements[2];
                Exam exam;
                switch (category){
                    case "Imaging":
                        exam = new ImagingExamination(
                            Integer.parseInt(elements[0]),
                            elements[1],
                            elements[2],
                            elements[3],
                            Integer.parseInt(elements[4]),
                            Double.parseDouble(elements[5]),
                            Integer.parseInt(elements[6])

                        );
                        break;
                    case "Microbiological":
                        exam=new Microbiological(
                            Integer.parseInt(elements[0]),
                            elements[1],
                            elements[2],
                            elements[3],
                            Integer.parseInt(elements[4]),
                            Double.parseDouble(elements[5]),
                            Integer.parseInt(elements[6])

                        );
                        break;
                    case "Specialized":
                        exam =new Specialized(
                            Integer.parseInt(elements[0]),
                            elements[1],
                            elements[2],
                            elements[3],
                            Integer.parseInt(elements[4]),
                            Double.parseDouble(elements[5]),
                            Integer.parseInt(elements[6])
                        );
                        break;
                       
                
            
            }
            exams.put(exam.getId(),exam);
        }
    
        reader.close();
    }catch (IOException e){
            System.err.println("Error reading File :"+e.getMessage());
        }
    }// end load exam 

    //4
     public void loadAppointment (String fileName){
        try (BufferedReader reader= new  BufferedReader(new FileReader (fileName))){
            String line ; // every line from the file 
            while((line=reader.readLine())!null) {
                String[]  elements =nLine(line);
                
                Appointment a= new Appointment(
                  Integer.parseInt(elements[0]),
                  Integer.parseInt(elements[1]),
                  Integer.parseInt(elements[2]),
                  Boolean.parseBoolean(elements[3]),
                  elements[4]

                );
                apointments.put(a.getAppointmentId(),a);
    
            }
            reader.close();// close the file 

        }catch (IOException e){
            System.err.println("Error reading File :"+e.getMessage());
        }
     }//end appointement load 



     // -----------------------END--------------------------






    
    

     //------- 4 methods that allow to write in a file for each hashmao------------
     

     //1
     public void saveDoctor(String filename){
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(filename))){
            for (Doctor d : doctors.values()){
                writer.write(d.toStringFile());
                writer.newLine();//we change the line for the next time we want to write somethig else in the file 

                
                
            }
            writer.close();
            
        } catch(IOException e){
        System.err.println("Error writing file : "+e.getMessage()); }
    }
    

    //2
    public void savePatient(String filename){
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(filename))){
            for(Patient p : patients.values()){
                writer.write(p.toStringFile());
                writer.newLine();

            }
            writer.close();
        } catch(IOException e){
        System.err.println("Error writing file : "+e.getMessage()); }
    }

    //3
    public void saveExam(String filename){
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(filename))){
            for (Exam item : exams.values()){
                if(item instanceof ImagingExamination)  {
                    ImagingExamination im=(ImagingExamination) item;

                    writer.write(im.toStringFile());
                    writer.newLine();

                }

                else if (item instanceof Microbiological){
                    Microbiological mic=(Microbiological) item;

                    writer.write(mic.toStringFile());
                    writer.newLine();
                }

                else if (item instanceof Specialized) {
                    Specialized sp = (Specialized ) item;
                    writer.write(sp.toStringFile());
                    writer.newLine();
                }
            }
            writer.close();

            
        }catch(IOException e){
            System.err.println("Error writing file : "+e.getMessage()); }
    }

    //4
     public void saveAppointment(String filename){
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(filename))){
            for (Appointment i : apointments.values()){
                writer.write(i.toStringFile());
                writer.newLine();

                
                
            }
            writer.close();
            
        } catch(IOException e){
        System.err.println("Error writing file : "+e.getMessage()); }
    }


    // -----------------------END--------------------------

    //------------ 4 methods to add an Item in a Hashmap--------------


    //1 
    public void addDoctor(Doctor d){
        doctors.put(d.getID(),d);
    }
    //2
    public void addPatient(Patient p){
        patients.put(p.getID(),p);
    }
    //3 
    public void addExam(Exam ex){
        exams.put(ex.getId(),ex);
    }
    //4 
    public void addAppointment(Appointment ap){
        appointments.put(ap.getAppointmentId(),ap);
    }

    // -----------------------END--------------------------

    //-------- 4 diferent methods to display all the Items in a Hashmap -----------
    //1
    public void showAllDoctors(){
        for(Doctor d : doctors.values()){
            System.out.println(d);
        }
    }

    //2 
    public void showAllPatients(){
        for(Patient p : patients.values() ){
            System.out.println(p);
        }
    }

    //3 display all exams(sorted based on the name of the exam)
    public void showAllExams(){
        exams.values().stream().sorted(Comparator.comparing(Exam::getExamName,String.CASE_INSENSITIVE_ORDER)).forEach(System.out::println);
    }

    //4
    public void showAllAppointments(){
        for(Appointment ap : appointments.values()){
            System.out.println(ap);
        }
    }

    // -----------------------END--------------------------


    //--------methods for doctor ----------------------------
    public void addDoctorFromUser(Scanner in){
        System.out.println("Enter doctor name :  ");
        String name =in.nextLine();

        System.out.println("Enter doctor phone :  ");
        String phone=in.nextLine();

        System.out.println("Enter years of experience :  ");
        int years=Integer.parseInt(in.nextLine());

        //All specialities that are offered
        System.out.println("Choose speciality: ");
        System.out.println("1.Cardiology");
        System.out.println("2.Radiology");
        System.out.println("3.Neurology");
        int choice =Integer.parseInt(in.nextLine());

        String speciality;
        if(choice==1) specialty="Cardiology";
        if(choice==2) specialty="Radiology";
        if(choice==3) specialty="Neurology";

        Doctor d=new Doctor(name,phone,speciality,years);
        addDoctor(d);
        System.out.println(" Doctor added successfully! ");

    }
    //----------------------------1---------------------------------------
    public int doctorHelper(Scanner in ){
        while(true){
            showAllDoctors();// the system shows all the doctors
                // the person selects which doctor he wants
            System.out.println("Enter doctor ID ");
            int id =Integer.parseInt(in.nextLine());
        
            Doctor d= doctors.get(id);

                //the system control if the doctor exists
        
            if(d==null){
                System.out.println("Doctor not found !"); 
                continue;// the id is requested again
            }
            System.out.println("Doctor details: ");
            System.out.println(d);
            return id;
        }
    }
    

    //------------------------------3---------------------
    public void showDoctorDetails(Scanner in){
        int id=helper(in);
        for (Exam ex: exams.values()){
            if(ex.getDoctorId()==id) {System.out.println(ex);}
                
            }//end
        }
    }
    //------------------------------4-----------------
    public void showDoctorAppointment(Scanner in ){
        int id=helper(in);
        for (Exam ex: exams.values()){
            if (ex.getDoctorId()==id){
                for(Appointment ap : appointments.values()){
                    if(ap.getExamId()==ex.getId()){
                        System.out.println(ap);
                    }
                }
            }
        }
        
    }


    // methods for patient 

    //------------------------------------------
    public int patientHelper(Scanner in ){
        while(true){
            showAllPatients();// the system shows all the doctors
                // the person selects which doctor he wants
            System.out.println("Enter patient ID:");
            int id =Integer.parseInt(in.nextLine());
        
            Patient p= patients.get(id);

                //the system control if the doctor exists
        
            if(d==null){
                System.out.println("Patient not found !"); 
                continue;// the id is requested again
            }
            System.out.println("Patient details: ");
            System.out.println(d);
            return id;
        }
    }
    //--------------------1--------------------
    public void addPatientFromUser(Scanner in){
        System.out.println("Enter Patient name: ");
        String name = in.nextLIne();

        System.out.println("Enter patient phone: ";)
        int phone =Integer.parseInt(in.nextLine());

        System.out.println("Enter Patient email: ");
        String email = in.nextLIne();
        Patient p= new Patient(name,phone,email);
        addPatientp(p);
        System.out.println("Patient added successfully!");
    }
    //----------------------3-------------------
    public void showPatientDetails(Scanner in ){
        int id =patientHelper(in);
        System.out.println("Appointmets of this patient: ");
        for (Appointment ap: apointments.values()){
            if(ap.getPatientId==id){
                System.out.println(ap);
            }
        }
    }

    //------------------methods for exams----------------------------

    //-----------------1------------------------
    public void addExamFromUser(Scanner in ){
        System.out.println("Enter exam name: ");
        String name=in.nextLIne();
        System.out.println("Enter maximum slots per day: ");
        int maxSlots=Integer.parseInt(in.nextLine());
        System.out.println("Enter the cost: ");
        double cost=Double.parseDouble(in.nextLine());

        int  id=doctorHelper(in);

        //shows the categories
        System.out.println("Choose exam category: ");
        System.out.println("1. Imaging Examination");
        System.out.println("2. Microbiological Examination");
        System.out.println("3. Specialized Examination");

        int choice1=Integer.parseInt(in.nextLine());
        String category;
        String extra;
        if (choice1==1){
            category=Imaging;
            System.out.println("Choose machine type: ");
            System.out.println("1.MRI");
            System.out.println("2.CT");
            System.out.println("3.X-Ray");
            int choice2=Integer.parseInt(in.nextLine());
            if (choice2==1) extra="MRI";
            if (choice2==2) extra="CT";
            if (choice2==3) extra="X-Ray";
            ImagingExamination ex=new ImagingExamination(name,maxSlots,cost,id,extra);
        }
        else if (choice1==2){
            category="Microbiological";
            System.out.println("Choose sample type: ");
            System.out.println("1.Blood");
            System.out.println("2.Urine");
            System.out.println("3.Swab");
            int choice2=Integer.parseInt(in.nextLine());
            if (choice2==1) extra="Blood";
            if (choice2==2) extra="Urine";
            if (choice2==3) extra="Swab";
            Microbiological ex=new Microbiological(name,maxSlots,cost,id,extra);

        }
        else if (choice1==3){
            category="Specialized";
            System.out.println("Choose speciality : ");
            System.out.println("1.Cardiology");
            System.out.println("2.Neurology");
            System.out.println("3.Pulmonology");
            int choice2=Integer.parseInt(in.nextLine());
            if (choice2==1) extra="Cardiology";
            if (choice2==2) extra="Neurology";
            if (choice2==3) extra="Pulmonology";
            Specialized ex=new Specialized(name,maxSlots,cost,id,extra);

        }

        //crating the class exam 
        addExam(ex);
        System.out.println("Added the exam successfully!");


    }
    //------------------END----------------------------------------------------


    //collects an exam from the list
    public int helperExam(Scanner in){
        while(true){
            System.out.println("Enter exam ID from the list: ");
            showAllExams();
            int id=Integer.parseInt(in.nextLine());
            Exam ex=exam.get(id);

        
            if(ex==null){
                System.out.println("Exam not found !"); 
                continue;// the id is requested again
            }
            System.out.println("Exam details: ");
            System.out.println(ex);
            return id;
        


    }//-----------------END------------------------


    
    // --------------------------shows exam details--------------------
    public void showExamDetails(Scanner in){
        int id=helperExam(in);
        System.out.println("Appointments for this exam: ");

        for(Appointment ap: appointments.values()){
            if(ap.getExamId()==id) {System.out.println(ap);}
        }
    }
    //--------------END---------------------------------------------------


    //methods for appointments

   

   public void addAppointmentFromUSer(Scanner in){
    int idpatient=patientHelper(in);
    int idExam=helperExam(in);
    while(true){
        System.out.println("Enter date (DD:MM:YYYY):  ");
        String date=in.nextLine();
        
        if(!date.matches("\\d{2}:\\d{2}\\d{4}")){
            System.out.println("Invalid date format ! Try again. ");
            continue;
        }
        // controls if there is any appointment available in an specific date
        int count=0 ;

        for(Appointmentap:appointments.values()){
            if(ap.getExamId()==idExam && ap.getDate().equals(date)){
                count++;
            }
        }
        if (count>=ex.getMaxSlots()){
            System.out.println("No available slots for this date. Choose another date.");
            continue;
        }
        break;


    }
    System.out.println("Fast results? (yes / no ): ");
    boolean fast=in.nextLine().equalsIgnoreCase("yes");
    Appointment ap =new Appointment(idpatient,idExam,date,fast);
    addAppointment(ap);
    System.out.println("Appointment added successfully!");

   }  
   //-------------------------END-----------------------------
   
   
   // to delete an appointment 
   public void deleteAppointment(Scanner in){
    while(true){
        showAllAppointments();
        System.out.println("Enter appointment ID to delete: ");
        int id =Integer.parseInt(in.nextLine());
        Appointment ap =appointments.get(id);
        if(ap++null){
            System.out.println("Appointment not fount! Try again ");
            continue;
        }
        break;
    }
    System.out.println(" Are you sure you want to delete this appointment ? : ");
    boolean answer=in.nextLine().equalsIgnoreCase("yes");
    if(answer){
        appointments.remove(id);
        System.out.println("Appointment deleted successfully!");
    }
    else{System.out.println("Deletion cancelled . ");}
   }
   //------------------------END-------------------------------

   // show appointment based on a date 
   public void showAppointmentDate(Scanner in){
    Scanner date ;
    while(true){
        System.out.println("Enter date (DD:MM:YYYY):  ");
        String date=in.nextLine();
        
        if(!date.matches("\\d{2}:\\d{2}\\d{4}")){
            System.out.println("Invalid date format ! Try again. ");
            continue;
        }
        break;

    }
    System.out.println("Appointmets for date:  "+date);
    for (Appointment ap:appointments.values()){
        if (ap.getDate().equals(date)){
            Patient p= patients.get(ap.getPatientId());
            Exam ex=exams.get(ap.getExamId());
            System.out.println(ap.toString+" | Patient Name "+p.getName()+
            " | Exam Name "+ex.getExamName());
        }
    }
    
    }

    //--------------------END---------------------------------------
     public void showPatientAppointments(Scanner in ){
        int id=patientHelper(in);
        System.out.println("Appointments for patient ID: "+id);
        for(Appointment ap : apointments.values()){
            if(ap.getPatientId()==id){System.out.println(ap);}

        }

    }

    //--------------------END-----------------------------------
    



}







   









    



        
        

        







    
    




