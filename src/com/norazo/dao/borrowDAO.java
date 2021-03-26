package com.norazo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.norazo.dto.borrowDTO;

public class borrowDAO {
	private Connection getConnection(){
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="scott";
		String pwd="tiger";
		Connection conn=null;
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection(url,user,pwd);
	}catch(ClassNotFoundException e){
		System.out.println(e);
	}catch(SQLException e){
		System.out.println(e);
	}
	return conn;
	}
	public int insertBorrow(borrowDTO dto){
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		sb.append(" insert into borrowlist(CUST_ID,BOOK_CODE,BORROW_DATE,RETURN_DATE)");
		sb.append(" values (?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd')+7)");            
		int row=0;
		try{
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getId());
			pstmt.setInt(2, dto.getBOOK_CODE());
			pstmt.setString(3, dto.getBorrow_DATE());
			pstmt.setString(4, dto.getBorrow_DATE()); //반납일은 대여일 +7
			
			row=pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println(e);
		}
		finally{
	    	 if(pstmt!=null) try{pstmt.close();} catch(Exception e){}
	    	 if(conn!=null) try{conn.close();} catch(Exception e){}
	     }
	     return row;
	}
	public int deleteBorrow(borrowDTO dto){
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		sb.append(" delete from borrowlist");
		sb.append(" where                 ");
		sb.append("        cust_id=?      ");
		//sb.append("        and book_code=?    ");
		int row=0;
		try{
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getId());
			//pstmt.setInt(2, dto.getBOOK_CODE());
			row=pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println(e);
		}
		finally{
	    	 if(pstmt!=null) try{pstmt.close();} catch(Exception e){}
	    	 if(conn!=null) try{conn.close();} catch(Exception e){}
	     }
	     return row;
	}

}
