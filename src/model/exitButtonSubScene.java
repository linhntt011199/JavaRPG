package model;

import javafx.scene.control.Button;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class exitButtonSubScene extends Button{
	// xac dinh kieu nut khi no suoc kao vao tha ra.
		//dinh nghia style nut
		
		// constructor
		public exitButtonSubScene(String image , int w, int h) {
			//setText(text);	// chuỗi để hiển thị
			//setButtonFont();
			setPrefWidth(w); // chieiu rong của nut
			setPrefHeight(h); // chieu dai
			setStyle(image);
			initializeButtonListeners(image, h);
		}
		
		private void setButtonPressedStyle(String image, int h) {
			setStyle(image);
			setPrefHeight(h);
			setLayoutY(getLayoutY() + 4);
		}
		
		private void setButtonReleasedStyle(String image, int h) {
			setStyle(image);
			setPrefHeight(h);
			setLayoutY(getLayoutY() - 4);
		}
		
		//create our listener khi nao nhan chuot, tha chuot va khi nao an enter-
		private void initializeButtonListeners(String image, int h) {
			setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					//ktra xem nut cua ng dunfg nhap vao co phai nut chuot chinh hya k
					if(event.getButton().equals(MouseButton.PRIMARY)) {
						//neu la nut ben trai thi thiet lap duoc bo cuc
						setButtonPressedStyle(image,h);
					}
				}
			});
			
			setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					//ktra xem nut cua ng dunfg nhap vao co phai nut chuot chinh hya k
					if(event.getButton().equals(MouseButton.PRIMARY)) {
						//neu la nut ben trai thi thiet lap duoc bo cuc
						setButtonReleasedStyle(image,h);
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