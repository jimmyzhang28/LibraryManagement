import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditProfile extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JPasswordField txtOriginal;
	private JPasswordField txtNew;
	private JPasswordField txtConfirm;
	private Intro intro;
	private MemberPage memPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfile frame = new EditProfile();
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
	public EditProfile() {
		intro = new Intro();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditYourProfile = new JLabel("Edit Your Profile");
		lblEditYourProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditYourProfile.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblEditYourProfile.setBounds(109, 11, 317, 38);
		contentPane.add(lblEditYourProfile);
		
		txtName = new JTextField();
		txtName.setBounds(263, 101, 137, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(159, 104, 46, 14);
		contentPane.add(lblName);
		
		JButton btnEditName = new JButton("Edit Name");
		btnEditName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = txtName.getText().split(" ");
				Intro.currMem.setFirstName(arr[0]);
				Intro.currMem.setLastName(arr[1]);
				JOptionPane.showMessageDialog(null, "Succesfully updated your name!");
				txtName.setText("");
				
			}
		});
		btnEditName.setBounds(218, 160, 100, 23);
		contentPane.add(btnEditName);
		
		JLabel lblEnterOriginalPassword = new JLabel("Enter Original Password");
		lblEnterOriginalPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnterOriginalPassword.setBounds(10, 249, 195, 14);
		contentPane.add(lblEnterOriginalPassword);
		
		JLabel lblConfirmNewPasssword = new JLabel("Confirm New Password");
		lblConfirmNewPasssword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmNewPasssword.setBounds(30, 350, 175, 14);
		contentPane.add(lblConfirmNewPasssword);
		
		JLabel lblEnterNewPassword = new JLabel("Enter New Password");
		lblEnterNewPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnterNewPassword.setBounds(10, 297, 195, 14);
		contentPane.add(lblEnterNewPassword);
		
		txtOriginal = new JPasswordField();
		txtOriginal.setBounds(263, 246, 137, 20);
		contentPane.add(txtOriginal);
		
		txtNew = new JPasswordField();
		txtNew.setBounds(263, 294, 137, 20);
		contentPane.add(txtNew);
		
		txtConfirm = new JPasswordField();
		txtConfirm.setBounds(263, 347, 137, 20);
		contentPane.add(txtConfirm);
		
		JButton btnNewButton = new JButton("Change Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String orig = new String(txtOriginal.getPassword()), newPass = new String(txtNew.getPassword()), confirm = new String(txtConfirm.getPassword());
				if(orig.equals(Intro.currMem.getPassword())) {
					if(newPass.equals(confirm)) {
						Intro.currMem.setPassword(newPass);
						JOptionPane.showMessageDialog(null, "Your password was updated");
					}
					else {
						JOptionPane.showMessageDialog(null, "Confirmation does not match new password");
						txtOriginal.setText("");txtNew.setText("");txtConfirm.setText("");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong original password!");
					txtOriginal.setText("");txtNew.setText("");txtConfirm.setText("");
				}
			}
		});
		btnNewButton.setBounds(173, 405, 167, 23);
		contentPane.add(btnNewButton);
		
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