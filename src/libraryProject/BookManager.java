package libraryProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class BookManager extends JPanel {
   private JTextField textField;
   private JTable table;
   private JButton deleteButton = null;

   private JScrollPane scrollPane;  
   private JComboBox choice;
   private JPanel panel_1;
   
   private Toolkit tk=Toolkit.getDefaultToolkit();
   private Dimension screenSize=tk.getScreenSize();
 

    private String driver = "oracle.jdbc.driver.OracleDriver";        

    private String url = "jdbc:oracle:thin:@localhost:1521:orcl";       
    private static String colNames[] = {"코드", "분류", "제목", "저자", "출판사"}; 
    public static DefaultTableModel model = new DefaultTableModel(colNames, 0);
            		//모델 객체 생성
    
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    
    private String[] modifyresult=new String[5]; 
    
    
    
   public BookManager() {
      setLayout(null);
      
      JPanel contentPane = new JPanel();
      contentPane.setBounds(0, 0, 783, 419);
      add(contentPane);
      contentPane.setLayout(null);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
      panel_1.setBounds(0, 34, 800, 43);
      panel_1.setLayout(null);
      contentPane.add(panel_1);
      panel_1.setLayout(null);
      
      choice = new JComboBox(); //분류 3가지해서 검색할 수 있게  
      choice.addItem("출판사");
      choice.addItem("저자");
      choice.addItem("제목");
//    comboBox.setModel(new DefaultComboBoxModel(new String[] {"출판사", "저자", "제목"}));
      choice.setBounds(27, 10, 115, 21);
      panel_1.add(choice);
      
      //팩 제목을 검색할 검색창 텍스트 필드
      textField = new JTextField();
      textField.setColumns(15);
      textField.setBounds(154, 10, 231, 21);
      panel_1.add(textField);
      
      JButton btnNewButton = new JButton("검색");
      btnNewButton.addActionListener(new EventHandler());
      btnNewButton.setActionCommand("btnNewButton");
      btnNewButton.setBounds(470, 9, 64, 23);
      panel_1.add(btnNewButton);
      
      JButton btnNewButton_1 = new JButton("도서등록");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
        	
            BookEnroll be=new BookEnroll();
            be.setVisible(true);
            be.setLocation((screenSize.width-be.getWidth())/2,(screenSize.height-be.getHeight())/2);
         }
      });
      btnNewButton_1.setBounds(546, 9, 116, 23);
      panel_1.add(btnNewButton_1);
      
      //도서관리에 있는 수정/삭제 버튼
      JButton btnNewButton_2 = new JButton("수정/삭제");
      btnNewButton_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
        	            
           System.out.println(modifyresult[0]); //코드
           System.out.println(modifyresult[1]);	//분류
           System.out.println(modifyresult[2]);	//제목
           System.out.println(modifyresult[3]);	//저자
           System.out.println(modifyresult[4]);	//출판사
            
                 
            BookModify d=new BookModify();
            d.setData(modifyresult);
            //버튼 클릭시 윈도우창 뜨게함.
            d.setVisible(true);
            d.setLocation((screenSize.width-d.getWidth())/2,(screenSize.height-d.getHeight())/2);
         }
      });
      btnNewButton_2.setBounds(674, 9, 97, 23);
      panel_1.add(btnNewButton_2);
      
      
      //도서관리 레이아웃 맨 아래에 들어가는 스크롤 패널.
      JScrollPane scrollPane = new JScrollPane(table);
      table = new JTable(model); //테이블에 모델 객채 생성
      table.addMouseListener(new JTableMouseListener());//테이블에 마우스감지기 설정
      
  
      scrollPane.setBounds(0, 74, 788, 338);
      contentPane.add(scrollPane);
      scrollPane.setViewportView(table); //만든 스크롤 패널에 'table'을 보이게 지정할 거임.
      table.getSelectedRow(); //테이블에 선택된 행들을 가져온다 
      //선택된 첫 번째 행의 인덱스를 반환합니다 (행이 선택되지 않은 경우 -1)
      //initialize();  
      select();
      
      //중간 "도서관리" 들어가는 패널.
      JPanel panel = new JPanel();
      panel.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
      panel.setBounds(0, 0, 800, 34);
      contentPane.add(panel);
      panel.setLayout(null);
      
      JLabel label = new JLabel("도서관리");
