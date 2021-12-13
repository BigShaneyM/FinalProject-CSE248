package application.model.data.handle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserChoicesSQLLiteHandle extends SQLLiteHandle {

	public UserChoicesSQLLiteHandle() {
		super("jdbc:sqlite:data/UserChoicesDB.sqlite");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDatabase() {
		try { 
			String sql = "CREATE TABLE IF NOT EXISTS Choices (username TEXT PRIMARY_KEY,"
					+ "ids TEXT"
					+ ");";
			
			Statement statement = this.getConnection().createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getChoices(String username) {
		this.connect();
		try { 
			String sql = "SELECT * FROM Choices WHERE username = ?";
			
			PreparedStatement pst = this.getConnection().prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String r = rs.getString(1);
				this.disconnect();
				return r;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.disconnect();
		return null;
	}
	
	public void updateChoices(String username, String ids) {
		
		this.connect();
		try { 
			String sql = "INSERT INTO Choices (username, ids) VALUES(?, ?)";
			
			PreparedStatement pst = this.getConnection().prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, ids);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.disconnect();
	}
	

}
