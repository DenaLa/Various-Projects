package learningApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static String[] onyo = new String[12]; //On'yomi
	static String[] kunyo = new String[12]; //Kun'yomi
	static String[] Kanji = new String[12]; //Kanji
	static String[] English = new String[12]; //English

	//Eldest Sibling. Directs the whole thing, like a good older sibling would.
	public static void main(String[] args) {
		boolean clear = false, sclear = false; //controls the loop
		String input; //input from the user (aplhabetical)
		int in; //input from the user (numerical)
		Scanner sc = new Scanner(System.in); //scanner for the input
		
		//Begins the entire program. Main Menu of sorts.
		System.out.println("Welcome to Kanji Exam!");
		do {
			//Where each mode is chosen. Corresponding numbers lead to subroutines.
			System.out.println("Please select what you would like to do.");
			System.out.println("1. Exam\n2. View Kanji Gallery\n3. Exit");
			
			try {
				in = sc.nextInt();
				if(in == 1) {
					testHub();
				}
				else if(in == 2) {
					Gallery();
				}
				else if(in == 3) {
					System.out.println("Exiting.");
					break;
				}
				else { //Catches invalid numbers
					System.out.println("Invalid input.");
				}
			}
			catch(InputMismatchException e) { //catches invalid inputs
				System.out.println("Invalid Input. Halting process.");
				sc.next(); //uses up the incorrect token to prevent an infinite loop.
			}
			//Sets up for the process to repeat again
			do {
				System.out.println("Would you like to continue? y/n");
				input = sc.next();
				
				if(input.equalsIgnoreCase("y")) {
					System.out.println("Process will repeat.");
					sclear = true;
				}
				else if(input.equalsIgnoreCase("n")) { //sets clear to true, ending the program
					System.out.println("Program will now end.");
					clear = true;
					sclear = true;
				}
				else { //catches invalid input
					System.out.println("Invalid input.");
				}
			}
			while(sclear == false);		
		}
		while(clear == false);
		System.out.println("Thank you for participating. Have a nice day.");
		sc.close(); //sc is only closed at the end because if any function closes it it causes
		//the program to crash.
		try {
			Thread.sleep(1000) //Consoles close scarily quick, so this will linger
			//on the screen just as a treat.
;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}// main
	
	//General Functions
	
	//Assembles vocabulary. Puts them in the universal arrays up on top.
	//Takes the file name and assembles.
	public static void assemble(String choice) {
		Queue<String> temp = new LinkedList<String>(); //used as temporary holding place
		String str; //for tokenizing
		String[] tokens = new String[4]; //hold tokens temporarily to be put in arrays.
		
		//try catch to read files.
		try(BufferedReader in = new BufferedReader(new FileReader(choice))){
			int i = 0;
			while((str = in.readLine()) != null) {
				tokens = str.split("、"); //tokenizer, splits using 「、」 not ","
				for(int j = 0; j<4; j++) {
					temp.add(tokens[j]); //add to queue
				}
				
				//Since a queue is FIFO, just remove them in the order of the text file
				onyo[i] = temp.remove();
				kunyo[i] = temp.remove();
				Kanji[i] = temp.remove();
				English[i] = temp.remove();
				i++;
			}
		}
		catch(IOException e) {//catches file error
			System.out.println("Error! File Not Found");
		}
	}//Assemble
	
	//Prints the assembled Set.
	public static void printAssembly() {
		for (int i = 0; i < 12; i++) {
			System.out.println(English[i] + " || " + Kanji[i] + " || " + onyo[i] + " || " + kunyo[i]);
		}
	}//print assembly
	
	//Prints the assembled Set's English. Used to prevent cheating, in a sense.
	public static void printAssemblyEng() {
		for (int i = 0; i < 12; i++) {
			System.out.println(English[i]);
		}
	}//print assembly
	
	
	//Selects a Collection. Used for both the Test and the Gallery, though more for the Gallery.
	public static int selectCollection() { //returns collection number
		Scanner sc = new Scanner(System.in); //for user input
		int slct = 0; //return value
		boolean clear = false; //manages the do/while loop
		
		do {	
			try { //to select collection.
				System.out.println("1. Collection 1 || 2. Collection 2");
				System.out.println("3. Collection 3 || 4. Collection 4");
				System.out.println("5. Collection 5 || 6. Collection 6");
				slct = sc.nextInt();							
				if(slct == 1 || slct == 2 || slct == 3 || slct == 4  || slct == 5 || slct == 6) { //Maybe make a queue or something for the contains function
					clear = true;
				}
				else { //catches invalid numbers
					System.out.println("Invalid input. Please select again.");
				}
			}
			catch(InputMismatchException e) { //catches invalid inputs.
				System.out.println("Invalid input. Please select again.");
				sc.next();
			}
			//If the user makes an incorrect input, the loop repeats until they enter
			//an available collection.		
		}
		while(clear == false);
		
		return slct;
	}//selectCollection
	
	//Selects a Set. Used strictly for the test.
	public static int selectSet() { //Returns set number. virtually the same as the Collection
		//version
		Scanner sc = new Scanner(System.in); //for user input
		int slct = 0; //return value
		boolean clear = false; //manages the do/while loop
			
		do {
				
			try {
				System.out.println("1. Set 1\n2. Set 2");
				slct = sc.nextInt();							
				if(slct == 1 || slct == 2) { //Valid set numbers.
					clear = true;
				}
				else {
					System.out.println("Invalid input. Please select again.");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid input. Please select again.");
				sc.next();
			}
			//If the user makes an incorrect input, the loop repeats until they enter
			//an available Set. So far I think there will only be two Sets per
			//Collections, so it doesn't need to change.
		}
		while(clear == false);
			
		return slct;
	}

	//Gallery Functions
	
	//Big Brother. Handles the whole Gallery process.
	public static void Gallery() {
		//all the functionality of the gallery.
		//select and view exist in here, as well as an option to exit.
		
		int coll = 0; //collection number
		boolean clear = false; //keeps the loop going
		Scanner sc = new Scanner(System.in); //scanner for input
		String choice = ""; //y/n answers
		
		System.out.println("You are now veiwing the Gallery.");
		
		do { //do while loop to manage the whole function.
				//Select Collection to print.
				System.out.println("Select which collection to choose from.");
				coll = selectCollection();
				
				//Confirm Selection.
				System.out.println("Would you like to view Collection "+coll+"? y/n");
				choice = sc.next();
				if(choice.equalsIgnoreCase("y")) { //Print selection.
					viewGallery(coll);
				}
				else if(choice.equalsIgnoreCase("n")) { //Pause selection.
					System.out.println("Process halted.");
				}
				else { //If input is invalid, pause selection.
					System.out.println("Invalid input.");
			}
			
				//returning to the first screen.
				System.out.println("Would you like to return to the main menu? y/n");

				try {
					choice = sc.next();
					if(choice.equalsIgnoreCase("y")) {
						System.out.println("Returning to main menu.\n");
						clear = true;
					}
					else if(choice.equalsIgnoreCase("n")) {
						System.out.println("You will continue viewing the Gallery.");
					}
					else {//Inputing incorrectly boots you to the main menu.
						System.out.println("Invalid input. Returning to the main menu.");
						clear = true;
					}
				}
				catch(InputMismatchException e) {//Inputing incorrectly boots you to the main menu.
					System.out.println("Invalid input. Returning to the main menu.");
					clear = true;
				}
				//Incorrect and invalid inputs boot the user back to the main menu.
		}//do
		while(clear == false);
	}//Gallery
	
	//Little Brother. Handles the "Viewing" part of the gallery. Isolated for easier tweaking.
	public static void viewGallery(int n) {//takes the collection number
		String choice; //file name
		System.out.println("Now viewing Collection " + n);
		choice = "Collection0" +n+ "Set01.txt"; 
		
		//Assemble and print rapidly
		assemble(choice);
		System.out.println("Set 1:");
		printAssembly();
		
		
		System.out.println("\nSet 2:");
		choice = "Collection0" +n+ "Set02.txt";
		assemble(choice);
		printAssembly();
		//Uses only one array twice because adding 8 arrays for 2 Sets is ridiculous.
	}//viewGallery
	
	//Test Functions
	
	//Big Sister. Handles the whole test process.
	public static void testHub() {
		//Select collection and set.
		//Select Test Type
		//Test is administered.
		
		//manages do while loop. There's a lot, because there's multiple loops.
		//Time complexity is crying.
		boolean clear = false, tsclear = false, aeclear = false, restart = false;
		//clear = Ultimate Clear. Did this function get through it's run?
		//tsclear = Test Select Clear. Did we select a test properly?
		//aeclear = After Exam Clear. Did we get through the things after the exam function?
		//restart = Do we want to restart? Do this whole thing again?
		
		Scanner sc = new Scanner(System.in); //Scanner for input
		int ans = 0; //Used for user input.
		String input = " "; //Used for user input.
		
		do {
			//Its own function for easier tweaking.
			selectTestSet();
			do {
				//Select the type of test to take.
				//As with other choices, it repeats until you give a valid option.
				try {
					System.out.println("Which Test would you like to take?\n1. Kanji: Given the English word, you must write the Kanji and Kana within the"
							+ " time limit.\n2. Kana: Given the Kanji, must write the Kana within the time limit.");
					ans = sc.nextInt();
					if(ans == 1) {
						System.out.println("You have selected the Kanji Test.");
						exam(English,ans);
						tsclear = true;
					}
					else if(ans == 2) {
						System.out.println("You have selected the Kana Test.");
						exam(Kanji,ans);
						tsclear = true;
					}
					else { //Invalid input
						System.out.println("Incorrect input.");
					}
				}
				catch(InputMismatchException e) { //Invalid input, eat token to prevent
					//an infinite loop.
					System.out.println("Incorrect input.");
					sc.next();
				}
			}
			while(tsclear == false);

			//Go to main menu or restart.
			do {
				System.out.println("Would you like to return to the main menu? Declining will restart the selection"
						+ " process. \ny/n");
				input = sc.next();
				if(input.equalsIgnoreCase("y")){
					System.out.println("Returning to main menu.");
					aeclear = true;
					restart = false;
				}
				else if(input.equalsIgnoreCase("n")){
					System.out.println("Process will restart.");
					aeclear = true;
					restart = true; //restart flag set to true.
				}
				else {
					System.out.println("Invalid input.");
				}
			}
			while(aeclear == false);
			
			if(restart == false) { //true clear only possible if restart is false.
				clear = true;
			}
		}
		while(clear == false);
	}//testHub

	//Little Sister. Selects the test Set.
	public static void selectTestSet() {
		Scanner sc = new Scanner(System.in); //scanner for user input
		int coll = 0,set = 0; //integer answer, collection number, set number
		String ans = " ", file;//y/n answer, file name
		boolean clear = false; //manages do while loop
		
		System.out.println("Now setting up the test.");	
			do {
				System.out.println("Please select a Collection.");
				coll = selectCollection();
				System.out.println("Please select a Set.");
				set = selectSet();
				System.out.println("Do you want to use Collection " + coll + " Set " + set + "?\n(y/n)");
				file = "Collection0"+coll+"Set0"+set+".txt";
				assemble(file);
				printAssemblyEng();
				ans = sc.next();
				if(ans.equalsIgnoreCase("y")) {
					System.out.println("Please wait...");
					clear = true;
				}
				else if(ans.equalsIgnoreCase("n")) {
					System.out.println("Restarting selection.");
				}
				else {//If the input is incorrect, the whole thing restarts.
					System.out.println("Invalid input. Restarting selection.");
				}
			}
			while(clear == false);	
	}//SelectTestSet

	//Littler Sister. Given the task of building the "Deck"
	public static void buildTestDeck(int[] deck) {
		//deck will be explained when it comes up later.
		
		Queue<Integer> select = new LinkedList<Integer>(); //stores random numbers
		//with no repeats.
		int max = 11, min = 0; //max and min numbers
		int index; //array index
		
		while(select.size()<deck.length) { //while select is smaller than deck's length.
			//index: Choose a random number
			index = (int)Math.floor(Math.random()*(max-min+1)+min);
			
			//If select doesn't contain index, add it in. 
			//This is so no questions repeat.
			if(!select.contains(index)) {
				select.add(index);
			}
		}
		
		//Fill the deck with the random numbers.
		for(int i = 0; i<deck.length; i++) {
			deck[i] = select.remove();
		}
	}//buildTestDeck

	//Littlest sister. Responsible for the Exam itself. がんばれ、　妹ちゃん。
	public static void exam(String[] Display,int type) {//Takes in the array to be displayed
		//and the type of test it is supposed to be.
		//Kanji: English Displayed, responsible for Kanji and Kana
		//Kana: Kanji Displayed, responsible for Kana
		
		Scanner sc = new Scanner(System.in); //scanner for input
		String input; // user input
		int deck[] = new int[6]; //the deck contains numbers. 
		//it is used to fetch random numbers from the given Display array.
		//they are stored so they can be referred to later.
		boolean clear = false; //controls do/while loops
		
		
		System.out.print("Six (6) vocabulary words will be picked at random. "); 
		//Different instructions.
		if(type == 1) {
			System.out.println("The English will be displayed on the screen.\nYou have 40 seconds each to correctly" +
		" write the Kanji for each English word displayed.");
		}
		else if(type == 2) {
			System.out.println("The Kanji will be displayed on the screen.\nYou have 40 seconds each to correctly" +
					" write the Kana for each Kanji displayed.");
		}
		//building deck.
		buildTestDeck(deck);
		
		
		System.out.println("\nYou cannot quit the test once it has started.");
		System.out.println("When you are ready, press any key + ENTER to start.\n"
				+ "If you wish to quit, press n + ENTER.");
		input = sc.next();
		if(input.equalsIgnoreCase("n")) {
			System.out.println("Exiting test...");
			return; //will actually just exit.
		}
		
		//***START OF EXAM***
		do {
			for(int i = 0; i< 6; i++) {
				System.out.println(Display[deck[i]]);
				try {
					Thread.sleep(15000); //10 second delay.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//***END OF EXAM***
			
			System.out.println("Now displaying the answers...");
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			//Displays Test Questions along with their answers.
			for(int i = 0; i< 6; i++) {
				System.out.println("Vocabulary " + (i+1) + ":");
				System.out.println(English[deck[i]] + " || " + Kanji[deck[i]] + " || " +
				onyo[deck[i]] + " || " + kunyo[deck[i]]);
				try {
					Thread.sleep(100); //.1 second delay.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
			System.out.println("Press any key + ENTER to continue.");
			sc.next();
			
			clear = true;
		}
		while(clear == false);
	}//Exam
	
}//Main
