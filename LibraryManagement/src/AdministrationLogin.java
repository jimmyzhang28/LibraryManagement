import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdministrationLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JPasswordField txtPassword;
	private static Intro intro;
	private static AdministrationPage adPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrationLogin frame = new AdministrationLogin();
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
	public AdministrationLogin() {
		intro = new Intro();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdministrationLogin = new JLabel("Administration Login");
		lblAdministrationLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrationLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdministrationLogin.setBounds(26, 11, 471, 64);
		contentPane.add(lblAdministrationLogin);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Admin> ads = intro.getAdmins();
				String name = txtName.getText(), pass = new String(txtPassword.getPassword());
				boolean foundName = false;
				for (int i = 0; i < ads.size(); i++) {
					if (name.equals(ads.get(i).getFirstName() + " " + ads.get(i).getLastName())) {
						foundName = true;
						if (pass.equals(ads.get(i).getPassword())) {
							adPage = new AdministrationPage(ads.get(i));
							dispose();
							adPage.setVisible(true);
							JOptionPane.showMessageDialog(null,
									"Successfully logged in as " + Intro.currAdmin.getFirstName() + " " + Intro.currAdmin.getLastName());
						} else {
							JOptionPane.showMessageDialog(null, "Password incorrect");
							txtName.setText("");
							txtPassword.setText("");
						}
					}
				}
				if (!foundName) {
					JOptionPane.showMessageDialog(null, "Admin with name " + name + " does not exist.");
					txtName.setText("");
					txtPassword.setText("");
				}
			}
		});
		btnLogin.setBounds(208, 279, 89, 23);
		contentPane.add(btnLogin);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(128, 175, 67, 23);
		contentPane.add(lblName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(128, 209, 102, 33);
		contentPane.add(lblPassword);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogin.setBounds(213, 104, 73, 43);
		contentPane.add(lblLogin);

		txtName = new JTextField();
		txtName.setBounds(276, 175, 107, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(276, 217, 107, 20);
		contentPane.add(txtPassword);
		txtName.setText("");
		txtPassword.setText("");

		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				intro.setVisible(true);
			}
		});
		btnHome.setBounds(425, 448, 89, 23);
		contentPane.add(btnHome);

	}
}
