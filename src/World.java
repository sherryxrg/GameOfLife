import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/** 
 * The world has lifeforms; basically the board lifeforms are on.
 * @author sherryguo
 *
 */
public class World extends GridPane {
	
	Cell cells[][];
	
	/** Holds the visual representation of cells (rectangles).
	 *  Color depends on the lifeform,
	 *  but has no effect on the cells[][] array. **/
	public static GridPane gp;
	
	/** Dimensions of the world board. **/
	private int height;
	private int width;
	
	/** A single square representing a cell of cells 2D array. **/
	public Rectangle square;
	
	/**
	 * Creates a world board in 2 ways. 
	 * First, cells 2D array is filled by cells carrying random lifeforms.
	 * Second, squares corresponding to the occupant lifeform's color is added to gridpane.
	 * 
	 * @param boardWitdth
	 * @param boardHeight
	 */
	public World(int bwidth, int bheight) {
		
		this.width = bwidth;
		this.height = bheight;
		
		gp = new GridPane();
        this.cells = new Cell[width][height];
		
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				square = new Rectangle(30, 30);
				
				this.cells[row][col] = new Cell(row, col, this);
				
				square.setFill(Paint.valueOf(cells[row][col].getLifeformColor()));
				square.setStroke(Color.BLACK);
				
				gp.add(square, row, col);
			}
		}
		
	}
	
	/** Gets the height and width of the 2D cells array. **/
	public int getBoardHeight() {
		return this.height;
	}
	
	public int getBoardWidth() {
		return this.width;
	}
	
	/** Returns the board from   **/
	public Cell[][] getCellBoard() {
		return cells;
	}
 
	
	/** Pushes the world into the next cycle. **/
	public void nextCycle() {
		this.updateCells();
		
	}
	
	/** Gets the cell at a specified location. **/
	public Cell getCellAt(int x, int y) {
		return this.cells[x][y];
	}
	
	/** Goes through each cell occupied by a lifeform, and does its turn. **/
	public void updateCells() {
		
		ArrayList<Lifeform> living = this.getOccupiedCells();
		
		for (Lifeform item: living) {
			item.doturn();
//			System.out.println(item.getColor() + " has finished a turn.");
//			System.out.println("-------------------");
//			
		}
		
	}
	
	/** Gets all the currently non-null cells in the 2D arrays.
	 * 	Getting a list of all occupied cells first eliminates the
	 *  issue of new 'seedlings' seeding again.
	 *  
	 *  @return arraylist of all occupants currently on board. **/
	public ArrayList<Lifeform> getOccupiedCells() {
		
		ArrayList<Lifeform> occupants = new ArrayList<Lifeform>();
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (cells[row][col].getLifeform() != null) {
					
					occupants.add(cells[row][col].getLifeform());
					//System.out.println("Occupant added from " + row + ", " + col);
				}
			}
		}
		
		return occupants;
		
	}
	
	/** Important! After lifeforms change cell locations,
	 *  this method updates the cells 2D array in world to reflect changes.**/
	public void setWorldCells(int row, int col, Cell cell) {
		this.cells[row][col] = cell;
		
	}
	
	

}
