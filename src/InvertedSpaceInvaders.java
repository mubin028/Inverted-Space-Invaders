import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 *
 */
public class InvertedSpaceInvaders extends JFrame implements Runnable {
	public static int WIDTH = 700;//The width of the frame
	public static int HEIGHT = 600;//The height of the game
	private int gameSpeed = 300; //how quickly thread updates game in ms
	public static int score = 0; // starting int for score
	public static boolean gameEnd = false; //boolean for if game ended
	AlienArmy army; //alien army
	Ship ship; // ship ai
	Graphics offscreen_high; //graphics to update bufferedImage
	BufferedImage offscreen; //BufferedImage for game

	Image backGroundImage = new javax.swing.ImageIcon("src/Images/backgroundSkin.jpg").getImage(); //Image if the game Background
	Image alienImage= new javax.swing.ImageIcon("src/Images/alien2Skin.gif").getImage(); //Image of the alien




	public InvertedSpaceInvaders() {


		// sets the title of frame, make it non resizable, sets size and location

		setTitle("Inverted Space Invaders              " + "current shooting column selected: " + (AlienArmy.shotrow + 1) + "        "+ "current Score: " + score);
		setResizable(false);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);

		//Create the ship
		ship = new Ship(this);

		//  Create the alien army and adds keylistener to the army
		army = new AlienArmy(ship, this, alienImage);
		addKeyListener(army);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		offscreen = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB); //creates the buffered image
		offscreen_high = offscreen.createGraphics(); // new graphics




		setVisible(true); //sets everything visible
		startGame(); //starts the game


	}


	/*
	 * Handles score if Alien gets hit
	 * get Hit and Lose 5 points
	 */

	public void hitAlienScore() { 
		score -= 5;
		updatetitle(score);

	}


	/*
	 * updates the frame Title when score is updated
	 */
	private void updatetitle(int score ) {

		setTitle("Inverted Space Invaders              " + "current shooting column selected: " + (AlienArmy.shotrow + 1) + "        "+ "current Score: " + score);


	}


	/*
	 * Handles score if ship gets hit
	 * plus 20 points
	 * updates title
	 */
	public void shotShip() {
		score += 20;
		updatetitle(score);

	}

	/*
	 *This class creates and starts the thread
	 */
	public void startGame() {

		//starts new thread that updates game ever 300 ms
		Thread thread = new Thread(this);
		thread.start();
	}

	/*
	 *This class is in charge of initial drawing of the game
	 */
	public void paint(Graphics g) {

		offscreen_high.drawImage(backGroundImage, 0, 0, this); //sets background picture
		army.drawArmy(offscreen_high); //draws the army

		ship.drawShip(offscreen_high); //draws the ship

		g.drawImage(offscreen,0,0,this); //draws everything onto BufferedImage
	}


	/*
	 * This calls the ai to move the ship
	 */
	public void moveShip() {
		ship.moveShip();

	}



	public void run() {
		int count = 0;
		while(true && gameEnd==false) {
			try {
				Thread.sleep(gameSpeed);
			} catch(InterruptedException ie) {
				//Ignore this exception
			}
			//If the game is currently running, move the aliens

			if (count >= 5) { //moves the ship every five times this is ran
				moveShip();
				count = 0;
			}

			repaint();//Update the screen
			count ++;

		}
	}


	//gives a reference to the Alien Army
	public AlienArmy getAlienArmy() {
		return army;

	}




}