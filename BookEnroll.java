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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.norazo.dao.bookDAO;
import com.norazo.dto.bookDTO;
import libraryProject.BookManager;

public class BookEnroll extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	
	private JLabel genre;
	private JLabel bookname;
	private JLabel author;
	private JLabel publisher;
	private JLabel lblNewLabel_2;
	
	private JTextField textField_2;	
	private JTextField textField_3;
	private JTextField textField_4;
	
	
	private static BookEnroll frame;
	private static int x,y;
	private JComboBox comboBox;

	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Toolkit tk=Toolkit.getDefaultToolkit();
					Dimension screenSize=tk.getScreenSize();
					frame = new BookEnroll();
					frame.setSize(267,345);
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

	//도서관리에서 등록버튼을 클릭하면 나오는 창.
	public BookEnroll() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 267, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		genre = new JLabel("분류"); //스크롤 분류할 것
		genre.setBounds(27, 65, 57, 15);
		contentPane.add(genre);
		
		bookname = new JLabel("도서명");
		bookname.setBounds(27, 104, 57, 15);
		contentPane.add(bookname);
		
		textField_2 = new JTextField(); //도서명 텍스트 필드
		textField_2.setBounds(96, 101, 116, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		author = new JLabel("저자명");
		author.setBounds(27, 143, 57, 15);
		contentPane.add(author);
		
		textField_3 = new JTextField(); //저자명 텍스트 필드
		textField_3.setBounds(96, 140, 116, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		publisher = new JLabel("출판사명");
		publisher.setBounds(27, 181, 57, 15);
		contentPane.add(publisher);
		
		textField_4 = new JTextField(); //출판사명 텍스트 필드
		textField_4.setBounds(96, 181, 116, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("등록");
		btnNewButton.addActionListener(new EventHandler());
		btnNewButton.setBounds(20, 227, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("취소");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// '취소'부분만 끄려고.
				/*
				 하나의 Frame에서 다른 Frame을 열어야 할 경우가 있는데
  * 각각 Frame의 종료 이벤트가 Exit로 설정되어 있을경우 다른 창까지 모두 종료되기 때문에
  * 원하는 하나의 Frame만 종료 시키기 위해서는 dispose() 메소드를 사용한다.
				 */
			}
		});
		button.setBounds(129, 227, 97, 23);
		contentPane.add(button);
		
		
		//도서관리->도서등록 클릭시 나오는 창에 위 패널과 문자.
		panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.setBounds(0, 0, 251, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_2 = new JLabel("도서등록");
//		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel_2.setBounds(96, 10, 66, 25);
		panel.add(lblNewLabel_2);
		
		comboBox = new JComboBox(); //저장시 카테고리 분류
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"선택안함", "철 학", "종 교", "과 학", "예 술", "언 어", "문 학", "역 사"}));
		comboBox.setBounds(96, 62, 116, 21);
		contentPane.add(comboBox);
	}//BookEnroll end
	
	class EventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//도서관리에서 도서등록 클릭 후 텍스트 필드에 값을 입력하지 않을 때 나오는 경고창.
			String bookname=textField_2.getText(); 
			String author=textField_3.getText();
			String publisher=textField_4.getText();
			
			if(bookname.equals("")){ //도서명 입력하지 않고 등록 클릭시 나오는 경고창.
				final Dialog booknameInfo=new Dialog(frame,"Information",true); //단순 메세지창으로 사용하려고 dialog 씀
				booknameInfo.setSize(200,100);
				booknameInfo.setLocation((x-booknameInfo.getWidth())/2+1000,(y-booknameInfo.getHeight())/2+500);
				booknameInfo.setLayout(new FlowLayout());
				JLabel msg=new JLabel("도서명을 입력해주세요.");
				JButton ok=new JButton("확인");
				ok.addActionListener(new ActionListener(){ //경고창 확인 버튼 클릭시
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						booknameInfo.setVisible(false); //창이 닫히고
						booknameInfo.dispose(); //해당 프레임만 종료
					}				
				});
				booknameInfo.add(msg); //경고창에 라벨 추가한 것.
				booknameInfo.add(ok);
				booknameInfo.setVisible(true);
				textField_2.requestFocus(); //도서명부터 입력하도록 키 포커스를 준다. 깜빡임
				textField_2.selectAll();
			}else if(author.equals("")){
				final Dialog authorInfo=new Dialog(frame,"Information",true); //final을 붙여서 다른데 또 쓰이지 않도록.
				authorInfo.setSize(200,100);
				authorInfo.setLocation((x-authorInfo.getWidth())/2+1000,(y-authorInfo.getHeight())/2+500);
				authorInfo.setLayout(new FlowLayout());
				JLabel msg=new JLabel("저자를 입력해주세요.");
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
				JLabel msg=new JLabel("출판사를 입력해주세요.");
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
			}else{ //다 입력되었으면 등록될 수 있게 DTO를 부른다.
				//아래부터 지우고 테이블 연동해서 들어갈 수 있도록 만들어야 함.
				bookDTO dto=new bookDTO();
				
				String category=comboBox.getSelectedItem().toString();
				int index=0;
				int length=2;
				char[] charSet=new char[]{ //여기 문자로 집어넣어줘야 함.
						'0','1','2','3','4','5','6','7','8','9'
				};
				//제목, 저자, 출판사가 같아도 책코드가 달라 분류할 수 있게 랜덤으로 입력.
				StringBuffer sb=new StringBuffer();
				for(int i=0; i<length; i++){
					index=(int)(charSet.length*Math.random());
					sb.append(charSet[index]);
				}
				/* 특정 작가나 출판사를 입력하면 비슷한 코드대 형성하기...실패...
				if(author.equals("김민수"))
					sb.append("20");
				if(author.equals("이덕주"))
					sb.append("21");
				if(author.equals("임경헌"))
					sb.append("22");
				if(author.equals("홍석호"))
					sb.append("23");
				if(publisher.equals("미디"))
					sb.append("10");
				if(publisher.equals("나무"))
					sb.append("11");
				if(publisher.equals("동네"))
					sb.append("12");
				if(publisher.equals("은정사"))
					sb.append("13");
					*/
				
				dto.setBOOK_CODE(Integer.parseInt(sb.toString()));
				dto.setBOOK_CATEGORY(category);
				dto.setBOOK_NAME(bookname);
				dto.setBOOK_AUTHOR(author);
				dto.setBOOK_COMPANY(publisher);
				
				
				
				bookDAO dao=new bookDAO();
				int data=dao.insertBook(dto);
				System.out.println("입력자료:"+data);
				
				final Dialog finishInfo=new Dialog(frame,"Information",true);
				finishInfo.setSize(200,100);
				finishInfo.setLocation((x-finishInfo.getWidth())/2+1000,(y-finishInfo.getHeight())/2+500);
				finishInfo.setLayout(new FlowLayout());
				JLabel msg=new JLabel("책이 등록되었습니다.");
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
		  		//BookManager.model.setRowCount(0);
		  		BookManager bm=new BookManager();
		  			  		
			}
					
		}//eventhandler end
	}
}
