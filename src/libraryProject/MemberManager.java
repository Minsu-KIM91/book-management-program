package libraryProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.norazo.dao.MemberDAO;


public class MemberManager extends JPanel {
   private JTextField textField;
   public JTable table;

   private String driver = "oracle.jdbc.driver.OracleDriver";        
   private String url = "jdbc:oracle:thin:@localhost:1521:orcl";       
   private static String colNames[] = {"아이디","이름","주소","생년월일","나이","전화번호","이메일"}; 
   public static DefaultTableModel model; 
           
   private Connection conn = null;
   private PreparedStatement pstmt = null;
   private ResultSet rs = null;   
   private JScrollPane scrollPane_1;
   private JComboBox comboBox;
   private String[] modifyresult=new String[8];

   
   public MemberManager() {
      setLayout(null);
      
      JPanel panel = new JPanel();
      panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
      panel.setBounds(0, 0, 783, 34);
      add(panel);
      panel.setLayout(null);
      
      JLabel label = new JLabel("회원관리");
//      label.setFont(new Font("", Font.BOLD, 20));
      label.setBounds(344, 10, 88, 19);
      panel.add(label);

      JPanel panel_1 = new JPanel();
      panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
      panel_1.setLayout(null);
      panel_1.setBounds(0, 33, 783, 43);
      add(panel_1);
      
      //검색시 이용할 정보, 콤보박스
      comboBox = new JComboBox();
      comboBox.setModel(new DefaultComboBoxModel(new String[] {"이름", "나이", "주소", "전화번호"}));
      comboBox.setBounds(50, 10, 85, 21);
      panel_1.add(comboBox);
      
      textField = new JTextField();
      textField.setBounds(147, 10, 189, 21);
      panel_1.add(textField);
      textField.setColumns(10);
      
      JButton button = new JButton("검색");
      button.addActionListener(new EventHandler());
      button.setActionCommand("search");
      button.setBounds(348, 9, 130, 23);
      panel_1.add(button);
      
      JButton btnNewButton_1 = new JButton("수정/탈퇴");
      btnNewButton_1.addActionListener(new EventHandler());
      btnNewButton_1.setActionCommand("re");
      btnNewButton_1.setBounds(642, 9, 130, 23);
      panel_1.add(btnNewButton_1);
      
      JButton button_1 = new JButton("회원등록");
      button_1.addActionListener(new EventHandler());
      button_1.setActionCommand("Enroll");
      button_1.setBounds(500, 9, 130, 23);
      panel_1.add(button_1);

      model = new DefaultTableModel(colNames,0);
      table = new JTable(model);
      table.addMouseListener(new JTableMouseListener());
      scrollPane_1 = new JScrollPane(table);
      scrollPane_1.setBounds(0, 74,783,348);
         table.getColumnModel().getColumn(0).setPreferredWidth(63);
         table.getColumnModel().getColumn(1).setPreferredWidth(63);
         table.getColumnModel().getColumn(2).setPreferredWidth(186);
         table.getColumnModel().getColumn(3).setPreferredWidth(59);
         table.getColumnModel().getColumn(4).setPreferredWidth(66);
         table.getColumnModel().getColumn(5).setPreferredWidth(66);
         scrollPane_1.setViewportView(table);
         select();
         add(scrollPane_1);
   }
   
