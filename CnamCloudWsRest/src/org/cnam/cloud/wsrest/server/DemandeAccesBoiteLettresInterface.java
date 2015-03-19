package org.cnam.cloud.wsrest.server;

import org.cnam.cloud.wsrest.model.LivreursClients;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface DemandeAccesBoiteLettresInterface {

	@Put
	void create(LivreursClients livreursClients);
	
	@Get
	ContainerTemplateDemandeLivreur getDemandesLivreurs(); 
	
	
}
