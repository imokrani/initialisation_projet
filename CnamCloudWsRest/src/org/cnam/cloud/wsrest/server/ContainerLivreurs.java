package org.cnam.cloud.wsrest.server;

import java.util.ArrayList;
import java.util.List;

public class ContainerLivreurs {

	
	public List livreurs_list;

      

	 public List getLivreurs_list() {
		return livreurs_list;
	}

	public void setLivreurs_list(List livreurs_list) {
		this.livreurs_list = livreurs_list;
	}

	public ContainerLivreurs()
	 {
		 livreurs_list = new ArrayList();
	 }

	 public ContainerLivreurs(List livreurs_list)
	 {
	 this.livreurs_list = livreurs_list;
	 }
}
