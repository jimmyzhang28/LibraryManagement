import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ManageBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtSearchID;
	private JTextField txtDisplayID;
	private JTextField txtDisplayTitle;
	private JTextField txtDisplayAuthor;
	private Intro intro;
	private int index = 0;
	private DefaultListModel d;
	private AdministrationPage memPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageBook frame = new ManageBook();
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
	public ManageBook() {
		intro = new Intro();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblManageBooks = new JLabel("Admin: Manage Books");
		lblManageBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageBooks.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblManageBooks.setBounds(113, 11, 317, 38);
		contentPane.add(lblManageBooks);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(31, 91, 188, 186);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtID = new JTextField();
		txtID.setBounds(88, 29, 86, 20);
		panel.add(txtID);
		txtID.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(88, 60, 86, 20);
		panel.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setBounds(88, 95, 86, 20);
		panel.add(txtAuthor);
		txtAuthor.setColumns(10);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBookId.setBounds(-19, 32, 73, 14);
		panel.add(lblBookId);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setBounds(-19, 63, 73, 14);
		panel.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthor.setBounds(-19, 98, 73, 14);
		panel.add(lblAuthor);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intro.addBook(new Book(txtID.getText(),txtTitle.getText(),txtAuthor.getText()));
				JOptionPane.showMessageDialog(null, "Successfully added book!");
				txtID.setText(""); txtTitle.setText(""); txtAuthor.setText("");
				d.clear();
				for(Book b : intro.getBooks())
					d.addElement(b);
				System.out.println(intro.getAdmins());
				System.out.println(intro.getMembers());
				System.out.println(intro.getBooks());
				System.out.println("******");
			}
		});
		btnAddBook.setBounds(40, 137, 112, 23);
		panel.add(btnAddBook);
		
		JLabel lblAddBooks = new JLabel("Add Books");
		lblAddBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddBooks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddBooks.setBounds(71, 60, 112, 35);
		contentPane.add(lblAddBooks);
		
		JLabel lblChangeBookInfo = new JLabel("Remove Book");
		lblChangeBookInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeBookInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChangeBookInfo.setBounds(277, 60, 198, 35);
		contentPane.add(lblChangeBookInfo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(287, 91, 188, 186);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtSearchID = new JTextField();
		txtSearchID.setBounds(83, 57, 86, 20);
		panel_1.add(txtSearchID);
		txtSearchID.setColumns(10);
		
		JLabel lblBookId_1 = new JLabel("Book ID");
		lblBookId_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookId_1.setBounds(0, 60, 73, 14);
		panel_1.add(lblBookId_1);
		
		JButton btnRemoveBook = new JButton("Remove Book");
		btnRemoveBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean found = false;
				for(int i=0; i<intro.getBooks().size(); i++) {
					if(txtSearchID.getText().equals(intro.getBooks().get(i).getId())) {
						Book temp = intro.getBooks().get(i);
						for(int j=0; j<intro.getMembers().size(); j++) {
							if(intro.getMembers().get(j).equalsTo(temp.getOwner())) {
								int indexBook = 0;
								for(int k=0; k<intro.getMembers().get(j).getOwnedBooks().size(); i++) {
									if(intro.getMembers().get(j).getOwnedBooks().get(k).equalsTo(temp)) {
										indexBook = k; break;
									}
								}
								intro.getMembers().get(j).removeBook(indexBook); break;
							}
						}
						intro.removeBook(i); found = true;
						d.clear();
						for(Book b : intro.getBooks())
							d.addElement(b);
						JOptionPane.showMessageDialog(null, "Successfully removed book!");
						txtSearchID.setText("");
						System.out.println(intro.getAdmins());
						System.out.println(intro.getMembers());
						System.out.println(intro.getBooks());
						System.out.println("******");
						break;
					}
				}
				if(!found) {
					JOptionPane.showMessageDialog(null, "Book ID was not found");
					txtSearchID.setText("");
				}
			}
		});
		btnRemoveBook.setBounds(32, 120, 128, 23);
		panel_1.add(btnRemoveBook);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(272, 314, 230, 157);
		contentPane.add(scrollPane);
		
		d = new DefaultListModel();
		JList list = new JList(d);
		scrollPane.setViewportView(list);
		for(Book b : intro.getBooks())
			d.addElement(b);
		
		JLabel lblBookDisplay = new JLabel("Book Display");
		lblBookDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookDisplay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBookDisplay.setBounds(205, 276, 112, 35);
		contentPane.add(lblBookDisplay);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(22, 314, 240, 157);
		contentPane.add(panel_2);
		
		txtDisplayID = new JTextField();
		txtDisplayID.setColumns(10);
		txtDisplayID.setBounds(108, 11, 86, 20);
		panel_2.add(txtDisplayID);
		
		txtDisplayTitle = new JTextField();
		txtDisplayTitle.setColumns(10);
		txtDisplayTitle.setBounds(108, 42, 86, 20);
		panel_2.add(txtDisplayTitle);
		
		txtDisplayAuthor = new JTextField();
		txtDisplayAuthor.setColumns(10);
		txtDisplayAuthor.setBounds(108, 73, 86, 20);
		panel_2.add(txtDisplayAuthor);
		
		JLabel label = new JLabel("Book ID");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(-19, 14, 73, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Title");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(-9, 39, 63, 14);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("Author");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(-19, 76, 73, 14);
		panel_2.add(label_2);
		
		JRadioButton rdAvailable = new JRadioButton("Available");
		rdAvailable.setBounds(65, 100, 109, 23);
		panel_2.add(rdAvailable);
		
		JButton btnFirst = new JButton("F");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = 0;
				txtDisplayID.setText(intro.getBooks().get(index).getId());
				txtDisplayTitle.setText(intro.getBooks().get(index).getTitle());
				txtDisplayAuthor.setText(intro.getBooks().get(index).getAuthor());
				rdAvailable.setSelected(intro.getBooks().get(index).getOwner()==null);
			}
		});
		btnFirst.setBounds(10, 130, 54, 23);
		panel_2.add(btnFirst);
		
		JButton btnPrevious = new JButton("<");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(index==0) JOptionPane.showMessageDialog(null, "Reached limit");
				else {
					index--;
					txtDisplayID.setText(intro.getBooks().get(index).getId());
					txtDisplayTitle.setText(intro.getBooks().get(index).getTitle());
					txtDisplayAuthor.setText(intro.getBooks().get(index).getAuthor());
					rdAvailable.setSelected(intro.getBooks().get(index).getOwner()==null);
				}
			}
		});
		btnPrevious.setBounds(65, 130, 54, 23);
		panel_2.add(btnPrevious);
		
		JButton btnNext = new JButton(">");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(index==intro.getBooks().size()-1) JOptionPane.showMessageDialog(null, "Reached limit");
				else {
					index++;
					txtDisplayID.setText(intro.getBooks().get(index).getId());
					txtDisplayTitle.setText(intro.getBooks().get(index).getTitle());
					txtDisplayAuthor.setText(intro.getBooks().get(index).getAuthor());
					rdAvailable.setSelected(intro.getBooks().get(index).getOwner()==null);
				}
			}
		});
		btnNext.setBounds(118, 130, 54, 23);
		panel_2.add(btnNext);
		
		JButton btnLast = new JButton("L");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = intro.getBooks().size()-1;
				txtDisplayID.setText(intro.getBooks().get(index).getId());
				txtDisplayTitle.setText(intro.getBooks().get(index).getTitle());
				txtDisplayAuthor.setText(intro.getBooks().get(index).getAuthor());
				rdAvailable.setSelected(intro.getBooks().get(index).getOwner()==null);
			}
		});
		btnLast.setBounds(176, 130, 54, 23);
		panel_2.add(btnLast);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				memPage = new AdministrationPage(Intro.currAdmin);
				memPage.setVisible(true);
			}
		});
		btnBack.setBounds(425, 20, 89, 23);
		contentPane.add(btnBack);
	}
}