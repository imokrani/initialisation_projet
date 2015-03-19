package org.cnam.clients;

import org.cnam.cloud.wsrest.model.Clients;
import org.cnam.cloud.wsrest.model.TemplateDemandeLivreur;

import android.app.Application;

public class VaiablesGlobales extends Application{
     private Clients clients;
     private TemplateDemandeLivreur templateDemandeLivreur; 

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	public TemplateDemandeLivreur getTemplateDemandeLivreur() {
		return templateDemandeLivreur;
	}

	public void setTemplateDemandeLivreur(
			TemplateDemandeLivreur templateDemandeLivreur) {
		this.templateDemandeLivreur = templateDemandeLivreur;
	} 
     
     
}
