import java.awt.*;

/**
 *
 */
public class AlienArmyShot implements Runnable {


    private int shotSpeed = 10;//speed of shot
    

    private int SHOT_WIDTH  = 2;//width of the shot
    private int SHOT_HEIGHT = 5;//length of shot
    
    private int x = 0; //will be used for shot direction

    private int shotHeight = 0;

    boolean shotState = true;

    Ship ship;
    
    /*
     *method for the shot and starting the thread
     */
    public AlienArmyShot(int xVal, int yVal, Ship s) {
        x = xVal;//Set the shot direction
	shotHeight = yVal;
	ship = s;
	Thread thread = new Thread(this);
	thread.start();
    }

    /*
     *Incharge of moving the shot
     */
    private boolean moveShot() {
	
	//if the ship has been hit
	if (ship.checkShot(x, shotHeight)) {
            //We shot the ship
	    ship.hitByAlien();
	    shotState = false;
	    return true;
	}

        shotHeight = shotHeight + 2;

	
	//check we haven't gone off the screen
	if (shotHeight > InvertedSpaceInvaders.HEIGHT) {
	    shotState = false;
	    return true;
	}
	
		
	return false;
    }

    /*
     * Draw the image of the shot
     */    
    public void drawShot(Graphics g) {
	if (shotState) {
            g.setColor(new Color(255,48, 48));
	
	}
        g.fillRect(x, shotHeight, SHOT_WIDTH, SHOT_HEIGHT);
    } 

    public boolean getShotState() {
        return shotState;
    }

    //incharge of calling moveShot method ever 10ms
    public void run() {
        while(true) {
            try {
                Thread.sleep(shotSpeed);
            } catch(InterruptedException ie) {
                //Ignore this exception
            }
	    
	    if (moveShot()) {
                break;
	    }

	}
    }

    

}