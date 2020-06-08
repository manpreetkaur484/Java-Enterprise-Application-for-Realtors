package ca.sheridancollege.beans;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Commercial implements Serializable{

	private static final long serialVersionUID = 1L;
    private String address;   
	private String propertyType;
	private String communityType;
	private String floorSpace;
	private String landSize;
	private String amenities;
	private double annualTax;
	private String transactionType;
	private int id;
	
	private String[]  propertyTypes = {" ","Office", "Retail", "Industrail", "Multifamily", "Hotel","Special Purpose", "Other"};
	private String[] communityTypes = {" ","Brampton East", "Claireville", "Ebenezer", "Victoria", "Springbrook", "Churchville", "Other"};
	private String[] transactionTypes = {" ","Rental", "For Lease", "For Sale"};
	private String[] floorSpaces = {" ","0-10,000sqft", "10,001-20,000sqft", "20,001-30,000sqft", "30,001-40,000sqft", "40,001-50,000sqft", "50,001-60,000sqft", "60,001-70,000sqft", "70,001-80,000sqft", "80,001-90,000sqft", "90,001-100,000sqft", "Over 100,000", "Over 150,000", "Over 200,000", "Over 250,000"};
	private String[] landSizes = {" ","Any", "1+ acres", "2+ acres", "5+ acres", "10+ acres", "50+ acres", "100+ acres", "200+ acres", "300+ acres", "400+ acres", "500+ acres", "1000+ acres"};
	
	private Commercial(String address, String propertyType, String communityType, String floorSpace, String landSize, String amenities, double annualTax, String transactionType) {
		//super();
		this.address = address;
		this.propertyType = propertyType;
		this.communityType = communityType;
		this.floorSpace = floorSpace;
		this.landSize = landSize;
		this.amenities = amenities;
		this.annualTax = annualTax;
		this.transactionType = transactionType;
	}
}
