package org.cgi.poc;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class Board {

	/*
	 * ATTRIBUTS JSON
	 */
	
	private static final String JSON_ID = "id"; 
	private static final String JSON_TITRE = "titre";
	private static final String JSON_DESCRIPTION = "description";  
	
	
	
    private UUID idBoard;
	private String titleBoard;
	private String descriptionBorad; 
	
	 @SuppressWarnings("static-access")
	public  Board() {
		/*
		 * GENERATION D'UN ID AUTOMATIQUEMENT 
		 */
		  idBoard = UUID.randomUUID();
	}

	/*
	 * PERMETTRE LA SAUVEGARDE DE L'ENTITE BOARD  SOUS FORME D'UN OBJET JSON
	 */
     public Board(JSONObject json) throws JSONException {
    	 idBoard = UUID.fromString(json.getString(JSON_ID));
    	 if(json.has(JSON_TITRE)) {
    		 titleBoard = json.getString(JSON_TITRE);
    	 }
    	 if(json.has(JSON_DESCRIPTION)) {
    		 descriptionBorad = json.getString(JSON_DESCRIPTION); 
    	 }
     }
	
     /*
      * TRANSFORMATION D'UN OBJET BOARD A UN OBJET JSON
      */
     public JSONObject toJSON() throws JSONException {
    	 JSONObject jsonObject = new JSONObject(); 
    	 jsonObject.put(JSON_ID, idBoard.toString()); 
    	 jsonObject.put(JSON_TITRE, titleBoard); 
    	 jsonObject.put(JSON_DESCRIPTION, descriptionBorad); 
    	 return jsonObject; 
     }

     @Override
     public String toString() {
    	 return idBoard.toString(); 
     }
     
	public UUID getIdBoard() {
		return idBoard;
	}


	public void setIdBoard(UUID idBoard) {
		this.idBoard = idBoard;
	}


	public String getTitleBoard() {
		return titleBoard;
	}


	public void setTitleBoard(String titleBoard) {
		this.titleBoard = titleBoard;
	}


	public String getDescriptionBorad() {
		return descriptionBorad;
	}


	public void setDescriptionBorad(String descriptionBorad) {
		this.descriptionBorad = descriptionBorad;
	}
	
}
