package com.iii.ab10506;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// Insert one employee
public class InsertDemo2_myDate {
	public static void main(String[] args) {
		Connection conn = null;
		try {     
			String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
			conn = DriverManager.getConnection(connUrl, "root", "iiii");
			
			/*解法1*/
			/*
			java.util.Date today = new java.util.Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String insStmt = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insStmt);
			pstmt.setInt(1, 1009);
			pstmt.setString(2, "Jean Tsao");			
			pstmt.setString(3, sdf.format(today));	
			*/	
			/*解法2 不建議*/
			/*
			java.util.Date today=new java.util.Date();
			int year=today.getYear();			
			String insStmt = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insStmt);
			pstmt.setInt(1, 1009);
			pstmt.setString(2, "Jean Tsao");			
			pstmt.setLong(3, year);				
			*/
			/*解法3*/
			/*
			java.util.Calendar today = java.util.Calendar.getInstance();
			int year = today.get(Calendar.YEAR);
			int month = today.get(Calendar.MONTH)+1;
			int day = today.get(Calendar.DATE);
			String insStmt = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insStmt);
			pstmt.setInt(1, 1009);
			pstmt.setString(2, "Jean Tsao");			
			pstmt.setString(3, year+"/"+month+"/"+day);	
			*/
			/*解法4*/
			/*
			java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
			String insStmt = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insStmt);
			pstmt.setInt(1, 1009);
			pstmt.setString(2, "Jean Tsao");			
			pstmt.setDate(3, today);
			*/
			/*解法5 jid 1.8 */
			/*
			java.time.LocalDate today = java.time.LocalDate.now();
			String insStmt = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insStmt);
			pstmt.setInt(1, 1009);
			pstmt.setString(2, "Jean Tsao");			
			pstmt.setString(3, today.toString());	
			*/
			/*---*/
			/*
			pstmt.setDouble(4, 55000);
			pstmt.setInt(5, 100);
			pstmt.setString(6, "senior engineer");
			*/
			/*---*/
			/*解法 直接用SQL*/
			String insStmt = "INSERT INTO employee VALUES (?, ?, curdate(), ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insStmt);
			pstmt.setInt(1, 1009);
			pstmt.setString(2, "Jean Tsao");			
			/*pstmt.setString(3, today.toString())*/;	
			pstmt.setDouble(3, 55000);
			pstmt.setInt(4, 100);
			pstmt.setString(5, "senior engineer");
			
			
			int num = pstmt.executeUpdate();
			System.out.println("insert count = " + num);
			
			pstmt = conn.prepareStatement("SELECT * FROM employee");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.print("name = " + rs.getString("ename") + ", ");
//				System.out.println("salary = " + rs.getDouble("salary"));
				System.out.println(rs.getString("hiredate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) { 
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class InsertDemo
