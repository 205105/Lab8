package it.polito.tdp.metroparis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.metroparis.model.Connessione;
import it.polito.tdp.metroparis.model.Fermata;
import it.polito.tdp.metroparis.model.Linea;

public class ConnessioneDAO {
	
	private List<Connessione> connessioni=new LinkedList<Connessione>();
	
	public List<Connessione> caricaConnessioni(List<Fermata> fermate, List<Linea> linee){
		
		Connection conn= DBConnect.getConnection();
		PreparedStatement st;
		try {
			String sql="select * from connessione";
			st=conn.prepareStatement(sql);
			ResultSet res=st.executeQuery(sql);
			while(res.next()){
				Linea y=null;
				for(Linea r: linee){
					if(r.getId()==res.getInt("id_linea"))
						y=r;
				}
				Fermata p=null;
				for(Fermata partenza: fermate){
					if(partenza.getId()==res.getInt("id_stazP"))
						p=partenza;
				}
				Fermata a=null;
				for(Fermata arrivo: fermate){
					if(arrivo.getId()==res.getInt("id_stazA"))
						a=arrivo;
				}
				
				connessioni.add(new Connessione(res.getInt("id_connessione"), y, p, a));
			}res.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connessioni;
	}
	
	
}
