package User;

public class Pocket_account extends Account{

	private int LinkedAccount_ID;
	
	Pocket_account(){
		super();
		this.LinkedAccount_ID = 0;
	}
	
	Pocket_account(double Amount, int Account_id, String Pname, String Branch, int LinkedAccount_ID){
		super(Amount, Account_id, Pname, Branch);
		this.LinkedAccount_ID = LinkedAccount_ID;
	}
	
	public int getLinkedAccount_ID() {
		return this.LinkedAccount_ID;
	}
	
	public void setLinkedAcount_ID(int LinkedAccount_ID) {
		this.LinkedAccount_ID = LinkedAccount_ID;
	}
}
