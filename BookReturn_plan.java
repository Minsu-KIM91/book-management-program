package libraryProject;

import java.awt.Color;
import java.awt.Label;
import java.beans.EventHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

	//맨 처음 실행시 보여지는 화면. 금일 반납 예정이랑 반납 예정 도서
public class BookReturn_plan extends JPanel {

	private JTable table;
	private JTable table_1;
	public DefaultTableModel model; 
	public DefaultTableModel model2; 
	private String url="jdbc:oracle:thin:@localhost:1521:orcl";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	
	public BookReturn_plan() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("금일 반납 예정 도서");
		lblNewLabel.setBounds(105, 28, 128, 15);
		add(lblNewLabel);
		
		JLabel label = new JLabel("반납 예정 도서");
		label.setBounds(105, 195, 106, 15);
		add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(105, 53, 640, 125);
		add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(105, 227, 640, 180);
		add(scrollPane_1);
		
		model=new DefaultTableModel(new String[] {
				"아이디", "이름", "제목", "책 코드", "저자", "출판사"
		},0);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		
		model2=new DefaultTableModel(new String[] {
				"아이디", "이름", "책 제목", "책 코드", "저자", "출판사", "대여일", "반납일"
		},0);
		table_1 = new JTable(model2);
		scrollPane_1.setViewportView(table_1);
		select();
		select2();
	}
	private void select(){                
        //아래 sql문이 틀림...오류는 안뜨는데 테이블 출력이 안됨.
   		StringBuffer sb = new StringBuffer(); 
           sb.append("select                     ");
           sb.append("       member1.cust_id 	         ");
           sb.append("      ,member1.cust_name	         ");       
           sb.append("      ,book.BOOK_NAME      ");
           sb.append("      ,book.BOOK_CODE      ");
           sb.append("      ,book.BOOK_AUTHOR    ");
           sb.append("      ,book.BOOK_COMPANY   ");
           sb.append("from book,borrowlist,member1  ");
           sb.append("where                       "); //아래 date로 비교해서 하는게 핵심포인트임.
           sb.append("       to_date(borrowlist.return_DATE, 'yyyy-mm-dd') = to_date(sysdate,'yyyy-mm-dd') ");
           sb.append("       and borrowlist.BOOK_CODE=book.BOOK_CODE");
           sb.append("       and member1.cust_id=borrowlist.cust_id");
         
           try{
               Class.forName("oracle.jdbc.driver.OracleDriver");
               conn = DriverManager.getConnection(url, "scott", "tiger");
               pstmt = conn.prepareStatement(sb.toString());
               rs = pstmt.executeQuery();
               
               while(rs.next()){            
                   model.addRow(new Object[]{rs.getString("cust_id"),rs.getString("cust_name"),
                                                           rs.getString("BOOK_NAME"),rs.getString("BOOK_CODE"),
                                                           rs.getString("BOOK_AUTHOR"),rs.getString("BOOK_COMPANY")});
               }
           }catch(Exception e){
               System.out.println(e.getMessage());
           }finally{
               try {
                   rs.close(); 
                   pstmt.close(); 
                   conn.close();  
               } catch (Exception e2) {}
           }
       }
private void select2(){                
        //대여자 명단은 출력잘됨.
   		StringBuffer sb = new StringBuffer(); 
           sb.append("select                     ");
           sb.append("       member1.cust_id 	         ");
           sb.append("      ,member1.cust_name	         ");       
           sb.append("      ,book.BOOK_NAME      ");
           sb.append("      ,book.BOOK_CODE      ");
           sb.append("      ,book.BOOK_AUTHOR    ");
           sb.append("      ,book.BOOK_COMPANY   ");
           sb.append("      ,BORROW_DATE   ");
           sb.append("      ,RETURN_DATE   ");
           sb.append("      ,book.BOOK_COMPANY   ");
           sb.append("from book,borrowlist,member1  ");
           sb.append("where                       ");
           sb.append("         borrowlist.BOOK_CODE=book.BOOK_CODE");
           sb.append("       and member1.cust_id=borrowlist.cust_id");
         
           try{
               Class.forName("oracle.jdbc.driver.OracleDriver");
               conn = DriverManager.getConnection(url, "scott", "tiger");
               pstmt = conn.prepareStatement(sb.toString());
               rs = pstmt.executeQuery();
               
               while(rs.next()){          
                   model2.addRow(new Object[]{rs.getString("cust_id"),rs.getString("cust_name"),
                                                           rs.getString("BOOK_NAME"),rs.getString("BOOK_CODE"),
                                                           rs.getString("BOOK_AUTHOR"),rs.getString("BOOK_COMPANY"),rs.getString("BORROW_DATE"),rs.getString("RETURN_DATE")});
               }
           }catch(Exception e){
               System.out.println(e.getMessage());
           }finally{
               try {
                   rs.close(); 
                   pstmt.close(); 
                   conn.close();  
               } catch (Exception e2) {}
           }
       }
}
