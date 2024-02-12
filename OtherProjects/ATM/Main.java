import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static HashMap<Integer,Account> accounts = new HashMap<>();
	public static Scanner in = new Scanner(System.in);
	public static int num = 0;
	public static int options = 4;
	
	
	public static void main (String[] args) {
		int ID = 0;
		
		Account amy = new Account("Amy", ++ID,400,4000);
		Account benny = new Account("Benny", ++ID,70,2000);
		Account chris = new Account("Chris",++ID);
		
		accounts.put(amy.getID(),amy);
		accounts.put(benny.getID(),benny);
		accounts.put(chris.getID(),chris);
		
		atmFunction();
		in.close();
	}
	
	
	public static void atmFunction() {
		
		Account user;
		boolean done = false;
	
		System.out.println("Welcome to the ATM program!");

			
		do {
			try{
				System.out.println( "Please enter your account number.");
				num = in.nextInt();
				if(accounts.containsKey(num)) {
					user = accounts.get(num);
					System.out.println("Welcome, " + user.getName() + ".");
					atmMenu(user);
					done = true;
				}
				else {
					System.out.println("Account not found.");
				}
					
			}//Try catch
			catch(InputMismatchException e) {
				System.out.println("Incorrect Input Type.");
				in.next();
			}
		}
		while(done == false);
		
		
	}//atmFunction
	
	public static void atmMenu(Account user) {
		boolean done = false;
		
		do {
			try {
				System.out.println("What would you like to do today?");
				System.out.println("1. Check Balance\n2. Deposit Money\n3. Withdraw Money\n4. Exit");
				num = in.nextInt();
				if(num == options) {
					done = true;
				}
				else if(num == 1) {
					checkBalance(user);
					done = true;
				}
				else if(num == 2) {
					depositMoney(user);
					done = true;
				}
				else if(num == 3) {
					withdrawMoney(user);
					done = true;
				}
				else {
					System.out.println("Number out of bounds.");
				}
				
			}
			catch(InputMismatchException e) {
				System.out.println("Please input a number");
				in.next();
			}
			
			
		}
		while(done == false);
		System.out.println("Have a good day :)");
	}//account menu
	
	public static void checkBalance(Account user) {
		System.out.println("Your Checkings Account is: " + user.getCheckings()
		+ "\nYour Savings Account is: " + user.getSavings() + "\n");
	}
	
	
	public static void depositMoney(Account user) {
		float deposit = 0f;
		int choice = 0;
		boolean clear = false;
		
		System.out.println("How much would you like to deposit?");
		do {
			try {
				deposit = in.nextFloat();
				if(deposit < 1) {
					System.out.println("Amount must be greater than zero.");
				}
				else {
					clear = true;
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Please enter a number.");
				in.next();
			}
		}
		while(clear == false);
		clear = false;
		
		System.out.println("Which account would you like to deposit to?\n1. Savings\n2. Checkings");
		
		do {
			try {
				choice = in.nextInt();
				if(choice == 1) {
					System.out.println("Depositing " + deposit + " into Savings.");
					user.modifySavings(deposit);
					System.out.println("New amount: " + user.getSavings());
					clear = true;
				}
				else if(choice == 2) {
					System.out.println("Depositing " + deposit + " into Checkings.");
					user.modifyCheckings(deposit);
					System.out.println("New amount: " + user.getCheckings());
					clear = true;
				}
				else {
					System.out.println("Incorrect Input. Please input either 1 or 2.");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Please input either 1 or 2.");
				in.next();
			}
		}
		while(clear == false);
		
		
		
	}
	
	
	public static void withdrawMoney(Account user) {
		float withdraw = 0;
		float availableAmount = 0;
		int choice = 0;
		int accountNumber = 0; //1 is savings, 2 is checkings
		boolean clear = false;
		
		System.out.println("Which Account would you like to withdraw from?\n1. Savings\n2. Checkings");
		
		
		do {
			try {
				choice = in.nextInt();
				accountNumber = choice;
				if(choice == 1) {
					availableAmount = user.getSavings();
					System.out.println("Your available amount is " + availableAmount);
					clear = true;
				}
				else if(choice == 2) {
					availableAmount = user.getCheckings();
					System.out.println("Your available amount is " + availableAmount);
					clear = true;
				}
				else {
					System.out.println("Incorrect Input. Please selece either 1 or 2.");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Incorrect Output. Please select either 1 or 2.");
				in.next();
			}
		}
		while(clear == false);
		clear = false;
		
		System.out.println("How much would you like to withdraw?");
		
		
		do {
			try {
				withdraw = in.nextFloat();
				
				if(withdraw < 1) {
					System.out.println("Must have an amount greater than 0.");
				}
				else if(withdraw > availableAmount) {
					System.out.println("Requesting more than the account has available.");
					System.out.println("Your available amount is " + availableAmount);
				}
				else {
					System.out.println("Withdrawing " + withdraw + " from " + availableAmount);
					if(accountNumber == 1) {
						user.modifySavings(-withdraw);
						System.out.println("New Amount is " + user.getSavings());
						clear = true;
					}
					else if(accountNumber == 2) {
						user.modifyCheckings(-withdraw);
						System.out.println("New Amount is " + user.getCheckings());
						clear = true;
					}
				}
				
			}
			catch(InputMismatchException e) {
				System.out.println("Please Input a Number.");
				in.next();
			}
		}
		while(clear == false);
		
		
		
		
		
		
		
		
	}
	
	
	
}
