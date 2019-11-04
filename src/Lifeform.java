/**
 * All living things in the game. Plants and Animals.
 * @author sherryguo
 *
 */
public abstract class Lifeform {
	
	/** Depends on what lifeform it is. **/
	public String cellColor;
	
	/** Certain animals die after x turns without eating. Reset when just eaten. **/
	private int hungerLevel;
	
	/** Number of turns it takes until death. **/
	private int fatalLevel;
	
	/** The cell lifeform is currently on. **/
	Cell pos;
	
	/** A temporary location to hold the new location pos will be set to. **/
	Cell newPos;

	
	/**
	 * Each lifeform takes a cell as parameter to keep track of where 
	 * @param cell a Cell the lifeform is currently on.
	 */
	public Lifeform(Cell cell) {
		this.pos = cell;
		// System.out.println(cell.getPosX());
	}
	
	/** Each specific lifeform has their way of  **/
	public abstract void doturn();
	
	/** eat method; differs depending on lifeform type. **/
	public abstract void eat();
	
	public String getColor() {
		return cellColor;
	}
	
	/** on the lifeforms current cell, make another null cell on top. **/
	public void die() {
		pos.getWorld().setWorldCells(pos.getPosX(), pos.getPosY(), new Cell(pos.getPosX(), pos.getPosY())); 
	}

}
