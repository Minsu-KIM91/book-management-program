package libraryProject;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.norazo.dao.borrowDAO;
import com.norazo.dto.borrowDTO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookOut extends JPanel {
   private JTextField textField;
   private JTextField textField_1;
   public JTextField textField_2;
   private JTextField textField_3;
   private JTextField textField_4;
   private JTable table;
    public DefaultTableModel model; 
	private JButton name_search;
	private JButton book_search;
	private String url="jdbc:oracle:thin:@localhost:1521:orcl";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

  
	
   public BookOut() {
      setLayout(null);
      
      JPanel panel = new JPanel();
      panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
      panel.setBounds(0, 0, 783, 34);
      add(panel);
      panel.setLayout(null);
      
      JLabel label = new JLabel("도서반납");
//      label.setFont(new Font("", Font.BOLD, 20));
      label.setBounds(344, 10, 138, 21);
      panel.add(label);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
      panel_1.setBounds(0, 33, 783, 167);
      add(panel_1);
      panel_1.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("이름");
//      lblNewLabel.setFont(new Font("", Font.PLAIN, 14));
      lblNewLabel.setBounds(87, 25, 57, 15);
      panel_1.add(lblNewLabel);
      
      textField = new JTextField(); //이름 입력 텍스트 필드
      textField.setBounds(139, 25, 137, 21);
      panel_1.add(textField);
      textField.setColumns(10);
      
      JLabel lblNewLabel_1 = new JLabel("도서명");
//      lblNewLabel_1.setFont(new Font("", Font.PLAIN, 14));
      lblNewLabel_1.setBounds(87, 63, 57, 15);
      panel_1.add(lblNewLabel_1);
      
      textField_1 = new JTextField(); //도서명 입력 텍스트필드
      textField_1.setBounds(139, 63, 137, 21);
      panel_1.add(textField_1);
      textField_1.setColumns(10);
      
      JLabel lblNewLabel_2 = new JLabel("아이디");
//      lblNewLabel_2.setFont(new Font("", Font.PLAIN, 14));
      lblNewLabel_2.setBounds(87, 99, 57, 15);
      panel_1.add(lblNewLabel_2);
      
      textField_2 = new JTextField();  //아이디 입력    
      textField_2.setEditable(false); //아이디로 검색할 기능 안만들 거. 편집금지
      textField_2.setBounds(139, 97, 137, 21);
      panel_1.add(textField_2);
      textField_2.setColumns(10);
      
      JButton name_search = new JButton("검색");
      name_search.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      	}
      });
//      name_search.setFont(new Font("", Font.BOLD, 12));
      name_search.setBounds(286, 22, 68, 23);
      name_search.addActionListener(new EventHandler(this));
	  name_search.setActionCommand("name_search");
      panel_1.add(name_search);
      
      JButton book_search = new JButton("검색");
//      book_search.setFont(new Font("", Font.BOLD, 12));
      book_search.setBounds(286, 60, 68, 23);
      book_search.addActionListener(new EventHandler(this));
      book_search.setActionCommand("book_search");
      panel_1.add(book_search);
      
      JLabel lblNewLabel_3 = new JLabel("도서번호");
//      lblNewLabel_3.setFont(new Font("", Font.PLAIN, 14));
      lblNewLabel_3.setBounds(443, 26, 68, 15);
      panel_1.add(lblNewLabel_3);
      
      textField_3 = new JTextField();//도서번호 검색 텍스트필드
      textField_3.setEditable(false); //도서번호로 검색할 일은 없으니 편집금지!
      textField_3.setBounds(523, 23, 137, 21);
      panel_1.add(textField_3);
      textField_3.setColumns(10);
      
      JLabel lblNewLabel_4 = new JLabel("반납일");
//      lblNewLabel_4.setFont(new Font("", Font.PLAIN, 14));
      lblNewLabel_4.setBounds(443, 64, 68, 15);
      panel_1.add(lblNewLabel_4);
      
      textField_4 = new JTextField();//반납일 입력 테이블.
      textField_4.setEditable(false);//필요없어서 필드칸 죽임.
      textField_4.setBounds(523, 61, 137, 21);
      panel_1.add(textField_4);
      textField_4.setColumns(10);
      
      JButton out = new JButton("반납");
      out.addActionListener(new EventHandler(this));
      out.setActionCommand("out");
      out.setBounds(274, 127, 97, 23);
      panel_1.add(out);
      
      JButton btnNewButton_3 = new JButton("새로고침");
      btnNewButton_3.addActionListener(new EventHandler(this));
      btnNewButton_3.setActionCommand("refresh");
      btnNewButton_3.setBounds(414, 127, 97, 23);
      panel_1.add(btnNewButton_3);
      
      //반납 카테고리 검색섹션들 아래 레이아웃.
      JPanel panel_2 = new JPanel();
      panel_2.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
      panel_2.setBounds(0, 198, 783, 41);
      add(panel_2);
      panel_2.setLayout(null);
      
      JLabel label_1 = new JLabel("대여목록");
