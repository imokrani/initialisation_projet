package org.cnam.cloud.wsrest.model;

import java.io.Serializable;

import javax.persistence.Id;

public class Livreurs  implements Serializable {

	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -3931241320912222254L;
	
	 @Id
	 private Long idLivreur;
	 private String nomLivreur;
	 private String prenomLivreur;
	 private String mailLivreur;
	 private String loginLivreur;
	 private String motPasseLivreur;
	 private String telephone;
	 private String idTelephoneLivreur;
	 
	 
	 public Livreurs() {}
	 
	public Long getIdLivreur() {
		return idLivreur;
	}
	public void setIdLivreur(Long idLivreur) {
		this.idLivreur = idLivreur;
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
	public String getMailLivreur() {
		return mailLivreur;
	}
	public void setMailLivreur(String mailLivreur) {
		this.mailLivreur = mailLivreur;
	}
	public String getLoginLivreur() {
		return loginLivreur;
	}
	public void setLoginLivreur(String loginLivreur) {
		this.loginLivreur = loginLivreur;
	}
	public String getMotPasseLivreur() {
		return motPasseLivreur;
	}
	public void setMotPasseLivreur(String motPasseLivreur) {
		this.motPasseLivreur = motPasseLivreur;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getIdTelephoneLivreur() {
		return idTelephoneLivreur;
	}
	public void setIdTelephoneLivreur(String idTelephoneLivreur) {
		this.idTelephoneLivreur = idTelephoneLivreur;
	}
}
