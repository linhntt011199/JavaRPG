package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CharacterPicker extends VBox{
	private ImageView circleImage;
	private ImageView characterImage;
	
	private String circleNotChoosen = "view/resources/characterchooser/grey_circle.png";
	private String circleChoosen = "view/resources/characterchooser/circle_choosen.png";
	
	private CHARACTER character;
	
	private boolean isCircleChoosen;
	
	public CharacterPicker(CHARACTER character) {
		circleImage = new ImageView(circleNotChoosen);
		characterImage = new ImageView(character.getUrl());
		this.character = character;
		isCircleChoosen = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		this.getChildren().add(circleImage);
		this.getChildren().add(characterImage);
	}
	public CHARACTER getCharacter() {
		return character;
	}
	public boolean getCircleChoosen() {
		return isCircleChoosen;
	}
	public void setIsCircleChooser(boolean isCircleChoosen) {
		this.isCircleChoosen = isCircleChoosen;
		String imageToSet = this.isCircleChoosen ? circleChoosen: circleNotChoosen;
		circleImage.setImage(new Image(imageToSet));
	}
}
