package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoLabel extends Label {
	// luu tru font url
	public final static String FONT_PATH = "src/model/resource/VNI-Truck.ttf";
	public final static String BACKGROUND_IMAGE= "view/resource/yellow_button13.png";
	
	public InfoLabel(String text) {
		setPrefWidth(380); // chieu rong cua anh
		setPrefHeight(49);
		//setPadding(new Insets(40,40,40,40));
		setText(text);
		setWrapText(true);
		setLabelFont();
		setAlignment(Pos.CENTER);// de chu vao giua anh
		
		BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE,380,49,false, true),
				BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		setBackground(new Background(backgroundImage));
	}
	
	//set label font
	private void setLabelFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
			setTextFill(Color.web("#e68a00"));
		}catch(FileNotFoundException e) {
			// neu k tim duoc font file, tao 1 font binh thuong
			setFont(Font.font("Viettay Normal", 23));
		}
	}
}
