package com.srini.testtask.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srini.testtask.entity.Client;
import com.srini.testtask.service.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService service) {
		userService = service;
	}
	
	@RequestMapping("/")
	public String clientList(Model theModel, Authentication authentication) {
		String currentUser = authentication.getName();
		
		List<Client> clients = userService.findUserByName(currentUser).getClients();
		
		theModel.addAttribute("clientList", clients);
		
		return "index";
	}
	
}
