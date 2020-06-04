package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.CHARACTER;
import model.CharacterPicker;
import model.InfoLabel;
import model.MAP;
import model.MapPicker;
import model.SimpleRPGButton;
import model.SimpleRPGSubscene;

public class ViewManager<MBox> {
	
	private static final int WIDTH = 768;
	private static final int HEIGHT = 600;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private static final int NEW_BUTTON_START_X = 100;
	private static final int NEW_BUTTON_START_Y = 100;
	
	private SimpleRPGSubscene credistsSubScene;
	private SimpleRPGSubscene helpSubScene;
	private SimpleRPGSubscene scoreSubScene;
	private SimpleRPGSubscene characterChooserSubScene;
	private SimpleRPGSubscene mapChooserSubScene;
	
	private SimpleRPGSubscene sceneToHide;
	List<CharacterPicker> characterList;
	private CHARACTER choosenCharacter;
	List<MapPicker> mapList;
	private MAP choosenMap;
	List<SimpleRPGButton> menuButton;
	
	private static final String musicFile = "src/view/resources/BIGBANG.mp3";
	
	public ViewManager() {
		menuButton = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		mainStage.setTitle("Simple RPG");
		createSubScene();
		createButtons();
		createBackground();
		createLogo();
		//createMusic();
	}
	
	private void createMusic() {
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
	private void showSubScene(SimpleRPGSubscene subScene) {
		if (sceneToHide != null) {
			sceneToHide.moveSubScene();
		}
		subScene.moveSubScene();
		sceneToHide = subScene;
	}
	
	private void createSubScene() {
		credistsSubScene = new SimpleRPGSubscene();
		mainPane.getChildren().add(credistsSubScene);
		
		helpSubScene = new SimpleRPGSubscene();
		mainPane.getChildren().add(helpSubScene);
		
		scoreSubScene = new SimpleRPGSubscene();
		mainPane.getChildren().add(scoreSubScene);
		
		characterChooserSubScene = new SimpleRPGSubscene();
		mainPane.getChildren().add(characterChooserSubScene);
		
		
		
		createCharacterChooserSubScene();
		mapChooserSubScene = new SimpleRPGSubscene();
		mainPane.getChildren().add(mapChooserSubScene);
		creatMapChooserSubScene();
	}
	
	private void creatMapChooserSubScene() {
		mapChooserSubScene = new SimpleRPGSubscene();
		
		mainPane.getChildren().add(mapChooserSubScene);
		InfoLabel chooserMapLabel = new InfoLabel("CHOOSE YOUR MAP");
		chooserMapLabel.setLayoutX(5);
		chooserMapLabel.setLayoutY(5);
		mapChooserSubScene.getPane().getChildren().add(chooserMapLabel);
		mapChooserSubScene.getPane().getChildren().add(createMapToChoose());
		mapChooserSubScene.getPane().getChildren().add(createButtonToNext());
	}

	private HBox createMapToChoose() {
		HBox box = new HBox();
		box.setSpacing(10);
		mapList = new ArrayList<>();
		for(CHARACTER character : CHARACTER.values()) {
			CharacterPicker characterToPick = new CharacterPicker(character);
			characterList.add(characterToPick);
			box.getChildren().add(characterToPick);
			characterToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for(CharacterPicker character : characterList) {
						character.setIsCircleChooser(false);
					}
					characterToPick.setIsCircleChooser(true);
					choosenCharacter = characterToPick.getCharacter();
				}
				
			});
		}
		box.setLayoutX(275 - (118*2));
		box.setLayoutY(80);
		return box;
	}

	private void createCharacterChooserSubScene() {
		characterChooserSubScene = new SimpleRPGSubscene();
		
		mainPane.getChildren().add(characterChooserSubScene);
		InfoLabel chooserCharacterLabel = new InfoLabel("CHOOSE YOUR CHARACTER");
		chooserCharacterLabel.setLayoutX(5);
		chooserCharacterLabel.setLayoutY(5);
		characterChooserSubScene.getPane().getChildren().add(chooserCharacterLabel);
		characterChooserSubScene.getPane().getChildren().add(createCharacterToChoose());
		//characterChooserSubScene.getPane().getChildren().add(createButtonToNext());
		characterChooserSubScene.getPane().getChildren().add(createButtonToStart());
	}
	
	
	
	private SimpleRPGButton createButtonToNext() {
		SimpleRPGButton nextButton = new SimpleRPGButton("NEXT");
		nextButton.setLayoutX(100);
		nextButton.setLayoutY(300);
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(mapChooserSubScene);
			}
			
		});
		return nextButton;
	}

	private HBox createCharacterToChoose() {
		HBox box = new HBox();
		box.setSpacing(10);
		characterList = new ArrayList<>();
		for(CHARACTER character : CHARACTER.values()) {
			CharacterPicker characterToPick = new CharacterPicker(character);
			characterList.add(characterToPick);
			box.getChildren().add(characterToPick);
			characterToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for(CharacterPicker character : characterList) {
						character.setIsCircleChooser(false);
					}
					characterToPick.setIsCircleChooser(true);
					choosenCharacter = characterToPick.getCharacter();
				}
				
			});
		}
		box.setLayoutX(275 - (118*2));
		box.setLayoutY(80);
		return box;
	}

	private SimpleRPGButton createButtonToStart() {
		SimpleRPGButton startButton = new SimpleRPGButton("START");
		startButton.setLayoutX(100);
		startButton.setLayoutY(300);
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (choosenCharacter != null) {
					GameViewManager gameManager = new GameViewManager();
					gameManager.createNewGame(mainStage, choosenCharacter);
				}
			}
			
		});
		return startButton;
		
	}
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void addMenuButton(SimpleRPGButton button) {
		button.setLayoutX(NEW_BUTTON_START_X);
		button.setLayoutY(NEW_BUTTON_START_Y + menuButton.size() * 100);
		menuButton.add(button);
		mainPane.getChildren().add(button);
	}
	
	private void createButtons() {
		createStartButton();
		createScoreButton();
		createHelpButton();
		createCreditButton();
		createExitButton();
	}
	
	private void createStartButton() {
		SimpleRPGButton startButton = new SimpleRPGButton("PLAY");
		addMenuButton(startButton);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(characterChooserSubScene);
				
			}
			
		});
	}
	
	private void createScoreButton() {
		SimpleRPGButton scoreButton = new SimpleRPGButton("SCORE");
		addMenuButton(scoreButton);
		scoreButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(scoreSubScene);
				
			}
			
		});
	}
	
	private void createHelpButton() {
		SimpleRPGButton helpButton = new SimpleRPGButton("HELP");
		addMenuButton(helpButton);
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(helpSubScene);
				
			}
			
		});
	}
	
	private void createCreditButton() {
		SimpleRPGButton creditButton = new SimpleRPGButton("CREDIT");
		addMenuButton(creditButton);
		
		creditButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(credistsSubScene);
				
			}
			
		});
	}
	
	private void createExitButton() {
		SimpleRPGButton exitButton = new SimpleRPGButton("EXIT");
		addMenuButton(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Comfirmation");
				alert.setContentText("Choose your option");
				
				ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
				ButtonType buttonTypeCancel = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
				
				alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
				
				Optional<ButtonType> result = alert.showAndWait();
				
				if(result.get().getButtonData() == ButtonBar.ButtonData.YES) mainStage.close();
				
			}
			
		});
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("view/resources/backgroundCastles.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
		
	}
	
	private void createLogo() {
		ImageView logo = new ImageView("view/resources/SimpleRPG.png");
		logo.setLayoutX(300);
		logo.setLayoutY(30);
		
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

}
