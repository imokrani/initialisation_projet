package org.cnam.livreur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AccueilLivreurNouveauActivity  extends Activity {

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_livreurs);
        
        Button ajouterLivreur = (Button) findViewById(R.id.ajouterlivreurnouveau);
        ajouterLivreur.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		Intent i = new Intent(AccueilLivreurNouveauActivity.this, AjouterLivreurNouveauActivity.class);
		startActivity(i);
	    }
	});
        
        
        Button getTousLivreur = (Button) findViewById(R.id.gettoustivreursnouveau);
        getTousLivreur.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		Intent i = new Intent(AccueilLivreurNouveauActivity.this, GetTousLivreursNouveauActivity.class);
		startActivity(i);
	    }
	});
        
    }
}
