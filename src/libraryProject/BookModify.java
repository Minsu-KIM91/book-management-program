package libraryProject;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.norazo.dao.bookDAO;
import com.norazo.dto.bookDTO;

import libraryProject.BookManager;

public class BookModify extends JFrame {

	private JPanel contentPane;
	private JLabel label_1;
	private JLabel bookname;
	private JTextField textField_2;
	private JLabel author;
	private JTextField textField_3;
	private JLabel publisher;
	private JTextField textField_4;
	private JButton ModifyButton;
	private JButton DeleteButton;
	private JButton button_1;
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private static BookEnroll frame;
	private static int x,y;
     private String[] modifyresult;	


     /*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Toolkit tk=Toolkit.getDefaultToolkit();
					Dimension screenSize=tk.getScreenSize();
					BookModify frame = new BookModify();
					frame.setLocation((screenSize.width-frame.getWidth())/2,(screenSize.height-frame.getHeight())/2);
					x=screenSize.width-frame.getWidth()/2;
					y=screenSize.height-frame.getHeight()/2;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
    
   public void setData(String[] modifyresult)
   {
	   //수정창에서 입력한 값 그대로 텍스트를 집어넣어서 수정한다.
	   textField_2.setText(modifyresult[2]);	//제목
	   textField_3.setText(modifyresult[3]);	//저자
	   textField_4.setText(modifyresult[4]);	//출판사
	  
   }
	
	public BookModify() {
	
		//도서관리-> 수정/삭제 누르면 뜨는 창
		//-> 도서등록으로 생성된 테이블목록을 클릭하고 수정/삭제를 누르면 해당값들이 필드에 올려져 있고 삭제하든지 수정하든지 하면 됨.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 273, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label_1 = new JLabel("분류");
		label_1.setBounds(27, 70, 57, 15);
		contentPane.add(label_1);
		
		bookname = new JLabel("도서명");
		bookname.setBounds(27, 107, 57, 15);
		contentPane.add(bookname);
		textField_2 = new JTextField("");//책제목 입력
		textField_2.setBounds(96, 104, 116, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		author = new JLabel("저자명");
		author.setBounds(27, 142, 57, 15);
		contentPane.add(author);
		
		textField_3 = new JTextField(""); //저자 입력
		textField_3.setBounds(96, 139, 116, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		publisher = new JLabel("출판사명");
		publisher.setBounds(27, 178, 57, 15);
		contentPane.add(publisher);
		
		textField_4 = new JTextField(); //출판사 입력
		textField_4.setBounds(96, 178, 116, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		ModifyButton = new JButton("수정");
		//ModifyButton.addActionListener(new EventHandler());//각 필드를 채우지 않으면 경고창 생성
		ModifyButton.setActionCommand("modify");
		ModifyButton.setBounds(17, 217, 67, 23);
		contentPane.add(ModifyButton);
		
		DeleteButton = new JButton("삭제");
		DeleteButton.addActionListener(new EventHandler());//각 필드를 채우지 않으면 경고창 생성
		DeleteButton.setBounds(96, 217, 67, 23);
		contentPane.add(DeleteButton);
		
		button_1 = new JButton("취소");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); //그냥 창만 꺼지게 기능하기.
			}
		});
		button_1.setBounds(175, 217, 67, 23);
		contentPane.add(button_1);
		
		//도서관리->수정/삭제 누르면 뜨는 창 패널이랑 그 문구
		panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.setBounds(0, 0, 257, 48);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_2 = new JLabel("수정/삭제");
		lblNewLabel_2.setBounds(88, 10, 98, 28);
//		lblNewLabel_2.setFont(new Font("", Font.BOLD, 15));
		panel.add(lblNewLabel_2);
		
		// 수정/삭제창 카테고리분류 콤보박스
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"선택안함", "철 학", "종 교", "과 학", "예 술", "언 어", "문 학", "역 사"}));
		comboBox.setBounds(96, 67, 116, 21);
		contentPane.add(comboBox);
	}
	
	
	class EventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			// 수정/삭제 창에서 필드안에 값을 하나도 입력하지 않을 경우 경고창이 뜬다.
			// 테이블을 클릭하고 수정/삭제 누르면 테이블값이 자동으로 입력되어있음.
			String bookname=textField_2.getText();
			String author=textField_3.getText();
			String publisher=textField_4.getText();
			if(bookname.equals("")){
				final Dialog booknameInfo=new Dialog(frame,"Information",true); //다이얼로그창 문자나 이미지만 보여주는.
				booknameInfo.setSize(200,100);
				booknameInfo.setLocation((x-booknameInfo.getWidth())/2+1000,(y-booknameInfo.getHeight())/2+500);
				booknameInfo.setLayout(new FlowLayout());
				JLabel msg=new JLabel("도서명을 입력해주세요");
				JButton ok=new JButton("확인");
				ok.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						booknameInfo.setVisible(false);
						booknameInfo.dispose();
					}				
				});
				booknameInfo.add(msg);
				booknameInfo.add(ok);
				booknameInfo.setVisible(true);
				textField_2.requestFocus();
				textField_2.selectAll();
			}else if(author.equals("")){
				final Dialog authorInfo=new Dialog(frame,"Information",true);
				authorInfo.setSize(200,100);
				authorInfo.setLocation((x-authorInfo.getWidth())/2+1000,(y-authorInfo.getHeight())/2+500);
				authorInfo.setLayout(new FlowLayout());
				JLabel msg=new JLabel("저자를 입력해주세요");
				JButton ok=new JButton("확인");
				ok.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						authorInfo.setVisible(false);
						authorInfo.dispose();
					}				
				});
				authorInfo.add(msg);
				authorInfo.add(ok);
				authorInfo.setVisible(true);
				textField_3.requestFocus();
				textField_3.selectAll();
			}else if(publisher.equals("")){
				final Dialog publisherInfo=new Dialog(frame,"Information",true);
				publisherInfo.setSize(200,100);
				publisherInfo.setLocation((x-publisherInfo.getWidth())/2+1000,(y-publisherInfo.getHeight())/2+500);
				publisherInfo.setLayout(new FlowLayout());
				JLabel msg=new JLabel("출판사를 입력해주세요");
				JButton ok=new JButton("확인");
				ok.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						publisherInfo.setVisible(false);
						publisherInfo.dispose();
					}				
				});
				publisherInfo.add(msg);
				publisherInfo.add(ok);
				publisherInfo.setVisible(true);
				textField_4.requestFocus();
				textField_4.selectAll();
			}else{
				
				bookDTO dto=new bookDTO();
				bookDAO dao=new bookDAO();
				dto.setBOOK_NAME(textField_2.getText());
				int data=dao.deleteBook(dto);
				System.out.println("입력자료:"+data);
				
				
				final Dialog finishInfo=new Dialog(frame,"Information",true);
				finishInfo.setSize(200,100);
				finishInfo.setLocation(850,500);
				finishInfo.setLayout(new FlowLayout());
				JLabel msg=new JLabel("책이 삭제되었습니다.");
				JButton ok=new JButton("확인");
				ok.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						finishInfo.setVisible(false);
						finishInfo.dispose();
					}				
				});
				finishInfo.add(msg);
				finishInfo.add(ok);
				finishInfo.setVisible(true);
		  		dispose();
		  		
		  		//BookManager bm=new BookManager();
			}
		}
	}
}
