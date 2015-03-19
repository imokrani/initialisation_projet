package org.cgi.poc;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

public class BoardDAO {
   
	public static final String TAG = "BoardDAO"; 
	
	public static final String FILENAME = "boards.json";
	
	private ArrayList<Board> boards; 
	private static BoardDAO sBoardDAO;
	private Context appContext; 
	private BoardGestionJSONSerialisation boardsSerialisation; 
	
	private BoardDAO(Context appContextCons) {
		appContext =  appContextCons;
		boardsSerialisation = new BoardGestionJSONSerialisation(appContext, FILENAME); 
		try{
			boards = boardsSerialisation.chargerBoards(); 
		}catch(Exception ex) {
			boards = new ArrayList<Board>(); 
			Log.e(TAG, " --> Erreur de chargement des boards"); 
		}
	}
	
	public static BoardDAO get(Context c) {
		if(sBoardDAO == null) {
			sBoardDAO = new BoardDAO(c.getApplicationContext()); 
		}
		return sBoardDAO; 
	}
	
	public Board getBoard(UUID id) {
		for(Board b : boards) {
			if(b.getIdBoard().equals(id)) {
				return b; 
			}
		}
		return null; 
	}
	
	
	public void addBoard(Board b) {
		boards.add(b); 
	}
	
	public ArrayList<Board> getBoards() {
		return boards; 
	}
	
	public void supprimerBoard(Board b) {
		boards.remove(b); 
	}
	
	/*
	 * POUR PERSISTER UNE DONNEE NOUS ALLONS UTILISER LA SERIALISATION S AVEC JSON
	 */
	public boolean enregistrerBoard() {
		try {
			boardsSerialisation.enregistrerBoard(boards); 
			return true;
		}catch(Exception e) {
			Log.e(TAG, " --> Erreur d enredistrement de BOARD");
			return false; 
		}
	}
}
