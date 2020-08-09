package com.norazo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.norazo.dto.bookDTO;


//DAO: Data Access Object의 약자로 "데이터베이스에 접근하는 객체" 



public class bookDAO {

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
	
	public int updateBook(bookDTO dto){
		  Connection conn=getConnection();
	      PreparedStatement pstmt=null;
	      StringBuffer sb=new StringBuffer();
	      sb.append(" update book                                                      ");
	      sb.append(" set BOOK_CODE=?,BOOK_CATEGORY=?,BOOK_NAME=?,BOOK_AUTHOR=?,BOOK_COMPANY=?     ");
	      sb.append(" where                                                            ");
	      sb.append("        BOOK_NAME=?                                               ");
	      int row=0;
	      try{
	           pstmt=conn.prepareStatement(sb.toString());
	           pstmt.setInt(1,   dto.getBOOK_CODE());
	           pstmt.setString(2, dto.getBOOK_CATEGORY());
	           pstmt.setString(3, dto.getBOOK_NAME());
	           pstmt.setString(4, dto.getBOOK_AUTHOR());
	           pstmt.setString(5, dto.getBOOK_COMPANY());
	           pstmt.setString(6,dto.getBOOK_NAME());
	           	           
	           row=pstmt.executeUpdate();
	        }catch(SQLException e)
	        {
	           System.out.println(e);
	        }finally{
	           if(pstmt!=null) try{pstmt.close();} catch(Exception e){}
	           if(conn!=null) try{conn.close();} catch(Exception e){}
	        }
	        return row; //updatebook이 int로 반환되기 때문에 int row=0를 선언해서 return반환시 사용. 
	      
	}
	
	
	public int insertBook(bookDTO dto){
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		sb.append("  insert into book(BOOK_CODE,BOOK_CATEGORY,BOOK_NAME,BOOK_AUTHOR,BOOK_COMPANY)   ");
		sb.append("  values (?,?,?,?,?)                        ");
		int row=0;
		try{
			conn.setAutoCommit(false); //자동커밋 해제 -> insert하고 update해야하기 때문에 위 두 작업을 하나의 트랜잭션으로 처리하기 위함. 
			// 테이블이 만들어지고 저장되는 걸 연속적으로 잘 이행시키기 위함. 모든 데이터가 다 입력되었을 때 입력되도록.
			pstmt=conn.prepareStatement(sb.toString()); //반복적인 작업 수행할 때 preparestatement 좋음.
			pstmt.setInt(1, dto.getBOOK_CODE());
			pstmt.setString(2, dto.getBOOK_CATEGORY());
			pstmt.setString(3, dto.getBOOK_NAME());
			pstmt.setString(4, dto.getBOOK_AUTHOR());
			pstmt.setString(5, dto.getBOOK_COMPANY());
			row=pstmt.executeUpdate();
			conn.commit(); //조건이 맞아 떨어지면 커밋시킨다.
		}catch(SQLException e){
			
			System.out.println(e); 
			try{
				conn.rollback();
			}catch(SQLException e2){
				
			}
		}finally{
			if(pstmt!=null) try{pstmt.close();} catch(Exception e){}
			if(conn!=null) try{conn.close();} catch(Exception e){}
		}
		return row;
	}
	
	/*
	public int deleteBook(bookDTO dto){
		   
		   Connection conn=getConnection();
		   PreparedStatement pstmt=null;
		   StringBuffer sb=new StringBuffer();
		   sb.append(" delete from book                          "); 
		   sb.append(" where                                     ");
		   sb.append("        BOOK_NAME=?                        ");
		   int row=0;
		   try{
		        pstmt=conn.prepareStatement(sb.toString());       
		        pstmt.setString(1, dto.getBOOK_NAME());  
		        row=pstmt.executeUpdate();
		     }catch(SQLException e)
		     {
		        System.out.println(e);
		     }finally{
		        if(pstmt!=null) try{pstmt.close();} catch(Exception e){}
		        if(conn!=null) try{conn.close();} catch(Exception e){}
		     }
		     return row;
		}*/
	public int deleteBook(bookDTO dto){
        
        Connection conn=getConnection();
        PreparedStatement pstmt=null;
        StringBuffer sb=new StringBuffer();
        sb.append(" delete from book                          "); 
        sb.append(" where                                     ");
        sb.append("        BOOK_NAME=?                        ");
        int row=0;
        try{
             pstmt=conn.prepareStatement(sb.toString());       
             pstmt.setString(1, dto.getBOOK_NAME());  
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
	
	public ArrayList<bookDTO> selectBook(String name){
		Connection conn=getConnection();
		StringBuffer sb=new StringBuffer();
		sb.append("select                ");
		sb.append("      BOOK_CODE            ");
		sb.append("     , BOOK_CATEGORY            ");
		sb.append("      ,BOOK_NAME          ");
		sb.append("      ,BOOK_AUTHOR          ");
		sb.append("      ,BOOK_COMPANY          ");
		sb.append(" from book       ");
		sb.append(" where                ");
		sb.append("         BOOK_NAME=?       ");
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<bookDTO> al=new ArrayList<bookDTO>();
		try{
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, name); //첫 물음표. 책 이름 입력받아서 전체 데이터
			rs=pstmt.executeQuery();
			
			while(rs.next()){//각각 값을 가져와서 추가
				bookDTO dto=new bookDTO();
				dto.setBOOK_CODE(rs.getInt("BOOK_CODE"));
				dto.setBOOK_CATEGORY(rs.getString("BOOK_CATEGORY"));
				dto.setBOOK_NAME(rs.getString("BOOK_NAME"));
				dto.setBOOK_AUTHOR(rs.getString("BOOK_AUTHOR"));
				dto.setBOOK_COMPANY(rs.getString("BOOK_COMPANY"));
				al.add(dto);
			}		
		}catch(SQLException e){
			System.out.println(e);
		}finally{
			if(rs!=null) try{rs.close();} catch(Exception e){}
			if(pstmt!=null) try{pstmt.close();} catch(Exception e){}
			if(conn!=null) try{conn.close();} catch(Exception e){}
			
		}
		return al;
	}

}