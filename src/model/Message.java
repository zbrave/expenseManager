/**
 * Mert AYDAR
 * 13011068
 * e-mail: mertaydar@outlook.com
 * 16 Tem 2016
 */
package model;

public class Message {
	private int uniqueID;
	private int fromID;
	private int toID;
	private String msg;
	private String date;
	private String name;

	
	public int getFromID() {
		return fromID;
	}
	public void setFromID(int fromID) {
		this.fromID = fromID;
	}
	public int getToID() {
		return toID;
	}
	public void setToID(int toID) {
		this.toID = toID;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String dt) {
		this.date = dt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	 
}
