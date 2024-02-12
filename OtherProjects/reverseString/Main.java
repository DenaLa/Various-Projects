import java.util.Stack;

public class Main {
	public static Stack<Character> reverser = new Stack<>();
	
	
	public static void main(String[] args) {
		System.out.println(reverseString("duck"));
		System.out.println(reverseTwo("no"));
	}
	
	
	public static String reverseString(String str) {
		String ans = "";
		for(int i = 0; i < str.length(); i++) {
			reverser.push(str.charAt(i));
		}
		for(int i = 0; i < str.length(); i++) {
			ans = ans + reverser.pop();
		}
	
		return ans;
	}
	
	public static String reverseTwo(String str) {
		String ans = "";
		for(int i = str.length()-1; i > -1; i--) {
			ans = ans + str.charAt(i);
		}
		
		
		return ans;
	}
}
