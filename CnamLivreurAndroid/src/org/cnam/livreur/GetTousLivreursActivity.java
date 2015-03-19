package org.cnam.livreur;

import java.util.ArrayList;
import java.util.List;








import org.cnam.cloud.wsrest.model.User;



import org.cnam.cloud.wsrest.model.UserController;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class GetTousLivreursActivity extends Activity {
    private List<User> lists = null;
    private List<String> listsName = null;
    private ListView listLists;

    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.get_users);

	listLists = (ListView) findViewById(R.id.listLists);

	getUsers();

    }

    final void getUsers() {

	UserController list = new UserController();
	listsName = new ArrayList<String>();
	try {
	    lists = list.getAllUsers();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	if (lists != null) {
	    for (User u : lists) {
		if (u != null)
		    listsName.add(u.getFirstname() + " " + u.getLastname());
	    }

	    listLists.setAdapter(new ArrayAdapter<String>(getBaseContext(),
		    android.R.layout.simple_list_item_1, listsName));

	    listLists.setTextFilterEnabled(true);

	}
    }
}
