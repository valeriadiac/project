public class ImagingExamination extends Exam {
    String machineType;
    double costIncreaseRate=0,10// 10%



    // for new exam
    ImagingExamination(String examName, int maxSlots, double cost, int doctorId,String machineType){
        super( examName,"Imaging", maxSlots, cost,  doctorId);
        this.machineType=machineType;    
    }
    //for load an exam from a file 
    ImagingExamination(int id ,String examName,  int maxSlots, double cost, int doctorId,String machineType){
        super(id,examName,"Imaging",maxSlots,cost,doctorId );
        this.machineType=machineType;

    }

    public String getMachineType(){return machineType;}
    
    public double getCost(boolean fastResults){
        if (fastResults) {
            return cost +(cost*costIncreaseRate);
        }
        return cost;
    }
    @Override 
    public String toString(){
        return super.toString()+String.format("| Machine type  : %s ",machineType);
    }

    @Override
    public String toStringFile() {
        return super.toStringFile()+" , "+ machineType;
    }



}