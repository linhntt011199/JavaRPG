package model;

import javafx.geometry.Pos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBox;

//lop nay bao gom ship image va circle image
//circle image se bi day sau khi chon 1 trong ship
public class ShipPicker extends VBox{
	
	private ImageView circleImage;
	private ImageView shipImage;
	
	// bao gom Url cho filled circle image va empty circle image
	private String circleNotChoosen = "view/resource/shipchooser/grey_circle.png";
	private String circleChoosen  = "view/resource/shipchooser/yellow_boxTick.png";
	
	private SHIP ship;
	//nut do co duoc chon hay khong
	private boolean isCircleChoosen;
	
	public ShipPicker(SHIP ship) {
		//tao nut gray circle image -> nut khong duoc chon
		circleImage = new ImageView(circleNotChoosen);
		shipImage = new ImageView(ship.getUrl());
		this.ship = ship;
		isCircleChoosen = false; // circle ban dau se o trang thai chua chon
		// can chinh vi tri trung tam
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().add(circleImage);
		// them image vao VBox
		this.getChildren().add(shipImage);
	}
	
	public SHIP getShip() {
		return ship;
	}
	
	public boolean getIsCircleChoosen() {
		return isCircleChoosen;
	}
	
	// tao method de tao circle neu no da duoc chon hoac khong duoc chon
	public void setIsCircleChoosen(boolean isCircleChoosen) {
		this.isCircleChoosen = isCircleChoosen;
		String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
		circleImage.setImage(new Image(imageToSet));
	}
}
