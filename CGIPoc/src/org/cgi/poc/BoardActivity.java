package org.cgi.poc;


import org.cgi.poc.MainFragmentActivity;

import android.support.v4.app.Fragment;

public class BoardActivity extends MainFragmentActivity {

	  @Override
	  protected Fragment createFragment() {
		  return new BoardFragment(); 
	  }
}
