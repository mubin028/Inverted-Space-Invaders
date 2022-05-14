import java.awt.*;
/**
 * The Alien class. 
 */
public class Alien {

	public static int ALIEN_HEIGHT = 40; // height of the alien
	public static int ALIEN_WIDTH = 40; //width of the alien

	private int leftPosition = 0; //current x position of the alien
	private int heightPosition = 0;	//current height of the alien

	private boolean hitState = false;//Whether this alien has already been shot

	InvertedSpaceInvaders spaceInvaders = null; 
	Image alienImage= new javax.swing.ImageIcon("src/Images/alien2Skin.gif").getImage(); //image of the aliens


	/*
	 * class gets info from alienInvaders class and then assigns it to local variables;
	 */
	
	public Alien(Image ai, InvertedSpaceInvaders si) {
		alienImage = ai;
		spaceInvaders = si;
	}

	/*
	 * Returns whether the alien had been hit
	 */
	public boolean hasBeenHit() {
		return hitState;
	}

	/*
	 * Check if a shot fired hit an alien
	 */
	public boolean hitAlien(int x, int y) {

		//Is the alien currently alive?
		if (hitState) {
			//If its been shot then return false;
			return false;
		}

		// check the X range
		if ((x >= leftPosition) && (x <= (leftPosition+ALIEN_WIDTH))) {
			//check the Y range
			if ((y >= heightPosition) && (y <= (heightPosition+ALIEN_HEIGHT))) {
				//if true, We shot an alien
				hitState = true;
				return true;
			}
		} 
		return false;
	}

	/*
	 * Set the position of the alien on the screen
	 */
	public void setPosition(int x, int y) {
		leftPosition = x;
		heightPosition = y;
	}

	/*
	 * Returns the current x position of the alien
	 */
	public int getXPos() {
		return leftPosition;
	}

	/*
	 * Returns the current x position of the alien
	 */
	public int getYPos() {
		return heightPosition;
	}

	/*
	 * Draw the image of the Alien onto the screen 
	 */ 
	public void drawAlien(Graphics g) {
		if (!hitState) {
			g.drawImage(alienImage, leftPosition, heightPosition, spaceInvaders);
		}
	}

}