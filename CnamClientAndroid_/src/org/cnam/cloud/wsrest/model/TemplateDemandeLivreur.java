package org.cnam.cloud.wsrest.model;

import java.util.Date;

/**
 * @author MOKRANI
 *
 */
public class TemplateDemandeLivreur {

	
	  private Long idClient;
	  private Long idLivreur;
	  private Date DateDemande;
	  private boolean approbationClient;
	  private String nomLivreur;
	  private String prenomLivreur;
	  private String telephone;
	  private String adresse;
	  private String complementAdresse;
	  private String codePostal;
	  private String ville;
	  private String numeroBal;
	  
	  
	  public TemplateDemandeLivreur() {}
	  
	public TemplateDemandeLivreur(Long idClient, Long idLivreur,
			Date dateDemande, boolean approbationClient, String nomLivreur,
			String prenomLivreur, String telephone, String adresse,
			String complementAdresse, String codePostal, String ville,
			String numeroBal) {
		super();
		this.idClient = idClient;
		this.idLivreur = idLivreur;
		DateDemande = dateDemande;
		this.approbationClient = approbationClient;
		this.nomLivreur = nomLivreur;
		this.prenomLivreur = prenomLivreur;
		this.telephone = telephone;
		this.adresse = adresse;
		this.complementAdresse = complementAdresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numeroBal = numeroBal;
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
	public String getNomLivreur() {
		return nomLivreur;
	}
	public void setNomLivreur(String nomLivreur) {
		this.nomLivreur = nomLivreur;
	}
	public String getPrenomLivreur() {
		return prenomLivreur;
	}
	public void setPrenomLivreur(String prenomLivreur) {
		this.prenomLivreur = prenomLivreur;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getComplementAdresse() {
		return complementAdresse;
	}
	public void setComplementAdresse(String complementAdresse) {
		this.complementAdresse = complementAdresse;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getNumeroBal() {
		return numeroBal;
	}
	public void setNumeroBal(String numeroBal) {
		this.numeroBal = numeroBal;
	}
	  
	  
}