//      label.setFont(new Font("", Font.BOLD, 20));
      label.setBounds(340, 10, 138, 21);
      panel.add(label);
      
  

   }
   
   class EventHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		 	
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("btnNewButton")){
							
				model.setRowCount(0);//전체 테이블 화면을 지움.	
				select(textField.getText()); //텍스트필드 창에 있는 텍스트 가져와서 선택
			
				
			}
		}
	} 
   //여기 분류 라인을 지우면 위에 select가 오류가 뜬다...돌겠네...
   //	169 ~ 228 line 생략. (분류클릭하면 작품 보여줌.)
   private void select(String text){   
       
	      StringBuffer sb = new StringBuffer(); 
	      if(choice.getSelectedItem()=="출판사")
	     {
	    	  sb.append("select               ");
              sb.append("       BOOK_CODE      ");
              sb.append("      ,BOOK_CATEGORY  ");
              sb.append("      ,BOOK_NAME      ");
              sb.append("      ,BOOK_AUTHOR    ");
              sb.append("      ,BOOK_COMPANY   ");
              sb.append("from book            ");
              sb.append("where                ");
              sb.append("      BOOK_COMPANY like ? ");
	      }
	      else if(choice.getSelectedItem()=="저자")
	      {
	    	  sb.append("select               ");
	           sb.append("       BOOK_CODE      ");
	           sb.append("      ,BOOK_CATEGORY  ");
	           sb.append("      ,BOOK_NAME      ");
	           sb.append("      ,BOOK_AUTHOR    ");
	           sb.append("      ,BOOK_COMPANY   ");
	           sb.append("from book            ");
	           sb.append("where                ");
	           sb.append("      BOOK_AUTHOR like ? ");
	      }
	      else if(choice.getSelectedItem()=="제목")
	      {
	    	  sb.append("select               ");
	          sb.append("       BOOK_CODE      ");
	          sb.append("      ,BOOK_CATEGORY  ");
	          sb.append("      ,BOOK_NAME      ");
	          sb.append("      ,BOOK_AUTHOR    ");
	          sb.append("      ,BOOK_COMPANY   ");
	          sb.append("from book            ");
	          sb.append("where                ");
	          sb.append("      BOOK_NAME like ? ");
	      }
	 
	       try{
	           Class.forName("oracle.jdbc.driver.OracleDriver");
	           conn = DriverManager.getConnection(url, "scott", "tiger");
	           pstmt = conn.prepareStatement(sb.toString());
	           pstmt.setString(1, "%"+text+"%"); //물음표 값에 같은 텍스트 하나라도 있으면.
	           rs = pstmt.executeQuery(); 
	           
	           while(rs.next()){          
	               model.addRow(new Object[]{rs.getString("BOOK_CODE"),rs.getString("BOOK_CATEGORY"),
                           rs.getString("BOOK_NAME"),rs.getString("BOOK_AUTHOR"),
                           rs.getString("BOOK_COMPANY")});
	           }
	       }catch(Exception e){
	           System.out.println(e.getMessage());
	       }finally{
	           if(rs!=null) try{rs.close();}catch(Exception e){}
	            if(pstmt!=null)try{pstmt.close();}catch(Exception e){}
	            if(conn!=null)try{conn.close();}catch(Exception e){}
	       }
   }
	// 169 ~ 230line 생략. (분류하면 해당 작품 보여줌.)

	
   //인터페이스 마우스 리스너
    private class JTableMouseListener implements MouseListener{   
         public void mouseClicked(java.awt.event.MouseEvent e) {   
             
             JTable jtable = (JTable)e.getSource();
             int row = jtable.getSelectedRow(); //선택된 첫번째 행의 인덱스값을 반환
             //행 선택안하면 -1
  
             int c1=jtable.getColumnCount();//열의 수를 반환
             for(int i=0; i<c1; i++)
             {
            	 modifyresult[i]=model.getValueAt(row, i).toString();
             } //선택된 테이블에서 쿼리할 값들을 하나씩 받아온다.
             //눌러진 행에서 i번째 값으로...
            
                 
         }
         public void mouseEntered(java.awt.event.MouseEvent e) {
         }
         public void mouseExited(java.awt.event.MouseEvent e) {    
         }
         public void mousePressed(java.awt.event.MouseEvent e) {
         }
         public void mouseReleased(java.awt.event.MouseEvent e) {
         }
     }
    

    public void select(){                   	 
    	String query = "select BOOK_CODE, BOOK_CATEGORY, BOOK_NAME, BOOK_AUTHOR, BOOK_COMPANY from book";     
         try{
             Class.forName(driver);
             conn = DriverManager.getConnection(url, "scott", "tiger");
             pstmt = conn.prepareStatement(query);
             rs = pstmt.executeQuery();
             
             while(rs.next()){           
                 model.addRow(new Object[]{rs.getString("BOOK_CODE"),rs.getString("BOOK_CATEGORY"),
                                                         rs.getString("BOOK_NAME"),rs.getString("BOOK_AUTHOR"),
                                                         rs.getString("BOOK_COMPANY")});
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