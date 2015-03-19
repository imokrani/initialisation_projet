package org.cnam.cloud.wsrest.server;

import org.restlet.resource.Get;

public interface GetAllClientsLivreurInterafce {

	@Get
	public ContainerClients getClientsLivreurs() ; 
}
