package org.cnam.clients;


import org.cnam.cloud.wsrest.model.ClientService;
import org.cnam.cloud.wsrest.model.Clients;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AjouterClientActivity extends Activity {

	
	   public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.ajouter_client);

			Button btn = (Button) findViewById(R.id.ajouter_client);
			btn.setOnClickListener(new OnClickListener() {

			    @Override
			    public void onClick(View v) {
			    	ajouterClient();			
			    }
			});
		    }
	   
	    final void ajouterClient()
	    {
		final Thread checkUpdate = new Thread() {
		    public void run() {
		    	 /*
		    	  * Recuperer tous les champs     
		    	  */
		    	EditText nomCient	 = (EditText) findViewById(R.id.nomCient);
		    	EditText prenomClient = (EditText) findViewById(R.id.prenomClient);
		    	EditText mailClient = (EditText) findViewById(R.id.mailClient);
		    	EditText loginClient = (EditText) findViewById(R.id.loginClient);
		    	EditText motPasseClient = (EditText) findViewById(R.id.motPasseClient);
		    	EditText idTelephoneClient = (EditText) findViewById(R.id.idTelephoneClient);
		    	EditText adresse = (EditText) findViewById(R.id.adresse);
		    	EditText complementAdresse = (EditText) findViewById(R.id.complementAdresse);
		    	
		    	EditText codePostal = (EditText) findViewById(R.id.codePostal);
		    	EditText ville = (EditText) findViewById(R.id.ville);
		    	EditText numeroBal = (EditText) findViewById(R.id.numeroBal);
		    	
		    	Clients c = new Clients(); 
		    	
		    	c.setNomClient(nomCient.getText().toString());
		    	c.setPrenomClient(prenomClient.getText().toString());
		    	c.setMailClient(mailClient.getText().toString());
		    	c.setLoginClient(loginClient.getText().toString());
		    	c.setMotPasseClient(motPasseClient.getText().toString());
		    	c.setIdTelephoneClient(idTelephoneClient.getText().toString());
		    	c.setAdresse(adresse.getText().toString());
		    	c.setCodePostal(codePostal.getText().toString());
		    	c.setComplementAdresse(complementAdresse.getText().toString());
		    	c.setVille(ville.getText().toString());
		    	c.setNumeroBal(numeroBal.getText().toString());
		   
		    	final ClientService uc = new ClientService();
				try {
				    uc.create(c);
				} catch (Exception e) {
					e.printStackTrace();
				    return;
				}
				final Intent intent = new Intent(AjouterClientActivity.this,
						ClientAccueilActivity.class);
				startActivity(intent);
			    }
		    
			};
			checkUpdate.start();
		    }
	   
}
