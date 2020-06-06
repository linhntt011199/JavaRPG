package model;

import java.io.IOException;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class MenuSubScene extends SubScene {
	//private final static String FONT_PATH = "src/model/resources/kenvector_future.tft";
	private final static String BACKGROUND_IMAGE = "model/resources/panel_blue.png";
	private boolean isHidden;
	
	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public MenuSubScene() {
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));
		setHidden(true);
		setLayoutX(1024);
		setLayoutY(180);
	}
	
	public MenuSubScene(String fxml){
		super(new AnchorPane(), 600, 400);
		try {
			BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
			AnchorPane root2 = FXMLLoader.load(getClass().getResource(fxml));
			root2.setBackground(new Background(image));
			setHidden(true);
			setLayoutX(1024);
			setLayoutY(180);
		} catch (IOException e) {
			System.out.println( "Exception on FXMLLoader.load()" );
		}
	}
	
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.5));
		transition.setNode(this);
		if (isHidden()) {
			transition.setToX(-676); //define how a particular element change it's position according axis
			setHidden(false);
		} else {
			transition.setToX(0);
			setHidden(true);
		}
		transition.play();
	}
	
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
}
