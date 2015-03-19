package org.cgi.poc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class Data {
	
	/*
	 * PROPRIETE POUR LA SERIALISATION DE LA PHOTO 
	 */
	private static final String JSON_ID = "id";
	private static final String JSON_TITRE = "titre"; 
	private static final String JSON_DATE = "date"; 
	private static final String JSON_SOLVED = "solved"; 
	private static final String JSON_PHOTO = "photo"; 
	private static final String JSON_BOARD = "board"; 
	private static final String JSON_DESCRIPTION = "description"; 
	
	
	/*
	 * PROPRIETES PARTAGE PAR TOUT TYPE DE DATA    IMAGES   SONS   VIEDOS   TEXTES 
	 */
	    private UUID mId;
	    private String mTitle;
	    private String mDescription; 
	    private Date mDate;
	    private boolean mSolved;
	    private Photo maPhoto; 
	    private Board boardPhoto; 
	
	    @SuppressWarnings("static-access")
		public Data() {
	        mId = UUID.randomUUID();
		        /*
		         * FORMAT DE LA DATE
		         */
	           // android.text.format.DateFormat df = new android.text.format.DateFormat();
	           mDate = new Date();
	           SimpleDateFormat formater  = new SimpleDateFormat("'le' dd/MM/yyyy 'à' hh:mm:ss");
	           formater.format(mDate);
	            
		        
	    }
	    
	    /*
	     * PERMETTRE A L'OBJET DATA LA SERIALISATION AVEC L'OBJET JSONObject
	     * CELA VA PERMETTRE LA SAUVEGARDE DE LA DATA SOUS LA FORME D'UN OBJET JSON
	     */
	    public Data(JSONObject json) throws JSONException {
	        mId = UUID.fromString(json.getString(JSON_ID));
	        if(json.has(JSON_TITRE)) {
	        	mTitle = json.getString(JSON_TITRE);	
	        }
	        if(json.has(JSON_DESCRIPTION)) {
	        	mDescription = json.getString(JSON_DESCRIPTION); 
	        }
	        if(json.has(JSON_PHOTO)) {
	        	maPhoto = new Photo(json.getJSONObject(JSON_PHOTO)); 
	        }
	        if(json.has(JSON_BOARD)) {
	        	boardPhoto = new Board(json.getJSONObject(JSON_BOARD));
	        }
	        mSolved = json.getBoolean(JSON_SOLVED);
	        mDate = new Date(json.getLong(JSON_DATE));
	    }
	
	    /*
	     * TRANSFORMATION DE LA DATA EN AUN OBJET JSON
	     */
	    public JSONObject toJSON() throws JSONException {
	    	/*
	    	 * CREER UN OBJET JSON
	    	 */
	        JSONObject json = new JSONObject();
	        /*
	         * INITIALISER L'OBJET JSON
	         */
	        json.put(JSON_ID, mId.toString());
	        json.put(JSON_TITRE, mTitle);
	        json.put(JSON_DATE, mDate.getTime());
	        json.put(JSON_SOLVED, mSolved);
	        json.put(JSON_DESCRIPTION, mDescription); 
	        if(maPhoto != null) {
	        	json.put(JSON_PHOTO, maPhoto);
	        }
	        if(boardPhoto != null) {
	        	json.put(JSON_BOARD, boardPhoto); 
	        }
	        return json;
	    }
	    
	  @Override
	    public String toString() {
	        return mTitle;
	    }

	  /*
	   * Getter et setter
	   */
	    public String getTitle() {; 
	        return mTitle;
	    }

	    public void setTitle(String title) {
	        mTitle = title;
	    }

	    public UUID getId() {
	        return mId;
	    }

	    public boolean isSolved() {
	        return mSolved;
	    }

	    public void setSolved(boolean solved) {
	        mSolved = solved;
	    }

	    public Date getDate() {
	        return mDate;
	    }

	    public void setDate(Date date) {
	        mDate = date;
	    }

		public Photo getMaPhoto() {
			return maPhoto;
		}

		public void setMaPhoto(Photo maPhoto) {
			this.maPhoto = maPhoto;
		}

		public String getmDescription() {
			return mDescription;
		}

		public void setmDescription(String mDescription) {
			this.mDescription = mDescription;
		}

		public Board getBoardPhoto() {
			return boardPhoto;
		}

		public void setBoardPhoto(Board boardPhoto) {
			this.boardPhoto = boardPhoto;
		}	    
}
