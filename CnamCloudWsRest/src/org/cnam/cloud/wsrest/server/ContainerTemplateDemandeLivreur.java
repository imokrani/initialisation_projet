package org.cnam.cloud.wsrest.server;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MOKRANI
 *
 */
public class ContainerTemplateDemandeLivreur {

	List list_templateDemandeLivreur;
	

	
	public List getList_templateDemandeLivreur() {
		return list_templateDemandeLivreur;
	}

	public void setList_templateDemandeLivreur(List list_templateDemandeLivreur) {
		this.list_templateDemandeLivreur = list_templateDemandeLivreur;
	}

	public ContainerTemplateDemandeLivreur()
	 {
		list_templateDemandeLivreur = new ArrayList();
	 }

	 public ContainerTemplateDemandeLivreur(List list_templateDemandeLivreur)
	 {
	 this.list_templateDemandeLivreur = list_templateDemandeLivreur;
	 }
}
