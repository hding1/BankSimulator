package User;

public class Pocket_account extends Account{

	private String LinkedAccount_ID;
	
	
	Pocket_account(String Account_id, String TaxID, float Amount, String Branch, char branch, String LinkedAccount_ID){
		super(Account_id, TaxID, Amount, Branch, branch);
		this.LinkedAccount_ID = LinkedAccount_ID;
	}
	
	public String getLinkedAccount_ID() {
		return this.LinkedAccount_ID;
	}
	
	public void setLinkedAcount_ID(String LinkedAccount_ID) {
		this.LinkedAccount_ID = LinkedAccount_ID;
	}
}
