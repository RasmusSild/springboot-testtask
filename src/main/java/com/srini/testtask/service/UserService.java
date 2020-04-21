package com.srini.testtask.service;

import com.srini.testtask.entity.User;

public interface UserService {
	public User findUserByName(String username);
}
