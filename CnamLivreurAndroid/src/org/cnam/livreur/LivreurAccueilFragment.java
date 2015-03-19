package org.cnam.livreur;

import org.cnam.cloud.wsrest.model.ContainerLivreurs;
import org.cnam.cloud.wsrest.model.LivreurService;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LivreurAccueilFragment extends Fragment{
 
	// Progress Dialog Object
    ProgressDialog prgDialog;
    
    TextView errorMsg;
    
    
    
    EditText emailET;
    EditText pwdET;
    Button boutonLogin; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	  setHasOptionsMenu(true);	  
    }


    @Override
    @TargetApi(11)
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    	View v = inflater.inflate(R.layout.login_livreur, parent, false);
    	
    	  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            	
            	//??????????????????????????????????????????????????????????????????????
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
    	  errorMsg = (TextView)v.findViewById(R.id.login_error);

          emailET = (EditText)v.findViewById(R.id.loginLivreur);
      
          pwdET = (EditText)v.findViewById(R.id.loginPassword);
   
          prgDialog = new ProgressDialog(getActivity());
      
          prgDialog.setMessage("Please wait...");
      
          prgDialog.setCancelable(false);
          boutonLogin = (Button)v.findViewById(R.id.btnLogin); 
          boutonLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				connexionLivreur(v);
			}
		});
          
          return v; 
    }
    
    public void connexionLivreur(View view){
   	 Thread checkUpdate = new Thread() {
   	    public void run() {
			       // String email = emailET.getText().toString();
			        String login = emailET.getText().toString();
			        String password = pwdET.getText().toString();
			        if(Utility.isNotNull(login) && Utility.isNotNull(password)){
			        	final LivreurService uc = new LivreurService();
			    		try {
			    			ContainerLivreurs livreurs = uc.connexionLivreur(	login, password); 
			    		     if( (livreurs != null) && (livreurs.getLivreurs_list().size()>0) ) {
			    		    // if(  uc.connexionLivreur(	email, password)) {
			    		    	 VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();
			    		    	 vg.setLivreurs(livreurs.getLivreurs_list().get(0));
			    		    	 Log.e("COnnexionnn  SUCCESS ", "****************************************");
			    		         /*
			    		          * lancer l activite list client
			    		          */
			    		         Intent ii = new Intent(getActivity(), ListClientsLivreurActivity.class);
			    			     startActivityForResult(ii, 1); 
			    		     }else {
			    		    	 Log.e("Connexionnn  FAILED", "****************************************");
			    		     }
			    	
			    		    
			    		} catch (Exception e) {
			    			Log.e("Erreur de Connexion ", "****************************************");
			    		    e.printStackTrace();
			    		}
			        } else{
			            Toast.makeText(getActivity().getApplicationContext(), "Vos identifiants ne sont pas corrects !!", Toast.LENGTH_LONG).show();
			        }		        
	        }
		};    
		 checkUpdate.start();
   }

	
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.accueil_livreur, menu);
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_ajouter_livreur:
                Intent i = new Intent(getActivity(), AjouterLivreurNouveauActivity.class);
			     startActivityForResult(i, 0); 
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } 
    }
	
}
