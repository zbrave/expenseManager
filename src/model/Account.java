package model;

public class Account {

		private int ID;
		private int balance;
		
		public Account(int ID){
			this.ID=ID;
		}
		
		public void setBalance(int balance){
			this.balance=balance;
		}
		
		public int getBalance(){
			return balance;
		}
}
