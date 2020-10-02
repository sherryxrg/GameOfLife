import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

/**
 * A type of plant with a specific set of behaviours.
 * @author sherryguo
 *
 */
public class PlantA extends Plantae {
	
	public PlantA(Cell cell) {
		super(cell);
		m_cellColor = "Green";

	}
	
	/** what happens in this turn. **/
	@Override 
	public void doturn() {
      giveBirth();
	}

	/** Rules of Life:
	 * - never dies unless eaten
	 * - to giveBirth() must have at least 2 mates and 3 free spaces. **/
	@Override
	public void giveBirth() {
		//System.out.println("my current position is at: " + pos.getPosX() + " " + pos.getPosY());
		
		ArrayList<Cell> birthSpaces = new ArrayList<>();
		int pcount = 0;
		
		ArrayList<Cell> myNeighbours = m_pos.getNeighbours();
		for (Cell item: myNeighbours) {
			
			if (item.getLifeform() instanceof Plantae) {
				pcount++;
				continue;
			} else if (item.getLifeform() instanceof Animalia) {
				continue;
			} 
				birthSpaces.add(item);
		}
		
		if (pcount >= 2 && birthSpaces.size() >= 3) {
			Random rand = new Random();
			int ln = rand.nextInt(birthSpaces.size());
			
				m_pos.getWorld().setWorldCells(birthSpaces.get(ln).getPosX(), birthSpaces.get(ln).getPosY(),
						new Cell(birthSpaces.get(ln).getPosX(), birthSpaces.get(ln).getPosY(), 
								"plant", m_pos.getWorld()));
		}
		
	}
	

}
