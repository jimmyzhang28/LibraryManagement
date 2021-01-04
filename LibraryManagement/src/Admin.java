public class Admin {
	private String firstName, lastName, password;
	public Admin(String f, String l, String p) {
		firstName = f; lastName = l; password = p;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String toString() {
		return firstName + " " + lastName + " " + password;
	}
	
}
