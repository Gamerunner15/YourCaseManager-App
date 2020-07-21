package projects.Kolton.YourCaseManager.view;

import java.util.Scanner;

import projects.Kolton.YourCaseManager.AppCLI;
import projects.Kolton.YourCaseManager.models.User;

public class MainMenu {
	
	private Scanner userScanner = new Scanner(System.in);
	private AppCLI cli;
	private User user;
	
	public MainMenu(AppCLI cli) {
		this.cli = cli;
	}
	
	public boolean start() {
		System.out.println("Welcome to the Columbus Resource Guide! \n");
		System.out.println("(1) Login or (2) Create Account? (Q) Quit");
		String response = userScanner.nextLine();
		return cli.startHandler(response);
		
	}
	
	public boolean menuOptions() {
		System.out.println("What would you like to do?");
		System.out.println("1) Answer questions to improve reccomendations");
		System.out.println("2) List your information");
		System.out.println("3) Talk to Your Case Manager");
		System.out.println("R) Return to Main Menu");
		String response = userScanner.nextLine();
		
		return cli.menuOptionsHandler(response);
	}
	

}
