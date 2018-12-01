package User;

public abstract class Account {
	protected float Amount;
	protected String Account_id;
	protected String Pname;
	protected String Branch;
	protected char Status;
	
	Account(){
		this.Amount = 0;
		this.Account_id = "";
		this.Pname = "";
		this.Branch = "";
		this.Status = '1';
	}
	
	Account(String Account_id, String TaxID, float Amount, String Branch, char status){
		this.Amount = Amount;
		this.Account_id = Account_id;
		this.Pname = TaxID;
		this.Branch = Branch;
		this.Status = status;
	}
	
	public float getAmount() {
		return this.Amount;
	} 
	
	public String getAccount() {
		return this.Account_id;
	}
	
	public String getPname() {
		return this.Pname;
	}
	
	public String getBranch() {
		return this.Branch;
	}
	
	public char getStatus() {
		return this.Status;
	}
	
	public void setAmount(float Amount) {
		this.Amount = Amount;
	}
	
	public void setAccount(String Account_id) {
		this.Account_id = Account_id;
	}
	
	public void setPname(String Pname) {
		this.Pname = Pname;
	}
	
	public void setBranch(String Branch) {
		this.Branch = Branch;
	}
	
	public void setStatus(char Status) {
		this.Status = Status;
	}
	
}
