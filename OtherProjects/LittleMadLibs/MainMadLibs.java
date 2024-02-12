import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMadLibs {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String sentence = "", replace = "", speech = "";
		int choice = 0;
		ArrayList<String> holder = new ArrayList<>(); //to play with our sentence
		System.out.println("Hello! This is a simple project just to play with ArrayLists.");
		System.out.println("Let's make a MadLibs sentence!");
		
		System.out.println("You can make the sentence anything you want. I won't be stopping you. But at least have some spirit for the game, okay?");
		
		System.out.println("Please Enter Your Sentence Here: ");
		
		sentence = in.nextLine();
		
		String[] temp = sentence.split(" ");
		
		for(String word : temp) {
			holder.add(word);
		}
		
		System.out.print("Your sentence is: ");
		for(int i = 0; i<holder.size(); i++) {
			System.out.print(holder.get(i) + " ");
		}
		
		boolean clear = false;
		/*
		 * To Replace Words:
		 * User Selects Word The want to replace
		 * They tell the program what part of speech it is
		 * The program will then replace the word with the part of speech
		 * */
		
		do {
			System.out.println("\nWhat do you want to replace?");
			
			replace = in.nextLine();
			if(!holder.contains(replace)) {
				System.out.println("That's not in the sentence! You can try again, or you can leave, either is okay!");
				clear = false;
			}
			else {
				System.out.println("And what part of speech is it?");
				speech = in.nextLine();
				
				//Replace the word in the sentence with our part of speech!
				
				holder.set(holder.indexOf(replace), ("[" + speech.toUpperCase() + "]"));
			}
			
			replace = "";
			System.out.print("Our sentence is: ");
			for(int i = 0; i<holder.size(); i++) {
				System.out.print(holder.get(i) + " ");
			}
			
			System.out.println("\nDo you want to continue? \n1. Yes \n2. No");
			
			try {
				choice = in.nextInt();
				
				if(choice == 1) {
					System.out.println("We will continue");
					in.nextLine();
					clear = false;
				}
				else if(choice == 2) {
					System.out.println("We will stop.");
					clear = true;
				}
			}
			catch(InputMismatchException e) {
				System.out.println("That isn't a correct input! Let's stop here.");
				clear = true;
			}
			
		}
		while(clear == false);
		
		System.out.println("Our new sentence is: ");
		for(int i = 0; i<holder.size(); i++) {
			System.out.print(holder.get(i) + " ");
		}
		
		System.out.println("\nI hope you had fun!");
		
		
		in.close();
	}
}
