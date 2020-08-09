package libraryProject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//BookRental에서 '이름'을 검색할 때 나오는 창
public class SearchMember extends JDialog {//사용자 정의 대화 상자 : JOptionPane에서 제공하는 간단한 대화 상자 외에 제작자가 만들고 싶은 대화 상자가 있다면 JDialog 클래스를 상속(extends)함으로서 해결할 수 있다.
	
	 private String driver = "oracle.jdbc.driver.OracleDriver";        
	 private String url = "jdbc:oracle:thin:@localhost:1521:orcl";       
	 private static String colNames[] = {"아이디","이름","전화번호","����"}; 
	 public static DefaultTableModel model;            
	 private Connection conn = null;
	 //private java.sql.Connection conn = null;
	 private PreparedStatement pstmt = null;
	 private ResultSet rs = null;   
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	public String result;
	private BookRental book;
	private BookOut book2;


	    //BookRental에 사용할 멤버검색용 클래스
	public SearchMember(BookRental book) {
		
		this.book=book;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		//여백을 줘서 레이아웃 지정하고. 기본컨테이너를 바꿈.
		
		JLabel lblNewLabel = new JLabel("회원정보");
//		lblNewLabel.setFont(new Font("", Font.BOLD, 20));
		lblNewLabel.setBounds(170, 10, 84, 24);
		contentPanel.add(lblNewLabel);
		setContentPane(contentPanel);
		
		//바꾼 컨테이너 패널에 스크롤패널을 지정해 사용.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 44, 410, 208);
		contentPanel.add(scrollPane);
		
		//컬럼 표시
		model = new DefaultTableModel(colNames,0);
	    table = new JTable(model);
		table.setRowSelectionAllowed(false); //테이블의 행은 선택할 수 없도록.
		table.setColumnSelectionAllowed(true);// 테이블의 열은 선택할 수 있게.
		table.setCellSelectionEnabled(true); 
		//이 테이블에서 열 선택과 행 선택이 동시에 존재할 수 있는지 여부를 설정합니다. 
		//설정되면 테이블은 행 및 열 선택 모델의 교차점을 선택된 셀로 처리합니다.
		table.addMouseListener(new JTableMouseListener());
		scrollPane.setViewportView(table); //스크롤 패널에 테이블이 들어갈 수 있도록.
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

		
	}
	public SearchMember(BookOut book) {
		this.book2=book;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원정보");
//		lblNewLabel.setFont(new Font("", Font.BOLD, 20));
		lblNewLabel.setBounds(170, 10, 84, 24);
		contentPanel.add(lblNewLabel);
		setContentPane(contentPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 44, 410, 208);
		contentPanel.add(scrollPane);
		
		model = new DefaultTableModel(colNames,0);
	    table = new JTable(model);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.addMouseListener(new JTableMouseListener1());
		scrollPane.setViewportView(table);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

		
	} 
	
	// 입력된 이름과 일치한 정보 출력.
	public void select(String Member_Name){ 
		
	       
	       StringBuffer sb = new StringBuffer();
	       
	       sb.append("select                       ");
	       sb.append("       cust_id               ");
	       sb.append("      ,cust_name             ");	      
	       sb.append("      ,cust_phone            ");
	       sb.append("from member1                 ");
	       sb.append("where                        ");
	       sb.append("        cust_name like ?     ");
	     
	       try{
	           Class.forName(driver);
	           conn = DriverManager.getConnection(url, "scott", "tiger");
	           pstmt = conn.prepareStatement(sb.toString());
	           pstmt.setString(1, "%"+Member_Name+"%");
	           rs = pstmt.executeQuery(); 
	           
	           while(rs.next()){            
	               model.addRow(new Object[]{ rs.getString("cust_id")
	            		   					 ,rs.getString("cust_name")
	            		   					 ,rs.getString("cust_phone")
	               							});
	           }
	       }
	       catch(ClassNotFoundException e)
	       {
		       System.out.println(e);
		   }
	       catch(SQLException e)
	       {
		       System.out.println(e);
		   }
	       finally{
		         if(rs!=null) try{rs.close();}catch(Exception e){}
		         if(pstmt!=null)try{pstmt.close();}catch(Exception e){}
		         if(conn!=null)try{conn.close();}catch(Exception e){}
		         
		      }
	   }
	private class JTableMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			 JTable jtable = (JTable)e.getSource();
             int row = jtable.getSelectedRow();
             result = model.getValueAt(row, 0).toString();
             System.out.println(result);
             BookRental br=new BookRental();
             book.textField.setText(result);
             dispose();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub		
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub		
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub		
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub	
		} 
	}
	private class JTableMouseListener1 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			 JTable jtable = (JTable)e.getSource();
             int row = jtable.getSelectedRow();
             result=model.getValueAt(row, 0).toString();
             System.out.println(result);
             BookOut bo=new BookOut();
             book2.textField_2.setText(result);
             dispose();
             
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub		
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub		
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub		
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub	
		} 
	}
	
	
	
//	private void addTable(String userId){                
//        
//   		StringBuffer sb = new StringBuffer(); 
//   		sb.append("select               ");
//   		sb.append("       borrowlist.cust_id      ");
//        sb.append("      ,book.BOOK_CODE      ");
//        sb.append("      ,book.BOOK_NAME      ");
//        sb.append("      ,book.BOOK_AUTHOR    ");
//        sb.append("      ,book.BOOK_COMPANY   ");
//        sb.append("      ,borrowlist.BORROW_DATE    ");
//        sb.append("      ,borrowlist.RETURN_DATE    ");
//        sb.append("from book,borrowlist  ");
//        sb.append("where book.book_code = borrowlist.book_code  ");
//        sb.append("and return_date is not null  ");
//        sb.append("and borrowlist.cust_id ='"+userId+"'");   
//           try{
//               Class.forName("oracle.jdbc.driver.OracleDriver");
//               conn = DriverManager.getConnection(url, "scott", "tiger");
//               pstmt = conn.prepareStatement(sb.toString());
////               pstmt.setString(1, "%"+BOOK_NAME+"%");
//               rs = pstmt.executeQuery();
//               
//               model.setRowCount(0);
//               
//               while(rs.next()){           
//                   model.addRow(new Object[]{rs.getString("cust_id"),rs.getString("BOOK_CODE"),rs.getString("BOOK_NAME"),
//                                                           rs.getString("BOOK_AUTHOR"),rs.getString("BOOK_COMPANY"),
//                                                           rs.getString("BORROW_DATE"),rs.getString("RETURN_DATE")});
//               }
//           }catch(Exception e){
//               System.out.println(e.getMessage());
//           }finally{
//           	 if(rs!=null) try{rs.close();}catch(Exception e){}
//   	         if(pstmt!=null)try{pstmt.close();}catch(Exception e){}
//   	         if(conn!=null)try{conn.close();}catch(Exception e){}
//           }
//       }
}
