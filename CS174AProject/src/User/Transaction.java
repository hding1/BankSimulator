package User;

public class Transaction {

	private String type;
	private String Aid1;
	private String Aid2;
	private String tid;
	private float Amount;
	private String Date;
	
	Transaction(){
		this.type = "";
		this.Date = "";
	}
	
	Transaction(String tid, String Date, String Aid1, String Aid2,  String type, float Amount){
		this.type = type;
		this.Aid1 = Aid1;
		this.Aid2 = Aid2;
		this.tid = tid;
		this.Amount = Amount;
		this.Date = Date;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getAid1() {
		return this.Aid1;
	}
	
	public String getAid2() {
		return this.Aid2;
	}
	
	public String gettid() {
		return this.tid;
	}
	
	public float getAmount() {
		return this.Amount;
	}
	
	public String getDate() {
		return this.Date;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setAid1(String Aid1) {
		this.Aid1 = Aid1;
	}
	
	public void setAid2(String Aid2) {
		this.Aid2 = Aid2;
	}
	
	public void settid(String tid) {
		this.tid = tid;
	}
	
	public void setAmount(float Amount) {
		this.Amount = Amount;
	}
	
	public void setDate(String Date) {
		this.Date = Date;
	}
	
}
