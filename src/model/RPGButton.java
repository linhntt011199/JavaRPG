package model;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class RPGButton extends Button {
	// xac dinh kieu nut khi no suoc kao vao tha ra.
	private final String FONT_PATH = "src/model/resources/VNI-Truck.ttf";
	//dinh nghia style nut
	private final String BUTTON_PRESSED_STYLE= "-fx-background-color: transparent; -fx-background-image: url('model/resources/yellow_button01.png');";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('model/resources/yellow_button.png');";
	
	// constructor
	public RPGButton(String text, int a) {
		super();
		setText(text);	// chuỗi để hiển thị
		setButtonFont(a);
		setPrefWidth(200); // chieiu rong của nut
		setPrefHeight(80); // chieu dai
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListeners();
	}
	
	//phuong thuc khoi tao font
	private void setButtonFont(int a) {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
			setTextFill(Color.web("#e68a00"));
		}catch(FileNotFoundException e) {
			// neu k tim duoc font file, tao 1 font binh thuong
			setFont(Font.font("Viettay Normal", 23));
		}
	}
	
	// release style and kieu nhan
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(80);
		setLayoutY(getLayoutY() + 4);
	}
	
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(80);
		setLayoutY(getLayoutY() - 4);
	}
	
	//create our listener khi nao nhan chuot, tha chuot va khi nao an enter-
	private void initializeButtonListeners() {
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//ktra xem nut cua ng dunfg nhap vao co phai nut chuot chinh hya k
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					//neu la nut ben trai thi thiet lap duoc bo cuc
					setButtonPressedStyle();
				}
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//ktra xem nut cua ng dunfg nhap vao co phai nut chuot chinh hya k
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					//neu la nut ben trai thi thiet lap duoc bo cuc
					setButtonReleasedStyle();
				}
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			// toa hieu ung do bong khi an chuot vao
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
			}
		});
		
		setOnMouseExited (new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
	}

}
