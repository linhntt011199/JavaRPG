package game.states;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import game.Handler;
import game.ui.UIManager;

public class Gamewin {
    private UIManager uiManager;
    private Handler handler;
    private JFrame f;

    public Gamewin(Handler handler) throws IOException{
    	uiManager = new UIManager(handler);
    	f = new JFrame();
        handler.getMouseManager().setUiManager(uiManager);
	final JLabel label2=new JLabel("YOU WIN---Score: " + handler.getGame().getPlayer().getScore(),JLabel.CENTER);
	label2.setPreferredSize(new Dimension(850, 600));
        f.getContentPane().add(label2);
        f.setSize(500,400);
        f.setLocation(336,214);
        f.setVisible(true);
    }

    public void close() {
        f.dispose();
    }
}
