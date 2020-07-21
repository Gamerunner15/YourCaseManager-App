package projects.Kolton.YourCaseManager;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;

import org.springframework.web.client.RestTemplate;

import projects.Kolton.YourCaseManager.models.Address;
import projects.Kolton.YourCaseManager.models.Login;
import projects.Kolton.YourCaseManager.models.MedicalCondition;
import projects.Kolton.YourCaseManager.models.User;
import projects.Kolton.YourCaseManager.services.UserService;
import projects.Kolton.YourCaseManager.view.MainMenu;


/**
 * Hello world!
 *
 */
public class AppCLI {
	private Scanner userScanner = new Scanner(System.in);
	private MainMenu mainMenu;
	private UserService userService;
	private User user;
	private final RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {

		AppCLI cli = new AppCLI();

		cli.run(cli);

	}

	public void run(AppCLI cli) {

		mainMenu = new MainMenu(cli);
		userService = new UserService(mainMenu);

		boolean loop = true;
		while (loop) {
			loop = mainMenu.start();
		}

	}

	public boolean startHandler(String response) {
		if (response.equals("1")) {
			if (this.logIn() == false) {
				return true;
			} else {
				boolean keepGoing = true;
				while (keepGoing) {
					keepGoing = mainMenu.menuOptions();
				}
			}
			return true;

		} else if (response.equals("2")) {
			userService.createUser(this.createAccount());
			System.out.println("Success!");
			return true;

		} else if (response.equalsIgnoreCase("Q")) {
			System.out.println("Goodbye!");
			return false;
		} else {
			System.out.println("Please enter a valid response.");
			return true;
		}
	}

	public boolean menuOptionsHandler(String response) {
		if (response.equals("1")) {
			this.updateInformationMenu();
			return true;
		} else if (response.equals("2")) {
			this.printAllInfo();
			return true;
		} else if (response.equals("3")) {
			this.smartCaseManager();
			return true;
		} else if (response.equalsIgnoreCase("R")) {
			return false;
		} else {
			System.out.println("Please select a valid option.");
			return true;
		}
	}

	public boolean logIn() {
		System.out.print("Username: ");
		String username = userScanner.nextLine();
		System.out.print("Password: ");
		String password = userScanner.nextLine();
		Login login = new Login(username, password);
		boolean valid = userService.validateLogin(login);

		if (!valid) {
			System.out.println("Invalid username or password.");
			return false;
		} else {
			user = userService.getUserInfo(username);
			return true;
		}
	}

	public Login createAccount() {
		boolean isValid = false;
		String username = null;
		while (!isValid) {
			System.out.println("Enter your desired Username:");
			username = userScanner.nextLine();
			isValid = userService.validateUsername(username);
			if (!isValid) {
				System.out.println("That Username is taken. Please try again.");
			}
		}
		String password = "";
		boolean matches = false;
		while(!matches) {
		System.out.println("Enter your password:");
		password = userScanner.nextLine();
		System.out.println("Confirm your password:");
		String confirmPassword = userScanner.nextLine();
		if(password.equals(confirmPassword)) {
			matches = true;
		}
		}

		Login login = new Login(username, password);
		return login;
	}

	public void printAllInfo() {
		String fullAddress = "";
		if(!(user.getAddress() == null)) {
			fullAddress = user.getAddress().getFullAddress();
		}
		System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
		System.out.println("Email Address: " + user.getEmailAddress());
		System.out.println("Address: " + fullAddress);
		System.out.println("Birth Date: " + user.getBirthDate());
		System.out.println("Annual Income: " + user.getAnnualIncome());
		System.out.println("Gender: " + user.getGender());
		System.out.println("Need Cell Phone: " + user.isNeedsCellPhone());
		System.out.println("Need Housing: " + user.isNeedsHousing());
		System.out.println("Need Job: " + user.isNeedsJob());
		System.out.println("Need Transport: " + user.isNeedsTransport());
		System.out.println("Need Food: " + user.isNeedsFood() + "\n");

		System.out.println("You can add or update information in the options menu.");

	}
	
