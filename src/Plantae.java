/** 
 * The superclass of all plant classes; subclass of lifeform.
 * @author sherryguo
 */
public abstract class Plantae extends Lifeform {

	public Plantae(Cell cell) {
		super(cell);
	}
	
	/** what happens in this turn. **/
	@Override 
	public abstract void doturn();
	
	public abstract void giveBirth();

}
