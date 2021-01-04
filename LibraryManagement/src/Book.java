public class Book {
	private String id, title, author;
	private Member owner;
	public Book(String i, String t, String a) {
		id = i; title = t; author = a; owner = null;
	}
	public Member getOwner() {
		return owner;
	}
	public void setOwner(Member owner) {
		this.owner = owner;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String toString() {
		return id+" "+title + " by " + author;
	}
	public boolean equalsTo(Book b) {
		return id.equals(b.id) && title.equals(b.title) && author.equals(b.author);
	}
}