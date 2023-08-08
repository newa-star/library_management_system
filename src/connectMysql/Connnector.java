package connectMysql;
import java.sql.*;
public class Connnector {
	 
 
    // MySQL 8.0  JDBC driver and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/library_management_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    
    /*static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/user";*/
    static final String USER = "root";
    static final String PASS = "jzy19990914";
    
    public static ResultSet executeStatement(String sql) {
    	ResultSet rs = null;
    	try {
    		Class.forName(JDBC_DRIVER);
    		Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		Statement stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);
    	}
    	catch(SQLException se){
        // handle JDBC error
        se.printStackTrace();
    }
    	catch(Exception e){
        // handle Class.forName error
        e.printStackTrace();}
    	
    	return rs;
    }
    
    public static PreparedStatement executePreparedStatement(String sql) {
    	PreparedStatement stmt = null;
    	try {
    		Class.forName(JDBC_DRIVER);
    		Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.prepareStatement(sql);
    		
    	}
    	catch(SQLException se){
        // handle JDBC error
        se.printStackTrace();
    }
    	catch(Exception e){
        // handle Class.forName error
        e.printStackTrace();}
    	
    	return stmt;
    }
    
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	Connection conn = null;
        Statement stmt = null;
    	try{
            // register JDBC driver
            Class.forName(JDBC_DRIVER);
        
            // open connection
            System.out.println("connect database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // execute sql query
            System.out.println(" create Statement object...");
            stmt = conn.createStatement();
            String sql = "SELECT ID, name, email FROM user";
            ResultSet rs = stmt.executeQuery(sql);
        
            //display query result
            while(rs.next()){
                
                String id  = rs.getString("ID");
                String name = rs.getString("name");
                String email = rs.getString("email");
    
                // print out data
                System.out.print("ID: " + id);
                System.out.print(", user name: " + name);
                System.out.print(", email: " + email);
                System.out.print("\n");
            }
            // close resource
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // handle JDBC error
            se.printStackTrace();
        }catch(Exception e){
            // handle Class.forName error
            e.printStackTrace();
        }finally{
            // close resource
            try{
                if(stmt!=null) stmt.close();
            }
            catch(SQLException se2){
            }//DO NOTING
            try{
                if(conn!=null) conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
	}

}
