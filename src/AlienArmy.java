import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import javax.swing.Timer;

public class AlienArmy implements KeyListener, ActionListener {

	private final Timer bulletTimer = new Timer(1600, this); //amount of time between user shoot imput

	//The army has 3 rows of aliens
	Alien rowOne[] = new Alien[10];
	Alien rowTwo[] = new Alien[10];
	Alien rowThree[] = new Alien[10];

	//The direction the army is moving in
	boolean movingRight = true;

	int moveDistance = 15;
	int moveDirection; // direction of the movement of alien array
	boolean shooting = true; // if user can shoot or not
	int aliensShot; //keeps track of how many aliens have been shot


	int tr[] = new int[3]; //the righest most alien per row
	int tl[]= new int[3]; //the leftest most alien per row



	public static int shotrow = 0; //user defined row for shooting


	Vector alienShots = new Vector();   //A container to store details of the current alien shots

	private Ship ship;

	private InvertedSpaceInvaders spaceInvaders;

	Image alienImage;


	public AlienArmy(Ship s, InvertedSpaceInvaders si, Image ai) {
		ship = s;
		spaceInvaders = si;
		alienImage = ai;

		bulletTimer.start();

		createArmy();
		setStartingPositions();
	}

	/*
	 *  initialise the 3 rows of aliens
	 */
	private void createArmy() {
		for (int i = 0; i < 10; i++) {
			rowOne[i] = new Alien(alienImage, spaceInvaders);
			rowTwo[i] = new Alien(alienImage, spaceInvaders);
			rowThree[i] = new Alien(alienImage, spaceInvaders);//Finally set the third row
		}	
	}

	/*
	 * Set where alien army will start
	 */
	private void setStartingPositions() {

		int rowHeight = 50;//Set the height of the top row
		int leftStart = 50;//Sets the furtherest position to the left
		for (int i = 0; i < 10; i++) {
			rowOne[i].setPosition(leftStart, rowHeight);
			leftStart += 50;
		}
		rowHeight += 50;//Ready for the next row
		leftStart = 50;//Reset the left position
		for (int i = 0; i < 10; i++) {
			rowTwo[i].setPosition(leftStart, rowHeight);
			leftStart += 50;
		}
		rowHeight += 50;//Ready for the third row
		leftStart = 50;//Reset the left position
		for (int i = 0; i < 10; i++) {
			rowThree[i].setPosition(leftStart, rowHeight);
			leftStart += 50;
		}	
	}

