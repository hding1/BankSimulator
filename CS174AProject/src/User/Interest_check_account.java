package User;

public class Interest_check_account extends Account{

	private float Interest_rate;
	
	
	public Interest_check_account(String Account_id, String TaxID, float Amount, String Branch, char status, float ir){
		super(Account_id, TaxID, Amount, Branch, status);
		Interest_rate = ir;
	}
	
	public float getInterest_rate() {
		return this.Interest_rate;
	}
	
	public void changeIR(float ir) {
		Interest_rate = ir;
	}
}
