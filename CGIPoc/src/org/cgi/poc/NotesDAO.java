package org.cgi.poc;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

public class NotesDAO {

	 private static final String TAG = "NotesDAO";
	 private static final String FILENEMENOTES = "notes.json";
	 
	 private ArrayList<Notes> mNotes; 	
	 private static NotesDAO sNotesDAO;
	 private Context mAppContext;
	 private  NotesGestionJSONSerialisation mSerialisationNotes;
	 
	 
	 public NotesDAO(Context c) {
		 mAppContext = c; 
		 mSerialisationNotes = new NotesGestionJSONSerialisation(mAppContext, FILENEMENOTES); 
		 try {
			 mNotes = mSerialisationNotes.chargerNotes(); 
		 }catch(Exception ex) {
			 //Dans le cas ou il ne y a pas de notes a recupérer depuis le fichier json
			 mNotes = new ArrayList<Notes>(); 
			 //Ou  erreur de chargement des Data
			 
		 }
	 }
	 
	 
	  public static NotesDAO get(Context c) {
	        if (sNotesDAO == null) {
	        	sNotesDAO= new NotesDAO(c.getApplicationContext());
	        }
	        return sNotesDAO;
	    }
	 
	  public Notes getNote(UUID id) {
	        for (Notes n : mNotes) {
	            if (n.getIdNote().equals(id))
	                return n;
	        }
	        return null;
	    }

	  public void addNote(Notes n) {
	        mNotes.add(n);
	    }

	    public ArrayList<Notes> getNotes() {
	        return mNotes;
	    }
	    
	    public ArrayList<Notes> getNotesByBoard(String uuidBoard) {
	    	ArrayList<Notes> notesBoard = new ArrayList<Notes>();
	    	/*
	    	 * Selectionner que les notes qui correspondent au board 
	    	 */
	    	
	    	  for (Notes d : mNotes) {
		            if ((d.getBoardNote().getIdBoard().toString()).equals(uuidBoard)) {
		                  notesBoard.add(d); 	
		            }
		        }
	    	    return notesBoard; 
	    }

	    public void supprimerData(Notes n) {
	        mNotes.remove(n);
	    }
	    
	    public void supprimerDataByBord (Notes n, String idBoard) {
	    	
	    	  for (Notes nn : mNotes) {
		            if (  nn.getIdNote().equals(n.getIdNote())) {
		            	mNotes.remove(nn); 	
		            }
		        }
	    }
		
	    public boolean enregistrerNote() {
	    	try {
	    		mSerialisationNotes.enregistrerNote(mNotes); 
	    		return true; 
	    	}catch(Exception e) {
	    		Log.e(TAG, "Erreur dans l enregistrement ", e); 
	    		return false; 
	    	}
	    }
	    
	    public boolean enregsiterNoteByArray(ArrayList<Notes> lNotes) {
	     	try {
	    		mSerialisationNotes.enregistrerNote(lNotes); 
	    		return true; 
	    	}catch(Exception e) {
	    		Log.e(TAG, "Erreur dans l enregistrement ", e); 
	    		return false; 
	    	}
	    }
	  
	    public boolean enregistrerNotesByBoard(String uuidStringBoard) {
	    	   ArrayList<Notes> notesBoard = new ArrayList<Notes>(getNotesByBoard(uuidStringBoard));
	    		try {
		    		mSerialisationNotes.enregistrerNote(notesBoard); 
		    		return true; 
		    	}catch(Exception e) {
		    		Log.e(TAG, "Erreur dans l enregistrement by Board "+uuidStringBoard, e); 
		    		return false; 
		    	}
	    }

}
