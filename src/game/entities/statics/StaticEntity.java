package game.entities.statics;

import game.Handler;
import game.entities.Entity;

public abstract class StaticEntity extends Entity {
    
    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        food = true;
        health = 1;
    }
   
    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    
}
