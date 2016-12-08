import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Panel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JLabel lblPassword;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	
	private static Server1 s;
	static String mataKuliah;
	static String namaDosen;
	static int id;
	private JPasswordField pfPass;
	private JLabel lblLecture;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Login() throws Exception {
		setTitle("Lecture Broadcast Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfUsername.setBounds(202, 30, 204, 40);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(92, 183, 314, 25);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(36, 30, 134, 40);
		contentPane.add(lblNewLabel);
		
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPassword.setBounds(36, 81, 134, 40);
		contentPane.add(lblPassword);
		
		JButton btnSignIn = new JButton("SIGN IN");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Database db=new Database();
					System.out.println(db.isConnected());
					if(db.cekLogin(tfUsername.getText(), pfPass.getText())==1){
						try {
							id=db.getID(tfUsername.getText(), pfPass.getText());
							ResultSet rs = db.getMataKuliah(id);
							while (rs.next()) {
								comboBox.addItem(rs.getString("NAMA_MATAKULIAH"));	
							}
							btnNewButton.setVisible(true);
							comboBox.setVisible(true);
							lblLecture.setVisible(true);
							btnSignIn.setEnabled(false);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Username atau Password salah silahkan coba lagi");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		

	
		btnSignIn.setBounds(146, 135, 260, 37);
		contentPane.add(btnSignIn);
		
		btnNewButton = new JButton("Mulai");
		btnNewButton.addActionListener(new ActionListener() {
			Database db = new Database();
			public void actionPerformed(ActionEvent arg0) {
				mataKuliah=comboBox.getSelectedItem().toString();
				try {
					namaDosen=db.getNamaDosen(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
/*				try {
					s=new Server1();
				} catch (IOException | AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				try {
					Server1 s = new Server1();
					Server1.main(null);
					
					
					//Server1.main(null);
				} catch (IOException | AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(146, 219, 260, 28);
		contentPane.add(btnNewButton);
		
		pfPass = new JPasswordField();
		pfPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pfPass.setBounds(202, 81, 204, 40);
		contentPane.add(pfPass);
		
		lblLecture = new JLabel("Lecture");
		lblLecture.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLecture.setBounds(36, 173, 65, 40);
		contentPane.add(lblLecture);
		btnNewButton.setVisible(false);
		comboBox.setVisible(false);
		lblLecture.setVisible(false);
	}
	
	public static String retMataKuliah(){
		return mataKuliah;
	}
	public static String retNamaDosen(){
		return namaDosen;
	}
}
