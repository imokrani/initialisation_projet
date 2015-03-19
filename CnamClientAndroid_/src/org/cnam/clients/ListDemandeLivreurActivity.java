package org.cnam.clients;

import android.support.v4.app.Fragment;



public class ListDemandeLivreurActivity extends PrincipaleFragmentActivity{

	@Override
	protected Fragment creerFragment() {
		// TODO Auto-generated method stub
		return new ListDemandesLivreurFragment();
	}

}
