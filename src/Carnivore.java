import java.util.ArrayList;
import java.util.Random;

public class Carnivore extends Animalia {

	public Carnivore(Cell cell) {
		super(cell);
		m_cellColor = "Red";
		hungerLevel = 0;
	}
	
	/** One unit of time passes, it does its thing. **/
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

	/** Can move any nearby space unless occupied by carnivore or plant.
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
		if (!(m_newPos.getLifeform() instanceof Plantae)) {
			
			oldX = m_pos.getPosX();
			oldY = m_pos.getPosY();
			
			m_pos.setPosX(m_newPos.getPosX());
			m_pos.setPosY(m_newPos.getPosY());
			
			m_pos.getWorld().setWorldCells(m_pos.getPosX(), m_pos.getPosY(), m_pos);
			m_pos.getWorld().setWorldCells(oldX, oldY, new Cell(oldX, oldY));
			
			if (m_newPos.getLifeform() instanceof Herbivore || m_newPos.getLifeform() instanceof Omnivore) {
				this.eat();
			}
		}
	}

	/** Rules of Life:
	 * - If does not eat within 5 turns, dies. Eats everything else except its own kind.
	 * - to giveBirth() must have at least 1 mate, 2 spots with food, and 3 free spaces. **/
	@Override
	public void giveBirth() {
		ArrayList<Cell> birthSpaces = new ArrayList<>();
		int matecount = 0;
		int foodcount = 0;
		
		ArrayList<Cell> myNeighbours = m_pos.getNeighbours();
		for (Cell item: myNeighbours) {
			
			if (item.getLifeform() instanceof Herbivore || item.getLifeform() instanceof Omnivore) {
				foodcount++;
				continue;
			} else if (item.getLifeform() instanceof Carnivore) {
				matecount++;
				continue;
			} else if (item.getLifeform() instanceof Animalia) {
				continue;
			} 
				birthSpaces.add(item);
		}
		
		if (matecount >= 1 && foodcount >= 2 && birthSpaces.size() >= 3) {
			Random rand = new Random();
			int ln = rand.nextInt(birthSpaces.size());
			
				m_pos.getWorld().setWorldCells(birthSpaces.get(ln).getPosX(), birthSpaces.get(ln).getPosY(),
						new Cell(birthSpaces.get(ln).getPosX(), birthSpaces.get(ln).getPosY(), 
								"carnivore", m_pos.getWorld()));
		}

	}

}
