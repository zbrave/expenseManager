package model;

public class Debt {
	
	private int who;
	private int toWho;
	private float debt;
	
	public Debt(int who, int toWho, float debt) {
		this.who = who;
		this.toWho = toWho;
		this.debt = debt;
	}

	public int getWho() {
		return who;
	}

	public void setWho(int who) {
		this.who = who;
	}

	public int getToWho() {
		return toWho;
	}

	public void setToWho(int toWho) {
		this.toWho = toWho;
	}

	public float getDebt() {
		return debt;
	}

	public void setDebt(float debt) {
		this.debt = debt;
	}
	
	
	
}
