package com.srini.testtask.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.srini.testtask.entity.Client;
import com.srini.testtask.entity.Country;
import com.srini.testtask.entity.User;
import com.srini.testtask.service.ClientService;
import com.srini.testtask.service.CountryService;
import com.srini.testtask.service.UserService;

@Controller
public class ClientController {
	
	private ClientService clientService;
	private UserService userService;
	private CountryService countryService;
	
	@Autowired
	public ClientController(ClientService theClientService, UserService theUserService, CountryService theCountryService) {
		clientService = theClientService;
		userService = theUserService;
		countryService = theCountryService;
	}
	
	@GetMapping("/client")
	public String addClient(Model model) {
		Client client = new Client();
		List<Country> countries = countryService.findAll();
		model.addAttribute("client", client);
		model.addAttribute("countries", countries);
		model.addAttribute("operation", "ADD");
		model.addAttribute("postUrl", "/client");
		
		return "client";
	}
	
	@GetMapping("/client/{clientId}")
	public String editClient(@PathVariable("clientId") int clientId, Model model) {
		Client client = clientService.findById(clientId);
		List<Country> countries = countryService.findAll();
		model.addAttribute("client", client);
		model.addAttribute("countries", countries);
		model.addAttribute("operation", "EDIT");
		model.addAttribute("postUrl", "/client/" + clientId);
		
		return "client";
	}
	
	@PostMapping("/client")
	public String addClient(
				@ModelAttribute @Valid Client newClient,
				BindingResult bindingResult, 
				Model model, 
				RedirectAttributes redirectAttributes,
				Authentication authentication
			) {
		if (bindingResult.hasErrors()) {
			List<Country> countries = countryService.findAll();
			model.addAttribute("countries", countries);
			model.addAttribute("operation", "ADD");
			return "client";
		} else {
			String currentUserName = authentication.getName();
			User currentUser = userService.findUserByName(currentUserName);
			newClient.setUser(currentUser);
			clientService.addOrUpdateClient(newClient);
			
			redirectAttributes.addFlashAttribute("successMessage", "Added new Client");
		
			return "redirect:/client";
		}
	}
	
	@PostMapping("/client/{clientId}")
	public String updateClient(
				@PathVariable("clientId") int clientId,
				@ModelAttribute @Valid Client updatedClient, 
				BindingResult bindingResult, 
				Model model, 
				RedirectAttributes redirectAttributes,
				Authentication authentication
			) {
		if (bindingResult.hasErrors()) {
			List<Country> countries = countryService.findAll();
			model.addAttribute("countries", countries);
			model.addAttribute("operation", "EDIT");
			return "client";
		} else {
			String currentUserName = authentication.getName();
			User currentUser = userService.findUserByName(currentUserName);
			updatedClient.setUser(currentUser);
			updatedClient.setId(clientId);
			clientService.addOrUpdateClient(updatedClient);
			
			redirectAttributes.addFlashAttribute("successMessage", "Client updated");
		
			return "redirect:/client/" + clientId;
		}
	}
	
}
