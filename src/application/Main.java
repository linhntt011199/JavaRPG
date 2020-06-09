package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.MODE;
import view.GameViewManager;
import view.ViewManager;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ViewManager manager = new ViewManager();
			primaryStage = manager.getMainStage();
			
			GameViewManager game = new GameViewManager();
			game.createNewGame(primaryStage, MODE.EASY);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
