package org.cgi.poc;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

/*
 * Cette classe est la classe mere de toutes les données manipulées par l'application
 */
public class DataDAO {

	 private static final String TAG = "DataDAO";
	 private static final String FILENEME = "data.json"; 
	 
	 private ArrayList<Data> mData; 	
	 private static DataDAO sDataDAO;
	 private Context mAppContext;
	 
	 private DataGestionJSONSerialisation mSerialisation; 

	    private DataDAO(Context appContext) {
	        mAppContext = appContext;
	       // mData = new ArrayList<Data>();
	        mSerialisation = new DataGestionJSONSerialisation(mAppContext, FILENEME); 
	        try {
	        	mData = mSerialisation.chargerDatas(); 
	        }catch(Exception e) {
	        	mData = new ArrayList<Data>(); 
	        	Log.e(TAG, "Erreur de chargement des DATA", e); 
	        }
	    }

	    public static DataDAO get(Context c) {
	        if (sDataDAO == null) {
	        	sDataDAO = new DataDAO(c.getApplicationContext());
	        }
	        return sDataDAO;
	    }

	    public Data getData(UUID id) {
	        for (Data d : mData) {
	            if (d.getId().equals(id))
	                return d;
	        }
	        return null;
	    }
	    
	    public void addData(Data d) {
	        mData.add(d);
	    }

	    public ArrayList<Data> getDatas() {
	        return mData;
	    }
	    
	    public ArrayList<Data> getDatasByBoard(String uuidBoard) {
	    	ArrayList<Data> dataBoard = new ArrayList<Data>();
	    	/*
	    	 * Selectionner que les data qui correspondent au board 
	    	 */
	    	
	    	  for (Data d : mData) {
		            if ((d.getBoardPhoto().getIdBoard().toString()).equals(uuidBoard)) {
		                  dataBoard.add(d); 	
		            }
		        }
	    	    return dataBoard; 
	    }

	    public void supprimerData(Data d) {
	        mData.remove(d);
	    }
	    
	    public void supprimerDataByBord (Data d, String idBoard) {
	    	
	    	  for (Data dd : mData) {
		            if (  dd.getId().equals(d.getId())) {
		            	mData.remove(d); 	
		            }
		        }
	    	
	    	
	    	//ArrayList<Data> dataRemove = DataDAO.get(mAppContext).getDatasByBoard(d.getBoardPhoto().getIdBoard().toString()); 
	    	//dataRemove.remove(d); 
	    }
		/*
		 * METHODE D'ENREGISTREMENT DES DATA  AU FORMAT JSON
		 */
	    public boolean enregistrerData() {
	    	try {
	    		mSerialisation.enregistrerData(mData); 
	    		return true; 
	    	}catch(Exception e) {
	    		Log.e(TAG, "Erreur dans l enregistrement ", e); 
	    		return false; 
	    	}
	    }
	    
	    public boolean enregsiterDataByArray(ArrayList<Data> ldata) {
	     	try {
	    		mSerialisation.enregistrerData(ldata); 
	    		return true; 
	    	}catch(Exception e) {
	    		Log.e(TAG, "Erreur dans l enregistrement ", e); 
	    		return false; 
	    	}
	    }
	    public boolean enregistrerDataByBoard(String uuidStringBoard) {
	    	   ArrayList<Data> dataBoard = new ArrayList<Data>(getDatasByBoard(uuidStringBoard));
	    		try {
		    		mSerialisation.enregistrerData(dataBoard); 
		    		return true; 
		    	}catch(Exception e) {
		    		Log.e(TAG, "Erreur dans l enregistrement by Board "+uuidStringBoard, e); 
		    		return false; 
		    	}
	    }
 }
