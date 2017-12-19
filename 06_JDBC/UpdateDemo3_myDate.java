package com.iii.nb10506;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDemo3_myDate {
	public static void main(String[] args) {
		String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try (Connection conn = DriverManager.getConnection(connUrl, "root", "iiii");) {

			String inStmt = "INSERT INTO employee VALUES (?,?,curdate(),?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(inStmt);
			pstmt.setInt	(1, 1011);
			pstmt.setString	(2, "David");
			pstmt.setDouble	(3, 99000);
			pstmt.setInt	(4, 100);
			pstmt.setString	(5, "CEO");
			
			int num = pstmt.executeUpdate();
			System.out.println("insert count=" + num);

			pstmt = conn.prepareStatement("SELECT * FROM employee");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				System.out.print("name="+rs.getString("ename")+"\t");
				System.out.println("salary="+rs.getDouble("salary"));
			}
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
