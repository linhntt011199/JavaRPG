package game.entities.creatures;

import game.Handler;
import game.entities.Entity;
import game.gfx.Animation;
import game.tiles.Tile;

public abstract class Creature extends Entity {
    
    public static final float DEFAULT_SPEED = 2.0f; // toc do mac dinh
    public static final int DEFAULT_CREATURE_WIDTH = 32, // chieu rong sinh vat mac dinh
                            DEFAULT_CREATURE_HEIGHT = 32;
    protected float speed; // toc do
    protected float xMove, yMove;
    
    protected Animation animDown, animUp, animLeft, animRight;
    protected Animation currentImage;
    
    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED; // thiet lap toc do
        // khoi tao
        xMove = 0;
        yMove = 0;
    }
    
    public void move() {
    	// phuong phap di chuyen
        if(!checkEntityCollision(xMove, 0f))
            moveX();
        if(!checkEntityCollision(0f, yMove))
            moveY();
    }
    
    public void moveX(){
        if(xMove > 0){ //Sang phai 
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH; // tx: toa do x du dinh di chuyen den
            // x + xMove + bounds.x + bounds.width: vi tri hien tai + vi tri bat dau hop gioi han + khoang cach di chuyen + chieu rong hop gioi han
            // Tile.TILEWIDTH: chieu rong 1 o don vi
            // kiem tra goc tren ben phai va goc duoi ben phai co dung do khong
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
            // (y + bounds.y) / Tile.TILEHEIGHT: lay toa do y
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            } else{
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0) { // Sang trai 
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x + 1;
            }
        }
    }

    public void moveY(){
        if(yMove < 0) { // Len tren 
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y + 1;
            }
        } else if(yMove > 0){ // Xuong duoi 
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }
    
    protected boolean collisionWithTile(int x, int y) { //?? hinh nhu la kiem tra xem o co toa do (x,y) co la chat ran k
        return handler.getWorld().getTile(x,y).isSolid();
    }
    
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public boolean isLeft() {
        return currentImage==animLeft;
    }
    
    public boolean isUp() {
        return currentImage==animUp;
    }
    
    public boolean isRight() {
        return currentImage==animRight;
    }
    
    public boolean isDown() {
        return currentImage==animDown;
    }
    
}
