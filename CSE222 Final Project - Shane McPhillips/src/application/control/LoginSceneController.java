package application.control;

import application.Main;
import application.model.Utilities;
import application.model.data.StudentUserAccount;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginSceneController {

	//Labels
	@FXML
	private Label incorrectLogin;
	
	//TextFields
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	
	//Buttons
	@FXML
	private Button loginButton;
	@FXML
	private Button signupButton;
	
	@FXML
	private Label incorrect;
	
	public LoginSceneController() {}
	
	@FXML
	public void signup() {
		Scene signupScene = Utilities.getInstance().getFXMLScene("SignupScene.fxml");
		Utilities.getInstance().switchScene(signupButton.getScene(), signupScene);
	}
	
	@FXML
	public void login() {
		incorrect.setVisible(false);
		if (usernameField.getText() == null || usernameField.getText() == null) {
			incorrect.setText("Username and password can not be left empty!");
			incorrect.setVisible(true);
			return;
		}
		
		StudentUserAccount user = Main.getUsersDatabase().getUser(usernameField.getText(), passwordField.getText());
		if (user == null) {
			incorrect.setText("Username and password incorect. Try again.");
			incorrect.setVisible(true);
		} else {
			Main.currentUser = user;
			
			Scene scene = Utilities.getInstance().getFXMLScene("AccountOverviewScene.fxml");
			Utilities.getInstance().switchScene(loginButton.getScene(), scene);
			
		}
		return;
	}
	
	@FXML
	public void checkCredentials() {
		
	}
	
	
	
	

}
