package org.cnam.livreur;


import java.util.ArrayList;



import java.util.Date;

import org.cnam.cloud.wsrest.model.Clients;
import org.cnam.cloud.wsrest.model.GetAllClientsLivreur;
import org.cnam.cloud.wsrest.model.Livreurs;
import org.cnam.cloud.wsrest.model.LivreursClients;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListClientsLivreurFragment extends ListFragment {

	ArrayList<Clients> tousClients; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 setHasOptionsMenu(true);
    	 /*
    	  * message de bienvenu 
    	  */
    	 VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();
    	 
    	 Livreurs livreurs = vg.getLivreurs(); 
    	 getActivity().setTitle("Bienvenue "+ livreurs.getNomLivreur() + " " +livreurs.getPrenomLivreur());
    	// tousClients = getClients(); 
    	 GetAllClientsLivreur getcl = new GetAllClientsLivreur(); 
    	 tousClients = (ArrayList<Clients>) getcl.getClientsLivreurs(); 
    	 ClientsAdapter adapter = new ClientsAdapter(tousClients); 
    	 setListAdapter(adapter);
    	 setRetainInstance(true); 
    	
    }
    
    /*
     * Preparation des clients 
     */
//    public ArrayList<Clients> getClients() {
//    	ArrayList<Clients> clients  = new ArrayList<Clients>(); 
//    	Clients c1 = new Clients(); 
//    	c1.setNomClient("Client 1 nom");
//    	c1.setPrenomClient("Client 1 prenom");
//    	clients.add(c1);
//    	Clients c2 = new Clients();
//    	c2.setNomClient("Client 2 nom");
//    	c2.setPrenomClient("Client 2 prenom");
//    	clients.add(c2);
//    	Clients c3 = new Clients(); 
//    	c3.setNomClient("Client 3 nom prenom");
//    	c3.setPrenomClient("Client 3 prenom");
//    	clients.add(c3);
//    	Clients c4 = new Clients(); 
//    	c4.setNomClient("Client 4 nom");
//    	c4.setPrenomClient("Client 4 prenom");
//    	clients.add(c4);
//    	return clients; 
//    }
    
    /*
     * creeer la vue
     */
    @Override
    @TargetApi(11)
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    	View v = super.onCreateView(inflater, parent, savedInstanceState);
    	ListView listView = (ListView)v.findViewById(android.R.id.list);
    	 
    	 return v;
    }
    
    private class ClientsAdapter extends ArrayAdapter<Clients> {
    	public ClientsAdapter(ArrayList<Clients> clientsAdapter) {
    		super(getActivity(), android.R.layout.simple_list_item_1, clientsAdapter); 
    	}
    	/*
    	 * construction de la vue 
    	 */
    	
    	   @Override
           public View getView(int position, View convertView, ViewGroup parent) {
    		   if(convertView == null) {
    			   convertView = getActivity().getLayoutInflater().inflate(R.layout.list_clients_livreur, null); 
    		   }
    		   /*
    		    * recuperer le client clique 
    		    */
    		   
    		  Clients cleint	 = getItem(position); 
    		  TextView prenomClient = (TextView)convertView.findViewById(R.id.client_prenom_titleTextView); 
    		  prenomClient.setText(cleint.getPrenomClient()); 
   		   
   		      TextView nomClient = (TextView)convertView.findViewById(R.id.client_nom_titleTextView); 
   		      nomClient.setText(cleint.getNomClient()); 
   		      
   		      TextView mailClient = (TextView)convertView.findViewById(R.id.mail_textView1); 
   		      mailClient.setText("Email : "+cleint.getMailClient()); 
   		   
   		     TextView telephoneClient = (TextView)convertView.findViewById(R.id.telephone_textView1); 
   		     telephoneClient.setText("Tel : "+cleint.getIdTelephoneClient()); 

   		     TextView numeroBoiteLettre = (TextView)convertView.findViewById(R.id.numero_boite_lettre_textView1); 
   		     numeroBoiteLettre.setText("Num : "+cleint.getNumeroBal()); 
   		
   	         TextView adresse = (TextView)convertView.findViewById(R.id.adresse_boite_textView1); 
   	         adresse.setText("Adr : "+cleint.getAdresse());
   	         
   	      TextView ville = (TextView)convertView.findViewById(R.id.ville_textView1); 
   	      ville.setText(cleint.getVille());
   	      
   	      TextView codePostal = (TextView)convertView.findViewById(R.id.code_postal_textView1); 
   	      codePostal.setText(cleint.getCodePostal());
   		  
   		   return convertView;
    	   }
    }
    
    public void onListItemClick(ListView l, View v, int position, long id) {
 	    VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();
    	Livreurs livreurs = vg.getLivreurs(); 
    	Clients client = ((ClientsAdapter)getListAdapter()).getItem(position);
    	vg.setClients(client);
    	LivreursClients livreursClients = new LivreursClients(); 
    	livreursClients.setIdClient(client.getIdClient());
    	livreursClients.setIdLivreur(livreurs.getIdLivreur());
    	livreursClients.setDateDemande(new Date());
    	livreursClients.setApprobationClient(false);
    	Log.e("IDCLient", client.getIdClient().toString());
    	Log.e("IDLivreur", livreurs.getIdLivreur().toString());
    	//DemandeAccesBoiteLettres accesBoiteLettres =new DemandeAccesBoiteLettres(); 
    	try {
    		
    		vg.setLivreursClients(livreursClients);
			//accesBoiteLettres.create(livreursClients);
			//lancer la boite de dialogue
    	  	// Log.e(--)
       	 Intent ii = new Intent(getActivity(),  DialogAccesBoitesAuxLettres.class);
       	// ii.putExtra(DialogApprobationDemande.ID_LIVREUR, value.getIdLivreur().toString());
       	// ii.putExtra(DialogApprobationDemande.ID_CLIENT, value.getIdClient().toString());
       	 startActivityForResult(ii, 0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Toast toast = Toast.makeText(getActivity(), "Id client = "+client.getIdClient() + " Livreur Id = "+livreurs.getIdLivreur(), Toast.LENGTH_LONG);
        toast.show();
    	
    	 
    }
    
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list_clients_livreur, menu);
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_quitter_application:
             	 NavUtils.navigateUpFromSameTask(getActivity());
                 getActivity().finish(); 
                return true;
            case R.id.menu_synchroniser_clients: 
            	Toast toast = Toast.makeText(getActivity(), "Ce bouton est en cours de développement", Toast.LENGTH_LONG);
                toast.show();
            	return true; 
            default:
                return super.onOptionsItemSelected(item);
        } 
    }
    
}
