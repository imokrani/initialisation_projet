package org.cnam.cloud.wsrest.model;

import org.restlet.resource.ClientResource;

public class ApprobationDemandeAccesBal {

	
	   public final ClientResource cr = new ClientResource(
			    EngineConfiguration.gae_path + "/rest/approbationDemandeAccesBal");

		    public ApprobationDemandeAccesBal() {
		    	EngineConfiguration.getInstance();
		    }
		    
		    
			public void approbationAccesBAL(String idClient, String idLivreur) {
				final ApprobationDemandeAccesBalInterface uci = cr
			    		.wrap(ApprobationDemandeAccesBalInterface.class);
				
			
				
			 	cr.getReference().addQueryParameter("idClient", idClient);
				cr.getReference().addQueryParameter("idLivreur", idLivreur);
				        uci.approbationAccesBAL();
			}
}
