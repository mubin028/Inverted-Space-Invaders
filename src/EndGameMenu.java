import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EndGameMenu extends JFrame implements ActionListener{


	JLabel title = new JLabel(new ImageIcon("src/Images/title.jpg"));
	JButton exitButton = new JButton(new ImageIcon("src/Images/exitButton.jpg")); 
	JLabel scoreTitleLabel = new JLabel(new ImageIcon("src/Images/scoreTitle.jpg"));
	JLabel scoreLabel = new JLabel();
	JTextField nameTextField = new JTextField();
	JButton saveScoreButton = new JButton(new ImageIcon("src/Images/saveScore.jpg")); //exit button



	public static int totalScore;




	public EndGameMenu(){ //constructor method for everything

		setSize(700,600);
		setTitle("Save High Score");



	setLayout(null);
	getContentPane().setBackground(Color.BLACK);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		add(title);
		title.setBounds(200, 35, 300, 150);
		title.setVisible(true);

		add(scoreTitleLabel);
		scoreTitleLabel.setBounds(220, 250, 197, 55);
		scoreTitleLabel.setVisible(true);


		add(scoreLabel);
		scoreLabel.setBounds(425, 245, 197, 55);
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
		scoreLabel.setForeground(new Color(33, 79, 9));
		scoreLabel.setVisible(true);
		scoreLabel.setText(Integer.toString(totalScore));

		nameTextField.setText("Enter Name");
		nameTextField.setBounds(255, 380, 86, 20);
		add(nameTextField);
		nameTextField.setColumns(10);
		
		
		add(exitButton);
		exitButton.setBounds(280, 450, 150, 75);
		exitButton.setVisible(true);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setVisible(true);
		exitButton.addActionListener(this);


		saveScoreButton.setBounds(380, 320, 150, 150);
		add(saveScoreButton);
		saveScoreButton.setVisible(true);
		saveScoreButton.setContentAreaFilled(false);
		saveScoreButton.setBorderPainted(false);
		saveScoreButton.setVisible(true);
		saveScoreButton.addActionListener(this);

	}






	public void actionPerformed(ActionEvent e) {// closes everything if pressed
		if(e.getSource() == exitButton){
			System.exit(0);


		}

		if(e.getSource() == saveScoreButton){ //save score by calling save class and then exits the program
			save.name = nameTextField.getText();
			new save();
			System.exit(0);
		}
	}

}
