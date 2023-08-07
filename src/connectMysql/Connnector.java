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
    
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	Connection conn = null;
        Statement stmt = null;
    	try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("connect database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" create Statement object...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT ID, name, email FROM user";
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                String id  = rs.getString("ID");
                String name = rs.getString("name");
                String email = rs.getString("email");
    
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", user name: " + name);
                System.out.print(", email: " + email);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }
            catch(SQLException se2){
            }// 什么都不做
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
