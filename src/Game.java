import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Superclass for all games.
 * @author sherryguo
 *
 */
public class Game {
	
	World m_world;
	
	/** width and height of the board, used for creating World board. **/
	public int m_width; 
	public int m_height;
	
	public void init() {
		World initworld = new World(m_width,m_height);
		this.m_world = initworld;
		
	}

	/** return the current world. **/
	public World getWorld() {
		return m_world;
	}

	/** refreshes the world board into its next cycle. **/
	public void update() {
		
		//m_world.gp.getChildren().clear();
		this.getWorld().nextCycle();
		
		for (int row = 0; row < m_world.getBoardWidth(); row++) {
			for (int col = 0; col < m_world.getBoardHeight(); col++) {
				Rectangle square = new Rectangle(30, 30);
				
				square.setFill(Paint.valueOf(m_world.getCellBoard()[row][col].getLifeformColor()));
				square.setStroke(Color.BLACK);
				
				m_world.gp.add(square, row, col);
			}
		}
		
	}

}
