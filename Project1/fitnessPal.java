import java.io.BufferedWriter;  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class fitnessPal {
	public static void main(String[] args) throws IOException{
		Food list = new Food(12);
		//Below inputs are test codes!	
		list.add("Cheese", 1000);
		list.add("grain u", 54);
		list.add("cash", 34);
		list.add("beef", 54);
		list.add("maki   ", 50);
		list.add("maki", 50);
		list.add("maki", 50);
		list.add("White Pasta", 90);
		list.add("suishi", 32);
		list.add("ter", 87);
		list.add("chicken", 1);
		list.add("bananas", 54);
		list.add("maki", 56);
		list.add("pork", 54);
		list.add("ham", 54);

		//The Food class will sort the entire list
		list.sort();
		
		ArrayList<String> sortedTopList = new ArrayList<String>();
		sortedTopList = list.fileToProgram();
		displayToConsole(sortedTopList);
	}
	//takes data from the sorted array list and displays it correctly to console
	public static void displayToConsole( ArrayList<String> output){
		
		System.out.println("FoodName-Calorie");
		int limit= Food.getNumOfSlots();
		//If the user inputs n and n is larger than the number of data present, the program will just display all the data
		if (output.size()<Food.getNumOfSlots())
			limit = output.size();
		
		//The actual algorithm for displaying the data formally 
		for (int i=0; i< limit; i++){
			String content = output.get(i);
			//Only displays the names of the food while keeping spaces between the words
			content = content.trim().replaceAll("(\\d)([^a-z])","");
			System.out.print(content);
			//adds a dash
			System.out.print("-");
			String num = output.get(i);
			//only displays the calories of the food
			num = num.trim().replaceAll("[^0-9]","");
			System.out.print(num);
			System.out.println();
		}
	}
}
class Food {
	private int calories;
	private String foodName;
	//Number of top n slots
	private static int numOfSlots;
	//This arraylist will contain the entire data of the text
	private ArrayList<String> entireList = new ArrayList<String>();
	//This arraylist will contain the calories of the food (mainly used the sort function)  
	private ArrayList<Integer> listInteger = new ArrayList<Integer>();
	//Makes the list that has 10 slots for food and their names
	public Food(){
		this.numOfSlots = 10;
	}
	//Constructor that specifies the number of slots
	public Food(int numOfSlots){
		this.numOfSlots = numOfSlots;
	}
	public static int getNumOfSlots(){
		return numOfSlots;
	}
	//Adds food name and calorie to the list
	public void add(String foodName, int calories) throws IOException{
		//program will end if user inputs a negative value for calories
		if(calories<=0){
			System.out.println("Hi, food cannot have negative calories. Please try again.");
			System.exit(0);
		}
		//program will end if the user does not input a name for the food
		if(foodName.length()<1){
			System.out.println("Hi, your food needs a name! Please try again");
			System.exit(0);
		}
		//Fine-tuning the data so they can be sorted properly
		this.foodName = foodName.toLowerCase().trim();
		this.calories = Integer.parseInt(Integer.toString(calories).trim());
		//File writer is able to append data to pre-existing data,true tells java to add data
		FileWriter writer = new FileWriter("food.txt", true); 
		PrintWriter printer = new PrintWriter(writer);
		//adds the food's name
		printer.print(this.foodName);
		//adds the food's calories
		printer.print(this.calories);
		printer.println();
		printer.close();
		
	}
	//Sorts the food by highest to lowest calories
	public void sort() throws IOException{
		//First we add all the text to the arraylists for them to get sorted
		fileToProgram();
		//We then check the arraylist for any potential bugs and attempts to solve them
		checkAndSolve();
		//checkAndSolve clears the current arraylist so we have to call 
		//fileToProgram method again to add the error-free data to arraylist
		fileToProgram();
		/*Learned and implemented the Bubble Sort algorithm
		link: https://www.tutorialspoint.com/data_structures_algorithms/bubble_sort_algorithm.htm
		Altered the algorithm towards my needs 
		The algorithm will specifically sort the integer arraylist.
		In addition, whenever the integer arraylist gets sorted, the arraylist containing the actual names and calories 
		will tag along and get sorted as well.
		*/		
		for (int i=entireList.size()-1; i>=0; i--){
			for (int j=1; j<=i;j++){
				if (listInteger.get(j-1) < listInteger.get(j)){
					int temp = listInteger.get(j-1);
					listInteger.set(j-1, listInteger.get(j));
					listInteger.set(j, temp);
					String tempWord = entireList.get(j-1);
					entireList.set(j-1, entireList.get(j));
					entireList.set(j, tempWord);
				}
			}
		}
		//Adding sorted data to pre-existing data does not make any sense so the program clears the text file first
			clearTextFile();
		//After clearing the pre-exisiting data, the program adds the new sorted data to the text file
		programToFile();
	}
	//This method checks the list and sees if the food can be added to the list or already exists
	//If the food already exits, one of the duplicates will be deleted
	private void checkAndSolve() throws IOException{
		int count = entireList.size();
		    for (int i = 0; i < count; i++){
		        for (int j = i + 1; j < count; j++){
		        	//If two words are the same, the duplicate gets deleted
		            if (entireList.get(i).equals(entireList.get(j))){
		                entireList.remove(j--);
		                count--;
		            }
		        }
		    }
		    //Overwrites the new data to the existing data
		    programToFile();
		    //the arraylist should be cleared so proper data can be added
		    entireList.clear();
	}
	//Takes content and inputs it to text file (used for the sort() method)
	private void programToFile() throws IOException{
		//printStream overwrites pre-existing data everytime
		PrintStream printer = new PrintStream(new File("food.txt"));
		for (int i=0; i<entireList.size();i++){
			printer.print(entireList.get(i));
			printer.println();
		}
		
	}
	//Takes the content from text file to arraylist
	public ArrayList<String> fileToProgram() throws FileNotFoundException{
		//Scans the food text
		Scanner sc = new Scanner(new File("food.txt"));
		 /** While the text file has content, add the content of each line to each slot of the arraylist
		 * */
		while(sc.hasNextLine()){
			this.entireList.add(sc.nextLine());
		}
		while(sc.hasNextLine()){
			this.listInteger.add(sc.nextInt());
		}
		  /*Gets the calories from the 1st arraylist and puts the calories into the second arraylist*/
		for (int i = 0; i<this.entireList.size(); i++){
			String content = entireList.get(i);
			//Gets rid of all the letters and spaces so only the integers can be extracted to the listInteger arraylist
			content = content.trim().replaceAll("[^0-9]","");
			int cal = Integer.valueOf(content);
			listInteger.add(cal);
		}
		return entireList;
	}
	//Clears all content from the text file
	private void clearTextFile() throws FileNotFoundException{
		PrintStream clear = new PrintStream(new File("food.txt"));
	}
}
