package it.polito.tdp.metroparis.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metroparis.db.ConnessioneDAO;
import it.polito.tdp.metroparis.db.FermataDAO;
import it.polito.tdp.metroparis.db.LineaDAO;


public class MetroModel {
	
	FermataDAO daoF=new FermataDAO();
	LineaDAO daoL=new LineaDAO();
	ConnessioneDAO daoC=new ConnessioneDAO();
	
	private List<Fermata> fermate=new LinkedList<Fermata>(daoF.caricaFermate());
	private List<Linea> linee=new LinkedList<Linea>(daoL.caricaLinee());
	private List<Connessione> connessioni=new LinkedList<Connessione>(daoC.caricaConnessioni(fermate, linee));
	
	
	
	public SimpleWeightedGraph<Fermata, DefaultWeightedEdge> stationGraph = new SimpleWeightedGraph<Fermata, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	//public SimpleDirectedWeightedGraph<Fermata, DefaultWeightedEdge> stationGraph = new SimpleDirectedWeightedGraph<Fermata, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	
	public List<String> caricaNomeFermate(){
		List<String> f=new LinkedList<String>();
		for(Fermata a: fermate)
			f.add(a.getNome());
		return f;
	}

	public void run(){
		//carico i vertici
		for(Fermata f: fermate){
			stationGraph.addVertex(f);
		}
		
		//carico gli archi con i pesi
		for(Fermata p: fermate){
			for(Connessione c: connessioni ){
				if(c.getStazP().getId()==p.getId()){
					for(Linea l: linee){
						if(c.getLinea().getId()==l.getId())
							Graphs.addEdge(stationGraph, c.getStazP(), c.getStazA(), (calcolaDistanza(p, c.getStazA())/l.getVelocità())*3600);
						//il peso è in ore, per trasformarlo in secondi moltiplico per 3600, per trasformarlo in minuti moltiplico per 60
					}
				}
			}
		}
		
	}
	
	public double calcolaDistanza(Fermata partenza, Fermata arrivo){
		return LatLngTool.distance(new LatLng(partenza.getX(), partenza.getY()), new LatLng(arrivo.getX(), arrivo.getY()), LengthUnit.KILOMETER);
	}
	
	public GraphPath<Fermata, DefaultWeightedEdge> dijkstra(String partenza, String arrivo){
		Fermata p=null;
		Fermata a=null;
		for(Fermata f: fermate)
			if(f.getNome().compareTo(partenza)==0)
				p=f;
		for(Fermata f: fermate)
			if(f.getNome().compareTo(arrivo)==0)
				a=f;
		DijkstraShortestPath<Fermata, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<Fermata, DefaultWeightedEdge>(stationGraph, p, a);
		return dijkstra.getPath();
	}
	
	public double calcolaTempo(String partenza, String arrivo){ //è la somma del peso degli archi
		double totale=0.0;
		for(DefaultWeightedEdge e: dijkstra(partenza, arrivo).getEdgeList()){
			totale+=stationGraph.getEdgeWeight(e);
		}
		return totale;
	}
	
	public List<Fermata> getCammino(String partenza, String arrivo){
		return Graphs.getPathVertexList(dijkstra(partenza, arrivo));
	} 
}
