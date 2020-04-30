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
        if(this.owner instanceof Player){
            speed = BULLET_SPEED[0];

        } else{
            speed = BULLET_SPEED[level];
       
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
    
    public void checkAttacks() {
        Rectangle cb = getCollisionBounds(0, 0);
        if(this.owner instanceof Zombie) {
            for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
                if(e.getCollisionBounds(0, 0).intersects(cb)){
                    if(e.isFood()) {
                        this.setActive(false);
                        return;
                    } else if(e.equals(handler.getWorld().getEntityManager().getPlayer())) {
                        e.hurt(1);
                        this.setActive(false);
                        return;
                    }
                }
            }
        }else if(this.owner instanceof Player) {
            for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
                if(e.equals(this) || e.equals(this.owner))
                    continue;
                if(e.getCollisionBounds(0, 0).intersects(cb)){
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
        // Kiem tra hướng cua player de khoi tao xMove va yMove
        
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
    	if(Math.abs(X-x)>150) {
    		active=false;
    	}
    	else if(xMove > 0){ //Tiếp tục di chuyển sang phai nếu không chạm vào tile 
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            } else{ //Di chuyển đến sát bound của tile và biến mất 
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
