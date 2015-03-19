package org.cgi.poc;

import java.util.UUID;

import android.support.v4.app.Fragment;

public class CreateNotesActivity extends MainFragmentActivity{

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		

        UUID noteId = (UUID)getIntent().getSerializableExtra(CreateNotesFragment.EXTRA_NOTE_ID);
        String uuidBoard = (String)getIntent().getSerializableExtra(DataFragment.EXTRA_BOARD_ID);
        return CreateNotesFragment.newInstance(noteId , uuidBoard);
	}

}
