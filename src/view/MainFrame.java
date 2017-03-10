package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import controller.Login;
import model.Message;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private ResultSet res;
	private Statement sta;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	//	setIcon();
		int _id;
		setTitle("Expense Manager v5");
		setVisible(true);
		setBounds(500, 200, 625, 338);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelPhoto = new JLabel("");
		labelPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		labelPhoto.setBounds(10, 11, 109, 142);
		contentPane.add(labelPhoto);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblId.setBounds(129, 11, 169, 35);
		contentPane.add(lblId);
		
		JLabel lblAcc = new JLabel("Account :");
		lblAcc.setBounds(129, 57, 62, 14);
		contentPane.add(lblAcc);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(203, 57, 73, 14);
		contentPane.add(lblAmount);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(129, 114, 75, 14);
		contentPane.add(lblDate);
		
		//Monthly Expense Table 
		JButton btnMonthlyExpense = new JButton("Monthly Expense");
		btnMonthlyExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonthlyExpense moExTbl = new MonthlyExpense();
				moExTbl.setVisible(true);
				moExTbl.setDefaultCloseOperation(HIDE_ON_CLOSE);
			}
		});
		btnMonthlyExpense.setBounds(10, 190, 130, 23);
		contentPane.add(btnMonthlyExpense);
		
		JButton btnNewEntry = new JButton("New entry");
		btnNewEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewEntry frame = new NewEntry();
				frame.setVisible(true);
			}
		});
		btnNewEntry.setBounds(10, 228, 130, 23);
		contentPane.add(btnNewEntry);
		
		JButton btnPrsEx = new JButton("Personal Expense");
		btnPrsEx.setVisible(true); // Personal expense table.
		btnPrsEx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DebtsFrame dptsfrm = new DebtsFrame();
				dptsfrm.setVisible(true);
			}
		});
		btnPrsEx.setBounds(195, 190, 118, 23);
		contentPane.add(btnPrsEx);
		if ( Login.logId == 1 ) {
			JButton btnRefresh = new JButton("Refresh All Tables");
			btnRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(null, "Are you sure ?", "Message", 
                            JOptionPane.YES_NO_OPTION)==0){
						if (Login.refreshExpenseTable()==1){
							
							System.out.println("Refreshed.");
						}
						
					}
			}
			});
			btnRefresh.setBounds(195, 228, 118, 23);
			contentPane.add(btnRefresh);
		}
		
		JLabel lblMessages = new JLabel("Messages:");
		lblMessages.setBounds(129, 89, 62, 14);
		contentPane.add(lblMessages);
		
		JLabel lblNotification = new JLabel("Notifications:");
		lblNotification.setBounds(129, 139, 73, 14);
		contentPane.add(lblNotification);
		
		JLabel lblmsg = new JLabel();
		lblmsg.setText(Integer.toString(Login.getMessage(Login.logId).size()));
		lblmsg.setBounds(201, 89, 73, 14);
		contentPane.add(lblmsg);
		
		JLabel lblbild = new JLabel("Amount");
		lblbild.setBounds(201, 139, 73, 14);
		contentPane.add(lblbild);
		
		JLabel lbltrh = new JLabel("Date:");
		lbltrh.setBounds(201, 114, 73, 14);
		contentPane.add(lbltrh);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					sta = Login.conn.createStatement();
					sta.executeUpdate("update members set isOnline=0 where id="+Login.logId);
					System.out.println("Quit Sql server.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				Login.ShutdownDB();
				System.exit(1);
			}
		});
		btnExit.setBounds(195, 262, 118, 23);
		contentPane.add(btnExit);
		
		JButton btnSendMsg = new JButton("Send message");
		btnSendMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SendMessageFrame sendMsg = new SendMessageFrame();
				sendMsg.setVisible(true);
				sendMsg.setDefaultCloseOperation(HIDE_ON_CLOSE);
			}
		});
		btnSendMsg.setBounds(10, 262, 130, 23);
		contentPane.add(btnSendMsg);
		
		JLabel lblMonthMan = new JLabel("Man of month:");
		lblMonthMan.setBounds(322, 11, 90, 14);
		contentPane.add(lblMonthMan);
		
		JLabel lblMonthManName = new JLabel("Man of month");
		lblMonthManName.setFont(new Font("Ebrima", Font.BOLD, 15));
		lblMonthManName.setBounds(413, 0, 186, 34);
		lblMonthManName.setText(Login.manOfMonth());
		contentPane.add(lblMonthManName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(322, 57, 277, 211);
		contentPane.add(scrollPane);
		
		JTextPane txtpnChatpane = new JTextPane();
		txtpnChatpane.setEditable(false);
		scrollPane.setViewportView(txtpnChatpane);
		txtpnChatpane.setText(listGeneralMessage());
		
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login.sendGeneralMessage(textField.getText());
				txtpnChatpane.setText(listGeneralMessage());
				textField.setText("");
			}
		});
		btnSend.setBounds(510, 276, 89, 23);
		contentPane.add(btnSend);
		
		textField = new JTextField();
		textField.setBounds(323, 277, 191, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblWord = new JLabel("Word of month:");
		lblWord.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblWord.setForeground(Color.RED);
		lblWord.setBounds(322, 30, 278, 28);
		contentPane.add(lblWord);
		lblWord.setText(Login.getWordOfMonth());
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setSelectedIcon(new ImageIcon(MainFrame.class.getResource("/expenseManager/1475021170_Update.png")));
		btnNewButton.setIcon(new ImageIcon(MainFrame.class.getResource("/expenseManager/1475021170_Update.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MonthlyExpense a = new MonthlyExpense();
				lblAmount.setText(a.getAcc(Login.logId));
				lblWord.setText(Login.getWordOfMonth());
			}
		});
		btnNewButton.setBounds(289, 64, 24, 23);
		contentPane.add(btnNewButton);
		
		//Take datas from sql
		
		try {
			Statement sta = Login.conn.createStatement();
			ResultSet res = sta.executeQuery("select * from members where id="+Login.logId);
			while (res.next()) {
				MonthlyExpense a = new MonthlyExpense();
				lblAmount.setText(a.getAcc(Login.logId));
				lblbild.setText("Yakýnda...");
				SimpleDateFormat bicim=new SimpleDateFormat("dd/M/yyyy");
				Date tarih=new Date();
				lbltrh.setText(bicim.format(tarih).toString());	
				labelPhoto.setIcon(new ImageIcon(MainFrame.class.getResource(res.getString("photoSource"))));
				lblId.setText(res.getString("Ad"));
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (lblId.getText().equals(lblMonthManName.getText())){
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(10, 165, 191, 20);
			contentPane.add(textField_1);
			
			JButton button = new JButton("Send");
			button.setBounds(211, 165, 89, 23);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Login.setWordOfMonth(textField_1.getText());
				}
			});
			contentPane.add(button);
		}
		
	}
	
	public String listGeneralMessage(){
		ArrayList<Message> list = Login.getGeneralMessage();
		String paneText="";
		for (int i = 0; i < list.size(); i++) {
			paneText=paneText+(list.get(i).getName()+" : "+list.get(i).getMsg()+"\n");
		}
		
		return paneText;
	}
}
