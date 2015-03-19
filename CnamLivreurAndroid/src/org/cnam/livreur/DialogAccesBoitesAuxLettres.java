package org.cnam.livreur;

import org.cnam.cloud.wsrest.model.DemandeAccesBoiteLettres;
import org.cnam.cloud.wsrest.model.LivreursClients;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DialogAccesBoitesAuxLettres extends Activity {

	public final static String ID_LIVREUR = "ID_LIVREUR"; 
	public final static String ID_CLIENT= "ID_CLIENT";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil_conteneur_fragment);
        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.dialog_demande_acces_bal, null);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setView(alertDialogView);
        adb.setTitle("Confirmation de demande d'accès à la boite aux lettre");
        
       	VaiablesGlobales vg = (VaiablesGlobales)getApplication();
    	//TemplateDemandeLivreur value = vg.getTemplateDemandeLivreur(); 
    	
    	TextView titre_boite_lettre = (TextView) alertDialogView.findViewById(R.id.TextView1); 
    	//titre_boite_lettre.setText("Id Clien "+value.getIdClient() +"   "+value.getIdLivreur());
    	
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	/*
            	 * Enregistrer l'approbation 
            	 */
            	//String strIdCLient = getIntent().getStringExtra(ID_CLIENT); 
            	//String strIdCLient = getIntent().getStringExtra(ID_CLIENT);
            	VaiablesGlobales vg = (VaiablesGlobales)getApplication();
            	//TemplateDemandeLivreur value = vg.getTemplateDemandeLivreur(); 
            	//ApprobationDemandeAccesBal accesBoiteLettres = new ApprobationDemandeAccesBal(); 
            	DemandeAccesBoiteLettres accesBoiteLettres =new DemandeAccesBoiteLettres();
            	LivreursClients livreursClients = null; 
            	livreursClients = vg.getLivreursClients(); 
            	if(livreursClients != null )
					try {
						accesBoiteLettres.create(livreursClients);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
               // accesBoiteLettres.approbationAccesBAL(value.getIdClient().toString(), value.getIdLivreur().toString());
            	Toast.makeText(DialogAccesBoitesAuxLettres.this, " Votre demande a été envoyée avec succès !! ", Toast.LENGTH_SHORT).show();
            	finish();
          } });
 

        adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {           
            	finish();
          } });
        adb.show();
	}
}
