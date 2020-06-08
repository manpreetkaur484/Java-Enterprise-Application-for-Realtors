package ca.sheridancollege.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.beans.Commercial;
import ca.sheridancollege.beans.Residential;
import ca.sheridancollege.databases.DatabaseAccess;

@Repository
@Controller
public class ResidentialController {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	@Lazy
	private DatabaseAccess da;
	
	@GetMapping("/add/property/residential")
	public String addR(Model model) {
		model.addAttribute("residential", new Residential());
		 return "realtor/addResidential.html";
	}
	@GetMapping("/search/property/residential")
	public String searchR(Model model){
		model.addAttribute("residential", new Residential());
		 return "searchResidential.html";
	}

	// add data to residential property table
	@GetMapping("/addResidential")
	public String addCommercial(Model model, @ModelAttribute Residential residential) {
		da.addResidential(residential);
		model.addAttribute("residential", new Residential());
		return "realtor/addResidential.html";
	}

	// REALTOR-view data from residential property
	@GetMapping("/realtor/view/property/residential")
	public String viewPropertyResidentialR() {
		return "redirect:/viewRealtorResidential";
	}

	@GetMapping("/viewRealtorResidential")
	public String viewResidentialR(Model model) {
		List<Residential> residential = da.viewResidential();
		model.addAttribute("resd", residential);
		return "realtor/viewResidential.html";
	}

	// BUYER-view data from residential property
	@GetMapping("/buyer/view/property/residential")
	public String viewPropertyResidentialB() {
		return "redirect:/viewBuyerResidential";
	}

	@GetMapping("/viewBuyerResidential")
	public String viewResidentialB(Model model) {
		List<Residential> residential = da.viewResidential();
		model.addAttribute("resd", residential);
		return "buyer/viewBuyerResidential.html";
	}

	// delete the record
	@GetMapping("/delete/Res/{id}")
	public String deleteRes(@PathVariable int id) {
		Residential c = da.getResidentialPropertyById(id);
		da.deleteResidential(id);
		return "redirect:/viewRealtorResidential";
	}

	// edit the property
	@GetMapping("/edit/Res/{id}")
	public String editRes(@PathVariable int id, Model model) {
		Residential r = da.getResidentialPropertyById(id);
		model.addAttribute("residential", r);
		return "realtor/editResidential.html";
	}

	// MODIFY
	@GetMapping("/modifyResidential")
	public String editResidential(Model model, @ModelAttribute Residential residential) {
		// Residential r = new Residential();
		da.editResidential(residential);
		model.addAttribute("residential", da.viewResidential());
		return "redirect:/viewRealtorResidential";
	}

	// Generate Dummy Records
	@GetMapping("/dummy/records/residential")
	public String dummyRecords() {
		da.createResidentialRecords();
		return "displayMsg.html";
	}
	//Search for Residential Property
	@GetMapping("/search/residential")
	public String search(Model model,
			            @RequestParam String address,
			            @RequestParam String propertyType,
			            @RequestParam int bedrooms, 
			            @RequestParam int bathrooms,
			            @RequestParam String basement, 
			            @RequestParam String swimmingPool){
		ArrayList<Residential> r = da.searchResidential(address, propertyType, bedrooms, bathrooms, basement, swimmingPool);
		model.addAttribute("residential", r);
		return "viewResSearch.html";
	}
	
}
