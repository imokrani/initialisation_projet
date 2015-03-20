package org.cgi.poc;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.Window;
import android.view.WindowManager;
/*
 * ACTIVITE QUI FAIT APPEL A LA CAMERA 
 */
public class DataCameraActivity extends  MainFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /*
         * TOUTE LES ACTIVITES SONT ASSOCIEES A UNE FENETRE 
         * CETTE INSTRUCTION CACHE   TOOLBAR   ACTIONBARE   ICON
         */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*
         * POSITIONNER UN LAYOUT SUR LA FENETRE QUI OCCUPERA TOUTE LA FENTRE
         * LE FRAGEMENT LIE A CETTE ACTIVIYT OCUPPERA TOUTE LA FENETRE  
         */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }
	
	@Override
	protected Fragment createFragment() {
		return new DataCameraFragement();
	}

	
	
}
