import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

/** 
 * A kind of Animal that eats plants to survive. 
 * @author sherryguo
 *
 */
public class Herbivore extends Animal {
	
	/** Herbivore dies after hungerLevel reaches fatalLevel. Reset when just eaten. **/
	private int hungerLevel;
	
	/** Number of turns it takes until death. **/
	private final int fatalLevel = 5;
	
	/** Herbivore constructor. **/
	public Herbivore(Cell cell) {
		super(cell);
		
		cellColor = "Yellow";
		hungerLevel = 0;
		
	}
	
	/** What happens in this turn. Varies by lifeform.**/
	@Override 
	public void doturn() {
		
		hungerLevel++;
		
		if (hungerLevel == fatalLevel) {
			this.die();
		} else {
		
		this.move();
		
		}
		
		//System.out.println("<<In herbivore's doturn>>");
		
	}
	
	/** Hunger level resets everytime animal eats. **/
	@Override
	public void eat() {
		this.hungerLevel = 0;
	}
	
	/** Herbivores can only move to adjacent squares no occupied by another herbivore.
	 * 	If the square it moves to contains a plant, its hungerlevel is reset,
	 *  and distances itself from doom... **/
	public void move() { 
		
		int oldX;
		int oldY;
		int moveToIndex;
		
		//System.out.println("my current position is at: " + pos.getPosX() + " " + pos.getPosY());
			
		// call getneighbour, choose a neighbour
		ArrayList<Cell> myNeighbours = pos.getNeighbours();
		for (Cell item: myNeighbours) {
			//System.out.println("neighbour: " + item.getPosX() + " " + item.getPosY());
		}
		
		Random rand = new Random();
		//System.out.println("size of neighbours array: " + myNeighbours.size());
		moveToIndex = rand.nextInt(myNeighbours.size());
		
		newPos = myNeighbours.get(moveToIndex);
		
		// newPosition is initially the same as old position
		if (!(newPos.getLifeform() instanceof Animal)) {
			//System.out.println("moving to..." + newPos.getPosX() + " " + newPos.getPosY());
			
			oldX = pos.getPosX();
			oldY = pos.getPosY();
			
			pos.setPosX(newPos.getPosX());
			pos.setPosY(newPos.getPosY());
			//System.out.println("the new position is: " + pos.getPosX() + " " + pos.getPosY());
			
			pos.getWorld().setWorldCells(pos.getPosX(), pos.getPosY(), pos);
			pos.getWorld().setWorldCells(oldX, oldY, new Cell(oldX, oldY));
			
			if (newPos.getLifeform() instanceof Plant) {
				this.eat();
			}
		}

		
	}
	

}
