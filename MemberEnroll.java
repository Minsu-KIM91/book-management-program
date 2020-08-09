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



import com.norazo.dao.MemberDAO;
import com.norazo.dto.MemberDTO;

import libraryProject.MemberManager;


//회원관리창에서 회원등록 누르면 실행되는 창. 회원등록 클릭시 열리는 창 입력부 선언
public class MemberEnroll extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_birth1;
	private JTextField textField_birth2;
	private JTextField textField_birth3;
	private JTextField textField_add;
	private JTextField textField_phone1;
	private JTextField textField_phone2;
	private JTextField textField_mail;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JLabel idcheckLabel;
	private static MemberEnroll frame;
	private static int x,y;
	private JTextField textField_age;

	
	
	public MemberEnroll() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 300, 580, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 10, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.setToolTipText("");
		panel.setBounds(0, 0, 577, 93);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_11 = new JLabel("회원등록");
//		lblNewLabel_11.setFont(new Font("", Font.BOLD, 20));
		lblNewLabel_11.setBounds(237, 27, 110, 44);
		panel.add(lblNewLabel_11);
		
		
		JLabel mID = new JLabel("아이디");
		mID.setBounds(74, 133, 57, 15);
		contentPane.add(mID);
		
		textField_id = new JTextField();//아이디 입력텍스트
		textField_id.setBounds(190, 130, 116, 21);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		JLabel mName = new JLabel("성명");
		mName.setBounds(74, 176, 57, 15);
		contentPane.add(mName);
		
		textField_name = new JTextField();//성명 입력 텍스트
		textField_name.setBounds(190, 173, 116, 21);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		JLabel mBirth = new JLabel("생년월일");
		mBirth.setBounds(74, 222, 57, 15);
		contentPane.add(mBirth);
		
		JLabel lblNewLabel_4 = new JLabel("년");
		lblNewLabel_4.setBounds(259, 222, 25, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("월");
		lblNewLabel_5.setBounds(329, 222, 25, 15);
		contentPane.add(lblNewLabel_5);
		
		textField_birth1 = new JTextField();//년
		textField_birth1.setBounds(190, 219, 57, 21);
		contentPane.add(textField_birth1);
		textField_birth1.setColumns(10);
		
		textField_birth2 = new JTextField();//월
		textField_birth2.setBounds(281, 219, 35, 21);
		contentPane.add(textField_birth2);
		textField_birth2.setColumns(10);
		
		textField_birth3 = new JTextField();//월
		textField_birth3.setColumns(10);
		textField_birth3.setBounds(348, 219, 35, 21);
		contentPane.add(textField_birth3);
		
		JLabel label_1 = new JLabel("일");
		label_1.setBounds(395, 222, 25, 15);
		contentPane.add(label_1);
		
		JLabel mAdd = new JLabel("주소");
		mAdd.setBounds(74, 263, 57, 15);
		contentPane.add(mAdd);
		
		textField_add = new JTextField();//주소입력창
		textField_add.setText("");
		textField_add.setBounds(190, 260, 270, 21);
		contentPane.add(textField_add);
		textField_add.setColumns(10);
		
		JLabel mPhone = new JLabel("번호");
		mPhone.setBounds(74, 307, 57, 15);
		contentPane.add(mPhone);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"010", "011", "016", "017", "018", "019"}));
		comboBox.setBounds(190, 304, 57, 21);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_8 = new JLabel("-");
//		lblNewLabel_8.setFont(new Font("", Font.PLAIN, 18));
		lblNewLabel_8.setBounds(259, 310, 25, 15);
		contentPane.add(lblNewLabel_8);
		
		textField_phone1 = new JTextField();//휴대폰 중간번호
		textField_phone1.setBounds(281, 304, 62, 21);
		contentPane.add(textField_phone1);
		textField_phone1.setColumns(10);
		
		JLabel label_2 = new JLabel("-");
//		label_2.setFont(new Font("", Font.PLAIN, 18));
		label_2.setBounds(348, 307, 25, 15);
		contentPane.add(label_2);
		
		textField_phone2 = new JTextField();//휴대폰 끝번호 4자리
		textField_phone2.setColumns(10);
		textField_phone2.setBounds(372, 304, 62, 21);
		contentPane.add(textField_phone2);
		
		JLabel mMail = new JLabel("이메일");
		mMail.setBounds(74, 354, 57, 15);
		contentPane.add(mMail);
		
		textField_mail = new JTextField();
		textField_mail.setText("");
		textField_mail.setBounds(190, 351, 116, 21);
		contentPane.add(textField_mail);
		textField_mail.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("@");
