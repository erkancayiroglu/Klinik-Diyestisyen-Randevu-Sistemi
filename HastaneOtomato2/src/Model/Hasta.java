package Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.Helper;

public class Hasta extends User {
	
	Connection con =conn.connDb();
	Statement st= null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;

	public Hasta() {
		
	}

	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		// TODO Auto-generated constructor stub
	}
	
	public boolean register(String tcno, String password, String name) throws SQLException {
	    boolean success = false;

	    try (CallableStatement cstmt = con.prepareCall("{call RegisterUser(?, ?, ?)}")) {
	        cstmt.setString(1, tcno);
	        cstmt.setString(2, password);
	        cstmt.setString(3, name);
	        cstmt.execute();

	        try (ResultSet rs = cstmt.getResultSet()) {
	            if (rs.next()) {
	                String message = rs.getString("Message");
	                if (message.equals("success")) {
	                    success = true;
	                    Helper.showMsg("success");
	                } else {
	                    Helper.showMsg(message);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return success;
	}
	






	
	public boolean addAppointment(int hasta_id, int doctor_id, String doctor_name, String hasta_name, String app_date) throws SQLException {
	    int key = 0;
	    String query = "INSERT INTO appointment (doctor_id, doctor_name, hasta_id, hasta_name, app_date) VALUES (?, ?, ?, ?, ?)";

	    try {
	        if (app_date.startsWith("2023")) {
	            preparedStatement = con.prepareStatement(query);
	            
	            preparedStatement.setInt(1, doctor_id);
	            preparedStatement.setString(2, doctor_name);
	            preparedStatement.setInt(3, hasta_id);
	            preparedStatement.setString(4, hasta_name);
	            preparedStatement.setString(5, app_date);
	            
	            preparedStatement.executeUpdate();
	            key = 1;
	        } else {
	            // 2023 değilse ekleme yapma, key değeri 0 olarak kalır
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return key == 1;
	}
	
	public boolean updateWhourStatus(int doctor_id, String wdate) throws SQLException {
	    int key = 0;

	    String query = "UPDATE whour SET status = ? WHERE doctor_id = ? AND wdate = ?";

	    try {
	        preparedStatement = con.prepareStatement(query);
	        preparedStatement.setString(1, "p");
	        preparedStatement.setInt(2, doctor_id);
	        preparedStatement.setString(3, wdate);

	        key = preparedStatement.executeUpdate();  // Sorguyu çalıştır ve etkilenen satır sayısını key değişkenine ata

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return key > 0;  // Etkilenen satır sayısı 0'dan büyükse true döndür, aksi halde false döndür
	}
	
public boolean deleteAppointment(int id) throws SQLException {
		
		String query="DELETE FROM appointment WHERE id=?";	
		boolean key=false;
			try {                                                                        //bvene eklediöm
				st=con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();
				key=true;
			
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
		if(key)
			return true;
		else
			return false;
			

		
		}
public boolean updateWhourStatuss(int doctor_id, String wdate) throws SQLException {
    int key = 0;

    String query = "UPDATE whour SET status = ? WHERE doctor_id = ? AND wdate = ?";

    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, "a");
        preparedStatement.setInt(2, doctor_id);
        preparedStatement.setString(3, wdate);

        key = preparedStatement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return key > 0;
}
}
