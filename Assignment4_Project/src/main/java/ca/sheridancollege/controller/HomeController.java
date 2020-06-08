package ca.sheridancollege.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.beans.Commercial;
import ca.sheridancollege.beans.Residential;
import ca.sheridancollege.databases.DatabaseAccess;

@Repository
@Controller
public class HomeController {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	@Lazy
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String goAdd(Model model) {
		//Residential residential = new Residential();
		return "Home.html";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("/mortgage")
	public String mortgage(){
		return "MortgageCalculator.html";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/error/access-denied.html";
	}
	@PostMapping("/register")
	public String doRegistration(
	@RequestParam String username,
	@RequestParam String password,
	@RequestParam (required=false) boolean buyer,
	@RequestParam (required=false) boolean realtor){
		
		da.addUser(username, password);
		long userId = da.findUserAccount(username).getUserId();

		if(buyer) {
			da.addRole(userId,1);	
		}
		if(realtor) {	
			da.addRole(userId,2);
		}
		return "redirect:/";
	}
	@GetMapping("/register")
	public String goRegister() {
		return "registration.html";
	}
	
	@GetMapping("/view")
	public String viewList(Authentication authentication, Model model) {
		//String name = authentication.getName();
		ArrayList<String> roles = new ArrayList<String>();
		for(GrantedAuthority ga: authentication.getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		
		if(roles.contains("ROLE_BUYER")) {
			//commercials.addAll(da.viewCommercial());
			//residentials.addAll(da.viewResidential());
			return "buyer/viewBuyers.html";
		}
		if(roles.contains("ROLE_REALTOR")) {
			//commercials.addAll(da.viewCommercial());
			//residentials.addAll(da.viewResidential());
			return "realtor/viewRealtors.html";
		}
		return null;
	
}
	@GetMapping("/user")
	public String goLogin() {
		return "redirect:/view";
	}
}