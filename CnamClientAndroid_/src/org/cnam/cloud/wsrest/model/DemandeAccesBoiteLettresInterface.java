package org.cnam.cloud.wsrest.model;


import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface DemandeAccesBoiteLettresInterface {

	@Get
	ContainerTemplateDemandeLivreur getDemandesLivreurs(); 
	
//	@Put
//	void approbationAccesBAL(); 
}
