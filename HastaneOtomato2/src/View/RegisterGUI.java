package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel passw;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle("Klinik Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		passw = new JPanel();
		passw.setBackground(Color.WHITE);
		passw.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(passw);
		passw.setLayout(null);
		
		JLabel label = new JLabel("Ad Soyad");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(10, 11, 110, 23);
		passw.add(label);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(10, 34, 264, 32);
		passw.add(fld_name);
		
		JLabel lblTcNumaras = new JLabel("T.C. Numarası");
		lblTcNumaras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTcNumaras.setBounds(10, 77, 110, 23);
		passw.add(lblTcNumaras);
		
		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 104, 264, 32);
		passw.add(fld_tcno);
		
		JLabel lblifre = new JLabel("Şifre");
		lblifre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblifre.setBounds(10, 147, 110, 23);
		passw.add(lblifre);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(10, 174, 264, 32);
		passw.add(fld_pass);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length()== 0 || fld_pass.getText().length()== 0 || fld_name.getText().length()==0) {
					Helper.showMsg("fiil");
				}else {
					try {
						boolean control =hasta.register(fld_tcno.getText(),fld_pass.getText(),fld_name.getText());
						if(control) {
							Helper.showMsg("success");
							LoginGUI login =new LoginGUI();
							login.setVisible(true);
							dispose();
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btn_register.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_register.setBounds(10, 217, 264, 29);
		passw.add(btn_register);
		
		JButton btn_backto = new JButton("Geri Dön");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login =new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_backto.setBounds(10, 252, 264, 34);
		passw.add(btn_backto);
	}
}
