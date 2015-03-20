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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AjouterBoardFragment extends Fragment {

	private static final String TAG = " --> AjouterBoardFragment";
	
	/*
	 * CODIFCATION DES REQUETE INTENT
	 */
	public static final String EXTRA_BOARD_ID = "CGIPOC.BOARD_ID";
	public static final String EXTRA_BORAD_ACTION_EDIT = "BOARD_EDIT_NOT_DELETE"; 
	
	
	 Board board; 
	 EditText editTextTitre; 
	 EditText editTextDescription; 
	
	 Button buttonEnregistrer; 
	 Button buttonAnnuler; 
	  Board copieGlobale = null; 
	 
	 /*
	  * 
	  */
	 String _titre = null; 
	 String _description=null; 
	 
	 String isEdit = "KO"; 
	
    public static AjouterBoardFragment newInstance(UUID boardId) {
    	Log.e("Demarage ", "  ------   newInstance"); 
    	  Bundle args = new Bundle();
    	  args.putSerializable(EXTRA_BOARD_ID, boardId);
    	  AjouterBoardFragment ajouterBoardFragment = new AjouterBoardFragment(); 
    	  ajouterBoardFragment.setArguments(args); 
    	  Log.e("Fin ", "  ------   newInstance"); 
    	  return ajouterBoardFragment; 
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*
    	 * 
    	 */
    	
    	
        super.onCreate(savedInstanceState);
        UUID boardId = (UUID)getArguments().getSerializable(EXTRA_BOARD_ID);
        VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication();
        vg.setUUUIDStringBoard(boardId.toString()); 
        // isEdit = (String) getArguments().getSerializable(EXTRA_BORAD_ACTION_EDIT); 
       	Intent intentParam = getActivity().getIntent(); 
    	isEdit   = intentParam.getStringExtra(EXTRA_BORAD_ACTION_EDIT); 
    	if(isEdit.equals("OK")){
    		getActivity().setTitle(R.string.modifier_bord);
    	}else {
    		getActivity().setTitle(R.string.ajouter_bord);
    	}
        board = BoardDAO.get(getActivity()).getBoard(boardId);
        setHasOptionsMenu(true);
    }
    
    @Override
    @TargetApi(11)
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    	
    	View v = inflater.inflate(R.layout.creation_board_fragment, parent, false);
    	

          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
          	
          	//??????????????????????????????????????????????????????????????????????
              getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
          }
          
          final Board copie = new Board(); 
          copie.setIdBoard(board.getIdBoard()); 
          copie.setTitleBoard(board.getTitleBoard()); 
          copie.setDescriptionBorad(board.getDescriptionBorad()); 
          copieGlobale = new Board(); 
          copieGlobale.setIdBoard(board.getIdBoard()); 
          copieGlobale.setTitleBoard(board.getTitleBoard()); 
          copieGlobale.setDescriptionBorad(board.getDescriptionBorad()); 
          
          editTextTitre = (EditText)v.findViewById(R.id.board_titre); 
          editTextTitre.setText(board.getTitleBoard());
          editTextTitre.addTextChangedListener(new TextWatcher() {
              public void onTextChanged(CharSequence c, int start, int before, int count) {
            	   	Intent intentParam = getActivity().getIntent(); 
                	isEdit   = intentParam.getStringExtra(EXTRA_BORAD_ACTION_EDIT); 
                	if(isEdit.equals("OK")) {
                	      _titre = new String( board.getTitleBoard()); 
                	}
                    board.setTitleBoard(c.toString().trim());
              }

              public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                  // this space intentionally left blank
              }

              public void afterTextChanged(Editable c) {
                  // this one too
              }
          });
          
          editTextDescription = (EditText)v.findViewById(R.id.board_description_champ); 
          editTextDescription.setText(board.getDescriptionBorad()); 
          editTextDescription.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			 	Intent intentParam = getActivity().getIntent(); 
            	isEdit   = intentParam.getStringExtra(EXTRA_BORAD_ACTION_EDIT); 
            	if(isEdit.equals("OK")) {
            	      _description = new String( board.getDescriptionBorad()); 
            	}
				board.setDescriptionBorad(s.toString().trim()); 
				
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
          
         /*
          * Recuperation du bouton pour enregistrement 
          */
          
         buttonEnregistrer = (Button)v.findViewById(R.id.board_enregistrer_bouton);
         buttonEnregistrer.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
             	/*
             	 * ENREGISTRER LE BOARD 
             	 */
            	 BoardDAO.get(getActivity()).enregistrerBoard();
            	 VaiablesGlobales vg = (VaiablesGlobales)getActivity().getApplication(); 
            	 vg.setCountPhotosBoard(0); 
            	 NavUtils.navigateUpFromSameTask(getActivity());
            	 getActivity().finish(); 
            	//
             }
         });
         
         buttonAnnuler = (Button)v.findViewById(R.id.board_annuler_bouton); 
          
         buttonAnnuler.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
              	/*
              	 * ANNULER L'ENREGISTREMENT 
              	 */
              	Intent intentParam = getActivity().getIntent(); 
            	isEdit   = intentParam.getStringExtra(EXTRA_BORAD_ACTION_EDIT); 
            	 if(isEdit.equals("OK")) {
            		 if(copie != null) {
	            		 board.setTitleBoard(copie.getTitleBoard()); 
	            		 board.setDescriptionBorad(copie.getDescriptionBorad());
            		 }
            		 BoardDAO.get(getActivity()).enregistrerBoard();
            	 }else {
            		 BoardDAO.get(getActivity()).supprimerBoard(board);	 
            	 }
            	 NavUtils.navigateUpFromSameTask(getActivity());
            	 getActivity().finish(); 
              }
          });
    	return v; 
    }
    
    /*
     * CREER UN MENU DANS L ACTIVITE QUI CREE LE BOARD
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu_icons_bouton_retour_ajouter_board_, menu);
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
            	isEdit   = intentParam.getStringExtra(EXTRA_BORAD_ACTION_EDIT); 
            	if(isEdit.equals("OK")) {
            		if(copieGlobale != null) {
	            		 board.setTitleBoard(copieGlobale.getTitleBoard()); 
	            		 board.setDescriptionBorad(copieGlobale.getDescriptionBorad());
            		}
            		 BoardDAO.get(getActivity()).enregistrerBoard();
            	}else {
            		BoardDAO.get(getActivity()).supprimerBoard(board);
            	}
            	 NavUtils.navigateUpFromSameTask(getActivity());
                 getActivity().finish(); 
                return true;
            case R.id.bouton_retour_ajouter_board:
            	Intent intentParamRetour = getActivity().getIntent(); 
            	isEdit   = intentParamRetour.getStringExtra(EXTRA_BORAD_ACTION_EDIT); 
            	if(isEdit.equals("OK")) {
            		if(copieGlobale != null) {
	            		 board.setTitleBoard(copieGlobale.getTitleBoard()); 
	            		 board.setDescriptionBorad(copieGlobale.getDescriptionBorad());
            		}
            		 BoardDAO.get(getActivity()).enregistrerBoard();
            	}else {
            		BoardDAO.get(getActivity()).supprimerBoard(board);
            	}
            	
            	 NavUtils.navigateUpFromSameTask(getActivity());
                 getActivity().finish(); 
                return true; 
            case R.id.bouton_quitter_application_ajouter_board:
            	System.exit(0); 
            	return true; 
            default:
                return super.onOptionsItemSelected(item);
        } 
    }
     
    
	@Override
	public void onStart() {
		Log.e("AjouterBoardFragment     ", "----->  onStart");
		super.onStart(); 
	}
	
	@Override
	public void onStop() {
		super.onStop(); 
	}
	   
//	   public void sendResult(int resultCode) {
//	        if (getTargetFragment() == null) 
//	            return;
//
//	        /*
//	         * PREPARER L'ENVOIE DE LA DATE A L'ACTIVITE PARENTE 
//	         */
//	        Log.e("avant le passage du parametre ", ""+board.getIdBoard().toString());
//	        Intent i = new Intent();
//	        i.putExtra(EXTRA_BOARD_ID, board.getIdBoard().toString());
//	        
//	        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
//	    }
	
}
