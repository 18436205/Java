package zzchatsserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class ZzchatDbUtil {
	public static final String MYSOLDRIVER="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://127.0.0.1:3306/zzchat?useUnicode=true&characterEncoding=UTF-8";
	public static final String DBUSER="root";
	public static final String DBPASS="";
	
	public static void loadDriver(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public static Connection getConnection(){
		loadDriver();
		Connection conn=null;
		try {
		conn=DriverManager.getConnection(URL,DBUSER,DBPASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		}
	

	
	public static boolean seeKUser(String userName) {
		boolean seeKUserResult=false;
		Connection conn=getConnection();
		String user_Register_Sql="select * from user where username=?";
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		try {
			ptmt = conn.prepareStatement(user_Register_Sql);
			ptmt.setString(1, userName);
			//4、执行查询，返回结果集
			rs=ptmt.executeQuery();
			seeKUserResult=rs.next();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB(conn, ptmt, rs);
		}
		return seeKUserResult;
	}
	
	
	public static void addUser(String userName, String passWord){
Connection conn=getConnection();
		
		String user_add_Sql="insert into user(username,password,registertimestamp) values(?,?,?)";
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		try {
			ptmt = conn.prepareStatement(user_add_Sql);
			ptmt.setString(1, userName);
			ptmt.setString(2, passWord);
			//java.util.Date date=new java.util.Date();
			Date date =new Date();
			java.sql.Timestamp timestamp=new java.sql.Timestamp(date.getTime());
			ptmt.setTimestamp(3,timestamp);
			//4、执行查询，返回结果集
			int count=ptmt.executeUpdate();
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB(conn, ptmt, rs);
		}
		
	}
	
	public static boolean loginValidate(String userName,String passWord) {
		boolean loginSuccess=false;
		Connection conn=getConnection();
		
		String user_Login_Sql="select * from user where username=? and password=?";
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		try {
			ptmt = conn.prepareStatement(user_Login_Sql);
			ptmt.setString(1, userName);
			ptmt.setString(2, passWord);
			//4、执行查询，返回结果集
			rs=ptmt.executeQuery();
			
			//5、根据结果集来判断是否能登录
			loginSuccess=rs.next();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB(conn, ptmt, rs);
		}

		return loginSuccess;
	}
	
	public static String getFriendString (String userName) {
		Connection conn=getConnection();
		String friend_Relation_Sql="select * from relation where majoruser=? and relationtype=1";
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String friendString="";
		try {
			ptmt=conn.prepareStatement(friend_Relation_Sql);
			ptmt.setString(1, userName);
			rs=ptmt.executeQuery();
			while(rs.next()){
				friendString=friendString+rs.getString("slaveuser")+" ";
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			closeDB(conn, ptmt, rs);
		}
		
		System.out.println(userName+" 的relation数据表中的好友："+friendString);
		return friendString;
	}
	
	public static void closeDB(Connection conn,PreparedStatement ptmt,ResultSet rs) {
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ptmt!=null){
			try {
				ptmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	}
	
