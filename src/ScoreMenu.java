import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.util.Scanner;

public class ScoreMenu extends JFrame implements ActionListener {

	JButton okayButton = new JButton(new ImageIcon("src/Images/okayButton.png"));
	JLabel title = new JLabel(new ImageIcon("src/Images/previousScores.png"));
	JTextArea scores = new JTextArea();

	//sets up gui
	public ScoreMenu(){

		setSize(700,600);
		setTitle("Inverted Space Invaders");
		setLayout(null);
		getContentPane().setBackground(new Color(19, 52, 50));
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);

		add(okayButton);
		okayButton.setBounds(550, 485, 99, 25);
		okayButton.setVisible(true);
		okayButton.setContentAreaFilled(false);
		okayButton.setBorderPainted(false);
		okayButton.setVisible(true);
		okayButton.addActionListener(this);

		add(title);
		title.setBounds(50, 35, 273, 22);
		title.setVisible(true);
		readFile();
		
		add(scores);
		scores.setBounds(92, 159, 451, 325);
		scores.setVisible(true);
		scores.setEditable(false);

	}




//reads in the file data and sets it to text area
	private void readFile() {
		Scanner input;
		String str = "";
		
		try {
            input = new Scanner(new File("save.txt")); 
            while(input.hasNext()){
                str = str+" "+input.next();
            }
            
            input.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not found read");
            }
            String[] data = str.split(",");
            	
            	for (int g = 0; g < data.length; g++){
            		scores.setText(scores.getText() +"\n"+ data[g]);
            	}
            	
            	
            }

	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==okayButton){
			dispose();
		}


	}

}
