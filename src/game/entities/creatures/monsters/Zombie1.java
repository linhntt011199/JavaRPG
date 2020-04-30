package game.entities.creatures.monsters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import game.Handler;
import game.entities.creatures.Bullet;
import game.gfx.Animation;
import game.gfx.Assets;
import game.tiles.Tile;

public class Zombie1 extends Zombie {
    private static final int[] LIMIT = {3,5};
    private static final int[] BONUS = {100, 150};

    private int xStart, yStart;
    
    private long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;
   
    private ArrayList<Bullet> bullets;
    
    public Zombie1(Handler handler, float x, float y) {
        super(handler, x, y);
        //Animation
        animDown = new Animation(200, Assets.zombie1_down);
        animUp = new Animation(200, Assets.zombie1_up);
        animLeft = new Animation(200, Assets.zombie1_left);
        animRight = new Animation(200, Assets.zombie1_right);
        currentImage = animLeft;
        setMove();
        // Toa do khoi tao
        xStart = (int) (x / Tile.TILEWIDTH);
        yStart = (int) (y / Tile.TILEHEIGHT);
        
        //Bullet               
        bullets = new ArrayList<Bullet>();
    }
    
    @Override
    public void tick() {
        //Animation
        currentImage.tick();
        //Movement
        move();
        //Attack
        attack();
        
        Iterator<Bullet> it = bullets.iterator();
        while(it.hasNext()){
            Bullet b = it.next();
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
      
        if(isUp()){
                bullets.add(new Bullet(handler,this, x + width / 4, y));
               
        } else if(isDown()) {
            bullets.add(new Bullet(handler,this, x + width /4, y + height));
                
        }else if(isLeft()) {
            bullets.add(new Bullet(handler,this, x - width / 2, y + height / 4));
                
        }else if(isRight()) {
            bullets.add(new Bullet(handler,this, x + width, y + height / 4));
        }
        
        attackTimer = 0;
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x), (int) (y), width, height, null);
        bullets.forEach((b) -> {
            if(b.isActive())
                b.render(g);
        });
    }
    
    private BufferedImage getCurrentAnimationFrame() {
        if(xMove < 0){
            currentImage = animLeft;
        } else if (xMove > 0) {
            currentImage = animRight;
        } else if (yMove < 0) {
            currentImage = animUp;
        } else if (yMove > 0) {
            currentImage = animDown;
        }
    return currentImage.getCurrentFrame();
        
    }
    
    public void setMove() {
        Random rd = new Random();
        int i = rd.nextInt(2);
        if(i==1) {
            xMove = speed;
            yMove = 0;
        }
        else{
            xMove = 0;
            yMove = speed;
        }
            
    }
    
    @Override
    public void move() {
        moveX();
        moveY();
    }
    
    @Override
    public void moveX(){
        if(xMove > 0){ //Tiếp tục di chuyển sang trái nếu không chạm vào tile và đi trong khoảng cố định 
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if((Math.abs(tx - xStart) < LIMIT[level]) && (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))){
                x += xMove;
            }else if(Math.abs(tx - xStart) >= LIMIT[level]) {
                xMove = -xMove;
            }
            else{ //Di chuyển đến sát bound của tile và quay lai 
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
                xMove = -xMove;
            }
        } else if (xMove < 0) { // Đi sang trái 
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if((Math.abs(tx - xStart) < LIMIT[level]) && (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))){
                x += xMove;
            } else if(Math.abs(tx - xStart) >= LIMIT[level]) {
                xMove = -xMove;
            }
            else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
                xMove = -xMove;
            }
        }
    }

    @Override
    public void moveY(){
        if(yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            
            if((Math.abs(ty - yStart) < LIMIT[level]) && (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))){
                y += yMove;
            } else if(Math.abs(ty - yStart) >= LIMIT[level]) {
                yMove = -yMove;
            } 
            else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
                yMove = -yMove;

            }
        } else if(yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            
            if((Math.abs(ty - yStart) < LIMIT[level]) && (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))){
                y += yMove;
            } else if(Math.abs(ty - yStart) >= LIMIT[level]) {
                yMove = -yMove;
            }
            else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height;
                yMove = -yMove;

            }
        }
    }
    

}
