package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import entities.Entity;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.CHARACTER;
import model.Player;
import model.RectangleImage;

public class GameViewManager{
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	private static final int GAME_WIDTH = 800;
	private static final int GAME_HEIGHT = 600;
	
	private Stage menuStage;
	
	private static final int TILE_WIDTH = 64;
	private static final int TILE_HEIGHT = 64;
	
	private int tile[][] = new int[40][40];
	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private boolean isUpKeyPressed;
	private boolean isDownKeyPressed;
	
	Image image = new Image("view/resources/player_tilesheet.png");
	ImageView imageView = new ImageView(image);
	Player player = new Player(imageView);

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	private int renderSorter(Entity entity, Player player){ // bo so sanh cac thuc the
        if(entity.getLayoutY() + entity.getHeight() < player.getLayoutY() + player.getHeight()) // toa do y duoi cung cua cac thuc the
        	return -1; // a hien thi truoc b
        return 1; // a hien thi sau b
    }
    
	public GameViewManager() {
		initializeStage();
	}
	
	
	
	public boolean checkEntityCollision(float xOffset, float yOffset) { // kiem tra va cham cua thuc the
        for(Entity e: entities){ // nhan danh sach cac thuc the 
        	if (e.getName().equals("tree")) {
        		Rectangle Bound = e.getCollisionBounds(0f, 0f);
        		//Rectangle entityBound = new Rectangle(e.getLayoutX() + 1, e.getLayoutY() + 25, Bound.getWidth() - 2, Bound.getHeight() - 30);
        		Rectangle entityBound = new Rectangle(e.getLayoutX() + 1, e.getLayoutY() + 1, Bound.getWidth() - 1 , Bound.getHeight() - 4);
            	if(entityBound.intersects((player.getTranslateX()+ 10 + xOffset), (player.getTranslateY() + 25 + yOffset), player.getWidth()-20, player.getHeight() - 28)){ // co giao cat
                        return true;
            	}
                    
            }
        	if (e.getName().equals("rock")) {
        		Rectangle Bound = e.getCollisionBounds(0f, 0f);
        		Rectangle entityBound = new Rectangle(e.getLayoutX(), e.getLayoutY(), Bound.getWidth(), Bound.getHeight());
            	if(entityBound.intersects((player.getTranslateX()+10 + xOffset), (player.getTranslateY() + 20 + yOffset), player.getWidth()-20, player.getHeight()-20)){ // co giao cat
                        return true;
            	}
        	}
        	if (e.getName().equals("water")) {
        		Rectangle Bound = e.getCollisionBounds(0f, 0f);
        		Rectangle entityBound = new Rectangle(e.getLayoutX(), e.getLayoutY(), Bound.getWidth(), Bound.getHeight());
            	if(entityBound.intersects((player.getTranslateX()+8 + xOffset), (player.getTranslateY() + 16 + yOffset), player.getWidth()/2, player.getHeight()/2)){ // co giao cat
                        return true;
            	}
        	}
        }
        return false; // khong co va cham
    }

	private void moveCharacter() {
		if(isLeftKeyPressed) {
			if (isUpKeyPressed) {
					player.animation.play();
					player.animation.setOffsetX(96);
					player.animation.setOffsetY(32);
					if (!checkEntityCollision(-2,-2)) {
						player.moveX(-2);
						player.moveY(-2);
					} else {
						System.out.println(player.gethealth());
						if (player.minushealth() <= 0) gamePane.getChildren().remove(player);
					}
					
				}
				else if(isDownKeyPressed) {
					player.animation.play();
					player.animation.setOffsetX(96);
					player.animation.setOffsetY(0);
					if (!checkEntityCollision(-2,2)) {
						player.moveX(-2);
						player.moveY(2);
					}
				}
				else {
					player.animation.play();
					player.animation.setOffsetY(32);
					player.animation.setOffsetX(0);
					if (!checkEntityCollision(-2,0)) player.moveX(-2);
				}
				
			} else if (isRightKeyPressed) {
				if (isUpKeyPressed) {
					player.animation.play();
					player.animation.setOffsetX(96);
					player.animation.setOffsetY(96);
					if (!checkEntityCollision(2,-2)) {
						player.moveX(2);
						player.moveY(-2);
					}
				}
				else if(isDownKeyPressed) {
					player.animation.play();
					player.animation.setOffsetX(96);
					player.animation.setOffsetY(64);
					if (!checkEntityCollision(2,2)) {
						player.moveX(2);
						player.moveY(2);
					}
					
				}
				else {
					player.animation.play();
					player.animation.setOffsetY(64);
					player.animation.setOffsetX(0);
					if (!checkEntityCollision(2,0)) player.moveX(2);
				}
			} else if (isUpKeyPressed) {
				player.animation.play();
				player.animation.setOffsetY(96);
				player.animation.setOffsetX(0);
				if (!checkEntityCollision(0,-2)) player.moveY(-2);
			}else if (isDownKeyPressed) {
				player.animation.play();
				player.animation.setOffsetY(0);
				player.animation.setOffsetX(0);
				if (!checkEntityCollision(0,2)) player.moveY(2);
			} else {
				player.animation.stop();
			}
	
		
	}

