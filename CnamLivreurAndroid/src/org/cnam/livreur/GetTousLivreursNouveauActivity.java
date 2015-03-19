package org.cnam.livreur;

import java.util.ArrayList;
import java.util.List;

import org.cnam.cloud.wsrest.model.LivreurService;
import org.cnam.cloud.wsrest.model.Livreurs;
import org.cnam.cloud.wsrest.model.User;
import org.cnam.cloud.wsrest.model.UserController;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GetTousLivreursNouveauActivity extends Activity {

	
	private List<Livreurs> lists = null;
    private List<String> listsName = null;
    private ListView listLists;

    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.get_livreurs);

	listLists = (ListView) findViewById(R.id.listLists);

	getLivreurs();

    }

    final void getLivreurs() {

	LivreurService list = new LivreurService();
	listsName = new ArrayList<String>();
	try {
	    lists = list.getAllLivreurs("zz", "zz");
	    Log.e("taille des livrueur renvoyer  ----------->   ", ""+lists.size());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	if (lists != null) {
	    for (Livreurs u : lists) {
		if (u != null)
		    listsName.add(u.getNomLivreur() + " " + u.getPrenomLivreur());
	    }

	    listLists.setAdapter(new ArrayAdapter<String>(getBaseContext(),
		    android.R.layout.simple_list_item_1, listsName));

	    listLists.setTextFilterEnabled(true);

	}
    }
}
