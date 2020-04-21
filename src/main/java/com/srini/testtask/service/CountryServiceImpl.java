package com.srini.testtask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srini.testtask.dao.CountryDAO;
import com.srini.testtask.entity.Country;

@Service
public class CountryServiceImpl implements CountryService {
	
	private CountryDAO countryDAO;
	
	@Autowired
	public CountryServiceImpl(CountryDAO theCountryDAO) {
		countryDAO = theCountryDAO;
	}

	@Override
	@Transactional
	public List<Country> findAll() {
		return countryDAO.findAll();
	}

}
