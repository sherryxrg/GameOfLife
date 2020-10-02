import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Main class that launches the World (board).
 * @author sherryguo
 * @version 1.0
 */
public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Game game = new GameOfLife();
		game.init(); 

		Scene scene = new Scene(game.getWorld().gp);
		
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            System.out.println("mouse click detected!\n");
	            game.update();
	       
	        }
	    });
		
		stage.setScene(scene);
		stage.setTitle("Game of Life v2");
		stage.show();

	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
