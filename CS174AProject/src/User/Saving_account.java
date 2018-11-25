package User;

public class Saving_account extends Account{
	
	private final double Interest_rate = 0.075;
	
	Saving_account(){
		super();
	}
	
	Saving_account(double Amount, int Account_id, String Pname, String Branch){
		super(Amount, Account_id, Pname, Branch);
	}
	
	public double getIntere_rate() {
		return this.Interest_rate;
	}
	
}
