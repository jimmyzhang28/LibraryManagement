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

public class AdministrationPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private AdministrationLogin adLogin = new AdministrationLogin();
	private ManageBook manBook;
	private ManageMember manMember;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrationPage frame = new AdministrationPage(Intro.currAdmin);
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
	public AdministrationPage(Admin a) {
		Intro.currAdmin = a;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLibraryAdministrativePrivileges = new JLabel(
				Intro.currAdmin.getFirstName() + " " + Intro.currAdmin.getLastName() + " Privileges");
		lblLibraryAdministrativePrivileges.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryAdministrativePrivileges.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblLibraryAdministrativePrivileges.setBounds(39, 11, 450, 52);
		contentPane.add(lblLibraryAdministrativePrivileges);

		JButton btnManageMembers = new JButton("Manage Members");
		btnManageMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				manMember = new ManageMember();
				manMember.setVisible(true);
			}
		});
		btnManageMembers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnManageMembers.setBounds(168, 110, 187, 89);
		contentPane.add(btnManageMembers);

		JButton btnManageBooks = new JButton("Manage Books");
		btnManageBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manBook = new ManageBook();
				dispose();
				manBook.setVisible(true);
			}
		});
		btnManageBooks.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnManageBooks.setBounds(171, 260, 184, 89);
		contentPane.add(btnManageBooks);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Intro.currAdmin = null;
				dispose();
				adLogin.setVisible(true);
				JOptionPane.showMessageDialog(null, "Successfully logged out");
			}
		});
		btnLogout.setBounds(425, 448, 89, 23);
		contentPane.add(btnLogout);
	}

}
