package application.model.data.handle;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.model.data.College;

public class CollegeJsonHandle {
	
	
	private int totalSchools = 0;
	private int lastPageNumber = 0;
	
	public CollegeJsonHandle() {
		// TODO Auto-generated constructor stub
	}
	
	
	public LinkedList<College> getCollegeData() {
		String api_key = "PnyxZ0NMdPNeYOZx4e7m8lcHTPrrUp3Ff3PcKilD";
		String url_string = "https://api.data.gov/ed/collegescorecard/v1/schools.json?school.degrees_awarded.predominant=3&_fields=id,school.name,school.school_url,school.state,school.ownership,latest.academics.program.bachelors.computer,location.lat,location.lon,latest.student.size,latest.cost.tuition.in_state,latest.cost.tuition.out_of_state&api_key=" + api_key + "&_per_page=100";
		
		LinkedList<College> collegeData = new LinkedList<College>();
		LinkedList<JsonNode> jsonResultNodes = new LinkedList<JsonNode>();
		
		jsonResultNodes.offer(this.getPageResultArray(url_string, 0));
		System.out.println("Page: " + 0);
		for (int i = 1; i <= this.lastPageNumber; i++) {
			System.out.println("Page: " + i);
			jsonResultNodes.offer(this.getPageResultArray(url_string, i));
		}
		
		
		while (jsonResultNodes.peek() != null) {
			JsonNode resultNode = jsonResultNodes.poll();
			for (int i = 0; i < resultNode.size(); i++) {
				JsonNode collegeNode = resultNode.get(i);
				
				if (collegeNode.get("latest.academics.program.bachelors.computer").asInt() > 0) {
					System.out.println("[=============================]");
					String name = collegeNode.get("school.name").asText();
					String website = collegeNode.get("school.school_url").asText();
					String state = collegeNode.get("school.state").asText();
					int ownership = collegeNode.get("school.ownership").asInt();
					int id = collegeNode.get("id").asInt();
					int student_size = collegeNode.get("latest.student.size").asInt();
					int tuition_cost = collegeNode.get("latest.cost.tuition.out_of_state").asInt();
					int local_tuition_cost = collegeNode.get("latest.cost.tuition.in_state").asInt();
					float latitude = collegeNode.get("location.lat").floatValue();
					float longitude = collegeNode.get("location.lon").floatValue();
					
					System.out.println("name: " + name + "\nWebsite: " + website + "\nState: " + state
							+ "\nOwnership: " + ownership + "\nID: " + id + "\nStudent size: " + student_size
									+ "\nTuition: " + tuition_cost + "\nLocal Tuition: " + local_tuition_cost + 
									"\nLatitude: " + latitude + "\nLongitude: " + longitude + "\n");
					
					College college = new College(id, tuition_cost, local_tuition_cost, ownership, 
							student_size, name, website, state, latitude, longitude);
					collegeData.offer(college);
				}
			}
		}
		
		System.out.println("Size of college data: " + collegeData.size());
		
		
		/////////////////
		return collegeData;
	}
	
	private JsonNode getPageResultArray(String url_string, int pageNumber) {
		
		String instream = "";
		
		try {
			System.out.println("Connecting to College scorecard data...");
			URL url = new URL(url_string + "&_page=" + pageNumber);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			
			connection.setRequestMethod("GET");
			
			connection.connect();
			
			int connection_response = connection.getResponseCode();
			System.out.println("Connection response = " + connection_response);
			
			if (connection_response != 200) {
				throw new RuntimeException("[ERROR]: Response code = " + connection_response);
			} else {
				Scanner scanner = new Scanner(url.openStream());
				
				while (scanner.hasNext()) {
					instream += scanner.next();
				}
				
				
				scanner.close();
				connection.disconnect();
			}
			
			ObjectMapper objmapper = new ObjectMapper();
			
			
			JsonNode node = objmapper.readValue(instream, JsonNode.class);
			
			if (pageNumber == 0) {
				JsonNode meta = node.get("metadata");
				this.totalSchools = Integer.parseInt(meta.get("total").asText());
				
				//Since url views 100 items per page, divide total by 100 to get last page number
				
				this.lastPageNumber = ((int)Math.ceil(this.totalSchools / 100.0) - 1);
				System.out.println("Total pages: " + this.lastPageNumber);
				System.out.println("Total schools in database: " + this.totalSchools);
			}
			
			return node.get("results");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
