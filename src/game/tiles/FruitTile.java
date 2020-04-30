package game.tiles;

import game.gfx.Assets;

public class FruitTile extends Tile {
    
    public FruitTile(int id) {
        super(Assets.fruit, id);
        solid = true;
    }

}
