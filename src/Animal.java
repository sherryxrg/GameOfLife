/** 
 * The superclass of all animal classes; subclass of lifeform.
 * @author sherryguo
 */
abstract class Animal extends Lifeform {
	
	/** Constructor for animal. **/
	public Animal(Cell cell) {
		super(cell);
	}
	
	/** Determines how the animal moves on the board. **/
	public abstract void move();

	
}
