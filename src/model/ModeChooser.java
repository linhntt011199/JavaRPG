package model;

import javafx.geometry.Pos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

//lop nay bao gom mode image va circle image
//circle image se bi day sau khi chon 1 trong mode
public class ModeChooser extends VBox{
	
	private ImageView circleImage;
	private ImageView modeImage;
	
	// bao gom Url cho filled circle image va empty circle image
	private String circleNotChosen = "view/resource/modechooser/grey_circle.png";
	private String circleChosen  = "view/resource/modechooser/yellow_boxTick.png";
	
	private MODE mode;
	//nut do co duoc chon hay khong
	private boolean iscircleChosen;
	
	public ModeChooser(MODE mode) {
		//tao nut gray circle image -> nut khong duoc chon
		circleImage = new ImageView(circleNotChosen);
		modeImage = new ImageView(mode.getUrl());
		this.mode = mode;
		iscircleChosen = false; // circle ban dau se o trang thai chua chon
		// can chinh vi tri trung tam
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().add(circleImage);
		// them image vao VBox
		this.getChildren().add(modeImage);
	}
	
	public MODE getMode() {
		return mode;
	}
	
	public boolean getIscircleChosen() {
		return iscircleChosen;
	}
	
	// tao method de tao circle neu no da duoc chon hoac khong duoc chon
	public void setIscircleChosen(boolean iscircleChosen) {
		this.iscircleChosen = iscircleChosen;
		String imageToSet = this.iscircleChosen ? circleChosen : circleNotChosen;
		circleImage.setImage(new Image(imageToSet));
	}
}
