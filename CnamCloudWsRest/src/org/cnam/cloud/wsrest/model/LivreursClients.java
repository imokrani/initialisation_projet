package org.cnam.cloud.wsrest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

public class LivreursClients  implements Serializable {


	  /**
	 * 
	 */
	private static final long serialVersionUID = 354930132868018596L;
	
	  @Id
	  private Long idClientLivreur;
	  private Long idClient;
	  private Long idLivreur;
	  private Date DateDemande;
	  private boolean approbationClient;
	  
	  public LivreursClients() {}
	  
	public Long getIdClientLivreur() {
		return idClientLivreur;
	}
	public void setIdClientLivreur(Long idClientLivreur) {
		this.idClientLivreur = idClientLivreur;
	}
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public Long getIdLivreur() {
		return idLivreur;
	}
	public void setIdLivreur(Long idLivreur) {
		this.idLivreur = idLivreur;
	}
	public Date getDateDemande() {
		return DateDemande;
	}
	public void setDateDemande(Date dateDemande) {
		DateDemande = dateDemande;
	}
	public boolean isApprobationClient() {
		return approbationClient;
	}
	public void setApprobationClient(boolean approbationClient) {
		this.approbationClient = approbationClient;
	}  
}
