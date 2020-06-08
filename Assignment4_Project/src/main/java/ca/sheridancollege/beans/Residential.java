package ca.sheridancollege.beans;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Residential implements  Serializable{
    private static final long serialVersionUID = 1L;
	private String address;
	private String  propertyType;      
	private String buildingType;      
	private String communityType;     
	private double annualTax;         
	private int parkingSpaces;     
	private int bedrooms;          
	private int bathrooms;         
	private String basement;       
	private String swimmingPool;      
	private String amenities;
	private int id;
	 
	private String[]  propertyTypes = {" ", "Single Family Home", "Multi Family Home", "Condominium", "Townhouse", "Cooprative", "Land"};
	private String[] buildingTypes = {" ", "Detached", "Semi-Detached", "Apartment", "Condominium", "Townhouse"};
	private String[] communityTypes = {" ", "Brampton East", "Claireville", "Ebenezer", "Victoria", "Springbrook", "Churchville", "Other"};
	private String[] basements = {" ", "Finished", "Not Finished"};
	private String[] swimmingPools = {" ", "Yes", "No"};
	
	
	private Residential(String propertyType, String buildingType, String communityType, double annualTax, int parkingSpaces, int bedrooms, int bathrooms, String basement, String swimmingPool, String amenities) {
	//	super();
		this.address= address;
		this.propertyType = propertyType;      
		this.buildingType = buildingType;      
		this.communityType = communityType;     
		this.annualTax = annualTax;         
		this.parkingSpaces =parkingSpaces;     
		this.bedrooms = bedrooms;          
		this.bathrooms = bathrooms;         
		this.basement = basement;       
		this.swimmingPool = swimmingPool;      
		this.amenities = amenities;
	}
	
	
}
