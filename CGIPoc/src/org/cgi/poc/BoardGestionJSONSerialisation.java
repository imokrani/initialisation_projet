package org.cgi.poc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;

public class BoardGestionJSONSerialisation {
	
	
	private Context boardContext; 
	private String boardFileName;
	
	public BoardGestionJSONSerialisation(Context boardContextCons, String boardFileNameCons) {
		boardContext = boardContextCons; 
		boardFileName = boardFileNameCons; 
	}

    /*
     * CGARGER LES BOARD  DEJA ENREGISTRES SOUS FORME DE FICHIER JSON
     */
	public ArrayList<Board> chargerBoards() throws IOException, JSONException {
		ArrayList<Board> listBoards = new ArrayList<Board>(); 
		
		BufferedReader bufferedReader = null; 
		
		try {
			InputStream in = boardContext.openFileInput(boardFileName); 
			bufferedReader = new BufferedReader(new InputStreamReader(in)); 
			StringBuilder jsonString = new StringBuilder(); 
			String line; 
			while((line = bufferedReader.readLine())!= null) {
				jsonString.append(line); 
			}
			JSONArray tableau = (JSONArray)new JSONTokener(jsonString.toString()).nextValue(); 
			for(int i = 0; i < tableau.length(); i ++) {
				listBoards.add(new Board(tableau.getJSONObject(i))); 
			}
		}catch(FileNotFoundException ex) {
			ex.printStackTrace(); 
		}finally {
			if(bufferedReader != null) {
				bufferedReader.close(); 
			}
		}
		
		return listBoards; 
	}
	
	/*
	 * SAUVEGARDER DES FICHIER JSON
	 */
	
	public void enregistrerBoard(ArrayList<Board> boards) throws IOException, JSONException {
		JSONArray listeOjejctJson = new JSONArray(); 
		for(Board board : boards) {
			listeOjejctJson.put(board.toJSON()); 
		}
		
		/*
		 * ECRITURE DU FICHIER SUR DISQUE 
		 */
		
		Writer writer = null;
		
		try{
			OutputStream out = boardContext.openFileOutput(boardFileName, Context.MODE_PRIVATE); 
			writer = new OutputStreamWriter(out); 
			writer.write(listeOjejctJson.toString()); 
		}finally {
			if(writer !=null) {
				writer.close(); 
			}
		}
	}
}
