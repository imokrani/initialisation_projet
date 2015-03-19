package org.cnam.cloud.wsrest.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cnam.cloud.wsrest.model.Clients;
import org.cnam.cloud.wsrest.model.Livreurs;
import org.cnam.cloud.wsrest.model.LivreursClients;
import org.cnam.cloud.wsrest.model.TemplateDemandeLivreur;
import org.restlet.data.Form;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;


public class DemandeAccesBoiteLettres  extends ServerResource implements DemandeAccesBoiteLettresInterface{

	@Override
	public void create(LivreursClients livreursClients) {
		// TODO Auto-generated method stub
		 ObjectifyService.register(LivreursClients.class);
		 Objectify ofy = ObjectifyService.begin();

		 LivreursClients lc = new LivreursClients();
		 lc.setIdClient(livreursClients.getIdClient());
		 lc.setIdLivreur(livreursClients.getIdLivreur());
		 lc.setDateDemande(new Date());
		 lc.setApprobationClient(false);
		 try {
		     ofy.put(lc);
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }
	}

	@Override
	public ContainerTemplateDemandeLivreur getDemandesLivreurs() {
		// TODO Auto-generated method stub
		
		Form queryParams = getReference().getQueryAsForm();
		String idClient = queryParams.getFirstValue("idClient");
		 
		ContainerTemplateDemandeLivreur content = null; 
		
		List<LivreursClients> livreursClients = new ArrayList();
		
		 ObjectifyService.register(LivreursClients.class);
		 Objectify ofy = ObjectifyService.begin();
		 Query<LivreursClients> demandes = ofy.query(LivreursClients.class);
		// demandes.filter("idClient", idClient);
		 for (LivreursClients d : demandes) 
			 livreursClients.add(d);
          Clients cc = getClientsById(Long.parseLong(idClient));
		 List<TemplateDemandeLivreur> list_templateDemandeLivreur  = new ArrayList<TemplateDemandeLivreur>();
		 for (LivreursClients dem : livreursClients) {
			    TemplateDemandeLivreur t = new TemplateDemandeLivreur(); 
			  //  t.setIdClient(Long.parseLong(idClient));
			    t.setIdClient(dem.getIdClient());
			    t.setIdLivreur(dem.getIdLivreur());
			    t.setDateDemande(dem.getDateDemande());
			    t.setApprobationClient(dem.isApprobationClient());
			   // Clients client = getClientsById(dem.getIdClient()); 
			    Livreurs livreur = getLivreursById(dem.getIdLivreur());
			    t.setNomLivreur(livreur.getNomLivreur());
			    t.setPrenomLivreur(livreur.getPrenomLivreur());
			    t.setTelephone(livreur.getTelephone());
			    
			    t.setAdresse(cc.getAdresse());
			    t.setComplementAdresse(cc.getComplementAdresse());
			    t.setCodePostal(cc.getCodePostal());
			    t.setVille(cc.getVille());
			    t.setNumeroBal(cc.getNumeroBal());
			    
//			    t.setAdresse(client.getAdresse());
//			    t.setComplementAdresse(client.getComplementAdresse());
//			    t.setCodePostal(client.getCodePostal());
//			    t.setVille(client.getVille());
//			    t.setNumeroBal(client.getNumeroBal());
			    list_templateDemandeLivreur.add(t);
			    
		 }
		 
		 content = new ContainerTemplateDemandeLivreur(); 
		 content.setList_templateDemandeLivreur(list_templateDemandeLivreur);
		return content;
	}
	
	
	private Clients getClientsById(long idClient) {
		 ObjectifyService.register(Clients.class);
		 Objectify ofy = ObjectifyService.begin();
		 Query<Clients> clients = ofy.query(Clients.class);
		 clients.filter("idClient", idClient);
			for (Clients c : clients) {
				 //if(c.getIdClient().equals(idClient)) 
				          return c;
			}
			return null;
	}
	
	private Livreurs getLivreursById(long idLivreur) {
		 ObjectifyService.register(Livreurs.class);
		 Objectify ofy = ObjectifyService.begin();
		 Query<Livreurs> livreurs = ofy.query(Livreurs.class);
		 livreurs.filter("idLivreur", idLivreur);
			for (Livreurs l : livreurs) {
				 //if(l.getIdLivreur().equals(idLivreur)) 
				          return l;
			}
			return null;
	}

//	@Override
//	public void approbationAccesBAL() {
//		// TODO Auto-generated method stub
//		Form queryParams = getReference().getQueryAsForm();
//		String idClient = queryParams.getFirstValue("idClient");
//		String idLivreur = queryParams.getFirstValue("idLivreur");
//		ObjectifyService.register(LivreursClients.class);
//		Objectify ofy = ObjectifyService.begin();
//		Query<LivreursClients> demandes = ofy.query(LivreursClients.class);
//	
//		demandes.filter("idClient", Long.parseLong(idClient)); 
//		demandes.filter("idLivreur", Long.parseLong(idLivreur)); 
//		for(LivreursClients lc: demandes) {
//			lc.setApprobationClient(true);
//			ofy.put(lc);
//		}
//	}

}
