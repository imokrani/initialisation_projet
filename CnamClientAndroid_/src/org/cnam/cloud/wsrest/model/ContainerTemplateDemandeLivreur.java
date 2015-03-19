package org.cnam.cloud.wsrest.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MOKRANI
 *
 */
public class ContainerTemplateDemandeLivreur {

	List<TemplateDemandeLivreur> list_templateDemandeLivreur;
	

	
	public List<TemplateDemandeLivreur> getList_templateDemandeLivreur() {
		return list_templateDemandeLivreur;
	}

	public void setList_templateDemandeLivreur(List<TemplateDemandeLivreur> list_templateDemandeLivreur) {
		this.list_templateDemandeLivreur = list_templateDemandeLivreur;
	}

	public ContainerTemplateDemandeLivreur()
	 {
		list_templateDemandeLivreur = new ArrayList<TemplateDemandeLivreur>();
	 }

	 public ContainerTemplateDemandeLivreur(List<TemplateDemandeLivreur> list_templateDemandeLivreur)
	 {
	 this.list_templateDemandeLivreur = list_templateDemandeLivreur;
	 }
}