	/*
	 * In charge of movements for the alienArmy
	 */
	public void moveArmy(int moveDirection) {


		if (movingRight) {
			//We need to hit right wall to go down

			//First step: Check if the alien furthest to the right has hit the edge
			for (int i = 9; i >= 0; i--) {


				if (!rowOne[i].hasBeenHit()) {
					for (int x = 0; x < 10; x++) {
						if (!rowOne[x].hasBeenHit()) {
							tl[0] = x;
						}
					}
					tr[0] = i;

					//If the alien has not been hit - then it is the edge
					//Now check if the alien if at the edge
					if (rowOne[i].getXPos() > (InvertedSpaceInvaders.WIDTH-Alien.ALIEN_WIDTH-15)) {
						//Change direction
						movingRight = false;
						//Set the new lower y positions
						for (int y = 0; y < 10; y++) {
							rowOne[y].setPosition(rowOne[y].getXPos(), rowOne[y].getYPos()+moveDistance);
							rowTwo[y].setPosition(rowTwo[y].getXPos(), rowTwo[y].getYPos()+moveDistance);
							rowThree[y].setPosition(rowThree[y].getXPos(), rowThree[y].getYPos()+moveDistance);
						}
					}
				}
				if (!rowTwo[i].hasBeenHit()) {
					tr[1] = i;

					for (int x = 0; x < 10; x++) {
						if (!rowTwo[x].hasBeenHit()) {
							tl[1] = x;
						}
					}


					//If the alien has not been hit - then it is the edge
					//Now check if the alien if at the edge
					if (rowTwo[i].getXPos() > (InvertedSpaceInvaders.WIDTH-Alien.ALIEN_WIDTH-15)) {
						//Change direction

						movingRight = false;
						//Set the new lower y positions
						for (int y = 0; y < 10; y++) {
							rowOne[y].setPosition(rowOne[y].getXPos(), rowOne[y].getYPos()+moveDistance);
							rowTwo[y].setPosition(rowTwo[y].getXPos(), rowTwo[y].getYPos()+moveDistance);
							rowThree[y].setPosition(rowThree[y].getXPos(), rowThree[y].getYPos()+moveDistance);
						}

						return;//Return from this method, don't bother checking the rest.

					}		   

				}
				if (!rowThree[i].hasBeenHit()) {
					tr[2] = i;

					for (int x = 0; x < 10; x++) {
						if (!rowThree[x].hasBeenHit()) {
							tl[2] = x;
						}
					}

					//If the alien has not been hit - then it is the edge
					//Now check if the alien if at the edge
					if (rowThree[i].getXPos() > (InvertedSpaceInvaders.WIDTH-Alien.ALIEN_WIDTH-15)) {
						//Change direction
						movingRight = false;
						//Set the new lower y positions
						for (int y = 0; y < 10; y++) {
							rowOne[y].setPosition(rowOne[y].getXPos(), rowOne[y].getYPos()+moveDistance);
							rowTwo[y].setPosition(rowTwo[y].getXPos(), rowTwo[y].getYPos()+moveDistance);
							rowThree[y].setPosition(rowThree[y].getXPos(), rowThree[y].getYPos()+moveDistance);
						}

					}		  

				}		
			}	    

		} else {
			//We are moving left

			//First step: Check if the alien furthest to the left has hit the edge
			for (int i = 0; i < 10; i++) {

				if (!rowOne[i].hasBeenHit()) {
					for (int x = 9; x >= 0; x--) {
						if (!rowOne[x].hasBeenHit()) {
							tr[0] = x;
						}
					}
					tl[0] = i;

					//If the alien has not been hit - then it is the edge
					//Now check if the alien if at the edge
					if (rowOne[i].getXPos() < Alien.ALIEN_WIDTH) {
						//Change direction
						movingRight = true;

						//Set the new lower y positions
						for (int y = 0; y < 10; y++) {
							rowOne[y].setPosition(rowOne[y].getXPos(), rowOne[y].getYPos()+moveDistance);
							rowTwo[y].setPosition(rowTwo[y].getXPos(), rowTwo[y].getYPos()+moveDistance);
							rowThree[y].setPosition(rowThree[y].getXPos(), rowThree[y].getYPos()+moveDistance);
						}
					}

				}

				if (!rowTwo[i].hasBeenHit()) {
					for (int x = 9; x >= 0; x--) {
						if (!rowTwo[x].hasBeenHit()) {
							tr[1] = x;
						}
					}
					tl[1] = i;


					//If the alien has not been hit - then it is the edge
					//Now check if the alien if at the edge
					if (rowTwo[i].getXPos() < Alien.ALIEN_WIDTH) {
						//Change direction
						movingRight = true;

						//Set the new lower y positions
						for (int y = 0; y < 10; y++) {
							rowOne[y].setPosition(rowOne[y].getXPos(), rowOne[y].getYPos()+moveDistance);
							rowTwo[y].setPosition(rowTwo[y].getXPos(), rowTwo[y].getYPos()+moveDistance);
							rowThree[y].setPosition(rowThree[y].getXPos(), rowThree[y].getYPos()+moveDistance);
						}
					}		    

				}

				if (!rowThree[i].hasBeenHit()) {
					for (int x = 9; x >= 0; x--) {
						if (!rowThree[x].hasBeenHit()) {
							tr[2] = x;
						}
					}
					tl[2] = i;

					//If the alien has not been hit - then it is the edge
					//Now check if the alien if at the edge
					if (rowThree[i].getXPos() < Alien.ALIEN_WIDTH) {
						//Change direction
						movingRight = true;

						//Set the new lower y positions
						for (int y = 0; y < 10; y++) {
							rowOne[y].setPosition(rowOne[y].getXPos(), rowOne[y].getYPos()+moveDistance);
							rowTwo[y].setPosition(rowTwo[y].getXPos(), rowTwo[y].getYPos()+moveDistance);
							rowThree[y].setPosition(rowThree[y].getXPos(), rowThree[y].getYPos()+moveDistance);
						}
					}	

				}		
			}
		}


		if (moveDirection == 1 && rowOne[tr[0]].getXPos() > 15 && rowTwo[tr[1]].getXPos() > 15 && rowThree[tr[2]].getXPos() > 15 ){

			//Second step: Move everyone to the left
			for (int i = 0; i < 10; i++  ) {
				rowOne[i].setPosition(rowOne[i].getXPos()-moveDistance, rowOne[i].getYPos());
				rowTwo[i].setPosition(rowTwo[i].getXPos()-moveDistance, rowTwo[i].getYPos());
				rowThree[i].setPosition(rowThree[i].getXPos()-moveDistance, rowThree[i].getYPos());

			}	    

		}else if (moveDirection == 2 && rowOne[tl[0]].getXPos() < (InvertedSpaceInvaders.WIDTH - 50) && rowTwo[tl[1]].getXPos() < (InvertedSpaceInvaders.WIDTH - 50) && rowThree[tl[2]].getXPos() < (InvertedSpaceInvaders.WIDTH-50)){
			for (int i = 0; i < 10; i++) {
				rowOne[i].setPosition(rowOne[i].getXPos()+moveDistance, rowOne[i].getYPos());
				rowTwo[i].setPosition(rowTwo[i].getXPos()+moveDistance, rowTwo[i].getYPos());
				rowThree[i].setPosition(rowThree[i].getXPos()+moveDistance, rowThree[i].getYPos());

			}
		}
		for (int i = 0; i < 10; i++) {
			if (rowThree[i].hasBeenHit()){
				aliensShot++;
			}
		}

		for (int i = 0; i < 10; i++) {
			if (rowTwo[i].hasBeenHit()){
				aliensShot++;
			}
		}

		for (int i = 0; i < 10; i++) {
			if (rowOne[i].hasBeenHit()){
				aliensShot++;
			}
		}

		if (aliensShot == 30){
			endGame();
			InvertedSpaceInvaders.gameEnd = true;
		} else {
			aliensShot =0;
		}


	}

