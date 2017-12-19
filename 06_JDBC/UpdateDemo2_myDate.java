package com.iii.nb10506;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Update employee data
public class UpdateDemo2_myDate {
	public static void main(String[] args) {
		Connection conn = null;
		try {     
			String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
			conn = DriverManager.getConnection(connUrl, "root", "iiii");
			
			String updStmt = "UPDATE employee SET hiredate=? WHERE empno=?";
			PreparedStatement pstmt = conn.prepareStatement(updStmt);
			
			/*方法1*/
//			java.util.Date today1 = new java.util.Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");			
//			pstmt.setString	(1, sdf.format(today1));
//			pstmt.setInt	(2, 1001);
			
			
			/*方法2*/
//			java.util.Date today2 = new java.util.Date();
//			int year2 = today2.getYear()+1900;
//			int month2 = today2.getMonth()+1;
//			int day2 = today2.getDate();
//			pstmt.setString(1, year2 + "/" + month2 + "/" + day2);
//			pstmt.setInt(2, 1002);
			
//			/*方法3*/
//			java.util.Calendar today3 = java.util.Calendar.getInstance();
//			int year3 = today3.get(Calendar.YEAR);
//			int month3 = today3.get(Calendar.MONTH) + 1;
//			int day3 = today3.get(Calendar.DATE);
//			pstmt.setString(1, year3 + "/" + month3 + "/" + day3);
//			pstmt.setInt(2, 1003);
			
			/*方法4*/
//			java.sql.Date today4 = new java.sql.Date(System.currentTimeMillis());
//			pstmt.setDate(1, today4);
//			pstmt.setInt(2, 1004);
			
			/*方法5 jdk 1.8*/
//			java.time.LocalDate today5 = java.time.LocalDate.now();
//			pstmt.setString(1, today5.toString());
//			pstmt.setInt(2, 1005);
			
			/*解法 直接用SQL 看UpdateDemo3_myDate*/
//			pstmt.setString(1, curdate());
//			pstmt.setInt(2, 1006);
			
			
			int num = pstmt.executeUpdate();
			System.out.println("update count = " + num);

			pstmt = conn.prepareStatement("SELECT*FROM employee");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.print("name = " + rs.getString("ename") + ", \t");
				System.out.println("hiredate = " + rs.getString("hiredate"));
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

	
}// end of class UpdateDemo
