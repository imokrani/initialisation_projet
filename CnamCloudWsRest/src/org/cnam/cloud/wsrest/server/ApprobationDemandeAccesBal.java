package org.cnam.cloud.wsrest.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cnam.cloud.wsrest.model.Clients;
import org.cnam.cloud.wsrest.model.Livreurs;
import org.cnam.cloud.wsrest.model.LivreursClients;
import org.cnam.cloud.wsrest.model.TemplateDemandeLivreur;
import org.restlet.data.Form;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class ApprobationDemandeAccesBal  extends ServerResource implements ApprobationDemandeAccesBalInterface {

	@Override
	public void approbationAccesBAL() {
		// TODO Auto-generated method stub
		Form queryParams = getReference().getQueryAsForm();
		String idClient = queryParams.getFirstValue("idClient");
		String idLivreur = queryParams.getFirstValue("idLivreur");
		ObjectifyService.register(LivreursClients.class);
		Objectify ofy = ObjectifyService.begin();
		Query<LivreursClients> demandes = ofy.query(LivreursClients.class);
	
		demandes.filter("idClient", Long.parseLong(idClient)); 
		demandes.filter("idLivreur", Long.parseLong(idLivreur)); 
		for(LivreursClients lc: demandes) {
			lc.setApprobationClient(true);
			ofy.put(lc);
		}
	}

}
