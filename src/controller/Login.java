package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPasswordField;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import model.Debt;
import model.ExpenseTable;
import model.Person;
import model.Message;



public class Login {
	public static Connection conn;
	public static Statement sta;
	public static ResultSet res;	
	public static int logId;
	public static String logName;
	public static String ipAddress;
	public static String user;
	public static String pw;
	public static PreparedStatement ps;
	public static String status;
	public static Connection Connect() {

		try{
			String connectionString = "jdbc:sqlserver://"+ipAddress+";" +
	               "databaseName=giderHesap;user=sa;password=****;loginTimeout=1";
	/*		String connectionString = "jdbc:sqlserver://zbrave.database.windows.net:1433;database=giderHesap;user=zbrave@zbrave;"
			+ "password=*****;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;"
			+ "loginTimeout=30";
*/
			conn = DriverManager.getConnection(connectionString);
			status="Connected.";
			System.out.println(status);
			sta = conn.createStatement();
			res = sta.executeQuery("SELECT * FROM version");
			res.next();
			if (!res.getString("version").equals("ExpenseManager v5")) {
				status="New version available. Update.";
				System.out.println("Wrong version.");
			}
			res.close();
			sta.close();
		}
		catch (SQLServerException e1){
			e1.printStackTrace();
			status="No connection.";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static int LoginDB(String username, JPasswordField password){
		try {
			sta = conn.createStatement();
			res = sta.executeQuery("SELECT * FROM members");
			while (res.next()){
				if (res.getString("KullaniciAdi").equals(username) && res.getString("Sifre").equals(password.getText())) {
					logId=res.getInt("id");
					logName=res.getString("Ad");
					System.out.println(logId+" has logged in.");
				}
			}
			sta.executeUpdate("update members set isOnline=1 where id="+Login.logId);
			res.close();
			sta.close();
		//	ShutdownDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERR101!");
		}
		return logId;
	}
	
	public static void ShutdownDB(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}
	
	public static ArrayList<ExpenseTable> getExpenseTable() {
		ArrayList<ExpenseTable> ExpenseData = new ArrayList<ExpenseTable>();
		
		try {
			sta = conn.createStatement();
			res = sta.executeQuery("SELECT * FROM expenseTable");
			while ( res.next() ) {
				ExpenseTable exTbl = new ExpenseTable("","","",0,0);
				exTbl.setDescription(res.getString("Açýklama"));
				exTbl.setPerson(res.getString("Kiþi"));
				exTbl.setPiece(res.getInt("Adet"));
				exTbl.setType(res.getString("Tür"));
				exTbl.setPrice(res.getFloat("Tutar"));
				ExpenseData.add(exTbl);
			}
			res.close();
			sta.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ExpenseData;
	}
	
	public static ArrayList<Debt> getDebt() {
		ArrayList<Debt> debts = new ArrayList<Debt>();
		try {
			sta = conn.createStatement();
			res = sta.executeQuery("SELECT * FROM debts WHERE who="+logId+" or toWho="+logId);
			while ( res.next() ) {
				Debt tmpdbt = new Debt(0,0,0);
				tmpdbt.setDebt(res.getFloat("debt"));
				tmpdbt.setWho(res.getInt("who"));
				tmpdbt.setToWho(res.getInt("toWho"));
				debts.add(tmpdbt);
			}
			res.close();
			sta.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return debts;
	}
	
	public static int addNewEntry(int piece, String type, String description, float price) {
		int success=0;
		if (price < 0){
			return 0;
		}
		try {
			sta = conn.createStatement();
			success=sta.executeUpdate("INSERT INTO expenseTable(Kiþi,Adet,Tür,Açýklama,Tutar,Tarih)"+" VALUES('"+logName+"',"+piece+",'"+type+"','"+description+"',"+price+",GETDATE())");
			if ( success != 0 ) {
				System.out.println("Successfully added to db.");
			}
			sta.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return success;
	}
	
	public static int refreshExpenseTable(){
		try {
			sta = conn.createStatement();
			sta.execute("SELECT * INTO giderHesap.dbo.yedek FROM giderHesap.dbo.expenseTable");
			sta.execute("TRUNCATE TABLE giderHesap.dbo.expenseTable");
			sta.close();
			return 1;
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static ArrayList<Person> getNameList() {

		ArrayList<Person> kisiList = new ArrayList<Person>();
		
		try {
			sta = conn.createStatement();
			res = sta.executeQuery("SELECT TOP 10 [id],[KullaniciAdi],[mesajSayac],[isOnline]FROM [giderHesap].[dbo].[members]");
			while ( res.next() ) {
				Person kisi = new Person(-1, "AdSoyad", "nickname", -1, false, null, null);
				kisi.setID(res.getInt("id"));
				kisi.setNick(res.getString("KullaniciAdi"));
				kisi.setMsgCounter(res.getInt("mesajSayac"));
				kisi.setOnline(res.getBoolean("isOnline"));
				kisiList.add(kisi);
			}
			res.close();
			sta.close();
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		return kisiList;
	}
	
	public static int sendMessage(int toID, String message, int isShow) {
		int success=0;
		try {
			sta = conn.createStatement();
			success=sta.executeUpdate("INSERT INTO mesajlar(fromID,toID,mesaj,show,tarih)"+" VALUES('"+logId+"',"+toID+",'"+message+"','"+1+"'"+",GETDATE())");
			if ( success != 0 ) {
				System.out.println("Message added to db");
			}
			sta.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return success;
	}
	
	public static ArrayList<Message> getMessage(int id) {
		ArrayList<Message> mesajList = new ArrayList<Message>();
		try {
			sta = conn.createStatement();
			res=sta.executeQuery("SELECT TOP 1000 [id], [fromID],[toID],[mesaj],[tarih] FROM [giderHesap].[dbo].[mesajlar] WHERE show=1");
			while ( res.next() ) {
				Message msg = new Message();
				msg.setUniqueID(res.getInt("id"));
				msg.setFromID(res.getInt("fromID"));
				msg.setToID(res.getInt("toID"));
				msg.setMsg(res.getString("mesaj"));
				msg.setDate(res.getString("tarih"));
				String isim = "";
				if (msg.getFromID() == 1){
					isim=("Mert Aydar");
				}else if (msg.getFromID() == 6){
					isim=("Gökhan Ýçöz");
				}else if (msg.getFromID() == 10){
					isim=("Kürþat Sencar");
				}else if (msg.getFromID() == 12){
					isim=("Muhammed Özdede");
				}else if (msg.getFromID() == 13){
					isim=("M. Talha Yaþar");
				}
				msg.setName(isim);
				// selecting just toID person messages
				if (id == msg.getToID())
					mesajList.add(msg);
			}
			
			sta.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mesajList;
	}
	
	public static void deleteMessage(int id) throws SQLException {
			sta = conn.createStatement();
			sta.executeUpdate("UPDATE mesajlar SET show=0 where id="+id);
			
	}
	
	public static int sendGeneralMessage(String message) {
		int success=0;
		try {
			sta = conn.createStatement();
			success=sta.executeUpdate("INSERT INTO genelmesajlar(fromID,mesaj,tarih)"+" VALUES('"+logId+"','"+message+"',GETDATE())");
			if ( success != 0 ) {
				System.out.println("Mesaj Baþarýyla DB'ye eklendi.");
			}
			sta.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return success;
	}
	
	public static ArrayList<Message> getGeneralMessage() {
		ArrayList<Message> mesajList = new ArrayList<Message>();
		try {
			sta = conn.createStatement();
			res=sta.executeQuery("SELECT TOP 1000 [id],[mesaj],[fromID],[tarih] FROM [giderHesap].[dbo].[genelmesajlar]");
			while ( res.next() ) {
				Message msg = new Message();
				msg.setUniqueID(res.getInt("id"));
				msg.setFromID(res.getInt("fromID"));
				msg.setMsg(res.getString("mesaj"));
				msg.setDate(res.getString("tarih"));
				String name = "";
				if (msg.getFromID() == 1){
					name=("Mert Aydar");
				}else if (msg.getFromID() == 6){
					name=("Gökhan Ýçöz");
				}else if (msg.getFromID() == 10){
					name=("Kürþat Sencar");
				}else if (msg.getFromID() == 12){
					name=("Muhammed Özdede");
				}else if (msg.getFromID() == 13){
					name=("M. Talha Yaþar");
				}
				msg.setName(name);
				mesajList.add(msg);
			}
			
			sta.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mesajList;
	}
	
	public static String manOfMonth(){
		String men="";
		try {
			sta = conn.createStatement();
			res = sta.executeQuery("SELECT ad FROM members WHERE isKing=1");
			while ( res.next() ) {
				men=res.getString("ad");
			}
			res.close();
			sta.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return men;
	}
	
	public static void setWordOfMonth(String msg){
		try {
			sta = conn.createStatement();
			sta.executeUpdate("update members set ayinsozu='"+msg+"' where id="+Login.logId);			
			sta.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getWordOfMonth(){
		String word="";
		try {
			sta = conn.createStatement();
			res = sta.executeQuery("SELECT ayinsozu FROM members WHERE isKing=1");
			while ( res.next() ) {
				word=res.getString("ayinsozu");
			}
			res.close();
			sta.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return word;
	}
	
}
