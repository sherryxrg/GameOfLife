import java.util.ArrayList;

/** 
 * Cells that make up World (board).
 * @author sherryguo
 *
 */
public class Cell {
	
	/** Current inhabitant **/
	public World m_cellWorld;
	
	/** Current inhabitant **/
	public Lifeform m_lifeform;
	
	/** Cell's position **/
	private int m_posX;
    private int m_posY;
    
    /** Creates a cell populated with lifeform at random  **/
	public Cell(int x, int y, World w) {
		
		this.m_cellWorld = w;
		
		this.m_posX = x;
		this.m_posY = y;
		
		int creationNum = RandomGenerator.nextNumber(99);
		
		if (creationNum >= 80) {
			
			this.m_lifeform = new Herbivore(this);
			
		} else if (creationNum >= 60) {
			
			this.m_lifeform = new PlantA(this); 
			
		} else if (creationNum >= 50) {
			
			this.m_lifeform = new Carnivore(this);
			
		} else if (creationNum >= 45) {
			
			this.m_lifeform = new Omnivore(this);
			
		} else {
			
			this.m_lifeform = null;
		}
	}
	
	/**
	 * Seperate constructor for setting the old herbivore location to null.
	 * @param oldX
	 * @param oldY
	 */
	public Cell(int oldX, int oldY) {
		this.m_lifeform = null;
		
		this.m_posX = oldX;
		this.m_posY = oldY;
	}
	
	/**
	 * Seperate constructor for germinating new babies.
	 * @param plantX the x coordinate of where new baby will grow.
	 * @param plantY the y coordinate of where new baby will grow.
	 * @param p to differentiate from another constructor signature.
	 * @param w the world (board) this cell belongs to. Otherwise it won't know world's height/width.
	 */
	public Cell(int plantX, int plantY, String life, World w) {
		
		if (life.equals("plant")) {
			this.m_lifeform = new PlantA(this);
		} else if (life.equals("herbivore")) {
			this.m_lifeform = new Herbivore(this);
		} else if (life.equals("omnivore")) {
			this.m_lifeform = new Omnivore(this);
		} else if (life.equals("carnivore")) {
			this.m_lifeform = new Carnivore(this);
		}
		
		this.m_cellWorld = w;
		
		this.m_posX = plantX;
		this.m_posY = plantY;
	}
	
	/** Arraylist of 8 cells surrounding the cell. **/
	public ArrayList<Cell> getNeighbours() {
		ArrayList<Cell> neighbours = new ArrayList<>();
		Cell currCell;
		
		for (int row = m_posX-1; row < m_posX+2; row++) {
			for (int col  = m_posY-1; col < m_posY+2; col++) {
				if (row == m_posX && col == m_posY) {
					continue;
				}

				if (row >= 0 && row < m_cellWorld.getBoardHeight() && col >= 0 && col < m_cellWorld.getBoardWidth()) {
                    currCell = this.m_cellWorld.getCellAt(row, col);
                    neighbours.add(currCell);
      
                }
			}
		}
		return neighbours;
	} 
	
	/** Get attributes of inhabitant lifeform.
	 * Sets the lifeforms supposed color to grey if theres no lifeform. **/
	public String getLifeformColor() {
		
		if (m_lifeform == null) {
			return "Grey";
		}
		
		return m_lifeform.getColor();
	}
	
	public Lifeform getLifeform() {
		return this.m_lifeform;
	}
	
	/** Gets/sets the positions of this cell **/
	public int getPosX() {
		return this.m_posX;
	}
	
	public int getPosY() {
		return this.m_posY;
	}
	
	public void setPosX(int newX) {
		this.m_posX = newX;
	}
	
	public void setPosY(int newY) {
		this.m_posY = newY;
	}
	
	/** Gets the world the cell is on. Exists to use methods in World class. **/
	public World getWorld() {
		return this.m_cellWorld;
	}
	
	
}
