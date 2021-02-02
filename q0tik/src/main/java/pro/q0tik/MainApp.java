package pro.q0tik;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {
    
	public static GameWindow gameWindow = new GameWindow();
	
    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
        s.setScene(new Scene(gameWindow));
        s.show();
    }

    public static void restart() {
    	Stage s = (Stage) gameWindow.getScene().getWindow();
    	s.close();
    	gameWindow = new GameWindow();
    	s.setScene(new Scene(gameWindow));
    	s.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
