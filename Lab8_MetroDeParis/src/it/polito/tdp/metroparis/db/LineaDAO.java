package it.polito.tdp.metroparis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.metroparis.model.Linea;

public class LineaDAO {
	
private List<Linea> linee=new LinkedList<Linea>();
	
	public List<Linea> caricaLinee(){
		
		Connection conn= DBConnect.getConnection();
		PreparedStatement st;
		try {
			String sql="select * from linea";
			st=conn.prepareStatement(sql);
			ResultSet res=st.executeQuery(sql);
			while(res.next()){
				linee.add(new Linea(res.getInt("id_linea"), res.getString("nome"), res.getDouble("velocita"), res.getDouble("intervallo"), res.getString("colore")));
			}res.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return linee;
	}

}

