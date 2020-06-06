package entities;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Monster extends Entity{
	
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
	
	public ArrayList<Entity> collisions = new ArrayList<>();
	
	Rectangle removeRect = null;
	public SpriteAnimation animation;
	
	public Monster(ImageView imageView) {
		this.imageView = imageView;
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new SpriteAnimation(imageView,Duration.millis(500),count,columns,offsetX,offsetY, width, height);
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
	
	public void addCollision(Entity gameObject2D) {
        collisions.add(gameObject2D);
    }

    public void addCollision(ArrayList<Entity> gameObject2D) {
        collisions.addAll(gameObject2D);
    }
	
	public boolean checkMonsterCollision(float xOffset, float yOffset) { // kiem tra va cham cua thuc the
        for(Entity e: collisions){ // nhan danh sach cac thuc the 
        	if (e.getName().equals("tree")) {
        		Rectangle Bound = e.getCollisionBounds(0f, 0f);
        		//Rectangle entityBound = new Rectangle(e.getLayoutX() + 1, e.getLayoutY() + 25, Bound.getWidth() - 2, Bound.getHeight() - 30);
        		Rectangle entityBound = new Rectangle(e.getLayoutX() + 1, e.getLayoutY() + 1, Bound.getWidth() - 1 , Bound.getHeight() - 4);
        		//System.out.println((monster.getTranslateX()+ 100+10 + xOffset) + " " + (monster.getTranslateY() + 100+25 + yOffset));
            	if(entityBound.intersects((this.getTranslateX()+ 100 + 10 + xOffset), (this.getTranslateY() + 100 + 25 + yOffset), this.getWidth()-20, this.getHeight() - 28)){ // co giao cat
                        return true;
            	}
                    
            }
        	if (e.getName().equals("rock")) {
        		Rectangle Bound = e.getCollisionBounds(0f, 0f);
        		Rectangle entityBound = new Rectangle(e.getLayoutX(), e.getLayoutY(), Bound.getWidth(), Bound.getHeight());
            	if(entityBound.intersects((this.getTranslateX()+100+10 + xOffset), (this.getTranslateY() + 100+20 + yOffset), this.getWidth()-20, this.getHeight()-20)){ // co giao cat
                        return true;
            	}
        	}
        	if (e.getName().equals("water")) {
        		Rectangle Bound = e.getCollisionBounds(0f, 0f);
        		Rectangle entityBound = new Rectangle(e.getLayoutX(), e.getLayoutY(), Bound.getWidth(), Bound.getHeight());
            	if(entityBound.intersects((this.getTranslateX()+100+8 + xOffset), (this.getTranslateY() + 100+16 + yOffset), this.getWidth()/2, this.getHeight()/2)){ // co giao cat
                        return true;
            	}
        	}
        	
        	if (this.getTranslateX() + xOffset == 670 || this.getTranslateX() + xOffset == -100 || this.getTranslateY()  + yOffset == 470 || this.getTranslateY()  + yOffset == -100) return true;
        
    		
        }
        return false; // khong co va cham
    }
	
}
