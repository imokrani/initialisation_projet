package org.cgi.poc;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.cgi.poc.utils.UtilsPhoto;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * CE FRAGEMENT GERE LE FORMULAIRE DE CREATION D'UNE DATA
 */
public class DataFragment extends Fragment{
	/*
	 * TAG	POUR LES LOG
	 */
	private  static final String TAG = "DataFragment --> "; 
	
	 private static final String DIALOG_IMAGE = "image";
 
	/*
	 * CODE DES REQUETE QUI PERMETTENT UN ECHANGE ENTRE LES ACTIVITES APRES UN APPEL
	 */
    public static final String EXTRA_DATA_ID = "CGIPOC.DATA_ID";
    public static final String EXTRA_BOARD_ID ="CGIPOC.BOARD_ID";
    public static final String EXTRA_PHOTO_ACTION_EDIT = "PHOTO_IS_EDIT";
    public static final String EXTRA_ID_BORAD_CREATION_PHOTO = "ID_BOARD_CREATION_PHOTO";
    
    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;
    private static final int CODE_REQUETE_PHOTO=1;
    

	
    /*
     * LES ATTRIBUT D'UNE DATA
     */
    Data mData;
    TextView libelleBoardTextView; 
    EditText mTitleField;
    EditText dataDescriptionField;
    //Button mDateButton;
    ImageButton boutonCaledar; 
    TextView afficheDate; 
    CheckBox mSolvedCheckBox;

     String isEdit = "KO";  
    static  String idBoard; 
    Button boutonEnregisterPhoto; 
    Button boutonAnnulerPhoto; 
    
    /*
     * CREER UNE COPIE GLOBALE DE MDATA
     */
    Data copieGlobales = null; 
    
    /*
     * LE BOUTON QUI GERE LA CAMERA 
     */
    private ImageButton mPhotoButton;
    
    /*
     * Visualisation de la photo
     */
    private ImageView vueMiniaturePhoto;  
    
    
    /*
     * CETTE METHODE PERMET D'INSTANCIER UN FRAGEMENT 
     * PASSER LES ARGUMENTS A SON ACTIVITE   IDDATA
     * ET L'ACTIVITE A SON TOUR POURRA LES TRANSMETTRE A UNE AUTRE ACTIVITE
     */
    
    public static DataFragment newInstance(UUID dataId, String uuidStringBoard) {
    	/*
    	 * BUNDLE : EST OBJET QUI PERMET DE PASSER LES PARAMETRES LORS DE L APPEL D'UNE ACTIVITE PAR EXEMPLE
    	 */
        Bundle args = new Bundle();
        /*
         * SET LE PARMETRE AVEC   CLE   -->   VALEUR 
         */
        args.putSerializable(EXTRA_DATA_ID, dataId);
        args.putSerializable(EXTRA_BOARD_ID, uuidStringBoard);
        /*
         * CREER UNE NOUVELLE INSTANCE DE FRAGEMENT
         * PASSER NE ARGUEMENT DES DONNEES AU FRAGEMENT 
         */
        DataFragment fragment = new DataFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    /*
     * TOUT FRAGEMENT NECESSITE DE IMPLEMENTER LA METHODE ONCREATE
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*
    	 * INSTANCIATION DES L'ETAT DU FRAGEMENT
    	 */ 
    	
    	Log.e("VERIFICATION DU PASSAGE DU VIEW PAGER ", "PASSAGE APRES DEFILI");
        super.onCreate(savedInstanceState);
        /*
         * RECUPERATION DU PARAMETRE DE CLE = EXTRA_DATA_ID   DE CE FRAGEMENT
         */
        UUID dataId = (UUID)getArguments().getSerializable(EXTRA_DATA_ID);
        /*
         * DEFINIR LE MENU DE L'ACTIVITE
         */
        getActivity().setTitle(R.string.home_poc_cgi);
        
        
        /*
         * RECUPERER LA PROVENNACE DE LA ACTION
         */
      	Intent intentParam = getActivity().getIntent(); 
    	isEdit   = intentParam.getStringExtra(EXTRA_PHOTO_ACTION_EDIT);
    	idBoard = intentParam.getStringExtra(EXTRA_ID_BORAD_CREATION_PHOTO); 
        /*
         * DEPUIS LE CONTEXT RECUPERER LA LISTE DES DATA  ET  LA DATA AYANT POUR ID     dataId
         * JUSQUE LA mData NE CONTIENT QUE ID
         */ 
        mData = DataDAO.get(getActivity()).getData(dataId);
        /*
         * AUTORISATION DE L APPEL DE LA METHODE   :  onCreateOptionsMenu(…)
         * POUR CHERCHER UNE XML DANS LEQUEL EST DEFINIT UN MENU
         * OU BIEN UTILISR LE MENU PAR DEFAUT QU EST L ICON DE L APPLICATION AVEC LA METHODE      onOptionsItemSelected  
         */
        setHasOptionsMenu(true);
        Log.e("FIN DE LA METHODE  ", "-->  onCreate");
    }
    
