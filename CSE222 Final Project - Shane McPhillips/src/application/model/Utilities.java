package application.model;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Utilities {

	private static Utilities instance = null;
	private static final int SCENE_WIDTH = 768;
	private static final int SCENE_HEIGHT = 384;
	
	private Utilities() {}
	
	public static Utilities getInstance() {
		return (instance == null) ? instance = new Utilities() : instance;
	}
	
	public void switchScene(Scene currentScene, Scene newScene) {
		Stage stage = (Stage) currentScene.getWindow();
		stage.setScene(newScene);
		stage.show();
	}
	
	public Scene getFXMLScene(String sceneFileName) {
		Scene scene = null;
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/" + sceneFileName));
		Parent root = null;
		try {
			root = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("The file " + sceneFileName + " could not be loaded as a scene. [ERROR]: " + e.getMessage());
			return null;
		}
		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
		return scene;
	}

}
