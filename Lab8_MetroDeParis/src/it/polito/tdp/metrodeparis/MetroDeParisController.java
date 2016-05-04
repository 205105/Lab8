package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.metroparis.model.Fermata;
import it.polito.tdp.metroparis.model.MetroModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	
	private List<String> nomeFermate=new LinkedList<String>();
	
	private MetroModel model=new MetroModel();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbPartenza;

    @FXML
    private ComboBox<String> cmbArrivo;

    @FXML
    private Button bttCalcola;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doCalcola(ActionEvent event) {
    	String partenza=cmbPartenza.getValue();
    	String arrivo=cmbArrivo.getValue();
    	String risultato="";
    	List<Fermata> f=new LinkedList<Fermata>(model.getCammino(partenza, arrivo));
    	double tempo=model.calcolaTempo(partenza, arrivo);
    	for(Fermata t: f){
    		risultato+=t.getNome()+", ";
    		tempo+=0.30; 
    	}
    	System.out.println(tempo);
    	int ora = (int) (tempo / 3600);
    	tempo = tempo - (ora*3600);
    	int minuti = (int) (tempo / 60);
    	tempo = tempo - (minuti*60);
    	int secondi = (int) tempo;
    	String sec="";
    	if(secondi<10){
    		sec="0"+secondi;
    	} else {
    		sec=""+secondi;
    	}
    	risultato+="\n"+"Tempo di percorrenza stimato: "+ora+":"+minuti+":"+sec;
    	txtRisultato.setText(risultato);
    }

    @FXML
    void initialize() {
        assert cmbPartenza != null : "fx:id=\"cmbPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert cmbArrivo != null : "fx:id=\"cmbArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert bttCalcola != null : "fx:id=\"bttCalcola\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        nomeFermate.addAll(model.caricaNomeFermate());
        cmbPartenza.getItems().addAll(nomeFermate);
        //cmbPartenza.setValue("Argentine");
        cmbArrivo.getItems().addAll(nomeFermate);
        model.run();
    }
}
