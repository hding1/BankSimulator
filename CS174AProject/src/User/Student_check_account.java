package User;

public class Student_check_account extends Account{
	private float Interest_rate;
	
	public Student_check_account(String Account_id, String TaxID, float Amount, String Branch, char status, float ir){
		super(Account_id, TaxID, Amount, Branch,status);
		Interest_rate = ir;
	}
	
	public float getIntere_rate() {
		return this.Interest_rate;
	}
	
	public void changeIR(float ir) {
		Interest_rate = ir;
	}
}
