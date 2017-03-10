package model;

import java.util.ArrayList;

public class Person {

	private int ID;
	private String name;
	private String nickname;
	private int msgCounter;
	private boolean isOnline;
	private House house;
	private Account account;
	
	public Person(){
		ID=-1;
		nickname=null;
		msgCounter=-1;
		isOnline=false;
	}
	
	public Person(int iD, String name, String nickname, int msgCounter, boolean isOnline, House house, Account account) {
		super();
		ID = iD;
		this.name = name;
		this.nickname = nickname;
		this.msgCounter = msgCounter;
		this.isOnline = isOnline;
		this.house = house;
		this.account = account;
	}

	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setHouse(House house){
		this.house=house;
	}
	
	public House getHouse(){
		return house;
	}
	
	public void setAcc(Account account){
		this.account=account;
	}
	
	public Account getAcc(){
		return account;
	}

	public String getNick() {
		return nickname;
	}

	public void setNick(String nickname) {
		this.nickname = nickname;
	}

	public int getMsgCounter() {
		return msgCounter;
	}

	public void setMsgCounter(int msgCounter) {
		this.msgCounter = msgCounter;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