    /*
     * METTRE A JOUR LE BOUTON DATE AVEC LA VALEUR DE LA DATE RECUPERER DEPUIS LA LISTE 
     */
    public void updateDate() {
    	SimpleDateFormat formater  = new SimpleDateFormat("'le' dd/MM/yyyy 'à' hh:mm:ss");
    	String DateFormattee = formater.format(mData.getDate());
    	//mDateButton.setText(DateFormattee);
    	afficheDate.setText(DateFormattee); 
       // mDateButton.setText(mData.getDate().toString());
    }
    
    
    /*
     * TOUT FRAGEMENT NECISSITE D'IMPLEMENTER LA METHODE   ONCREATEVIEW
     */
    
    @Override
    @TargetApi(11)
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.e("DEBUT  DE LA METHODE  ", "-->  onCreateView");
    	/*
    	 * RECUPERATION DE LA VUE  AVEC LE TEMPLATE DEFINI DANS LE XML    ->   fragment_crime
    	 */
        //View v = inflater.inflate(R.layout.fragment_crime, parent, false);
    	View v = inflater.inflate(R.layout.creation_photo_fragment, parent, false);
    	
    	  Log.e("-->  onCreateView ", "-->  Recuperer le template de la activite");
        /*
         * GESTION DES DIFFERENTES VERSIONS D'ANDROID 
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	
        	//??????????????????????????????????????????????????????????????????????
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        /*
         *  RECUPERER TOUT LES ELEMENT DE LA VUE COMME DEFINI DANS LE XML
         */
        
        /*
         * TENTER DE FAIRE UNE SAUVEGARDE DE LA DATA COURANTE 
         */
        Log.e("-->  CREER UNE COPIE DE  copieGlobales ", "copieGlobales");
        copieGlobales = new Data(); 
        
        copieGlobales.setTitle(mData.getTitle()); 
        copieGlobales.setSolved(mData.isSolved()); 
        copieGlobales.setMaPhoto(mData.getMaPhoto()); 
        copieGlobales.setDate(mData.getDate()); 
        copieGlobales.setmDescription(mData.getmDescription()); 
        Log.e("-->  CREER UNE COPIE DE  copie ", "copie");
        final Data copie = new Data(); 
        copie.setDate(mData.getDate()); 
        copie.setSolved(mData.isSolved()); 
        copie.setMaPhoto(mData.getMaPhoto()); 
        copie.setTitle(mData.getTitle()); 
        copie.setmDescription(mData.getmDescription()); 
        vueMiniaturePhoto = (ImageView)v.findViewById(R.id.photo_previsualisation);
        /*
         * AFFICHAGE DE L'IMAGE 
         */
        
        vueMiniaturePhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Photo p = mData.getMaPhoto();
                if (p == null) 
                    return;

