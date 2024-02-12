import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		boolean active = true, correctInput = false;
		Scanner sc = new Scanner(System.in);
		String choice = "";
		
		do {
			numberGame();
			System.out.println("Do you want to play again? Please enter either 'y' or 'n'.");
			do {
				try {
					choice = sc.nextLine();
					if(choice.equalsIgnoreCase("y")) {
						active = true;
						correctInput = true;
					}
					else if(choice.equals("n")) {
						System.out.println("Bye! Come play again some time!");
						active = false;
						correctInput = true;
					}
					else {
						System.out.println("Please enter either 'y' or 'n'.");
						correctInput = false;
					}
				}
				catch(InputMismatchException e) {
					System.out.println("Unknown input. Please enter either 'y' or 'n'.");
				}
			}
			while(correctInput == false);

		}
		while(active);
		sc.close();
		
		
	}
	
	
	public static void numberGame() {
		mainDriver();
		
	}
	
	public static void hintMessage(int answer,int guess) {
		if(guess > answer) {
			System.out.println("Try going lower!");
		}
		else if (guess < answer) {
			System.out.println("Try going higher!");
		}
		
	}
	
	public static void mainDriver() {
		boolean active = true;
		int min = 1, max = 100, random, tries = 5, ans = -1;
		Scanner sc = new Scanner(System.in);
		
		random = (int)Math.floor(Math.random() * (max-min + 1) + min);
		System.out.println("I have thought of a number between " + min + " and " + max + ". You have "
				+ tries + " chances to get it right.");
		do {
			while(tries > 0) {
				System.out.println("You have " + tries + " left. Make your guess: ");
				
				try {
					ans = sc.nextInt();
					if(tries > 0) {
							if(ans == random) {
								System.out.println("Correct! You guessed " + ans + " and I thought " + random
									+ "! Good job :)");
								active = false;
								break;
							}
							else if(Math.abs(ans - random) <= 5 && tries > 1) {
								System.out.println("You're really close!");
							}
							else if(Math.abs(ans-random) >= 15 && tries > 1) {
								System.out.println("Geeze! Really far away, there!");
							}
							else if((Math.abs(ans-random) < 15 || Math.abs(ans-random) > 5) && tries > 1 ) {
								System.out.println("You're getting there!");
							}
						
							if(tries > 1) {
								hintMessage(random,ans);
							}
							
						}
					tries --;
				}
				catch(InputMismatchException e) {
					System.out.println("Non-number detected. Aborting this attempt and removing all your tries.");
					tries = 0;
				}
		
				}
			if(tries <= 0) {
				System.out.println("You ran out of tries! The answer was " + random + ". Better luck next time!");
			}
			active = false;
			
		}
		while(active == true);
	}
	
}
