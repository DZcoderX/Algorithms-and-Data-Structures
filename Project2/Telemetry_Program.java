package Project_2;

import java.io.File; 

import java.util.ArrayList;

import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.*;


import Project_2.utils.CSVEvent;
import Project_2.utils.CSVUtils;

public class r255zhan_proj2_program {

    public static void main(String[] args) {
	try {
	    // TODO: make sure you have this filename in the root folder of the
	    // project! I have also included an events_smaller.csv, which is
	    // useful for testing (and can be opened in Excel, unlike this
	    // file).
	    String csvFile = "events.csv";

	    // TODO: you may want to use a smaller number here when testing your
	    // code, but should bring it back up to 10,000,000 for your final
	    // tests.
	    int maxEvents = 10000000;

	    // We are going to read all of the events in and store them in a
	    // list to make the rest of your project easier for you. This is NOT
	    // the way one would normally operate. There is no reason to store
	    // all of the data in RAM. We only need to read words from the file,
	    // count them, and then move on to the rest of the file.
	    Scanner scanner = new Scanner(new File(csvFile));
 
	    // Ignore first line (header)
	    if (scanner.hasNextLine()) {
		scanner.nextLine();
	    }

	    ArrayList<CSVEvent> events = new ArrayList<>();
	    events.ensureCapacity(maxEvents);
	    while (scanner.hasNextLine() && events.size() < maxEvents) {
		ArrayList<String> line = CSVUtils.parseLine(scanner.nextLine());
		CSVEvent event = new CSVEvent(line);
		events.add(event);

		// Let's user know every million lines that something's still
		// happening.
		if (events.size() % 5000000 == 0) {
		    System.out.printf("Processed %,d events so far...\n", events.size());
		}
	    }

	    scanner.close();

	    System.out.printf("Finished reading %,d events.\n", events.size());

	    // ************************ IMPORTANT ****************************
	    //
	    // NOTE: You may NOT change the events list! Do NOT change the
	    // events list!
	    //
	    // You are to only read the events from index 0 to index
	    // events.size()-1.
	    //
	    // Do NOT sort the list of events or change it in any way.
	    //
	    // ***************************************************************

	    // ------------------------------------------------------------
	    //
	    // TODO: Add your code here to count the events, etc. You
	    // will do best to create many methods to organize
	    // your work and experiments.
	    //
	    // ------------------------------------------------------------
	    
	    
	    
	    
	    /*ArrayList Dictionary*/
	    arrayListDictionary(events);
	    /*TreeMap Dictionary*/
	    treeMapDictionary(events);
	    /*HashMap Dictionary*/
	    hashMapDictionary(events);
	    
	    
	    
	    
	} catch (Exception e) {
	    System.err.println("Caught unhandled exception: " + e.getMessage());
	    e.printStackTrace();
	}
    }
    
    /*=====================This is for HashMap implementation======================*/
    public static void hashMapDictionary(ArrayList<CSVEvent> events){
    	
    	Stopwatch swHashMap = new Stopwatch();
	    swHashMap.start();
	    //creates two hashmap, one for bookmarks, one for tabs
	    HashMap<Integer, Integer> bookmarks = new HashMap<>();
    	HashMap<Integer, Integer> tabs = new HashMap<>();
    	//Iterates through the original arraylist and determines the frequency of each
    	//bookmark or tab
    	for (int i=0; i<events.size(); i++){
    		int key = events.get(i).getCount();
    		//checks if the specific bookmark already exists
    		if (bookmarks.containsKey(events.get(i).getCount()) && events.get(i).isBookmarkEvent()){
    			int value = bookmarks.get(key).intValue();
    			value++;
    			bookmarks.put(key,value);
    		//checks if the specific tab already exists
    		} else if (tabs.containsKey(events.get(i).getCount()) && events.get(i).isTabEvent()){
    			int value = tabs.get(key).intValue();
    			value++;
    			tabs.put(key, value);
    		//if bookmark doesn't exist, add the unique bookmark to the bookmark map
    		} else if(events.get(i).isBookmarkEvent()){
    			bookmarks.put(key, 1);
    		//if tab doesn't exist, add the unique tab to the tab map
    		} else {
    			tabs.put(key, 1);
    		}
    	}
    	swHashMap.stop();
    	System.out.println("To count the events with HashMap took " + swHashMap.getElapsedSeconds() + " seconds.");
    	System.out.println("Found " + bookmarks.size() + " unique bookmark events");
    	System.out.println("Found " + tabs.size() + " unique tab events.");
    	
    	int top20Key = Integer.MIN_VALUE;
    	int top20Value = 0;
    	
    	int[] top20key = new int[20];
    	int[] top20value = new int[20];
    	
    	
    	for(int i =0; i<20; i++){
    		top20Value = Integer.MIN_VALUE;
    		//checks whether if frequency of that specific key is the greatest but wasn't
    		//added previous as well
    	for (Entry<Integer, Integer> output : bookmarks.entrySet()){
    		if(output.getValue()>top20Value && noExistsHash(top20key, output.getKey())){
    			top20Value = output.getValue();
    			top20Key = output.getKey();
    		}
    	}
    	top20key[i] = top20Key;
    	top20value[i] = top20Value;
    	}
    	for(int i =0; i<20; i++){
    	System.out.println(top20key[i] + "  " + top20value[i]);
    	}
    }
    //returns false if the key already exists and true if it doesn't
    public static boolean noExistsHash(int[] top20key, int key){
    	for (int i=0; i<top20key.length; i++){
    		if(top20key[i]==key){
    			return false;
    		}
    	}
    	return true;
    }
    /*=====================End of HashMap implementation======================*/
    
