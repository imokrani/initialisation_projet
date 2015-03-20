package org.cgi.poc;

import java.util.ArrayList;
import java.util.UUID;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;


/*
 * CETTE CALSSE EST APPELLE LORS DE L'EDITION D'DATA 
 * CLIQUER SUR LA LISTE DES DATA POUR MODIFICATION 
 * LE FRAGEMENT DE CETTE ACITIVITE EST LE DATAPAGER
 */
public class DataPagerActivity  extends FragmentActivity{

	
	 ViewPager mViewPager;
	 
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        mViewPager = new ViewPager(this);
	        mViewPager.setId(R.id.viewPager);
	        setContentView(mViewPager);
	        
	        String idBoard = (String)getIntent().getSerializableExtra(DataFragment.EXTRA_ID_BORAD_CREATION_PHOTO);
	        Log.e("Le ID dans le pager est ", idBoard); 
	       // String idBoard = getIntent().getStringExtra("EXTRA_ID_BORAD_CREATION_PHOTO"); 
	        final ArrayList<Data> listeDonnees = DataDAO.get(this).getDatasByBoard(idBoard); 
	       // final ArrayList<Data> listeDonnees = DataDAO.get(this).getDatas();

	        FragmentManager fm = getSupportFragmentManager();
	        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
	            @Override
	            public int getCount() {
	                return listeDonnees.size();
	            }
	            @Override
	            public Fragment getItem(int pos) {
	                UUID dataId =  listeDonnees.get(pos).getId();
	                Data d = DataDAO.get(getApplication()).getData(dataId); 
	                return DataFragment.newInstance(dataId,d.getBoardPhoto().getIdBoard().toString() );
	            }
	        }); 

	        UUID dataId = (UUID)getIntent().getSerializableExtra(DataFragment.EXTRA_DATA_ID);
	        for (int i = 0; i < listeDonnees.size(); i++) {
	            if (listeDonnees.get(i).getId().equals(dataId)) {
	                mViewPager.setCurrentItem(i);
	                break;
	            } 
	        }
	    }
}
