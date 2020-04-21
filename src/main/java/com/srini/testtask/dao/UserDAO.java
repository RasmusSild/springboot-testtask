package com.srini.testtask.dao;

import com.srini.testtask.entity.User;

public interface UserDAO {

	public User findUserByName(String username);
}
