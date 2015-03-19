package org.cnam.cloud.wsrest.server;

import java.util.ArrayList;
import java.util.List;

import org.cnam.cloud.wsrest.model.Livreurs;
import org.cnam.cloud.wsrest.model.User;
import org.restlet.data.Form;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class LivreurService extends ServerResource implements LivreurServiceInterface{

	@Override
	public void create(Livreurs livreur) {
		
		 ObjectifyService.register(Livreurs.class);
		 Objectify ofy = ObjectifyService.begin();

		 Livreurs tp = new Livreurs();
		
		 tp.setNomLivreur(livreur.getNomLivreur());
		 tp.setPrenomLivreur(livreur.getPrenomLivreur());
		 tp.setMailLivreur(livreur.getMailLivreur());
		 tp.setLoginLivreur(livreur.getLoginLivreur());
		 tp.setMotPasseLivreur(livreur.getMotPasseLivreur());
		 tp.setTelephone(livreur.getTelephone());
		 tp.setIdTelephoneLivreur(livreur.getIdTelephoneLivreur());
		 ofy.put(tp);
	}

	@Override
	public   ContainerLivreurs connexionLivreur() {
		/*
		 * recuperation des parametre de connexion 
		 */
		Form queryParams = getReference().getQueryAsForm();
		 //String mail = queryParams.getFirstValue("mail");
		 String login = queryParams.getFirstValue("login");
		 String motPasse = queryParams.getFirstValue("motPasse");
	  	 ContainerLivreurs content = null;
		 List livreurs = new ArrayList();
		 ObjectifyService.register(Livreurs.class);
		 Objectify ofy = ObjectifyService.begin();
		 Query<Livreurs> q = ofy.query(Livreurs.class);
		 q.filter("loginLivreur", login);
		 q.filter("motPasseLivreur", motPasse);
		for (Livreurs u : q) {
			 //if((u.getMailLivreur().equals(mail)) && (u.getMotPasseLivreur().equals(motPasse))) 
			          livreurs.add(u);
		}
		 content = new ContainerLivreurs();
		 content.setLivreurs_list(livreurs);
		 return content;
	}

//	@Override
//	public boolean connexionLivreur (){
//		
//		 Form queryParams = getReference().getQueryAsForm();
//		 String login = queryParams.getFirstValue("login");
//		 String motPasse = queryParams.getFirstValue("motPasse");
//		 System.out.println("dfssssssssssssssssssssssssssssssssssssssssssssssssssssdfsdfsdf");
//		
//		Objectify ofy = ObjectifyService.begin();
//		
//		Query<Livreurs> query = ofy.query(Livreurs.class);
////		query.filter("loginLivreur =", login);
////		query.filter("motPasseLivreur =", motPasse);
//		
//		Livreurs livreurs = query.get();
//		
//		if(livreurs==null) 
//		{
//			return false; 
//			//throw new IllegalArgumentException("Aucun utilisateur : "+login+" dans la base");
//			   
//		}
//		return true;
//	}

}
