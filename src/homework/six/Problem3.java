package homework.six;
import java.sql.*;

public class Problem3 {
	
	public static ResultSet countItems(int price) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// query
		String pc = "select 'pc' as type, count(s.model) as cnt from (select * from pc where price > " + price + " ) s";
		String printer = "select 'printer' as type, count(s.model) as cnt from (select * from printer where price > " + price + " ) s";
		String laptop = "select 'laptop' as type, count(s.model) as cnt from (select * from laptop where price > " + price + " ) s";
		String query = pc + " union " + laptop + " union " + printer;
		
		try {
			//establish connection
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			conn = DriverManager.getConnection("jdbc:default:connection");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch(Exception e) {
			e.printStackTrace();
		}
        return rs;
	}
}


//select s.* from TABLE(countItems(555)) s;

//CREATE FUNCTION countItems(price int)
//RETURNS TABLE
//(type varchar(10), count int)
//LANGUAGE JAVA
//PARAMETER STYLE DERBY_JDBC_RESULT_SET
//READS SQL DATA
//EXTERNAL NAME 'homework.six.Problem3.countItems';

