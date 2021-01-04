import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import javafx.scene.paint.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.io.*;

public class Intro extends JFrame {

	private JPanel contentPane;
	private static ArrayList<Book> books = new ArrayList<Book>();
	private static ArrayList<Admin> admins = new ArrayList<Admin>();
	private static ArrayList<Member> members = new ArrayList<Member>();
	private static AdministrationLogin adLog;
	private static MemberLogin memLog;
	public static Member currMem = null;
	public static Admin currAdmin = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException {
		Scanner scan1 = new Scanner(new File("admins.txt"));
		int one = Integer.parseInt(scan1.nextLine());
		for(int i=0; i<one; i++) {
			String[] n = scan1.nextLine().split(" ");
			String pass = scan1.nextLine();
			admins.add(new Admin(n[0],n[1],pass));
		}
		Scanner scan2 = new Scanner(new File("members.txt"));
		int two = Integer.parseInt(scan2.nextLine());
		for(int i=0; i<two; i++) {
			String[] n = scan2.nextLine().split(" ");
			String pass = scan2.nextLine();
			members.add(new Member(n[0],n[1],pass));
		}
		Scanner scan3 = new Scanner(new File("books.txt"));
		int three = Integer.parseInt(scan3.nextLine());
		for(int i=0; i<three; i++) {
			String id = scan3.nextLine(), title = scan3.nextLine(), auth = scan3.nextLine();
			books.add(new Book(id,title,auth));
		}
		Scanner scan4 = new Scanner(new File("link.txt"));
		int pplWithBook = Integer.parseInt(scan4.nextLine());
		for(int i=0; i<pplWithBook; i++) {
			String[] memName = scan4.nextLine().split(" "); int index = 0;
			for(int j=0; j<members.size(); j++) {
				if(members.get(j).getFirstName().equals(memName[0]) && members.get(j).getLastName().equals(memName[1])) {
					index = j; break;
				}
			}
			int numBooks = Integer.parseInt(scan4.nextLine());
			for(int j=0; j<numBooks; j++) {
				String bookTitle = scan4.nextLine();
				for(int k=0; k<books.size(); k++) {
					if(bookTitle.equals(books.get(k).getTitle())) {
						members.get(index).addBook(books.get(k)); break;
					}
				}
			}
		}
		int booksWithPpl = Integer.parseInt(scan4.nextLine());
		for(int i=0; i<booksWithPpl; i++) {
			String bookTitle = scan4.nextLine(); String[] memName = scan4.nextLine().split(" ");
			int indexBook = 0, indexMember = 0;
			for(int j=0; j<members.size(); j++) {
				if(members.get(j).getFirstName().equals(memName[0]) && members.get(j).getLastName().equals(memName[1])) {
					indexMember = j; break;
				}
			}
			for(int j=0; j<books.size(); j++) {
				if(books.get(j).getTitle().equals(bookTitle)) {
					indexBook = j; break;
				}
			}
			books.get(indexBook).setOwner(members.get(indexMember));
		}
		scan1.close();scan2.close();scan3.close();scan4.close();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Intro frame = new Intro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws FileNotFoundException 
	 */
	public Intro() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		System.out.println(admins);
		System.out.println(members);
		System.out.println(books);
		System.out.println("******");
		
		JLabel lblCincoRanchLibrary = new JLabel("Cinco Ranch Library Management System");
		lblCincoRanchLibrary.setHorizontalAlignment(SwingConstants.CENTER);
		lblCincoRanchLibrary.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCincoRanchLibrary.setBounds(20, 11, 471, 64);
		contentPane.add(lblCincoRanchLibrary);
		
		JButton btnAdministrationLogin = new JButton("Administration Login");
		btnAdministrationLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				adLog = new AdministrationLogin();
				adLog.setVisible(true);
			}
		});
		btnAdministrationLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdministrationLogin.setBounds(147, 73, 210, 127);
		contentPane.add(btnAdministrationLogin);
		
		JButton btnMemberLogin = new JButton("Member Login");
		btnMemberLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				memLog = new MemberLogin();
				memLog.setVisible(true);
			}
		});
		btnMemberLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMemberLogin.setBounds(147, 216, 210, 127);
		contentPane.add(btnMemberLogin);
		
		JButton btnSaveClose = new JButton("Save and Close");
		btnSaveClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update text file
				try {
					FileWriter f1 = new FileWriter(new File("admins.txt"));
					FileWriter f2 = new FileWriter(new File("members.txt"));
					FileWriter f3 = new FileWriter(new File("books.txt"));
					FileWriter f4 = new FileWriter(new File("link.txt"));
					f1.write(Integer.toString(admins.size())+"\r\n");
					for(Admin a : admins) f1.write(a.getFirstName()+ " "+a.getLastName()+"\r\n"+a.getPassword()+"\r\n");
					f2.write(Integer.toString(members.size())+"\r\n");
					for(Member m : members) f2.write(m.getFirstName()+ " "+m.getLastName()+"\r\n"+m.getPassword()+"\r\n");
					f3.write(Integer.toString(books.size())+"\r\n");
					for(Book b : books) f3.write(b.getId()+"\r\n"+b.getTitle()+"\r\n"+b.getAuthor()+"\r\n");
					
					int peopleWhoHaveBooks = 0, booksThatHaveOwners = 0;
					for(Member m : members) if(m.getOwnedBooks().size()>0) peopleWhoHaveBooks++;
					for(Book b : books) if(b.getOwner()!=null) booksThatHaveOwners++;
					f4.write(Integer.toString(peopleWhoHaveBooks)+"\r\n");
					for(Member m : members) {
						if(m.getOwnedBooks().size()>0) {
							f4.write(m.getFirstName()+" "+m.getLastName()+"\r\n"+m.getOwnedBooks().size()+"\r\n");
							for(Book owned : m.getOwnedBooks()) {
								f4.write(owned.getTitle()+"\r\n");
							}
						}
					}
					f4.write(Integer.toString(booksThatHaveOwners)+"\r\n");
					for(Book b : books) {
						if(b.getOwner()!=null) {
							f4.write(b.getTitle()+"\r\n"+b.getOwner().getFirstName()+" "+b.getOwner().getLastName()+"\r\n");
						}
					}
					f1.close();f2.close();f3.close();f4.close();System.exit(0);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSaveClose.setBounds(160, 394, 182, 37);
		contentPane.add(btnSaveClose);
	}
	
	public ArrayList<Admin> getAdmins() {
		return admins;
	}
	public ArrayList<Book> getBooks() {
		return books;
	}
	public ArrayList<Member> getMembers() {
		return members;
	}
	public void addAdmin(Admin a) {
		admins.add(a);
	}
	public void addBook(Book b) {
		books.add(b);
	}
	public void removeBook(int index) {
		books.remove(index);
	}
	public void addMember(Member m) {
		members.add(m);
	}
	public void removeMember(int index) {
		members.remove(index);
	}
	public void setAdmin(int i, Admin a) {
		admins.set(i,a);
	}
	public void setBook(int i, Book b) {
		books.set(i,b);
	}
	public void setMember(int i, Member m) {
		members.set(i,m);
	}
}