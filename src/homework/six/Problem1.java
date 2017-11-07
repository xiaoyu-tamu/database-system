package homework.six;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.derby.jdbc.ClientDriver;

public class Problem1 {
    public static void main(String[] args) throws SQLException {
    	
    	String maker = prompt("Please enter a manufacturer name : ");
		
    	Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		ArrayList<String> queries = new ArrayList<String>();
		String[] categories = {"laptop","printer","pc"};	

		for (String category : categories) {
			queries.add(buildQuery(maker,category));
		}
		
		for(String query: queries) {
			System.out.println("Query : " + query + "\n");
			System.out.println("Result:");
			
			ResultSet rs = stmt.executeQuery(query);

			// get attributes name from result set
			ResultSetMetaData meta = rs.getMetaData();
			ArrayList<String> attributes = new ArrayList<String>();				
			int colsize = meta.getColumnCount();
			
			for (int i=1; i<=colsize; i++) {
				String attr = meta.getColumnName(i);
				attributes.add(attr);
			}
			
			// print each attribute and separate by tab
			System.out.println(attributes.stream()
			           .map(Object::toString)
			           .collect(Collectors.joining("\t")));
			
			// print each element
			while (rs.next()) {
				String ret = "";
				for(String attr: attributes) {
					Object o = rs.getObject(attr);
					ret = ret + o + "\t";
				}
				System.out.println(ret);
			}
			
			// free memory
			if(rs != null) rs.close();
			System.out.println("=========================================================\n\n\n");
		}
		conn.close();
		stmt.close();
		System.out.println("Query complete successful");
    }
    private static String prompt(String msg) {
    		//asking uesr input
		Scanner reader = new Scanner(System.in);
	    System.out.print(msg);
	    String maker = reader.nextLine();
	    System.out.println();
	    reader.close();
	    return maker.toUpperCase();
    }
    
    private static Connection getConnection() {
	    // Establish connection

    		Connection conn = null;
	    	try {
	    		Driver d = new ClientDriver();
			String url = "jdbc:derby://localhost:1527/manfDB";
			conn = d.connect(url, null);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return conn;
    }

    private static String buildQuery(String maker, String table) {
        	String query = "select product.maker, product.type, " + table + ".* "
 			           + "from product," + table + " "
 			           + "where maker = '" + maker + "' and product.model = " + table + ".model";
     	return query;
    }
}
