package p1;

public class Admin {

	String Email;
	String Password;
	String code;
	public Admin(String email, String password, String code) {
		super();
		Email = email;
		Password = password;
		this.code = code;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
