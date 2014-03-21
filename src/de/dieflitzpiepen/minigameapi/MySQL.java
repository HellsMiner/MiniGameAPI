/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dieflitzpiepen.minigameapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Tammo
 */
public class MySQL {
    public static Connection connection;

    public static void close() {
    	try {
    		if (connection != null) {
    			connection.close();
    		}
    	} catch (Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    }

    public static boolean connect() {
		boolean b = false;
		

		try {
		    // Connect
		    Class.forName("com.mysql.jdbc.Driver");
		    connection = DriverManager.getConnection("jdbc:mysql://" + MiniGameAPI.MySQL_Host + ":3306/" + MiniGameAPI.MySQL_DB,
		    		MiniGameAPI.MySQL_User, MiniGameAPI.MySQL_Pass);

		    // Create Statement and query
		    Statement stmt = connection.createStatement();
		    b = stmt.execute("CREATE TABLE IF NOT EXISTS rankUser(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), rank VARCHAR(255), nick VARCHAR(255));");
		    stmt.close();
		} catch (Exception ex) {
		    System.err.println(ex.getMessage());
		    b = false;
		}

		return b;
    }

    public static void Update(String qry) {
    	try {
    		Statement stmt = connection.createStatement();
    		stmt.executeUpdate(qry);

    		stmt.close();
    	} catch (Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    }

    public static ResultSet Query(String qry) {
    	ResultSet rs = null;

    	try {
    		Statement stmt = connection.createStatement();
    		rs = stmt.executeQuery(qry);

    	} catch (Exception ex) {
    		System.out.println(ex.getMessage());
    	}

    	return rs;
    }
}
