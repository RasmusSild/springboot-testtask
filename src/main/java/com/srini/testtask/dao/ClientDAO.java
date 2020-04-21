package com.srini.testtask.dao;

import com.srini.testtask.entity.Client;

public interface ClientDAO {

	public void addOrUpdateClient(Client client);
	
	public Client findById(int id);
}
