package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import controller.Login;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class LoginFrame extends JFrame {
	public LoginFrame() {
	}
	static JFrame frame = new JFrame("Expense Manager Application");

	public static boolean getLogInf() {
		return loginSuccess;
	}

	static boolean loginSuccess=false;
		
	public static void placeComponents(JPanel panel) {

			panel.setLayout(null);

			JLabel userLabel = new JLabel("ID");
			userLabel.setBounds(130, 10, 80, 25);
			panel.add(userLabel);

			JTextField userText = new JTextField(20);
			userText.setBounds(230, 10, 160, 25);
			userText.setText(Login.user);
			panel.add(userText);

			JLabel passwordLabel = new JLabel("Password");
			passwordLabel.setBounds(130, 40, 80, 25);
			panel.add(passwordLabel);

			JPasswordField passwordText = new JPasswordField(20);
			passwordText.setBounds(230, 40, 160, 25);
			passwordText.setText(Login.pw);
			panel.add(passwordText);
			
			JLabel logStatus = new JLabel("");
			logStatus.setBounds(230, 70, 200, 15);
			panel.add(logStatus);
			
			JButton loginButton = new JButton("Login");
			loginButton.setBounds(300, 90, 80, 25);
			panel.add(loginButton);
			
			if (!Login.status.equals("Connected.")) {
				loginButton.setEnabled(false);
			}
			
			//Mainframe geçiþ
			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int _id=Login.LoginDB(userText.getText(),passwordText);
					if (_id!=0) {
					logStatus.setText("Logging in... ");
					loginSuccess=true;
					// When logged in.
					frame.setVisible(false);
					MainFrame frame = new MainFrame();
					}
					else {
						logStatus.setText("Wrong ID/Password!");
					}
				}
			});
			frame.getRootPane().setDefaultButton(loginButton);
	}
		
		public static boolean logMainFrame() {
			
			Login db = new Login();
			
			loginSuccess=false;
			setIcon();
			
			frame.setBounds(500, 350, 420, 170);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//Status Bar
			JPanel statusPanel = new JPanel();
			statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
			statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
			statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
			JLabel statusLabel = new JLabel("status");
			statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
			statusPanel.add(statusLabel);
			// connect to sql
			db.Connect();
			statusLabel.setText(Login.status);

			//Login screen 
			JPanel panel = new JPanel();
			frame.getContentPane().add(panel);
			frame.getContentPane().add(statusPanel, BorderLayout.SOUTH);
			placeComponents(panel);
			
			frame.setVisible(true);
			return loginSuccess;
	  	  
		}
		private static void setIcon() {
			JLabel iconLabel = new JLabel();
			frame.getContentPane().add(iconLabel);
			iconLabel.setBounds(0, 0, 128, 128);
			iconLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/expenseManager/login.png")));
			iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