    /*=====================This is for the tree map implementation===============================*/
    public static void treeMapDictionary(ArrayList<CSVEvent> events){
    	Stopwatch swTree = new Stopwatch();
	    swTree.start();
	  //creates two treemap, one for bookmarks, one for tabs
    	TreeMap<Integer, Integer> bookmarks = new TreeMap<>();
    	TreeMap<Integer, Integer> tabs = new TreeMap<>();
    	//Iterates through the original arraylist and determines the frequency of each
    	//bookmark or tab
    	for (int i=0; i<events.size(); i++){
    		int key = events.get(i).getCount();
    		//checks if the specific bookmark already exists
    		if (bookmarks.containsKey(events.get(i).getCount()) && events.get(i).isBookmarkEvent()){
    			int value = bookmarks.get(key).intValue();
    			value++;
    			bookmarks.put(key,value);
    			//checks if the specific tab already exists
    		} else if (tabs.containsKey(events.get(i).getCount()) && events.get(i).isTabEvent()){
    			int value = tabs.get(key).intValue();
    			value++;
    			tabs.put(key, value);
    			//if bookmark doesn't exist, add the unique bookmark to the bookmark map
    		} else if(events.get(i).isBookmarkEvent()){
    			bookmarks.put(key, 1);
    			//if tab doesn't exist, add the unique tab to the tab map
    		} else {
    			tabs.put(key, 1);
    		}
    	}
    	swTree.stop();
    	System.out.println("To count the events with TreeMap took " + swTree.getElapsedSeconds() + " seconds.");
    	System.out.println("Found " + bookmarks.size() + " unique bookmark events");
    	System.out.println("Found " + tabs.size() + " unique tab events.");
    	/*==============Printing out the top 20 frequent books and their counts:==========*/
    	
    	int top20Key = Integer.MIN_VALUE;
    	int top20Value = 0;
    	
    	int[] top20key = new int[20];
    	int[] top20value = new int[20];
    	
    	
    	for(int i =0; i<20; i++){
    		top20Value = Integer.MIN_VALUE;
    		//checks whether if frequency of that specific key is the greatest but wasn't
    		//added previous as well
    		for (Entry<Integer, Integer> output : bookmarks.entrySet()){
    			if(output.getValue()>top20Value && noExistsTree(top20key, output.getKey())){
    				top20Value = output.getValue();
    				top20Key = output.getKey();
    			
    		}
    	}
    	top20key[i] = top20Key;
    	top20value[i] = top20Value;
    	}
    	for(int i =0; i<20; i++){
    	System.out.println(top20key[i] + "  " + top20value[i]);
    	}
    }
  //returns false if the key already exists and true if it doesn't
    public static boolean noExistsTree(int[] top20key, int key){
    	for (int i=0; i<top20key.length; i++){
    		if(top20key[i]==key){
    			return false;
    		}
    	}
    	
    	return true;
    }
    	
    /*=====================End of tree map implementation====================================*/
    
    
    
    
    /*=====================This is for the arraylist dictionary ===================================*/
    public static void arrayListDictionary(ArrayList<CSVEvent> events) {
    	Stopwatch sw = new Stopwatch();
	    sw.start();
    	ArrayList<ValueFrequency> event = new ArrayList<>();
    	boolean noExist = true;
    	for (int i=0; i<events.size(); i++){
    		CSVEvent origin = events.get(i);
    		for (int j =0; j<event.size();j++){
    			ValueFrequency current = event.get(j);
    			//check if it's a bookmark and if its values match
    			if(origin.getEvent().equals(current.getType()) && (origin.getCount()==current.getValue())){
    				current.freqAdd();
    				noExist = false;
    				break;
    			} else {
    				noExist = true;
    			}
    		}
    		//if none were found then we append a few unique element to the list
    		if (noExist){
    			ValueFrequency add = new ValueFrequency();
    			add.setValueType(origin.getEvent());
    			add.setType(origin.getCount());
    			event.add(add);
    		}
    	}
    	
    	
    	
    	int count =0;
    	int count2 = 0;
    
    	for (int i =0; i<event.size();i++){
    		ValueFrequency current = event.get(i);
    		if(current.getType().equals("tab")){
    			count++;
    		}
    		if(current.getType().equals("bookmark")){
    			count2++;
    		}
    	}
    	sw.stop();
    	System.out.println("To count the events with unsorted list took " + sw.getElapsedSeconds() + " seconds.");
    	System.out.println("======================================");
    	System.out.println("Number of unique bookmarks: "+ count2);
    	System.out.println("Number of unique tabs: "+ count);
    	
    	/*==================================Adding the top 20 bookmarks==================================*/
    	ArrayList<ValueFrequency> top20 = new ArrayList<>();
    	ValueFrequency toAdd = new ValueFrequency();
    	for(int a = 0; a<20; a++){
    		int max = Integer.MIN_VALUE;
    	for(int i=0; i <event.size();i++){
    		ValueFrequency current = event.get(i);
    		if(current.getFreq()>max && (current.getType().equals("bookmark")) && arrayListTimeAddOn(current,top20)){
    			max = current.getFreq();
    			toAdd = current;
    		}
    	}
    	top20.add(toAdd);
    	}
    	System.out.println("The 20 most frequent number of bookmarks and their counts are: ");
    	for(int i=0; i <20; i++){
    		System.out.print(top20.get(i).getValue() + "    ");
    		System.out.print(top20.get(i).getFreq());
    		System.out.println();
    	}
    }
    public static boolean arrayListTimeAddOn(ValueFrequency current, ArrayList<ValueFrequency> top20){
    	for (int i=0; i<top20.size();i++){
    		if(current.getValue() == top20.get(i).getValue()){
    			return false;
    		}
    	}
    	return true;
    }
    /*=====================End of arraylist dictionary ===================================*/
    	
    }
    
