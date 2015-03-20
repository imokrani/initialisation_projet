package org.cgi.poc;

import java.util.Date;
import java.util.UUID;

import org.cgi.poc.R.id;
import org.json.JSONException;
import org.json.JSONObject;

public class Notes {

     /*
      * JSON
      */
	private static final String JSON_ID_NOTE = "id"; 
	private static final String JSON_TITRE_NOTE = "titre";
	private static final String JSON_CONTENU_NOTE = "contenu"; 
	private static final String JSON_COULEUR_NOTE = "couleur";
	private static final String JSON_DATE_CREATION = "creation";
	private static final String JSON_DATE_MODIFICATION = "modification";
	private static final String JSON_BOARD = "board"; 
	
	 private UUID idNote; 
     private String titreNote; 
     private String contenuNote; 
     private String couleur; 
     private Date dateCreation; 
     private Date dateModification; 
     private Board boardNote;
     
     public Notes() {
    	 idNote = UUID.randomUUID(); 
    	 dateCreation = new Date(); 
    	 dateModification = new Date(); 
     }
     
     
     public Notes(JSONObject json) throws JSONException {
    	    idNote = UUID.fromString(json.getString(JSON_ID_NOTE));
	        if(json.has(JSON_TITRE_NOTE)) {
	        	titreNote = json.getString(JSON_TITRE_NOTE);	
	        }
	        if(json.has(JSON_CONTENU_NOTE)) {
	        	contenuNote = json.getString(JSON_CONTENU_NOTE); 
	        }
	        if(json.has(JSON_COULEUR_NOTE)) {
	        	couleur   = json.getString(JSON_COULEUR_NOTE); 
	        }
	        
	        if(json.has(JSON_DATE_CREATION)) {
	        	dateCreation = new Date(json.getLong(JSON_DATE_CREATION)) ;
	        }
        	if(json.has(JSON_DATE_MODIFICATION)) {
        		dateModification = new Date(json.getLong(JSON_DATE_MODIFICATION)) ;
	        }
        	if(json.has(JSON_BOARD)) {
        		boardNote =  new Board(json.getJSONObject(JSON_BOARD));
        	}
        

     }
     
     
     @Override
	    public String toString() {
	        return titreNote;
	    }
     
     
     
     public JSONObject toJSON() throws JSONException {
	    	
	        JSONObject json = new JSONObject();
	        json.put(JSON_ID_NOTE, idNote.toString());
	        json.put(JSON_TITRE_NOTE, titreNote);
	        json.put(JSON_CONTENU_NOTE, contenuNote);
	        json.put(JSON_COULEUR_NOTE, couleur);
	        json.put(JSON_DATE_CREATION, dateCreation.getTime()); 
	        json.put(JSON_DATE_MODIFICATION, dateModification.getTime()); 
	   	 	if(boardNote != null) {
	        	json.put(JSON_BOARD, boardNote); 
	        }
	        return json;
	    }


	public UUID getIdNote() {
		return idNote;
	}


	public void setIdNote(UUID idNote) {
		this.idNote = idNote;
	}


	public String getTitreNote() {
		return titreNote;
	}


	public void setTitreNote(String titreNote) {
		this.titreNote = titreNote;
	}


	public String getContenuNote() {
		return contenuNote;
	}


	public void setContenuNote(String contenuNote) {
		this.contenuNote = contenuNote;
	}


	public String getCouleur() {
		return couleur;
	}


	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	public Date getDateModification() {
		return dateModification;
	}


	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}


	public Board getBoardNote() {
		return boardNote;
	}


	public void setBoardNote(Board boardNote) {
		this.boardNote = boardNote;
	}
}
