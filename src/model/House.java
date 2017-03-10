package model;

import java.util.ArrayList;

public class House {
	
	private int ID;
	private String address;
	private ArrayList <Person> prs=new ArrayList <Person>();
	
	public House (int ID, String address){
		this.address=address;
		this.ID=ID;
	}
	
	public void setID(int ID){
		this.ID=ID;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setAdres(String address){
		this.address=address;
	}
	
	public String getAdres(){
		return address;
	}
	
	public void setPerson(Person prs){
		this.prs.add(prs);
	}
	
	public ArrayList<Person> getPerson(){
		return prs;
	}
}
