/**
 * Mert AYDAR
 * 13011068
 * e-mail: mertaydar@outlook.com
 * 15 Tem 2016
 */
package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Login;
import model.Person;


public class SendMessageFrame extends JFrame {

	private JPanel contentPane;
	private String[] persNames;
	private ArrayList<Person> list;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendMessageFrame frame = new SendMessageFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SendMessageFrame() {
		setTitle("Send Message");
		list = Login.getNameList();
		persNames = new String[5];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("To who :");
		lblId.setBounds(10, 11, 64, 14);
		contentPane.add(lblId);
		
		JLabel lblPass = new JLabel("Message :");
		lblPass.setBounds(10, 36, 46, 14);
		contentPane.add(lblPass);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(58, 8, 129, 20);
		contentPane.add(comboBox);
		for (int i = 0; i < list.size(); i++) {
			persNames[i] = list.get(i).getNick();
		}
		comboBox.setModel(new DefaultComboBoxModel(persNames));
		
		JTextArea textArea = new JTextArea(2,20);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		textArea.setForeground(SystemColor.desktop);
		textArea.setLineWrap(true);
		textArea.setBounds(66, 36, 200, 45);
		contentPane.add(textArea);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(10, 92, 157, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=0;
				while (!comboBox.getSelectedItem().equals(list.get(i).getNick())) {
					i++;
				}
				if (textArea.getText().length() < 51) {
					Login.sendMessage(list.get(i).getID(), textArea.getText(), 1);
					setVisible(false);
					dispose();
				}else {
					lblNewLabel.setText("Message too long !!!");
				}
				
			}
		});
		btnSend.setBounds(177, 92, 89, 23);
		contentPane.add(btnSend);
		
		JButton btnMymessages = new JButton("My message");
		btnMymessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Login.getMessage(Login.logId).size() == 0) {
					Component frame = null;
					JOptionPane.showMessageDialog(frame,
						    "Message box empty.",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				} else {
					MyMessages myMessages = new MyMessages();
					myMessages.setVisible(true);
					myMessages.setDefaultCloseOperation(HIDE_ON_CLOSE);
				}
			}
		});
		btnMymessages.setBounds(20, 116, 103, 23);
		contentPane.add(btnMymessages);
	}
}
