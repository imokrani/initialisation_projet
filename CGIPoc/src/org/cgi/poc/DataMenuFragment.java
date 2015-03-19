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
import java.util.UUID;

import org.cgi.poc.utils.UtilsPhoto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;




import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

/*
 * FRAGEMENT DYNAMIQUE QUI AFFICHE   EST LE MENU DE L'APPLICATION  
 * ET  LA LISTE DES IMAGE CREE DANS LA PAGE D'ACCEUIL 
 * HERITE DE LA CLASSE LISTFRAGEMENT   CAR ELLE GENERE UNE LIST DE FRAGEMENT
 */
public class DataMenuFragment extends ListFragment {

    private ArrayList<Data> mDatas;
    private boolean mSubtitleVisible; 
    VaiablesGlobales vg; 
    
    public static final String EXTRA_ID_BORAD_ASSOCIE_PHOTO = "ID_BOARD_ASSOCIE_PHOTO"; 
    public static final int CODE_REQUETE_ENREGISTRER_NOUVELLE_IMAGE = 0; 
    ImageView miniatureImage; 
    String uuidBoard = null; 
    SharedPreferences.Editor prefs;
    SharedPreferences preferences;
    /*
     * CE FRAGMENT N'A POUR RESPONSABLITE QUE POUR CREER LA VUE DE L ACTIVITE   DATAMENUACTIVITY
     * ACTIVITY QUI AFFICHE A LA FOIS LE MENU ET LA PAGE D'ACCEUIL DE L'APPLICATION
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	/*
    	 *  INSTANCIATION DE LA VUE DE L ACTIVITE 
    	 */
        super.onCreate(savedInstanceState);
        /*
         *  INDIQUER QUE CETTE ACTIVITE GERE LE MENU   DEFINI DANS LES FICHIER XML OU MENU PAR DEFAUT "HOME"
         *  CELA IMPOSE L'IMPLIMENTATION DES  METHODES
         *  onOptionsItemSelected : GERER LA SELECTION DES MENU 
         *  onCreateOptionsMenu   : RECUPERER LE TEMPLATE DU MENU DEFINI DANS LE XML
         */
        setHasOptionsMenu(true);
        
        /*
         * RECUPERATION DE L'ACTIVITE LIEE A CE FRAGEMENT  ASSIGNER UN TITRE A CETTE ACTIVITE 
         */
      
        
        /*
         * RECUPERATION DE LA LISTE DES DONNEE A AFFICHIER DANS LA PAGE D'ACCEUIL SOUS FORME D'UNE DE CHAINES
         * DE CARACTERES OU AUTRE OBJET COMPLEXES
         */
        
        //TODO   ne pas charger toutes les donnees il faut prendre que les donnne liees au board 
        Log.e("AFFICHAGE DE LA LISTE DES PHOTO PRISE DANS  ADAPTER ARRAY  ---->  ", ""+ uuidBoard);
        /*
         * Recuperation de l'id board 
         */
    	Intent intentParam = getActivity().getIntent(); 
        uuidBoard =intentParam.getStringExtra(EXTRA_ID_BORAD_ASSOCIE_PHOTO); 

       
       // VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();
         vg = (VaiablesGlobales)getActivity().getApplication();
        if(uuidBoard != null) {
        	vg.setUUUIDStringBoard(uuidBoard); 
        }else {
        	uuidBoard = vg.getUUUIDStringBoard();
        }
        Board board = BoardDAO.get(getActivity()).getBoard(UUID.fromString(uuidBoard)); 
        getActivity().setTitle(board.getTitleBoard() +":"+"Images("+DataDAO.get(getActivity()).getDatasByBoard(uuidBoard).size()+")");
        
        Log.e(" Le UUID DU  BOARD "+board.getTitleBoard()+"EST    -->  ", " :  "+uuidBoard); 
        
    	mDatas = DataDAO.get(getActivity()).getDatasByBoard(uuidBoard);
     
    	
        //mDatas = DataDAO.get(getActivity()).getDatas();
        
