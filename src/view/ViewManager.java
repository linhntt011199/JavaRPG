package view;

//import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.GameButton;
import model.InfoLabel;
import model.MODE;
import model.ModeChooser;
import model.MenuSubScene;

public class ViewManager {
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 700;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private static final int MENU_BUTTONS_START_X = 100;
	private static final int MENU_BUTTONS_START_Y = 150;
	
	public final static String MUSIC_PATH = "src/view/resources/introMusic.mp3";
	
	private MenuSubScene playSubScene;
	private MenuSubScene scoresSubScene;
	private MenuSubScene helpSubScene;
	private MenuSubScene creditsSubScene;
	
	private MenuSubScene sceneToHide;
	
	List<GameButton> menuButtons;
	List<ModeChooser> modeList;
	private MODE choosenMode;
	
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createSubScene();
		createButtons();
		createBackground();
		createLogo();
//		createMusic();
	}
	
//	private void createMusic() {
//		Media music = new Media(new File(MUSIC_PATH).toURI().toString());  
//		MediaPlayer mediaPlayer = new MediaPlayer(music);
//		mediaPlayer.setAutoPlay(true);
//	}

	private void showSubScene(MenuSubScene subScene) {
//		if (sceneToHide == null) {
//			subScene.moveSubScene();
//			System.out.println("Case 1");
//		} else if (sceneToHide != null && sceneToHide == subScene) {
//			subScene.moveSubScene();
//			System.out.println("Case 2");
//		} else if (sceneToHide != null && sceneToHide != subScene && sceneToHide.isHidden() == false) {
//			sceneToHide.moveSubScene();
//			subScene.moveSubScene();
//			System.out.println("Case 3");
//		} else if (sceneToHide != null && sceneToHide != subScene && sceneToHide.isHidden() == true){
//			subScene.moveSubScene();
//			System.out.println("Case 4");
//		}
		
		if (sceneToHide != null && sceneToHide != subScene && sceneToHide.isHidden() == false) {
			sceneToHide.moveSubScene();
			subScene.moveSubScene();
		} else {
			subScene.moveSubScene();
		}
		sceneToHide = subScene;
	}
	
	private void createSubScene() {
		scoresSubScene = new MenuSubScene();
		mainPane.getChildren().add(scoresSubScene);
		
		helpSubScene = new MenuSubScene();
		mainPane.getChildren().add(helpSubScene);
		
//		creditsSubScene = new MenuSubScene("/test.fxml");
//		Text string = new Text("...");
//		string.setLayoutX(110);
//		string.setLayoutY(40);
//		creditsSubScene.getPane().getChildren().add(string);
		creditsSubScene = new MenuSubScene();
		mainPane.getChildren().add(creditsSubScene);
		
		createLevelChooserSubScene();
	}
	
	private void createLevelChooserSubScene() {
		playSubScene = new MenuSubScene();
		mainPane.getChildren().add(playSubScene);
		InfoLabel chooseLevelLabel = new InfoLabel("CHOOSE MODE");
		chooseLevelLabel.setLayoutX(110);
		chooseLevelLabel.setLayoutY(40);
		playSubScene.getPane().getChildren().add(chooseLevelLabel);
		playSubScene.getPane().getChildren().add(createModeToChoose());
		playSubScene.getPane().getChildren().add(createButtonToStart());
	}
	
	private HBox createModeToChoose() {
		HBox box = new HBox();
		box.setSpacing(60);
		modeList = new ArrayList<>();
		for (MODE mode : MODE.values()) {
			ModeChooser modeToChoose = new ModeChooser(mode);
			box.getChildren().add(modeToChoose);
			modeList.add(modeToChoose);
			modeToChoose.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for (ModeChooser mode : modeList) {
						mode.setIsCircleChoosen(false);
					}
					modeToChoose.setIsCircleChoosen(true);
					setChoosenMode(modeToChoose.getMode());
				}
			});
		}
		box.setLayoutX(300-(118*2));
		box.setLayoutY(150);
		return box;
	}
	
	private GameButton createButtonToStart() {
		GameButton startButton = new GameButton("START");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (choosenMode != null) {
					GameViewManager gameManager = new GameViewManager();
					gameManager.createNewGame(mainStage, choosenMode);
				}
			}
			
		});
		return startButton;
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void addMenuButton(GameButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	private void createButtons() {
		createPlayButton();
		createScoresButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
	}
	
	private void createPlayButton() {
		GameButton startButton = new GameButton("PLAY");
		addMenuButton(startButton);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(playSubScene);
			}
		});
	}
	private void createScoresButton() {
		GameButton scoresButton = new GameButton("SCORES");
		addMenuButton(scoresButton);
		
		scoresButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(scoresSubScene);
			}
		});
	}
	private void createHelpButton() {
		GameButton helpButton = new GameButton("HELP");
		addMenuButton(helpButton);
		helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(helpSubScene);
			}
		});
	}
	private void createCreditsButton() {
		GameButton creditsButton = new GameButton("CREDITS");
		addMenuButton(creditsButton);
		
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(creditsSubScene);		
			}
		});
	}
	private void createExitButton() {
		GameButton exitButton = new GameButton("EXIT");
		addMenuButton(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainStage.close();		
			}
		});
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("view/resources/blue.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, 
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}
	private void createLogo() {
		ImageView logo = new ImageView("view/resources/rpg.png");
		logo.setLayoutX(400);
		logo.setLayoutY(50);
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());	
			}
		});
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
			}
		});
		mainPane.getChildren().add(logo);
	}

	public MODE getChoosenMode() {
		return choosenMode;
	}

	public void setChoosenMode(MODE choosenMode) {
		this.choosenMode = choosenMode;
	}
}