//      label_1.setFont(new Font("", Font.BOLD, 20));
      label_1.setBounds(344, 10, 138, 21);
      panel_2.add(label_1);
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(0, 237, 783, 182);
      add(scrollPane);
      model=new DefaultTableModel(new String[] {
    		  "회원 ID", "책 코드", "제목", "저자", "출판사", "대여일", "반납일"
		},0);// 대여목록 아래 스크롤로 표시될 카테고리들
      
      table = new JTable(model); //스크롤로 열들이 움직일 수 있게 해주고.
      table.addMouseListener(new JTableMouseListener());
      select(); //스크롤 패널에 id,코드, 제목, 저자, 출판사, 대여일, 반납일을 입력위해 DB와 연동한 테이블.
	  scrollPane.setViewportView(table); //스크롤패널에 테이블을 가져오겠다.
	  
      
   }
   
   class EventHandler implements ActionListener{
   private BookOut book;
   public EventHandler(BookOut book)
  {
	    this.book=book;
  }
	public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("name_search")){	
				//이름 검색하면 그 사람이 빌려간 도서가 나오도록 만들어야 하는데, 
				//지금은 클릭해서 회원정보 띄우고 아이디 가져옴.
//				SearchMember name=new SearchMember(book);
//				name.select(textField.getText().toString());
//				name.setVisible(true);
				addTable(textField.getText().toString());
			}
			else if(e.getActionCommand().equals("book_search")){
				
				model.setRowCount(0); 
				select(textField_1.getText()); //빌려간 도서명 가져오고
			}
			else if(e.getActionCommand().equals("out")){//아이디 기준으로 반납되게 만들어놓음.
				borrowDTO dto=new borrowDTO();
				dto.setId(textField_2.getText()); //아이디 가져와라
				borrowDAO dao=new borrowDAO();		
				int data=dao.deleteBorrow(dto); 
				System.out.println("반납:"+data); 
				select();
			}
			else if(e.getActionCommand().equals("refresh")){
					//스크롤 패널 위에 select()실행.
				select();
					
			}
		}
	   
   }
   
   private class JTableMouseListener implements MouseListener{

		@Override //반납을 위한 정보입력. 클릭으로 텍스트 필드에 아이디,반납할 책코드 입력.
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JTable jtable = (JTable)e.getSource();
           int row = jtable.getSelectedRow(); //테이블에 선택된 행을 가져와러 row에 대입
           String str=model.getValueAt(row, 1).toString(); //선택된 행의 1번 값을 문자열로 반환
           textField_3.setText(str); //선택반환된 문자를 필드3에 넣어라
           String str1 = model.getValueAt(row, 0).toString();
           textField_2.setText(str1);
           
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
   
       public void setData(String str){
    	   textField_2.setText(str);	  
	   }
       
       
 //기존 book테이블의 코드와 빌려간 책 코드를 비교해서 값들을 반환.(대여자/대여책 관련)     
       private void select(){                 
           
   		StringBuffer sb = new StringBuffer(); 
           sb.append("select               ");
           sb.append("       borrowlist.cust_id      ");
           sb.append("      ,book.BOOK_CODE      ");
           sb.append("      ,book.BOOK_NAME      ");
           sb.append("      ,book.BOOK_AUTHOR    ");
           sb.append("      ,book.BOOK_COMPANY   ");
           sb.append("      ,borrowlist.BORROW_DATE    ");
           sb.append("      ,borrowlist.RETURN_DATE    ");
           sb.append("from book,borrowlist  ");
           sb.append("where                       ");
           sb.append("       book.BOOK_CODE=borrowlist.BOOK_CODE");
         
           try{
               Class.forName("oracle.jdbc.driver.OracleDriver");
               conn = DriverManager.getConnection(url, "scott", "tiger");
               pstmt = conn.prepareStatement(sb.toString());
               rs = pstmt.executeQuery();
               
               model.setRowCount(0);// 선초기화 시켜서 아래 데이터 올리는 걸로...
               
               while(rs.next()){
                   model.addRow(new Object[]{rs.getString("cust_id"),rs.getString("BOOK_CODE"),rs.getString("BOOK_NAME"),
                                                           rs.getString("BOOK_AUTHOR"),rs.getString("BOOK_COMPANY"),
                                                           rs.getString("BORROW_DATE"),rs.getString("RETURN_DATE")});
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
       
       
 // 반납 카테고리에서 도서명 검색시 작동하는 메소드(빌려간 회원과 해당책을 테이블에 리턴해줘야함)
       private void select(String BOOK_NAME){                
    	   
    	   //338라인으로 시작 회원인 상태에서 검사하고 진행하도록 구문 수정. void대신 string으로 받으면 return활용해서 이어받을수 있음. 변수.
    	   //if문 사용시 string값으로만 이름인지 책인지 구분이 안되니. 숫자를 입력해서(구분가능한 값으로) if문 진행.
   		StringBuffer sb = new StringBuffer(); 
   		sb.append("select               ");
   		sb.append("       borrowlist.cust_id      ");
        sb.append("      ,book.BOOK_CODE      ");
        sb.append("      ,book.BOOK_NAME      ");
        sb.append("      ,book.BOOK_AUTHOR    ");
        sb.append("      ,book.BOOK_COMPANY   ");
        sb.append("      ,borrowlist.BORROW_DATE    ");
        sb.append("      ,borrowlist.RETURN_DATE    ");
        sb.append("from book,borrowlist  ");
        sb.append("where book.book_code = borrowlist.book_code  ");
        sb.append("and return_date is not null  ");
        sb.append("and BOOK_NAME LIKE '%"+BOOK_NAME+"%' ");  //BOOK_NAME 구문을 userID로 수정해서 테이블값 추가하면 됨.
           try{
               Class.forName("oracle.jdbc.driver.OracleDriver");
               conn = DriverManager.getConnection(url, "scott", "tiger");
               pstmt = conn.prepareStatement(sb.toString());
//               pstmt.setString(1, "%"+BOOK_NAME+"%");
               rs = pstmt.executeQuery();
               
               while(rs.next()){           
                   model.addRow(new Object[]{rs.getString("cust_id"),rs.getString("BOOK_CODE"),rs.getString("BOOK_NAME"),
                                                           rs.getString("BOOK_AUTHOR"),rs.getString("BOOK_COMPANY"),
                                                           rs.getString("BORROW_DATE"),rs.getString("RETURN_DATE")});
               }
           }catch(Exception e){
               System.out.println(e.getMessage());
           }finally{
           	 if(rs!=null) try{rs.close();}catch(Exception e){}
   	         if(pstmt!=null)try{pstmt.close();}catch(Exception e){}
   	         if(conn!=null)try{conn.close();}catch(Exception e){}
           }
       }
       
       //이름입력해서 출력...
       private void addTable(String userName){
    	   
    	   
    	   StringBuffer sb2 = new StringBuffer();
	       
	       sb2.append("select                       ");
	       sb2.append("       cust_id               ");
	       sb2.append("      ,cust_name             ");	      
	       sb2.append("      ,cust_phone            ");
	       sb2.append("from member1                 ");
	       sb2.append("where                        ");
	       sb2.append("        cust_name like ?     ");
	       
	       String userId = "";
	       
	       try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "scott", "tiger");
	           pstmt = conn.prepareStatement(sb2.toString());
	           pstmt.setString(1, "%"+userName+"%");
	           rs = pstmt.executeQuery();
	           
	           while(rs.next()){            
	               userId = rs.getString("cust_id");
	           }
	           
		} catch (ClassNotFoundException e1) {
		} catch (SQLException e) {
		}
           
    	   
           
      		StringBuffer sb = new StringBuffer(); 
      		sb.append("select               ");
      		sb.append("       borrowlist.cust_id      ");
           sb.append("      ,book.BOOK_CODE      ");
           sb.append("      ,book.BOOK_NAME      ");
           sb.append("      ,book.BOOK_AUTHOR    ");
           sb.append("      ,book.BOOK_COMPANY   ");
           sb.append("      ,borrowlist.BORROW_DATE    ");
           sb.append("      ,borrowlist.RETURN_DATE    ");
           sb.append("from book,borrowlist  ");
           sb.append("where book.book_code = borrowlist.book_code  ");
           sb.append("and return_date is not null  ");
           sb.append("and borrowlist.cust_id ='"+userId+"'");   
              try{
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  conn = DriverManager.getConnection(url, "scott", "tiger");
                  pstmt = conn.prepareStatement(sb.toString());
//                  pstmt.setString(1, "%"+BOOK_NAME+"%");
                  rs = pstmt.executeQuery();
                  
                  model.setRowCount(0);
                  
                  while(rs.next()){           
                      model.addRow(new Object[]{rs.getString("cust_id"),rs.getString("BOOK_CODE"),rs.getString("BOOK_NAME"),
                                                              rs.getString("BOOK_AUTHOR"),rs.getString("BOOK_COMPANY"),
                                                              rs.getString("BORROW_DATE"),rs.getString("RETURN_DATE")});
                  }
              }catch(Exception e){
                  System.out.println(e.getMessage());
              }finally{
              	 if(rs!=null) try{rs.close();}catch(Exception e){}
      	         if(pstmt!=null)try{pstmt.close();}catch(Exception e){}
      	         if(conn!=null)try{conn.close();}catch(Exception e){}
              }
          }
     
       
         
}
