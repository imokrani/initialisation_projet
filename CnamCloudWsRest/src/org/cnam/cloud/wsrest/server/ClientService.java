package org.cnam.cloud.wsrest.server;

import java.util.ArrayList;
import java.util.List;

import org.cnam.cloud.wsrest.model.Clients;
import org.cnam.cloud.wsrest.model.Livreurs;
import org.restlet.data.Form;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class ClientService extends ServerResource implements ClientServiceInterface {

	@Override
	public void create(Clients client) {
		// TODO Auto-generated method stub
		 ObjectifyService.register(Clients.class);
		 Objectify ofy = ObjectifyService.begin();

		 Clients c = new Clients();
		 c.setNomClient(client.getNomClient());
		 c.setPrenomClient(client.getPrenomClient());
		 c.setMailClient(client.getMailClient());
		 c.setLoginClient(client.getLoginClient());
		 c.setMotPasseClient(client.getMotPasseClient());
		 c.setIdTelephoneClient(client.getIdTelephoneClient());
		 c.setAdresse(client.getAdresse());
		 c.setComplementAdresse(client.getComplementAdresse());
		 c.setCodePostal(client.getCodePostal());
		 c.setVille(client.getVille());
		 c.setNumeroBal(client.getNumeroBal());
		 
		 ofy.put(c);
	}

	@Override
	public ContainerClients connexionClient() {
		// TODO Auto-generated method stub
		
		/*
		 * recuperation des parametre de connexion 
		 */
		  Form queryParams = getReference().getQueryAsForm();
		 String mail = queryParams.getFirstValue("mail");
		 String motPasse = queryParams.getFirstValue("motPasse");
	  	 ContainerClients content = null;
		 List clients = new ArrayList();
		 ObjectifyService.register(Clients.class);
		 Objectify ofy = ObjectifyService.begin();
		 Query<Clients> cli = ofy.query(Clients.class);
		 cli.filter("mailClient", mail);
		 cli.filter("motPasseClient", motPasse);
		for (Clients cc : cli) {
			 //if((cc.getMailClient().equals(mail)) && (cc.getMotPasseClient().equals(motPasse))) 
			          clients.add(cc);
		}
		 content = new ContainerClients();
		 content.setClients_list(clients);
		 return content;
	}

}
