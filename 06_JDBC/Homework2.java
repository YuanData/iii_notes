package com.hann;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;

public class Homework2 {
	public static void main(String[] args) {
		Connection conn = null;
		try {     
			String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
			conn = DriverManager.getConnection(connUrl, "root", "!qaz2wsX");
			

			String qryStmt = "SELECT * FROM employee";
			PreparedStatement pstmt = conn.prepareStatement(qryStmt);
			ResultSet rs = pstmt.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int nOfColumn = rsmd.getColumnCount();		//計算欄位總數
			//System.out.println(nOfColumn);

//==========懶的手動key資料，用現有的 empno+1000 來新增
			FileWriter fw = new FileWriter("./res/emp.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			while(rs.next()) {
				pw.print((rs.getInt(1)+3000) + ",");
	     		for(int i = 2; i <= nOfColumn; i++)
	     			pw.print(rs.getString(i) + ",");
	     		pw.println("\n");
			}
			
			pw.close();
			bw.close();
			fw.close();
//===============================================
			
			String insStmt = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(insStmt);
			
            FileReader fr = new FileReader("./res/emp.txt");
            BufferedReader br = new BufferedReader(fr) ;
            
            int batch_size = 2;		//Batch size設定為 3
			String str = null;
            
            while((str = br.readLine()) != null){
            	if(str.equals(""))
            		continue;		//排除空字串
            	System.out.println(str);
            	String[] token = null;
            	
            	if(batch_size != 0){
            		token = str.split(",");
            		for(int i = 1; i <= nOfColumn; i++){
            			pstmt.setString(i, token[i-1]);
            		}
        			pstmt.addBatch();
        			System.out.println("HI~");
        			batch_size--;
            	}else{
            		pstmt.executeBatch();
            		System.out.println("===== Execute Batch =====");
            		
            		batch_size = 3;
            		token = str.split(",");
            		for(int i = 1; i <= nOfColumn; i++)
        				pstmt.setString(i, token[i-1]);
        			pstmt.addBatch();
            		batch_size--;
				}       	
            }

            br.close();   
            fr.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
}// end of class BatchUpdateDemo
