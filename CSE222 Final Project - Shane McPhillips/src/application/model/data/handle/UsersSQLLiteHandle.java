package application.model.data.handle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.model.data.StudentUserAccount;

public class UsersSQLLiteHandle extends SQLLiteHandle{
	
	public UsersSQLLiteHandle() {
		super("jdbc:sqlite:data/UserInfoDB.sqlite");
	}
	
	protected void initDatabase() {
		String sql = "CREATE TABLE IF NOT EXISTS Users ("
				+ "username text PRIMARY_KEY NOT NULL,"
				+ "fullname text NOT NULL,"
				+ "password text NOT NULL,"
				+ "zipcode integer,"
				+ "state text NOT NULL);";
		
		Statement statement;
		try {
			statement = this.getConnection().createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertUser(StudentUserAccount userAccount) {
		
		this.connect();
		
		
		String sql = "INSERT INTO Users(username,fullname,password,zipcode,state) VALUES(?,?,?,?,?)";
		
		try {
			PreparedStatement pst = this.getConnection().prepareStatement(sql);
			pst.setString(1, userAccount.getUsername());
			pst.setString(2, userAccount.getFullName());
			pst.setString(3, userAccount.getPassword());
			pst.setInt(4, userAccount.getZipcode());
			pst.setString(5, userAccount.getState());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.disconnect();
	}
	
	public boolean containsUsername(String username) {
		this.connect();
		String sql = "SELECT username FROM Users WHERE username = ?";
		
		try {
			PreparedStatement pst = this.getConnection().prepareStatement(sql);
			pst.setString(1, username);
			
			ResultSet rs = pst.executeQuery();
			
			this.disconnect();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public StudentUserAccount getUser(String username, String password) {
		String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
		
		this.connect();
		
		try {
			PreparedStatement pst = this.getConnection().prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				StudentUserAccount user = new StudentUserAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
				this.disconnect();
				return user;
			} else {
				this.disconnect();
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.disconnect();
		return null;
		
	}

}
