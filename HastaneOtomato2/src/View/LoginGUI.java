package View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Helper.*;
import Helper.Helper;
import Model.Doctor;
import Model.Hasta;

import java.sql.Statement;
import Model.Bashekim;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doctorTc;
	private DBConnection conn= new DBConnection(); 
	private JPasswordField fld_hastaPass;
	private JPasswordField fld_doctorPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("Klinik Yönetim Sitemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 446);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("3.PNG")));
		lbl_logo.setBounds(246, 25, 67, 82);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Sisteme Hoşgeliniz");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(189, 100, 230, 46);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 157, 579, 219);
		w_pane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTcKimlikNumaras = new JLabel("T.C. Kimlik Numarası");
		lblTcKimlikNumaras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTcKimlikNumaras.setBounds(20, 30, 155, 48);
		w_hastaLogin.add(lblTcKimlikNumaras);
		
		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblifre.setBounds(20, 107, 155, 48);
		w_hastaLogin.add(lblifre);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setBounds(185, 32, 221, 48);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  if(fld_hastaTc.getText().length()== 0 || fld_hastaPass.getText().length()== 0) {
					  Helper.showMsg("fiil");
				  }else {
					  boolean key=true;
						try {
							Connection con=conn.connDb();
							Statement st=con.createStatement();
							ResultSet rs= st.executeQuery("SELECT * FROM kullanıcılar");
							
							while(rs.next()) {
								 if(fld_hastaTc.getText().equals(rs.getString("tcno")) && fld_hastaPass.getText().equals(rs.getString("password"))) {
									 if(rs.getString("type").equals("hasta")) {
										 Hasta hasta= new Hasta(); 
										 hasta.setId(rs.getInt("id"));
											hasta.setPassword("password");
											hasta.setTcno(rs.getString("tcno"));
											hasta.setName(rs.getString("name"));
											hasta.setType(rs.getString("type"));
											HastaGUI hGUI=new HastaGUI(hasta);
											hGUI.setVisible(true);
											dispose();
											key =false;
									 }
							
								 }
							
							}
								
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						if(key) {
							Helper.showMsg("Böyle bir hasta bulunamdı lütfen kayıt olunuz!");
						}
				  }
			}
		});
		btn_hastaLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_hastaLogin.setBounds(430, 30, 114, 50);
		w_hastaLogin.add(btn_hastaLogin);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI =new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_register.setBounds(430, 108, 114, 51);
		w_hastaLogin.add(btn_register);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(185, 111, 221, 44);
		w_hastaLogin.add(fld_hastaPass);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JLabel lblTcKimlikNumaras_1 = new JLabel("T.C. Kimlik Numarası");
		lblTcKimlikNumaras_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTcKimlikNumaras_1.setBounds(10, 30, 155, 48);
		w_doctorLogin.add(lblTcKimlikNumaras_1);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(175, 32, 221, 48);
		w_doctorLogin.add(fld_doctorTc);
		
		JButton btn_doctorLogin = new JButton("Giriş Yap");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorTc.getText().length()== 0 || fld_doctorPass.getText().length()== 0) {
					Helper.showMsg("fiil");
					}else {
						 boolean key=true;
					
						try {
							Connection con=conn.connDb();
							Statement st=con.createStatement();
							ResultSet rs= st.executeQuery("SELECT * FROM kullanıcılar");
							while(rs.next()) {
								 if(fld_doctorTc.getText().equals(rs.getString("tcno")) && fld_doctorPass.getText().equals(rs.getString("password"))) {
									 if(rs.getString("type").equals("bashekim")) {
										 Bashekim bhekim= new Bashekim(); 
										 bhekim.setId(rs.getInt("id"));
											bhekim.setPassword("password");
											bhekim.setTcno(rs.getString("tcno"));
											bhekim.setName(rs.getString("name"));
											bhekim.setType(rs.getString("type"));
											BashekimGUI bGUI=new BashekimGUI(bhekim);
											bGUI.setVisible(true);
											dispose();
											key=false;
										 
									 }
									
									if(rs.getString("type").equals("doktor")) {
										Doctor doctor =new Doctor();
										doctor.setId(rs.getInt("id"));
										doctor.setPassword("password");
										doctor.setTcno(rs.getString("tcno"));
										doctor.setName(rs.getString("name"));
										doctor.setType(rs.getString("type"));
										DoctorGUI dGUI =new DoctorGUI(doctor);
										dGUI.setVisible(true);
										dispose();
										key=false;
									}
									 
									 
								 }
								
							
							}
							
							
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						if(key) {
							Helper.showMsg("Böyle bir Diyetisyen  bulunamadı lütfen yönetimle irtibata geçiniz!");
						}
						
					}
			}
		});
		btn_doctorLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_doctorLogin.setBounds(421, 60, 114, 50);
		w_doctorLogin.add(btn_doctorLogin);
		
		JLabel lblifre_1 = new JLabel("Şifre:");
		lblifre_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblifre_1.setBounds(10, 107, 155, 48);
		w_doctorLogin.add(lblifre_1);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(175, 109, 221, 48);
		w_doctorLogin.add(fld_doctorPass);
	}
}
