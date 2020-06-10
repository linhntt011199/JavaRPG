package entities;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class HealthBar extends Pane{
	Rectangle outerHealthRect;
	Rectangle innerHealthRect;
	
	public HealthBar() {
		double height = 2;
		
		double outerWidth = 32;
		double innerWidth = 32;
		
		double x = 0.0;
		double y = 0.0;
		
		outerHealthRect = new Rectangle(x, y, outerWidth, height);
		outerHealthRect.setStroke(Color.BLACK);
		outerHealthRect.setStrokeWidth(2);
		outerHealthRect.setStrokeType(StrokeType.OUTSIDE);
		outerHealthRect.setFill(Color.RED);
		
		innerHealthRect = new Rectangle(x, y, innerWidth, height);
		innerHealthRect.setStrokeType(StrokeType.OUTSIDE);
		innerHealthRect.setFill(Color.YELLOW);
		
		getChildren().addAll(outerHealthRect, innerHealthRect);
	}
	
	
	public void setValue(double value) {
		innerHealthRect.setWidth(outerHealthRect.getWidth() * value);
	}
}
