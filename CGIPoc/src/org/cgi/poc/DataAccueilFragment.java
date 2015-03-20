package org.cgi.poc;

import java.util.Date;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

/*
 *DEFINITION DU MENU ENWIDJET 
 */
public class DataAccueilFragment extends Fragment {

	  private RadioGroup radiodataGroup;
	  private RadioButton radioDataButton;
	  
	  String idBoardUUIDString; 
	  
	  public static final String EXTRA_ID_BORAD_ASSOCIE_PHOTO = "ID_BOARD_PHOTO"; 
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.e("REPASSE PAR DataAccueilFragment", "onCreate"); 
    	 super.onCreate(savedInstanceState);
    	 setHasOptionsMenu(true);
    }
    

    @Override
    @TargetApi(11)
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    	Log.e("REPASSE PAR DataAccueilFragment", "onCreateView"); 
    	 View v = inflater.inflate(R.layout.accueil_radio_bouton, parent, false);
	     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	              getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
	     }
	     Intent ii = getActivity().getIntent(); 
          
	         idBoardUUIDString = ii.getStringExtra(EXTRA_ID_BORAD_ASSOCIE_PHOTO);
          if(idBoardUUIDString == null) {
        	  VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication(); 
        	  idBoardUUIDString = vg.getUUUIDStringBoard(); 
          }
	     radiodataGroup = (RadioGroup) v.findViewById(R.id.data_Bord_group_bouton);
	     RadioButton photos = (RadioButton)v.findViewById(R.id.radioPhotos); 
	     Log.e("UUID BOARD  ", idBoardUUIDString); 
	     Log.e("La taille de liste des photos ", "    "+DataDAO.get(getActivity()).getDatasByBoard(idBoardUUIDString).size());
	     photos.setText(photos.getText()+ " ( "+DataDAO.get(getActivity()).getDatasByBoard(idBoardUUIDString).size()+" )"); 
	     photos.refreshDrawableState();  
	     radiodataGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	    		Toast toast ; 
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) { 
			
				switch(checkedId) {
                case R.id.radioPhotos: 
                	/*
                	 * recuperer le id Board 
                	 */
                	Log.e("R.id.radioPhotos ", " Photo selectionnee "); 
                  
                    Intent intentPhoto = new Intent(getActivity(), DataMenuActivity.class); 
                	Log.e("Le UUID  recuperee depuis Liste des Board",  "     -->    "+ idBoardUUIDString); 
                    intentPhoto.putExtra(DataMenuFragment.EXTRA_ID_BORAD_ASSOCIE_PHOTO, idBoardUUIDString);
                    Log.e("R.id.radioPhotos ", " lancer l activite "); 
                    startActivityForResult(intentPhoto, 2); 
                     break;
                case R.id.radioNotes: 
                	//toast = Toast.makeText(getActivity(), "radioNotes En cours de développement", Toast.LENGTH_LONG);
                    //toast.show();
                	 Intent intentNotes = new Intent(getActivity(), ListeNotesActivity.class); 
                 	Log.e("Le UUID  recuperee depuis Liste des Board",  "     -->    "+ idBoardUUIDString); 
                     intentNotes.putExtra(ListeNotesFragment.EXTRA_ID_BORAD_ASSOCIE_NOTE, idBoardUUIDString);
                     Log.e("R.id.radioPhotos ", " lancer l activite "); 
                     startActivityForResult(intentNotes, 25); 
                    break;
                case R.id.radioSons: 
                	toast = Toast.makeText(getActivity(), "radioSons En cours de développement", Toast.LENGTH_LONG);
                    toast.show();
                    break;
                case R.id.radioVideos:
                	toast = Toast.makeText(getActivity(), "radioSons En cours de développement", Toast.LENGTH_LONG);
                    toast.show();
                    break;
          }   
				//radioDataButton = (RadioButton)findViewById(checkedId); 
				//Toast.makeText(getActivity(), radioDataButton.getText(), Toast.LENGTH_SHORT).show();
				
			}
		}); 
        return v; 
    }
    
    /*
     * GESTION DES MENUS 
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    	Log.e("REPASSE PAR DataAccueilFragment", "onCreateOptionsMenu");
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu_icons_bouton_retour_board, menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Log.e("REPASSE PAR DataAccueilFragment", "onOptionsItemSelected");
    	Log.e("onOptionsItemSelected ", " Debut"); 
    	
        switch (item.getItemId()) {
            case android.R.id.home:
            	/*
            	 * SI CLICK SUR L ICONE DE L'APPLICATION NAVIGUER A L'ACTIVITE   PARENTE
            	 * SI ACTIVITE PARENTE EST NUULLE L APPLICATION EN CHANGE PAS D'ETAT
            	 */
            	Log.e("android.R.id.home ", " Icon Accueil");
             	 NavUtils.navigateUpFromSameTask(getActivity());
                 getActivity().finish(); 	
            	 
                return true;
            case R.id.bouton_retour:
            	Log.e("R.id.bouton_retour", " Bouton Retour");
            	 NavUtils.navigateUpFromSameTask(getActivity());
                 getActivity().finish(); 
                return true; 
            default:
                return super.onOptionsItemSelected(item);
                
                 
        } 
    }
    
    
    
}
