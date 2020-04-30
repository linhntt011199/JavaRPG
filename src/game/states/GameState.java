package game.states;

import java.awt.Graphics;
import game.Handler;
import game.entities.creatures.Player;
import game.worlds.World;

public class GameState extends State {
    
    private World world;
    private Player player;

    public GameState(Handler handler, Player player, String path) {
        super(handler);
        this.player = player;
        world = new World(handler, player, path);
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
        
    }
    
    @Override
    public void render(Graphics g) {
        world.render(g);
        
    }

    public Player getPlayer() {
        return player;
    }
    
    public World getWorld() {
		return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }   

    
}