	public void smartCaseManager() {
		System.out.println("Yo Whats up?");
		String response = userScanner.nextLine();
		System.out.println("Cool beans. Let's see what I can do to help.");
		
	}

	public void updateInformationMenu() {
		
		//Update or Enter Name fields
		if(user.getFirstName() == null || user.getLastName() == null) {
			System.out.println("What is your first name?");
			user.setFirstName(userScanner.nextLine());
			System.out.println("What is your last name?");
			user.setLastName(userScanner.nextLine());
		} else {
			System.out.println("Would you like to update your name?(Y OR N)");
			String response = userScanner.nextLine();
			if (response.equalsIgnoreCase("Y")) {
				System.out.println("What is your first name?");
				user.setFirstName(userScanner.nextLine());
				System.out.println("What is your last name?");
				user.setLastName(userScanner.nextLine());
			} else if (!response.equalsIgnoreCase("N")) {
				System.out.println("Not a valid response. Moving on.");
			}
		}
		
		if(user.getBirthDate() == null) {
			String birthDate = "";
			boolean valid = false;
			while(!valid) {
				System.out.println("What is your birth date? (YYYY-MM-DD)");
				birthDate = userScanner.nextLine();
				valid = this.isBirthDateValid(birthDate);
			}
			user.setBirthDate(birthDate);
		}
		
		//email address question
		System.out.println("Would you like to update your Email Address?(Y OR N)");
		String response = userScanner.nextLine();
		if (response.equalsIgnoreCase("Y")) {
			this.updateEmailAddress();
		} else if (!response.equalsIgnoreCase("N")) {
			System.out.println("Not a valid response. Moving on.");
		}
		//Address question
		System.out.println("Would you like to update your address?(Y OR N)");
		response = userScanner.nextLine();
		if (response.equalsIgnoreCase("Y")) {
			this.updateAddress();
		} else if (!response.equalsIgnoreCase("N")) {
			System.out.println("Not a valid response. Moving on.");
		}
		//income question
		System.out.println("Would you like to update your annual income?(Y OR N)");
		response = userScanner.nextLine();
		if (response.equalsIgnoreCase("Y")) {
			this.updateIncome();
		} else if (!response.equalsIgnoreCase("N")) {
			System.out.println("Not a valid response. Moving on.");
		}
		//gender question
		System.out.println("Would you like to update your gender?(Y OR N)");
		response = userScanner.nextLine();
		if (response.equalsIgnoreCase("Y")) {
			this.updateGender();
		} else if (!response.equalsIgnoreCase("N")) {
			System.out.println("Not a valid response. Moving on.");
		}

		
		//Job Question
		System.out.println("Do you need a job? (Y or N)");
		response = userScanner.nextLine();
		if(response.equalsIgnoreCase("Y")) {
			user.setNeedsJob(true);
		} else if (response.equalsIgnoreCase("N")) {
			user.setNeedsJob(false);
		} else {
			System.out.println("Not a valid response. Moving on.");
		}
		
		//Food Question
		System.out.println("Do you need food? (Y or N)");
		response = userScanner.nextLine();
		if(response.equalsIgnoreCase("Y")) {
			user.setNeedsFood(true);
		} else if (response.equalsIgnoreCase("N")) {
			user.setNeedsFood(false);
		} else {
			System.out.println("Not a valid response. Moving on.");
		}
		
		//Housing Question
		System.out.println("Do you need housing? (Y or N)");
		response = userScanner.nextLine();
		if(response.equalsIgnoreCase("Y")) {
			user.setNeedsHousing(true);
		} else if (response.equalsIgnoreCase("N")) {
			user.setNeedsHousing(false);
		} else {
			System.out.println("Not a valid response. Moving on.");
		}
		
		//Cellphone question
		System.out.println("Do you need a cell phone? (Y or N)");
		response = userScanner.nextLine();
		if(response.equalsIgnoreCase("Y")) {
			user.setNeedsCellPhone(true);
		} else if (response.equalsIgnoreCase("N")) {
			user.setNeedsCellPhone(false);
		} else {
			System.out.println("Not a valid response. Moving on.");
		}
		//Medical Conditions Question
		System.out.println("Do you need to add existing medical conditions? (Y or N)");
		response = userScanner.nextLine();
		if(response.equalsIgnoreCase("Y")) {
			this.updateMedicalConditions();
		} else if (!response.equalsIgnoreCase("N")) {
			System.out.println("Not a valid response. Moving on.");
		}

		userService.updateUserInfo(user);
		System.out.println("Update Complete!");

	}
	