	private void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		gameStage.setTitle("Map 1");
		Canvas canvas = new Canvas(1400, 800);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		readFile();
		drawBackground(gc);
		gamePane.getChildren().add(canvas);
		
		
		player.setLayoutX(0);
		player.setLayoutY(0);
		gamePane.getChildren().addAll(player);
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
				} else if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = true;
				} else if (event.getCode() == KeyCode.UP) {
					isUpKeyPressed = true;
				} else if (event.getCode() == KeyCode.DOWN) {
					isDownKeyPressed = true;
				}
			}
			
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub 
				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				} else if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = false;
				} else if (event.getCode() == KeyCode.UP) {
					isUpKeyPressed = false;
				} else if (event.getCode() == KeyCode.DOWN) {
					isDownKeyPressed = false;
				}
			}
			
		});
		
	}
	
	private void readFile() {
		File world = new File("src/view/resources/World.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(world);
		} catch (FileNotFoundException e) {
			System.out.println("Error loading file");
		};
		while(sc.hasNextLine()) {
			for (int i = 0; i < 20; i ++) {
				String[] line = sc.nextLine().trim().split(" ");
				for (int j = 0; j < 32; j ++) {
					tile[i][j] = Integer.parseInt(line[j]);
				}
			}
		}
	}
	
	public void drawBackground(GraphicsContext gc) {
		
		Image tileSheet = new Image("view/resources/ClassicRPG_Sheet.png");
		ImageView imageView = new ImageView(tileSheet);
		PixelReader reader = tileSheet.getPixelReader();
		WritableImage grass = new WritableImage(reader, 128, 448, 64, 64);
		WritableImage dirt = new WritableImage(reader, 320, 0, 64, 64);
		WritableImage rock = new WritableImage(reader, 480, 355, 35, 27);
		WritableImage tree = new WritableImage(reader, 256, 256, 64, 64);
		WritableImage water = new WritableImage(reader, 192, 256, 64, 64);
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 13; col++) 
				gc.drawImage(grass, TILE_WIDTH*col, TILE_HEIGHT*row, TILE_WIDTH, TILE_HEIGHT);
		}
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 13; col++) {
				if(tile[row][col] == 3) {
					gc.drawImage(rock, TILE_WIDTH*col, TILE_HEIGHT*row, 35, 27);
					Entity entity = new Entity(imageView, "rock", 480, 355, 35, 27);
					entity.setLayoutX(TILE_WIDTH*col);
					entity.setLayoutY(TILE_HEIGHT*row);
					entities.add(entity);
				}
				if(tile[row][col] == 2) {
					gc.drawImage(tree, TILE_WIDTH*col, TILE_HEIGHT*row, TILE_WIDTH, TILE_HEIGHT);
					Entity entity = new Entity(imageView, "tree", 256, 256, TILE_WIDTH, TILE_HEIGHT);
					entity.setLayoutX(TILE_WIDTH*col);
					entity.setLayoutY(TILE_HEIGHT*row);
					entities.add(entity);
				}
				if(tile[row][col] == 4) gc.drawImage(dirt, TILE_WIDTH*col, TILE_HEIGHT*row, TILE_WIDTH, TILE_HEIGHT);
				if(tile[row][col] == 5) {
					gc.drawImage(water, TILE_WIDTH*col, TILE_HEIGHT*row, TILE_WIDTH, TILE_HEIGHT);
					Entity entity = new Entity(imageView, "water", 192, 256, TILE_WIDTH, TILE_HEIGHT);
					entity.setLayoutX(TILE_WIDTH*col);
					entity.setLayoutY(TILE_HEIGHT*row);
					entities.add(entity);
				}
			}
		}
		
	}

	public void createNewGame(Stage menuStage, CHARACTER choosenCharacter) {
		
		this.menuStage = menuStage;
		this.menuStage.hide();
		createGameLoop();
		gameStage.show();
		
	}

	private void createGameLoop() {
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				//if (!Collision(player,grass1)) 
					moveCharacter();
			}
			
		};
		timer.start();
		
	}

}
