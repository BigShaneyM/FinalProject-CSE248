package application.control;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.Utilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AccountOverviewSceneController implements Initializable{
	
	@FXML
	private Button backButton;
	@FXML
	private ListView<String> listView;
	@FXML
	private Button searchButton;
	
	@FXML
	private TextField field;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button removeButton;
	

	public AccountOverviewSceneController() {
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(URL url, ResourceBundle res) {
		
		listView.setItems(FXCollections.observableArrayList(Main.currentUser.getIDS()));
	}
	
	
	@FXML
	public void lookForColleges() {
		Scene scene = Utilities.getInstance().getFXMLScene("CollegeSearchScene.fxml");
		Utilities.getInstance().switchScene(searchButton.getScene(), scene);
	}
	
	@FXML
	public void back() {
		Scene scene = Utilities.getInstance().getFXMLScene("LoginScene.fxml");
		Utilities.getInstance().switchScene(backButton.getScene(), scene);
	}
	
	@FXML
	public void add() {
		if (field.getText() != null || field.getText() != "") {
			Main.currentUser.addChoice(field.getText());
			
			listView.setItems(FXCollections.observableArrayList(Main.currentUser.getIDS()));
		}
	}
	
	@FXML
	public void remove() {
		if (field.getText() != null || field.getText() != "") {
			Main.currentUser.removeChoice(field.getText());
			
			listView.setItems(FXCollections.observableArrayList(Main.currentUser.getIDS()));
		}
	}

}
