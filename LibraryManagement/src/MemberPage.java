import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberPage extends JFrame {

	private JPanel contentPane;
	private MemberLogin memLogin = new MemberLogin();
	private SearchBook s = new SearchBook();
	private BorrowReturn br;
	private EditProfile ep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberPage frame = new MemberPage(Intro.currMem);
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
	public MemberPage(Member m) {
		Intro.currMem = m;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the Library, " + Intro.currMem.getFirstName() + "!");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWelcomeToThe.setBounds(54, 11, 416, 52);
		contentPane.add(lblWelcomeToThe);
		
		JButton btnSearchBook = new JButton("Search Books");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				s.setVisible(true);
			}
		});
		btnSearchBook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearchBook.setBounds(175, 79, 187, 89);
		contentPane.add(btnSearchBook);
		
		JButton btnBorrowReturn = new JButton("Borrow/Return");
		btnBorrowReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				br = new BorrowReturn();
				dispose(); br.setVisible(true);
				
			}
		});
		btnBorrowReturn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBorrowReturn.setBounds(175, 193, 184, 89);
		contentPane.add(btnBorrowReturn);
		
		JButton btnEditProfile = new JButton("Edit Profile");
		btnEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ep = new EditProfile();
				dispose();
				ep.setVisible(true);
			}
		});
		btnEditProfile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditProfile.setBounds(175, 301, 184, 89);
		contentPane.add(btnEditProfile);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Intro.currMem = null;
				dispose();
				memLogin.setVisible(true);
				JOptionPane.showMessageDialog(null, "Succesfully logged out");
			}
		});
		btnLogout.setBounds(425, 448, 89, 23);
		contentPane.add(btnLogout);
	}
	
	public Member getMember() {
		return Intro.currMem;
	}
	
	public void setMember(Member m) {
		Intro.currMem = m;
	}
}