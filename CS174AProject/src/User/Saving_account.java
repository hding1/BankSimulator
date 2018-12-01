package User;

public class Saving_account extends Account{
	
	private float Interest_rate;
	
	Saving_account(){
		super();
		Interest_rate = 7.5f;
	}
	
	Saving_account(String Account_id, String TaxID, float Amount, String Branch, char branch,float IR){
		super(Account_id, TaxID, Amount, Branch, branch);
		Interest_rate = IR;
	}
	
	public float getIntere_rate() {
		return this.Interest_rate;
	}
	
	public void changeIR(float ir) {
		Interest_rate = ir;
	}
	
}
