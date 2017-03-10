/**
 * Mert AYDAR
 * 13011068
 * e-mail: mertaydar@outlook.com
 * 16 Tem 2016
 */
package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Login;
import model.Message;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MyMessages extends JFrame {

	private JPanel contentPane;
	private ArrayList<Message> list;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyMessages frame = new MyMessages();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MyMessages() {
		list = Login.getMessage(Login.logId);
		setTitle("My Messages");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 165);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 11, 319, 20);
		contentPane.add(comboBox);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Login.deleteMessage(list.get(comboBox.getSelectedIndex()).getUniqueID());
					setVisible(false);
					MyMessages msgFrame = new MyMessages();
					msgFrame.setVisible(true);
					msgFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
					System.out.println("refresh");
					System.out.println(list.get(comboBox.getSelectedIndex()).getUniqueID()+" deleted.");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(335, 10, 89, 23);
		contentPane.add(btnDelete);
		
		JTextArea txtrMessagebox = new JTextArea();
		txtrMessagebox.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrMessagebox.setBackground(new Color(153, 255, 102));
		txtrMessagebox.setEditable(false);
		txtrMessagebox.setText("messageBox");
		txtrMessagebox.setBounds(10, 46, 319, 75);
		contentPane.add(txtrMessagebox);
		String[] cmbBoxMsjArr = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			String msg = "";
			msg = list.get(i).getMsg();
			cmbBoxMsjArr[i] = msg;
		}
		
		comboBox.setModel(new DefaultComboBoxModel(cmbBoxMsjArr));
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(345, 44, 79, 14);
		contentPane.add(lblId);
		
		JLabel lblDate = new JLabel("date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblDate.setBounds(341, 69, 83, 46);
		contentPane.add(lblDate);
		// For first assignment
		txtrMessagebox.setText(comboBox.getSelectedItem().toString());
		lblId.setText(list.get(comboBox.getSelectedIndex()).getName());
		lblDate.setText(list.get(comboBox.getSelectedIndex()).getDate());
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtrMessagebox.setText(comboBox.getSelectedItem().toString());
				lblId.setText(list.get(comboBox.getSelectedIndex()).getName());
				lblDate.setText(list.get(comboBox.getSelectedIndex()).getDate());
				System.out.println(list.get(comboBox.getSelectedIndex()).getMsg());
			}
		});
	}
	
}
