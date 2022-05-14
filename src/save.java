import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class save {
	
	public static String name;
    
    public save(){
         try {
                BufferedWriter output = new BufferedWriter(new FileWriter("save.txt", true));
                    output.write(name+ " " + EndGameMenu.totalScore +","+ "\r\n" );
                    //write save file
                    output.close();
                } catch (IOException e) {
                    System.out.println("Failed to save");
                }
    }

}