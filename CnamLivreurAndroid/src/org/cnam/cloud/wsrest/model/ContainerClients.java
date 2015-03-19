package org.cnam.cloud.wsrest.model;

import java.util.ArrayList;
import java.util.List;

public class ContainerClients {

	public List<Clients> clients_list;

	public List<Clients>  getClients_list() {
		return clients_list;
	}

	public void setClients_list(List<Clients>  clients_list) {
		this.clients_list = clients_list;
	}
	
	public ContainerClients()
	 {
		clients_list = new ArrayList<Clients> ();
	 }

	 public ContainerClients(List<Clients>  clients_list)
	 {
	 this.clients_list = clients_list;
	 }
}