	//incharge if drawing the Alien Army by sending info to Alien class also draws shots aliens have fired
	public void drawArmy(Graphics g) {
		for (int i = 0; i < 10; i++) {
			rowOne[i].drawAlien(g);//Draw the first row
			rowTwo[i].drawAlien(g);//Draw the second row
			rowThree[i].drawAlien(g);//Draw the third row
		} 
		//draw any of the shots the aliens have fired
		Vector tmp = new Vector();
		for (int i = 0; i < alienShots.size(); i++) {
			AlienArmyShot as = (AlienArmyShot)alienShots.elementAt(i);

			if (as.getShotState()) {


				tmp.addElement(as);
			}

			as.drawShot(g);


		}
		alienShots = tmp;



		//checks if y position has reached bottom of board and ends game if it has
		for (int i = 0; i < 10; i++  ) {

			if (!rowThree[i].hasBeenHit()) {
				if (rowThree[i].getYPos() > 500){
					endGame();
					InvertedSpaceInvaders.gameEnd = true;
					return;

				}
			}else if (!rowTwo[i].hasBeenHit()) {
				if (rowTwo[i].getYPos() > 500){
					endGame();
					InvertedSpaceInvaders.gameEnd = true;
					return;
				}

			}else if  (!rowOne[i].hasBeenHit()) {
				if (rowOne[i].getYPos() > 500){
					endGame();
					InvertedSpaceInvaders.gameEnd = true;
					return;
				}
			}
		} 





		if (aliensShot == 30){
			endGame();
			InvertedSpaceInvaders.gameEnd = true;
		}
	}


