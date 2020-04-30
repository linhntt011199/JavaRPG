package game.worlds;

import java.awt.Graphics;
import java.util.Random;
import game.Handler;
import game.entities.EntityManager;
import game.entities.creatures.Player;
import game.entities.creatures.monsters.Zombie;
import game.entities.creatures.monsters.Zombie1;
import game.entities.creatures.monsters.Zombie2;
import game.entities.statics.Rock;
import game.entities.statics.Tree;
import game.tiles.Tile;
import game.utils.Utils;

public class World {
    
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int numberOfMonster, numberOfFood;
    private int[][] tile;
    private int[] monsters, food;
    private EntityManager entityManager;    
    
    public World(Handler handler, Player player, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, player);
        loadWorld(path);
        //Add monster
        for(int i = 0; i < monsters.length; i += 3) {
            
            if(monsters[i] == 1)
                entityManager.addEntity(new Zombie(handler, monsters[i+1], monsters[i+2]));
            else if(monsters[i] == 2)
                entityManager.addEntity(new Zombie1(handler, monsters[i+1], monsters[i+2]));
            else if(monsters[i] == 3)
                entityManager.addEntity(new Zombie2(handler, monsters[i+1], monsters[i+2]));
        }
        
        //Add Food
        for(int i = 0; i < food.length; i += 2) {
            Random rd = new Random();
            int id = rd.nextInt(2);
            if(id == 0){
                entityManager.addEntity(new Tree(handler, food[i], food[i+1]));
            }else if(id == 1){
                entityManager.addEntity(new Rock(handler, food[i], food[i+1]));
            }
        }

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);

    }
    
    public void tick() {
        entityManager.tick();
    }
    
    public void render(Graphics g) {
         
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                getTile(x,y).render(g,(int) (x * Tile.TILEWIDTH ),
                        (int) (y * Tile.TILEHEIGHT));
            }
        }
        
        //Entities
        entityManager.render(g);
        
        //Score 
        Score score = new Score(handler.getWorld().getEntityManager().getPlayer().getScore());
        Score.render(g);
    }
    
    
    
    public Tile getTile(int x, int y) {
        
        return Tile.tiles[this.tile[x][y]];
    }
    
    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        //Monster
        numberOfMonster = Utils.parseInt(tokens[4]);
        monsters = new int[numberOfMonster * 3];
        for (int i = 0; i < monsters.length; i++) {
                monsters[i] = Utils.parseInt(tokens[i + 5]);
        }
        
        //Food
        numberOfFood = Utils.parseInt(tokens[5 + monsters.length]);
        food = new int[numberOfFood * 2];
        for (int i = 0; i < food.length; i++) {
            food[i] = Utils.parseInt(tokens[i + 6 + monsters.length]);
        }
        
        //Tile
        tile = new int[width][height];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++){
                tile[x][y] = Utils.parseInt(tokens[(x + y * width) + 6 + monsters.length + food.length]);
            }
        }
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public int[][] getTiles() {
        return tile;
    }

    public int getNumberOfMonster() {
        return numberOfMonster;
    }

    public void setNumberOfMonster(int numberOfMonster) {
        this.numberOfMonster += numberOfMonster;
    }
}
