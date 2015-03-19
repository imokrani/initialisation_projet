package org.cnam.livreur;

import android.support.v4.app.Fragment;

public class ListClientsLivreurActivity extends PrincipaleFragmentActivity {

	@Override
	protected Fragment creerFragment() {
		// TODO Auto-generated method stub
		return new ListClientsLivreurFragment();
	}

}
