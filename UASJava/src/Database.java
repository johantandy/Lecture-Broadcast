import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
private Connection conn; //Declare datababse Connection
	
	public Database() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/dosen",
				"root",
				""
				);
	}
	
	public boolean isConnected(){

		return (conn != null);
	}
	
	public int cekLogin(String id,String pass) throws SQLException{
		ResultSet rs=null;
		int a=0;
		try {
			String query="Select count(*) from dosen where username= ? and password = ?";
			PreparedStatement pst=this.conn.prepareStatement(query);
			pst.setString(1, id);
			pst.setString(2, pass);
			rs=pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		while(rs.next()){
			 a=rs.getInt(1);
		}
		return a;
	}
	
	public int getID(String id,String pass) throws SQLException{
		ResultSet rs=null;
		int a=0;
		try {
			String query="Select ID_DOSEN from dosen where username= ? and password = ?";
			PreparedStatement pst=this.conn.prepareStatement(query);
			pst.setString(1, id);
			pst.setString(2, pass);
			rs=pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		while(rs.next()){
			 a=rs.getInt(1);
		}
		return a;
	}
	
	public ResultSet getMataKuliah(int id){
	    ResultSet rs = null;
	    try{
	        PreparedStatement stmt = conn.prepareStatement("SELECT NAMA_MATAKULIAH FROM MATA_KULIAH WHERE ID_DOSEN = ?");
	        stmt.setInt(1, id);
	        rs = stmt.executeQuery();
	    } catch (SQLException e){
	        e.printStackTrace();
	    }
	    return rs;  
	}
	
	public String getNamaDosen(int id) throws SQLException{
		ResultSet rs=null;
		String a=null;
		try {
			String query="Select NAMA_DOSEN from dosen where ID_DOSEN = ?";
			PreparedStatement pst=this.conn.prepareStatement(query);
			pst.setInt(1, id);
			rs=pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		while(rs.next()){
			 a=rs.getString(1);
		}
		return a;
	}
}

