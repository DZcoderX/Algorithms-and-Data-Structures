package Project_2;

/**
 * This class will likely be useful for you when attempting the ArrayList
 * solution. Feel free to add constructors or other helper methods, if you like.
 *
 * @author Mark Hancock
 *
 */
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