//		lblNewLabel_10.setFont(new Font("", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(318, 354, 27, 15);
		contentPane.add(lblNewLabel_10);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"-이메일선택-", "naver.com", "hanmail.net", "google.com", "yahoo.com", "msn.com"}));
		comboBox_1.setBounds(344, 351, 102, 21);
		contentPane.add(comboBox_1);
		
		//버튼 미구현....
		JButton bt1 = new JButton("다시입력");
		bt1.setBounds(106, 445, 97, 23);
		contentPane.add(bt1);
		
		JButton bt2 = new JButton("등록");
		//btnNewButton_1.addActionListener(new EventHandler());
		bt2.addActionListener(new EventHandler());
		bt2.setBounds(219, 445, 97, 23);
		contentPane.add(bt2);
		
		JButton bt3 = new JButton("취소");
		bt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
		bt3.setBounds(329, 445, 97, 23);
		contentPane.add(bt3);
		
		//중복검사 버튼 및 기능 구현
		JButton btnNewButton = new JButton("중복검사");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao=new MemberDAO();			
				String str=dao.idcheck(textField_id.getText());
				if(textField_id.getText().equals(str))
				{
					textField_id.requestFocus();
					textField_id.setText("");
					idcheckLabel.setForeground(Color.RED);
					idcheckLabel.setText("아이디가 중복됩니다.");
				}
				else
				{
					idcheckLabel.setForeground(Color.BLUE);
					idcheckLabel.setText("아이디 사용 가능");
				}
			}//그냥 빈칸에 대고 중복검사 눌러도 "아이디가 중복됩니다" 출력됨.
		});
		btnNewButton.setBounds(318, 129, 86, 23);
		contentPane.add(btnNewButton);
		
		JLabel mAge = new JLabel("나이");
		mAge.setBounds(318, 176, 57, 15);
		contentPane.add(mAge);
		
		textField_age = new JTextField();//나이 입력 창
		textField_age.setColumns(10);
		textField_age.setBounds(358, 173, 57, 21);
		contentPane.add(textField_age);
		
		idcheckLabel = new JLabel("");//아이디 중복검사 후 안내문 입력되는 라벨
		idcheckLabel.setBounds(190, 155, 126, 15);
		contentPane.add(idcheckLabel);
	}
	
	class EventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			
			
		StringBuffer sb=new StringBuffer();	
		StringBuffer sb1=new StringBuffer();
		StringBuffer sb2=new StringBuffer();
		
		String mID=textField_id.getText();
		
		
		String mName=textField_name.getText();
		
		
		sb.append(textField_birth1.getText());
		sb.append(textField_birth2.getText());
		sb.append(textField_birth3.getText());
		
		
		sb1.append(comboBox.getSelectedItem().toString());
		sb1.append(textField_phone1.getText());
		sb1.append(textField_phone2.getText());
		
		
		sb2.append(textField_mail.getText()+"@");
		sb2.append(comboBox_1.getSelectedItem().toString());
		
		
		String mAdd=textField_add.getText();
		String mAge=textField_age.getText();
		
		//회원등록창 공백 작성시 경고출력문
		if(mID.equals("")){
			final Dialog mIDInfo=new Dialog(frame,"Information",true);
			mIDInfo.setSize(200,100);
			mIDInfo.setLocation((x-mIDInfo.getWidth())/2+1000,(y-mIDInfo.getHeight())/2+500);
			mIDInfo.setLayout(new FlowLayout());
			JLabel msg=new JLabel("아이디를 입력해주세요.");
			JButton ok=new JButton("확인");
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mIDInfo.setVisible(false);
					mIDInfo.dispose();
				}				
			});
			mIDInfo.add(msg);
			mIDInfo.add(ok);
			mIDInfo.setVisible(true);
			textField_id.requestFocus();
			textField_id.selectAll();
		}else if(mID("")){ //아이디칸이 공백이면.
			final Dialog mIDInfo1=new Dialog(frame,"Information",true);
			mIDInfo1.setSize(200,100);
			mIDInfo1.setLocation((x-mIDInfo1.getWidth())/2+1000,(y-mIDInfo1.getHeight())/2+500);
			mIDInfo1.setLayout(new FlowLayout());
			JLabel msg=new JLabel("아이디는 특수문자를 사용할 수 없습니다.");
			JButton ok=new JButton("확인");
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mIDInfo1.setVisible(false);
					mIDInfo1.dispose();
				}				
			});
			mIDInfo1.add(msg);
			mIDInfo1.add(ok);
			mIDInfo1.setVisible(true);
			textField_id.requestFocus(); //이름 입력안하고 등록으로 넘어가기 때문에 깜빡임을 집어넣는다.
			textField_id.selectAll();
		
		
		}else if(mName.equals("")){
			final Dialog mNameInfo=new Dialog(frame,"Information",true);
			mNameInfo.setSize(200,100);
			mNameInfo.setLocation((x-mNameInfo.getWidth())/2+1000,(y-mNameInfo.getHeight())/2+500);
			mNameInfo.setLayout(new FlowLayout());
			JLabel msg=new JLabel("이름을 입력해주세요.");
			JButton ok=new JButton("확인");
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mNameInfo.setVisible(false);
					mNameInfo.dispose();
				}				
			});
			mNameInfo.add(msg);
			mNameInfo.add(ok);
			mNameInfo.setVisible(true);
			textField_name.requestFocus();
			textField_name.selectAll();
		}else if(mAge.equals("")){
			final Dialog mAgeInfo=new Dialog(frame,"Information",true);
			mAgeInfo.setSize(200,100);
			mAgeInfo.setLocation((x-mAgeInfo.getWidth())/2+1000,(y-mAgeInfo.getHeight())/2+500);
			mAgeInfo.setLayout(new FlowLayout());
			JLabel msg=new JLabel("나이를 입력해주세요.");
			JButton ok=new JButton("확인");
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mAgeInfo.setVisible(false);
					mAgeInfo.dispose();
				}				
			});
			mAgeInfo.add(msg);
			mAgeInfo.add(ok);
			mAgeInfo.setVisible(true);
			textField_age.requestFocus();
			textField_age.selectAll();
		}/*else if(!integerOrNot(mAge)){
			final Dialog mAgeInfo1=new Dialog(frame,"Information",true);
			mAgeInfo1.setSize(200,100);
			mAgeInfo1.setLocation((x-mAgeInfo1.getWidth())/2+70,(y-mAgeInfo1.getHeight())/2+70);
			mAgeInfo1.setLayout(new FlowLayout());
			JLabel msg=new JLabel("���̴� ���ڷ� �Է����ּ���");
			JButton ok=new JButton("Ȯ��");
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mAgeInfo1.setVisible(false);
					mAgeInfo1.dispose();
				}				
			});
			mAgeInfo1.add(msg);
			mAgeInfo1.add(ok);
			mAgeInfo1.setVisible(true);
			textField_age.requestFocus();
			textField_age.selectAll();
		}*/else if(sb.equals("")){
			final Dialog mBirthInfo=new Dialog(frame,"Information",true);
			mBirthInfo.setSize(200,100);
			mBirthInfo.setLocation((x-mBirthInfo.getWidth())/2+1000,(y-mBirthInfo.getHeight())/2+500);
			mBirthInfo.setLayout(new FlowLayout());
			JLabel msg=new JLabel("생년월일을 입력해주세요.");
			JButton ok=new JButton("확인");
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mBirthInfo.setVisible(false);
					mBirthInfo.dispose();
				}				
			});
			mBirthInfo.add(msg);
			mBirthInfo.add(ok);
			mBirthInfo.setVisible(true);
			textField_birth1.requestFocus();
			textField_birth1.selectAll();
		}else if(!integerOrNot(sb.toString())){
			final Dialog mBirthInfo1=new Dialog(frame,"Information",true);
			mBirthInfo1.setSize(200,100);
			mBirthInfo1.setLocation((x-mBirthInfo1.getWidth())/2+1000,(y-mBirthInfo1.getHeight())/2+500);
			mBirthInfo1.setLayout(new FlowLayout());
			JLabel msg=new JLabel("생년월일은 숫자로 입력해주세요.");
			JButton ok=new JButton("확인");
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mBirthInfo1.setVisible(false);
					mBirthInfo1.dispose();
				}				
			});
			mBirthInfo1.add(msg);
			mBirthInfo1.add(ok);
			mBirthInfo1.setVisible(true);
			textField_birth1.requestFocus();
			textField_birth1.selectAll();
		}else if(mAdd.equals("")){
			final Dialog mAddInfo=new Dialog(frame,"Information",true);
			mAddInfo.setSize(200,100);
			mAddInfo.setLocation((x-mAddInfo.getWidth())/2+1000,(y-mAddInfo.getHeight())/2+500);
			mAddInfo.setLayout(new FlowLayout());
			JLabel msg=new JLabel("주소를 입력해주세요.");
			JButton ok=new JButton("확인");
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mAddInfo.setVisible(false);
					mAddInfo.dispose();
				}				
			});
			mAddInfo.add(msg);
			mAddInfo.add(ok);
			mAddInfo.setVisible(true);
			textField_add.requestFocus();
			textField_add.selectAll();
		}else if(sb1.equals("")){
			final Dialog mPhoneInfo=new Dialog(frame,"Information",true);
			mPhoneInfo.setSize(200,100);
			mPhoneInfo.setLocation((x-mPhoneInfo.getWidth())/2+1000,(y-mPhoneInfo.getHeight())/2+500);
			mPhoneInfo.setLayout(new FlowLayout());
			JLabel msg=new JLabel("번호를 입력해주세요.");
			JButton ok=new JButton("확인");
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mPhoneInfo.setVisible(false);
					mPhoneInfo.dispose();
				}				
			});
			mPhoneInfo.add(msg);
			mPhoneInfo.add(ok);
			mPhoneInfo.setVisible(true);
			textField_phone1.requestFocus();
			textField_phone1.selectAll();
			}else if(!integerOrNot(sb1.toString())){
				final Dialog mPhoneInfo1=new Dialog(frame,"Information",true);
				mPhoneInfo1.setSize(200,100);
				mPhoneInfo1.setLocation((x-mPhoneInfo1.getWidth())/2+1000,(y-mPhoneInfo1.getHeight())/2+500);
				mPhoneInfo1.setLayout(new FlowLayout());
				JLabel msg=new JLabel("번호는 숫자로 입력해주세요.");
				JButton ok=new JButton("확인");
				ok.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						mPhoneInfo1.setVisible(false);
						mPhoneInfo1.dispose();
					}				
				});
				mPhoneInfo1.add(msg);
				mPhoneInfo1.add(ok);
				mPhoneInfo1.setVisible(true);
				textField_phone1.requestFocus();
				textField_phone1.selectAll();
			}else if(sb2.equals("")){
			final Dialog mMailInfo=new Dialog(frame,"Information",true);
			mMailInfo.setSize(200,100);
			mMailInfo.setLocation((x-mMailInfo.getWidth())/2+1000,(y-mMailInfo.getHeight())/2+500);
			mMailInfo.setLayout(new FlowLayout());
			JLabel msg=new JLabel("이메일을 입력해주세요.");
			JButton ok=new JButton("확인");
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mMailInfo.setVisible(false);
					mMailInfo.dispose();
				}				
			});
			mMailInfo.add(msg);
			mMailInfo.add(ok);
			mMailInfo.setVisible(true);
			textField_mail.requestFocus();
			textField_mail.selectAll();
		}
			else{ //모든 칸이 다 꽉 찼으면  멤버dto에 입력한 것들을 대입한다.
				
				MemberDTO dto=new MemberDTO();
				dto.setId(mID);
				dto.setName(mName);
				dto.setBirthday(sb.toString()); //19910419 -> 1991419 4월은 04로 입력해야함. DAO에서 to_date시키기 때문에 오류남.
				dto.setAddress(mAdd);
				dto.setAge(mAge);
				dto.setPhone(sb1.toString()); //01095998215
				dto.setEmail(sb2.toString()); //alstndhffla@naver.com
				//위 데이터를 MemberDAO로 오라클에 입력 후 확인을 위해 data로 카운트
				MemberDAO dao=new MemberDAO();
				int data=dao.insertMember(dto);
				System.out.println("입력자료"+data);		  		
				dispose();
			}
  		
		}
		
	}
	
	// 회원등록시 전화번호 입력값이 숫자인지 문자인지 판별.
	public boolean integerOrNot(String memData){  
		char[] charData = memData.toCharArray();
		boolean check=true;
		while(check){
			for(int i=0; i<charData.length; i++){		
				if(!Character.isDigit(charData[i])){
						check = !check;
						break;
						
				}
			}
			break;	
		}return check;
	}
	
	//회원등록시 아이디 입력칸에 문자형만 들어올 수 있도록.. 특수문자 방지용.
	public boolean mID(String string) {
		// TODO Auto-generated method stub
		return false;
	}
}

