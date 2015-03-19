package org.cgi.poc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/*
 * 
 * - CETTE ACTIVITE HERITE DE L'ACTIVITY   FRAGMENTACTIVITY
 * - LA VUE DE CETTE ACTIVITE EST GEREE OBLIGATOIRMENT PAR UN FRAGEMENT  
 * - TOUTE LES ACTIVITES QUI VONT HERITER DE CETTE ACTIVITE DOIVENT IMPLEMENTER UNE METHODE QUI PERMET DE CREER LES FRAGEMENT 
 * - CETTE ACTIVITE INITIALISE UN CONTENAIR  DE TYPE    activity_fragment 
 */
public abstract class MainFragmentActivity extends FragmentActivity
{
      /*
       * -METHODE A IMPLEMENTER PAR LES CLASSES FILLES POUR LA CREATION DES FRAGMENENT OU LISTFRAGEMLENT
       */
	  	protected abstract Fragment createFragment();
	  
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
	             fragment = createFragment();
	             /*
	              * AJOUTER LE FRAGEMENT DANS LE CONTAINER DE L'ACTIVITE  
	              */
	             manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
	         }
	    }
}
