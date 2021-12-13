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

public class SignupSceneController {
	
	@FXML
	private Button backButton;
	@FXML
	private Button createAccountButton;
	
	@FXML
	private TextField fullnameField;
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField zipcodeField;
	@FXML
	private TextField stateField;
	
	@FXML
	private Label incorrect;
	@FXML
	private Label created;

	public SignupSceneController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@FXML
	public void signup() {
		created.setVisible(false);
		incorrect.setVisible(false);
		if (fullnameField.getText()== null) {
			incorrect.setText("You must enter your full name!");
			incorrect.setVisible(true);
		} else if (usernameField.getText() == null) {
			incorrect.setText("You must enter a username!");
			incorrect.setVisible(true);
		} else if (passwordField.getText() == null) {
			incorrect.setText("You must enter a password!");
			incorrect.setVisible(true);
		} else if (zipcodeField.getText() == null) {
			incorrect.setText("You must enter a 6 digit zipcode!");
			incorrect.setVisible(true);
		} else if (stateField.getText() == null) {
			incorrect.setText("You must enter your state initials!");
			incorrect.setVisible(true);
		} else if (Main.getUsersDatabase().containsUsername(usernameField.getText())) {
			incorrect.setText("That username is taken already! Please enter a different one.");
			incorrect.setVisible(true);
		} else {
		
			//Create record in database.
			StudentUserAccount user = new StudentUserAccount(fullnameField.getText(), usernameField.getText(), passwordField.getText(), Integer.parseInt(zipcodeField.getText()), stateField.getText());
			Main.getUsersDatabase().insertUser(user);
			created.setVisible(true);
		}
	}
	
	@FXML
	public void back() {
		Scene scene = Utilities.getInstance().getFXMLScene("LoginScene.fxml");
		Utilities.getInstance().switchScene(backButton.getScene(), scene);
	}
	

}
