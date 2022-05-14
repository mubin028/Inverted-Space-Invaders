		import java.awt.BorderLayout;
		import java.awt.Color;
		import java.awt.event.ActionEvent;
		import java.awt.event.ActionListener;
		 
		 
		import javax.swing.BorderFactory;
		import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
		import javax.swing.JPanel;
		import javax.swing.JProgressBar;
		import javax.swing.JWindow;
		import javax.swing.Timer;
	
		/*
		 * Author: Mubin
		 * Date: January 20, 2017
		 * Course code: ICS3U1-01
		 * Purpose: This class is used to start the game,
		 * special features: saves score, reads scores from text file, ai for ship, movement for aliens, 
		 * graphics for shots fired from aliens and the ships
		 * 


		*/

public class InvertedSpaceInvadersTest extends JWindow {
		
		 
		    private static JProgressBar loadingScreenProgressBar; //creates the progress bar
		    private static int count = 1; //timer
		    private static int TIMER_PAUSE = 15;//increment speed
		    private static int MAX = 100;//when exits progressbar
		    private static Timer progressBarTimer;//timer
		    private JFrame frame = new JFrame();
		    
		 
		    public InvertedSpaceInvadersTest() {

		        createSplash();
		    }
		 
		    private void createSplash() {

	
		        JPanel splashScreenPanel = new JPanel();//creates a new panel
		        splashScreenPanel.setLayout(new BorderLayout());//sets the layout
		        JLabel splashImage = new JLabel(new ImageIcon("src/Images/SplashScreen.jpg"));//gets splashscrren image from folder
		        splashScreenPanel.add(splashImage);//adds image to panel
		        
		        this.setLocationRelativeTo(null);
		 
		        loadingScreenProgressBar = new JProgressBar();
		        loadingScreenProgressBar.setSize(100,200);
		        loadingScreenProgressBar.setForeground(new Color(51,242,147));
		        loadingScreenProgressBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        splashScreenPanel.add(loadingScreenProgressBar, BorderLayout.SOUTH);
		 
	
		     
		        
		        frame.add(splashScreenPanel);
		        frame.setVisible(true);
		        frame.setSize(450, 450);
		        frame.setLocationRelativeTo(null);
		 
		        startProgressBar();
		 
		 
		    }
		 
		 
		    private void startProgressBar() {
		        //creates a splash
		        //-Author-runs the progress bar
		 
		    	//this loop runs the progress bar and at the end starts laptopAdvisorGUI
		    	progressBarTimer = new Timer(TIMER_PAUSE, new ActionListener() {


		    		public void actionPerformed(ActionEvent arg0) {
		    			loadingScreenProgressBar.setValue(count);


		    			if (MAX == count) {
		    				
		    				progressBarTimer.stop();

		    				frame.dispose();
		    				
		    				new gameMenu();

		    			}
		    			count++;
		    		}

		    	}
		    			);
		    	progressBarTimer.start();
		    }

		    public static void main(String[] args) {	    		    	
		    	new InvertedSpaceInvadersTest();	 
		    }
		}