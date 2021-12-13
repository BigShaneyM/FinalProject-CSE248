package application.control;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.model.Utilities;
import application.model.data.TabulatedCollege;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CollegeSearchSceneController implements Initializable {

	@FXML
	private Button backButton;
	@FXML
	private Button searchButton;
	@FXML
	private Button addButton;
	
	@FXML
	private CheckBox p, pnp, pfp;
	
	@FXML
	private TextField stateField, maxCost, location, addFav;
	
	@FXML
	private TableView<TabulatedCollege> view;
	
	@FXML
	private TableColumn<TabulatedCollege, String> nameCol;
	@FXML
	private TableColumn<TabulatedCollege, String> stateCol;
	
	@FXML
	private TableColumn<TabulatedCollege, Integer> localCostCol;
	@FXML
	private TableColumn<TabulatedCollege, Integer> publicCostCol;
	@FXML
	private TableColumn<TabulatedCollege, Integer> numStudentsCol;
	@FXML
	private TableColumn<TabulatedCollege, Integer> numOwnershipCol;
	@FXML
	private TableColumn<TabulatedCollege, Integer> idCol;
	
	
	public void initialize(URL location, ResourceBundle resource) {
		
		final ObservableList<TabulatedCollege> tableData = FXCollections.observableArrayList(Main.getCollegesDatabase().getTabulated(0, null, 0, 0));
		
		nameCol.setCellValueFactory(new PropertyValueFactory<TabulatedCollege, String>("Name"));
		stateCol.setCellValueFactory(new PropertyValueFactory<TabulatedCollege, String>("State"));
		
		idCol.setCellValueFactory(new PropertyValueFactory<TabulatedCollege, Integer>("Id"));
		localCostCol.setCellValueFactory(new PropertyValueFactory<TabulatedCollege, Integer>("LocalCost"));
		publicCostCol.setCellValueFactory(new PropertyValueFactory<TabulatedCollege, Integer>("PublicCost"));
		numStudentsCol.setCellValueFactory(new PropertyValueFactory<TabulatedCollege, Integer>("NumStudents"));
		numOwnershipCol.setCellValueFactory(new PropertyValueFactory<TabulatedCollege, Integer>("NumOwnership"));
		
		view.setItems(tableData);
	}
	
	
	public CollegeSearchSceneController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void back() {
		Scene scene = Utilities.getInstance().getFXMLScene("LoginScene.fxml");
		Utilities.getInstance().switchScene(backButton.getScene(), scene);
	}
	
	@FXML
	public void search() {
		int ownership = 0;
		if (p.isSelected())
			ownership = 1;
		else if (pnp.isSelected())
			ownership = 2;
		else if (pfp.isSelected())
			ownership = 3;
		
		int maxcost = 0;
		
		try {
			maxcost = Integer.parseInt(maxCost.getText());
		} catch (Exception e) {
			
		}
		
		List<TabulatedCollege> data = Main.getCollegesDatabase().getTabulated(ownership, (stateField.getText() == "" ? null : stateField.getText()), maxcost, 0);
		view.getItems().clear();
		view.setItems(FXCollections.observableArrayList(data));
	}
	
	@FXML
	public void addToList() {
		if (addFav.getText() != null || addFav.getText() != "") {
			Main.currentUser.addChoice(addFav.getText());
		}
	}
	
	@FXML
	public void choiceP() {
		pnp.setSelected(false);
		pfp.setSelected(false);
	}
	
	@FXML
	public void choicePNP() {
		p.setSelected(false);
		pfp.setSelected(false);
	}
	
	@FXML
	public void choicePFP() {
		pnp.setSelected(false);
		p.setSelected(false);
	}
	
	

}
