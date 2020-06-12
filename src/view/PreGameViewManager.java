package view;

import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MODE;

public class PreGameViewManager {
	private Scene preGameScene;
	private Stage preGameStage;
	
	private Stage menuStage;
	
	public static final String SPLASH_GIF ="model/gif/loading_subscene.gif";
	public static final String SPLASH_GIF1 ="model/gif/loading_game.gif";
	
	
	
//	private static final int HEIGHT = 260;
//	private static final int WIDTH = 550;
	
	public PreGameViewManager(){
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("loading.fxml"));
		try {
			Parent root = (Parent) loader.load();
		  	preGameScene = new Scene(root);
			preGameStage = new Stage();
			preGameStage.setScene(preGameScene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createPreNewGame(Stage menuStage, MODE chosenMode) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		preGameStage.show();
		PauseTransition delay = new PauseTransition(Duration.seconds(7.5));
		delay.setOnFinished( event -> {
			preGameStage.hide();
			GameViewManager gameView = new GameViewManager();
			gameView.createNewGame(preGameStage, chosenMode);
		});
		delay.play();
	}
}
