import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BorrowReturn extends JFrame {

	private JPanel contentPane;
	private Intro intro;
	private MemberPage memPage;
	private DefaultListModel d, ee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowReturn frame = new BorrowReturn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BorrowReturn() {
		intro = new Intro();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBorrowOrReturn = new JLabel("Borrow or Return a Book");
		lblBorrowOrReturn.setHorizontalAlignment(SwingConstants.CENTER);
		lblBorrowOrReturn.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblBorrowOrReturn.setBounds(109, 11, 317, 38);
		contentPane.add(lblBorrowOrReturn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 100, 447, 119);
		contentPane.add(scrollPane);
		
		d = new DefaultListModel();
		JList listAvailable = new JList(d);
		scrollPane.setViewportView(listAvailable);
		listAvailable.setBorder(new LineBorder(new Color(0, 0, 0)));
		ArrayList<Book> b = intro.getBooks();
		for(Book bb : b)
			if(bb.getOwner()==null)
				d.addElement(bb);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(44, 305, 447, 119);
		contentPane.add(scrollPane_1);
		
		ee = new DefaultListModel();
		JList listOwned = new JList(ee);
		listOwned.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setColumnHeaderView(listOwned);
		for(int i=0; i<Intro.currMem.getOwnedBooks().size(); i++)
			ee.addElement(Intro.currMem.getOwnedBooks().get(i));
		
		JLabel lblAvailableBooks = new JLabel("Available Books");
		lblAvailableBooks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAvailableBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvailableBooks.setBounds(200, 69, 135, 20);
		contentPane.add(lblAvailableBooks);
		
		JLabel lblBooksYouOwn = new JLabel("Books You Own");
		lblBooksYouOwn.setHorizontalAlignment(SwingConstants.CENTER);
		lblBooksYouOwn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBooksYouOwn.setBounds(200, 274, 135, 20);
		contentPane.add(lblBooksYouOwn);
		
		JButton btnGetSelectedBooks = new JButton("Get Selected Books");
		btnGetSelectedBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Book bookk = (Book) listAvailable.getSelectedValue();
				int index = listAvailable.getSelectedIndex();
				d.removeElementAt(index);
				ee.addElement(bookk);
				Intro.currMem.addBook(bookk);
				ArrayList<Book> b = intro.getBooks();
				for(int i=0; i<b.size(); i++) {
					if(bookk.equalsTo(b.get(i))) {
						intro.getBooks().get(i).setOwner(Intro.currMem);
						break;
					}
				}
				System.out.println(intro.getAdmins());
				System.out.println(intro.getMembers());
				System.out.println(intro.getBooks());
				System.out.println("******");
			}
		});
		btnGetSelectedBooks.setBounds(186, 230, 163, 23);
		contentPane.add(btnGetSelectedBooks);
		
		JButton btnReturnSelectedBooks = new JButton("Return Selected Books");
		btnReturnSelectedBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book bookk = (Book) listOwned.getSelectedValue();
				int index = listOwned.getSelectedIndex();
				ee.removeElementAt(index);
				d.addElement(bookk);
				for(int i=0; i<Intro.currMem.getOwnedBooks().size(); i++) {
					if(bookk.equalsTo(Intro.currMem.getOwnedBooks().get(i))) {
						Intro.currMem.removeBook(i); break;
					}
				}
				ArrayList<Book> b = intro.getBooks();
				for(int i=0; i<b.size(); i++) {
					if(bookk.equalsTo(b.get(i))) {
						intro.getBooks().get(i).setOwner(null);
						break;
					}
				}
				System.out.println(intro.getAdmins());
				System.out.println(intro.getMembers());
				System.out.println(intro.getBooks());
				System.out.println("******");
			}
		});
		btnReturnSelectedBooks.setBounds(171, 435, 195, 23);
		contentPane.add(btnReturnSelectedBooks);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				memPage = new MemberPage(Intro.currMem);
				memPage.setVisible(true);
			}
		});
		btnNewButton.setBounds(425, 448, 89, 23);
		contentPane.add(btnNewButton);

	}
}