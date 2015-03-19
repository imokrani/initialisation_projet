package org.cgi.poc;

import java.util.ArrayList;
import java.util.UUID;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
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

public class ListeNotesFragment extends ListFragment {

	ArrayList<Notes> mNotes; 
    public static final String EXTRA_ID_BORAD_ASSOCIE_NOTE = "ID_BOARD_ASSOCIE_NOTE";  
    String uuidBoard = null; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        
        //recuperer le board 
    	Intent intentParam = getActivity().getIntent(); 
        uuidBoard =intentParam.getStringExtra(EXTRA_ID_BORAD_ASSOCIE_NOTE);
        
         VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();
        vg = (VaiablesGlobales)getActivity().getApplication();
       if(uuidBoard != null) {
       	vg.setUUUIDStringBoard(uuidBoard); 
       }else {
       	uuidBoard = vg.getUUUIDStringBoard();
       }
       Board board = BoardDAO.get(getActivity()).getBoard(UUID.fromString(uuidBoard)); 
       getActivity().setTitle(board.getTitleBoard() +":"+"Notes("+NotesDAO.get(getActivity()).getNotesByBoard(uuidBoard).size()+")");
       
       mNotes = NotesDAO.get(getActivity()).getNotesByBoard(uuidBoard);
       Log.e("Afficher la taille des notes ", "   -> "+mNotes.size()); 
       NotesAdapter adapter = new NotesAdapter(mNotes); 
       setListAdapter(adapter);
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
        	 
         }
    	 return v; 
    }
    
    private class NotesAdapter extends ArrayAdapter<Notes> {
    	   public NotesAdapter(ArrayList<Notes> listNotes) {
    		   super(getActivity(), android.R.layout.simple_list_item_1, listNotes);
    	   }
    	  
    	   @Override
           public View getView(int position, View convertView, ViewGroup parent) {
    		   if (null == convertView) {
    	            	convertView = getActivity().getLayoutInflater().inflate(R.layout.accueil_list_notes, null);     	
    	       }
    		   
    		   Log.e("Rapassage par la liste pour afficher la liste des notes ", "LIST note"); 
    		   
    		   Notes note = getItem(position); 
    		   
    		   TextView titreNote = (TextView)convertView.findViewById(R.id.notes_list_item_titre); 
    		   titreNote.setText(note.getTitreNote());
    		   
    		   return convertView; 
    	   }
    }
 
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu_icons_list_notes, menu);    
    }
    
    
    /*
     * ECOUTER LES MENU + ACTIONS SUR LE MENU CHOISI 
     */
    @TargetApi(11)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        switch (item.getItemId()) {
			            
        				case android.R.id.home:NavUtils.navigateUpFromSameTask(getActivity());
        	                 getActivity().finish(); 
        					return true;
			            case R.id.partager_photo:
			            	      Log.e("Le choix de partager une note ", "Creation note"); 
					              Notes note = new Notes();
					              VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication(); 
					              vg.setNote(note);
					              NotesDAO.get(getActivity()).addNote(note);

					              Intent i = new Intent(getActivity(), CreateNotesActivity.class);
					               Log.e("ID note dans ListeNoteActivity  --> "+note.getIdNote(), "CreateNotesActivity"); 
					              i.putExtra(CreateNotesFragment.EXTRA_NOTE_ID, note.getIdNote());
					            
					              if(uuidBoard != null) {
					                    i.putExtra(CreateNotesFragment.EXTRA_ID_BORAD_CREATION_NOTES, uuidBoard); 
					              }else {
					            	 
					            	  uuidBoard =  vg.getUUUIDStringBoard();
					            	 
					            	  i.putExtra(CreateNotesFragment.EXTRA_ID_BORAD_CREATION_NOTES, uuidBoard); 
					              }
					             // i.putExtra(CreateNotesFragment.EXTRA_PHOTO_ACTION_EDIT, "KO");
					              
					              startActivityForResult(i, 0);
					              return true; 
					    case  R.id.bouton_retour:
					    	 NavUtils.navigateUpFromSameTask(getActivity());
			                 getActivity().finish(); 
					    	return true; 
			            default:
			            	System.out.println(item.getItemId());
			                return super.onOptionsItemSelected(item);
			        }
        
    }
    

    
    
    
    
    
}
