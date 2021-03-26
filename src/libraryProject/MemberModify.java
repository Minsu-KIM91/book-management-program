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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.norazo.dao.MemberDAO;
import com.norazo.dto.MemberDTO;

//회원관리 -> 수정/탈퇴 클릭시 나오는 창. 회원목록중에 수정하고 싶은 회원을 먼저 클릭하고 버튼을 누르도록!
//(아이디하고 이름은 고정, 나머지 수정 가능 -> 이메일은 아직 자동으로 가져오는 거 못함.)
public class MemberModify extends JFrame {

	private JPanel contentPane;
	private JTextField text_id;
	private JTextField text_name;
	private JTextField text_birth_1;
	private JTextField text_birth_2;
	private JTextField text_birth_3;
	private JTextField text_address;
	private JTextField text_age;
	private JTextField text_phone_2;
	private JTextField text_phone_3;
	private JTextField text_email;
	private static MemberModify frame;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	
	private JButton out;
	private JButton update;
	private JButton cancel;
	
	
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_17;
	private JLabel label_18;
	private JLabel label_19;
	private JLabel label_20;
	
	
	public MemberModify() {
		setResizable(false); //창의 크기를 조절할 수 없도록.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//원하는 하나의 프레임만 종료 시키기 위해서.
		setBounds(100, 100, 580, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//위,왼쪽,아래,오른쪽 간격 5인 빈 보더
		contentPane.setLayout(null); //기본 배치관리자 삭제
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 638, 83);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("회원수정");
//		lblNewLabel.setFont(new Font("", Font.BOLD, 20));
		lblNewLabel.setBounds(237, 27, 110, 44);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setBounds(74, 133, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		label_2 = new JLabel("이름");
		label_2.setBounds(74, 176, 57, 15);
		contentPane.add(label_2);
		
		label_3 = new JLabel("생년월일");
		label_3.setBounds(74, 222, 57, 15);
		contentPane.add(label_3);
		
		label_4 = new JLabel("주소");
		label_4.setBounds(74, 263, 57, 15);
		contentPane.add(label_4);
		
		label_5 = new JLabel("번호");
		label_5.setBounds(74, 307, 57, 15);
		contentPane.add(label_5);
		
		label_6 = new JLabel("이메일");
		label_6.setBounds(74, 354, 57, 15);
		contentPane.add(label_6);
		
		out = new JButton("탈퇴하기");
		out.addActionListener(new EventHandler());
		out.setActionCommand("out");
		out.setBounds(106, 445, 97, 23);
		contentPane.add(out);
		
		update = new JButton("수정하기");
		update.addActionListener(new EventHandler());
		update.setActionCommand("update");
		update.setBounds(219, 445, 97, 23);
		contentPane.add(update);
		
		cancel = new JButton("취소");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancel.setBounds(329, 445, 97, 23);
		contentPane.add(cancel);
		
		text_id = new JTextField();
		text_id.setEditable(false);
		text_id.setBounds(190, 130, 116, 21);
		contentPane.add(text_id);
		text_id.setColumns(10);
		
		text_name = new JTextField();
		text_name.setEditable(false);
		text_name.setColumns(10);
		text_name.setBounds(190, 173, 116, 21);
		contentPane.add(text_name);
		
		text_birth_1 = new JTextField();
		text_birth_1.setColumns(10);
		text_birth_1.setBounds(190, 219, 57, 21);
		contentPane.add(text_birth_1);
		
		text_address = new JTextField();
		text_address.setColumns(10);
		text_address.setBounds(190, 260, 270, 21);
		contentPane.add(text_address);
		
		text_email = new JTextField();
		text_email.setColumns(10);
		text_email.setBounds(190, 351, 116, 21);
		contentPane.add(text_email);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"-선택-", "010", "011", "016", "017", "018", "019"}));
		comboBox_1.setBounds(190, 304, 57, 21);
		contentPane.add(comboBox_1);
		
		label_7 = new JLabel("@");
		label_7.setBounds(318, 354, 27, 15);
		contentPane.add(label_7);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"-이메일 선택-", "naver.com", "daum.net", "nate.com", "hotmail.com", "yahoo.com", "empas.com", "korea.com", "dreamwiz.com", "gmail.com"}));
		comboBox_2.setBounds(344, 351, 102, 21);
		contentPane.add(comboBox_2);
		
		label_8 = new JLabel("-");
		label_8.setBounds(259, 310, 25, 15);
		contentPane.add(label_8);
		
		text_phone_2 = new JTextField();
		text_phone_2.setColumns(10);
		text_phone_2.setBounds(281, 304, 62, 21);
		contentPane.add(text_phone_2);
		
		text_phone_3 = new JTextField();
		text_phone_3.setColumns(10);
		text_phone_3.setBounds(372, 304, 62, 21);
		contentPane.add(text_phone_3);
		
		label_9 = new JLabel("-");
		label_9.setBounds(348, 307, 25, 15);
		contentPane.add(label_9);
		
		label_17 = new JLabel("년");
		label_17.setBounds(259, 222, 25, 15);
		contentPane.add(label_17);
		
		text_birth_2 = new JTextField();
		text_birth_2.setColumns(10);
		text_birth_2.setBounds(281, 219, 35, 21);
		contentPane.add(text_birth_2);
		
		label_18 = new JLabel("월");
		label_18.setBounds(329, 222, 25, 15);
		contentPane.add(label_18);
		
		text_birth_3 = new JTextField();
		text_birth_3.setColumns(10);
		text_birth_3.setBounds(348, 219, 35, 21);
		contentPane.add(text_birth_3);
		
		label_19 = new JLabel("일");
		label_19.setBounds(395, 222, 25, 15);
		contentPane.add(label_19);
		
		label_20 = new JLabel("나이");
		label_20.setBounds(318, 176, 57, 15);
		contentPane.add(label_20);
		
		text_age = new JTextField();
		text_age.setColumns(10);
		text_age.setBounds(358, 173, 57, 21);
		contentPane.add(text_age);
		
		setVisible(true); //이게 없으면 '수정'창이 출력안됨.
	}
	
	//
	public void setDate(String[] modifyresult){
		
		text_id.setText(modifyresult[0]);
		text_name.setText(modifyresult[1]);
		text_address.setText(modifyresult[2]);
		text_birth_1.setText(modifyresult[3].substring(0,4)); //  date로 저장되어 있음 91/04/19  -> 91
		text_birth_2.setText(modifyresult[3].substring(4,6)); // 앞에서부터 3번째까지 덜어내고 총 5개 반환-> 04
		text_birth_3.setText(modifyresult[3].substring(6,8)); //
		text_age.setText(modifyresult[4]);
		text_phone_2.setText(modifyresult[5].substring(3,7)); //01095998215 -> 9599
		text_phone_3.setText(modifyresult[5].substring(7,11)); // 01095998215 -> 8215
		
	}	
	class EventHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("out")){	//탈퇴하기 클릭시 
				MemberDTO dto=new MemberDTO();
				String mID=text_id.getText();
				dto.setId(mID);
				MemberDAO dao=new MemberDAO();		
				int data=dao.deleteMember(dto); //멤버dao에서 sql문으로 삭제
				System.out.println("삭제"+data);		  		
				dispose();			
			}
			else if(e.getActionCommand().equals("update")){ //수정하기 클릭시
				
				//칸에 입력한대로 테이블 값이 바뀌도록 선언.
				StringBuffer sb=new StringBuffer();	
				StringBuffer sb1=new StringBuffer();
				StringBuffer sb2=new StringBuffer();
				
				String mID=text_id.getText(); //아이디
				String mName=text_name.getText(); //이름
			
				String birth1 = text_birth_1.getText(); // 년도
				String birth2 = text_birth_2.getText(); // 월
				String birth3 = text_birth_3.getText(); // 일
				sb.append(birth1);
				sb.append(birth2);
				sb.append(birth3);
				// sb 19910419
								
				sb1.append(comboBox_1.getSelectedItem().toString());
				sb1.append(text_phone_2.getText());
				sb1.append(text_phone_3.getText());
								
//				sb2.append(text_email.getText());
				String email1 = text_email.getText();
//				sb2.append(comboBox_2.getSelectedItem().toString());
				String email2 = comboBox_2.getSelectedItem().toString();
								
				String mAdd=text_address.getText();
				String mAge=text_age.getText();
				
				// 수정할 때 칸을 다 채우지 않고 '수정하기'를 누룰시 채우라고 알려주는 경고창.
				// 277 ~ 296 -> 이름 칸을 비웠을 시 보여주는 경고창이라 없어도 무방.
				if(mName.equals("")){
					final Dialog mNameInfo=new Dialog(frame,"Information",true);
					mNameInfo.setSize(200,100);
					//mNameInfo.setLocation((x-mNameInfo.getWidth())/2+70,(y-mNameInfo.getHeight())/2+70);
					mNameInfo.setLayout(new FlowLayout());
					JLabel msg=new JLabel("이름을 입력해주세요");
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
					text_name.requestFocus();
					text_name.selectAll();
					//277 ~ 296 -> 이름 칸을 비웠을 시 보여주는 경고창이라 없어도 무방.
					
				}else if(mAge.equals("")){
					final Dialog mAgeInfo=new Dialog(frame,"Information",true);
					mAgeInfo.setSize(200,100);
					//mAgeInfo.setLocation((x-mAgeInfo.getWidth())/2+70,(y-mAgeInfo.getHeight())/2+70);
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
					text_age.requestFocus();
					text_age.selectAll();
				}/*else if(!integerOrNot(mAge)){
					final Dialog mAgeInfo1=new Dialog(frame,"Information",true);
					mAgeInfo1.setSize(200,100);
					mAgeInfo1.setLocation((x-mAgeInfo1.getWidth())/2+70,(y-mAgeInfo1.getHeight())/2+70);
					mAgeInfo1.setLayout(new FlowLayout());
					JLabel msg=new JLabel("나이는 숫자로 입력해주세요.");
					JButton ok=new JButton("확인");
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
				}*/
					//911207
					//1991.01.1
					else if(sb.equals("")){ // 생략가능
						if(birth1.length() < 4 || birth2.length() < 2 || birth3.length() < 2) {
							final Dialog mBirthInfo=new Dialog(frame,"Information",true);
							mBirthInfo.setSize(200,100);
							//mBirthInfo.setLocation((x-mBirthInfo.getWidth())/2+70,(y-mBirthInfo.getHeight())/2+70);
							mBirthInfo.setLayout(new FlowLayout());
							JLabel msg=new JLabel("생년월일을 입력해주세요");
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
							text_birth_1.requestFocus();
							text_birth_1.selectAll();
						}
					
				}else if(mAdd.equals("")){
					final Dialog mAddInfo=new Dialog(frame,"Information",true);
					mAddInfo.setSize(200,100);
					//mAddInfo.setLocation((x-mAddInfo.getWidth())/2+70,(y-mAddInfo.getHeight())/2+70);
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
					text_address.requestFocus();
					text_address.selectAll();
				}else if(sb1.equals("")){
					final Dialog mPhoneInfo=new Dialog(frame,"Information",true);
					mPhoneInfo.setSize(200,100);
					//mPhoneInfo.setLocation((x-mPhoneInfo.getWidth())/2+70,(y-mPhoneInfo.getHeight())/2+70);
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
					text_phone_2.requestFocus();
					text_phone_2.selectAll();
					}else if(!integerOrNot(sb1.toString())){
						final Dialog mPhoneInfo1=new Dialog(frame,"Information",true);
						mPhoneInfo1.setSize(200,100);
						//mPhoneInfo1.setLocation((x-mPhoneInfo1.getWidth())/2+70,(y-mPhoneInfo1.getHeight())/2+70);
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
						text_phone_2.requestFocus();
						text_phone_2.selectAll();
					}else if(email1.equals("") || email2.equals("-이메일 선택-")){ // 이메일 작동안함 ;;
					final Dialog mMailInfo=new Dialog(frame,"Information",true);
					mMailInfo.setSize(200,100);
					//mMailInfo.setLocation((x-mMailInfo.getWidth())/2+70,(y-mMailInfo.getHeight())/2+70);
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
					text_email.requestFocus();
					text_email.selectAll();
				}
					else{					 
						MemberDTO dto=new MemberDTO();
						dto.setId(mID);
						dto.setName(mName);
						dto.setBirthday(sb.toString());
						dto.setAddress(mAdd);
						dto.setAge(mAge);
						dto.setPhone(sb1.toString());
						sb2.append(email1+"@");
						sb2.append(email2);
						dto.setEmail(sb2.toString());			
						MemberDAO dao=new MemberDAO();
						int data=dao.updateMember(dto);
						System.out.println("수정자료:"+data);		  		
						dispose();
					}
			}//수정하기 누른 후 나오는 정보창 입력건 end
			
		}
	}// 수정/탈퇴 버튼 클릭시 eventHandler end
	
	public boolean integerOrNot(String memData){ // 번호 입력값이 숫자인지 문자인지 판별
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
}
