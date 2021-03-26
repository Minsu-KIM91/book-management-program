package libraryProject;



import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.CardLayout;

public class Main extends JFrame {

	JPanel panel;
	CardLayout card=new CardLayout(); //사용할 카드 레이아웃 설정
	
	JButton btnNewButton;
	JButton button;
	JButton button_1;
	JButton button_2;
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { //runnable run 메서드가 EventQueue 디스패치스레드로 호출되도록 합니다.
			public void run() {
				try {
					Toolkit tk=Toolkit.getDefaultToolkit(); //디폴트값의 툴 킷을 리턴합니다->모니터화면 크기 알아내는 것.해상도기준
					Dimension screenSize=tk.getScreenSize(); //화면 크기를 얻어내는데, 폭과 높이를 캡슐화해줌.
					Main frame = new Main();		
					frame.setVisible(true);
					frame.setLocation((screenSize.width-frame.getWidth())/2,(screenSize.height-frame.getHeight())/2);
					//절대위치지정.

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		
		setTitle("도서관(사서용)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 505);		
		
		setLayout(null);		
		
		btnNewButton = new JButton("대출");
		btnNewButton.setBounds(0, 0, 200, 49);
		add(btnNewButton);
		
		button = new JButton("반납");
		button.setBounds(200, 0, 200, 49);
		add(button);
		
		button_1 = new JButton("도서관리");
		button_1.setBounds(400, 0, 200, 49);
		add(button_1);
		
		button_2 = new JButton("회원관리");
		button_2.setBounds(600, 0, 200, 49);
		add(button_2);		
		
		panel = new JPanel();
		panel.setBounds(0, 48, 783, 419);
		panel.setLayout(card);
		
		panel.add(new BookRental(),"1");
		panel.add(new BookOut(),"2");
		panel.add(new BookManager(),"3");
		panel.add(new MemberManager(),"4");
		panel.add(new BookReturn_plan(),"5");
		card.show(panel, "5"); //프로그램 구동시 맨 첫 화면으로 보여줄 카드패널.
		add(panel);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				card.show(panel,"1");
			}
		});		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(true);
				card.show(panel,"2");
			}
		});	
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				panel.setVisible(true);
				card.show(panel,"3");
			}
		});		
			
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				panel.setVisible(true);
				card.show(panel,"4");
			}
		});	

		
	}
}
