package org.cnam.cloud.wsrest.model;

import java.util.List;

import org.restlet.resource.ClientResource;



public class DemandeAccesBoiteLettres {

	   public final ClientResource cr = new ClientResource(
			    EngineConfiguration.gae_path + "/rest/demandeAccesBoiteLettres");

		    public DemandeAccesBoiteLettres() {
		    	EngineConfiguration.getInstance();
		    }
		  
		public 	List<TemplateDemandeLivreur> getDemandesLivreurs(String idClient) {
		 	final DemandeAccesBoiteLettresInterface uci = cr
		    		.wrap(DemandeAccesBoiteLettresInterface.class);
		 	cr.getReference().addQueryParameter("idClient", idClient);
		 	ContainerTemplateDemandeLivreur content = uci.getDemandesLivreurs(); 
		 	return content.getList_templateDemandeLivreur(); 
		}
		
//		public void approbationAccesBAL(String idClient, String idLivreur) {
//			final DemandeAccesBoiteLettresInterface uci = cr
//		    		.wrap(DemandeAccesBoiteLettresInterface.class);
//			
//		
//			
//		 	cr.getReference().addQueryParameter("idClient", idClient);
//			cr.getReference().addQueryParameter("idLivreur", idLivreur);
//			        uci.approbationAccesBAL();
//		}

}
