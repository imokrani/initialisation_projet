package org.cnam.cloud.wsrest.server;

import org.cnam.cloud.wsrest.model.Clients;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface ClientServiceInterface {

	
	@Put
	 void create(Clients client);
	
	 @Get
	 ContainerClients connexionClient();
}
