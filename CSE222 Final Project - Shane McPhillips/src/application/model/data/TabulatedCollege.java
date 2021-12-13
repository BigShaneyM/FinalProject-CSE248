package application.model.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabulatedCollege {
	
	private SimpleStringProperty name, state;
	private SimpleIntegerProperty localCost, publicCost, numStudents, numOwnership, id;
	
	
	public TabulatedCollege(College college) {
		this.name = new SimpleStringProperty(college.getName());
		this.state = new SimpleStringProperty(college.getState());
		
		this.localCost = new SimpleIntegerProperty(college.getLocalTuitionCost());
		this.publicCost = new SimpleIntegerProperty(college.getOutOfStateTuitionCost());
		this.numStudents = new SimpleIntegerProperty(college.getStudentSize());
		this.numOwnership = new SimpleIntegerProperty(college.getOwnership());
		this.id = new SimpleIntegerProperty(college.getId());
	}
	

	public int getId() {
		return this.id.get();
	}
	
	public void setId(int id) {
		this.id.set(id);
	}
	
	public String getName() {
		return name.get();
	}



	public void setName(String name) {
		this.name.set(name);
	}



	public String getState() {
		return state.get();
	}



	public void setState(String state) {
		this.state.set(state);
	}



	public int getLocalCost() {
		return localCost.get();
	}



	public void setLocalCost(int localCost) {
		this.localCost.set(localCost);
	}



	public int getPublicCost() {
		return publicCost.get();
	}



	public void setPublicCost(int publicCost) {
		this.publicCost.set(publicCost);
	}



	public int getNumStudents() {
		return numStudents.get();
	}



	public void setNumStudents(int numStudents) {
		this.numStudents.set(numStudents);
	}



	public int getNumOwnership() {
		return numOwnership.get();
	}



	public void setNumOwnership(int numOwnership) {
		this.numOwnership.set(numOwnership);
	}


}
