package projects.Kolton.YourCaseManager.models;

public class Address {
	
	private String streetAddress;
	private String cityName;
	private String stateName;
	private int zipCode;
	private String buildingType;
	
	public Address() {
		
	}
	
	
	public Address(String streetAddress, String cityName, String stateName, int zipCode, String buildingType) {
		this.streetAddress = streetAddress;
		this.cityName = cityName;
		this.stateName = stateName;
		this.zipCode = zipCode;
		this.buildingType = buildingType;
		
	}
	
	public String getFullAddress() {
		return streetAddress + " " + cityName + ", " + stateName + " " + zipCode;
	}


	public String getStreetAddress() {
		return streetAddress;
	}


	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}


	public int getZipCode() {
		return zipCode;
	}


	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}


	public String getBuildingType() {
		return buildingType;
	}


	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	
	

}
