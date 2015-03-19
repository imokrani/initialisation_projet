package org.cgi.poc;

import org.json.JSONException;
import org.json.JSONObject;

public class Photo {

	private static final String JSON_NOM_PHOTO ="nomPhoto"; 
	
	
	private String nomPhoto; 
	
	public Photo(String nomPhotoConstructeur) {
		this.nomPhoto = nomPhotoConstructeur; 
	}
	
	public Photo(JSONObject jsonObject) throws JSONException {
		nomPhoto = jsonObject.getString(JSON_NOM_PHOTO); 
	}
	
	public JSONObject toJSON() throws JSONException{
		JSONObject jsonObject = new JSONObject(); 
		jsonObject.put(JSON_NOM_PHOTO, nomPhoto); 
		return jsonObject; 
	}

	public String getNomPhoto() {
		return nomPhoto;
	}	
}
