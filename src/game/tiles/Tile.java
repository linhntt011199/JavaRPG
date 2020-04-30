package game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile rockTile = new RockTile(2);
    public static Tile stoneTile = new StoneTile(3);
    public static Tile fruitTile = new FruitTile(4);
    public static Tile treeTile = new TreeTile(5);
    public static Tile stonegrassTile = new StonegrassTile(6);
    public static Tile waterTile = new WaterTile(7);
    public static Tile woodTile = new WaterTile(8);
    
    public static final int TILEWIDTH = 32, TILEHEIGHT = 32;
    
    protected BufferedImage texture;
    protected final int id;
    protected boolean solid, food=false;
    
    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }
    
    public void tick() {
        
    }
    
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }
    
    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
    
    
    public int getId() {
        return id;
    }

    public boolean isFood() {
        return food;
    }
    
}
