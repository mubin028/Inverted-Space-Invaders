import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class gameMenu extends JFrame implements ActionListener {
	
	JLabel title = new JLabel(new ImageIcon("src/Images/menuTitle.png"));
	JButton exitButton = new JButton(new ImageIcon("src/Images/menuExitButton.png")); 
	JButton startButton = new JButton(new ImageIcon("src/Images/startButton.png"));
	JButton scoresButton = new JButton(new ImageIcon("src/Images/scoreButton.png"));
	JButton instructionsButton = new JButton(new ImageIcon("src/Images/instructionsButton.png"));

	
	
	
	public gameMenu(){
		
		setSize(700,600);
		setTitle("Inverted Space Invaders");
		setLayout(null);
		getContentPane().setBackground(new Color(19, 52, 50));
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		add(title);
		title.setBounds(200, 35, 300, 150);
		title.setVisible(true);
		
		add(startButton);
		startButton.setBounds(297, 200, 106, 25);
		startButton.setVisible(true);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.setVisible(true);
		startButton.addActionListener(this);
		
		
		add(scoresButton);
		scoresButton.setBounds(214, 340, 273, 65);
		scoresButton.setVisible(true);
		scoresButton.setContentAreaFilled(false);
		scoresButton.setBorderPainted(false);
		scoresButton.setVisible(true);
		scoresButton.addActionListener(this);
		
		add(instructionsButton);
		instructionsButton.setBounds(226, 280, 248, 25);
		instructionsButton.setVisible(true);
		instructionsButton.setContentAreaFilled(false);
		instructionsButton.setBorderPainted(false);
		instructionsButton.setVisible(true);
		instructionsButton.addActionListener(this);
		
		
		
		add(exitButton);
		exitButton.setBounds(310, 430, 77, 27);
		exitButton.setVisible(true);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setVisible(true);
		exitButton.addActionListener(this);
		
		
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==startButton){
			new InvertedSpaceInvaders();
			dispose();
			
			
		}
		if (e.getSource()==	scoresButton){
			new ScoreMenu();
			
		}
		
		if (e.getSource()==	instructionsButton){
			new InstructionsMenu();
			
		}
		
		if (e.getSource()==	exitButton){
			System.exit(0);
			
		}
	}

}
