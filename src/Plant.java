
public abstract class Plant extends Lifeform {

	public Plant(Cell cell) {
		super(cell);
	}
	
	/** what happens in this turn. **/
	@Override 
	public void doturn() {
		
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		
	}
	
	public abstract void seed();

}
