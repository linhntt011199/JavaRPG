package model;

import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;
import javafx.scene.image.Image;

import javafx.animation.TranslateTransition;

public class SpaceRunnerSubScene extends SubScene{
	
	//tao field moi neu cai subscene bị giau di
	private boolean isHidden;
	
	public SpaceRunnerSubScene(String panel, int w, int h) {
		super(new AnchorPane(), w, h);
		prefWidth(w);
		prefHeight(h);
		
		BackgroundImage image = new BackgroundImage(new Image(panel,w,h, false, true),
				BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		
		// tao anchorpane
		AnchorPane root2 = (AnchorPane) this.getRoot();
		// tao background
		root2.setBackground(new Background(image));
		
		isHidden = true;
		
		// tao mot subscene sau main menu
		setLayoutX(1024);
		setLayoutY(200);
	}

	// di chuyen subscene
	public void moveSubScene() {
		//tao chuyen dong cho 1 panel(an haoc hien)
		TranslateTransition transition = new TranslateTransition();
		//dinh nghia khoang thoi gian cua mot hanh dong
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		if(isHidden) {
			transition.setToX(-676);
			// neu subscene bi an, muon show no ra
			isHidden = false;
		}else {// neu no dang hien thi, muon an no di
			transition.setToX(0);
			isHidden = true;
		}
		
		transition.play();
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	// method tra lai pane tren subscene
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
}

