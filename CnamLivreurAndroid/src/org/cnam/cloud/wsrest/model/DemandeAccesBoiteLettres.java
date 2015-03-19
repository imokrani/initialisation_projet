package org.cnam.cloud.wsrest.model;

import org.restlet.resource.ClientResource;

import android.util.Log;

public class DemandeAccesBoiteLettres {

	   public final ClientResource cr = new ClientResource(
			    EngineConfiguration.gae_path + "/rest/demandeAccesBoiteLettres");

		    public DemandeAccesBoiteLettres() {
		    	EngineConfiguration.getInstance();
		    }
		    
		    public void create(LivreursClients livreursClients) throws Exception {
		    	final DemandeAccesBoiteLettresInterface uci = cr
		    		.wrap(DemandeAccesBoiteLettresInterface.class);

		    	try {
		    	    uci.create(livreursClients);
		    	    Log.i("DemandeAccesBoiteLettres", "Creation success !");
		    	} catch (Exception e) {
		    	    Log.i("DemandeAccesBoiteLettres", "Creation failed !");
		    	    e.printStackTrace();
		    	    throw e;
		    	}
		        }
}
