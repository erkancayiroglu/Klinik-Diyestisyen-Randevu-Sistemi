package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;

import Helper.Helper;
import Model.Doctor;

public class DoctorGUI extends JFrame {
    private JPanel w_pane;
    private static Doctor doctor = new Doctor();
    private JTable table_whour;
    private DefaultTableModel whourModel;
    private Object[] whourData = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DoctorGUI frame = new DoctorGUI(doctor);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * 
     * @throws SQLException
     */
    public DoctorGUI(Doctor doctor) throws SQLException {
        // Çalışma saatleri için tablo modeli oluşturulur
        whourModel = new DefaultTableModel();
        Object[] colWhour = new Object[2];
        colWhour[0] = "ID";
        colWhour[1] = "Tarih";
        whourModel.setColumnIdentifiers(colWhour);

        // Çalışma saatleri verileri için dizi oluşturulur
        whourData = new Object[2];

        // Doktorun çalışma saatlerini alıp tablo modeline eklenir
        for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
            whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
            whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
            whourModel.addRow(whourData);
        }
        setTitle("Klinik Yönetim Sistemi");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        w_pane = new JPanel();
        w_pane.setBackground(Color.WHITE);
        w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(w_pane);
        w_pane.setLayout(null);

        JLabel label = new JLabel("HoşGeldiniz, Sayın  " + doctor.getName());
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label.setBounds(24, 14, 536, 37);
        w_pane.add(label);

        JButton button = new JButton("Çıkış Yap");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoginGUI login =new LoginGUI();
				login.setVisible(true);
				dispose();
        		
        	}
        });
        button.setFont(new Font("Tahoma", Font.PLAIN, 16));
        button.setBounds(584, 11, 113, 43);
        w_pane.add(button);

        JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
        w_tab.setBounds(21, 77, 685, 356);
        w_pane.add(w_tab);

        JPanel w_whour = new JPanel();
        w_whour.setBackground(Color.WHITE);
        w_tab.addTab("Çalışma Saatleri", null, w_whour, null);
        w_whour.setLayout(null);

        JDateChooser select_date = new JDateChooser();
        select_date.setBounds(54, 11, 109, 20);
        w_whour.add(select_date);

        JComboBox select_time = new JComboBox();
        select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00"}));
        select_time.setBounds(187, 11, 68, 20);
        w_whour.add(select_time);

        JButton btn_addWhour = new JButton("EKLE");
        btn_addWhour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = "";
                try {
                    date = sdf.format(select_date.getDate());
                } catch (Exception e2) {
                    // TODO : handle exception
                }
                if (date.length() == 0) {
                    Helper.showMsg("Lütfen geçerli bir tarih giriniz");
                } else {
                    String time = " " + select_time.getSelectedItem().toString() + ":00";
                    String selectDate = date + time;
                    try {
                        boolean control = doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
                        if (control) {
                            Helper.showMsg("success");
                            updateWhourModel(doctor);
                        } else {
                            Helper.showMsg("error");
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btn_addWhour.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btn_addWhour.setBounds(293, 11, 73, 20);
        w_whour.add(btn_addWhour);

        JScrollPane w_scrollWhour = new JScrollPane();
        w_scrollWhour.setBounds(10, 38, 660, 279);
        w_whour.add(w_scrollWhour);

        table_whour = new JTable(whourModel);
        w_scrollWhour.setViewportView(table_whour);
      
        updateWhourModel(doctor);
        
        JButton btn_deleteWhour = new JButton("SİL");
        btn_deleteWhour.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selRow= table_whour.getSelectedRow();
        		if(selRow >= 0) {
        		String selectRow =table_whour.getModel().getValueAt(selRow, 0).toString();
        		int selID= Integer.parseInt(selectRow);
        		boolean control;
        		try {
        			control=doctor.deleteWhour(selID);
        			if(control) {
        				Helper.showMsg("success");
        				updateWhourModel(doctor);
        			}else {
        				Helper.showMsg("error");
        			}
        		}catch (SQLException e1) {
        			e1.printStackTrace();
        		}
        		}else {
        			Helper.showMsg("Lütfen Bir tarih seçiniz!");
        		}
        	}
        });
        btn_deleteWhour.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btn_deleteWhour.setBounds(583, 11, 73, 20);
        w_whour.add(btn_deleteWhour);
    }

    public void updateWhourModel(Doctor doctor) throws SQLException {
        DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
        clearModel.setRowCount(0);
        for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
            whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
            whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
            whourModel.addRow(whourData);
        }
    }
}