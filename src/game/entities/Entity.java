package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import game.Handler;

public abstract class Entity {
    
    public static final int[] DEFAULT_HEALTH = {3, 5}; // suc khoe mac dinh

    protected Handler handler; // xu ly
    protected float x, y; // toa do
    protected int width, height; // chieu rong, chieu cao
    protected int health, level; // suc khoe
    protected boolean active = true, // hoat dong
    					food = false;
    protected Rectangle bounds; // hop gioi han va cham
    
    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.level = handler.getGame().getLevel();
        health = DEFAULT_HEALTH[level];
        bounds = new Rectangle(0, 0, width, height); // gioi han tu goc tren ben trai
    }
        
    public abstract void tick(); // cap nhat tat ca cac bien va su di chuyen
    
    public abstract void render(Graphics g); // ve len man hinh
    
    public abstract void die();
    
    public void hurt(int amt) { // lam giam suc khoe cua thuc the
        health -= amt;
        if(health <= 0) { // thuc the chet
            active = false; 
            die();
        }
    }
    
    public boolean checkEntityCollision(float xOffset, float yOffset) { // kiem tra va cham cua thuc the
        for(Entity e: handler.getWorld().getEntityManager().getEntities()){ // nhan danh sach cac thuc the
            if(e.equals(this)){ // khong kiem tra va cham voi chinh no
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){ // co giao cat
                return true;
            }
        }
        return false; // khong co va cham
    }
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset) { // gioi han va cham
    	// xOffset: do lech x
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
        // xOffset = 0, yOffset = 0: vi tri cua thuc the + hop gioi han, tra ve hinh chu nhat kich thuoc phu hop tren thuc the
        // xOffset <> 0, yOffset <> 0: khung gioi han o mot diem khac
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public void setLevel(int i) {
        this.level = i;
    }

}
