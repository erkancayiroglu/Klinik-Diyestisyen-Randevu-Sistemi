package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import Helper.*;

import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {

	private JPanel w_pane;

	/**
	 * Launch the application.
	 */
	static Bashekim bashekim=new Bashekim();
	 static Appointment appointment = new Appointment();
	 static Hasta hasta = new Hasta();
	
	Clinic clinic =new Clinic();
	
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JTextField fld_dPass;
	private JTextField fld_doctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel =null; 
	private Object[] doctorData=null;
	private JTable table_clinic;
	private int selectDoctorID= 0;
	private DefaultTableModel clinicModel=null;
	private Object[] clinicData= null;
	private JTextField fld_clinicName;
	private JPopupMenu clinicMenu;
	private JTable table_worker;
	private JTable table_appo;
	private DefaultTableModel appoModel=null;
	private Object[] appoData=null;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		//Doktor Model
		doctorModel =new DefaultTableModel();
		Object[] colDoctorName= new Object[4];
		colDoctorName[0]= "ID";
		colDoctorName[1]= "Ad Soyad";
		colDoctorName[2]= "T.C. No";
		colDoctorName[3]= "Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData= new Object[4];
		for(int i=0; i< bashekim.getDoctorList().size();i++) {
			doctorData[0]= bashekim.getDoctorList().get(i).getId();
			doctorData[1]= bashekim.getDoctorList().get(i).getName();
			doctorData[2]= bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]= bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
			
			
		}
		
		appoModel = new DefaultTableModel();
		Object[] colAppoName = new Object[6];
		colAppoName[0] = "ID";
		colAppoName[1] = "Doctor ID";
		colAppoName[2] = "Hasta ID";
		colAppoName[3] = "Doktor AD";
		colAppoName[4] = "Hasta AD";
		colAppoName[5] = "Tarih";
		appoModel.setColumnIdentifiers(colAppoName);

		appoData = new Object[6];
		for (int i = 0; i < appointment.getAppoList().size(); i++) {
		    appoData[0] = appointment.getAppoList().get(i).getId();
		    appoData[1] = appointment.getAppoList().get(i).getDoctorID();
		    appoData[2] = appointment.getAppoList().get(i).getHastaID();
		    appoData[3] = appointment.getAppoList().get(i).getDoctorName();
		    appoData[4] = appointment.getAppoList().get(i).getHastaName();
		    appoData[5] = appointment.getAppoList().get(i).getAppDate();
		    appoModel.addRow(appoData);
		}
		
		
		//Clinic Model
				clinicModel =new DefaultTableModel();
				Object[] colClinic= new Object[2];
				colClinic[0]= "ID";
				colClinic[1]= "Alan Ad";
				
				clinicModel.setColumnIdentifiers(colClinic);
				clinicData= new Object[2];
				for(int i=0; i< clinic.getList().size();i++) {
					clinicData[0]= clinic.getList().get(i).getId();
					clinicData[1]= clinic.getList().get(i).getName();
					
					clinicModel.addRow(clinicData);
					
				}
		//workerModel
				
				DefaultTableModel workerModel = new DefaultTableModel();
				Object[] colWorker = new Object[2];
				colWorker[0]="ID";
				colWorker[1]="Ad Soyad";
				workerModel.setColumnIdentifiers(colWorker);
				Object[] workerData =new Object[2];
				
				
				
				
				
		
		setTitle("Klinik Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 495);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HoşGeldiniz, Sayın  " + bashekim.getName());
		lblNewLabel.setBounds(26, 30, 316, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login =new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(584, 27, 113, 43);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(26, 89, 685, 356);
		w_pane.add(w_tab);
		
		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(Color.WHITE);
		w_tab.addTab("Diyetisyen Yönetimi", null, w_doctor, null);
		w_doctor.setLayout(null);
		
		JLabel label = new JLabel("Ad Soyad");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(21, 11, 110, 23);
		w_doctor.add(label);
		
		fld_dName = new JTextField();
		fld_dName.setBounds(21, 45, 121, 23);
		w_doctor.add(fld_dName);
		fld_dName.setColumns(10);
		
		JLabel label_1 = new JLabel("T.C No");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(21, 68, 121, 23);
		w_doctor.add(label_1);
		
		fld_dTcno = new JTextField();
		fld_dTcno.setBounds(21, 91, 121, 23);
		w_doctor.add(fld_dTcno);
		fld_dTcno.setColumns(10);
		
		JLabel label_2 = new JLabel("Şifre");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(21, 120, 121, 23);
		w_doctor.add(label_2);
		
		fld_dPass = new JTextField();
		fld_dPass.setBounds(21, 148, 121, 23);
		w_doctor.add(fld_dPass);
		fld_dPass.setColumns(10);
		
		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_dName.getText().length()==0 || fld_dPass.getText().length()==0 || fld_dTcno.getText().length()==0) {
					Helper.showMsg("fiil");
				}else { 
					try {
						boolean control=bashekim.addDoctor(fld_dTcno.getText(),fld_dPass.getText(),fld_dName.getText());
						if(control) {
						Helper.showMsg("success");
						fld_dName.setText(null);
						fld_dTcno.setText(null);
						fld_dPass.setText(null);
						updateDoctorModel();
						
						
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
			}
		 }
		});
		btn_addDoctor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_addDoctor.setBounds(21, 182, 121, 29);
		w_doctor.add(btn_addDoctor);
		
		JLabel label_3 = new JLabel("Kullanıcı ID");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(21, 215, 86, 23);
		w_doctor.add(label_3);
		
		fld_doctorID = new JTextField();
		fld_doctorID.setBounds(21, 249, 121, 23);
		w_doctor.add(fld_doctorID);
		fld_doctorID.setColumns(10);
		
		JButton btn_delDoctor = new JButton("Sil");
		btn_delDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorID.getText().length()==0) {
					Helper.showMsg("Lütfen Geçerli bir doktor seciniz");
					}else {
						if(Helper.confirm("sure")) {
							int selectID=Integer.parseInt(fld_doctorID.getText());
							try {
								boolean control =bashekim.deleteDoctor(selectID);
								if(control) {
									Helper.showMsg("success");
									fld_doctorID.setText(null);
									updateDoctorModel();
																	
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
			}
		});
		btn_delDoctor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_delDoctor.setBounds(21, 283, 121, 34);
		w_doctor.add(btn_delDoctor);
		
		JScrollPane w_scrollDoctor = new JScrollPane(); 
		w_scrollDoctor.setBounds(154, 11, 516, 306);
		w_doctor.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		
		
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(),0) .toString());

				}catch (Exception ex) {
					
				}
			}
			
		
		});
		table_doctor.getModel().addTableModelListener(new TableModelListener () {
			
	        @Override
			public void tableChanged(TableModelEvent e) {
			if(e.getType() == TableModelEvent.UPDATE) {
				int selectID=Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				String selectName=table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
				String 	selectTcno=table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
				String  selectPass=table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
			
			
			try {
				boolean control= bashekim.updateDoctor(selectID, selectTcno,  selectPass,selectName);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
				
			}
	});
		
		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tab.addTab("Polikinlik", null, w_clinic, null);
		w_clinic.setLayout(null);
		
		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 11, 237, 306);
		w_clinic.add(w_scrollClinic);
		
		clinicMenu  =new JPopupMenu();
		JMenuItem updateMenu =new JMenuItem("Güncelle");
		JMenuItem deleteMenu =new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);	
		
		updateMenu.addActionListener(new ActionListener() {
			
			@Override
			
		public void actionPerformed(ActionEvent e) {
				
				int selID =Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic  selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
					try {
						updateClinicModel();
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
						
					}
						
				
				
				});	
			
			}	
			
		});	
		deleteMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Helper.confirm("sure")) {
					int selID =Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
					if(clinic.deleteClinic(selID)) {
						Helper.showMsg("success");
						updateClinicModel();
						
					}else {
						Helper.showMsg("error");
					}
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
		
		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				Point point = e.getPoint();
				int selectedRow =table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
			
			}
			
			
			
		});
		w_scrollClinic.setViewportView(table_clinic);
		
		JLabel lbl_poliklinikAd = new JLabel("Uzmanlık Alanlar ");
		lbl_poliklinikAd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_poliklinikAd.setBounds(257, 6, 132, 23);
		w_clinic.add(lbl_poliklinikAd);
		
		
		
		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(399, 11, 260, 306);
		w_clinic.add(w_scrollWorker);
		
		table_worker = new JTable(workerModel);
		w_scrollWorker.setViewportView(table_worker);
		
		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_clinicName.getText().length()==0) {
					Helper.showMsg("fiil");
						
				}else {
					try {
						if(clinic.addClinic(fld_clinicName.getText())){
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();

}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				
				
				
			}
		});
		btn_addClinic.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_addClinic.setBounds(257, 79, 132, 29);
		w_clinic.add(btn_addClinic);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(257, 45, 132, 23);
		w_clinic.add(fld_clinicName);
		
		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(258, 213, 131, 34);
		w_clinic.add(select_doctor);
		
		JButton btn_addWorker = new JButton("Doktor - Alan Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow =table_clinic.getSelectedRow();
				if(selRow >= 0) {
					String selClinic= table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID =Integer.parseInt(selClinic);
					Item doctorItem =(Item) select_doctor.getSelectedItem();
					try {
						boolean control =bashekim.addWorker(doctorItem.getKey(), selClinicID);
						
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for(int i=0;i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
								workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();					
								workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();		
								workerModel.addRow(workerData);
							}
							table_worker.setModel(workerModel);
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					Helper.showMsg("Lütfen bir Polikinlik seçiniz!");
					                                                    
				}
				
			}
		});
		btn_addWorker.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btn_addWorker.setBounds(257, 269, 132, 29);
		for(int i=0; i< bashekim.getDoctorList().size(); i++) {
			select_doctor.addItem(new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}
		select_doctor.addActionListener(e -> {
			JComboBox c =(JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			
		});
		
		w_clinic.add(btn_addWorker);
		
		JLabel lblAlanAd = new JLabel("Alan Adı");
		lblAlanAd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlanAd.setBounds(257, 128, 132, 23);
		w_clinic.add(lblAlanAd);
		
		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if(selRow >= 0) {
					String selClinic =table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID=Integer.parseInt(selClinic);
					DefaultTableModel clearModel= (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0;i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
							workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();					
							workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();		
							workerModel.addRow(workerData);
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				}else {
					Helper.showMsg("Lütfen Bir Polikinlik Seçin");
					
				}
			}
			});
		btn_workerSelect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_workerSelect.setBounds(257, 162, 132, 29);
		w_clinic.add(btn_workerSelect);
		
		JPanel panel = new JPanel();
		w_tab.addTab("Hasta Randevuları", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 660, 277);
		panel.add(scrollPane);
		
		table_appo = new JTable(appoModel);
		scrollPane.setViewportView(table_appo);
		table_appo.setModel(appoModel);
		updateAppoModel();
		
		JButton btn_bring = new JButton("Güncel Randevular");
		btn_bring.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_bring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					table_appo.setModel(appoModel);
					updateAppoModel();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btn_bring.setBounds(88, 294, 169, 23);
		panel.add(btn_bring);
		
		JButton btnNewButton_1 = new JButton("Randevu İptal et");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow= table_appo.getSelectedRow();
        		if(selRow >= 0) {
        		String date = table_appo.getModel().getValueAt(selRow, 2).toString();
        		String selectRow =table_appo.getModel().getValueAt(selRow, 0).toString();
        		int selID= Integer.parseInt(selectRow);
        		
        		
        		try {

        			boolean control=hasta.deleteAppointment(selID);
        			if(control) {
        				Helper.showMsg("success");
        				
        				
        			
        		
						hasta.updateWhourStatuss(selectDoctorID, date);
						updateAppoModel();
        			
        			}else {
        				Helper.showMsg("error");
        			}
        		}catch (SQLException e1) {
        			e1.printStackTrace();
        		}
        		}else {
        			Helper.showMsg("Silmek istediğiniz Bir randevu seçiniz!");
        		}
        	}
        });
	
			
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(397, 296, 195, 27);
		panel.add(btnNewButton_1);
		
	}
		
		
	
	

  		public void updateDoctorModel() throws SQLException {
  			DefaultTableModel clearModel= (DefaultTableModel) table_doctor.getModel();
  			clearModel.setRowCount(0);
  			for(int i=0; i< bashekim.getDoctorList().size();i++) {
  				doctorData[0]= bashekim.getDoctorList().get(i).getId();
  				doctorData[1]= bashekim.getDoctorList().get(i).getName();
  				doctorData[2]= bashekim.getDoctorList().get(i).getTcno();
  				doctorData[3]= bashekim.getDoctorList().get(i).getPassword();
  				doctorModel.addRow(doctorData);
  				
  				
  			}
  			
  		}
  		
  		public void updateClinicModel() throws SQLException {
  			DefaultTableModel clearModel= (DefaultTableModel) table_clinic.getModel();
  			clearModel.setRowCount(0);
  			
  			for(int i=0; i< clinic.getList().size();i++) {
				clinicData[0]= clinic.getList().get(i).getId();
				clinicData[1]= clinic.getList().get(i).getName();
				
				clinicModel.addRow(clinicData);
  			
  		}
  		
  		
  		
  		}
  		
  		public void updateAppoModel() throws SQLException {
  			DefaultTableModel clearModel= (DefaultTableModel) table_appo.getModel();
  			clearModel.setRowCount(0);
  			for(int i=0; i< appointment.getAppoList().size();i++) {
  				appoData[0]= appointment.getAppoList().get(i).getId();
  				appoData[1]= appointment.getAppoList().get(i).getDoctorID();
  				appoData[2]= appointment.getAppoList().get(i).getHastaID();
  				appoData[3]= appointment.getAppoList().get(i).getDoctorName();
  				appoData[4]= appointment.getAppoList().get(i).getHastaName();
  				appoData[5]= appointment.getAppoList().get(i).getAppDate();
  			
  				appoModel.addRow(appoData);
  				
  				
  			}
  			
}
}
