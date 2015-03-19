package org.cnam.clients;



import org.cnam.cloud.wsrest.model.ClientService;
import org.cnam.cloud.wsrest.model.ContainerClients;

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

public class ClientAccueilFragment extends Fragment {

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
    	View v = inflater.inflate(R.layout.login_client, parent, false);
    	
    	  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            	
            	//??????????????????????????????????????????????????????????????????????
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
    	  errorMsg = (TextView)v.findViewById(R.id.login_error);

          emailET = (EditText)v.findViewById(R.id.loginEmail);
      
          pwdET = (EditText)v.findViewById(R.id.loginPassword);
   
          prgDialog = new ProgressDialog(getActivity());
      
          prgDialog.setMessage("Please wait...");
      
          prgDialog.setCancelable(false);
          boutonLogin = (Button)v.findViewById(R.id.btnLogin); 
          boutonLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				connexionClient(v);
			}
		});

    	  return v; 
    	  
    }
    
    public void connexionClient(View view){
//      	 Thread checkUpdate = new Thread() {
//      	    public void run() {
      	    	String email = emailET.getText().toString();
		        String password = pwdET.getText().toString();
		        if(Utility.isNotNull(email) && Utility.isNotNull(password)){
		        	final ClientService uc = new ClientService();
		    		try {
		    			ContainerClients clients = uc.connexionClient(email, password); 
		    		     if( (clients != null) && (clients.getClients_list().size()>0) ) {
		    		    // if(  uc.connexionLivreur(	email, password)) {
		    		    	 VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();
		    		    	 vg.setClients(clients.getClients_list().get(0));
		    		    	 Log.e("CONNEXIONNNNNN  SUCCESS ", "****************************************");
		    		         /*
		    		          * lancer l activite list demande livrueur
		    		          */
		    		          Intent ii = new Intent(getActivity(), ListDemandeLivreurActivity.class);
		    			      startActivityForResult(ii, 1); 
		    		     }else {
		    		    	 Log.e("CONNEXIONNNNNN  FAILED", "****************************************");
		    		     }
		    	
		    		    
		    		} catch (Exception e) {
		    			Log.e("ERRORRRRRRRRRRRR  CONNEXIONNNNNN  ", "****************************************");
		    		    e.printStackTrace();
		    		}
		        } else{
		            Toast.makeText(getActivity().getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
		        }	
      	    	
      	    }
	        
//		};    
//		 checkUpdate.start();
    //}

    
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.accueil_client, menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_ajouter_client:
                Intent i = new Intent(getActivity(), AjouterClientActivity.class);
			     startActivityForResult(i, 0); 
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } 
    }
    
    
}
