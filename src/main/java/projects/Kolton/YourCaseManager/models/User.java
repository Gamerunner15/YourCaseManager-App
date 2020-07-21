package projects.Kolton.YourCaseManager.models;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;


public class User {
	
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String birthDate;
	private Address address;
	private int addressId;
	private String gender;
	private double annualIncome;
	private boolean needsJob;
	private boolean needsHousing;
	private boolean needsFood;
	private boolean needsCellPhone;
	private boolean needsTransport;
	private List<MedicalCondition> medicalConditions;
	
	public User() {
		
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

	public boolean isNeedsJob() {
		return needsJob;
	}

	public void setNeedsJob(boolean needsJob) {
		this.needsJob = needsJob;
	}

	public boolean isNeedsHousing() {
		return needsHousing;
	}

	public void setNeedsHousing(boolean needsHousing) {
		this.needsHousing = needsHousing;
	}

	public boolean isNeedsFood() {
		return needsFood;
	}

	public void setNeedsFood(boolean needsFood) {
		this.needsFood = needsFood;
	}

	public boolean isNeedsCellPhone() {
		return needsCellPhone;
	}

	public void setNeedsCellPhone(boolean needsCellPhone) {
		this.needsCellPhone = needsCellPhone;
	}

	public boolean isNeedsTransport() {
		return needsTransport;
	}

	public void setNeedsTransport(boolean needsTransport) {
		this.needsTransport = needsTransport;
	}

	public List<MedicalCondition> getMedicalConditions() {
		return medicalConditions;
	}

	public void setMedicalConditions(List<MedicalCondition> medicalConditions) {
		this.medicalConditions = medicalConditions;
	}
	
	public void addMedicalCondition(MedicalCondition condition) {
		if(medicalConditions == null) {
			medicalConditions = new ArrayList<MedicalCondition>();
		}
		medicalConditions.add(condition);
	}
	

}
