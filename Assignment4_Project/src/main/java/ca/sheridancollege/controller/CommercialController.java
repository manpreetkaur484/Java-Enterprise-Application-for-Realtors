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
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.beans.Commercial;
import ca.sheridancollege.beans.Residential;
import ca.sheridancollege.databases.DatabaseAccess;

@Repository
@Controller
public class CommercialController {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	@Lazy
	private DatabaseAccess da;
	
	@GetMapping("/add/property/commercial")
	public String addC(Model model){
		model.addAttribute("commercial", new Commercial());
		 return "realtor/addCommercial.html";
	}
	@GetMapping("/search/property/commercial")
	public String searchC(Model model){
		model.addAttribute("commercial", new Commercial());
		 return "searchCommercial.html";
	}
	
	//add data to commercial property tables
	@GetMapping("/addCommercial")
	public String addCommercial(Model model, @ModelAttribute Commercial commercial) {
		da.addCommercial(commercial);
		model.addAttribute("commercial", new Commercial());
		return "realtor/addCommercial.html";
	}
	
	
	// REALTOR-view data from commercial property
	@GetMapping("/realtor/view/property/commercial")
	public String viewPropertyCommercialR() {
		return "redirect:/viewRealtorCommercial";
	}

	@GetMapping("/viewRealtorCommercial")
	public String viewCommercialR(Model model) {
		List<Commercial> commercial = da.viewCommercial();
		model.addAttribute("coms", commercial);
		return "realtor/viewCommercial.html";
	}

	// BUYER-view data from commercial property
	@GetMapping("/buyer/view/property/commercial")
	public String viewPropertyCommercialB() {
		return "redirect:/viewBuyerCommercial";
	}

	@GetMapping("/viewBuyerCommercial")
	public String viewCommercialB(Model model) {
		List<Commercial> commercial = da.viewCommercial();
		model.addAttribute("coms", commercial);
		return "/buyer/viewBuyerCommercial.html";
	}
	
	//delete the record
	@GetMapping("/delete/Com/{id}")
	public String deleteCom(@PathVariable int id) {
		Commercial c = da.getCommercialPropertyById(id);
		da.deleteCommercial(id);
		return "redirect:/viewRealtorCommercial";
	}
	
	//edit the property
		@GetMapping("/edit/Com/{id}")
		public String editCom(@PathVariable int id, Model model) {
			Commercial c = da.getCommercialPropertyById(id);
			model.addAttribute("commercial", c);
			return "realtor/editCommercial.html";
		}
	//MODIFY
		@GetMapping("/modifyCommercial")
		public String editCommercial(Model model, @ModelAttribute Commercial commercial) {
			//Commercial c = new Commercial();
			da.editCommercial(commercial);
			model.addAttribute("commercial", da.viewCommercial());
			return "redirect:/viewRealtorCommercial";
		}
		
		//Generate Dummy Records
		@GetMapping("/dummy/records/commercial")
		public String dummyRecords() {
			da.createCommercialRecords();
			return "displayMsg.html";
		}
		
		//Search for Commercial Property
		@GetMapping("/search/Commercial")
		public String search(Model model,@RequestParam String address, 
				                         @RequestParam String propertyType,
				                         @RequestParam String floorSpace, 
				                         @RequestParam String landSize,
				                         @RequestParam String transactionType) {
			ArrayList<Commercial> c = da.searchCommercial(address, propertyType, floorSpace,landSize, transactionType);
			model.addAttribute("commercial", c);
			return "viewComSearch.html";
		}
}
