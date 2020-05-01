package game.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import game.Handler;
import game.entities.creatures.Player;

public class EntityManager { // quan ly thuc the
    private Handler handler; // xu ly
    private Player player; // doi tuong nguoi choi
    private ArrayList<Entity> entities; // danh sach cac thuc the
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){ // bo so sanh cac thuc the
        @Override
        public int compare(Entity a, Entity b) { // phuong thuc so sanh
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight()) // toa do y duoi cung cua cac thuc the
                return -1; // a hien thi truoc b
            return 1; // a hien thi sau b
        }
    };

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>(); // danh sach mang moi
        addEntity(player);
    }
    public void tick() { // danh dau

        Iterator<Entity> it = entities.iterator(); // vong lap cua cac loai thuc the
        while(it.hasNext()){
            Entity e = it.next();
            e.tick(); // danh dau
            if(!e.isActive()) // kiem tra e co hoat dong khong
                it.remove(); // xoa thuc the
        }
        entities.sort(renderSorter); // goi sap xep
    }
    
    public void render(Graphics g) { // hien thi cac thuc the trong tro choi
        for(Entity e: entities) {
            e.render(g);
        }
    }
    
    public void addEntity(Entity e) { // nhan mot thuc the e
        entities.add(e); // them mot thuc the 
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    
    
}