	//closes the game, sets score for EndgameMenu and closes game
	private void endGame() {
		EndGameMenu.totalScore = spaceInvaders.score;

		new EndGameMenu();
		spaceInvaders.dispose();


	}

	/*
	 * method for collision detection
	 */
	public boolean checkShot(int x, int y) {
		for (int i = 0; i < 10; i++) {
			if (rowOne[i].hitAlien(x, y)) {
				spaceInvaders.hitAlienScore();
				return true;
			}
			if (rowTwo[i].hitAlien(x, y)) {
				spaceInvaders.hitAlienScore();		    
				return true;
			}
			if (rowThree[i].hitAlien(x, y)) {
				spaceInvaders.hitAlienScore();		    
				return true;
			}	    
		}
		return false;
	}

	//key listener
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==37){
			//left arrow key
			moveDirection = 1;
			moveArmy(moveDirection);
		}
		else if (e.getKeyCode()==39){
			//right arrow key
			moveDirection = 2;
			moveArmy(moveDirection);

		}

		if (e.getKeyCode()==32){
			//spacebar
			alienShoot();

		}

		if (e.getKeyCode()==48){


			//num key #0
			shotrow = 9;
			updateTitle();

		}

		if (e.getKeyCode()==49){
			//num key #1
			shotrow = 0;
			updateTitle();
		}

		if (e.getKeyCode()==50){
			//num key #2
			shotrow = 1;
			updateTitle();
		}

		if (e.getKeyCode()==51){
			//num key #3
			shotrow = 2;
			updateTitle();
		}

		if (e.getKeyCode()==52){
			//num key #4
			shotrow = 3;
			updateTitle();
		}

		if (e.getKeyCode()==53){
			//num key #5
			shotrow = 4;
			updateTitle();

		}

		if (e.getKeyCode()==54){
			//num key #6
			shotrow = 5;
			updateTitle();

		}

		if (e.getKeyCode()==55){
			//num key #7
			shotrow = 6;
			updateTitle();

		}
		if (e.getKeyCode()==56){
			//num key #8
			shotrow = 7;
			updateTitle();

		}
		if (e.getKeyCode()==57){
			//num key #9
			shotrow = 8;
			updateTitle();

		}
	}


	//updates the title when someone changes current shooting column
	private void updateTitle() {
		spaceInvaders.setTitle("Inverted Space Invaders              " + "current shooting column selected: " + (shotrow + 1) + "        "+ "current Score: " + InvertedSpaceInvaders.score);

	}

	//in charge of how many shots get made and if a shot can occur
	private void alienShoot() {

		//checks if shot can occur, if it can checks how many aliens in the column are alive and then sends info to alienShot to make new shot


		if (shooting == true){

			if (!rowOne[shotrow].hasBeenHit()) {
				AlienArmyShot as = new AlienArmyShot(rowOne[shotrow].getXPos()+(int)(Alien.ALIEN_WIDTH/2), rowOne[shotrow].getYPos(), ship);
				alienShots.addElement(as);
			}

			if (!rowTwo[shotrow].hasBeenHit()) {	
				AlienArmyShot as = new AlienArmyShot(rowTwo[shotrow].getXPos()+(int)(Alien.ALIEN_WIDTH/2), rowTwo[shotrow].getYPos(), ship);
				alienShots.addElement(as);

			}
			if (!rowThree[shotrow].hasBeenHit()) {	
				AlienArmyShot as = new AlienArmyShot(rowThree[shotrow].getXPos()+(int)(Alien.ALIEN_WIDTH/2), rowThree[shotrow].getYPos(), ship);
				alienShots.addElement(as);

			}
			bulletTimer.start();
			shooting = false;
		} else {
			shooting = false;
		}
	}

	//not used
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	//not used
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==bulletTimer) {

			shooting = true; // when timer occurs, sets shoot true
		}


	}
}
