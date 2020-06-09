package entities;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Monster extends Entity{
	
	//Monster Configuration
	ImageView imageView;
	int count;
	int columns;
	int offsetX;
	int offsetY;
	int width; // size of monster
	int height;
	int xMove = 0;
	int yMove = 0;
	double health;
	int speed;
	int dX;
	int dY;
	int layoutX;
	int layoutY;
	int h = 1;
	HealthBar healthBar;
	private static final double healthMax = 100;
	
	public ArrayList<Entity> collisions = new ArrayList<>();
	
	Rectangle removeRect = null;
	public SpriteAnimation animation;
	public Player player;
	
	public Monster(ImageView imageView, String nameEntity, int health, int speed, int layoutX, int layoutY, int dX, int dY, int width, int height, int columns, int count) {
		this.imageView = imageView;
		this.health = health;
		this.speed = speed;
		this.dX = dX;
		this.dY = dY;
		this.layoutX = layoutX;
		this.layoutY = layoutY;
		this.setLayoutX(layoutX);
		this.setLayoutY(layoutY);
		this.width = width;
		this.height = height;
		this.columns = columns;
		this.count = count;
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new SpriteAnimation(imageView,Duration.millis(500),count,columns,offsetX,offsetY, width, height);
		getChildren().addAll(imageView);
		this.entityName = nameEntity;
		this.health = 100;
    	healthBar = new HealthBar();
    	this.getChildren().add(this.healthBar);
    	healthBar.relocate(x + 5 + (imageView.getBoundsInLocal().getWidth()-healthBar.getBoundsInLocal().getWidth())/2, y - healthBar.getBoundsInLocal().getHeight()/2);
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
			if(right) {if (this.getTranslateX()  <  dX/2) this.setTranslateX(this.getTranslateX() + 1);}
			else {if (this.getTranslateX() > - dX/2) this.setTranslateX(this.getTranslateX()-1);}
			
		}
	}
	
	

	public void moveY(int y) {
		boolean down = y>0?true:false;
		for(int i = 0; i < Math.abs(y); i ++) {
			if(down) {if (this.getTranslateY()  < dY/2) this.setTranslateY(this.getTranslateY() + 1);}
			else {if (this.getTranslateY() > - dY/2) this.setTranslateY(this.getTranslateY()-1);}
			
		}
	}
	
	public double getHealth() {
		return health;
	}
	
	public double getRelativeHealth() {
		return getHealth() / healthMax;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void addCollision(Entity gameObject2D) {
        collisions.add(gameObject2D);
    }

    public void addCollision(ArrayList<Entity> gameObject2D) {
        collisions.addAll(gameObject2D);
    }
	
	public boolean checkMonsterCollision(float xOffset, float yOffset) { // kiem tra va cham cua thuc the
        for(Entity e: collisions){ // nhan danh sach cac thuc the 
        	if (e.equals(this)) continue;
        	if (e.getName().equals("tree")) {
        		Rectangle entityBound = new Rectangle(e.getLayoutX() + 1, e.getLayoutY() + 1, e.width - 1 , e.height - 4);
        		//System.out.println((this.getTranslateX()+ layoutX+ 10 + xOffset)+ " " +(this.getTranslateY() + 25 + layoutY + yOffset) + " "+ (this.getWidth()-20) + " "+ (this.getHeight() - 28));
            	if(entityBound.intersects((this.getTranslateX()+ layoutX+ 10 + xOffset), (this.getTranslateY() + 25 + layoutY + yOffset), this.getWidth()-20, this.getHeight() - 28)){ // co giao cat
                        return true;
            	}
                    
            }
        	if (e.getName().equals("rock")) {
        		Rectangle entityBound = new Rectangle(e.getLayoutX(), e.getLayoutY(), e.width, e.height);
            	if(entityBound.intersects((this.getTranslateX()+10 + layoutX + xOffset), (this.getTranslateY() +20 + layoutY + yOffset), this.getWidth()-20, this.getHeight()-20)){ // co giao cat
                        return true;
            	}
        	}
        	if (e.getName().equals("water")) {
        		Rectangle entityBound = new Rectangle(e.getLayoutX(), e.getLayoutY(), e.width, e.height);
            	if(entityBound.intersects((this.getTranslateX()+8 + layoutX + xOffset), (this.getTranslateY() + layoutY+ 16 + yOffset), this.getWidth()-20, this.getHeight()/2)){ // co giao cat
                        return true;
            	}
        	}
        	if (e.getName().equals("monster")) {
        		Rectangle entityBound = new Rectangle(e.getLayoutX(), e.getLayoutY(), e.width-5, e.height-3);
            	if(entityBound.intersects((this.getTranslateX()+8 + layoutX + xOffset), (this.getTranslateY() + layoutY+ 16 + yOffset), this.getWidth()-20, this.getHeight()/2)){ // co giao cat
                        return true;
            	}
        	}
        	if (this.getTranslateX() + xOffset == dX/2 || this.getTranslateX() + xOffset == -dX/2 || this.getTranslateY()  + yOffset == dY/2 || this.getTranslateY()  + yOffset == -dY/2) return true;
        
    		
        }
        return false; // khong co va cham
    }
	
	private boolean checkMonsterCollisionPlayer(Player player) {
		Rectangle playerBound = new Rectangle(player.getTranslateX(), (player.getTranslateY()), player.getWidth(), player.getHeight());
    	if(playerBound.intersects((this.getTranslateX()+layoutX ), (this.getTranslateY() +layoutY ), this.getWidth(), this.getHeight())){ // co giao cat
                return true;
    	}
		return false;
	}
	
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}
	    
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	
	public void moveMonster(Player player, int left, int right, int up, int down) { 
		
		//System.out.println(h);
		if (checkMonsterCollisionPlayer(player)) {
			this.animation.stop();
			
		}
		else {
		
		if(h == left) { // left
			this.animation.play();
			this.animation.setOffsetY(this.width * left);
			this.animation.setOffsetX(offsetX);
			
			if (!this.checkMonsterCollision(-2,0)) 
				this.moveX(-this.speed);  
			else do {
				h = (int) (Math.random()*4+0);
			} while (h == left);
				
		} else if (h==right) { // right
			this.animation.play();
			this.animation.setOffsetY(this.width * right);
			this.animation.setOffsetX(offsetX);
			if (!this.checkMonsterCollision(2,0)) {
				//System.out.println(checkMonsterCollision(2, 0));
				this.moveX(this.speed);
			}
			else do {
				h = (int) (Math.random()*4+0);
			} while (h == right);
			
			} else if (h==up) { // up
				this.animation.play();
				this.animation.setOffsetY(this.width*up);
				this.animation.setOffsetX(offsetX);
				if (!this.checkMonsterCollision(0,-2)) 
					this.moveY(-this.getSpeed());
				else do {
					h = (int) (Math.random()*4+0);
				} while (h == up);
			}else if (h==down) {
				this.animation.play();
				this.animation.setOffsetY(this.width*down);
				this.animation.setOffsetX(offsetX);
				if (!this.checkMonsterCollision(0,2)) 
					this.moveY(this.speed);
				else do {
					h = (int) (Math.random()*4+0);
				} while (h == down);
			}
	
		}
	}

	public void update(int i) {
		this.health += i;
		if (this.health <= 0) setActive(false);
		healthBar.setValue(getRelativeHealth());
	}

	
	
}
