package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Bashekim extends User{
	
	Connection con =conn.connDb();
	Statement st= null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;

	public Bashekim(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		
	}
	public Bashekim () {}
	
	public ArrayList<User>getDoctorList() throws SQLException {
		ArrayList<User> list= new ArrayList<>();
		
		
		
		User obj;
		try {
			st=con.createStatement();
			rs =st.executeQuery("SELECT *FROM kullanıcılar WHERE type='doktor'");
			while(rs.next()) {
			
				obj=new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return list;
		}																																	//düzenleme
	public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException {
	    ArrayList<User> list = new ArrayList<>();

	    try (PreparedStatement st = con.prepareStatement("SELECT k.id, k.password, k.name, k.type, k.tcno FROM worker w LEFT JOIN kullanıcılar k ON w.user_id = k.id WHERE clinic_id = ? AND clinic_id>=1")) {
	        st.setInt(1, clinic_id);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            User obj = new User(rs.getInt("id"), rs.getString("password"), rs.getString("name"), rs.getString("type"), rs.getString("tcno"));
	            list.add(obj);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Hata yönetimini geliştirerek uygun bir şekilde kullanıcıya hata mesajı gönderebilirsiniz.
	    }

	    return list;
	}
	
	public boolean addDoctor(String tcno, String password, String name) throws SQLException {
		
	String query="INSERT INTO kullanıcılar"+"(tcno,password,name,type) VALUES "+"(?,?,?,?)";	
	boolean key=false;
		try {
			st=con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
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
	
	public boolean deleteDoctor(int id) throws SQLException {
		
		String query="DELETE FROM kullanıcılar WHERE id=?";	
		boolean key=false;
			try {
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
	
public boolean updateDoctor(int id,String tcno, String password, String name) throws SQLException {
		
		String query="UPDATE kullanıcılar SET name=?, tcno=?, password=?  WHERE id=?";	
		boolean key=false;
			try {
				st=con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, tcno);
				preparedStatement.setString(3, password);
				preparedStatement.setInt(4, id);
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


public boolean addWorker(int user_id, int clinic_id) throws SQLException {
	
	String query="INSERT INTO worker"+"(user_id,clinic_id) VALUES "+"(?,?)";	
	boolean key=false;
	int count=0;
		try {
			st=con.createStatement();
			rs= st.executeQuery("SELECT * FROM worker WHERE clinic_id=" + clinic_id + "AND user_id=" +user_id);
			while(rs.next()){
				count++;
			}
			if(count==0) {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1,user_id);
			preparedStatement.setInt(2, clinic_id);
			preparedStatement.executeUpdate();
			}
			key=true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	if(key)
		return true;
	else
		return false;
		

	
	}


	
	
}