	public void updateEmailAddress() {
		System.out.println("Please provide your email address:");
		boolean valid = false;
		String emailAddress = "";
		while (!valid) {
			try {
				emailAddress = userScanner.nextLine();
				if (!emailAddress.contains("@")) {
					throw new Exception();
				}

				valid = true;
			} catch (Exception e) {
				System.out.println("Invalid response.");
				valid = false;
			}
		}
		user.setEmailAddress(emailAddress);
	}

	public void updateAddress() {
		System.out.println("Please provide your street address: (Ex: 1111 StreetName St West)");
		String streetAddress = userScanner.nextLine();
		System.out.println("Please provide your city: ");
		String city = userScanner.nextLine();
		System.out.println("Please provide your state: ");
		String state = userScanner.nextLine();
		System.out.println("Please provide your zipcode: ");
		int zipcode = 0;
		boolean valid = false;
		while (!valid) {
			try {
				zipcode = Integer.parseInt(userScanner.nextLine());

				if (zipcode < 10000) {
					throw new Exception();
				}
				valid = true;
			} catch (Exception e) {
				valid = false;
				System.out.println("Invalid zipcode. Try again.");
			}
		}
		System.out.println("Please provide the building type: ");
		String buildingType = userScanner.nextLine();
		Address address = new Address(streetAddress, city, state, zipcode, buildingType);
		user.setAddress(address);
	}

	public void updateIncome() {
		System.out.println("Please provide the estimated annual income for your household:");
		boolean valid = false;
		double income = 0;
		while (!valid) {
			try {
				income = Double.parseDouble(userScanner.nextLine());
				valid = true;
			} catch (Exception e) {
				System.out.println("Invalid response.");
				valid = false;
			}
		}
		user.setAnnualIncome(income);;
	}

	public void updateGender() {
		System.out.println("Please provide your gender-identity:");
		boolean valid = false;
		String gender = "";
		while (!valid) {
			try {
				gender = userScanner.nextLine();
				if (gender.equalsIgnoreCase("dinosaur")) {
					throw new Exception();
				}

				valid = true;
			} catch (Exception e) {
				System.out.println("Invalid response.");
				valid = false;
			}
		}
		user.setGender(gender);
	}
	
	
	
	public void updateMedicalConditions() {
		System.out.println("Your current list of medical conditions is:");
		List<MedicalCondition> conditions = user.getMedicalConditions();
		if(conditions != null) {
		for (MedicalCondition condition : user.getMedicalConditions()) {
			System.out.println("- " + condition.getName());
		}
		}
		System.out.println("Please list any additional medical conditions: (separated only by commas)");
		String response = userScanner.nextLine();
		String[] responseSplit = response.split(",");
		for (String name : responseSplit) {
			MedicalCondition condition = new MedicalCondition();
			condition.setName(name);
			user.addMedicalCondition(condition);
		}
		
		System.out.println("Your new list of medical conditions is:");
		for (MedicalCondition condition : user.getMedicalConditions()) {
			System.out.println("- " + condition.getName());
		}

	}
	

	// -----------------------------------Validation--------------------
	public boolean isDateValid(String date) {
		ZoneId z = ZoneId.of("America/Montreal");
		LocalDate today = LocalDate.now(z);

		try {
			LocalDate newDate = LocalDate.parse(date);

			if (newDate.isBefore(today)) {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println("Please enter a valid date. Example: YYYY-MM-DD");
			return false;
		}
		return true;
	}
	
	public boolean isBirthDateValid(String date) {
		ZoneId z = ZoneId.of("America/Montreal");
		LocalDate today = LocalDate.now(z);

		try {
			LocalDate newDate = LocalDate.parse(date);

			if (newDate.isAfter(today)) {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println("Please enter a valid date. Example: YYYY-MM-DD");
			return false;
		}
		return true;
	}
}
