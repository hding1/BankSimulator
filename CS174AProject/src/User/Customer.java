package User;

public class Customer {
	
	private String Name;
	private double TaxID;
	private String Address;
	private String Pin;
	
	Customer(){
		this.Name = "";
		this.TaxID = 0.0;
		this.Address = "";
	}
	
	Customer(String Name, double TaxID, String Address, String Pin){
		this.Name = Name;
		this.TaxID = TaxID;
		this.Address = Address;
		this.Pin = Pin;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public double getTaxID() {
		return this.TaxID;
	}
	
	public String getAddress() {
		return this.Address;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public void setTaxID(double TaxID) {
		this.TaxID = TaxID;
	}
	
	public void setAddress(String Address) {
		this.Address = Address;
	}
}
