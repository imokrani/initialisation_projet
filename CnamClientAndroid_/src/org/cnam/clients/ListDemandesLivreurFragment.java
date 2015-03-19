package org.cnam.clients;

import java.util.ArrayList;

import org.cnam.cloud.wsrest.model.Clients;
import org.cnam.cloud.wsrest.model.DemandeAccesBoiteLettres;
import org.cnam.cloud.wsrest.model.TemplateDemandeLivreur;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListDemandesLivreurFragment  extends ListFragment {

	
	ArrayList<TemplateDemandeLivreur > demandesLivreur; 
	 private static final int REQUEST_DATE = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 setHasOptionsMenu(true);
    	 /*
    	  * Message de bienvenue
    	  */
    	 VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();
    	 Clients clientConnecte = vg.getClients(); 
    	 getActivity().setTitle("Bienvue "+ clientConnecte.getNomClient() + " " +clientConnecte.getPrenomClient());
    	 /*
    	  * TemplateDemandeLivrueur
    	  */
    	 DemandeAccesBoiteLettres accesBoiteLettres = new DemandeAccesBoiteLettres();
    	 demandesLivreur = new ArrayList<TemplateDemandeLivreur>(accesBoiteLettres.getDemandesLivreurs(clientConnecte.getIdClient().toString()));
    	 DemandesAdapter adapter = new DemandesAdapter(demandesLivreur);
    	 setListAdapter(adapter);
    	 setRetainInstance(true); 
    }
    
    /*
     * Liste des demande 
     */
    public void onListItemClick(ListView l, View v, int position, long id) {
    	 VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();
    	 
    	 Intent ii = new Intent(getActivity(),  DialogApprobationDemande.class);
    	 TemplateDemandeLivreur value = ((DemandesAdapter)getListAdapter()).getItem(position);
    	 vg.setTemplateDemandeLivreur(value);
    	// ii.putExtra(DialogApprobationDemande.ID_LIVREUR, value.getIdLivreur().toString());
    	// ii.putExtra(DialogApprobationDemande.ID_CLIENT, value.getIdClient().toString());
    	 startActivityForResult(ii, 0);
    }
    
    
    @Override
    @TargetApi(11)
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    	View v = super.onCreateView(inflater, parent, savedInstanceState);
    	ListView listView = (ListView)v.findViewById(android.R.id.list);
    	 
    	 return v;
    }
    
    
    private class DemandesAdapter extends ArrayAdapter<TemplateDemandeLivreur> {
    	
    	public DemandesAdapter(ArrayList<TemplateDemandeLivreur> listTemplate) {
    		super(getActivity(), android.R.layout.simple_list_item_1, listTemplate); 
    	}
    	
    	
 	   @Override
       public View getView(int position, View convertView, ViewGroup parent) {
		   if(convertView == null) {
			   convertView = getActivity().getLayoutInflater().inflate(R.layout.list_demandes_livreur, null); 
		   }
		   
		      TemplateDemandeLivreur templateDemande = getItem(position); 
		   
			  TextView prenomLivreur = (TextView)convertView.findViewById(R.id.livreur_prenom_titleTextView); 
			  prenomLivreur.setText(templateDemande.getPrenomLivreur()); 
   		   
   		      TextView nomLivreur = (TextView)convertView.findViewById(R.id.livreur_nom_titleTextView); 
   		   nomLivreur.setText(templateDemande.getNomLivreur()); 
   		   
   	      TextView telephoneLivreur = (TextView)convertView.findViewById(R.id.telephone_livreur); 
   	      telephoneLivreur.setText("Tel :"+templateDemande.getTelephone()); 
  		   
  	      TextView dateDemande = (TextView)convertView.findViewById(R.id.date_demande); 
  	      dateDemande.setText("Date :"+templateDemande.getDateDemande().toString()); 
      	ImageView pictoPartegerServeurPicto_ok = (ImageView)convertView.findViewById(R.id.picto_partager_serveur_picto_ok) ;

  	    if(templateDemande.isApprobationClient()) {
        	Drawable d = getResources().getDrawable(R.drawable.picto_ok);
     		pictoPartegerServeurPicto_ok.setImageDrawable(d);
        }else {
        	Drawable d = getResources().getDrawable(R.drawable.picto_ko);
     		pictoPartegerServeurPicto_ok.setImageDrawable(d);           
        }
   		   
		   return convertView;
	   }
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list_demandes_acces_bal, menu);
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
