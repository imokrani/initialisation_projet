package org.cnam.cloud.wsrest.model;

import org.restlet.resource.ClientResource;

import android.util.Log;

public class ClientService {

    public final ClientResource cr = new ClientResource(
    	    EngineConfiguration.gae_path + "/rest/client");
    
    public ClientService(){
    	EngineConfiguration.getInstance();
    }
    
    
    public void create(Clients client) throws Exception {
	final ClientServiceInterface uci = cr
		.wrap(ClientServiceInterface.class);

	try {
	    uci.create(client);
	    Log.i("ClientService", "Creation success !");
	} catch (Exception e) {
	    Log.i("ClientService", "Creation failed !");
	    e.printStackTrace();
	    throw e;
	}
    }
    
    public ContainerClients connexionClient(String mail , String motPasse) {
     	final ClientServiceInterface uci = cr
	    		.wrap(ClientServiceInterface.class);
		cr.getReference().addQueryParameter("mail", mail);
		cr.getReference().addQueryParameter("motPasse", motPasse);
		ContainerClients content = uci.connexionClient();
		return content; 
    }
    
}
