package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Login;
import model.ExpenseTable;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;

public class MonthlyExpense extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private float totalSpend;
	private float amount_tlh=0,amount_krs=0,amount_mrt=0,amount_gkh=0,amount_mmi=0,avg;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonthlyExpense frame = new MonthlyExpense();
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
	public MonthlyExpense() {
		setTitle("Monthly Expenses");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblKursat = new JLabel("K\u00FCr\u015Fat: ");
		
		JLabel lblMuhammed = new JLabel("Muhammed:");
		
		JLabel lblTalha = new JLabel("Talha:");
		
		JLabel lblMert = new JLabel("Mert:");
		
		JLabel lblGkhan = new JLabel("G\u00F6khan:");
		
		JLabel lblAmount_talha = new JLabel("amount");
		
		JLabel lblAmount_kurs = new JLabel("amount");
		
		JLabel lblAmount_mert = new JLabel("amount");
		
		JLabel lblAmount_mami = new JLabel("amount");
		
		JLabel lblAmount_gkhn = new JLabel("amount");
		
		JLabel lblAvg = new JLabel("Average:");
		lblAvg.setForeground(Color.BLUE);
		lblAvg.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel label_ort = new JLabel("0.0");
		label_ort.setForeground(Color.BLUE);
		label_ort.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblKursat)
								.addComponent(lblTalha, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAmount_talha, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAmount_kurs, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblMert, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAmount_mert, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addGap(24)
									.addComponent(lblGkhan)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAmount_gkhn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblMuhammed)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAmount_mami, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addGap(31)
									.addComponent(lblTotal)
									.addGap(18)
									.addComponent(lblAmount, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAvg, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(label_ort, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
							.addGap(128)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTalha)
						.addComponent(lblAmount_talha)
						.addComponent(lblGkhan)
						.addComponent(lblMert)
						.addComponent(lblAmount_mert)
						.addComponent(lblAmount_gkhn))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKursat)
						.addComponent(lblTotal)
						.addComponent(lblAmount)
						.addComponent(lblMuhammed)
						.addComponent(lblAmount_kurs)
						.addComponent(lblAmount_mami)
						.addComponent(lblAvg, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_ort, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		String col[] = {"Person","Piece","Kind","Description","Amount"};

		DefaultTableModel tableModel = new DefaultTableModel(col, 0)  {		// The 0 argument is number rows.
																				
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};

		JTable table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		ArrayList<ExpenseTable> tmpTable = new ArrayList<ExpenseTable>();

		tmpTable = Login.getExpenseTable();
		totalSpend=0;
		for (int i = 0; i < tmpTable.size(); i++){
		   int piec = tmpTable.get(i).getPiece();
		   String name = tmpTable.get(i).getPerson();
		   String desc = tmpTable.get(i).getDescription();
		   String type = tmpTable.get(i).getType();
		   float prc = tmpTable.get(i).getPrice();
		   totalSpend+=prc;
		   if ( name.equals("Mert Aydar") ) {
			   amount_mrt+=prc;
		   }
		   
		   if ( name.equals("Gokhan Icoz") ) {
			   amount_gkh+=prc;
		   }
		   
		   if ( name.equals("Kursat Sencar") ) {
			   amount_krs+=prc;
		   }
		   
		   if ( name.equals("Muhammed Ozdede") ) {
			   amount_mmi+=prc;
		   }
		   
		   if ( name.equals("M Talha Yasar") ) {
			   amount_tlh+=prc;
		   }
		   
		   Object[] data = {name, piec, type, desc, prc};

		   tableModel.addRow(data);

		}
		lblAmount_talha.setText(String.valueOf(amount_tlh));
		lblAmount_kurs.setText(String.valueOf(amount_krs));
		lblAmount_mert.setText(String.valueOf(amount_mrt));
		lblAmount_gkhn.setText(String.valueOf(amount_gkh));
		lblAmount_mami.setText(String.valueOf(amount_mmi));
		lblAmount.setText(String.valueOf(totalSpend));
		avg = totalSpend / 5;
		label_ort.setText(String.valueOf(avg));
		
	}
	
	public String getAcc(int logId){
		Float hesap=null;
		if (logId==1){
			hesap=amount_mrt-avg;
			return hesap.toString();
		}
		else if (logId==6){
			hesap=amount_gkh-avg;
			return hesap.toString();
		}
		else if (logId==10){
			hesap=amount_krs-avg;
			return hesap.toString();
		}
		else if (logId==12){
			hesap=amount_mmi-avg;
			return hesap.toString();
		}
		else if (logId==13){
			hesap=amount_tlh-avg;
			return hesap.toString();
		}
		return "Err: Account do not readed.";
	}

}

