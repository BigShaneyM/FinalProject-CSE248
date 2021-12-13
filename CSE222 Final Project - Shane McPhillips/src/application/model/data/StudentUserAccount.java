package application.model.data;

import java.util.ArrayList;
import java.util.List;

import application.Main;

public class StudentUserAccount {
	
	private String fullName;
	private String username;
	private String password;
	private int zipcode;
	private String state;
	
	private List<String> ids;
	
	public StudentUserAccount(String fullName, String username, String password, int zipcode, String state) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.zipcode = zipcode;
		this.state = state;
		
		String raw = Main.getUserChoiceDatabase().getChoices(username);
		ids = new ArrayList<String>(10);
		if (raw == null) {
		} else {
			String[] s = raw.split(",");
			for (String s1 : s) {
				ids.add(s1);
			}
		}
		
	}
	
	public List<String> getIDS() {
		return this.ids;
	}
	
	public void addChoice(String id) {
		if (ids.size() == 10 || this.ids.contains(id)) {
			return;
		} else {
			ids.add(id);
			
			String a = "";
			for (int i = 0; i < ids.size(); i++) {
				if (ids.get(i) != null)
					a += ids.get(i);
			}
			
			Main.getUserChoiceDatabase().updateChoices(this.username, a);
			
		}
	}
	
	public void removeChoice(String id) {
		if (ids.contains(id)) {
			ids.remove(id);
			
			String a = "";
			for (int i = 0; i < ids.size(); i++) {
				if (ids.get(i) != null)
					a += ids.get(i);
			}
			
			Main.getUserChoiceDatabase().updateChoices(this.username, a);
		}
	}
	
	public String getFullName() {
		return fullName;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public int getZipcode() {
		return zipcode;
	}
	public String getState() {
		return state;
	}

	
}