   public void select(){       
       
       StringBuffer sb = new StringBuffer();
      
       sb.append("select                       ");
       sb.append("       cust_id               ");
       sb.append("      ,cust_name              ");
       sb.append("      ,cust_address          ");
       sb.append("      ,cust_birth          ");
       sb.append("      ,cust_age              ");
       sb.append("      ,cust_phone            ");
       sb.append("      ,cust_email          ");
       sb.append("from member1               ");
     
       try{
           Class.forName(driver);
           conn = DriverManager.getConnection(url, "scott", "tiger");
           pstmt = conn.prepareStatement(sb.toString());
           rs = pstmt.executeQuery(); 
           
           while(rs.next()){          
               model.addRow(new Object[]{ rs.getString("cust_id"),rs.getString("cust_name"),rs.getString("cust_address"),
                                                       rs.getString("cust_birth"),rs.getString("cust_age"),rs.getString("cust_phone"),
                                                       rs.getString("cust_email")
                                                       });
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
   
   //회원관리 검색창.
   private void select(String text){  
		  
	   StringBuffer sb = new StringBuffer(); 
	   if(comboBox.getSelectedItem()=="이름")
	   {
		   sb.append("select                       ");
	       sb.append("       cust_id               ");
	       sb.append("      ,cust_name              ");
	       sb.append("      ,cust_address          ");
	       sb.append("      ,cust_birth          ");
	       sb.append("      ,cust_age              ");
	       sb.append("      ,cust_phone            ");
	       sb.append("      ,cust_email          ");
	       sb.append("from member1               ");
	       sb.append("where                ");
	       sb.append("      cust_name like ? ");
	   }
	   else if(comboBox.getSelectedItem()=="나이")
	   {
		   sb.append("select                       ");
	       sb.append("       cust_id               ");
	       sb.append("      ,cust_name              ");
	       sb.append("      ,cust_address          ");
	       sb.append("      ,cust_birth          ");
	       sb.append("      ,cust_age              ");
	       sb.append("      ,cust_phone            ");
	       sb.append("      ,cust_email          ");
	       sb.append("from member1               ");
	       sb.append("where                ");
	       sb.append("      cust_age like ? "); 
	   }
	   else if(comboBox.getSelectedItem()=="주소")
	   {
		   sb.append("select                       ");
	       sb.append("       cust_id               ");
	       sb.append("      ,cust_name              ");
	       sb.append("      ,cust_address          ");
	       sb.append("      ,cust_birth          ");
	       sb.append("      ,cust_age              ");
	       sb.append("      ,cust_phone            ");
	       sb.append("      ,cust_email          ");
	       sb.append("from member1               ");
	       sb.append("where                ");
	       sb.append("      cust_address like ? "); 
	   }
	   else
	   {					//마지막은 전화번호
		   sb.append("select                       ");
	       sb.append("       cust_id               ");
	       sb.append("      ,cust_name              ");
	       sb.append("      ,cust_address          ");
	       sb.append("      ,cust_birth          ");
	       sb.append("      ,cust_age              ");
	       sb.append("      ,cust_phone            ");
	       sb.append("      ,cust_email          ");
	       sb.append("from member1               ");
	       sb.append("where                ");
	       sb.append("      cust_phone like ? ");  
	   }
       try{
           Class.forName("oracle.jdbc.driver.OracleDriver");
           conn = DriverManager.getConnection(url, "scott", "tiger");
           pstmt = conn.prepareStatement(sb.toString());
           pstmt.setString(1, "%"+text+"%");
           rs = pstmt.executeQuery(); 
           
           while(rs.next()){            
               model.addRow(new Object[]{ rs.getString("cust_id"),rs.getString("cust_name"),rs.getString("cust_address"),
                                                       rs.getString("cust_birth"),rs.getString("cust_age"),rs.getString("cust_phone"),
                                                       rs.getString("cust_email")
                                                       });
           }
       }catch(Exception e){
           System.out.println(e.getMessage());
       }finally{
       	 if(rs!=null) try{rs.close();}catch(Exception e){}
	         if(pstmt!=null)try{pstmt.close();}catch(Exception e){}
	         if(conn!=null)try{conn.close();}catch(Exception e){}
       }
       }
   
   // 회원관리 카테고리 버튼 액션들 - 검색(search), 수정/탈퇴(re), 회원등록(enroll)
   class EventHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("search")){		
				model.setRowCount(0); 
				select(textField.getText()); //145라인 select 클래스 실행.		
			}
			else if(e.getActionCommand().equals("re")){// 수정/탈퇴 클릭시 작동
				MemberModify obj=new MemberModify();
				obj.setDate(modifyresult);
				obj.setVisible(true);
			}
			else{//회원관리 -> 회원등록 -> 정보 입력 후 '등록' 클릭시 Enroll클래스 발동.
				MemberEnroll obj=new MemberEnroll();
				obj.setVisible(true);
			}
		}
	}	
   private class JTableMouseListener implements MouseListener{    
       public void mouseClicked(java.awt.event.MouseEvent e) {    
           
           JTable jtable = (JTable)e.getSource();
           int row = jtable.getSelectedRow();                
          
           int c1=jtable.getColumnCount();


           for(int i=0; i<c1-1; i++)
           {
              modifyresult[i]=model.getValueAt(row, i).toString();
              System.out.println(modifyresult[i]);
           }
          
               
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
   
   
}