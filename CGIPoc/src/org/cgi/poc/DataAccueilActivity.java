package org.cgi.poc;

import android.support.v4.app.Fragment;

public class DataAccueilActivity extends MainFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new DataAccueilFragment();
	}

}
