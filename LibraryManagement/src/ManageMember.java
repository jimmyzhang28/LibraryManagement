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
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageMember extends JFrame {

	private JPanel contentPane;
	private JTextField txtMemberName;
	private JPasswordField txtMemberPassword;
	private Intro intro;
	private AdministrationPage memPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageMember frame = new ManageMember();
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
	public ManageMember() {
		intro = new Intro();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminManageMembers = new JLabel("Admin: Manage Members");
		lblAdminManageMembers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminManageMembers.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblAdminManageMembers.setBounds(111, 11, 317, 38);
		contentPane.add(lblAdminManageMembers);
		
		JLabel lblAddMember = new JLabel("Add Member");
		lblAddMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddMember.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddMember.setBounds(221, 60, 112, 35);
		contentPane.add(lblAddMember);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(129, 94, 270, 179);
		contentPane.add(panel);
		
		txtMemberName = new JTextField();
		txtMemberName.setColumns(10);
		txtMemberName.setBounds(130, 29, 91, 20);
		panel.add(txtMemberName);
		
		JLabel lblMemberName = new JLabel("Member Name");
		lblMemberName.setHorizontalAlignment(SwingConstants.LEFT);
		lblMemberName.setBounds(10, 32, 99, 14);
		panel.add(lblMemberName);
		
		JLabel lblMemberPassword = new JLabel("Member Password");
		lblMemberPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMemberPassword.setBounds(-15, 94, 132, 14);
		panel.add(lblMemberPassword);
		
		txtMemberPassword = new JPasswordField();
		txtMemberPassword.setBounds(130, 91, 91, 20);
		panel.add(txtMemberPassword);
		
		JLabel lblRemoveMember = new JLabel("Remove Member");
		lblRemoveMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveMember.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRemoveMember.setBounds(181, 284, 198, 35);
		contentPane.add(lblRemoveMember);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 324, 229, 147);
		contentPane.add(scrollPane);
		
		DefaultListModel d=  new DefaultListModel();
		JList list = new JList(d);
		scrollPane.setViewportView(list);
		for(Member m : intro.getMembers())
			d.addElement(m);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				memPage = new AdministrationPage(Intro.currAdmin);
				memPage.setVisible(true);
			}
		});
		btnBack.setBounds(425, 448, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnRemoveSelectedMember = new JButton("Remove Selected Member");
		btnRemoveSelectedMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Member selected = (Member) list.getSelectedValue();
				for(int i=0; i<selected.getOwnedBooks().size(); i++)
					selected.getOwnedBooks().get(i).setOwner(null);
				int index = -1;
				for(int i=0; i<intro.getMembers().size(); i++) {
					if(selected.equalsTo(intro.getMembers().get(i))) {
						index = i; break;
					}
				}
				intro.removeMember(index);
				d.removeElementAt(list.getSelectedIndex());
				JOptionPane.showMessageDialog(null, "Successfully removed member!");
				System.out.println(intro.getAdmins());
				System.out.println(intro.getMembers());
				System.out.println(intro.getBooks());
				System.out.println("******");
			}
		});
		btnRemoveSelectedMember.setBounds(318, 381, 196, 23);
		contentPane.add(btnRemoveSelectedMember);
		
		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtMemberName.getText(), pass = new String(txtMemberPassword.getPassword());
				boolean found = false;
				for(Member m : intro.getMembers()) {
					if(name.equals(m.getFirstName()+" "+m.getLastName())) {
						JOptionPane.showMessageDialog(null, "Member with this name already exists!");
						found = true;
					}
				}
				if(!found) {
					String[] names = name.split(" ");
					Member m = new Member(names[0],names[1],pass);
					intro.addMember(m);
					d.addElement(m);
					JOptionPane.showMessageDialog(null, "Succesfully added member!");
					System.out.println(intro.getAdmins());
					System.out.println(intro.getMembers());
					System.out.println(intro.getBooks());
					System.out.println("******");
				}
			}
		});
		btnAddMember.setBounds(57, 145, 112, 23);
		panel.add(btnAddMember);
	}
}