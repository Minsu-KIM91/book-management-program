package libraryProject;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dialog;
import java.awt.Font;

import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.norazo.dao.MemberDAO;
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

public class BookRental extends JPanel {
	public JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
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
	
	
	
	public BookRental() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(0, 0, 783, 34);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("도서대출");
		lblNewLabel.setBounds(344, 10, 88, 19);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(0, 32, 783, 167);
		add(panel_1);
		
		JLabel label = new JLabel("아이디");
		label.setBounds(87, 99, 57, 15);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("도서명");
		label_1.setBounds(87, 63, 57, 15);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("도서번호");
		label_2.setBounds(443, 26, 57, 15);
		panel_1.add(label_2);
		
		textField = new JTextField(); //아이디 텍스트 필드
		textField.setColumns(10);
		textField.setBounds(139, 97, 137, 21);
		panel_1.add(textField);
		
		textField_1 = new JTextField(); //도서번호 텍스트 필드
		textField_1.setColumns(10);
		textField_1.setBounds(523, 23, 137, 21);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();//도서명 텍스트 필드
		textField_2.setColumns(10);
		textField_2.setBounds(139, 61, 137, 21);
		panel_1.add(textField_2);
		
		JButton rental = new JButton("대출");
		rental.setBounds(274, 127, 97, 23);
		rental.addActionListener(new EventHandler(this));
		rental.setActionCommand("rental");
		panel_1.add(rental);
		
		JButton button_1 = new JButton("취소");
		button_1.setBounds(414, 127, 97, 23);
		panel_1.add(button_1);
		
		JLabel label_3 = new JLabel("대출일");
		label_3.setBounds(443, 64, 57, 15);
		panel_1.add(label_3);
		
		textField_3 = new JTextField();//대여일 텍스트 필드
		textField_3.setColumns(10);
		textField_3.setBounds(523, 61, 137, 21);
		panel_1.add(textField_3);
		
		JLabel label_4 = new JLabel("이름");
		label_4.setBounds(87, 25, 57, 15);
		panel_1.add(label_4);
		
		textField_4 = new JTextField();//이름 텍스트 필드
		textField_4.setColumns(10);
		textField_4.setBounds(139, 23, 137, 21);
		panel_1.add(textField_4);
		
		name_search = new JButton("검색"); //빌려갈 회원 이름 검색
		name_search.addActionListener(new EventHandler(this));
		name_search.setActionCommand("name_search"); // name_search이름으로 커맨드 실행.
		name_search.setBounds(286, 22, 68, 23);
		panel_1.add(name_search);
		
		book_search = new JButton("검색"); //도서명으로 대출해갈 책 검색.
		book_search.addActionListener(new EventHandler(this));
		book_search.setActionCommand("book_search");
		book_search.setFont(new Font("", Font.BOLD, 12));
		book_search.setBounds(286, 60, 68, 23);
		panel_1.add(book_search);
		
		
		//도서대여 정보입력창 아래 패널 및 글씨
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(0, 198, 783, 41);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_5 = new JLabel("도서현황");
		label_5.setBounds(344, 10, 88, 19);
		panel_2.add(label_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 238, 783, 198);
		add(scrollPane);
		model=new DefaultTableModel(new String[] {
				"코드", "분류", "도서", "저자", "출판사", "6"
		},0);
		
		table = new JTable(model);
		table.addMouseListener(new JTableMouseListener());
		select();
		scrollPane.setViewportView(table);
		
	}
	
	class EventHandler implements ActionListener{
     private BookRental book;
      public EventHandler(BookRental book)
      {
    	  this.book=book;
      }
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("name_search")){				
				SearchMember name=new SearchMember(book);
				name.select(textField_4.getText().toString());//검색한 회원이름의 아이디 이름 전화번호 가져와라
				name.setVisible(true);
			}
			else if(e.getActionCommand().equals("book_search")){ 
				
				model.setRowCount(0); 
				select(textField_2.getText()); //도서명을 가져가서 select메서드 실행결과 가져와라
			}
			else if(e.getActionCommand().equals("rental")) { //대여 클릭시 
				borrowDTO dto=new borrowDTO();
				dto.setBOOK_CODE(Integer.parseInt(textField_1.getText())); //도서번호 텍스트 필드
				dto.setId(textField.getText()); //아이디 
				//System.out.println(textField.getText()); 아이디가 입력은 되는데....
				dto.setBorrow_DATE(textField_3.getText()); //대여일
				//System.out.println(textField_3.getText()); 입력방법: 20/07/30  
				borrowDAO dao=new borrowDAO();
				int data = dao.insertBorrow(dto);
				System.out.println("대출: "+data);
				
			}
		}
	}
	
	private class JTableMouseListener implements MouseListener{

		@Override //도서를 대출할 시 테이블클릭하면 도서번호를 따오도록. 자동입력해 사용.
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JTable jtable = (JTable)e.getSource();
            int row = jtable.getSelectedRow();
            String str=model.getValueAt(row, 0).toString();
            textField_1.setText(str); //도서번호
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
		  textField.setText(str); //아이디 문자로 입력
	   }
	private void select(){                
        
		StringBuffer sb = new StringBuffer(); 
        sb.append("select               ");
        sb.append("       BOOK_CODE      ");
        sb.append("      ,BOOK_CATEGORY  ");
        sb.append("      ,BOOK_NAME      ");
        sb.append("      ,BOOK_AUTHOR    ");
        sb.append("      ,BOOK_COMPANY   ");
        sb.append("from book            ");
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, "scott", "tiger");
            pstmt = conn.prepareStatement(sb.toString());
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
private void select(String BOOK_NAME){                 
        
		StringBuffer sb = new StringBuffer(); 
        sb.append("select               ");
        sb.append("       BOOK_CODE      ");
        sb.append("      ,BOOK_CATEGORY  ");
        sb.append("      ,BOOK_NAME      ");
        sb.append("      ,BOOK_AUTHOR    ");
        sb.append("      ,BOOK_COMPANY   ");
        sb.append("from book            ");
        sb.append("where                ");
        sb.append("      BOOK_NAME like ? ");
        
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(url, "scott", "tiger");
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, "%"+BOOK_NAME+"%");
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

	
}
