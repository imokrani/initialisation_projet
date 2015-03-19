package org.cnam.cloud.wsrest.server;


import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;


public class RestletDispatch extends Application {

	
	 @Override
	 public synchronized Restlet createInboundRoot()
	 {
	 final Router router = new Router(getContext());
	 router.attach("/user", UserController.class);
	 router.attach("/livreur", LivreurService.class);
	 router.attach("/client", ClientService.class);
	 router.attach("/getClientsLivreur", GetAllClientsLivreur.class);
	 router.attach("/demandeAccesBoiteLettres", DemandeAccesBoiteLettres.class);
	 router.attach("/approbationDemandeAccesBal", ApprobationDemandeAccesBal.class);
	 
	 
	 
	 return router;
	 }
}
