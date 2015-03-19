package org.cnam.livreur;

import org.cnam.cloud.wsrest.model.Clients;
import org.cnam.cloud.wsrest.model.Livreurs;
import org.cnam.cloud.wsrest.model.LivreursClients;

import android.app.Application;

public class VaiablesGlobales extends Application{

	
	private Livreurs livreurs;
	
	private Clients clients; 
	
	private LivreursClients livreursClients; 

	public Livreurs getLivreurs() {
		return livreurs;
	}

	public void setLivreurs(Livreurs livreurs) {
		this.livreurs = livreurs;
	}

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	public LivreursClients getLivreursClients() {
		return livreursClients;
	}

	public void setLivreursClients(LivreursClients livreursClients) {
		this.livreursClients = livreursClients;
	} 
    
	
	
}
