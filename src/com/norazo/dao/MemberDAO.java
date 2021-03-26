package com.norazo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import libraryProject.BookRental;

import com.norazo.dto.MemberDTO;

public class MemberDAO {
	
	private Connection getConnection()
	{
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
	      String user="scott";
	      String pwd="tiger";
	      Connection conn=null;
	      
	      try{
	          Class.forName("oracle.jdbc.driver.OracleDriver");
	          conn=DriverManager.getConnection(url, user,pwd);

	       }catch(ClassNotFoundException e){
	          System.out.println(e);
	       }catch(SQLException e){
	          System.out.println(e);
	       }      
	       return conn;
	}
	public int insertMember(MemberDTO dto){
		
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		sb.append(" insert into member1(cust_id, cust_name, cust_birth, cust_address, cust_age, cust_phone, cust_email)   ");
		sb.append(" values(?,?,to_date(?),?,?,?,?)         ");
		int row=0;
		try{
	    	 pstmt=conn.prepareStatement(sb.toString());
	    	 pstmt.setString(1, dto.getId());
	    	 pstmt.setString(2, dto.getName());
	    	 pstmt.setString(3, dto.getBirthday());
	    	 pstmt.setString(4, dto.getAddress());
	    	 pstmt.setString(5, dto.getAge());
	    	 pstmt.setString(6, dto.getPhone());
	    	 pstmt.setString(7, dto.getEmail());
	    	 
	    	 row=pstmt.executeUpdate();
	     }catch(SQLException e)
	     {
	    	 System.out.println(e);
	     }finally{
	    	 if(pstmt!=null) try{pstmt.close();} catch(Exception e){}
	    	 if(conn!=null) try{conn.close();} catch(Exception e){}
	     }
	     return row;
	}
public int updateMember(MemberDTO dto){
		
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		sb.append(" update member1                                                           ");
		sb.append(" set cust_address=?,cust_age=?,cust_birth=?,cust_phone=?,cust_email=?       ");
		sb.append(" where                                                                    ");
		sb.append("        cust_id=?                                                         ");
		int row=0;
		try{
	    	 pstmt=conn.prepareStatement(sb.toString());    	
	    	 pstmt.setString(1, dto.getAddress());
	    	 pstmt.setString(2, dto.getAge());
	    	 pstmt.setString(3, dto.getBirthday());
	    	 pstmt.setString(4, dto.getPhone());
	    	 pstmt.setString(5, dto.getEmail());
	    	 pstmt.setString(6, dto.getId());
	    	 
	    	 row=pstmt.executeUpdate();
	     }catch(SQLException e)
	     {
	    	 System.out.println(e);
	     }finally{
	    	 if(pstmt!=null) try{pstmt.close();} catch(Exception e){}
	    	 if(conn!=null) try{conn.close();} catch(Exception e){}
	     }
	     return row;
	}
public int deleteMember(MemberDTO dto){
	
	Connection conn=getConnection();
	PreparedStatement pstmt=null;
	StringBuffer sb=new StringBuffer();
	sb.append(" delete from member1                                                           "); 
	sb.append(" where                                                                    ");
	sb.append("        cust_id=?                                                         ");
	int row=0;
	try{
    	 pstmt=conn.prepareStatement(sb.toString());    	
    	 pstmt.setString(1, dto.getId());	 
    	 row=pstmt.executeUpdate();
     }catch(SQLException e)
     {
    	 System.out.println(e);
     }finally{
    	 if(pstmt!=null) try{pstmt.close();} catch(Exception e){}
    	 if(conn!=null) try{conn.close();} catch(Exception e){}
     }
     return row;
}
	public String idcheck(String id){
		Connection conn=getConnection();
		String str="";
		StringBuffer sb=new StringBuffer();
		sb.append("	select                      ");
		sb.append("       cust_id              ");
		sb.append("	from member1                ");
		sb.append("	where                       ");
		sb.append("      cust_id=?             ");
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				str=rs.getString("cust_id");
			}
		}
		catch(SQLException e){
	         System.out.println(e);
	      }finally{
	         if(rs!=null) try{rs.close();}catch(Exception e){}
	         if(pstmt!=null)try{pstmt.close();}catch(Exception e){}
	         if(conn!=null)try{conn.close();}catch(Exception e){}
	         
	      }
		return str;
	}
}
