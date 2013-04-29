package tests;

import logic.DatabaseInterface;
import logic.StatPolicy;

public class PrintDatabase {

	private static DatabaseInterface db;
	private static StatPolicy stat;
	
	public static void main(String[] args) {
		db = new DatabaseInterface();
		db.connect();
		db.printAll();
		db.disconnect();
	}
}
