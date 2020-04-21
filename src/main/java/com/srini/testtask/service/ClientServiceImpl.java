package com.srini.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srini.testtask.dao.ClientDAO;
import com.srini.testtask.entity.Client;

@Service
public class ClientServiceImpl implements ClientService {
	
	private ClientDAO clientDAO;
	
	@Autowired
	public ClientServiceImpl(ClientDAO theClientDAO) {
		clientDAO = theClientDAO;
	}

	@Override
	@Transactional
	public void addOrUpdateClient(Client client) {
		clientDAO.addOrUpdateClient(client);
	}

	@Override
	@Transactional
	public Client findById(int id) {
		return clientDAO.findById(id);
	}

}
