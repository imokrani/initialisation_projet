package org.cgi.poc;

import android.support.v4.app.Fragment;

/*
 *  L'ACTIVITE PRINCIPALE DE TOUTE L'APPLICATION
 *  LE POINT D'ENTREE DE L'APPLICATION
 */
public class DataMenuActivity extends MainFragmentActivity {

	  /*
	   * CREER UNE LISTE DE FRAGEMENTS POUR L'ACTIVITE PRINCIPALE   -> DataMenuActivity
	   */
	  @Override
	  protected Fragment createFragment() {
		  return new DataMenuFragment(); 
	  }
}
