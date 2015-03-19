package org.cnam.cloud.wsrest.model;

import java.util.ArrayList;
import java.util.List;

public class ContainerLivreurs {

	
	public List<Livreurs> livreurs_list;

      

	 public List<Livreurs> getLivreurs_list() {
		return livreurs_list;
	}

	public void setLivreurs_list(List<Livreurs> livreurs_list) {
		this.livreurs_list = livreurs_list;
	}

	public ContainerLivreurs()
	 {
		 livreurs_list = new ArrayList<Livreurs>();
	 }

	 public ContainerLivreurs(List<Livreurs> livreurs_list)
	 {
	 this.livreurs_list = livreurs_list;
	 }
}
