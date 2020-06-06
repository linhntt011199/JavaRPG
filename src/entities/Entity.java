package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Entity extends Pane {
	ImageView imageView;
	int x;
	int y;
	int width;
	int height;
	protected Rectangle bounds;
	String nameEntity;
	
	public Entity(ImageView imageView, String entityName, int x, int y, int width, int height) {
		this.imageView = imageView;
		this.nameEntity = entityName;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imageView.setViewport(new Rectangle2D(x, y, width, height));
		getChildren().add(imageView);
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
	
	public Entity() {
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) { // gioi han va cham
	    	// xOffset: do lech x
	        return new Rectangle((x + xOffset), (y + yOffset), this.getWidth(), this.getHeight());
	        // xOffset = 0, yOffset = 0: vi tri cua thuc the + hop gioi han, tra ve hinh chu nhat kich thuoc phu hop tren thuc the
	        // xOffset <> 0, yOffset <> 0: khung gioi han o mot diem khac
	 }

	public String getName() {
		return nameEntity;
	}
	 
	
}

