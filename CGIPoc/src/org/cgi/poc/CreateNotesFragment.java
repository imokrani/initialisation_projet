package org.cgi.poc;

import java.util.UUID;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateNotesFragment extends Fragment {

	 public static final String EXTRA_NOTE_ID = "CGIPOC.NOTE_ID";
	 public static final String EXTRA_BOARD_ID= "CGIPOC.BOARD_ID";
	 public static final String EXTRA_ID_BORAD_CREATION_NOTES = "CGIPOC.BOARD_ID_NOTES";
	 static  String idBoard; 
	 Notes mNote; 
	 

	    TextView libelleBoardTextView; 
	    EditText mTitleField;
	    EditText dataDescriptionField;

	    TextView afficheDate; 

	    // String isEdit = "KO";  
	  //  static  String idBoard; 
	    Button boutonEnregisterPhoto; 
	    Button boutonAnnulerPhoto;
	    Notes copieGlobales = null; 
	    
	    
	    public static CreateNotesFragment newInstance(UUID dataId, String uuidStringBoard) {
	    
	        Bundle args = new Bundle();
	        args.putSerializable(EXTRA_NOTE_ID, dataId);
	        args.putSerializable(EXTRA_BOARD_ID, uuidStringBoard);
	        /*
	         * CREER UNE NOUVELLE INSTANCE DE FRAGEMENT
	         * PASSER NE ARGUEMENT DES DONNEES AU FRAGEMENT 
	         */
	        CreateNotesFragment fragment = new CreateNotesFragment();
	        fragment.setArguments(args);
	        return fragment;
	    }
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		   
		    Log.e("Formulaire de creation de la note", "Creation note OK"); 
	        super.onCreate(savedInstanceState);
	        UUID dataId = (UUID)getArguments().getSerializable(EXTRA_NOTE_ID);
	        Log.e("ID DE LA NOTE RECUPERE", ""+dataId);
	        getActivity().setTitle(R.string.home_poc_cgi);
	        
	      	Intent intentParam = getActivity().getIntent(); 
	    	//isEdit   = intentParam.getStringExtra(EXTRA_PHOTO_ACTION_EDIT);
	    	idBoard = intentParam.getStringExtra(EXTRA_ID_BORAD_CREATION_NOTES); 
	 
	        mNote = NotesDAO.get(getActivity()).getNote(dataId);

	        setHasOptionsMenu(true);
	    }
	 
	 @Override
	    @TargetApi(11)
	    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		 View v = inflater.inflate(R.layout.creation_notes_fragment, parent, false);
		 
		 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
	        }
	        
	        /*
	         *  RECUPERER TOUT LES ELEMENT DE LA VUE COMME DEFINI DANS LE XML
	         */
	        
	        /*
	         * TENTER DE FAIRE UNE SAUVEGARDE DE LA DATA COURANTE 
	         */
	       // Log.e("-->  CREER UNE COPIE DE  copieGlobales ", "copieGlobales");
	        copieGlobales = new Notes(); 
	        
	        copieGlobales.setTitreNote(mNote.getTitreNote()); 
	        //copieGlobales.setSolved(mData.isSolved());
	        copieGlobales.setContenuNote(mNote.getContenuNote()); 
	        copieGlobales.setDateCreation(mNote.getDateCreation()); 
	        copieGlobales.setDateModification(mNote.getDateModification()); 
	        copieGlobales.setCouleur(mNote.getCouleur()); 
	        
//	        Log.e("-->  CREER UNE COPIE DE  copie ", "copie");
	        final Notes copie = new Notes(); 
	        copie.setTitreNote(mNote.getTitreNote()); 
	        //copieGlobales.setSolved(mData.isSolved());
	        copie.setContenuNote(mNote.getContenuNote()); 
	        copie.setDateCreation(mNote.getDateCreation()); 
	        copie.setDateModification(mNote.getDateModification()); 
	        copie.setCouleur(mNote.getCouleur()); 
//	        vueMiniaturePhoto = (ImageView)v.findViewById(R.id.photo_previsualisation);
	        /*
	         * AFFICHAGE DE L'IMAGE 
	         */
