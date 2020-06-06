package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ModeChooser extends VBox {
	private ImageView circleImage;
	private ImageView modeImage;
	
	private String circleNotChoosen = "view/resources/modechooser/red_circle.png";
	private String circleChoosen = "view/resources/modechooser/red_boxTick.png";
	
	private MODE mode;
	
	private boolean isCircleChoosen;
	
	public ModeChooser(MODE mode) {
		circleImage = new ImageView(circleNotChoosen);
		modeImage = new ImageView(mode.getURL());
		this.mode = mode;
		isCircleChoosen = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().add(circleImage);
		this.getChildren().add(modeImage);
	}
	
	public MODE getMode() {
		return mode;
	}
	
	public boolean getIsCircleChoosen() {
		return isCircleChoosen;
	}
	
	public void setIsCircleChoosen(boolean isCircleChoosen) {
		this.isCircleChoosen = isCircleChoosen;
		String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
		circleImage.setImage(new Image(imageToSet));
	}
}
