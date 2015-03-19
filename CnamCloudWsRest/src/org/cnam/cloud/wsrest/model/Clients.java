package org.cnam.cloud.wsrest.model;

import java.io.Serializable;

import javax.persistence.Id;

/**
 * @author MOKRANI
 *
 */
public class Clients  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3658986464079641231L;

	 @Id
	 private Long idClient;
	 private String nomClient;
	 private String prenomClient;
	 private String mailClient;
	 private String loginClient;
	 private String motPasseClient;
	 private String idTelephoneClient;
	 private String adresse;
	 private String complementAdresse;
	 private String codePostal;
	 private String ville;
	 private String numeroBal;
	 
	 
	 public Clients() {}
	 
	 
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getPrenomClient() {
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	public String getMailClient() {
		return mailClient;
	}
	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
	}
	public String getLoginClient() {
		return loginClient;
	}
	public void setLoginClient(String loginClient) {
		this.loginClient = loginClient;
	}
	public String getMotPasseClient() {
		return motPasseClient;
	}
	public void setMotPasseClient(String motPasseClient) {
		this.motPasseClient = motPasseClient;
	}

	public String getIdTelephoneClient() {
		return idTelephoneClient;
	}
	public void setIdTelephoneClient(String idTelephoneClient) {
		this.idTelephoneClient = idTelephoneClient;
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
