package entities;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final int width;
    private final int height;
    
    public SpriteAnimation(
            ImageView imageView,
            Duration duration,
            int count, int columns,
            int offsetX, int offsetY,
            int width, int height) {
    	
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
        setCycleCount(Animation.INDEFINITE);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }

    public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	
	public void setOffset(int offsetX, int offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	@Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(frac * count), count - 1);
	    final int x = (index % columns) * width + offsetX;
	    final int y = (index / columns) * height + offsetY;
	    imageView.setViewport(new Rectangle2D(x, y, width, height));
    } 

}

