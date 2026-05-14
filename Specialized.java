public  class Specialized extends Exam  {
    
    String specialty;
    double costIncreaseRate=0,30// 30%



    // for new exam
    Specialized(String examName, int maxSlots, double cost, int doctorId,String specialty){
        super( examName,"Specialized", maxSlots, cost,  doctorId);
        this.specialty=specialty;    
    }
    //for load an exam from a file 
    Specialized(int id ,String examName,  int maxSlots, double cost, int doctorId,String specialty){
        super(id,examName,"Specialized",maxSlots,cost,doctorId );
        this.specialty=specialty;

    }

    public String getSpecialty(){return spspecialty;}
    
    public double getCost(boolean fastResults){
        if (fastResults) {
            return cost +(cost*costIncreaseRate);
        }
        return cost;
    }
    @Override 
    public String toString(){
        return super.toString()+String.format("| Speciality  : %s ",specialty);
    }

    @Override
    public String toStringFile() {
        return super.toStringFile()+" , "+ specialty;
    }
    
}
