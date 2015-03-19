package org.cnam.clients;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class PrincipaleFragmentActivity extends FragmentActivity{

	
	protected abstract Fragment creerFragment();
	  
  	/*
  	 * INITIALISATION DE L'ACTIVITE
  	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*
    	 * INSTANCATION DE L'ETAT DE L'ACTIVITE 
    	 */
    	 super.onCreate(savedInstanceState);
    	 /*
    	  * ASSOCIER UN TEMPLATE POUR LA PARTIE GRAPHIQUE DE CETTE ACITIVITE 
    	  * LES FRAGEMENT DE CETTE ACTIVITE VONT REMPLIR LE CONTENAIRE   ->   activity_fragment
    	  */
          //setContentView(R.layout.activity_fragment);
    	 setContentView(R.layout.accueil_conteneur_fragment);
          
         /*
          * MANAGER DES FRAGEMENT PERMET DE RECUPERER LES FRAGEMENT DEPUIS UNE DEFINITION  XML
          * R.id.fragmentContainer C'EST L'IDENTIFIANT DU TEMPLATE activity_fragment 
          */
         FragmentManager manager = getSupportFragmentManager();
         Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

         /*
          * SI IL NE Y A DE FRAGEMENT DEFINI DANS LE FICHIER XML 
          * APPEL LA METHODE CREATEFRAGMENTE POUR CREER UN FRAGMENT DE MANIERE DYNAMIQUE 
          */
         if (fragment == null) {
             fragment = creerFragment();
             /*
              * AJOUTER LE FRAGEMENT DANS LE CONTAINER DE L'ACTIVITE  
              */
             manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
         }
    }
}
