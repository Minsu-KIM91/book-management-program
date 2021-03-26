package libraryProject;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;


//관리자 로그인 화면. admin/1234
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private static Login frame;
	private static int x,y;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Toolkit tk=Toolkit.getDefaultToolkit();
					Dimension screenSize=tk.getScreenSize();
					frame = new Login();
					frame.setSize(340,200);
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

	
	public Login() {
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel adminID = new JLabel("아이디");
		adminID.setHorizontalAlignment(SwingConstants.LEFT);
		adminID.setBounds(61, 58, 57, 15);
		contentPane.add(adminID);
		
		JLabel adminPWD = new JLabel("비밀번호");
		adminPWD.setBounds(61, 83, 57, 15);
		contentPane.add(adminPWD);
		
		textField = new JTextField();
		textField.setBounds(130, 55, 116, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(130, 81, 116, 21);
		contentPane.add(passwordField);
		
		btnNewButton = new JButton("로그인");
		btnNewButton.setBounds(110, 115, 97, 23);
		contentPane.add(btnNewButton);
		
		textField.addActionListener(new EventHandler());
		passwordField.addActionListener(new EventHandler());
		btnNewButton.addActionListener(new EventHandler());
	}
	class EventHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String id=textField.getText();
			String pwd=passwordField.getText();
			if(!id.equals("admin")){
				final Dialog idInfo=new Dialog(frame,"Information",true);
				idInfo.setSize(150,110);
				idInfo.setLocation((x-idInfo.getWidth())/2+70,(y-idInfo.getHeight())/2+70);	
				idInfo.setLayout(new FlowLayout());
				JLabel msg=new JLabel("아이디를 확인해주세요");
				JButton ok=new JButton("확인"); 
				ok.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						idInfo.setVisible(false);
						idInfo.dispose();
					}				
				});
				idInfo.add(msg);
				idInfo.add(ok);
				idInfo.setVisible(true);
				textField.requestFocus();
				textField.selectAll();
			}
			else if(!pwd.equals("1234")){
				final Dialog pwdInfo=new Dialog(frame,"Information",true);
				pwdInfo.setSize(170,110);
				pwdInfo.setLocation((x-pwdInfo.getWidth())/2+70,(y-pwdInfo.getHeight())/2+70);	
				pwdInfo.setLayout(new FlowLayout());
				JLabel msg=new JLabel("비밀번호를 확인해주세요");
				JButton ok=new JButton("확인");
				ok.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						pwdInfo.setVisible(false);
						pwdInfo.dispose();
					}				
				});
				pwdInfo.add(msg);
				pwdInfo.add(ok);
				pwdInfo.setVisible(true);
				passwordField.requestFocus();
				passwordField.selectAll();
			}
			else
			{
				Main Mainframe=new Main();
				String[] str={"메인호출"};
				Mainframe.main(str);
				frame.setVisible(false);
			}
		}
		
	}
}
