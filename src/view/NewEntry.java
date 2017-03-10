package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Login;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class NewEntry extends JFrame {

	private JPanel contentPane;
	private JTextField textField_amount;
	private JTextField textField_amountkrs;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewEntry frame = new NewEntry();
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
	public NewEntry() {
		setTitle("New Entry");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPiece = new JLabel("Piece : ");
		lblPiece.setBounds(21, 22, 46, 14);
		contentPane.add(lblPiece);
		
		JComboBox comboBox_piece = new JComboBox();
		comboBox_piece.setToolTipText("");
		comboBox_piece.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBox_piece.setSelectedIndex(0);
		comboBox_piece.setBounds(67, 19, 50, 20);
		contentPane.add(comboBox_piece);
		
		JLabel lblKind = new JLabel("Kind : ");
		lblKind.setBounds(141, 22, 46, 14);
		contentPane.add(lblKind);
		
		JComboBox comboBox_kind = new JComboBox();
		comboBox_kind.setModel(new DefaultComboBoxModel(new String[] {"Misc..", "Invoice", "Nutrient", "Cleaning"}));
		comboBox_kind.setSelectedIndex(0);
		comboBox_kind.setBounds(173, 19, 84, 20);
		contentPane.add(comboBox_kind);
		
		JLabel lblDesc = new JLabel("Description :");
		lblDesc.setBounds(21, 84, 60, 14);
		contentPane.add(lblDesc);
		
		JLabel lblAmount = new JLabel("Amount :");
		lblAmount.setBounds(280, 22, 46, 14);
		contentPane.add(lblAmount);
		
		textField_amount = new JTextField();
		textField_amount.setText("0");
		textField_amount.setBounds(327, 19, 36, 20);
		contentPane.add(textField_amount);
		textField_amount.setColumns(10);
		
		JTextArea textArea_desc = new JTextArea();
		textArea_desc.setLineWrap(true);
		textArea_desc.setBounds(77, 65, 249, 50);
		contentPane.add(textArea_desc);
		
		JLabel label = new JLabel(",");
		label.setBounds(373, 22, 46, 14);
		contentPane.add(label);
		
		textField_amountkrs = new JTextField();
		textField_amountkrs.setText("0");
		textField_amountkrs.setBounds(383, 19, 28, 20);
		contentPane.add(textField_amountkrs);
		textField_amountkrs.setColumns(10);
		
		JLabel lblTl = new JLabel("tl");
		lblTl.setBounds(414, 22, 20, 14);
		contentPane.add(lblTl);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i,success=0;
				float amount=Float.parseFloat(textField_amountkrs.getText());

				for(i=0; i < textField_amountkrs.getText().length(); i++){
					amount/=10;
				}
				if (amount >= 0) {
					success=Login.addNewEntry(comboBox_piece.getSelectedIndex()+1, comboBox_kind.getSelectedItem().toString(), textArea_desc.getText(), Integer.parseInt(textField_amount.getText()) + amount );
				}
				// when add successfully close window
				if ( success != 0 ) {
					setVisible(false);
					dispose();
				}
			}
		});
		btnAdd.setBounds(345, 61, 89, 23);
		contentPane.add(btnAdd);
		
		
		
	}
}
