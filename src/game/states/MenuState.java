package game.states;

import java.awt.Graphics;
import java.io.IOException;

import game.Handler;
import game.gfx.Assets;
import game.ui.UIImageButton;
import game.ui.UIManager;
import game.worlds.Score;

public class MenuState extends State {

    private UIManager uiManager;
    private ChooseState c;
    private HowToPlayState h;
    
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        
        uiManager.addObject(new UIImageButton(336, 150, 128, 64, Assets.btn_start, () -> {
            c = new ChooseState(handler);
        }));
        
        uiManager.addObject(new UIImageButton(336, 214, 128, 64, Assets.how_to_play_button, () ->{ 
            try {
		h=new HowToPlayState(handler);
            } catch (IOException e) {
		e.printStackTrace();
            }
        }));
        
        uiManager.addObject(new UIImageButton(336, 278, 128, 64, Assets.quit_button, () ->{ 
            System.exit(0);
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();        
       
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
        new Score(0);
        long highestScore = Score.getHighestScoreFromFile();
        g.drawString("Highest Score: "+ highestScore, 350, 400);
    }

    public UIManager getUiManager() {
        return uiManager;
    }
    
    
    
}

