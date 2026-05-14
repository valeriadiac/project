public class Microbiological extends Exam {
    String sampleType;
    double costIncreaseRate=0,20// 20%



    // for new exam
    Microbiological(String examName, int maxSlots, double cost, int doctorId, String sampleType){
        super( examName,"Microbiological", maxSlots, cost,  doctorId);
        this.sampleType=sampleType;    
    }
    //for load an exam from a file 
    Microbiological(int id ,String examName,  int maxSlots, double cost, int doctorId,String sampleType ){
        super(id,examName,"Microbiological",maxSlots,cost,doctorId );
        this.sampleType=sampleType;

    }

    public String getSampleType(){return sampleType;}
    
    public double getCost(boolean fastResults){
        if (fastResults) {
            return cost +(cost*costIncreaseRate);
        }
        return cost;
    }
    @Override 
    public String toString(){
        return super.toString()+String.format("| Sample Type  : %s ",sampleType);
    }

    @Override
    public String toStringFile() {
        return super.toStringFile()+" , "+ sampleType;
    }