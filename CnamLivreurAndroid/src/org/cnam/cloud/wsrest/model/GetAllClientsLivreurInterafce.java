package org.cnam.cloud.wsrest.model;


import org.restlet.resource.Get;

public interface GetAllClientsLivreurInterafce {

	@Get
	public ContainerClients getClientsLivreurs() ; 
}

