package application.model.data.handle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLLiteHandle {
	
	private Connection connection;
	private String url;
	private boolean connected = false;

	public SQLLiteHandle(String url) {
		this.url = url;
		this.connect();
		this.initDatabase();
		this.disconnect();
	}
	
	public void connect() {
		if (connected) {
			return;
		}
		try {
			this.connection = DriverManager.getConnection(url);
			this.connected = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		if (!this.connected) {
			return;
		}
		try {
			this.connection.close();
			this.connected = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isConnected() {
		return this.connected;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	protected abstract void initDatabase();

}
