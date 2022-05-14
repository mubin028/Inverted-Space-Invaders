import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InstructionsMenu extends JFrame implements ActionListener {
	JLabel instructions = new JLabel(new ImageIcon("src/Images/Instructions.png"));
	JButton okayButton = new JButton(new ImageIcon("src/Images/okayButton.png"));
	
	public InstructionsMenu(){
		
		
		setSize(700,600);
		setTitle("Inverted Space Invaders");
		setLayout(null);
		getContentPane().setBackground(new Color(19, 52, 50));
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		add(instructions);
		instructions.setBounds(50, 35, 614, 455);
		instructions.setVisible(true);
		
		
		add(okayButton);
		okayButton.setBounds(550, 485, 99, 25);
		okayButton.setVisible(true);
		okayButton.setContentAreaFilled(false);
		okayButton.setBorderPainted(false);
		okayButton.setVisible(true);
		okayButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==okayButton){
			dispose();
		}
		
	}

}
