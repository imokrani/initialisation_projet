package org.cnam.cloud.wsrest.model;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface ClientServiceInterface {

	@Put
	 void create(Clients client);
	
	 @Get
	 ContainerClients connexionClient();
}
