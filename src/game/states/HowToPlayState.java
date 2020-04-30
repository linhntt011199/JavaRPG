package game.states;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import game.Handler;
import game.ui.UIManager;

public class HowToPlayState{
    
    private UIManager uiManager;
    private JButton b1; 
    private MenuState m;
    private Handler handler;
    
    public HowToPlayState(Handler handler) throws IOException{
    	
    	uiManager = new UIManager(handler);
    	JFrame f = new JFrame();
        handler.getMouseManager().setUiManager(uiManager);
	final JLabel tf =new JLabel(new ImageIcon("res/textures/tutorial.png"));
 
    	b1 = new JButton("Exit");    
        b1.setBounds(210,330,80,30);    
        b1.addActionListener((ActionEvent e) -> {
            handler.getMouseManager().setUiManager(uiManager);
            m=new MenuState(handler);
            f.dispose();  
        });
           
        
        f.add(b1);
        f.getContentPane().add(new JScrollPane(tf));
        f.setSize(500,400);
        f.setLocation(0,0);
        f.setVisible(true);
    }
}
