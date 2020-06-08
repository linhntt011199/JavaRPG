package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Entity;
import entities.Monster;
import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.MODE;

public class GameViewManager {
	private static final int tile_width = 64, tile_height = 64;
	private static final int ncols = 32, nrows = 20; // number of rows & columns of map
	private static final int GAME_WIDTH = ncols*tile_width, GAME_HEIGHT = nrows*tile_height;
	private static int [][] tile = new int[100][100];
	private static boolean [][] visited = new boolean[100][100];
	private ArrayList<Entity> gameObject2D = new ArrayList<>();
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private Stage menuStage;
	private boolean movingRight;
	private boolean movingLeft;
	private boolean movingUp;
	private boolean movingDown;
	private Player player;
	private Monster monster1, monster2;
	
	
	
	public GameViewManager() {
		initinalizeStage();
		createKeyListener();
	}

	private void initinalizeStage() {
		gameStage = new Stage();
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage.setScene(gameScene);
		gameStage.setTitle("Hi! 🤗🤗");
	}
	
	private void createKeyListener() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					movingLeft = true;
				} else if (event.getCode() == KeyCode.RIGHT) {
					movingRight = true;
				} else if (event.getCode() == KeyCode.UP) {
					movingUp = true;
				} else if (event.getCode() == KeyCode.DOWN) {
					movingDown = true;
				} 
			}
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					movingLeft = false;
				} else if (event.getCode() == KeyCode.RIGHT) {
					movingRight = false;
				} else if (event.getCode() == KeyCode.UP) {
					movingUp = false;
				} else if (event.getCode() == KeyCode.DOWN) {
					movingDown = false;
				} 
			}
		});
	}
	
	private void readFile() {
		File world = new File("src/view/resources/world2.txt");
        Scanner sc = null;
		try {
			sc = new Scanner(world);
		} catch (FileNotFoundException e) {
			System.out.println("Error loading file");
		}; 
        while(sc.hasNextLine()) {
           for (int i=0; i<nrows; i++) {
              String[] line = sc.nextLine().trim().split(" ");
              for (int j=0; j<ncols; j++) {
                 tile[i][j] = Integer.parseInt(line[j]);
              }
           }
        }		
	}
	
	public void createNewGame(Stage menuStage, MODE choosenMode) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		readFile();
		loadWorld(gc);
		gamePane.getChildren().add(canvas);
		createMonster();
		gameObject2D.add(monster1);
		gameObject2D.add(monster2);
		createPlayer();
		
		createGameLoop();
		gameStage.show();
	}
	
	private void createPlayer() {
		Image image = new Image("view/resources/character.png");
		ImageView imageView = new ImageView(image); 
		player = new Player(imageView);
		gamePane.getChildren().add(player);
		player.addCollision(gameObject2D);
	}
	
	private void createMonster() {
		Image image1 = new Image("view/resources/EntitySet.png");
		ImageView imageView1 = new ImageView(image1); 
		monster1 = new Monster(imageView1, "monster", 100, 2, 400, 400, 400, 400, 32, 32, 3, 3);
		gamePane.getChildren().add(monster1);
		monster1.setOffsetX(32*6);
		monster1.setOffsetY(0);
		monster1.addCollision(gameObject2D);
		
		
		
		Image image2 = new Image("view/resources/monster.png");
		ImageView imageView2 = new ImageView(image2); 
		monster2 = new Monster(imageView2, "monster", 100, 2, 300, 200, 400, 400, 64, 64, 8, 8);
		gamePane.getChildren().add(monster2);
		monster2.addCollision(gameObject2D);
	}

	public void loadWorld(GraphicsContext gc) {
		Image tileset = new Image("view/resources/tileset.png");
		ImageView imageView = new ImageView(tileset);
        PixelReader reader = tileset.getPixelReader();
        WritableImage grass = new WritableImage(reader, 128, 448, 64, 64);
		WritableImage dirt = new WritableImage(reader, 320, 0, 64, 64);
		WritableImage rock = new WritableImage(reader, 480, 355, 35, 27);
		WritableImage tree = new WritableImage(reader, 256, 256, 64, 64);
		WritableImage water = new WritableImage(reader, 192, 256, 64, 64);
        
        for(int row = 0; row < nrows; row++)
            for(int col = 0; col < ncols; col++)
            	gc.drawImage(grass, tile_width*col, tile_height*row, tile_width, tile_height);
        
        for(int row = 0; row < nrows; row++) {
            for(int col = 0; col < ncols; col++) {
            	if (!visited[row][col]) {
	            	if (tile[row][col] == 2) {
	            		gc.drawImage(tree, tile_width*col, tile_height*row, tile_width, tile_height);
	            		visited(tree, row, col);
	            		gameObject2D.add(new Entity(imageView, "tree", tile_width*col, tile_height*row, tile_width, tile_height));
	            	}
	            	else if (tile[row][col] == 3) {
	            		gc.drawImage(rock, tile_width*col, tile_height*row, 35, 27);
	            		visited(rock, row, col);
	            		gameObject2D.add(new Entity(imageView, "rock", tile_width*col, tile_height*row, 35, 27));
	            	}
	            	else if (tile[row][col] == 4) {
	            		gc.drawImage(dirt, tile_width*col, tile_height*row, tile_width, tile_height);
	            		visited(tree, row, col);
	            	}
	            	else if(tile[row][col] == 5) {
						gc.drawImage(water, tile_width*col, tile_height*row, tile_width, tile_height);
						visited(water, row, col);
	            		gameObject2D.add(new Entity(imageView, "water", tile_width*col, tile_height*row, tile_width, tile_height));	
					}
//	            	else continue;
            	}
            }
        }
        for (Entity e : gameObject2D) {
        	System.out.println("x: " + e.getLayoutX() + "y: " + e.getLayoutY());
        }
    }
	
	private void visited(WritableImage image, int dx, int dy) {
		for(int i = dx; i < dx + image.getWidth()/tile_width; i++)
            for(int j = dy; j < dy + image.getHeight()/tile_width; j++)
            	visited[i][j] = true;
	}

	private void createGameLoop() {
		
		 //h = 2;
		AnimationTimer gameTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				monster1.moveMonster(player, 1, 2, 3, 0); // left, right, up, down
				monster2.moveMonster(player, 3, 1, 2, 0);
				moveCharacter();
			}	
		};
		gameTimer.start();
	}
	
	
	
	
	
	private void moveCharacter() {
		if(movingLeft) {
			if (movingUp) {
					player.animation.play();
					player.animation.setOffsetX(96);
					player.animation.setOffsetY(32);
					if (!player.checkEntityCollision(-2,-2)) {
						player.moveX(-2);
						player.moveY(-2);
					} else {
//						System.out.println(player.gethealth());
//						if (player.minushealth() <= 0) gamePane.getChildren().remove(player);
					}
					
				}
				else if(movingDown) {
					player.animation.play();
					player.animation.setOffsetX(96);
					player.animation.setOffsetY(0);
					if (!player.checkEntityCollision(-2,2)) {
						player.moveX(-2);
						player.moveY(2);
					}
				}
				else {
					player.animation.play();
					player.animation.setOffsetY(32);
					player.animation.setOffsetX(0);
					if (!player.checkEntityCollision(-2,0)) player.moveX(-2);
				}
				
		} else if (movingRight) {
				if (movingUp) {
					player.animation.play();
					player.animation.setOffsetX(96);
					player.animation.setOffsetY(96);
					if (!player.checkEntityCollision(2,-2)) {
						player.moveX(2);
						player.moveY(-2);
					}
				}
				else if(movingDown) {
					player.animation.play();
					player.animation.setOffsetX(96);
					player.animation.setOffsetY(64);
					if (!player.checkEntityCollision(2,2)) {
						player.moveX(2);
						player.moveY(2);
					}				
				}
				else {
					player.animation.play();
					player.animation.setOffsetY(64);
					player.animation.setOffsetX(0);
					if (!player.checkEntityCollision(2,0)) player.moveX(2);
				}
		} else if (movingUp) {
				player.animation.play();
				player.animation.setOffsetY(96);
				player.animation.setOffsetX(0);
				if (!player.checkEntityCollision(0,-2)) player.moveY(-2);
		} else if (movingDown) {
				player.animation.play();
				player.animation.setOffsetY(0);
				player.animation.setOffsetX(0);
				if (!player.checkEntityCollision(0,2)) player.moveY(2);
			} else {
				player.animation.stop();
			}
	}
}
