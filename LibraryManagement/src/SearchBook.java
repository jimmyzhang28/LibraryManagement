import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class SearchBook extends JFrame {

	private JPanel contentPane;
	private JList list;
	private JScrollPane scrollPane;
	private Intro intro;
	private JButton btnDisplay;
	private JTextField txtSearch;
	private Object[][] objsarr = null;
	private MemberPage memPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchBook frame = new SearchBook();
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
	public SearchBook() {
		intro = new Intro();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearchBooks = new JLabel("Search Books");
		lblSearchBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchBooks.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblSearchBooks.setBounds(104, 11, 317, 38);
		contentPane.add(lblSearchBooks);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(207, 77, 109, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		String[] options = {"All", "Book ID", "Title", "Author"};
		JComboBox comboBox = new JComboBox(options);
		comboBox.setBounds(50, 77, 126, 20);
		contentPane.add(comboBox);
		
		Object[] cols = {"Book ID","Title","Author"};
		ArrayList<String[]> objs = new ArrayList<String[]>(); 
		
		DefaultListModel d= new DefaultListModel();
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 134, 448, 260);
		contentPane.add(scrollPane);
		list = new JList(d);
		scrollPane.setViewportView(list);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Book> books = intro.getBooks();
				d.clear();
				switch((String)(comboBox.getSelectedItem())) {
				case "All": {
					for(Book b : books) 
						d.addElement((b.getOwner()==null ? "IN STOCK " : "OUT OF STOCK ") + b.toString());
					break;
				}
				case "Book ID": {
					String id = txtSearch.getText();
					for(Book b : books) 
						if(b.getId().equals(id)) 
							d.addElement((b.getOwner()==null ? "IN STOCK " : "OUT OF STOCK ") + b.toString());
					break;
				}
				case "Title": {
					String id = txtSearch.getText();
					for(Book b : books)
						if(b.getTitle().equals(id))
							d.addElement((b.getOwner()==null ? "IN STOCK " : "OUT OF STOCK ") + b.toString());
					break;
				}
				case "Author": {
					String id = txtSearch.getText();
					for(Book b : books) {
						if(b.getAuthor().equals(id))
							d.addElement((b.getOwner()==null ? "IN STOCK " : "OUT OF STOCK ") + b.toString());
					}
					break;
				}
				}
			}
		});
		btnDisplay.setBounds(351, 76, 89, 23);
		contentPane.add(btnDisplay);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				memPage = new MemberPage(Intro.currMem);
				memPage.setVisible(true);
			}
		});
		btnBack.setBounds(425, 448, 89, 23);
		contentPane.add(btnBack);
		
	}
}
