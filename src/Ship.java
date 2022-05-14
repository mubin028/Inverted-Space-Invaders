
import java.awt.event.*;
import java.util.Random;

import javax.swing.Timer;

//Needed for the graphics
import java.awt.*;

public class Ship{

    public static int SHIP_HEIGHT = 50; //height
    public static int SHIP_WIDTH = 50; //Width

    private int shipX = 0; //ship x pos
    private int heightPosition = 0; //ship height (y)
   boolean movingRight = true; //direction of the ship

    InvertedSpaceInvaders spaceInvaders;

    
    ShipShot shot; //the shot from the ship
    Image shipImage= new javax.swing.ImageIcon("src/Images/shipSkin.gif").getImage(); //image of the ship
    boolean hitState = false; //if hit
	int count = 2;

    /*
     *creates the ship, sets starting x position and height position
     */
    public Ship(InvertedSpaceInvaders si) {
        spaceInvaders = si;
        
        shipX = (int)((InvertedSpaceInvaders.WIDTH/2)+(SHIP_WIDTH/2)); // sets the starting x position of ship in middle
        heightPosition = InvertedSpaceInvaders.HEIGHT-SHIP_HEIGHT-20; // sets the height of the ship
    }

 
    public void drawShip(Graphics g) {
    	g.drawImage(shipImage, shipX, heightPosition, spaceInvaders);
        //If the shot is still alive
        if ((shot != null) && (shot.getShotState())) {
            shot.drawShot(g);
        }
    }

    /**
     * Check if a shot fired by an alien hit the ship
     */
    public boolean checkShot(int xShot, int yShot) {

        //First lets check the X range
        if ((xShot >= shipX) && (xShot <= (shipX+SHIP_WIDTH))) {
            //X is ok, now lets check the Y range
            if ((yShot >= heightPosition) && (yShot <= (heightPosition+SHIP_HEIGHT))) {
                //The ship was hit!
                hitState = true;
                return true;
            }
        } 
        return false;
    }

    public void hitByAlien() {
        spaceInvaders.shotShip();
    }

    	
    //this is basic ai to make the ship move left and right and shoot
	public void moveShip() {
		
		Random rand = new Random();
		int n = rand.nextInt(4) + 1;


		if (movingRight == true) {
			//We are moving right



	// if x is less than right wall x
			if (shipX < 500) {
				shipX = shipX + 15;
				

			//if  ship hits wall
			}else if ( shipX >= 500){
				movingRight = false;
				shipX = shipX - 15;
			}
		} 
		
		if (movingRight == false){
			//We are moving left


			// if it higher that x of lefft wall
			if (shipX > 0) {
				
				shipX = shipX - 15;
				

			}else if (shipX <= 0){
				
				shipX = shipX + 15;
				movingRight = true;

			}	

		}	   
	if (count == 3){
        AlienArmy army = spaceInvaders.getAlienArmy();
        shot = new ShipShot(shipX+(int)(SHIP_WIDTH/2), heightPosition, army);
        count = 0;
	}

	count++;	
	}
	}


