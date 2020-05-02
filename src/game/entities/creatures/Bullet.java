package game.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import game.Handler;
import game.entities.Entity;
import game.entities.creatures.monsters.Zombie;
import game.gfx.Assets;
import game.tiles.Tile;

public class Bullet extends Creature {
    
    public static final float[] BULLET_SPEED = {2.0f, 4.0f};
    private Creature owner;
    private float X,Y;
    public Bullet(Handler handler, Creature owner, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        level = this.handler.getGame().getLevel();
        this.owner = owner;
        X=x;
        Y=y;
        if(this.owner instanceof Player){ // neu la nguoi choi thi toc do dan 2.0
            speed = BULLET_SPEED[0];

        } else{
            speed = BULLET_SPEED[level]; // toc do dan phu thuoc vao level
       
        } 
        
        setMove();
        // Create bound to check collision
        bounds.x = 3;
        bounds.y = 3;
        bounds.width = width / 4;
        bounds.height = height / 4;
    }
    
    @Override
    public void tick() {
        move();
        checkAttacks();
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bullet, (int) (x), 
                (int) (y), width / 4, height / 4, null);
        
    }
    
    public void checkAttacks() { // kiem tra xem co dang tan cong
        Rectangle cb = getCollisionBounds(0, 0); // gioi han va cham, co toa do cua hop va cham
        if(this.owner instanceof Zombie) { // neu la Zombie
        	// kiem tra cac cuoc tan cong
            for(Entity e : handler.getWorld().getEntityManager().getEntities()) { // nhan danh sach cac thuc the
                if(e.getCollisionBounds(0, 0).intersects(cb)){ // kiem tra thuc the co giao nhau voi gioi han va cham khong
                    if(e.isFood()) { // neu e la thuc an
                        this.setActive(false); // bien mat
                        return;
                    } else if(e.equals(handler.getWorld().getEntityManager().getPlayer())) { // neu e la nguoi choi
                        e.hurt(1); // lam giam suc khoe  
                        this.setActive(false); // active cua vien dan
                        return;
                    }
                }
            }
        }else if(this.owner instanceof Player) { // neu la nguoi choi
            for(Entity e : handler.getWorld().getEntityManager().getEntities()) { // nhan danh sach cac thuc the
                if(e.equals(this) || e.equals(this.owner)) // khong phai la nguoi choi
                    continue; // tiep tuc
                if(e.getCollisionBounds(0, 0).intersects(cb)){ // kiem tra thuc the co giao nhau voi gioi han va cham khong
                    if(e.isFood()){
                        this.setActive(false);
                        return;
                    } else{
                        e.hurt(1);
                        this.setActive(false);
                        return;
                    }
                }
            }
        }
        
        
    }
    
    @Override
    public void die()  {
        
    }
    
    @Override
    public void move() {
        moveX();
        moveY();
    }
    
    public void setMove() {
        // Kiem tra huong de khoi tao xMove va yMove
        
        if(owner.isRight()){
            xMove = speed;
            yMove = 0;
        } else if(owner.isLeft()){
            xMove = -speed;
            yMove = 0;
        }
        else if(owner.isUp()){
            yMove = -speed;
            xMove = 0;
        }
        else if(owner.isDown()){
            yMove = speed;
            xMove = 0;
        }
    }
    
    @Override
    public void moveX(){
    	if(Math.abs(X-x)>150) { // neu khoang cach > [150] thi bien mat
    		active=false;
    	}
    	else if(xMove > 0){ //Tiep tuc di chuyen sang phai neu khong cham vao tile 
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            } else{ //Di chuyen den sat bound cua tile va  bien mat 
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width + 1;
                active = false;
            }
        } else if (xMove < 0) { //Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x - 1;
                active = false;
            }
        }
    }

    @Override
    public void moveY(){
    	if(Math.abs(Y-y)>150) {
    		active=false;
    	}
    	else if(yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y + 1;
                active = false;

            }
        } else if(yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                active = false;

            }
        }
    }
    
    @Override
    public void setLevel(int level) {
        speed = BULLET_SPEED[level];
        this.level = level;
    }
}
