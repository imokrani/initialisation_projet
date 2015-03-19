package org.cnam.cloud.wsrest.model;

import java.util.List;

import org.cnam.cloud.wsrest.model.Livreurs;
import org.cnam.cloud.wsrest.model.User;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ServerResource;

import android.util.Log;


public class LivreurService {

	  public final ClientResource cr = new ClientResource(
			    EngineConfiguration.gae_path + "/rest/livreur");

		    public LivreurService() {
			     EngineConfiguration.getInstance();
		    }
		    
		    
    public void create(Livreurs livreur) throws Exception {
    	final LivreurServiceInterface uci = cr
    		.wrap(LivreurServiceInterface.class);

    	try {
    	    uci.create(livreur);
    	    Log.i("LivreurService", "Creation success !");
    	} catch (Exception e) {
    	    Log.i("LivreurService", "Creation failed !");
    	    e.printStackTrace();
    	    throw e;
    	}
        }
    
		  
    
	public List<Livreurs> getAllLivreurs(String login , String motPasse) {
	   	final LivreurServiceInterface uci = cr
	    		.wrap(LivreurServiceInterface.class);
	   	cr.getReference().addQueryParameter("login", login);
		cr.getReference().addQueryParameter("motPasse", motPasse);
		//ContainerLivreurs content = uci.getAllLivreurs();
		ContainerLivreurs content = uci.connexionLivreur();
		return content.getLivreurs_list();
	}
	
	public ContainerLivreurs connexionLivreur(String login , String motPasse) {
	 	final LivreurServiceInterface uci = cr
	    		.wrap(LivreurServiceInterface.class);
		cr.getReference().addQueryParameter("login", login);
		cr.getReference().addQueryParameter("motPasse", motPasse);
		ContainerLivreurs content = uci.connexionLivreur();
		return content; 
//		if(content != null && content.getLivreurs_list().size() >0) {
//			return true; 
//		}
//		return false; 
	}
	
	
//    public boolean connexionLivreur()  throws Exception{
//		       	final LivreurServiceInterface uci = cr
//			    		.wrap(LivreurServiceInterface.class);
//		
//		    	try {
//		    		cr.getReference().addQueryParameter("login", "cxwcxwcxwcxw");
//		    		cr.getReference().addQueryParameter("motPasse", "xcxwcxwcxcxwcxw");
//		    		if(uci.connexionLivreur()) {
//		    			Log.i("LivreurService", "connexion avec  success !");
//		    		 	return true;
//		    		}else {
//		    			Log.i("LivreurService", "connexion avec  failed !");
//		    		}
//		    	    
//		    	    
//		    	} catch (Exception e) {
//		    	    Log.i("LivreurService", "connexion avec  failed !");
//		    	    e.printStackTrace();
//		    	    throw e;
//		    	}
//		    	return false; 
//		    }
}
