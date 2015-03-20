package org.cgi.poc;



import java.util.UUID;

import org.cgi.poc.MainFragmentActivity;

import android.support.v4.app.Fragment;
import android.util.Log;

public class AjouterBordActivity extends MainFragmentActivity {

	@Override
    protected Fragment createFragment() {
		Log.e("Demarrage de l'activte ", " --> AjouterBordActivity  "); 

        UUID boardId = (UUID)getIntent().getSerializableExtra(AjouterBoardFragment.EXTRA_BOARD_ID);
        return AjouterBoardFragment.newInstance(boardId);
    }
}
