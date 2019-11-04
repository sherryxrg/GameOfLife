import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

/**
 * A type of plant with a specific set of behaviours.
 * @author sherryguo
 *
 */
public class PlantA extends Plant {
	
	public PlantA(Cell cell) {
		super(cell);
		cellColor = "Green";

	}
	
	/** what happens in this turn. **/
	@Override 
	public void doturn() {
      seed();
	}

	/** doesn't do anything in this version for plants. **/
	@Override
	public void eat() {
		
	}

	/** In order to seed, a plant must have exactly 4 other plants nearby, 
	 *  and at least 3 empty cells. Seed is sent randomly to one of the empty cells. **/
	@Override
	public void seed() {
		//System.out.println("my current position is at: " + pos.getPosX() + " " + pos.getPosY());
		
		ArrayList<Cell> seedableSpaces = new ArrayList<>();
		int pcount = 0;
		
		ArrayList<Cell> myNeighbours = pos.getNeighbours();
		for (Cell item: myNeighbours) {
			
			if (item.getLifeform() instanceof Plant) {
				pcount++;
				continue;
			} else if (item.getLifeform() instanceof Animal) {
				continue;
			} 
				seedableSpaces.add(item);
			
		}
		
		if (pcount == 4 && seedableSpaces.size() >= 3) {
			for (Cell item: seedableSpaces) {
				pos.getWorld().setWorldCells(item.getPosX(), item.getPosY(), new Cell(item.getPosX(), item.getPosY(), 0, pos.getWorld()));
			}
		}
		
	}
	

}
