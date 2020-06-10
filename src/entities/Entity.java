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
	String entityName;
	protected boolean active = true;
	
	private boolean isFacingRight = false;
    private boolean isFacingDown = true;
    private boolean isFacingUp = false;
    private boolean isFacingLeft = false;
	
	public Entity(ImageView imageView, String entityName, int x, int y, int width, int height) {
		this.imageView = imageView;
		this.entityName = entityName;
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
		return entityName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isFacingRight() {
        boolean facing = false;
        if (isFacingRight && !isFacingDown && !isFacingUp && !isFacingLeft) facing = true;
        return facing;
    }

    public boolean isFacingLeft() {
        boolean facing = false;
        if (!isFacingRight && !isFacingDown && !isFacingUp && isFacingLeft) facing = true;
        return facing;
    }

    public boolean isFacingDown() {
        boolean facing = false;
        if (!isFacingRight && isFacingDown && !isFacingUp && !isFacingLeft) facing = true;
        return facing;
    }

    public boolean isFacingUp() {
        boolean facing = false;
        if (!isFacingRight && !isFacingDown && isFacingUp && !isFacingLeft) facing = true;
        return facing;
    }

    // diagonal
    public boolean isFacingUpRight() {
        boolean facing = false;
        if (isFacingRight && isFacingUp) facing = true;

        return facing;
    }

    public boolean isFacingUpLeft() {
        boolean facing = false;
        if (isFacingLeft && isFacingUp) facing = true;

        return facing;
    }

    public boolean isFacingDownRight() {
        boolean facing = false;
        if (isFacingRight && isFacingDown) facing = true;

        return facing;
    }

    public boolean isFacingDownLeft() {
        boolean facing = false;
        if (isFacingDown && isFacingLeft) facing = true;

        return facing;
    }
    
    // face setters
    public void setFaceRight() {
        isFacingRight = true;
        isFacingDown = false;
        isFacingUp = false;
        isFacingLeft = false;
    }

    public void setFaceLeft() {
        isFacingRight = false;
        isFacingDown = false;
        isFacingUp = false;
        isFacingLeft = true;
    }

    public void setFaceDown() {
        isFacingRight = false;
        isFacingDown = true;
        isFacingUp = false;
        isFacingLeft = false;
    }

    public void setFaceUp() {
        isFacingRight = false;
        isFacingDown = false;
        isFacingUp = true;
        isFacingLeft = false;
    }

    // diagonal faces
    public void setFaceUpRight() {
        isFacingRight = true;
        isFacingDown = false;
        isFacingUp = true;
        isFacingLeft = false;
    }

    public void setFaceUpLeft() {
        isFacingRight = false;
        isFacingDown = false;
        isFacingUp = true;
        isFacingLeft = true;
    }

    public void setFaceDownRight() {
        isFacingRight = true;
        isFacingDown = true;
        isFacingUp = false;
        isFacingLeft = false;
    }

    public void setFaceDownLeft() {
        isFacingRight = false;
        isFacingDown = true;
        isFacingUp = false;
        isFacingLeft = true;
    }
	 	
}

