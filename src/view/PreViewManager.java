package view;

import view.PreViewManager;

import model.exitButtonSubScene;
import javafx.stage.Stage;

import java.io.File;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;

public class PreViewManager {
	private AnchorPane preViewMainPane;
	private Scene preViewMainScene;
	private Stage preViewMainStage;
	
	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;
	
	//anh
	private final String MENU_BUTTON = "-fx-background-color: transparent; -fx-background-image: url('model/resources/menu.png');";
	private final String SETTINGS_MUSIC_BUTTON = "-fx-background-color: transparent; -fx-background-image: url('model/resources/play.png');";
	private final String MUSIC = "src/model/resources/deepside.mp3";
	
	private Stage menuStage;
	MediaPlayer mediaPlayer;
	
	public Stage getMainStage() {
		return preViewMainStage;
	}
	public PreViewManager() {
		preViewMainPane = new AnchorPane();
		preViewMainScene = new Scene(preViewMainPane,WIDTH,HEIGHT);
		preViewMainStage = new Stage();
		preViewMainStage.setScene(preViewMainScene);
		createBackground();
		preViewMainPane.getChildren().add(buttonNextToMenu());
		createMusic();
		preViewMainPane.getChildren().add(buttonOnOffMusic(mediaPlayer));
		
		createLogo();
	}
	//================================
	private void createMusic() {
	       Media sound = new Media(new File(MUSIC).toURI().toString());
	       mediaPlayer = new MediaPlayer(sound);
	       mediaPlayer.play();
	       
	}
	//================================
	private exitButtonSubScene buttonOnOffMusic( MediaPlayer mediaplayer) {
		String image = SETTINGS_MUSIC_BUTTON;
		exitButtonSubScene onOffButton = new exitButtonSubScene(image,50,50);
		onOffButton.setLayoutX(920);
		onOffButton.setLayoutY(680);
		
		onOffButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//ktra xem nut cua ng dunfg nhap vao co phai nut chuot chinh hya k
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					//neu la nut ben trai thi thiet lap duoc bo cuc
					mediaPlayer.pause();	
					if(event.getClickCount() == 2) {
						mediaPlayer.play();
					}
				}
			}
		});
		onOffButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//ktra xem nut cua ng dunfg nhap vao co phai nut chuot chinh hya k
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					//neu la nut ben trai thi thiet lap duoc bo cuc
					if(event.getButton().equals(MouseButton.PRIMARY)) {
						//neu la nut ben trai thi thiet lap duoc bo cuc
						mediaPlayer.pause();					
						if(event.getClickCount() == 2) {
							mediaPlayer.play();	
						}
					}
				}
			}
		});

		
		return onOffButton;
	}
	//================================
	private exitButtonSubScene buttonNextToMenu() {
		exitButtonSubScene startButton = new exitButtonSubScene(MENU_BUTTON,100,100);
		startButton.setLayoutX(900);
		startButton.setLayoutY(550);
		
		startButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				startButton.setEffect(new DropShadow());
			}
		});
		
		startButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				startButton.setEffect(null);
			}
		});
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ViewManager ViewManager = new ViewManager();
				ViewManager.createNewView(preViewMainStage);
				}
		});
		return startButton;
	}
	
	
	private void createBackground() {
		// doc duoc anh nen
		Image backgroundImage = new Image("view/resources/background.png", 1024,768,false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
		preViewMainPane.setBackground(new Background(background));
		
	}
	
	public  void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		preViewMainStage.show();
	}
	
	private void createLogo() {
		ImageView logo = new ImageView("view/resources/logo1.png");
		logo.setLayoutX(200);
		logo.setLayoutY(200);
		
		// hieu ung do bong
		preViewMainPane.getChildren().add(logo);
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
	}

}
