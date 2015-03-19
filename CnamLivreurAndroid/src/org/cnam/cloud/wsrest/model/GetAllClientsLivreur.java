package org.cnam.cloud.wsrest.model;

import java.util.List;

import org.restlet.resource.ClientResource;

public class GetAllClientsLivreur {

	
	  public final ClientResource cr = new ClientResource(
			    EngineConfiguration.gae_path + "/rest/getClientsLivreur");

		    public GetAllClientsLivreur() {
			     EngineConfiguration.getInstance();
		    }
		    
		    
		    public List<Clients> getClientsLivreurs()  {
		    	final GetAllClientsLivreurInterafce uci = cr
		    			.wrap(GetAllClientsLivreurInterafce.class);
		    		ContainerClients content = uci.getClientsLivreurs();
		    		return content.getClients_list();
		    }
}
