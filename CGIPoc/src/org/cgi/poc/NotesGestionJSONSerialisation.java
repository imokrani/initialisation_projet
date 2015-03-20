package org.cgi.poc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

public class NotesGestionJSONSerialisation {

	
	private Context context; 
    private String nomFichierNotes; 	
    
    
    public NotesGestionJSONSerialisation(Context c, String nomFichierJsonNotes) {
    	context = c; 
    	nomFichierNotes = nomFichierJsonNotes; 
    }
    
    /*
     * Chargement des notes 
     */
    
    
    public ArrayList<Notes> chargerNotes() throws IOException, JSONException {
    	    ArrayList<Notes> toutesNotes = new ArrayList<Notes>(); 
    	    Log.e("Charger le fichier JSON contient toutes les notes ", "NotesGestionJSONSerialisation");
    	    BufferedReader reader = null; 
    	    try {
    	    	InputStream in = context.openFileInput(nomFichierNotes); 
    	    	reader = new BufferedReader(new InputStreamReader(in)); 
    	    	StringBuilder jsonString = new StringBuilder(); 
    	    	String line = null; 
    	    	
    	    	while ((line = reader.readLine())!= null) {
    	    		jsonString.append(line);
    	    	}
    	    	
    	    	JSONArray  tableau = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
    	    	
    	    	for(int i = 0; i< tableau.length() ;i ++ ) {
    	    		toutesNotes.add(new Notes(tableau.getJSONObject(i))); 
    	    	}
    	    }catch(FileNotFoundException ex) {
    	    	Log.e("Le fichier notes.json   n est pas retrouvee ", "Probleme de chargement du fichier JSON"); 
    	    }finally {
    	    	if(reader != null) {
    	    		reader.close(); 
    	    	}
    	    }
    	    
    	   return toutesNotes;  
    	    
    }
    
	
	public void enregistrerNote(ArrayList<Notes> notes) throws IOException, JSONException {
		Log.e("Lancer l'enregistrement de la liste des  notes "," enregistrerNote"); 
		JSONArray  listeObjetJson = new JSONArray(); 
		for(Notes note : notes) {
			listeObjetJson.put(note.toJSON()); 
		}
		
		/*
		 * ECRIRE LE FICHIER JSON SUR DISQUE 
		 */
		Writer writer = null; 
		try {
	             OutputStream out = context.openFileOutput(nomFichierNotes, Context.MODE_PRIVATE);
	             writer = new OutputStreamWriter(out);
	             writer.write(listeObjetJson.toString());
		}finally {
			if(writer != null)
				 writer.close(); 
		}
	}
}
