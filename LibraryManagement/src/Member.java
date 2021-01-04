import java.util.*;
public class Member {
	private String firstName, lastName, password;
	private ArrayList<Book> ownedBooks;
	public Member(String f, String l, String p) {
		firstName = f; lastName = l; password = p; ownedBooks = new ArrayList<Book>();
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
		return firstName + " " + lastName;
	}
	public ArrayList<Book> getOwnedBooks() {
		return ownedBooks;
	}
	public void addBook(Book b) {
		ownedBooks.add(b);
	}
	public void removeBook(int index) {
		ownedBooks.remove(index);
	}
	public boolean equalsTo(Member m) {
		return firstName.equals(m.firstName) && lastName.equals(m.lastName) && password.equals(m.password);
	}
}
