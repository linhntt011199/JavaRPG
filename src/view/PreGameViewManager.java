package view;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MODE;
import model.RPGSubScene;

public class PreGameViewManager {
	private AnchorPane preGamePane;
	private Scene preGameScene;
	private Stage preGameStage;
	
	private Stage menuStage;
	
	public static final String SPLASH_GIF ="model/gif/loading_subscene.gif";
	public static final String SPLASH_GIF1 ="model/gif/loading_game.gif";
	
	private static final int HEIGHT = 260;
	private static final int WIDTH = 550;
	
	public PreGameViewManager() {
		preGamePane = new AnchorPane();
		preGameScene = new Scene(preGamePane,WIDTH,HEIGHT);
		preGameStage = new Stage();
		preGameStage.setScene(preGameScene);
		createLoadingScene();
	}
	
	private void createLoadingScene( ) {
		RPGSubScene loadingScene = new RPGSubScene(SPLASH_GIF,500,256);
		loadingScene.setLayoutX(20);
		loadingScene.setLayoutY(5);
		preGamePane.getChildren().add(loadingScene);
		
		ImageView i = new ImageView(SPLASH_GIF1);
		i.setLayoutX(50);
		i.setLayoutY(6);
		loadingScene.getPane().getChildren().add(i);
		PauseTransition visiblePause = new PauseTransition(Duration.seconds(7));
		visiblePause.setOnFinished(event->loadingScene.setVisible(false));
		visiblePause.play();
	}
	
	public void createPreNewGame(Stage menuStage, MODE chosenMode) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		preGameStage.show();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameViewManager gameView = new GameViewManager();
		gameView.createNewGame(preGameStage, chosenMode);
	}
	
//	static void pause(long millisecond){
//	    long Time0 = System.currentTimeMillis();
//	    long Time1;
//	    long runTime = 0;
//	    while (runTime < millisecond) { // 1000 milliseconds = 1 second
//	        Time1 = System.currentTimeMillis();
//	        runTime = Time1 - Time0;
//	    }
//	}
}
