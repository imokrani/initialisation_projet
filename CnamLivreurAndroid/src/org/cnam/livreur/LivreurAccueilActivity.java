package org.cnam.livreur;

import android.support.v4.app.Fragment;





public class LivreurAccueilActivity  extends PrincipaleFragmentActivity{

	  @Override
	  protected Fragment creerFragment() {
		  return new LivreurAccueilFragment(); 
	  }
}
