package User;

import java.sql.*;

import java.text.SimpleDateFormat;
import java.util.Date;
//import java.lang.reflect.Array;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

public class UserDAO {
	private static String dbURL = "jdbc:mysql://localhost/HEIGHT?serverTimezone=UTC&useSSL=false";
	private static String dbID = "root";
	private static String dbPassword = "845284";
	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rs;
	private static PreparedStatement pstmt;
	private int count = 0;
	// rank.java 잘됨
	// jsp 도 먹음
	// html 로 실행하면 안됨 
	
	
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	// Display DB's all items
	public void Display_DB(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			stmt = conn.createStatement();
			
			String sql = "SELECT id ,age, sex, height, stone FROM height_object";
			
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				int id = rs.getInt(1);
				int sex = rs.getInt(2);
				int age = rs.getInt(3);
				double height = rs.getDouble(4);
				int stone = rs.getInt(5);
				System.out.println(id + " " + sex + " " + age +" " + height + " " + stone);
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러" + e);
		}finally {
			try {
				if(conn != null && !conn.isClosed()) conn.close();
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(rs != null && !rs.isClosed()) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	
	}
	// Display Height items
	public static void Display_Height(UserVO user){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
			
			String sql = "SELECT height FROM height_object WHERE age=" + user.getUserAge() + " && "+  "sex=" + user.getUserSex();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				double height = rs.getDouble(1);
				System.out.println(height);
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러" + e);
		}finally {
			try {
				if(conn != null && !conn.isClosed()) conn.close();
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(rs != null && !rs.isClosed()) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	
	}	
	
	// Return Height items
	public List<Double> Return_Height(UserVO user){
		List<Double> Results = new ArrayList<Double>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			String FindTable = "";
			int ageCheck = user.getUserAge();
			if(ageCheck < 20) FindTable = "teen";
			else if(ageCheck >= 20 && ageCheck < 30) FindTable = "20_30";
			else if(ageCheck >= 30 && ageCheck < 40) FindTable = "30_40";
			else if(ageCheck >= 40 && ageCheck < 50) FindTable = "40_50";
			else if(ageCheck >= 50 && ageCheck < 62) FindTable = "50_60";
			String sql = "SELECT height, stone FROM height_" + FindTable +" WHERE age=" + user.getUserAge() + " && "+  "sex=" + user.getUserSex() + " ORDER BY height DESC";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				double height = rs.getDouble(1);
				Results.add(height);
				count++;
				
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러" + e);
		}finally {
			try {
				if(conn != null && !conn.isClosed()) conn.close();
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(rs != null && !rs.isClosed()) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		count = 0;
		return Results;
		
	
	}
	public List<Double> Return_Height_ASC(UserVO user){
		List<Double> Results = new ArrayList<Double>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
			String FindTable = "";
			int ageCheck = user.getUserAge();
			if(ageCheck < 20) FindTable = "teen";
			else if(ageCheck >= 20 && ageCheck < 30) FindTable = "20_30";
			else if(ageCheck >= 30 && ageCheck < 40) FindTable = "30_40";
			else if(ageCheck >= 40 && ageCheck < 50) FindTable = "40_50";
			else if(ageCheck >= 50 && ageCheck < 62) FindTable = "50_60";
			String sql = "SELECT height, stone FROM height_" + FindTable + " WHERE age=" + user.getUserAge() + " && "+  "sex=" + user.getUserSex() + " ORDER BY height ASC";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				double height = rs.getDouble(1);
				Results.add(height);
				
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러" + e);
		}finally {
			try {
				if(conn != null && !conn.isClosed()) conn.close();
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(rs != null && !rs.isClosed()) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return Results;
		
	
	}
	// Return MAX height
	public double Return_MAX_Height(UserVO user){
		double Results = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
			
			String sql = "SELECT MAX(height) FROM height_object WHERE age=" + user.getUserAge() + " && "+  "sex=" + user.getUserSex();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				double height = rs.getDouble(1);
				Results = height;
				
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러" + e);
		}finally {
			try {
				if(conn != null && !conn.isClosed()) conn.close();
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(rs != null && !rs.isClosed()) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return Results;
		
	
	}
	// Return Min height
	public double Return_MIN_Height(UserVO user){
		double Results = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
			
			String sql = "SELECT MIN(height) FROM height_object WHERE age=" + user.getUserAge() + " && "+  "sex=" + user.getUserSex();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				double height = rs.getDouble(1);
				Results = height;
				
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러" + e);
		}finally {
			try {
				if(conn != null && !conn.isClosed()) conn.close();
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(rs != null && !rs.isClosed()) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return Results;
		
	
	}
	public List<Object> Return_Id(UserVO user){
		List<Object> Results = new ArrayList<Object>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			String FindTable = "";
			int ageCheck = user.getUserAge();
			if(ageCheck < 20) FindTable = "teen";
			else if(ageCheck >= 20 && ageCheck < 30) FindTable = "20_30";
			else if(ageCheck >= 30 && ageCheck < 40) FindTable = "30_40";
			else if(ageCheck >= 40 && ageCheck < 50) FindTable = "40_50";
			else if(ageCheck >= 50 && ageCheck < 62) FindTable = "50_60";
			
			String sql = "SELECT id FROM height_"+ FindTable +" WHERE age=" + user.getUserAge() + " && "+  "sex=" + user.getUserSex() + " ORDER BY height DESC";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				int id = rs.getInt(1);
				Results.add(id);
				
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러" + e);
		}finally {
			try {
				if(conn != null && !conn.isClosed()) conn.close();
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(rs != null && !rs.isClosed()) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return Results;
		
	
	}
	

	

	
	// Insert item into DB
	public void Insert_DB(UserVO user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			String FindTable = "";
			int ageCheck = user.getUserAge();
			if(ageCheck < 20) FindTable = "teen";
			else if(ageCheck >= 20 && ageCheck < 30) FindTable = "20_30";
			else if(ageCheck >= 30 && ageCheck < 40) FindTable = "30_40";
			else if(ageCheck >= 40 && ageCheck < 50) FindTable = "40_50";
			else if(ageCheck >= 50 && ageCheck < 62) FindTable = "50_60";
			String sql = "INSERT INTO height_"+ FindTable +" (age, sex, height, stone) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, user.getUserId());
			pstmt.setInt(1, user.getUserAge());
			pstmt.setInt(2, user.getUserSex());
			pstmt.setDouble(3, user.getUserHeight());
			pstmt.setInt(4, user.getSTONE());
			
			int count = pstmt.executeUpdate();
			if(count == 0) System.out.println("데이터 입력 실패");
			else System.out.println("데이터 입력 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러" + e);
		}finally {
			try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
                if( pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
		}
		
	}
	public void Insert_User(UserVO user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			
			Date time = new Date();
			String time1 = format1.format(time);
			
			
			String sql = "INSERT INTO height_user (age, sex, height, stone, created) VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, user.getUserId());
			pstmt.setInt(1, user.getUserAge());
			pstmt.setInt(2, user.getUserSex());
			pstmt.setDouble(3, user.getUserHeight());
			pstmt.setInt(4, user.getSTONE());
			pstmt.setString(5, time1);
			
			int count = pstmt.executeUpdate();
			if(count == 0) System.out.println("데이터 입력 실패");
			else System.out.println("데이터 입력 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러" + e);
		}finally {
			try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
                if( pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
		}
		
	}
	
	// Delete item from DB 
	public void Delete_DB(int ID, UserVO user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			String FindTable = "";
			int ageCheck = user.getUserAge();
			if(ageCheck < 20) FindTable = "teen";
			else if(ageCheck >= 20 && ageCheck < 30) FindTable = "20_30";
			else if(ageCheck >= 30 && ageCheck < 40) FindTable = "30_40";
			else if(ageCheck >= 40 && ageCheck < 50) FindTable = "40_50";
			else if(ageCheck >= 50 && ageCheck < 62) FindTable = "50_60";
			String sql = "DELETE FROM height_"+ FindTable+" WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ID);
			
			int r = pstmt.executeUpdate();
			System.out.println("변경된 row : " + r);
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러" + e);
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Find new items
	public int Find_STONE(UserVO user){
		int id = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
			String FindTable = "";
			int ageCheck = user.getUserAge();
			if(ageCheck < 20) FindTable = "teen";
			else if(ageCheck >= 20 && ageCheck < 30) FindTable = "20_30";
			else if(ageCheck >= 30 && ageCheck < 40) FindTable = "30_40";
			else if(ageCheck >= 40 && ageCheck < 50) FindTable = "40_50";
			else if(ageCheck >= 50 && ageCheck < 62) FindTable = "50_60";
			String sql = "SELECT id FROM height_" + FindTable +" WHERE stone = 1";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				id = rs.getInt(1);
				//System.out.println(id);
				
				
				
				
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러" + e);
		}finally {
			try {
				if(conn != null && !conn.isClosed()) conn.close();
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(rs != null && !rs.isClosed()) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	
	}


	public static void main(String[] args) {
		// Insert 예제 
		//UserVO use1= new UserVO(0, 1, 16, 160, 1);
		//System.out.println(use1.getUserAge());
	   // Insert_DB(use1);
		
		// Delete 예제
		// User user = new User(9696, 1, 19, 174.2);
		// Delete_DB(user);
		
		// Display_Height 예제
		// User user = new User(9686, 1, 11, 174.2);
		// Display_Height(user);
		
		// Return_Height 예제
		// User user = new User(9691, 1, 11, 174.2);
		
		//Return_Height(user);
		
		//Find_stone 예
		// System.out.println(Find_STONE(user));
		
		// Return_ID 예제
		// System.out.println(Return_Id(user));
		//Display_DB();
	}
}
