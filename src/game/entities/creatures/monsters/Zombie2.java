package game.entities.creatures.monsters;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import game.Handler;
import game.entities.creatures.Bullet;
import game.gfx.Animation;
import game.gfx.Assets;
import game.tiles.Tile;

public class Zombie2 extends Zombie {    
    private long lastAttackTimer, attackCooldown = 1000, attackTimer = attackCooldown;
    private static final int[] BONUS = {200, 400};

    private float xPlayer, yPlayer;
    private final float xZ, yZ;
    
    private ArrayList<Bullet> bullets;
    
    public Zombie2(Handler handler, float x, float y) {
        super(handler, x, y);
        xZ=x;
        yZ=y;
        speed = ZOMBIE_SPEED[level];
        xMove = speed;
        yMove = 0;
  
        //Animation
        animDown = new Animation(200, Assets.zombie_down);
        animUp = new Animation(200, Assets.zombie_up);
        animLeft = new Animation(200, Assets.zombie_left);
        animRight = new Animation(200, Assets.zombie_right);
        
        currentImage = animDown;
        
        bullets = new ArrayList<Bullet>();
    }
    
    @Override
    public void tick() {
        //Animation
        currentImage.tick();
        move();
        attack();
        
        Iterator<Bullet> it = bullets.iterator();
        while(it.hasNext()){
            Bullet b = it.next();
            b.setSpeed(b.BULLET_SPEED[level]);
            b.tick();
            if(!b.isActive())
                it.remove();
        }
    }
    
    @Override
    public void attack() {
    	attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;
      
        int i=1;

        float xx=x+width/4;
        float yy=y+height/4;
        while(i>0) {
        	
        if(currentImage == animUp){
        	yy-=10;
        } else if(currentImage == animDown) {
        	yy+=10;
        }else if(currentImage == animLeft) {
        	xx-=10;
        }else if(currentImage == animRight) {
        	xx+=10;
        }
        bullets.add(new Bullet(handler,this, xx, yy));
        	i--;
        }
        attackTimer = 0;
    }
    
    
    @Override
    public void render(Graphics g) {
        g.drawImage(currentImage.getCurrentFrame(), (int) x, (int) y, width, height, null);
        bullets.forEach((b) -> {
            b.render(g);
        });
    }

    
    @Override
    public void move() {
    	xPlayer=handler.getWorld().getEntityManager().getPlayer().getX();
        yPlayer=handler.getWorld().getEntityManager().getPlayer().getY();
        if(Math.abs(yPlayer-yZ)>150 || Math.abs(xPlayer-xZ)>150) {
        	return;
        }
        if((Math.abs(x-xPlayer)>Math.abs(y-yMove))||x==xPlayer) { // tien den nguoi choi
        	if(y>yPlayer)
        		yMove=-speed;
        	else
        		yMove=+speed;
        	moveY();
        }
        	
        else {
        	if(x>xPlayer)
        		xMove=-speed;
        	else
        		xMove=+speed;
        	moveX();
        } 
        
    }
    
    @Override
    public void moveX(){
        if(xMove > 0){ //Tiếp tục di chuyển sang trái nếu không chạm vào tile 
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
                currentImage=animRight;
            } else{ //Di chuyển đến sát bound của tile và biến mất 
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
                xMove = -xMove;
                moveY();
            }
        } else if (xMove < 0) { //Moving right
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
                currentImage = animLeft;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
                xMove = -xMove;
                moveY();
            }
        }
    }

    @Override
    public void moveY(){
        if(yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
                currentImage=animUp;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
                yMove = -yMove;
                moveX();
            }
        } else if(yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
                currentImage=animDown;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height;
                yMove = -yMove;
                moveX();
            }
        }
    }
    
}
