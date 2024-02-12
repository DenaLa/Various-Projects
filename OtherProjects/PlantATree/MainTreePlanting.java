import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainTreePlanting {
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		int choice = 0; //player input
		boolean clear = false; //flag to indicate correct choice
		String[] treeTypes = new String[] {"Oak", "Coconut", "Willow", "Olive", "Ginkgo", "Magnolia"};
		HashMap<String,Integer> treeAmount = new HashMap<>();
		
		for(String tree: treeTypes) {
			treeAmount.put(tree, 0);
		}
		
		
		
		System.out.println("Welcome to your own little tree garden. It's as simple as planting a tree and watching the garden grow.");
		
		
		
		do {
			System.out.println("Would You like to\n1. Plant a tree\n2. See how many trees there are");
			try {
				choice = in.nextInt();
				if(choice == 1) {
					plantTree(treeTypes, treeAmount, in, choice);
					clear = false;
				}
				else if(choice == 2) {
					askQuestion(treeAmount);
					clear = true;
				}
				else{
					System.out.println("Please make sure to enter either 1 or 2.");
					clear = false;
				}
				
				clear = false;
				System.out.println("Would you like to continue?");
				System.out.println("1. Yes\n2. No");
				choice = in.nextInt();
				try {
					if(choice == 1) {
						System.out.println("Understood. We will start from the beginning.");
						clear = false;
					}
					else if(choice == 2) {
						System.out.println("Understood. We will now end the program.");
						clear = true;
						
					}
					else {
						System.out.println("Incorrect Input. The program will now end.");
						clear = true;
					}
				}
				catch(InputMismatchException e) {
					System.out.println("Incorrect Input.");
					clear = true;
				}
				
		}
			catch(InputMismatchException e) {
				System.out.println("Please make sure to enter either 1 or 2.");
				in.next();
			}
		}
		while(clear == false);
		
		System.out.println("Thank you for planting trees with me!");
		
		
		in.close();
	}//main
	
	public static void plantTree(String[] treeType,HashMap<String,Integer> trees, Scanner in, int choice) {
		boolean clear = false;
		
		do {
			System.out.println("Which type of tree would you like to plant?");
			for(int i = 0; i<treeType.length; i++) {
				System.out.println((i+1) + ". " + treeType[i]);
			}
			try {
				choice = in.nextInt();
				if(choice > treeType.length || choice < 1) {
					System.out.println("Invalid option. Please choose from the trees provided.");
					clear = false;
				}
				else {
					trees.put(treeType[choice-1], trees.get(treeType[choice-1])+1);
					System.out.println(treeType[(choice - 1)] + " planted successfully!");
					
					if(trees.get(treeType[choice-1]) == 1) {
						System.out.println("There is now 1 " + treeType[choice - 1] + " tree planted!");
					}
					else{
						System.out.println("There are now " + trees.get(treeType[choice-1]) + " " + treeType[choice - 1] + " trees planted!");
					}
					
				}
				System.out.println("Would you like to plant another tree?");
				System.out.println("1. Yes\n2. No");
				choice = in.nextInt();
				if(choice == 1) {
					clear = false;
				}
				else if(choice == 2) {
					System.out.println("Understood. Heading back to the main menu.");
					clear = true;
				}
				else {
					System.out.println("Input not understood. Returning to the main menu.");
					clear = true;
				}
				
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid option. Please choose from the trees provided.");
				in.next();
			}
			
			
			
			
		}
		while(clear == false);
	}// plant tree
	
	
	public static void askQuestion(HashMap<String,Integer> trees) {
		int total = 0;
		System.out.println("Here are the amounts as followed: ");
		for(String key : trees.keySet()) {
			System.out.println(key + ": " + trees.get(key));
			total += trees.get(key);
		}
		System.out.println("Total: " + total);
		
	}//ask
	
	
}
