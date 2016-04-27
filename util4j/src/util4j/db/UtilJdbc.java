package util4j.db;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;

import util4j.config.ConfigManager;

public class UtilJdbc {
	private static ConfigManager ConfigDB=new ConfigManager();//using non static method
	static {  
        try {
        	Class.forName(ConfigDB.getDriver());  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static Connection getConnection(){  
        Connection conn = null;  
        String url = ConfigDB.getUrl();
        String user = ConfigDB.getUser(); 
        String pwd = ConfigDB.getPwd();
        try {  
        	conn = DriverManager.getConnection(url,user,pwd);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return conn;  
    }  
    public static void release(Object o){  
		if (o == null){  
		    return;  
		}  
		if (o instanceof ResultSet){  
		    try {  
		        ((ResultSet)o).close();  
		    } catch (SQLException e) {  
		        e.printStackTrace();  
		    }  
		} else if(o instanceof Statement){  
		    try {  
		        ((Statement)o).close();  
		    } catch (SQLException e) {  
		        e.printStackTrace();  
		    }  
		} else if (o instanceof Connection){  
		    Connection c = (Connection)o;  
		    try {  
		        if (!c.isClosed()){  
		            c.close();  
		        }  
		    } catch (SQLException e) {  
		        e.printStackTrace();  
		    }  
		} 
    }  
      
	public static void release(ResultSet rs, Statement stmt,Connection conn){  
		release(rs);  
		release(stmt);  
		release(conn);  
	}
	
	public static int executeSQL(String sql,String[] getValue){
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			if (getValue!=null) {
				for (int i = 0; i < getValue.length; i++) {
					pstmt.setString(i+1, getValue[i]);
				}
			}
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("executeSQL throw exception");
			e.printStackTrace();
		}finally{
			release(null,pstmt,conn);
		}
		return result;
	}
}
