package com.srini.testtask.service;

import com.srini.testtask.entity.Client;

public interface ClientService {
	
	public void addOrUpdateClient(Client client);
	
	public Client findById(int id);
}
