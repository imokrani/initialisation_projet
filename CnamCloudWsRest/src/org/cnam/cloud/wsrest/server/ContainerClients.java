package org.cnam.cloud.wsrest.server;

import java.util.ArrayList;
import java.util.List;

public class ContainerClients {

	public List clients_list;

	public List getClients_list() {
		return clients_list;
	}

	public void setClients_list(List clients_list) {
		this.clients_list = clients_list;
	}
	
	public ContainerClients()
	 {
		clients_list = new ArrayList();
	 }

	 public ContainerClients(List clients_list)
	 {
	 this.clients_list = clients_list;
	 }
}
