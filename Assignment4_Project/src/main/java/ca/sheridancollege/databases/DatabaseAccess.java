package ca.sheridancollege.databases;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import ca.sheridancollege.beans.Commercial;
import ca.sheridancollege.beans.Residential;
import ca.sheridancollege.beans.User;

@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//ADD data in the commercial table
	public void addCommercial(Commercial commercial) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();	
		String query = "INSERT INTO commercial_property (address, propertyType, communityType, floorSpace, landSize, amenities, annualTax, transactionType) VALUES"
				+ "(:address, :propertyType, :communityType, :floorSpace, :landSize, :amenities, :annualTax, :transactionType)";
		parameters.addValue("address", commercial.getAddress());
		parameters.addValue("propertyType", commercial.getPropertyType());
		parameters.addValue("communityType", commercial.getCommunityType());
		parameters.addValue("floorSpace", commercial.getFloorSpace());
		parameters.addValue("landSize", commercial.getLandSize());
		parameters.addValue("amenities", commercial.getAmenities());
		parameters.addValue("annualTax", commercial.getAnnualTax());
		parameters.addValue("transactionType", commercial.getTransactionType());
				
		jdbc.update(query, parameters);
	}
	//ADD data in the residential table
	public void addResidential(Residential residential) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();	
		String query = "INSERT INTO residential_property(address, propertyType, buildingType, communityType, annualTax, parkingSpaces, bedrooms, bathrooms, basement, swimmingPool, amenities) VALUES "
				+ "(address, :propertyType, :buildingType, :communityType, :annualTax, :parkingSpaces, :bedrooms, :bathrooms, :basement, :swimmingPool, :amenities)";
		        parameters.addValue("address", residential.getAddress());
		        parameters.addValue("propertyType", residential.getPropertyType());
				parameters.addValue("buildingType", residential.getBuildingType());
				parameters.addValue("communityType", residential.getCommunityType());
				parameters.addValue("annualTax", residential.getAnnualTax());
				parameters.addValue("parkingSpaces", residential.getParkingSpaces());
				parameters.addValue("bedrooms", residential.getBedrooms());
				parameters.addValue("bathrooms", residential.getBathrooms());
				parameters.addValue("basement", residential.getBasement());
				parameters.addValue("swimmingPool", residential.getSwimmingPool());
				parameters.addValue("amenities", residential.getAmenities());
						
		jdbc.update(query, parameters);
	}
	
	//view(Get) data for commercial property
	public ArrayList<Commercial> viewCommercial() {
		String q = "SELECT * FROM commercial_property";
		ArrayList<Commercial> com = (ArrayList<Commercial>) jdbc.query(q,
				new BeanPropertyRowMapper<Commercial>(Commercial.class));
		return com;
	}
	//view(Get) data for residential Property
	public ArrayList<Residential> viewResidential() {
		String q = "SELECT * FROM residential_property";
		ArrayList<Residential> res = (ArrayList<Residential>) jdbc.query(q,
				new BeanPropertyRowMapper<Residential>(Residential.class));
		return res;
}

	//Get residential property by id
	public Residential getResidentialPropertyById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String q = "SELECT * FROM residential_property where id=:id";
		parameters.addValue("id", id);
		ArrayList<Residential> property = (ArrayList<Residential>) jdbc.query(q, parameters,
				new BeanPropertyRowMapper<Residential>(Residential.class));

		if (property.size() > 0)
			return property.get(0);
		else
			return null;
	}
	//Get Commercial property by id
	public Commercial getCommercialPropertyById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String q = "SELECT * FROM commercial_property where id=:id";
		parameters.addValue("id", id);
		ArrayList<Commercial> property = (ArrayList<Commercial>) jdbc.query(q, parameters,
				new BeanPropertyRowMapper<Commercial>(Commercial.class));

		if (property.size() > 0)
			return property.get(0);
		else
			return null;
	}
	
	//Delete Residential property 
	public void deleteResidential(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String q = "DELETE from residential_property WHERE id=:id";
		parameters.addValue("id", id);
		jdbc.update(q, parameters);	
	}
	//Delete Commercial property
	public void deleteCommercial(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String q = "DELETE from commercial_property WHERE id=:id";
		parameters.addValue("id", id);
		jdbc.update(q, parameters);	
	}
	//edit commercial property
	public void editCommercial(Commercial commercial) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String q = "UPDATE commercial_property SET address=:address, propertyType =:propertyType,  communityType =:communityType, floorSpace =:floorSpace, landSize=:landSize, amenities=:amenities, annualTax=:annualTax,  transactionType=:transactionType WHERE id=:id";
		parameters.addValue("id", commercial.getId());
		parameters.addValue("address", commercial.getAddress());
		parameters.addValue("propertyType", commercial.getPropertyType());
		parameters.addValue("communityType", commercial.getCommunityType());
		parameters.addValue("floorSpace", commercial.getFloorSpace());
		parameters.addValue("landSize", commercial.getLandSize());
		parameters.addValue("amenities", commercial.getAmenities());
		parameters.addValue("annualTax", commercial.getAnnualTax());
		parameters.addValue("transactionType", commercial.getTransactionType());
		jdbc.update(q, parameters);
	}
	//edit residential property
	public void editResidential(Residential residential) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String q = "UPDATE residential_property SET address=:address, propertyType =:propertyType, buildingType=:buildingType, communityType =:communityType, annualTax=:annualTax, parkingSpaces=:parkingSpaces, bedrooms=:bedrooms, bathrooms=:bathrooms, basement=:basement, swimmingPool=:swimmingPool, amenities=:amenities WHERE id=:id";
		parameters.addValue("id", residential.getId());
		parameters.addValue("address", residential.getAddress());
		parameters.addValue("propertyType", residential.getPropertyType());
		parameters.addValue("buildingType", residential.getBuildingType());
		parameters.addValue("communityType", residential.getCommunityType());
		parameters.addValue("annualTax", residential.getAnnualTax());
		parameters.addValue("parkingSpaces", residential.getParkingSpaces());
		parameters.addValue("bedrooms", residential.getBedrooms());
		parameters.addValue("bathrooms", residential.getBathrooms());
		parameters.addValue("basement", residential.getBasement());
		parameters.addValue("swimmingPool", residential.getSwimmingPool());
		parameters.addValue("amenities", residential.getAmenities());
		jdbc.update(q, parameters);
	}
	//generate dummy records for commercial table
	public void createCommercialRecords() {
	String query = "INSERT INTO commercial_property(address, propertyType,communityType, floorSpace, landSize, amenities, annualTax, transactionType ) VALUES "
			+ "('5 Resolution Dr Unit 1, Brampton', 'Office', 'Brampton East', '10,001-20,000sqft', '1+ acres', 'Public Transit', 3000.00, 'Rental'),"
			+ "('6780 Davand Dr Unit 24, Mississauga', 'Retail', 'Claireville', '0-10,000sqft', '2+ acres', 'Public Transit, Near Highway', 4000.00, 'For Lease'),"
			+ "('30 Coventry Rd,Brmapton', 'Industrail', 'Ebenezer', '10,001-20,000sqft', '5+ acres', 'Public Transit, Shopping Mall', 5000.00, 'For Sale'),"
			+ "('49 McMurchy Ave N, Brampton', 'Multifamily', 'Victoria', '20,001-30,000sqft', '10+ acres', 'Grocery Shop, Library, Public Transit', 6000.00, 'Rental'),"
			+ "('Comfort Inn, Brampton', 'Hotel', 'Springbrook', '30,001-40,000sqft', '50+ acres', 'Convenience Store, Library, Public Transit', 7000.00, 'For Lease'),"
			+ "('Oak Lane Community, Blacksburg', 'Special Purpose', 'Churchville', '40,001-50,000sqft', '100+ acres', 'Medical Center, Public Transit, Grocery Shop', 7500.00, 'For Sale'),"
			+ "('350 Rutherford Rd S #10, Brampton', 'Other', 'Other', '50,001-60,000sqft', '200+ acres', 'Near Highway, Public Transit', 7600.00, 'Rental'),"
			+ "('110 Brickyard Way #11-14, Brampton', 'Retail', 'GTA', '60,001-70,000sqft', '300+ acres', 'Community Center, Public Transit', 8000.00, 'For Lease'),"
			+ "('495 Industrial Park Rd, Blacksburg', 'Industrail', 'Claireville', '70,001-80,000sqft', '400+ acres', 'Library, Public Transit, Near Highway', 8100.00, 'For Sale'),"
			+ "('25 Rambler Dr, Brampton', 'Multifamily', 'SpringBook', '80,001-90,000sqft', '500+ acres', 'Library, Public Transit, Near Highway', 8200.00, 'Rental'),"
			+ "('Quality Inn & Suites, Brampton', 'Hotel', 'Ebenezer', '90,001-100,000sqft', '1000+ acres', 'Medical Center, Public Transit, Grocery Shop', 8300.00, 'For Lease'),"
			+ "('Oak Lane Community, Blacksburg', 'Special Purpose', 'Victoria', 'Over 100,000', '500+ acres', 'Grocery Shop, Library, Public Transit', 9000.00, 'For Sale'),"
			+ "('499 Industrial Park Rd, Blacksburg', 'Other', 'Churchville', 'Over 150,000', '1000+ acres', 'Public Transit, Near Highway', 10000.00, 'Rental'),"
			+ "('345 Queen St W, Brampton', 'Retail', 'Brampton East', 'Over 200,000', '500+ acres', 'Public Transit, Shopping Mall', 20000.00, 'For Lease'),"
			+ "('NU Hotel, 6465 Airport Rd, Mississauga', 'Hotel', 'Ebenezer', 'Over 250,000', '1000+ acres', 'Medical Center, Public Transit, Grocery Shop', 21000.00, 'For Sale')";
	jdbc.update(query, new HashMap());
	}
	//generate dummy records for residential table
	public void createResidentialRecords() {
		String query = "INSERT INTO residential_property(address, propertyType, buildingType, communityType, annualTax, parkingSpaces, bedrooms, bathrooms, basement, swimmingPool, amenities) VALUES "
				+ "('46 Don Minkaer Drive, Brampton','Single Family Home', 'Detached', 'Brampton East',2000.00, 3, 4, 3, 'Yes', 'No', 'Public Transit, Near Highway'),"
				+ "('45 Trevino Cres, Brampton', 'Multi Family Home', 'Semi-Detached', 'Claireville',3000.00, 10, 7, 5, 'Yes', 'Yes', 'Public Transit, Shopping Mall'),"
				+ "('47 Deerglen Drive, Brampton','Condominium', 'Condominium', 'Ebenezer',4000.00, 50, 30, 15, 'Yes', 'Yes', 'Grocery Shop, Library, Public Transit'),"
				+ "('36 Grassington Ct, Brmapton', 'Townhouse', 'Townhouse', 'Victoria',5000.00, 2, 2, 2, 'No', 'No', 'Convenience Store, Library, Public Transit'),"
				+ "('24 Diploma Drive, Brampton', 'Cooprative', 'Condominium', 'Springbrook',6000.00, 3, 3, 3, 'Yes', 'No', 'Medical Center, Public Transit, Grocery Shop'),"
				+ "('5024 Kempling Lane, Burlington', 'Land', 'Townhouse', 'Churchville',7000.00, 0, 0, 0, 'No', 'No', 'Near Highway, Public Transit'),"
				+ "('50 Sunny Meadow Blvd, Brampton', 'Single Family Home', 'Detached', 'GTA',8000.00, 4, 4, 3, 'Yes', 'No', 'Community Center, Public Transit'),"
				+ "('398 Humberwood Blvd, Etobicoke', 'Multi Family Home', 'Semi-Detached', 'Oakville',5000.00, 10, 8, 5, 'Yes', 'Yes', 'Library, Public Transit, Near Highway'),"
				+ "('5599 Ambler Dr, Mississauga', 'Condominium', 'Condominium', 'Victoria',4000.00, 1,1, 1, 'No', 'No', 'Public Transit, Near Highway'),"
				+ "('950 Burnhamthorpe Rd W, Mississauga', 'Townhouse', 'Townhouse', 'Brampton East',2000.00, 2, 2, 2, 'No', 'No', 'Public Transit, Shopping Mall'),"
				+ "('9455 Mississauga Rd, Brampton', 'Single Family Home', 'Detached', 'Brampton',3000.00, 5, 4, 3, 'Yes', 'No', 'Grocery Shop, Library, Public Transit'),"
				+ "('600 Kenilworth Ave N, Hamilton', 'Single Family Home', 'Detached', 'Mississauga',8800.00, 5, 4, 3, 'Yes', 'No', 'Convenience Store, Library, Public Transit'),"
				+ "('9940 Airport Rd, Brampton', 'Condominium', 'Condominium', 'Churchville',2200.00, 2, 2, 1, 'No', 'No', 'Medical Center, Public Transit, Grocery Shop'),"
				+ "('36 Grassington Ct, Brampton', 'Multi Family Home', 'Semi-Detached', 'Claireville',2400.00, 5, 4, 3, 'Yes', 'No', 'Community Center, Public Transit'),"
				+ "('78 Braemar Dr, Bampton', 'Single Family Home', 'Detached', 'Brampton East',2500.00, 2, 2, 1, 'No', 'No', 'Library, Public Transit, Near Highway')";
		jdbc.update(query, new HashMap());
	}
	
	//SEARCH for Commercial Property  
	public ArrayList<Commercial> searchCommercial(String address, String propertyType, String floorSpace,String landSize, String transactionType){
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("address", address);
		parameters.addValue("propertyType", propertyType);
		parameters.addValue("floorSpace", floorSpace);
		parameters.addValue("landSize", landSize);
		parameters.addValue("transactionType", transactionType);
		String q = "SELECT * FROM commercial_property WHERE propertyType=:propertyType OR floorSpace =:floorSpace OR landSize=:landSize OR transactionType=:transactionType OR address LIKE '%" + address + "%'";
		ArrayList<Commercial> property = (ArrayList<Commercial>) jdbc.query(q, parameters,
				new BeanPropertyRowMapper<Commercial>(Commercial.class));
		return property;
	}
	//SEARCH for Residential Property
	public ArrayList<Residential> searchResidential(String address, String propertyType, int bedrooms, int bathrooms, String basement, String swimmingPool){
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("address", address);
		parameters.addValue("propertyType", propertyType);
		parameters.addValue("bedrooms", bedrooms);
		parameters.addValue("bathrooms", bathrooms);
		parameters.addValue("basement", basement);
		parameters.addValue("swimmingPool", swimmingPool);
		String q = "SELECT * FROM residential_property WHERE propertyType=:propertyType OR bedrooms=:bedrooms OR bathrooms=:bathrooms OR basement=:basement OR swimmingPool=:swimmingPool OR address LIKE '%" + address + "%'";
        ArrayList<Residential> property = (ArrayList<Residential>)jdbc.query(q, parameters,
        		new BeanPropertyRowMapper<Residential>(Residential.class));	
	  return property;
	}
	//Find User Account from database
	public  User findUserAccount(String userName) {
		String query = "SELECT * FROM sec_user where userName=:userName";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userName", userName);
		ArrayList<User> userList = 
				(ArrayList<User>)jdbc.query(query, parameters,
		new BeanPropertyRowMapper<User>(User.class));
		if (userList.size()>0)
		return userList.get(0);
		else
		return null;
		}
	//Find User Role from databases
	public List<String> getRolesById(long userId) {
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "select user_role.userId, sec_role.roleName "
		+ "FROM user_role, sec_role "
		+ "WHERE user_role.roleId=sec_role.roleId "
		+ "and userId=:userId";

		parameters.addValue("userId", userId);
		List<Map<String, Object>> rows =
				jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
		roles.add((String)row.get("roleName"));
		}
		return roles;
	}
	//Add user to Databases
	public void addUser(String username, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
			String q =
	"insert into sec_user (userName, encryptedPassword, ENABLED) values (:userName, :password,1)";		
	parameters.addValue("userName", username);
	parameters.addValue("password", passwordEncoder.encode(password));
	jdbc.update(q, parameters);		
	}
	
	public void addRole(long userId, long roleId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String  q = "INSERT INTO user_role(userId, roleId)values(:userId, :roleId)";
    	parameters.addValue("userId", userId);
		parameters.addValue("roleId", roleId);
		jdbc.update(q, parameters);
	}
	//Assign the UserId and RoleId
	public void addRole(String userName, String password, String user) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		if(user=="buyer") {
			String q =
					"INSERT INTO sec_role(roleName)values('ROLE_BUYER')";	
			parameters.addValue("userName", userName);
			parameters.addValue("password", passwordEncoder.encode(password));
			jdbc.update(q, parameters);
		}
		if(user=="realtor") {
			String q =
					"INSERT INTO sec_role(roleName)values('ROLE_REALTOR')";	
			parameters.addValue("userName", userName);
			parameters.addValue("password", passwordEncoder.encode(password));
			jdbc.update(q, parameters);
		}
		
	}
		
}