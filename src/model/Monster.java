package model;

import gfx.Animations;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Monster extends Pane{
	
	//Monster Configuration
	ImageView imageView;
	int count = 3;
	int columns = 3;
	int offsetX = 0;
	int offsetY = 0;
	int width = 32; // size of monster
	int height = 32;
	int xMove = 0;
	int yMove = 0;
	int hp = 100;
	int speed = 2;
	
	Rectangle removeRect = null;
	public Animations animation;
	
	public Monster(ImageView imageView) {
		this.imageView = imageView;
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new Animations(imageView,Duration.millis(500),count,columns,offsetX,offsetY, width, height);
		getChildren().addAll(imageView);
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) { // gioi han va cham
	    	// xOffset: do lech x
	        //return new Rectangle((int) (offsetX + xOffset), (int) (offsetY + yOffset), width, height);
		return new Rectangle(offsetX + xOffset, offsetY + yOffset, width, height);
	        // xOffset = 0, yOffset = 0: vi tri cua thuc the + hop gioi han, tra ve hinh chu nhat kich thuoc phu hop tren thuc the
	        // xOffset <> 0, yOffset <> 0: khung gioi han o mot diem khac
	    }
   
	
	public void moveX(int x) {
		boolean right = x>0?true:false;
		for(int i = 0; i < Math.abs(x); i ++) {
			if(right) {this.setTranslateX(this.getTranslateX() + 1);}
			else {this.setTranslateX(this.getTranslateX()-1);}
			
		}
	}
	
	

	public void moveY(int y) {
		boolean down = y>0?true:false;
		for(int i = 0; i < Math.abs(y); i ++) {
			if(down) {this.setTranslateY(this.getTranslateY() + 1);}
			else {this.setTranslateY(this.getTranslateY()-1);}
			
		}
	}
	
	public int getHP() {
		return hp;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int minusHP() {
		hp -= 20;
		return hp;
	}
}
