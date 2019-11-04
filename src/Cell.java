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
	private int posX;
    private int posY;
    
    /** Creates a cell; the  **/
	public Cell(int x, int y, World w) {
		
		this.m_cellWorld = w;
		
		this.posX = x;
		this.posY = y;
		
		int creationNum = RandomGenerator.nextNumber(99);
		
		if (creationNum >= 85) {
			
			this.m_lifeform = new Herbivore(this);
			
		} else if (creationNum >= 65) {
			
			this.m_lifeform = new PlantA(this); 
			
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
		
		this.posX = oldX;
		this.posY = oldY;
	}
	
	/**
	 * Seperate constructor for germinating new plants.
	 * @param plantX the x coordinate of where new plant will grow.
	 * @param plantY the y coordinate of where new plant will grow.
	 * @param p to differentiate from another constructor signature.
	 * @param w the world (board) this cell belongs to. Otherwise it won't know world's height/width.
	 */
	public Cell(int plantX, int plantY, int p, World w) {
		this.m_lifeform = new PlantA(this);
		this.m_cellWorld = w;
		
		this.posX = plantX;
		this.posY = plantY;
	}
	
	/** Arraylist of 8 cells surrounding the cell. **/
	public ArrayList<Cell> getNeighbours() {
		ArrayList<Cell> neighbours = new ArrayList<>();
		Cell currCell;
		
		for (int row = posX-1; row < posX+2; row++) {
			for (int col  = posY-1; col < posY+2; col++) {
				if (row == posX && col == posY) {
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
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public void setPosX(int newX) {
		this.posX = newX;
	}
	
	public void setPosY(int newY) {
		this.posY = newY;
	}
	
	/** Gets the world the cell is on. Exists to use methods in World class. **/
	public World getWorld() {
		return this.m_cellWorld;
	}
	
	
}
