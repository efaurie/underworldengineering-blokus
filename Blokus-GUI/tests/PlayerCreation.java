package tests;

import logic.DatabaseInterface;
import logic.StatPolicy;

public class PlayerCreation {
	
	private static DatabaseInterface db;
	private static StatPolicy stat;
	
	public static void main(String[] args) {
		db = new DatabaseInterface();
		db.connect();
		stat = new StatPolicy();
		createUserOne();
		createUserTwo();
		createUserThree();
		createUserFour();
		db.disconnect();
	}
	
	private static void createUserOne() {
		String username = "test1@test.com";
		String password = "test1";
		String name = "Test 1";
		int score = 2000;
		int rank = stat.getRank(score);
		db.createUser(username, password, name);
		db.updatePlayerEntry(username, password, score, rank);
		System.out.println("User Created...");
	}
	
	private static void createUserTwo() {
		String username = "test2@test.com";
		String password = "test2";
		String name = "Test 2";
		int score = 850;
		int rank = stat.getRank(score);
		db.createUser(username, password, name);
		db.updatePlayerEntry(username, password, score, rank);
		System.out.println("User Created...");
	}
	
	private static void createUserThree() {
		String username = "test3@test.com";
		String password = "test3";
		String name = "Test 3";
		int score = 1350;
		int rank = stat.getRank(score);
		db.createUser(username, password, name);
		db.updatePlayerEntry(username, password, score, rank);
		System.out.println("User Created...");
	}
	
	private static void createUserFour() {
		String username = "test4@test.com";
		String password = "test4";
		String name = "Test 4";
		int score = -300;
		int rank = stat.getRank(score);
		db.createUser(username, password, name);
		db.updatePlayerEntry(username, password, score, rank);
		System.out.println("User Created...");
	}

}
