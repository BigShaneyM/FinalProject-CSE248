package application.model.data;

public class College {
	
	private int id,tuitionCost,tuitionCostLocal,ownership,student_size;
	private String name,website,state;
	private float lat,lon;

	

	public College(int id, int tuitionCost, int tuitionCostLocal, int ownership, int student_size, String name,
			String website, String state, float lat, float lon) {
		this.id = id;
		this.tuitionCost = tuitionCost;
		this.tuitionCostLocal = tuitionCostLocal;
		this.ownership = ownership;
		this.student_size = student_size;
		this.name = name;
		this.website = website;
		this.state = state;
		this.lat = lat;
		this.lon = lon;
	}
	
	

	public int getOwnership() {
		return ownership;
	}
	
	public int getStudentSize() {
		return student_size;
	}

	public int getId() {
		return id;
	}

	public int getOutOfStateTuitionCost() {
		return tuitionCost;
	}

	public int getLocalTuitionCost() {
		return tuitionCostLocal;
	}

	public String getName() {
		return name;
	}

	public String getWebsite() {
		return website;
	}

	public String getState() {
		return state;
	}

	public float getLatitude() {
		return lat;
	}

	public float getLongitude() {
		return lon;
	}
	
	
	
	

}
