import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;
/** 
 * A kind of Animal that eats plants, herbivores, omnivores to survive. 
 * @author sherryguo
 *
 */
public class Omnivore extends Animalia {

	public Omnivore(Cell cell) {
		super(cell);
		m_cellColor = "Blue";
		hungerLevel = 0;
	}
	
	/** One unit of time passes, lifeform does its thing. **/
	@Override
	public void doturn() {
		hungerLevel++;
		
		if (hungerLevel == fatalLevel) {
			this.die();
		} else {
			this.move();
			this.giveBirth();
		}
	}

	/** Can move onto any space other than one occupied by an omnivore.
	 * If space is not empty, it eats whatever is there. **/
	@Override
	public void move() {
		int oldX;
		int oldY;
		int moveToIndex;
		
		ArrayList<Cell> myNeighbours = m_pos.getNeighbours();
		
		Random rand = new Random();
		moveToIndex = rand.nextInt(myNeighbours.size());
		
		m_newPos = myNeighbours.get(moveToIndex);
		
		// newPosition is initially the same as old position
		if (!(m_newPos.getLifeform() instanceof Omnivore)) {
			
			oldX = m_pos.getPosX();
			oldY = m_pos.getPosY();
			
			m_pos.setPosX(m_newPos.getPosX());
			m_pos.setPosY(m_newPos.getPosY());
			
			m_pos.getWorld().setWorldCells(m_pos.getPosX(), m_pos.getPosY(), m_pos);
			m_pos.getWorld().setWorldCells(oldX, oldY, new Cell(oldX, oldY));
			
			if (m_newPos.getLifeform() instanceof Plantae || m_newPos.getLifeform() instanceof Carnivore || m_newPos.getLifeform() instanceof Herbivore) {
				this.eat();
			}
		}

	}

	/** Rules of Life:
	 * - If does not eat within 5 turns, dies. Eats everything else except its own kind.
	 * - to giveBirth() must have at least 1 mate, 1 spots with food, and 2 free spaces. **/
	@Override
	public void giveBirth() {
		ArrayList<Cell> birthSpaces = new ArrayList<>();
		int matecount = 0;
		int foodcount = 0;
		
		ArrayList<Cell> myNeighbours = m_pos.getNeighbours();
		for (Cell item: myNeighbours) {
			
			if (item.getLifeform() instanceof Plantae || item.getLifeform() instanceof Carnivore || item.getLifeform() instanceof Herbivore) {
				foodcount++;
				continue;
			} else if (item.getLifeform() instanceof Omnivore) {
				matecount++;
				continue;
			} else if (item.getLifeform() instanceof Animalia) {
				continue;
			} 
				birthSpaces.add(item);
		}
		
		if (matecount >= 1 && foodcount >= 1 && birthSpaces.size() >= 3) {
			Random rand = new Random();
			int ln = rand.nextInt(birthSpaces.size());
			
				m_pos.getWorld().setWorldCells(birthSpaces.get(ln).getPosX(), birthSpaces.get(ln).getPosY(),
						new Cell(birthSpaces.get(ln).getPosX(), birthSpaces.get(ln).getPosY(), 
								"omnivore", m_pos.getWorld()));
		}

	}

}
