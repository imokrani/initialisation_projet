package org.cnam.livreur;


import org.cnam.cloud.wsrest.model.ContainerLivreurs;
import org.cnam.cloud.wsrest.model.LivreurService;
import org.cnam.cloud.wsrest.model.Livreurs;
import org.json.JSONException;
import org.json.JSONObject;
 







import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginLivreurActivity extends Activity {

	
	// Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    // Email Edit View Object
    EditText loginLivreur;
    // Passwprd Edit View Object
    EditText pwdET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_livreur);
        // Find Error Msg Text View control by ID
        errorMsg = (TextView)findViewById(R.id.login_error);
        // Find Email Edit View control by ID
        loginLivreur = (EditText)findViewById(R.id.loginLivreur);
        // Find Password Edit View control by ID
        pwdET = (EditText)findViewById(R.id.loginPassword);
        // Instantiate Progress Dialog object
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
    }
 
    /*
     * METHODE DE CONNEXION DU LIVRUEUR
     */
     public void connexionLivreur(View view){
    	 Thread checkUpdate = new Thread() {
    	    public void run() {
			        String login = loginLivreur.getText().toString();
			        String password = pwdET.getText().toString();
			        if(Utility.isNotNull(login) && Utility.isNotNull(password)){
			        	final LivreurService uc = new LivreurService();
			    		try {
			    			ContainerLivreurs livreurs = uc.connexionLivreur(	login, password); 
			    		     if( (livreurs != null) && (livreurs.getLivreurs_list().size()>0) ) {
			    		    	 VaiablesGlobales vg = (VaiablesGlobales)getApplication();
			    		    	 Log.e("CONNEXIONNNNNN  SUCCESS ", "****************************************");
			    		     }else {
			    		    	 Log.e("CONNEXIONNNNNN  FAILED", "****************************************");
			    		     }
			    	
			    		    
			    		} catch (Exception e) {
			    			Log.e("ERRORRRRRRRRRRRR  CONNEXIONNNNNN  ", "****************************************");
			    		    e.printStackTrace();
			    		}
			        } else{
			            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
			        }		        
	        }
		};    
		 checkUpdate.start();
    }
 

 

}
