package application;
	
import java.io.File;
import java.util.LinkedList;

import application.model.Utilities;
import application.model.data.College;
import application.model.data.StudentUserAccount;
import application.model.data.handle.CollegeJsonHandle;
import application.model.data.handle.CollegeSQLLiteHandle;
import application.model.data.handle.UserChoicesSQLLiteHandle;
import application.model.data.handle.UsersSQLLiteHandle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static CollegeSQLLiteHandle cHandle;
	private static UsersSQLLiteHandle uHandle;
	private static UserChoicesSQLLiteHandle ucHandle;
	
	private static CollegeJsonHandle cjHandle;
	
	public static StudentUserAccount currentUser = null;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene loginScene = Utilities.getInstance().getFXMLScene("LoginScene.fxml");
			primaryStage.setScene(loginScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initDatabases() {
		if (!new File("data/CollegeInfoDB.sqlite").exists()) {
			cHandle = new CollegeSQLLiteHandle();
			cjHandle = new CollegeJsonHandle();
			LinkedList<College> collegeData = cjHandle.getCollegeData();
			getCollegesDatabase().insertLinkedList(collegeData);
			collegeData = null;
		} else {
			cHandle = new CollegeSQLLiteHandle();
		}
		ucHandle = new UserChoicesSQLLiteHandle();
		uHandle = new UsersSQLLiteHandle();
	}
	
	
	public static CollegeSQLLiteHandle getCollegesDatabase() {
		return cHandle;
	}
	
	public static UsersSQLLiteHandle getUsersDatabase() {
		return uHandle;
	}
	
	public static UserChoicesSQLLiteHandle getUserChoiceDatabase() {
		return ucHandle;
	}
	
	public static void main(String[] args) {
		initDatabases();
		launch(args);
	}
}
