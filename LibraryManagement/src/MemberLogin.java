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
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MemberLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JPasswordField txtPassword;
	private JTextField txtNameSignup;
	private JPasswordField txtPasswordSignup;
	private Intro intro;
	private MemberPage memPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberLogin frame = new MemberLogin();
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
	public MemberLogin() {
		intro = new Intro();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 524, 482);
		contentPane.add(panel);
		
		JLabel lblMemberLoginSignup = new JLabel("Member Login/Sign-up");
		lblMemberLoginSignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemberLoginSignup.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMemberLoginSignup.setBounds(20, 11, 471, 64);
		panel.add(lblMemberLoginSignup);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Member> mems = intro.getMembers();
				String name = txtName.getText(), pass = new String(txtPassword.getPassword());
				boolean foundName = false;
				for(int i=0; i<mems.size(); i++) {
					if(name.equals(mems.get(i).getFirstName()+" "+mems.get(i).getLastName())) {
						foundName = true;
						if(pass.equals(mems.get(i).getPassword())) {
							memPage = new MemberPage(mems.get(i));
							dispose();
							memPage.setVisible(true);
							JOptionPane.showMessageDialog(null, "Successfully logged in as " + memPage.getMember().getFirstName() + " " +memPage.getMember().getLastName());
						}
						else {
							JOptionPane.showMessageDialog(null, "Password incorrect");
							txtName.setText(""); txtPassword.setText("");
						}
					}
				}
				if(!foundName) {
					JOptionPane.showMessageDialog(null, "Member with name "+name+" does not exist.");
					txtName.setText(""); txtPassword.setText("");
				}
			}
		});
		btnLogin.setBounds(288, 234, 89, 23);
		panel.add(btnLogin);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(133, 140, 67, 23);
		panel.add(lblName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(133, 174, 102, 33);
		panel.add(lblPassword);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogin.setBounds(221, 86, 73, 43);
		panel.add(lblLogin);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(281, 140, 107, 20);
		panel.add(txtName);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(281, 182, 107, 20);
		panel.add(txtPassword);
		
		JLabel lblSignup = new JLabel("Sign Up");
		lblSignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignup.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSignup.setBounds(221, 284, 73, 43);
		panel.add(lblSignup);
		
		JLabel lblNameSingup = new JLabel("Name");
		lblNameSingup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNameSingup.setBounds(133, 338, 67, 23);
		panel.add(lblNameSingup);
		
		txtNameSignup = new JTextField();
		txtNameSignup.setColumns(10);
		txtNameSignup.setBounds(281, 338, 107, 20);
		panel.add(txtNameSignup);
		
		txtPasswordSignup = new JPasswordField();
		txtPasswordSignup.setBounds(281, 380, 107, 20);
		panel.add(txtPasswordSignup);
		txtName.setText("");txtPassword.setText("");txtNameSignup.setText("");txtPasswordSignup.setText("");
		
		JLabel lblPasswordSignup = new JLabel("Password");
		lblPasswordSignup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPasswordSignup.setBounds(133, 372, 102, 33);
		panel.add(lblPasswordSignup);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Member> mems = intro.getMembers();
				String name = txtNameSignup.getText(), pass = new String(txtPasswordSignup.getPassword());
				boolean found = false;
				for(int i=0; i<mems.size(); i++) {
					if(name.equals(mems.get(i).getFirstName()+" "+mems.get(i).getLastName())) {
						found = true;
						break;
					}
				}
				if(found) {
					JOptionPane.showMessageDialog(null, "Member with name "+name+" already exists");
					txtNameSignup.setText("");
					txtPasswordSignup.setText("");
				}
				else {
					String[] t = name.split(" ");
					intro.addMember(new Member(t[0],t[1],pass));
					JOptionPane.showMessageDialog(null, "New member " + name + " was added! Log in now!");
					txtNameSignup.setText("");
					txtPasswordSignup.setText("");
					System.out.println(intro.getAdmins());
					System.out.println(intro.getMembers());
					System.out.println(intro.getBooks());
					System.out.println("******");
				}
			}
		});
		btnSignUp.setBounds(215, 432, 89, 23);
		panel.add(btnSignUp);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Member> mems = intro.getMembers();
				String name = JOptionPane.showInputDialog("Enter your name");
				int index = -1;
				for(int i=0; i<mems.size(); i++) {
					if(name.equals(mems.get(i).getFirstName()+" "+mems.get(i).getLastName())) {
						index = i;
						break;
					}
				}
				if(index==-1) {
					JOptionPane.showMessageDialog(null, "Member with name " + name + " does not exist.");
					actionPerformed(e);
				}
				else {
					String pass = JOptionPane.showInputDialog("Enter your new password");
					String[] t = name.split(" ");
					intro.setMember(index, new Member(t[0],t[1],pass));
					JOptionPane.showMessageDialog(null, "Password successfully reset!");
					System.out.println(intro.getAdmins());
					System.out.println(intro.getMembers());
					System.out.println(intro.getBooks());
					System.out.println("******");
				}
			}
		});
		btnForgotPassword.setBounds(122, 234, 142, 23);
		panel.add(btnForgotPassword);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				intro.setVisible(true);
			}
		});
		btnHome.setBounds(425, 448, 89, 23);
		panel.add(btnHome);
	}
}