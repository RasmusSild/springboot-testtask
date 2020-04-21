package com.srini.testtask.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.srini.testtask.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public UserDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public User findUserByName(String username) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		String hql = "from User "  + 
	             "where username = :username";
		Query query = currentSession.createQuery(hql);
		query.setParameter("username", username);
	
		User currentUser = (User)query.getSingleResult();
		
		return currentUser;
	}
}