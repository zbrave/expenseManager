package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Login;
import model.ExpenseTable;

public class DebtsFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DebtsFrame frame = new DebtsFrame();
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
	public DebtsFrame() {
		setTitle("Personal Payments");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Debtor", "Creditor", "Amount"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		// Counts for by given and taken.
		
		ArrayList<ExpenseTable> tmpTable = new ArrayList<ExpenseTable>();
		tmpTable = Login.getExpenseTable();
		float totalSpend=0,amount_mrt=0,amount_gkh=0,amount_krs=0,amount_mmi=0,amount_tlh=0;
		for (int i = 0; i < tmpTable.size(); i++){
			   String name = tmpTable.get(i).getPerson();
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
			   
		}
		
		// Last calculation
		
		float mrtAcc = amount_mrt-totalSpend/5;
		float gkhAcc = amount_gkh-totalSpend/5;
		float mmiAcc = amount_mmi-totalSpend/5;
		float krsAcc = amount_krs-totalSpend/5;
		float tlhAcc = amount_tlh-totalSpend/5;
		float[] Accs = new float[5];
		Accs[0] = mrtAcc;
		Accs[1] = gkhAcc;
		Accs[2] = mmiAcc;
		Accs[3] = krsAcc;
		Accs[4] = tlhAcc;
		String[] names = new String[5];
		names[0] = "Mert";
		names[1] = "Gökhan";
		names[2] = "Muhammed";
		names[3] = "Kürþat";
		names[4] = "Talha";
		int i, j;
		for (i = 0 ; i < 5; i++) {
			System.out.println(names[i]+" "+Accs[i]);
		}
		for (i = 0; i < 4; i++ ) {
			for (j = i+1; j < 5; j++) {
				if ( Accs[i] < Accs[j] ) {
					float tmp;
					String sTmp;
					tmp = Accs[i];
					Accs[i] = Accs[j];
					Accs[j] = tmp;
					sTmp = names[i];
					names[i] = names[j];
					names[j] = sTmp;
				}
			}
		}
		for (i = 0 ; i < 5; i++) {
			System.out.println(names[i]+" "+Accs[i]);
		}
		i = 0; j = 4; //setted flags to queue's first and last location.
		do {
		if ((Accs[i] + Accs[j]) == 0) {
			System.out.println(names[i]+" <--- "+names[j]+" : "+Accs[i]);
			model.addRow(new Object[]{names[j],names[i],Accs[i]});
			Accs[i]=0;
			Accs[j]=0;
			i++;
			j--;
		}
		else if ((Accs[i] + Accs[j]) > 0) {
			System.out.println(names[i]+" <--- "+names[j]+" : "+ (-1*Accs[j]) );
			model.addRow(new Object[]{names[j],names[i],-1*Accs[j]});
			Accs[i] = Accs[i] + Accs[j];
			Accs[j] = 0;
			j--;
		}
		else if ((Accs[i] + Accs[j]) < 0) {
			System.out.println(names[i]+" <--- "+names[j]+" : "+Accs[i]);
			model.addRow(new Object[]{names[j],names[i],Accs[i]});
			Accs[j] = Accs[i] + Accs[j];
			Accs[i]=0;
			i++;
		}
		else {
//			printf("Err 505!!!\n");
		}
		}while(i<j);
		
	}

}
