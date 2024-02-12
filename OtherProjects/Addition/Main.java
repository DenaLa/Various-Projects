import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean constant = true;
		Scanner im = new Scanner(System.in);
		String ans = "";
		
		
		
		do {
			System.out.println(addition());
			System.out.println("Again?");
			ans = im.nextLine();
			if(ans.equalsIgnoreCase("y")){
				constant = true;
			}
			else if(ans.equalsIgnoreCase("n")) {
				constant = false;
				im.close();
			}
			else {
				System.out.println("Unknown Input. Exiting.");
				constant = false;
				im.close();
			}
		}
		while(constant);
	}
	
	
	public static int addition() {
		int answer = 0, a,b;
		Scanner in = new Scanner(System.in);
		
		try {
			System.out.println("Number A: ");
			a = in.nextInt();
			System.out.println("Number B: ");
			b = in.nextInt();
			answer = a + b;
		}
		catch(InputMismatchException e) {
			System.out.println("No. 💜💜💜💜");
			return 0;
		}
		
		
		
		return answer;
	}

}
