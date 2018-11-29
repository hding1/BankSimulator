package User;

public class Student_check_account extends Account{

	Student_check_account(){
		super();
	}
	
	Student_check_account(String Account_id, String TaxID, float Amount, String Branch, char branch,  String ir){
		super(Account_id, TaxID, Amount, Branch,branch);
	}
}
