package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class loadingController implements Initializable {
	
	@FXML
    private Label label;
    
	@FXML
	private ProgressBar progressBar;
	
	class cur_Thread implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				progressBar.setProgress(i/100.0);
//				label.setText(i + "%");
				try {
					Thread.sleep(70);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progressBar.setProgress(0.0);
//		label.setText(0.0 + "%");	
		Thread th = new Thread(new cur_Thread());
		th.start();
	}
}
