package com.srini.testtask.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.srini.testtask.entity.Client;

@Repository
public class ClientDAOImpl implements ClientDAO {
	
    private EntityManager entityManager;
	
	@Autowired
	public ClientDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public void addOrUpdateClient(Client newClient) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(newClient);
	}

	@Override
	public Client findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Client client = currentSession.get(Client.class, id);
		return client;
	}

}
