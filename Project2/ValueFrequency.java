package Project_2;


public class ValueFrequency {
	//private int userID;
	private String valueType;
    private int value;
    private int freq;
    
    public ValueFrequency(){
    	
    } 
    public void setValueType(String valueType){
    	this.valueType = valueType;
    } 
    public String getType(){
    	return this.valueType;
    }
    public int getValue(){
    	return this.value;
    }
    public int getFreq(){
    	return this.freq;
    }
    public void setType(int value){
    	this.value = value;
    	this.freq++;
    }
    public void freqAdd(){
    	this.freq++;
    }
    
}
