package entities;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Magic extends Entity {
	public static final float[] BULLET_SPEED = {2.0f, 3.0f, 4.0f};
    public Entity owner;
    private float X,Y; // vi tri ban dau cua bullet
    private float x,y; // vi tri sau khi ban ra
    private float speed;
    private float xMove, yMove;
    int offsetX;
	int offsetY;
	public SpriteAnimation animation;

	public ArrayList<Entity> collisions = new ArrayList<Entity>();
    
    public Magic(ImageView imageView, Player owner, float x, float y) {
        this.owner = owner;
        this.X = x; this.Y = y;
        this.x = x; this.y = y;
        this.imageView = imageView;
    	this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    	animation = new SpriteAnimation(imageView, Duration.millis(300), 7, 7, offsetX, offsetY, 64, 64);
    	getChildren().addAll(imageView);
        if(this.owner instanceof Player){
            setSpeed(BULLET_SPEED[2]);
        } else{
            setSpeed(BULLET_SPEED[1]);
//            speed = BULLET_SPEED[2];
        }
        setMove();
        setActive(true);
	}
    
    public void checkEntityCollision() {
    	for(Entity e: collisions){ // nhan danh sach cac thuc the 
        	if (e.getName().equals("tree")) {
        		Rectangle entityBound = new Rectangle(e.getLayoutX(), e.getLayoutY(), e.getWidth(), e.getHeight());
            	if(entityBound.intersects(this.getTranslateX(), this.getTranslateY(), width, height)){ // co giao cat
            		setActive(false);
            	}
                    
            }
        	if (e.getName().equals("rock")) {
        		Rectangle entityBound = new Rectangle(e.getLayoutX(), e.getLayoutY(), e.getWidth(), e.getHeight());
            	if(entityBound.intersects(this.getTranslateX(), this.getTranslateY(), width, height)){ // co giao cat
                	setActive(false);
            	}
        	}
        	if (e.getName().equals("monster")) {
        		Rectangle entityBound = new Rectangle(e.getTranslateX() + e.getLayoutX()-15, e.getTranslateY()+e.getLayoutY()-2, e.getWidth()/2, e.getHeight()-3);
            	if(entityBound.intersects(this.getTranslateX(), this.getTranslateY(), width, height)){ // co giao cat
            		((Monster) e).update(-20);
            		setActive(false);
            	}
        	}
        }
    }
    
    public void move() {
        animation.play();
//    	System.out.println("x: " + x + " & " + "y: " + y + " || " + "X: " + X + " & " + "Y: " + Y + " || " + "X-x: " + Math.abs(X-x) + " & " + "Y-y: " + Math.abs(Y-y));
    	this.setTranslateX(x);
    	this.setTranslateY(y);
        moveX();
        moveY();
    }
    
    public void setMove() {
        // Kiem tra hướng cua player de khoi tao xMove va yMove
    	if(owner.isFacingUpRight()) {
    		animation.setOffset(0, 64*3);
			xMove = getSpeed();
    		yMove = -getSpeed();
	    } else if(owner.isFacingUpLeft()) {
	    	animation.setOffset(0, 64);
			xMove = -getSpeed();
    		yMove = -getSpeed();
	   	} else if(owner.isFacingDownLeft()) {
	   		animation.setOffset(0, 64*7);
			xMove = -getSpeed();
    		yMove = getSpeed();	
	   	} else if(owner.isFacingDownRight()) {
	   		animation.setOffset(0, 64*5);
			xMove = getSpeed();
    		yMove = getSpeed();
	    } else if(owner.isFacingRight()) {
	    	animation.setOffset(0, 64*4);
			xMove = getSpeed();
            yMove = 0;
	    } else if(owner.isFacingLeft()) {
	    	animation.setOffset(0, 0);
			xMove = -getSpeed();
            yMove = 0;	
	    } else if(owner.isFacingUp()) {
	    	animation.setOffset(0, 64*2);
			yMove = -getSpeed();
	        xMove = 0;
		} else if(owner.isFacingDown()) {
			animation.setOffset(0, 64*6);
			yMove = getSpeed();
	        xMove = 0;
	 	}
	}
    
    public void moveX(){
    	if(Math.abs(X-x)>150) {
    		setActive(false);
    		animation.stop();
    	}
    	else if(xMove > 0) {	//Tiếp tục di chuyển sang phai nếu không chạm vào tile 
    		checkEntityCollision();
            if (isActive() == true) x += xMove;
    	}
        else if (xMove < 0) { 	//Moving left
        	checkEntityCollision();
            if (isActive() == true) x += xMove;
        }
    }

    public void moveY(){
    	if(Math.abs(Y-y)>150) {
    		setActive(false);
    		animation.stop();
    	}
    	else if(yMove < 0) { 	// move up
    		checkEntityCollision();
            if (isActive() == true) y += yMove;
    	}
        else if(yMove > 0) {	// move down
        	checkEntityCollision();
            if (isActive() == true) y += yMove;
        }
    }

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
