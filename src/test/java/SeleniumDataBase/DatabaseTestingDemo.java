package SeleniumDataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTestingDemo {
    // Connection object
    static Connection con = null;
    // Statement object
    private static Statement stmt;
    // Constant for Database URL
    public static String DB_URL = "jdbc:mysql://localhost:3306/user";
    // Constant for Database Username
    public static String DB_USER = "root";
    // Constant for Database Password
    public static String DB_PASSWORD = "root";

    @Before
    public void setUp() throws Exception {
        try {
            // Make the database connection
            String dbClass = "com.mysql.cj.jdbc.Driver"; // Updated driver class
           // Class.forName(dbClass).newInstance();
            Class.forName(dbClass);

            // Get connection to DB
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Statement object to send the SQL statement to the Database
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        try {
        	String Address= "Sfax";
            String query= "select userId,userName from userinfo where userAddress='"+Address+"'";
            // Get the contents of userinfo table from DB
            ResultSet res = stmt.executeQuery(query);
     
            // Print the result until all the records are printed
            while (res.next()) {
                System.out.println(res.getString(1));
                System.out.print("\t" + res.getString(2));
//                System.out.print("\t" + res.getString(3));
//                System.out.println("\t" + res.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        // Close DB connection
        if (con != null) {
            con.close();
        }
    }
}
