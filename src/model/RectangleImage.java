package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleImage extends Rectangle{
	private int startX;
	private int startY;
	private final int width;
	private final int height;
	
	public RectangleImage(
		int startX,
		int startY,
		int width,
		int height	
	) {
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.height = height;
		this.setFill(Color.RED);
	}
	
	public void setStartX(int x) {
		this.startX = x;
	}
	
	public void setStartY(int y) {
		this.startY = y;
	}
		
}
