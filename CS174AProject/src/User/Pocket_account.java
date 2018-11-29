package User;

public class Pocket_account extends Account{

	private int LinkedAccount_ID;
	
	Pocket_account(){
		super();
		this.LinkedAccount_ID = 0;
	}
	
	Pocket_account(String Account_id, String TaxID, float Amount, String Branch, char branch, int LinkedAccount_ID){
		super(Account_id, TaxID, Amount, Branch, branch);
		this.LinkedAccount_ID = LinkedAccount_ID;
	}
	
	public int getLinkedAccount_ID() {
		return this.LinkedAccount_ID;
	}
	
	public void setLinkedAcount_ID(int LinkedAccount_ID) {
		this.LinkedAccount_ID = LinkedAccount_ID;
	}
}
