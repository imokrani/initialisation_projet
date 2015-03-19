package org.cnam.cloud.wsrest.server;

import java.util.ArrayList;
import java.util.List;

import org.cnam.cloud.wsrest.model.Clients;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class GetAllClientsLivreur  extends ServerResource implements GetAllClientsLivreurInterafce {

	@Override
	public ContainerClients getClientsLivreurs() {
		// TODO Auto-generated method stub
		ContainerClients content = null;
		 List clients = new ArrayList();
		 ObjectifyService.register(Clients.class);
		 Objectify ofy = ObjectifyService.begin();
		 
		 

		 Query<Clients> cs = ofy.query(Clients.class);
		for (Clients c : cs)
			clients.add(c);


		 content = new ContainerClients();
		 content.setClients_list(clients);

		 return content;
	}

}
