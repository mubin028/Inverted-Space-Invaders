import java.util.Scanner;

public class Ascii {
	public static void main(String[]args){
		
	
	Scanner input = new Scanner(System.in);
	String character;
	int ascii; //ascii value
	
	System.out.println("enter a character");
	character = input.next();
	
	ascii = Integer.parseInt(character);
	
	System.out.printf("The character + %s +  has the value  + %d" ,character,ascii);
	
	
	}
}
