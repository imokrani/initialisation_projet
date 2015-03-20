package org.cgi.poc;

import java.util.ArrayList;
import java.util.UUID;

import org.cgi.poc.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;

public class BoardFragment  extends ListFragment {
	
	private ArrayList<Board> boards; 
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	  setHasOptionsMenu(true);
    	 getActivity().setTitle(R.string.home_poc_cgi_boards);
    	 boards = BoardDAO.get(getActivity()).getBoards(); 
    	 
    	 BoardAdapter boardAdapter = new BoardAdapter(boards); 
    	 setListAdapter(boardAdapter); 
    	 setRetainInstance(true); 
    }
    
    
    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    	View v = super.onCreateView(inflater, parent, savedInstanceState);
    	ListView listView = (ListView)v.findViewById(android.R.id.list);
    	
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {   
      	     registerForContextMenu(listView);
      }else {
    	  listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL); 
      	
      	/*
      	 * DEFINIR LES DIFFERENT LISTNER LIES A L'ACTION SUR LA LISTVIEW
      	 */
    	 
    	  

    	  /*
    	   * 
    	   */

    	  
      	listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
						
                  	/*
                  	 * CREATION DE MENU CONTEXTUEL SUR LA BARE D ACTION A PARTIR D UN TEMPLATE 
                  	 */
                  	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
							MenuInflater inflater = mode.getMenuInflater(); 
							inflater.inflate(R.menu.fragment_menu_suppression_board_application, menu); 
							return true;
						}
                  	
                  	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
							return false;
						}
						
						public void onDestroyActionMode(ActionMode mode) {
							
						}
						
						/*
						 * QUEL MENU CONTEXTUEL CHOISI PAR L UTILISTAUER 
						 */
						public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
							switch (item.getItemId()) {
							/*
							 * SUPPRESSION D UN BOARD
							 */
							case R.id.menu_item_supprimer_data:
								 BoardAdapter adaptateur = (BoardAdapter)getListAdapter();
								 BoardDAO boardDAO = BoardDAO.get(getActivity());
								 for (int i = adaptateur.getCount() - 1; i >= 0; i--) {
									 if(getListView().isItemChecked(i)) {
										 boardDAO.supprimerBoard(adaptateur.getItem(i)); 
									 }
								 }
								 mode.finish(); 
								 adaptateur.notifyDataSetChanged(); 
								 return true;
						    /*
						     * EDITER LES BOARD 
						     */
							case R.id.menu_item_edit_data:
								BoardAdapter adaptateurBoardEdit = (BoardAdapter)getListAdapter(); 
								BoardDAO boardDAOEdit = BoardDAO.get(getActivity());
								Board boardEdit = null; 
								 for (int i = adaptateurBoardEdit.getCount() - 1; i >= 0; i--) {
									 if(getListView().isItemChecked(i)) {
										boardEdit =  adaptateurBoardEdit.getItem(i); 
									 }
								 }
								 if(boardEdit != null) {
									   Log.e("MENU BORD ", "Lancer l activite org.cgi.poc.board.AjouterBordActivity.class "); 
						               Intent i = new Intent(getActivity(), AjouterBordActivity.class);
				
						              /*
						               * PASSER LES PARAMETRE A L'ACTIVITE DataActivity    NOTAMMENT   IDDATA
						               * EXTRA_DATA_ID /  IDENTIFIANT DE LA DONNEE A CREER   DANS LE FORMULAIRE DE L'ACTIVITE   DataActivity
						               */
						             //  i.putExtra(AjouterBoardFragment.EXTRA_BOARD_ID, boardEdit.getIdBoard().toString());
						               i.putExtra(AjouterBoardFragment.EXTRA_BOARD_ID, boardEdit.getIdBoard());
						               i.putExtra(AjouterBoardFragment.EXTRA_BORAD_ACTION_EDIT, "OK");
						              /*
						               * DEMARER L'ACTIVITE DataActivity  AVEC UNE POSSIBILITE D'AVOIR UN RETOUR startActivityForResult 
						               */
						              startActivityForResult(i, 0); 
								 }
								 
								 
							default:
								return false;
							}	
						}
						
						public void onItemCheckedStateChanged(ActionMode mode, int position,
								long id, boolean checked) {							
						}
						
					});  
      }
        return v;
    }
    
    /*
     * GESTION DU CLICK SUR UN BOARD 
     * RZDIRECTION SUR LE SOUS-MENU    PHOTO  SONS  NOTES ....
     */
    
    public void onListItemClick(ListView l, View v, int position, long id) {
    	//Intent intentAccueildata = new Intent();
    	Board boardClike = ((BoardAdapter)getListAdapter()).getItem(position);
    	
    	Intent i = new Intent(getActivity(), DataAccueilActivity.class);
    	i.putExtra(DataAccueilFragment.EXTRA_ID_BORAD_ASSOCIE_PHOTO, boardClike.getIdBoard().toString());
    	Log.e("Le UUID Dans la Liste des Bord  ", "     -->    " +boardClike.getIdBoard().toString());
    	/*
    	 * Mettre le UUID comme variable globale 
    	 */
    	  VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();
    	  vg.setUUUIDStringBoard(boardClike.getIdBoard().toString());
    	  Log.e("*********************************************************************", boardClike.getIdBoard().toString()); 
		startActivity(i);
    }
    
    
    /*
     * 
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((BoardAdapter)getListAdapter()).notifyDataSetChanged();
        /*
         * TRAITER LE RETOUR DE L ACTIVITY
         */
        if(requestCode == Activity.RESULT_CANCELED) {
        	 VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();
        	
        	Log.e("Bouton RETOUR Android ", "Pas normal"); 
        	Intent ii = getActivity().getIntent(); 
        	Log.e(" EXTRA_BOARD_ID ", ""+vg.getUUUIDStringBoard()); 
        //	ii.getStringExtra(AjouterBoardFragment.EXTRA_BOARD_ID);
        	Board b =BoardDAO.get(getActivity()).getBoard(UUID.fromString(vg.getUUUIDStringBoard()));
        	BoardDAO.get(getActivity()).supprimerBoard(b); 
        }
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu_icons_board, menu);
    }
    
    /*
     * GESTION DE LA SELECTION SUR LE MENU
     */
    @SuppressWarnings("deprecation")
	@TargetApi(11)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        switch (item.getItemId()) {
			            /*
			             *  LE MENU   CONSULTER       -->  IMAGES 
			             */
	
			            case R.id.menu_ajouter_board:
			            	      /*
			            	       * CREER UNE NOUVELLE DATA AVEC UN ID   ASSOCIE  ALEATOIREMENT 
			            	       */
					              Board board = new Board();
					              /*
					               * AJOUTER LA DATA DANS UNE LISTE VISIBLE PAR L'ACTIVITE
					               */
					              BoardDAO.get(getActivity()).addBoard(board);
					              /*
					               * PREPARER L'APPEL DE L'ACTIVITY DataActivity   FORMULAIRE DE CREATION DES DATA
					               */
					              Log.e("MENU BORD ", "Lancer l activite org.cgi.poc.board.AjouterBordActivity.class "); 
					               Intent i = new Intent(getActivity(), AjouterBordActivity.class);
			
					              /*
					               * PASSER LES PARAMETRE A L'ACTIVITE DataActivity    NOTAMMENT   IDDATA
					               * EXTRA_DATA_ID /  IDENTIFIANT DE LA DONNEE A CREER   DANS LE FORMULAIRE DE L'ACTIVITE   DataActivity
					               */
					               i.putExtra(AjouterBoardFragment.EXTRA_BOARD_ID, board.getIdBoard());
					               i.putExtra(AjouterBoardFragment.EXTRA_BORAD_ACTION_EDIT, "KO");
					              /*
					               * DEMARER L'ACTIVITE DataActivity  AVEC UNE POSSIBILITE D'AVOIR UN RETOUR startActivityForResult 
					               */
					              startActivityForResult(i, 0);
					              return true;
			            case R.id.bouton_quitter_application:
			            	//System.exit(0);
			            	System.runFinalizersOnExit(true);
			            	return true; 
			          /*
			           * LE MENU   SETTINGS    
			           */  
			            case R.id.menu_item_show_subtitle:
				            	
				                return false;
			            case R.id.bouton_retour:
			            	return true; 
			            /*
			             * SI AUCUN ELEMENT DE MENU EST CONCERNE    
			             */  
			            default:
			                return super.onOptionsItemSelected(item);
			        }
        
    }
    
    
    /*
     * CLASSE INTERNE POUR QUI GERE LES BOARDS DE L APPLICATIION 
     */
    private class BoardAdapter extends ArrayAdapter<Board> {
    	
		public BoardAdapter(ArrayList<Board> boardsAdapter) {
			super(getActivity(), android.R.layout.simple_list_item_1, boardsAdapter);
		}
    	
    	/*
    	 * CONSTRUCTION DE LA VUE 
    	 */
    	   @Override
           public View getView(int position, View convertView, ViewGroup parent) {
    		   
    		   if(convertView == null) {
    			   convertView = getActivity().getLayoutInflater().inflate(R.layout.accueil_list_boards, null); 
    		   }
    		   
    		   /*
    		    * RECUPERATION DES COMPOSANTS 
    		    */
    		   Board board = getItem(position); 
    		   
    		   TextView textViewTitre = (TextView)convertView.findViewById(R.id.board_titre_list_item_titleTextView); 
    		   textViewTitre.setText(board.getTitleBoard()); 
    		   
    		   TextView textViewDescription = (TextView)convertView.findViewById(R.id.board_description_list_item_titleTextView); 
    		   textViewDescription.setText(board.getDescriptionBorad()); 
    		   return convertView; 
    	   }
    	
    }

}
