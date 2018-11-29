package User;

public class Interest_check_account extends Account{

	private final double Interest_rate = 0.055;
	
	Interest_check_account(){
		super();
	}
	
	Interest_check_account(String Account_id, String TaxID, float Amount, String Branch, char branch){
		super(Account_id, TaxID, Amount, Branch, branch);
	}
	
	public double getInterest_rate() {
		return this.Interest_rate;
	}
}
