import java.awt.*;

public class ShipShot implements Runnable {
    private int shotSpeed = 10; //speed of the shipShot

    private int SHOT_WIDTH = 2; //width of shot
    private int SHOT_HEIGHT = 5; //height of shot

    private int x = 0; //hold shot direction

    private int shotHeight = 0;

    boolean shotState = true;

    AlienArmy alienArmy;

    /*
     *method for the shot
     */
    public ShipShot(int xVal, int yVal, AlienArmy aa) {
        x = xVal;//Set the shot direction
        shotHeight = yVal;
        alienArmy = aa;
        Thread thread = new Thread(this); //creates the thread for the shot
        thread.start();//starts the thread
    }

    /*
     * Incharge of moving the shot
     */
    private boolean moveShot() {

        //see if we've hit anything!
        if (alienArmy.checkShot(x, shotHeight)) {
            //We hit something!
            shotState = false;
            return true;
        }


        shotHeight -= 2; 

        //check we haven't gone off the screen
        if (shotHeight < 0) {
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
            g.setColor(Color.white);
        }
        g.fillRect(x, shotHeight, SHOT_WIDTH, SHOT_HEIGHT);
    }

    public boolean getShotState() {
        return shotState;
    }

    /*
     * incharge of calling the moveShot method every 10ms;
     */ 
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