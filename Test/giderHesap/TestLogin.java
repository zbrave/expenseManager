/**
 * Mert AYDAR
 * 13011068
 * e-mail: mertaydar@outlook.com
 * 12 Tem 2016
 */
package giderHesap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import junit.framework.Assert;

public class TestLogin {
	
	public static Connection baglanti;
	public static Statement sta;
	public static ResultSet res;	
	public static int logId;
	public static String logName;
	public static PreparedStatement ps;
	static String status;

	
	public static boolean Connect() {

		try{
		/*	String connectionString = "jdbc:sqlserver://zbrave.database.windows.net:1433;"
					+ "database=giderHesap;user=zbrave@zbrave;password=******;"
					+ "encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	*/
			String connectionString = "jdbc:sqlserver://localhost;" +
		               "databaseName=giderHesap;user=sa;password=*******;";
			baglanti = DriverManager.getConnection(connectionString);
			
			}
		catch (Exception e1){
			status="Servis yok.";
		}
		if (baglanti != null){
			return true;
		}
		else {
			return false;
		}
	}
	
	@Test
	public void testLog() {
		Assert.assertEquals(true, Connect());
	}
}
