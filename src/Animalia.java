/** 
 * The superclass of all animal classes; subclass of lifeform.
 * @author sherryguo
 */
abstract class Animalia extends Lifeform {
	
	/** Certain animals die after x turns without eating. Reset when just eaten. **/
	public int hungerLevel;
	
	/** All animals die after no eating for 5 turns. **/
	public final int fatalLevel = 5;
	
	/** Constructor for animal. Specified in the specified type.**/
	public Animalia(Cell cell) {
		super(cell);
	}
	
	/** Hunger level resets everytime animal eats. **/
	public void eat() {
		this.hungerLevel = 0;
	}
	
	/** Determines how the animal moves on the board. **/
	public abstract void move();

	
}
