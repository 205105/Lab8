package it.polito.tdp.metroparis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.metroparis.model.Connessione;
import it.polito.tdp.metroparis.model.Fermata;

public class FermataDAO {
	
	private List<Fermata> fermate=new LinkedList<Fermata>();
	
	public List<Fermata> caricaFermate(){
		
		Connection conn= DBConnect.getConnection();
		PreparedStatement st;
		try {
			String sql="select * from fermata";
			st=conn.prepareStatement(sql);
			ResultSet res=st.executeQuery(sql);
			while(res.next()){
				fermate.add(new Fermata(res.getInt("id_fermata"), res.getString("nome"), res.getDouble("coordX"), res.getDouble("coordY")));
			}res.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fermate;
	}

}
