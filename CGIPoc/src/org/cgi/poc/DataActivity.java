package org.cgi.poc;

import java.util.UUID;

import android.support.v4.app.Fragment;

/*
 * CETTE ACTIVITE GERE UN FORMUALIRE DE CREATION D'UNE DATA  ELLE HERITE DE LA CLASSE FRAGEMENTACTIVITY 
 * CETTE ACTIVITE APPELLE UN FRAGEMENT POUR LA GESTION DE SA VUE 
 * CETTE ACTIVITE HERITE DE L'ACTIVITE PRINCIPALE   MAINFRAGEMENTACTVITE 
 */
public class DataActivity extends MainFragmentActivity{

	/*
	 * CREER LE FRAGMENT DE L ACTIVITE DataActivity
	 */
	@Override
    protected Fragment createFragment() {
		
        UUID dataId = (UUID)getIntent().getSerializableExtra(DataFragment.EXTRA_DATA_ID);
        String uuidBoard = (String)getIntent().getSerializableExtra(DataFragment.EXTRA_BOARD_ID);
        return DataFragment.newInstance(dataId , uuidBoard);
    }
}
