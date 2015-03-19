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

/*
 * LA RESPONSABILITE DE CETTE CLASSE EST DE RECUPERER LES DONNEES DE L'OBET DATA ET LES SAUVEGARDER SOUS FORME D'UNE FICHIER
 */
public class DataGestionJSONSerialisation {

	private Context mContext; 
	private String mFileName; 
	
	/*
	 * LORS DE L'INSTANCIATION DE CETTE CLASSE POUR CREER LE FICHIER JSON QUI CORRESPONDRA AU DONNE DE DATA 
	 * FOURNIR LE CONTEXT  ANDROID 
	 * FOURNIR LE NOM DU FICHIER JSON QUI SERA GENERE
	 */
	public DataGestionJSONSerialisation(Context c, String nomFichier) {
		mContext = c; 
		mFileName = nomFichier; 
	}
	
	public ArrayList<Data> chargerDatas() throws IOException, JSONException{
		ArrayList<Data> donnees = new ArrayList<Data>();
	    /*
	     * PERMET LA LECTURE DES CARACTERE A PARTAIRE D'UNE MEMOIRE TAMPONE  
	     */
		BufferedReader reader = null; 
	    try {
	    	/*
	    	 * OUVERTURE D'UN FLUX DE LECTURE ECRITURE DU LE FICHIER  QUE L'ON SOIUHAITE CREER 
	    	 */
	    	InputStream in = mContext.openFileInput(mFileName); 
	    	reader = new BufferedReader(new InputStreamReader(in)); 
	    	StringBuilder jsonString = new StringBuilder(); 
	    	String line = null; 
	    	
	    	while ((line = reader.readLine())!= null) {
	    		jsonString.append(line);
	    	}
	    	
	    	JSONArray  tableau = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
	    	
	    	for(int i = 0; i< tableau.length() ;i ++ ) {
	    		donnees.add(new Data(tableau.getJSONObject(i))); 
	    	}
	    	
	    	
	    }catch(FileNotFoundException ex) {
	    	ex.printStackTrace(); 
	    }finally {
	    	if(reader != null)
	    		 reader.close(); 
	    }
		return donnees; 
	}
	
	
	public void enregistrerData(ArrayList<Data> donnees) throws IOException, JSONException {
		/*
		 * CONSTRUITE UNE TABLEAU D'OBJET JSON 
		 */
		JSONArray  listeObjetJson = new JSONArray(); 
		for(Data donnee : donnees) {
			listeObjetJson.put(donnee.toJSON()); 
		}
		
		/*
		 * ECRIRE LE FICHIER JSON SUR DISQUE 
		 */
		Writer writer = null; 
		try {
	             OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
	            writer = new OutputStreamWriter(out);
	            writer.write(listeObjetJson.toString());
		}finally {
			if(writer != null)
				 writer.close(); 
		}
	}
}
