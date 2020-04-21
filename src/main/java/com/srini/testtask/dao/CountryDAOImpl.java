package com.srini.testtask.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.srini.testtask.entity.Country;

@Repository
public class CountryDAOImpl implements CountryDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public CountryDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Country> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Country> theQuery = currentSession.createQuery("from Country", Country.class);
		
		List<Country> countries = theQuery.getResultList();
		return countries;
	}

}
