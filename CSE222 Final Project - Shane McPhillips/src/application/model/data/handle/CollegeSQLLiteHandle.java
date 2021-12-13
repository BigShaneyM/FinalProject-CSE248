package application.model.data.handle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import application.model.data.College;
import application.model.data.TabulatedCollege;

public class CollegeSQLLiteHandle extends SQLLiteHandle{

	public CollegeSQLLiteHandle() {
		super("jdbc:sqlite:data/CollegeInfoDB.sqlite");
	}
	
	public void insertCollege(College college) {
		String sql = "INSERT INTO Colleges (id,name,website,state,"
				+ "public_tuition,local_tuition,student_size,ownership,"
				+ "longitude,latitude) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pst = this.getConnection().prepareStatement(sql);
			pst.setInt(1, college.getId());
			pst.setString(2, college.getName());
			pst.setString(3, college.getWebsite());
			pst.setString(4, college.getState());
			pst.setInt(5, college.getOutOfStateTuitionCost());
			pst.setInt(6, college.getLocalTuitionCost());
			pst.setInt(7, college.getStudentSize());
			pst.setInt(8, college.getOwnership());
			pst.setFloat(9, college.getLongitude());
			pst.setFloat(10, college.getLatitude());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertLinkedList(LinkedList<College> collegeData) {
		this.connect();
		
		int count = 0;
		
		while (collegeData.peek() != null) {
			College college = collegeData.poll();
			this.insertCollege(college);
			count++;
		}
		
		System.out.println("Colleges entered into sql: " + count);
		this.disconnect();
	}
	
	public List<TabulatedCollege> getTabulated(int ownership, String state, int maxCost, int zipLocation) {
		
		List<TabulatedCollege> tabulated = new ArrayList<TabulatedCollege>();
		
		this.connect();
		
		String param = "";
		if (ownership == 0)
			param += "ownership != ?";
		else
			param += "ownership = ?";
		
		if (state != null) {
			param += " AND state = ?";
		}
		
		if (maxCost > 0 ) {
			param += " AND public_tuition <= ?";
		}
		
		String sql = "SELECT * FROM Colleges WHERE " + param;
		
		try {
			System.out.println(sql);
			PreparedStatement pst = this.getConnection().prepareStatement(sql);
			pst.setInt(1, ownership);
			
			int paramNum = 1;
			if (state != null)
				{
				paramNum++;
				pst.setString(paramNum, state);
				}
			if (maxCost > 0)
				{
				paramNum++;
				pst.setInt(paramNum, maxCost);
				}
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				College college = new College(rs.getInt(1),
						rs.getInt(5), rs.getInt(6), rs.getInt(8), rs.getInt(7), 
						rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(10), rs.getFloat(9));
				tabulated.add(new TabulatedCollege(college));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.disconnect();
		return tabulated;
	}
	
	protected void initDatabase() {
		try { 
			String sql = "CREATE TABLE IF NOT EXISTS Colleges (" + 
			"id integer PRIMARY_KEY," + 
			"name text NOT NULL," + 
			"website text NOT NULL," + 
			"state text NOT NULL," + 
			"public_tuition integer," +
			"local_tuition integer," + 
			"student_size integer," + 
			"ownership integer," + 
			"longitude real," + 
			"latitude real" +
			");";
			
			Statement statement = this.getConnection().createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
