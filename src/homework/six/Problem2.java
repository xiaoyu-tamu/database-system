package homework.six;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.*;
import java.util.*;


public class Problem2 {
    public static void main(String[] args) {
	    //================================================================================
	    // database parameters
	    //================================================================================		
    		final String username = "lxyamerica";
		final String password = "!Tamucsce";
		final String server = "database2.cs.tamu.edu";
		final String url = "jdbc:mysql://" + server;
		final String databaseName = "lxyamerica-ship";
		
	    //================================================================================
	    // read user input and simple validation
	    //================================================================================
				
		// ask for the class of ships 
		ArrayList<ArrayList<String>> class_container = prompt("Please enter a ship class information: ", 6, true);
		// ask for individual ship's detail
		ArrayList<ArrayList<String>> ship_container = prompt("Please enter ship last name and year :", 2, false);
	
	    Connection conn = null;
		try {
		    //================================================================================
		    // Establish connection
		    //================================================================================
			conn = DriverManager.getConnection(url,username,password);  
			conn.setCatalog(databaseName);

		    //================================================================================
		    // Execute query
		    //================================================================================
			Statement stmt = conn.createStatement(); 

			// insert user defined class into classes
			for(ArrayList<String> token : class_container ) {
				token = transform(token);
				String query = buildQuery(token,"classes");
				stmt.executeUpdate(query);
			}
			
			// insert user defined ships into ships
			String classname = class_container.get(0).get(0);
			for(ArrayList<String> token : ship_container ) {
				// add ship class name to parameters
				token.add(1, classname);
				// prepend ship class name
				token.set(0,classname + " " + token.get(0)); 
				token = transform(token);
				String query = buildQuery(token,"ships");
				stmt.executeUpdate(query);
			}
			System.out.println("Insertion successful");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    

    
    public static ArrayList<String> transform(ArrayList<String> src) {
		ArrayList<String> ret = new ArrayList<String>();

    		for(String token : src) {
    			if(isNumeric(token)) {
    				ret.add(token);
    			} else {
    				ret.add("\"" + token + "\"");
    			}
    		}
    		return ret;
    }
    
    public static boolean isNumeric(String s) {      		
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
    }  
    
    private static String buildQuery(ArrayList<String> props, String table) {
        	String query = "INSERT INTO " + table + " "
 			           + "VALUES (" + String.join(", ", props) + ")";
     	return query;
    }
    
    private static ArrayList<ArrayList<String>> prompt(String msg, int len, boolean once) { 
    		ArrayList<ArrayList<String>> container = new ArrayList<ArrayList<String>>();
    		try {
	    		Reader rdr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(rdr);
	
			String input = "";
			ArrayList<String> tokens = null;
			
			
			while(true) {
				System.out.print(msg);
	
		    		input = br.readLine().trim();
		    		if ( input.equals("quit") ) {
		    			break;
		    		}
		    		
		    		String[] t = input.split("\\s*,\\s*");
		    		tokens = new ArrayList<>(Arrays.asList(t));
		    		
		    	    if(tokens.size() != len) {
			    		System.out.println("ERROR : expected " + len + " attributes");
			    		System.exit(1);
			    }
		    	    container.add(tokens);
		    	    if (once) break;
			}

	    		
	    	} catch (Exception e) {
			e.printStackTrace();
		}
	    	return container;
    }
}