/**
 * All living things in the game. Plants and Animals.
 * @author sherryguo
 *
 */
public abstract class Lifeform {
	
	/** Depends on what lifeform it is. **/
	public String m_cellColor;
	
	/** The cell lifeform is currently on. **/
	Cell m_pos;
	
	/** A temporary location to hold the new location pos will be set to. **/
	Cell m_newPos;

	/**
	 * Each lifeform takes a cell as parameter to keep track of where 
	 * @param cell a Cell the lifeform is currently on.
	 */
	public Lifeform(Cell cell) {
		this.m_pos = cell;
		// System.out.println(cell.getPosX());
	}
	
	/** Each specific lifeform has their way of  **/
	public abstract void doturn();
	
	/** creates any kind of new lifeform **/
	public abstract void giveBirth();
	
	public String getColor() {
		return m_cellColor;
	}
	
	/** sets the cell to null by creating a new null cell on top of the old one. **/
	public void die() {
		m_pos.getWorld().setWorldCells(m_pos.getPosX(), m_pos.getPosY(), new Cell(m_pos.getPosX(), m_pos.getPosY())); 
	}

}
