package org.cnam.livreur;

import org.cnam.cloud.wsrest.model.LivreurService;
import org.cnam.cloud.wsrest.model.Livreurs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AjouterLivreurNouveauActivity extends Activity{

	
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.ajouter_livreur_nouveau);

	Button btn = (Button) findViewById(R.id.ajouterLivreurMainteant);
	btn.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
	    	ajouterUtilisateur();			
	    }
	});
    }
    
    
    final void ajouterUtilisateur()
    {
//	final Thread checkUpdate = new Thread() {
//	    public void run() {
		EditText nom = (EditText) findViewById(R.id.nomLivreur);
		EditText prenom = (EditText) findViewById(R.id.prenomLivreur);
		EditText mail = (EditText) findViewById(R.id.mailLivreur);
		
		EditText loginLivreur = (EditText) findViewById(R.id.loginLivreur); 
		EditText motPasseLivreur = (EditText) findViewById(R.id.motPasseLivreur); 
		EditText confirmationmotPasseLivreur = (EditText) findViewById(R.id.ConfirmationmotPasseLivreur); 
		
		EditText phone = (EditText) findViewById(R.id.telephoneLivreur);
		Livreurs livreur = new Livreurs();
		livreur.setNomLivreur(nom.getText().toString());
		livreur.setPrenomLivreur(prenom.getText().toString());
		livreur.setMailLivreur(mail.getText().toString());
		livreur.setLoginLivreur(loginLivreur.getText().toString());
		livreur.setMotPasseLivreur(motPasseLivreur.getText().toString());
		livreur.setTelephone(phone.getText().toString());
		
		
		final LivreurService uc = new LivreurService();
		try {
			
			 if(Utility.isNotNull(nom.getText().toString()) && Utility.isNotNull(prenom.getText().toString())
					 && Utility.isNotNull(mail.getText().toString())&& Utility.isNotNull(loginLivreur.getText().toString())
					 && Utility.isNotNull(motPasseLivreur.getText().toString())&& Utility.isNotNull(confirmationmotPasseLivreur.getText().toString())
					 && Utility.isNotNull(phone.getText().toString())) {
				      if(Utility.validatePhoneNumber(phone.getText().toString())) {
							 if(Utility.validate(mail.getText().toString())){
									     if((motPasseLivreur.getText().toString()).equals((confirmationmotPasseLivreur.getText().toString()))) {
									    	 uc.create(livreur);
									    	 Log.e("Livreur cree avec succes  ", "ok");
									 		final Intent intent = new Intent(AjouterLivreurNouveauActivity.this, LivreurAccueilActivity.class);
											startActivity(intent);
									     }else {
					
											  Toast.makeText(AjouterLivreurNouveauActivity.this, "Le mot de passe confimé n'est pas correct !",
												Toast.LENGTH_SHORT).show();
									     }
							 }else {
								  Toast.makeText(AjouterLivreurNouveauActivity.this, "Merci de saisir une adresse mail valide !!",
											Toast.LENGTH_SHORT).show();
							 }
				      }else {
				    	  Toast.makeText(AjouterLivreurNouveauActivity.this, "Merci de saisir un numéro de télephone  valide !!",
									Toast.LENGTH_SHORT).show();
				      }
			 }else {
			 	 Toast	toast = Toast.makeText(getApplication(), "Merci de renseignez tous les champs pour créer un compte livreur !", Toast.LENGTH_LONG);
                 toast.show();
			 }
		    
			 Log.e("Livreur pas cree   ", "ko");
		    
		} catch (Exception e) {
			e.printStackTrace();
		    return;
		}

//	    }
//	};
//	checkUpdate.start();
    }
    
    
    /*
     * 
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu_ajouter_livreur, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        switch (item.getItemId()) {
        case R.id.menu_quitter_ajouter_livreur:
         	 NavUtils.navigateUpFromSameTask(this);
         	this.finish(); 
            return true;
        default:
            return super.onOptionsItemSelected(item);
    } 
       
    }
}