        /*
         * CETTE CLASSE HERITE DE LA CLASSE ARRAYADAPER QUI FAIT LE BINDING ENTRE UNE LIST D'OBJETS JAVA 
         * ET LES WEDJETS ANDROID
         * A PARTIR D'UNE LISTE DE DONNEES CETTE CLASSE PEUT GENERER UNE VUE 
         * C'EST TRES UTILE DANS LE CAS DES DATAGRID 
         * CE COMPOSANT PEUT RENDRE LES ELEMENT CLIQUABLE GENERE UN CERTAIN NOMBRE D'EVENEMENTS
         * RAISON POUR LAQUELLE NOUS AVONS DEFINIT CE FRAGEMENT COMME LISTFRAGEMENT POUR RECUPERER LISTVIEW
         */
        DataAdapter adapter = new DataAdapter(mDatas);
        /*
         * AJOUTER CET ADAPTATEUR A NOTRE LISTFRAGMENT
         */
        setListAdapter(adapter);
        setRetainInstance(true);
        mSubtitleVisible = false;
    }
    
    @SuppressWarnings("unused")
	private boolean sauvegradeUUIDBoard( String uuidBoard_) throws IOException, JSONException {
    	Writer writer =null; 
    	try{
    		OutputStream out  = getActivity().openFileOutput("uuid_board.json", Context.MODE_PRIVATE);
    		 writer = new OutputStreamWriter(out);
    		 JSONObject uuidJson = new JSONObject(); 
    		 uuidJson.put("UUIDBOARD", uuidBoard_); 
    		 writer.write(uuidJson.toString()); 
    		 return true; 
    	}finally {
    		 if(writer != null) {
    			 writer.close();
    		 }
    	}
    	
    }
    
    private String recupererUUIDBoard() throws IOException, JSONException {
            String UUIDBoard = null; 
    		BufferedReader reader = null;
		    try{
		    	InputStream in = getActivity().openFileInput("uuid_board.json"); 
		    	reader = new BufferedReader(new InputStreamReader(in));
		    	StringBuilder jsonString = new StringBuilder(); 
		    	String line = null; 
		    	while ((line = reader.readLine())!= null) {
		    		jsonString.append(line);
		    	}
		    	
		    	JSONArray  tableau = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
		    	JSONObject json =  tableau.getJSONObject(0);
		    	if(json.has("UUIDBOARD")) {
		    		UUIDBoard = json.getString("UUIDBOARD"); 
		    	}
		    	return UUIDBoard; 
		    }catch(FileNotFoundException ex) {
		    	ex.printStackTrace(); 
		    }finally {
		    	if(reader != null)
		    		 reader.close(); 
		    }
		    	return null;
    }
    
    /*
     * LES FRAGEMENT FONCTIONNENT DE MANIERE DIFFERENTES PAR RAPPORT AUX ACTIVITE   EN PELUS DU ONCREATE IL FAUT IMPLEMENTER ONCREATEVIEW 
     * C EST CETTE METHODE QUI CREE LA VUE ET LA RENVOIE A L ACTIVITE A LAQUEL LE FRAGEMENT EST ATTACHE
     */
    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    	
    	/*
    	 * INSTANCIE TOUS LES OBJETS NECESSAIRES A LA CREATION DE LA VUE 
    	 */
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        
        
        
        /*
         * ListView   EST UN CONTENEUR PERMETTATN D'AFFICHER DES DONNEES SOUS FORME D"UNE LISTE 
         * LA CLE    android.R.id.list     EST UTILISEE POUR RECUPERER LA LISTVIEW UTILISEE  PAR LE FRAGEMENT 
         */
        ListView listView = (ListView)v.findViewById(android.R.id.list);
       
        
        /*
         * VERIFICATION DE LA VERSION DU SDK POUR PERMETTRE OU PAS CERTAINE FONCTIONS SELON LES VERSION CIBLEES PAR L'APPLICATION
         */
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {   
//            if (mSubtitleVisible) {
//                getActivity().getActionBar().setSubtitle(R.string.subtitle);
//            }
        	/*
        	 * UTILISER UN MENU CONTEXTUEL DETACHE DE LA BARE D'ACTION
        	 */
        	 registerForContextMenu(listView);
        }else {
        	/*
        	 * UTILISER UN MENU CONTEXTUEL ACTION BARE  QUI APPARAIT SUR LA BARRE D'ACTION 
        	 */
        	 
        	/*
        	 * DEFINIR LE MODE DE SELECTION SUR LA LISTVIEW
        	 */
        	listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL); 
        	
        	/*
        	 * DEFINIR LES DIFFERENT LISTNER LIES A L'ACTION SUR LA LISTVIEW
        	 */
        	listView.setMultiChoiceModeListener(
                     new MultiChoiceModeListener() {
						
                    	/*
                    	 * CREATION DE MENU CONTEXTUEL SUR LA BARE D ACTION A PARTIR D UN TEMPLATE 
                    	 */
                    	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
 							MenuInflater inflater = mode.getMenuInflater(); 
 							inflater.inflate(R.menu.fragment_menu_suppression_application, menu); 
 							return true;
 						}
                    	
                    	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
							return false;
						}
						
						public void onDestroyActionMode(ActionMode mode) {
							
						}
						
						/*
						 * QUEL MENU CONTEXTUEL CHOISI PAR L UTILISTAUER 
						 */
						public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
							switch (item.getItemId()) {
							case R.id.menu_item_supprimer_data:
								 DataAdapter adaptateur = (DataAdapter)getListAdapter();
								 DataDAO dataDAO = DataDAO.get(getActivity());
								 for (int i = adaptateur.getCount() - 1; i >= 0; i--) {
									 if(getListView().isItemChecked(i)) {
										 
										  dataDAO.supprimerData(adaptateur.getItem(i)); 
										  //dsfdsfdsf
										  Data donnee = adaptateur.getItem(i); 
										  for(Data dataTemp : mDatas) {
											  if(dataTemp.getId().equals(donnee.getId())){
												  
												  DataDAO.get(getActivity()).supprimerData(dataTemp);
												  mDatas.remove(dataTemp);
											 }
										  }
										  

									 }
								 }
								 getActivity().setTitle(BoardDAO.get(getActivity()).getBoard(UUID.fromString(uuidBoard)).getTitleBoard() +" / "+" Images( "+DataDAO.get(getActivity()).getDatasByBoard(uuidBoard).size()+" )");
								 mode.finish(); 
								 adaptateur.notifyDataSetChanged(); 
								 return true;
							case R.id.menu_item_edit_data:
								return true;
							default:
								return false;
							}	
						}
						
						public void onItemCheckedStateChanged(ActionMode mode, int position,
								long id, boolean checked) {							
						}
						
					});
        }
         
        /*
         * RENVOYER LA VUE
         */
        return v;
    }
    
    /*
     * PERMET DE SAVOIR QUEL ELEMENT DE L ADAPTATEUR A ETE SELECTIONNE
     * LANCER L'ACTIVITE  DataPagerActivity  POUR EDITER L ELEMENENT 
     * MODIFIER L ELEMENENT DANS L ACTIVITE FILLE  DataPagerActivity
     * ECOUTER LA REPONSE DE L'ACTIVITE DataPagerActivity
     */
    public void onListItemClick(ListView l, View v, int position, long id) {
        /*
         * RECUPERATION DE LA LIGNE SELECTIONNEE
         */
        Data donnee = ((DataAdapter)getListAdapter()).getItem(position);
        /*
         * PREPARER UN INTENT POUR LANCER UNE ACTICVITE DataPagerActivity  QUI PERMET D AFFICHER UNE LIGNE DANS UN FORMULAIRE 
         */
        Intent i = new Intent(getActivity(), DataPagerActivity.class);
        i.putExtra(DataFragment.EXTRA_PHOTO_ACTION_EDIT, "OK");
        /*
         * PASSAGE DE PARAMETRE POUR L'ACTIVITE APPELEE
         */
        i.putExtra(DataFragment.EXTRA_DATA_ID, donnee.getId());
       // VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication(); 
        i.putExtra(DataFragment.EXTRA_ID_BORAD_CREATION_PHOTO, vg.getUUUIDStringBoard()); 
        /*
         * LANCER UN APPEL A L ACTIVITE AVEC LA startActivityForResult  QUI PERMET D'AVOIR UN RETOUR SUR CETTE REQUETE
         * 0 / EST LE CODE DE LA REQUETE QUI PERMETTRA A L'ACTIVITE FILLE DE NOUS RENVOYER UNE REPONSE AVEC LA METHODE SENDRESULT
         * AU NIVEAU DE CETTE ACTIVITE NOUS POUVONS TRAITER LA REPONSE AVEC LA METHODE onActivityResult  
         */
        startActivityForResult(i, 0);
    }

    /*
     * CETTE METHODE PERMET DE RECOLTER DES INFORMATION SUR L'ACTIVITE FILLE LANCEE  OU BIEN SUR LA SOUS ACTIVITE 
     * LA CONDITION POUR IMPLEMENTER CETTE METHODE EST QUE LA ACTIVITE FILLE DOIT ETRE LANCEE AVEC LA METHODE startActivityForResult
     * ET NON PAS AVEC LA METHODE startActivity
     * ROLE : NOTIFICATION SUR LES MODIFICATION APPORTEES SUR LE CONTENUR DE L ADAPTATEUR 
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    
        ((DataAdapter)getListAdapter()).notifyDataSetChanged();
        
        /*
         * genrer le bouton retour 
         */
        if(requestCode == Activity.RESULT_CANCELED) {
        	         Log.e("Bouton RETOUR ", " "); 
        	         
			       //	 VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();
			          
			       	 //Data d = vg.getData();
        	         
			       	Log.e("Data ", " "+vg.getData());
			       	 DataDAO.get(getActivity()).supprimerDataByBord(vg.getData(), vg.getUUUIDStringBoard());  
 			       	 
       }
        
    }
    
    /*
     * CREER LES MENU DEPUIS LES FICHIER XML 
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
       // inflater.inflate(R.menu.fragment_menu_application, menu);
        inflater.inflate(R.menu.fragment_menu_icons_application, menu);
        
//        MenuItem showSubtitle = menu.findItem(R.id.menu_item_show_subtitle);
//        if (mSubtitleVisible && showSubtitle != null) {
//            showSubtitle.setTitle(R.string.hide_subtitle);
//        }
    }
    
    
    /*
     * ECOUTER LES MENU + ACTIONS SUR LE MENU CHOISI 
     */
    @TargetApi(11)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        switch (item.getItemId()) {
			        
        				case android.R.id.home:
        					 Log.e("LE menu est clike", "************************************************"); 
        					 NavUtils.navigateUpFromSameTask(getActivity());
        	                 getActivity().finish(); 
        					return true; 

			            /*
			             * LE MENU   PARTAGER       -->  IMAGES    
			             */
			            case R.id.partager_photo:
			            	      /*
			            	       * CREER UNE NOUVELLE DATA AVEC UN ID   ASSOCIE  ALEATOIREMENT 
			            	       */ 
					              Data data = new Data();
				            	  Log.e("FORMULAIRE DE CREATION DE LA POHOTO", " ---->  idPhoto =  "+data.getId());
					              
					              /*
					               * 
					               */
					              
					              vg.setData(data);
					              Log.e("RECUPERATION DE IdPhoto depuis la Data", " ---->  idPhoto =  "+vg.getData().getId());
					              
					              /*
					               * AJOUTER LA DATA DANS UNE LISTE VISIBLE PAR L'ACTIVITE
					               */
					              DataDAO.get(getActivity()).addData(data);
					              /*
					               * PREPARER L'APPEL DE L'ACTIVITY DataActivity   FORMULAIRE DE CREATION DES DATA
					               */
					              Intent i = new Intent(getActivity(), DataActivity.class);
					              /*
					               * PASSER LES PARAMETRE A L'ACTIVITE DataActivity    NOTAMMENT   IDDATA
					               * EXTRA_DATA_ID /  IDENTIFIANT DE LA DONNEE A CREER   DANS LE FORMULAIRE DE L'ACTIVITE   DataActivity
					               */
					            
					              i.putExtra(DataFragment.EXTRA_DATA_ID, data.getId());
					            
					              if(uuidBoard != null) {
					                    i.putExtra(DataFragment.EXTRA_ID_BORAD_CREATION_PHOTO, uuidBoard); 
					              }else {
					            	 
					            	  uuidBoard =  vg.getUUUIDStringBoard();
					            	 
					            	  i.putExtra(DataFragment.EXTRA_ID_BORAD_CREATION_PHOTO, uuidBoard); 
					              }
					              i.putExtra(DataFragment.EXTRA_PHOTO_ACTION_EDIT, "KO");
					              /*
					               * DEMARER L'ACTIVITE DataActivity  AVEC UNE POSSIBILITE D'AVOIR UN RETOUR startActivityForResult 
					               */
					              startActivityForResult(i, CODE_REQUETE_ENREGISTRER_NOUVELLE_IMAGE);
					              return true; 
			          /*
			           * LE MENU   SETTINGS    
			           */  
					    case  R.id.bouton_retour:
					    	 NavUtils.navigateUpFromSameTask(getActivity());
					    	 getActivity().getIntent().putExtra("XX", "10");
			                 getActivity().finish(); 
					    	return true; 
//			            case R.id.menu_item_show_subtitle:
////				            	if (getActivity().getActionBar().getSubtitle() == null) {
////				                    getActivity().getActionBar().setSubtitle(R.string.subtitle);
////				                    mSubtitleVisible = true;
////				                    item.setTitle(R.string.hide_subtitle);
////				            	}  else {
////				            		getActivity().getActionBar().setSubtitle(null);
////				            		 mSubtitleVisible = false;
////				            		//item.setTitle(R.string.show_subtitle);
////				            		 getActivity().finish(); 
////				            	}
//				            	
//				                return true;
			            /*
			             * SI AUCUN ELEMENT DE MENU EST CONCERNE    
			             */  
			            default:
			                return super.onOptionsItemSelected(item);
			        }
        
    }
    
    /*
     * DECLARATION DE L'ADAPTATEUR SOUS FORME D'UNE CLASSE INTERNE 
     */
    private class DataAdapter extends ArrayAdapter<Data> {
    	/*
    	 * DECLARATION D'UN CONSTRUCTEUR QUI PREND EN PARAMETRE UNE LISTE DE DONNEES A FORMATER 
    	 * simple_list_item_1 / IDENTIFIANT DU FICHIER XML A UTILISER COMME MODELE POUR LA LISTE   PERSONNALISEE
    	 */
        public DataAdapter(ArrayList<Data> datas) {
            super(getActivity(), android.R.layout.simple_list_item_1, datas);
        }

        /*
         * METHODE DE L'ADAPTATEUR PERMETTANT DE GENERER LA VUE 
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            /*
             * SI LA VUE N EST PAS DEJA FORMATEE :  FORMATER LA AVEC UN LAYOUT      list_item_crime
             */
            if (null == convertView) {
              //  convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            	convertView = getActivity().getLayoutInflater().inflate(R.layout.accueil_list_images, null);
            	
            }

            /*
             *  CONFIGURER LA VUE POUR CHQUE ELEMENT DE LA LISTE DE LA MANIERE SUIAVNTE  
             */
            Data c = getItem(position); 
            
            
            
            
            TextView titleTextView = (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            
            TextView description = (TextView)convertView.findViewById(R.id.data_list_description); 
            description.setText(c.getmDescription());
            
            //TextView dateTextView = (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
           // dateTextView.setText(c.getDate().toString());
             miniatureImage = (ImageView)convertView.findViewById(R.id.photo_previsualisation_accueil); 
            /*
             * AFFICHER LA MINIATURE 
             */
             Photo photo = c.getMaPhoto(); 
     		BitmapDrawable bitmapDrawable = null; 
     		if(photo != null) {
     			String path = getActivity().getFileStreamPath(photo.getNomPhoto()).getAbsolutePath();
     			bitmapDrawable = UtilsPhoto.getScaledDrawable(getActivity(), path);
     		}
     		miniatureImage.setImageDrawable(bitmapDrawable);
             
           /*
            * Remplace le checkbox par un picto  
            */
           // CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            //solvedCheckBox.setChecked(c.isSolved());
        	ImageView pictoPartegerServeurPicto_ok = (ImageView)convertView.findViewById(R.id.picto_partager_serveur_picto_ok) ;

            if(c.isSolved()) {
            	Drawable d = getResources().getDrawable(R.drawable.picto_ok);
         		pictoPartegerServeurPicto_ok.setImageDrawable(d);
            }else {
            	Drawable d = getResources().getDrawable(R.drawable.picto_ko);
         		pictoPartegerServeurPicto_ok.setImageDrawable(d);           
            }
//     		java.io.FileInputStream in;
//			try {
//				in = getActivity().openFileInput("picto_ok.png");
//				pictoPartegerServeur.setImageBitmap(BitmapFactory.decodeStream(in));
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//     		Drawable d = Drawable.createFromPath("picto_ok.png");
//     		pictoPartegerServeur.setImageDrawable(d);
     	
            
            /*
             * RENVOYER LA VUE (LISTE DE FRAGEMENT) 
             */
            return convertView;
        }
    }
    
    
    /*
     * GENERER LA SUPPRERSION DES DATA AVEC UN MENU CONTEXTUEL SUR LA BARRE D'ACTION 
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo info) {
    	/*
    	 * AJOUTER LE TEMPLTE DU MENU CONTEXTUEL 
    	 */
    	getActivity().getMenuInflater().inflate(R.menu.fragment_menu_suppression_application, menu); 
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	/*
    	 * PERMET DE RECUPERER LES INFORMATION SUR LA DATA QUI A DECLENCHEE LE MENU CONTEXTUEL
    	 */
    	AdapterContextMenuInfo informations = (AdapterContextMenuInfo)item.getMenuInfo();
    	int position = informations.position; 
    	DataAdapter adaptateur = (DataAdapter)getListAdapter(); 
    	Data donnee = adaptateur.getItem(position); 
    	/*
    	 * DETERMINER LE QUEL DES ELEMENTS DU MENU CONTEXTUEL A DECLENCHE L ACTION 
    	 */
    	switch (item.getItemId()) {
		case R.id.menu_item_supprimer_data:
			/*
			 * SUPPRIMER LA DATA DU MODEL 
			 */
			Log.e("Lancer la supprission de la data", "avant"); 
			//DataDAO.get(getActivity()).supprimerData(donnee);
			 // VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication(); 
			DataDAO.get(getActivity()).supprimerDataByBord(donnee, vg.getUUUIDStringBoard()); 
			adaptateur.notifyDataSetChanged(); 
			return true;
		default:
			return false;
		}
    }
    
//	private void showPhoto() {
//	 for(Data mData : mDatas) {
//		Photo photo = mData.getMaPhoto(); 
//		BitmapDrawable bitmapDrawable = null; 
//		if(photo != null) {
//			String path = getActivity().getFileStreamPath(photo.getNomPhoto()).getAbsolutePath();
//			bitmapDrawable = UtilsPhoto.getScaledDrawable(getActivity(), path);
//		}
//		miniatureImage.setImageDrawable(bitmapDrawable); 
//	 }
//	}
//	
//	@Override
//	public void onStart() {
//		super.onStart(); 
//		showPhoto(); 
//	}
//	
//	@Override
//	public void onStop() {
//		super.onStop(); 
//		UtilsPhoto.cleanImageView(miniatureImage);
//	}
}
