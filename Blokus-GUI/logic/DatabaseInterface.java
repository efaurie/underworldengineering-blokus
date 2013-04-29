package logic;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInterface {
	
	private static final String DATABASE_NAME = "PlayerDatabase.db";
	private static final String PLAYER_TABLE = "Players (username string, password string, name string, " +
			"score int, rank int)";
	
	private static final int START_SCORE = 0;
	private static final int START_RANK = 1;
	
	private Connection connection;
	private Statement statement;
	private String dbLocation;
	
	public DatabaseInterface() {
		init();
	}
	
	private void init() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = null;
			URL location = DatabaseInterface.class.getProtectionDomain().getCodeSource().getLocation();
			dbLocation = location.getPath() + DATABASE_NAME;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			createTable();
		} catch (SQLException e) {
			System.out.println("Error Connecting To Database");
		}
	}
	
	private void createTable() {
		try {
			statement.executeUpdate("create table if not exists " + PLAYER_TABLE);
		} catch (SQLException e) {
			System.out.println("Table Creation Failed!");
			disconnect();
		}
	}
	
	public void disconnect() {
		try {
			if(connection != null)
				connection.close();
		} catch(SQLException e) {
			System.out.println("Error Closing Database Connection!");
		}
	}
	
	public boolean createUser(String username, String password, String playerName) {
		try {
			PreparedStatement pStatement1 = connection.prepareStatement("SELECT * FROM Players WHERE username = ?");
			pStatement1.setString(1, username);
			ResultSet rs = pStatement1.executeQuery();
			if(!rs.next()) {
				PreparedStatement pStatement = connection.prepareStatement(
						"insert into Players values(?,?,?,?,?)");
				pStatement.setString(1, username);
				pStatement.setString(2, password);
				pStatement.setString(3, playerName);
				pStatement.setInt(4,  START_SCORE);
				pStatement.setInt(5, START_RANK);
				pStatement.executeUpdate();
				return true;
			} else {
				System.out.println("Username Taken!");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Could not add player!");
			return false;
		}
	}
	
	public String getPlayerName(String username, String password) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"select name from Players where username = ? and password = ?");
			pStatement.setString(1,  username);
			pStatement.setString(2,  password);
			ResultSet rs = pStatement.executeQuery();
			if(rs.next())
				return rs.getString("name");
			else
				return null;
		} catch (SQLException e) {
			System.out.println("Could not get player name!");
			return null;
		}
	}
	
	public int getPlayerScore(String username, String password) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"select score from Players where username = ? and password = ?");
			pStatement.setString(1,  username);
			pStatement.setString(2,  password);
			ResultSet rs = pStatement.executeQuery();
			if(rs.next())
				return rs.getInt("score");
			else
				return -1;
		} catch (SQLException e) {
			System.out.println("Could not get player score!");
			return -1;
		}
	}
	
	public int getPlayerRank(String username, String password) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"select rank from Players where username = ? and password = ?");
			pStatement.setString(1,  username);
			pStatement.setString(2,  password);
			ResultSet rs = pStatement.executeQuery();
			if(rs.next())
				return rs.getInt("rank");
			else
				return -1;
		} catch (SQLException e) {
			System.out.println("Could not get player rank!");
			return -1;
		}
	}
	
	public boolean updatePlayerEntry(String username, String password, int newScore, int newRank) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"update Players set score=?, rank=? where username=? and password=?");
			pStatement.setInt(1, newScore);
			pStatement.setInt(2, newRank);
			pStatement.setString(3,  username);
			pStatement.setString(4,  password);
			pStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Could not update player stats!");
			return false;
		}
	}
	
	public boolean loginCorrect(String username, String password) {
		try {
			PreparedStatement pStatement = connection.prepareStatement("select * from Players where username=? and password=?");
			pStatement.setString(1,  username);
			pStatement.setString(2,  password);
			ResultSet rs = pStatement.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			System.out.println("Could not verify login info!");
			return false;
		}
	}
	
	public void printAll() {
		try {
			ResultSet rs = statement.executeQuery("select * from Players");
			while(rs.next()) {
				System.out.println("Username: " + rs.getString("username") + " Password: " + rs.getString("password") + 
						" Player Name: " + rs.getString("name") + " Score: " + rs.getInt("score") + " Rank: " + rs.getInt("rank"));
			}
		} catch(SQLException e) {
			System.out.println("Failed!");
		}
	}

}
