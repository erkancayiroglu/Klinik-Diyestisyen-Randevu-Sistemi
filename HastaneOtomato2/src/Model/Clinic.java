package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {
	
	
	private int id;
	private String name;
	
	DBConnection conn= new DBConnection();

	Statement st= null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;
	
	
	
	public Clinic() {}
	
	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public ArrayList<Clinic>getList() throws SQLException {
		ArrayList<Clinic> list= new ArrayList<>();
		Clinic obj;
		Connection con =conn.connDb();

		try {

			st=con.createStatement();
			rs= st.executeQuery("SELECT * FROM clinic" );
			while(rs.next()) {
				obj= new Clinic();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				list.add(obj);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			
			st.close();
			rs.close();
			con.close();
			
		}
 		
		
			return list;
		}	
	
	public Clinic getFetch(int id) {
		Connection con =conn.connDb();
		Clinic c = new Clinic();
        try {
			st=con.createStatement();
			   rs= st.executeQuery("SELECT* FROM clinic WHERE id= "+ id);
			   while(rs.next()) {
				  c.setId(rs.getInt("id"));
				  c.setName(rs.getString("name"));
				   break;
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
		return c;
		
	}
	
	
	
	
	public boolean addClinic( String name) throws SQLException {
		
		String query="INSERT INTO clinic"+"(name) VALUES "+"(?)";	
		boolean key=false;
		Connection con =conn.connDb();

			try {
				st=con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, name);
				
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
	
	
	public boolean deleteClinic(int id) throws SQLException {
		
		String query="DELETE FROM clinic WHERE id=?";	
		boolean key=false;
		Connection con =conn.connDb();

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
	
public boolean updateClinic(int id, String name) throws SQLException {
		
		String query="UPDATE clinic SET name=?  WHERE id=?";	
		boolean key=false;
		Connection con =conn.connDb();

			try {
				st=con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, id);
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
		
	  
public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException {
    ArrayList<User> list = new ArrayList<>();
	Connection con =conn.connDb();
    try (PreparedStatement st = con.prepareStatement("SELECT k.id, k.password, k.name, k.type, k.tcno FROM worker w LEFT JOIN kullanıcılar k ON w.user_id = k.id WHERE clinic_id = ?")) {
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


	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
