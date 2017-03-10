package model;

public class ExpenseTable {
	
	private String person;
	private String type;
	private String description;
	private int piece;
	private float price;
	
	public ExpenseTable(String person, String type, String description, int piece, float price) {
		this.description=description;
		this.person=person;
		this.piece=piece;
		this.price=price;
		this.type=type;
	}
	
	public String getPerson() {
		return person;
	}
	
	public void setPerson(String person) {
		this.person = person;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPiece() {
		return piece;
	}
	
	public void setPiece(int piece) {
		this.piece = piece;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
