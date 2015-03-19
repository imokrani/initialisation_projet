package org.cnam.cloud.wsrest.model;

import org.cnam.cloud.wsrest.model.LivreursClients;
import org.restlet.resource.Put;

public interface DemandeAccesBoiteLettresInterface {

	@Put
	void create(LivreursClients livreursClients);
}