                FragmentManager fm = getActivity()
                    .getSupportFragmentManager();
                String path = getActivity()
                    .getFileStreamPath(p.getNomPhoto()).getAbsolutePath();
                ImageFragment.createInstance(path)
                    .show(fm, DIALOG_IMAGE);
            }
        });
        
        
        libelleBoardTextView = (TextView)v.findViewById(R.id.libelle_board); 
        Log.e("-->  RECUPERER  Board ", "Board");
        Board board = BoardDAO.get(getActivity()).getBoard(UUID.fromString(idBoard)); 
        if(board != null) {
        	mData.setBoardPhoto(board); 
        	Log.e("La valeur de  getTitleBoard ", board.getTitleBoard());
        	libelleBoardTextView.setText(board.getTitleBoard()); 
        }
        
        
        Log.e("mTitleField ", "-->  mTitleField");
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        if(mTitleField == null) {
        	Log.e("**************************************************************  Booooooooooooooooooooom ", "mTitleField   ===   NUL");
        }
        mTitleField.setText(mData.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before, int count) {
            	
            	if(isEdit.equals("OK")) {
            		
            	}
                mData.setTitle(c.toString().trim());
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // this one too
            }
        });
        
        /*
         * RECUPERATION DE DESCRIPTION DE L'IMAGE
         */
        Log.e("dataDescriptionField ", "-->  dataDescriptionField");
        dataDescriptionField = (EditText)v.findViewById(R.id.data_description_champ);
        dataDescriptionField.setText(mData.getmDescription()); 
        dataDescriptionField.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				mData.setmDescription(s.toString().trim()); 
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
         
        /*
         * RECUPERER LE BOUTONN  DE  DATE  COMME DEFINI DANS LE XML
         */
        afficheDate = (TextView)v.findViewById(R.id.affiche_date_prise_photo); 
        updateDate();
        /*
         * Calendar
         */
        boutonCaledar = (ImageButton)v.findViewById(R.id.boutonDateCaledar); 
        boutonCaledar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
            	 * GERER LE CLICK SUR LE BOUTON 
            	 */
                FragmentManager fm = getActivity().getSupportFragmentManager();
                /*
                 * CREER LA POPUP DE DATE  + AFFICHAGE 
                 */
                DatePickerFragment dialog = DatePickerFragment.newInstance(mData.getDate());
                dialog.setTargetFragment(DataFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
			}
		}); 
        /*
         * GESTION DU CHECKBOX
         */
        Log.e("mSolvedCheckBox ", "-->  mSolvedCheckBox");
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mData.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /*
                 * ACTION SUITE AU CHECKED
                 */
                 mData.setSolved(isChecked);
            }
        });
        
        /*
         * GERER LA CAMERA 
         */
        Log.e("mPhotoButton ", "-->  mPhotoButton");
        mPhotoButton = (ImageButton)v.findViewById(R.id.crime_imageButton);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // launch the camera activity
                Intent i = new Intent(getActivity(), DataCameraActivity.class);
                startActivityForResult(i, CODE_REQUETE_PHOTO);
            }
        });
        
        // if camera is not available, disable camera functionality
        PackageManager pm = getActivity().getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) &&
                !pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            mPhotoButton.setEnabled(false);
        }
        
     /*
      * RECUPERER LE BOUTON ENREGISTRER ET ENVOYER 
      */
        Log.e("boutonEnregisterPhoto ", "-->  boutonEnregisterPhoto");
        boutonEnregisterPhoto = (Button)v.findViewById(R.id.photo_enregistrer_bouton); 
        boutonEnregisterPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*
				 * LANCER L'ENREGISTRMENT DE LA PHOTO
				 */
				 VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();  
				 String id =  vg.getUUUIDStringBoard(); 
				 /*
				  * NE PAS ENREGISTRER SI LA PHOTO N EST PAS PRISE 
				  */
				 if(mData.getMaPhoto() != null) {
					 DataDAO.get(getActivity()).enregistrerDataByBoard(id); 
	            	 NavUtils.navigateUpFromSameTask(getActivity());
	            	 getActivity().finish(); 
				 }else {
					 Toast 	toast = Toast.makeText(getActivity(), "Vous n'avez encore pris la photo", Toast.LENGTH_LONG);
	                    toast.show();
				 }
				 
				 
				
			}
		});
        Log.e("boutonAnnulerPhoto ", "-->  boutonAnnulerPhoto");
        boutonAnnulerPhoto = (Button)v.findViewById(R.id.photo_annuler_bouton); 
        boutonAnnulerPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*
				 * LANCER L'ANNULATION DE L'ENREGISTREMENT DE LA PHOTO 
				 */
				/*
              	 * ANNULER L'ENREGISTREMENT 
              	 */
              	Intent intentParam = getActivity().getIntent(); 
            	isEdit   = intentParam.getStringExtra(EXTRA_PHOTO_ACTION_EDIT); 
            	 if(isEdit.equals("OK")) {
            		 if(copie != null) {
	            		 mData.setTitle(copie.getTitle()); 
	            		 mData.setDate(copie.getDate());
	            		 mData.setSolved(copie.isSolved());
	            		 mData.setMaPhoto(copie.getMaPhoto()); 
	            		 mData.setmDescription(copie.getmDescription()); 
            		 }
            		// DataDAO.get(getActivity()).enregistrerData();
            		 DataDAO.get(getActivity()).enregistrerDataByBoard(idBoard);
            	 }else {
            		 DataDAO.get(getActivity()).supprimerData(mData);	 
            	 }
            	 NavUtils.navigateUpFromSameTask(getActivity());
            	 getActivity().finish(); 
			}
		}); 
        /*
         * RENVOYER LA VUE 
         */
        return v; 
    }
    
    /*
     * CETTE METHODE EST UTILISEE POUR RECUPERER UNE VALEUR DE RETOUR ENVOYEE PAR UNE ACTIVITE ENFANT 
     * IL S AGIT DE L ACTIVITE DATAPAGERACTIVITE
     * REQUESTCODE : REPRESENTE LE CODE DE LA REQUETE ENVOYEE 
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mData.setDate(date);
            updateDate();
        } else if(requestCode == CODE_REQUETE_PHOTO) {
        	/*
        	 * CREER UNE NOUVELLE PHOTO ATTACHER LA AU FORMULAIRE 
        	 */
        	String nomFichier = data.getStringExtra(DataCameraFragement.EXTRA_PHOTO_NOM_PHOTO); 
        	if(nomFichier != null) {
        		Photo photo = new Photo(nomFichier);
        		mData.setMaPhoto(photo); 
        		Log.e(TAG, "Data   "+ mData.getTitle() + "  has photo"); 
        	}
        }
    }

    /*
     * android.R.id.home : L'IDENTIFIANT DE L'ICON DE L'APPLICATION DANS LE MENU PAR DEFAUT 
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            	/*
            	 * SI CLICK SUR L ICONE DE L'APPLICATION NAVIGUER A L'ACTIVITE   PARENTE
            	 * SI ACTIVITE PARENTE EST NUULLE L APPLICATION EN CHANGE PAS D'ETAT
            	 */
            	Intent intentParam = getActivity().getIntent(); 
            	isEdit   = intentParam.getStringExtra(EXTRA_PHOTO_ACTION_EDIT); 
            	if(isEdit.equals("OK")) {
            		if(copieGlobales != null) {
	            	
	              		 mData.setTitle(copieGlobales.getTitle()); 
	            		 mData.setDate(copieGlobales.getDate());
	            		 mData.setSolved(copieGlobales.isSolved());
	            		 mData.setMaPhoto(copieGlobales.getMaPhoto()); 
	            		 mData.setmDescription(copieGlobales.getmDescription()); 
            		}
            		 DataDAO.get(getActivity()).enregistrerData();
            	}else {
            		DataDAO.get(getActivity()).supprimerData(mData);
            	}
            	 NavUtils.navigateUpFromSameTask(getActivity());
                 getActivity().finish(); 
                return true;
                
            case R.id.bouton_retour:
            	Intent intentParam_ = getActivity().getIntent(); 
            	isEdit   = intentParam_.getStringExtra(EXTRA_PHOTO_ACTION_EDIT); 
            	if(isEdit.equals("OK")) {
            		if(copieGlobales != null) {
	            	
	              		 mData.setTitle(copieGlobales.getTitle()); 
	            		 mData.setDate(copieGlobales.getDate());
	            		 mData.setSolved(copieGlobales.isSolved());
	            		 mData.setMaPhoto(copieGlobales.getMaPhoto()); 
	            		 mData.setmDescription(copieGlobales.getmDescription()); 
            		}
            		 DataDAO.get(getActivity()).enregistrerData();
            	}else {
            		DataDAO.get(getActivity()).supprimerData(mData);
            	}
            	 NavUtils.navigateUpFromSameTask(getActivity());
                 getActivity().finish(); 
                return true;
                
            default:
                return super.onOptionsItemSelected(item);
        } 
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu_icons_bouton_retour_board, menu);
    }
    
   
    /*
     * LE MOMENT IDEAL DE SAUVEGARDER UNE DATA EST LORS QUE L'ACTIVITE LIEE A CE FRAGEMENT SOIT EN PAUSE 
     */
//    @Override
//    public void onPause() {
//    	super.onPause(); 
//    	DataDAO.get(getActivity()).enregistrerData(); 
//    }
    
    /*
     * RECUPERATION DE LA PHOTO DEPUIS LE DIQUE ET AFFICHAGE DE LA PHOTO DANS UNE IMAGEVIEW
     */
	private void showPhoto() {
		Photo photo = mData.getMaPhoto(); 
		BitmapDrawable bitmapDrawable = null; 
		if(photo != null) {
			String path = getActivity().getFileStreamPath(photo.getNomPhoto()).getAbsolutePath();
			bitmapDrawable = UtilsPhoto.getScaledDrawable(getActivity(), path);
		}
		vueMiniaturePhoto.setImageDrawable(bitmapDrawable); 
	}
	
	@Override
	public void onStart() {
		super.onStart(); 
		showPhoto(); 
	}
	
	@Override
	public void onStop() {
		super.onStop(); 
		UtilsPhoto.cleanImageView(vueMiniaturePhoto);
	}
}
