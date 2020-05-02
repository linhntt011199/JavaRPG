package game.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import game.Handler;
import game.entities.Entity;
import game.gfx.Animation;
import game.gfx.Assets;

public class Player extends Creature {
    //Attack timer: bo dem thoi gian tan cong
    private long lastAttackTimer, 
    				attackCooldown = 300, // sau 300 miligiay cho phep nguoi choi tan cong lai 
    				attackTimer = attackCooldown; // nguoi choi co the tan cong ngay khi bat dau
    //Bullet
    private ArrayList<Bullet> bullets;
    //Score
    public static long score = 0;
    
    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT); // toa do x,y, chieu cao, chieu rong mac dinh
        health = 1;
        // thiet lap ranh gioi va cham phu hop voi co the cua nguoi choi
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 20;
        bounds.height = 30;
        
        //Animation
        animDown = new Animation(200, Assets.player_down); // toc do 200 milis
        animUp = new Animation(200, Assets.player_up);
        animLeft = new Animation(200, Assets.player_left);
        animRight = new Animation(200, Assets.player_right);
        currentImage = animDown;
        //Bullets
        bullets = new ArrayList<Bullet>();
        
        
    }

    @Override
    public void tick() {
        //Animation
        currentImage.tick();
        //Movement
        getInput(); // dau vao
        move();
        //Attack 
        attack();
        checkAttacks(); // kiem tra tan cong
        //Bullet 
        Iterator<Bullet> it = bullets.iterator(); // vong lap co dan
        while(it.hasNext()){
            Bullet b = it.next();
            b.tick(); // danh dau
            if(!b.isActive())
                it.remove();
        }
        
    }
    
    public void checkAttacks() { // kiem tra xem nguoi dung co dang tan cong khong
        Rectangle cb = getCollisionBounds(0,0);  // gioi han va cham, co toa do cua hop va cham
        Rectangle ar = new Rectangle(); // hinh chu nhat tan cong
        int arSize = 4; // kich thuoc ar
        ar.width = arSize;
        ar.height = arSize;
        
        if(currentImage == animUp){ // phia tren
            ar.x = cb.x + cb.width / 2 - arSize / 2; // gioi han hinh chu nhat tan cong se tap trung vao nguoi choi
            // cb.x + cb.width / 2: toa do trung tam cua nguoi choi
            ar.y = cb.y - arSize; // tren gioi han va cham
        } else if(currentImage == animDown) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height; // ben duoi gioi han va cham
        }else if(currentImage == animLeft) { // ben trai
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize; // can giua 
        }else if(currentImage == animRight) { // ben phai
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else // neu khong co nut tan cong nao duoc nhan
            return;
      
     // kiem tra cac cuoc tan cong
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){ 
            if(e.equals(this)) // khong phai la nguoi choi
                continue; // tiep tuc
            if(e.getCollisionBounds(0, 0).intersects(ar)){ // kiem tra thuc the co giao nhau voi hinh chu nhat tan cong khong
            												// neu co tuc la dang tan cong thuc the nay
                if(e.isFood()){
                    e.hurt(1); // lam ton thuong e
                    return; // thoat vi nguoi choi chi tan cong mot thuc the tai mot thoi diem
                }else{
                    this.hurt(1);
                    return;
                }   
            }
        }
    }
    
    public void attack() {
        if(handler.getKeyManager().space) {
        	// cap nhat bo dem thoi gian
            attackTimer += System.currentTimeMillis() - lastAttackTimer;
            lastAttackTimer = System.currentTimeMillis();
            if(attackTimer < attackCooldown) // neu bo dem thoi gian nho hon thoi gian hoi chieu -> nguoi choi phai cho them
                return; // khong chay bat ki hoat dong nao o ben duoi
            if(isUp()){
                bullets.add(new Bullet(handler,this, x + width / 4, y));
               
            } else if(isDown()) {
                bullets.add(new Bullet(handler,this, x + width /4, y + height));
                
            }else if(isLeft()) {
                bullets.add(new Bullet(handler,this, x - width / 2, y + height / 4));
                
            }else if(isRight()) {
                bullets.add(new Bullet(handler,this, x + width, y + height / 4));
            }
        }
        attackTimer = 0; // thiet lap lai
    }

    @Override
    public void die() {
    	handler.getGame().setDie_player(true);
        
    }
    
    private void getInput() { // nhan dau vao
        xMove = 0;
        yMove = 0;
        if(handler.getKeyManager().up) // phim len
            yMove = -speed;
        if(handler.getKeyManager().down) // phim xuong
            yMove = speed;
        if(handler.getKeyManager().left) // phim trai
            xMove = -speed;
        if(handler.getKeyManager().right) // phim phai
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x), (int) (y), width, height, null); // ve o toa do x,y, chieu rong width, chieu cao height
        // getCurrentAnimationFrame(): khung hoat hinh hien tai
        bullets.forEach((b) -> {
            b.render(g);
        });
    }
    
    private BufferedImage getCurrentAnimationFrame() { // lay khung hinh dong
    	// hien thi hinh anh dong phu thuoc vao cach di chuyen
        if(xMove < 0){ // di chuyen sang trai
            currentImage = animLeft;
        } else if (xMove > 0) { // di chuyen sang phai
            currentImage = animRight;
        } else if (yMove < 0) { // di chuyen len
            currentImage = animUp;
        } else if (yMove > 0) {
            currentImage = animDown;
        }
        return currentImage.getCurrentFrame();
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score += score;
    }
    public void setcore() {
    	this.score = 0;
    }
    
}