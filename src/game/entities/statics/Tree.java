package game.entities.statics;

import java.awt.Graphics;
import game.Handler;
import game.gfx.Assets;
import game.tiles.Tile;

public class Tree extends StaticEntity { // cay
    private static final int[] BONUS = {100, 150};

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT); // chieu rong va chieu cao bang 1 o
        health = 1;
        // thiet lap hop gioi han
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
    }
    
    @Override
    public void tick() {
    }
    
    @Override
    public void die() { // pha huy
        handler.getWorld().getEntityManager().getPlayer().setScore(BONUS[level]);
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.wood, (int) (x), // ve hinh anh
                (int) (y), width, height, null);
    }
    
    @Override
    public boolean isFood() {
        return true;
    }
}
