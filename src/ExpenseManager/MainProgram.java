package ExpenseManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import controller.Login;
import view.LoginFrame;

public class MainProgram {
	
	static String version = new String("ExpenseManager v5"); 
	
	public static void main(String[] args) throws SQLException {
		readTextFile();
		LoginFrame.logMainFrame();
		System.out.println("MainProgram working.");
	}
	
	public static void readTextFile() {
		BufferedReader br = null;

		try {

			String sCurrentLine;
			// get data source from file
			// ip=?
			// user=?
			// pw=?
			br = new BufferedReader(new FileReader("C:\\Log5.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				if (sCurrentLine.contains("ip")){
					Login.ipAddress = sCurrentLine.substring(3);
				}
				else if (sCurrentLine.contains("user")){
					Login.user = sCurrentLine.substring(5);
				}
				else if (sCurrentLine.contains("pw")){
					Login.pw = sCurrentLine.substring(3);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