//	        
//	        vueMiniaturePhoto.setOnClickListener(new View.OnClickListener() {
//	            public void onClick(View v) {
//	                Photo p = mData.getMaPhoto();
//	                if (p == null) 
//	                    return;
//
//	                FragmentManager fm = getActivity()
//	                    .getSupportFragmentManager();
//	                String path = getActivity()
//	                    .getFileStreamPath(p.getNomPhoto()).getAbsolutePath();
//	                ImageFragment.createInstance(path)
//	                    .show(fm, DIALOG_IMAGE);
//	            }
//	        });
//	        
	        
	        libelleBoardTextView = (TextView)v.findViewById(R.id.libelle_board); 
	        Log.e("-->  RECUPERER  Board ", "Board");
	        Board board = BoardDAO.get(getActivity()).getBoard(UUID.fromString(idBoard)); 
	        if(board != null) {
	        	mNote.setBoardNote(board); 
	        	Log.e("La valeur de  getTitleBoard ", board.getTitleBoard());
	        	libelleBoardTextView.setText(board.getTitleBoard()); 
	        }
	        
	        
	        Log.e("mTitleField ", "-->  mTitleField");
	        mTitleField = (EditText)v.findViewById(R.id.note_titre);
	        
	        mTitleField.setText(mNote.getTitreNote());
	        
	        mTitleField.addTextChangedListener(new TextWatcher() {
	            public void onTextChanged(CharSequence c, int start, int before, int count) {
	            	
	            	
	                mNote.setTitreNote(c.toString().trim());
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
	        dataDescriptionField.setText(mNote.getContenuNote()); 
	        dataDescriptionField.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					// TODO Auto-generated method stub
					mNote.setContenuNote(s.toString().trim()); 
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
	         * GESTION DU CHECKBOX
	         */
//	        Log.e("mSolvedCheckBox ", "-->  mSolvedCheckBox");
//	        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
//	        mSolvedCheckBox.setChecked(mData.isSolved());
//	        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//	            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//	                /*
//	                 * ACTION SUITE AU CHECKED
//	                 */
//	                 mData.setSolved(isChecked);
//	            }
//	        });
//	        
	        
	     /*
	      * RECUPERER LE BOUTON ENREGISTRER ET ENVOYER 
	      */
	        Log.e("boutonEnregisterPhoto ", "-->  boutonEnregisterPhoto");
	        boutonEnregisterPhoto = (Button)v.findViewById(R.id.photo_enregistrer_bouton); 
	        boutonEnregisterPhoto.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.e("Lancer l'enregistrement", "boutonEnregisterPhoto");
					/*
					 * LANCER L'ENREGISTRMENT DE LA PHOTO
					 */
					 VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();  
					 String id =  vg.getUUUIDStringBoard(); 
					 /*
					  * NE PAS ENREGISTRER SI LA PHOTO N EST PAS PRISE 
					  */
					 
					 	if((mNote.getContenuNote() == null) || (mNote.getContenuNote().trim().equals(""))   ) {
					 		 Toast 	toast = Toast.makeText(getActivity(), "Merci de saisir le contenu de la note SVP !!!", Toast.LENGTH_LONG);
			                    toast.show();
					 	} else {
					 		NotesDAO.get(getActivity()).enregistrerNotesByBoard(id); 
			            	 NavUtils.navigateUpFromSameTask(getActivity());
			            	 getActivity().finish(); 
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
//	              	Intent intentParam = getActivity().getIntent(); 
//	            	isEdit   = intentParam.getStringExtra(EXTRA_PHOTO_ACTION_EDIT); 
//	            	 if(isEdit.equals("OK")) {
//	            		 if(copie != null) {
//		            		 mData.setTitle(copie.getTitle()); 
//		            		 mData.setDate(copie.getDate());
//		            		 mData.setSolved(copie.isSolved());
//		            		 mData.setMaPhoto(copie.getMaPhoto()); 
//		            		 mData.setmDescription(copie.getmDescription()); 
//	            		 }
//	            		// DataDAO.get(getActivity()).enregistrerData();
//	            		 DataDAO.get(getActivity()).enregistrerDataByBoard(idBoard);
//	            	 }else {
//	            		 DataDAO.get(getActivity()).supprimerData(mData);	 
//	            	 }
//	            	 NavUtils.navigateUpFromSameTask(getActivity());
//	            	 getActivity().finish(); 
				}
			}); 
	        /*
	         * RENVOYER LA VUE 
	         */
	        return v;
	 }
	 
	 
	
	    @Override
	    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	        super.onCreateOptionsMenu(menu, inflater);
	        inflater.inflate(R.menu.fragment_menu_icons_bouton_retour_ajouter_notes, menu);
	    }

	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case android.R.id.home:
	            	/*
	            	 * SI CLICK SUR L ICONE DE L'APPLICATION NAVIGUER A L'ACTIVITE   PARENTE
	            	 * SI ACTIVITE PARENTE EST NUULLE L APPLICATION EN CHANGE PAS D'ETAT
	            	 */
	            	Intent intentParam = getActivity().getIntent(); 
	            	//isEdit   = intentParam.getStringExtra(EXTRA_PHOTO_ACTION_EDIT); 
//	            	if(isEdit.equals("OK")) {
//	            		if(copieGlobales != null) {
//		            	
//		              		 mData.setTitle(copieGlobales.getTitle()); 
//		            		 mData.setDate(copieGlobales.getDate());
//		            		 mData.setSolved(copieGlobales.isSolved());
//		            		 mData.setMaPhoto(copieGlobales.getMaPhoto()); 
//		            		 mData.setmDescription(copieGlobales.getmDescription()); 
//	            		}
	            		// DataDAO.get(getActivity()).enregistrerData();
//	            	}else {
//	            		DataDAO.get(getActivity()).supprimerData(mData);
//	            	}
	            	 NavUtils.navigateUpFromSameTask(getActivity());
	                 getActivity().finish(); 
	                return true;
	                
	            case R.id.bouton_retour_ajouter_note:
	            	Intent intentParamRetour = getActivity().getIntent(); 
	            	//isEdit   = intentParamRetour.getStringExtra(EXTRA_BORAD_ACTION_EDIT); 
//	            	if(isEdit.equals("OK")) {
//	            		if(copieGlobale != null) {
//		            		 board.setTitleBoard(copieGlobale.getTitleBoard()); 
//		            		 board.setDescriptionBorad(copieGlobale.getDescriptionBorad());
//	            		}
//	            		 BoardDAO.get(getActivity()).enregistrerBoard();
//	            	}else {
//	            		BoardDAO.get(getActivity()).supprimerBoard(board);
//	            	}
	            	
	            	 NavUtils.navigateUpFromSameTask(getActivity());
	                 getActivity().finish(); 
	                return true; 
	            case R.id.bouton_quitter_application_ajouter_note:
	            	System.exit(0); 
	            	return true; 
	            default:
	                return super.onOptionsItemSelected(item);
	        } 
	    }
	    
	 
}
